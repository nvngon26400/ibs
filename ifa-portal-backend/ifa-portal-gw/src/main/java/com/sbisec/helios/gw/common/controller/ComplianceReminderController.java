package com.sbisec.helios.gw.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.RowCount;
import com.sbibits.earth.servlet.annotation.Popup;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.model.FrameworkSessionInfo;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.service.TokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * コンプライアンスチェック用コントローラ
 *
 * @author SCSK
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "MsgReminder", ignoreCheck = true)
@Popup
public class ComplianceReminderController extends BaseController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ComplianceReminderController.class);
    
    @Autowired
    private TokenService tokenService;
    
    /** Checks what reminder needs the user has and adds it to the userAccount. */
    @PostMapping("/updateNeedForComplianceReminder")
    public void updateNeedForComplianceReminder(Model model, HttpServletRequest request) throws Exception {
        
        LOGGER.debug("updateNeedForComplianceReminder()");
        
        boolean complianceLetters = checkComplianceReminderLetters(request);
        boolean monthlySelfcheck = checkRemindMonthlySelfcheck(request);
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        userAccount.setUserNeedsToReadComplianceLetters(complianceLetters);
        userAccount.setUserNeedsToReadMonthlySelfcheck(monthlySelfcheck);
        
        // ユーザ共通情報を更新する
        FrameworkSessionInfo sessionInfo = tokenService
                .getSessionInfo((String) request.getAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID));
        tokenService.updateSessionInfo(sessionInfo, userAccount);
        request.setAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        
    }
    
    /** Return true if this user should be reminded to check compliance letters right away.
     *
     * @throws Exception 例外 */
    private boolean checkComplianceReminderLetters(HttpServletRequest request) throws Exception {
        
        UserAccount userAccount = getUserAccount(request);
        
        //-----------------------------------------------------------
        // Users with BROKER_ID = 0509 (マネプラ) shouldn't be reminded
        
        String brokerId = userAccount.getBrokerId();
        if (brokerId != null && brokerId.equals("0509")) {
            return false;
        }
        
        String reminderCompliance = ApiRequestUtil.invoke(ServiceNameConstants.MED_SYSTEM_VARIABLE_SERVICE,
                "getMedSystemVariable", new TypeReference<String>() {
                }, "REMINDER_COMPLIANCE");
        String reminderComplianceStartYm = ApiRequestUtil.invoke(ServiceNameConstants.MED_SYSTEM_VARIABLE_SERVICE,
                "getMedSystemVariable", new TypeReference<String>() {
                }, "REMINDER_COMPLIANCE_START_YM");
        String reminderComplianceStartDate = ApiRequestUtil.invoke(ServiceNameConstants.MED_SYSTEM_VARIABLE_SERVICE,
                "getMedSystemVariable", new TypeReference<String>() {
                }, "REMINDER_COMPLIANCE_START_DATE");
        
        // RT2484 - コンプライアンス通信の未読通知の仕様変更
        if (userAccount.getCreateTime() != null) {
            String userCreateMonth = new SimpleDateFormat("yyyyMM").format(userAccount.getCreateTime());
            if (reminderComplianceStartYm.compareTo(userCreateMonth) < 0) {
                reminderComplianceStartYm = userCreateMonth;
            }
        }
        
        //-----------------------------------------------------------
        // Users with privId which belongs to
        // TB_MED_SYSTEM_VARIABLE::REMINDER_COMPLIANCE are subject to reminders
        
        String privId = userAccount.getPrivId();
        if (!reminderCompliance.contains(privId)) {
            LOGGER.debug("Compliance letter check: This user has wrong privId for user compliance.");
            return false;
        }
        
        //-----------------------------------------------------------
        // Only remind users who are actually allowed to access the page
        
        boolean userCanSeeView = false;
        for (String viewStr : userAccount.getAccessibleViewList()) {
            if (viewStr != null && viewStr.equals("SUB0302-01")) {
                userCanSeeView = true;
                break;
            }
        }
        if (!userCanSeeView) {
            LOGGER.debug("Compliance letter check: This user can't see the compliance letter view.");
            return false;
        }
        
        //-----------------------------------------------------------
        // All of the compliance letters from REMINDER_COMPLIANCE_START_YM
        // to previous month should be confirmed
        
        String currentMonth = null;
        String userId = userAccount.getUserId();
        String brokerCode = userAccount.getBrokerCode();
        DataList<RowCount> numberOfMissedComplianceData = ApiRequestUtil.invoke(ServiceNameConstants.COMPLIANCE_SERVICE,
                "countMissedComplianceForPreviousMonths", new TypeReference<DataList<RowCount>>() {
                }, userId, brokerCode, reminderComplianceStartYm, currentMonth);
        
        if (numberOfMissedComplianceData.get(0).getRowCount() > 0) {
            LOGGER.debug("Compliance letter check: User has unread compliance letters from earlier months.");
            return true;
        }
        
        //-----------------------------------------------------------
        // No need to remind if the current date is below the monthly reminder date
        
        String todaysDate = new SimpleDateFormat("dd").format(new Date());
        int todaysDateInt = Integer.parseInt(todaysDate);
        int checkDeadlineDate = Integer.parseInt(reminderComplianceStartDate);
        if (todaysDateInt < checkDeadlineDate) {
            LOGGER.debug(
                    "Compliance letter check: No need to be reminded about compliance, current date is below start date");
            return false;
        }
        
        //-----------------------------------------------------------
        // Check if there are any unread compliance letters from this month
        
        DataList<RowCount> numberOfMissedComplianceFromThisMonthData = ApiRequestUtil.invoke(
                ServiceNameConstants.COMPLIANCE_SERVICE, "countMissedComplianceForTargetMonth",
                new TypeReference<DataList<RowCount>>() {
                }, userId, brokerCode, currentMonth);
        if (numberOfMissedComplianceFromThisMonthData.get(0).getRowCount() > 0) {
            LOGGER.debug("Compliance letter check: User has unread compliance letters from this month.");
            return true;
        }
        
        LOGGER.debug("Compliance letter check: User doesn't have unread compliance letters from this month.");
        return false;
    }
    
    private boolean checkRemindMonthlySelfcheck(HttpServletRequest request) throws Exception {
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        String reminderSelfcheck = ApiRequestUtil.invoke(ServiceNameConstants.MED_SYSTEM_VARIABLE_SERVICE,
                "getMedSystemVariable", new TypeReference<String>() {
                }, "REMINDER_SELFCHECK");
        String reminderSelfcheckStartDate = ApiRequestUtil.invoke(ServiceNameConstants.MED_SYSTEM_VARIABLE_SERVICE,
                "getMedSystemVariable", new TypeReference<String>() {
                }, "REMINDER_SELFCHECK_START_DATE");
        
        //-----------------------------------------------------------
        // Only remind users with privId which belongs to
        // TB_MED_SYSTEM_VARIABLE::REMINDER_SELFCHECK
        
        String privId = userAccount.getPrivId();
        if (!reminderSelfcheck.contains(privId)) {
            return false;
        }
        
        //-----------------------------------------------------------
        // Only remind users who are actually allowed to access the page
        
        boolean userCanSeeView = false;
        for (String viewStr : userAccount.getAccessibleViewList()) {
            if (viewStr != null && viewStr.equals("SUB0401_02-01")) {
                userCanSeeView = true;
                break;
            }
        }
        if (!userCanSeeView) {
            return false;
        }
        
        //-----------------------------------------------------------
        // No need to remind if the current date is below the monthly reminder date
        
        String todaysDate = new SimpleDateFormat("dd").format(new Date());
        int todaysDateInt = Integer.parseInt(todaysDate);
        int checkDeadlineDate = Integer.parseInt(reminderSelfcheckStartDate);
        if (todaysDateInt < checkDeadlineDate) {
            LOGGER.debug("No need to be reminded about selfcheck, current date is below start date.");
            return false;
        }
        
        //-----------------------------------------------------------
        // Remind user if there are unread letters from this month
        
        String currentMonth = new SimpleDateFormat("yyyyMM").format(new Date());
        String brokerCode = userAccount.getBrokerCode();
        DataList<RowCount> unreadLettersOfThisMonthCountData = ApiRequestUtil.invoke(
                ServiceNameConstants.COMPLIANCE_SERVICE, "countUnreadSelfcheckLettersForMonth",
                new TypeReference<DataList<RowCount>>() {
                }, brokerCode, currentMonth);
        if (unreadLettersOfThisMonthCountData.get(0).getRowCount() > 0) {
            LOGGER.debug("User has unread selfcheck letters from this month.");
            return true;
        }
        
        LOGGER.debug("User doesn't have unread selfcheck letters from this month.");
        return false;
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
}

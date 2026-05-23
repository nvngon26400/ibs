package com.sbisec.helios.gw.common.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.MedUsers;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.constants.LoginMessageKeyConstants;
import com.sbisec.helios.gw.common.form.ChangePwForm;
import com.sbisec.helios.gw.common.util.EncryptPassword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * パスワード変更
 *
 * @author SCSK
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "UpdatePassword", ignoreCheck = true)
public class UpdatePasswordController extends BaseController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePasswordController.class);
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * FCT015_パスワード変更
     *
     * @param changePwForm 入力項目
     * @param result 入力項目バリデーションチェック結果
     * @param request リクエスト
     * @param response レスポンス
     * @param model 結果モデル
     * @return 変更結果
     * @throws Exception 任意の例外
     */
    @PostMapping("/login/ifaPasswordChangePasswordChangeA003")
    @ResponseJson
    public String changePassword(@RequestBody @Validated ChangePwForm changePwForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        
        LOGGER.debug("Entering password change");
        
        List<String> problemStrings = new ArrayList<String>();
        
        String passwordErrorMessage = getMessage(ERRORS_ACCURATELY, new String[] { "パスワード" });
        
        if (result.hasFieldErrors("oldPassword")) {
            LOGGER.debug("Error in old password format");
            problemStrings.add("旧パスワード: " + passwordErrorMessage);
        }
        
        if (result.hasFieldErrors("newPassword")) {
            LOGGER.debug("Error in new password format");
            problemStrings.add("新パスワード: " + passwordErrorMessage);
        }
        
        if (result.hasFieldErrors("newPasswordRepeat")) {
            LOGGER.debug("Error in repeated password format");
            problemStrings.add("新パスワード（確認入力）: " + passwordErrorMessage);
        }
        

        UserAccount ua = IfaCommonUtil.getUserAccount();
        MedUsers medUsers = ua.getMedUsers();
        String userId = medUsers.getUserId();
        String oldPassword = EncryptPassword.encrypt(changePwForm.getOldPassword());
        String newPassword = EncryptPassword.encrypt(changePwForm.getNewPassword());
        String newPasswordRepeat = EncryptPassword.encrypt(changePwForm.getNewPasswordRepeat());
        
        // Matching userId and password.
        UserAccount userAccount = ApiRequestUtil.invoke(ServiceNameConstants.USER_ADMINISTRATION_SERVICE,
                "getUserAccount", new TypeReference<UserAccount>() {
                }, userId, null);
        
        // User is not found, or failed to verify password.
        if (userAccount == null || !userAccount.getPassword().equals(oldPassword)) {
            LOGGER.debug("Current password doesn't match with database password.");
            problemStrings.add(getMessage(LoginMessageKeyConstants.ERRORS_LOGIN_NOT_EXIST_PASSWORD, null));
        }
        
        // Check matching new passwords
        if (!newPassword.equals(newPasswordRepeat)) {
            LOGGER.debug("New password doesn't match the repeated password.");
            problemStrings.add(getMessage(LoginMessageKeyConstants.ERRORS_LOGIN_NOT_MATCH_VERIFY_PASSWORD, null));
        }
        
        // Check matching new passwords
        if (newPassword.equals(oldPassword)) {
            LOGGER.debug("New password can't be same as old password");
            problemStrings.add(getMessage(LoginMessageKeyConstants.ERRORS_LOGIN_NOT_ALLOWED_SAME_OLD_PASSWORD, null));
        }
        
        // Check password not same as id
        if (changePwForm.getNewPassword().equals(userId)) {
            LOGGER.debug("New password can't be same as user id");
            problemStrings.add(getMessage(LoginMessageKeyConstants.ERRORS_LOGIN_NOT_ALLOWED_SAME_USER_ID, null));
        }
        
        DataList<String> returnData = new DataList<String>();
        returnData.setRequestedTime(getFormattedRequestedTime(request));
        
        if (!problemStrings.isEmpty()) {
            returnData.setErrorLevel(ErrorLevel.FATAL.getId());
            returnData.setReturnCode("LOGIN_PASSWORD_ERROR");
            returnData.setDataList(problemStrings);
            returnData.setTotalSize(problemStrings.size());
            returnData.setMaxRownum(problemStrings.size());
            return jc.toString(returnData);
        }
        
        int result2 = ApiRequestUtil.invoke(ServiceNameConstants.USER_ADMINISTRATION_SERVICE, "updateMedUsersPassword",
                new TypeReference<Integer>() {
                }, userId, newPassword);
        
        // We expect a change to one row
        if (result2 != 1) {
            returnData.setErrorLevel(ErrorLevel.SYSTEM_ERROR.getId());
            returnData.setReturnCode("ERROR");
            returnData.setMessage(getMessage(LoginMessageKeyConstants.ERRORS_LOGIN_PASSWORD_CHANGE_ERROR, null));
            return jc.toString(returnData);
        }
        
        returnData.setErrorLevel(ErrorLevel.SUCCESS.getId());
        returnData.setReturnCode("SUCCESS");
        return jc.toString(returnData);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}

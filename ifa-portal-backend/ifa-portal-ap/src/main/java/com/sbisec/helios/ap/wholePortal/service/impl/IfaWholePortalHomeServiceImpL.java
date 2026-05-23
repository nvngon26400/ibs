package com.sbisec.helios.ap.wholePortal.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.MedUsers;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.wholePortal.dao.IfaWholePortalHomeDao;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql001RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql001ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql002RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql002ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql003RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql003ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql004ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql005RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql005ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql006RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql006ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql007RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaWholePortalHomeSql007ResponseModel;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001ResponseDtoCustomerAlert;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001ResponseDtoManagerAlertComplianceReport;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001ResponseDtoManagerAlertSelfAssessment;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001ResponseDtoNotification;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001ResponseDtoNotificationCategory;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA011RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA011ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA018RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA018ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA018ResponseDtoManagerAlertComplianceReport;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA018ResponseDtoManagerAlertSelfAssessment;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA021RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA021ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA021ResponseDtoNotification;
import com.sbisec.helios.ap.wholePortal.service.IfaWholePortalHomeService;


/**
 * 画面ID：SUB01-01
 * 画面名：総合ポータル_ホーム

 * @author 池亀緑
 *
 */
@Component(value = "cmpIfaWholePortalHomeService")
public class IfaWholePortalHomeServiceImpL implements IfaWholePortalHomeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaWholePortalHomeServiceImpL.class);

    @Autowired
    private IfaWholePortalHomeDao dao;

    @Autowired
    private Fct007 fct007;

    @Autowired
    private Fct030 fct030;

    @Autowired
    FileDownloadUtil fileDownloadUtil;

    @Autowired
    private IfaDateUtil ifaDateUtil;

    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATA_LIST_NOTFOUND = "errors.dataList.notfound";

    /**権限コード(1,3,6)*/
    private static final List<String> SQL002_PRIV_ID = Arrays.asList(PrivId.HEAD_OFFICE.getId(),
            PrivId.B_INNER_MANAGEMENT.getId(),
            PrivId.BB_INNER_MANAGEMENT.getId());

    /**権限コード(3,6)*/
    private static final List<String> SQL003_PRIV_ID = Arrays.asList(PrivId.B_INNER_MANAGEMENT.getId(),
            PrivId.BB_INNER_MANAGEMENT.getId());

    /** コンプライアンス通信 未回答あり */
    private static final String UNREPLY_FLAG_TRUE = "1";

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaWholePortalHomeA001RequestDto
     * Dto レスポンス：IfaWholePortalHomeA001ResponseDto
     */
    public DataList<IfaWholePortalHomeA001ResponseDto> initializeA001(IfaWholePortalHomeA001RequestDto dtoReq)
            throws Exception {

        DataList<IfaWholePortalHomeA001ResponseDto> dtoRes = new DataList<IfaWholePortalHomeA001ResponseDto>();
        List<IfaWholePortalHomeA001ResponseDto> resMainList = new ArrayList<>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWholePortalHomeServiceImplL.initializeA001");
        }

        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        MedUsers medUsers = userAccount.getMedUsers();

        if (!PrivId.HEAD_OFFICE.getId().equals(medUsers.getPrivId())) {
            OutputFct030Dto fct030Result = fct030.getData(new InputFct030Dto());
            if (fct030Result == null || fct030Result.getBrokerChargeList() == null
                    || fct030Result.getBrokerChargeList().size() == 0) {
                // 業務エラーメッセージの取得
                String message = IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST, new String[] {});
                dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ErrorLevel.FATAL.name(), message);
                return dtoRes;
            }
        }

        //SQL001
        IfaWholePortalHomeSql001RequestModel selSql001Req = new IfaWholePortalHomeSql001RequestModel();
        selSql001Req.setUserId(userAccount.getUserId());
        selSql001Req.setPrivId(medUsers.getPrivId());
        DataList<IfaWholePortalHomeSql001ResponseModel> selSql001Res = dao.selectIfaWholePortalHomeSql001(selSql001Req);

        List<IfaWholePortalHomeA001ResponseDtoCustomerAlert> customerAlertList = new ArrayList<IfaWholePortalHomeA001ResponseDtoCustomerAlert>();
        selSql001Res.getDataList().forEach(d -> {
            IfaWholePortalHomeA001ResponseDtoCustomerAlert customerAlert = new IfaWholePortalHomeA001ResponseDtoCustomerAlert();
            customerAlert.setAlertDate(d.getAlertDate());
            customerAlert.setAlertId(d.getAlertId());
            customerAlertList.add(customerAlert);
        });
        IfaWholePortalHomeA001ResponseDto res = new IfaWholePortalHomeA001ResponseDto();
        res.setCustomerAlertList(customerAlertList);

        //SQL002
        if (SQL002_PRIV_ID.contains(medUsers.getPrivId())) {
            IfaWholePortalHomeSql002RequestModel selSql002Req = new IfaWholePortalHomeSql002RequestModel();
            selSql002Req.setPrivId(medUsers.getPrivId());
            selSql002Req.setUserId(userAccount.getUserId());

            DataList<IfaWholePortalHomeSql002ResponseModel> selSql002Res = dao
                    .selectIfaWholePortalHomeSql002(selSql002Req);
            List<IfaWholePortalHomeSql002ResponseModel> sql002ResList = selSql002Res.getDataList();

            String thisMonth = ifaDateUtil.format(IfaDateUtil.YYYYMM);
            int sysDateInt = Integer.parseInt(thisMonth);

            IfaWholePortalHomeA001ResponseDtoManagerAlertComplianceReport managerAlertComplianceReport = new IfaWholePortalHomeA001ResponseDtoManagerAlertComplianceReport();

            //最小の通信年月を算出
            List<Integer> yearMonthList = new ArrayList<Integer>();
            for (IfaWholePortalHomeSql002ResponseModel resYearMonth : sql002ResList) {
                if (resYearMonth.getYearMonth() != null && !resYearMonth.getYearMonth().isEmpty()) {
                    yearMonthList.add(Integer.parseInt(resYearMonth.getYearMonth()));
                }
            }

            Integer minYearMonth = yearMonthList.stream().min(Comparator.naturalOrder()).orElse(null);

            for (IfaWholePortalHomeSql002ResponseModel sql002Res : sql002ResList) {
                if (sql002Res.getYearMonth() != null && !sql002Res.getYearMonth().isEmpty()) {
                    int resDateInt = Integer.parseInt(sql002Res.getYearMonth());
                    //通信年月＝今月未閲覧の営業員あり
                    if (resDateInt == sysDateInt
                            && !UNREPLY_FLAG_TRUE.equals(managerAlertComplianceReport.getThisMonthUnreplyFlag())) {
                        managerAlertComplianceReport.setThisMonthUnreplyFlag(UNREPLY_FLAG_TRUE);
                        managerAlertComplianceReport.setTitleThisMonth(sql002Res.getTitle());
                    }
                    //通信年月＜今月未閲覧の営業員あり、かつ最小の通信年月の場合
                    if (resDateInt < sysDateInt && (minYearMonth != null && resDateInt == minYearMonth)
                            && !UNREPLY_FLAG_TRUE.equals(managerAlertComplianceReport.getLastMonthUnreplyFlag())) {
                        managerAlertComplianceReport.setLastMonthUnreplyFlag(UNREPLY_FLAG_TRUE);
                        managerAlertComplianceReport.setTitleLastMonth(sql002Res.getTitle());
                    }

                    // 当月未回答、過去月未回答がどちらも1件ずつ取得できたら、それ以上は検索を行わない
                    if (UNREPLY_FLAG_TRUE.equals(managerAlertComplianceReport.getThisMonthUnreplyFlag())
                            && UNREPLY_FLAG_TRUE.equals(managerAlertComplianceReport.getLastMonthUnreplyFlag())) {
                        break;
                    }
                }
            }
            res.setManagerAlertComplianceReport(managerAlertComplianceReport);

            if (SQL003_PRIV_ID.contains(medUsers.getPrivId())) {
                //SQL003
                IfaWholePortalHomeSql003RequestModel selSql003Req = new IfaWholePortalHomeSql003RequestModel();
                selSql003Req.setPrivId(medUsers.getPrivId());
                selSql003Req.setBrokerCode(userAccount.getBrokerCode());

                DataList<IfaWholePortalHomeSql003ResponseModel> selSql003Res = dao
                        .selectIfaWholePortalHomeSql003(selSql003Req);
                List<IfaWholePortalHomeSql003ResponseModel> sql003ResList = selSql003Res.getDataList();

                //最小の登録年月
                List<Integer> registerDateList = new ArrayList<Integer>();
                for (IfaWholePortalHomeSql003ResponseModel resRegisterDate : sql003ResList) {
                    if (resRegisterDate.getRegisterDate() != null && !resRegisterDate.getRegisterDate().isEmpty()) {
                        registerDateList.add(Integer.parseInt(resRegisterDate.getRegisterDate()));
                    }
                }
                Integer minRegisterDate = registerDateList.stream().min(Comparator.naturalOrder()).orElse(null);

                String registerYearMonthThisMonth = null;
                String registerYearMonthLastMonth = null;

                for (IfaWholePortalHomeSql003ResponseModel sql003Res : sql003ResList) {
                    if (sql003Res.getRegisterDate() != null && !sql003Res.getRegisterDate().isEmpty()) {
                        int resDateInt = Integer.parseInt(sql003Res.getRegisterDate());
                        //登録年月＝今月の誤答または未回答あり
                        if (resDateInt == sysDateInt && StringUtil.isNullOrEmpty(registerYearMonthThisMonth)) {
                            registerYearMonthThisMonth = sql003Res.getRegisterDate();
                        }
                        //登録年月＜今月の誤答または未回答あり、かつ最小の登録年月の場合
                        if (resDateInt < sysDateInt && (minRegisterDate != null && resDateInt == minRegisterDate)
                                && StringUtil.isNullOrEmpty(registerYearMonthLastMonth)) {
                            registerYearMonthLastMonth = String.valueOf(minRegisterDate);
                        }

                        // 当月および過去月の誤答または未回答がどちらも1件ずつ取得できたら、それ以上は検索を行わない
                        if (UNREPLY_FLAG_TRUE.equals(registerYearMonthThisMonth)
                                && UNREPLY_FLAG_TRUE.equals(registerYearMonthLastMonth)) {
                            break;
                        }
                    }
                }
                IfaWholePortalHomeA001ResponseDtoManagerAlertSelfAssessment managerAlertSelfAssessment = new IfaWholePortalHomeA001ResponseDtoManagerAlertSelfAssessment();
                managerAlertSelfAssessment.setRegisterYearMonthLastMonth(registerYearMonthLastMonth);
                managerAlertSelfAssessment.setRegisterYearMonthThisMonth(registerYearMonthThisMonth);
                res.setManagerAlertSelfAssessment(managerAlertSelfAssessment);
            }
        }

        //SQL004
        DataList<IfaWholePortalHomeSql004ResponseModel> selSql004Res = dao.selectIfaWholePortalHomeSql004();
        List<IfaWholePortalHomeA001ResponseDtoNotificationCategory> notificationCategoryList = new ArrayList<IfaWholePortalHomeA001ResponseDtoNotificationCategory>();
        selSql004Res.getDataList().forEach(d -> {
            IfaWholePortalHomeA001ResponseDtoNotificationCategory notificationCategory = new IfaWholePortalHomeA001ResponseDtoNotificationCategory();
            notificationCategory.setCategoryId(d.getCategoryId());
            notificationCategory.setCategoryName(d.getCategoryName());
            notificationCategory.setRequiredFlag(d.getRequiredFlag());
            notificationCategoryList.add(notificationCategory);
        });
        res.setNotificationCategoryList(notificationCategoryList);

        List<IfaWholePortalHomeA001ResponseDtoNotification> notificationList = new ArrayList<IfaWholePortalHomeA001ResponseDtoNotification>();
        //ログイン者の権限が'1'（本店）または'2'（支店）の場合
        if (PrivId.HEAD_OFFICE.getId().equals(medUsers.getPrivId())
                || PrivId.BRANCH.getId().equals(medUsers.getPrivId())) {
            //SQL005
            IfaWholePortalHomeSql005RequestModel selSql005Req = new IfaWholePortalHomeSql005RequestModel();
            DataList<IfaWholePortalHomeSql005ResponseModel> selSql005Res = new DataList<IfaWholePortalHomeSql005ResponseModel>();

            selSql005Req.setUserId(userAccount.getUserId());
            selSql005Res = dao.selectIfaWholePortalHomeSql005(selSql005Req);

            for (IfaWholePortalHomeSql005ResponseModel d : selSql005Res.getDataList()) {
                IfaWholePortalHomeA001ResponseDtoNotification notification = new IfaWholePortalHomeA001ResponseDtoNotification();
                notification.setNotificationId(d.getNotificationId());
                notification.setTitle(d.getTitle());
                notification.setCategoryName(d.getCategoryName());
                notification.setUpdateTime(d.getUpdateTime());
                notification.setRegisterDayTime(d.getRegisterDayTime());
                notificationList.add(notification);
            }
        } else {
            //ログイン者の権限が'1'（本店）'2'（支店）以外の場合
            //SQL006
            IfaWholePortalHomeSql006RequestModel selSql006Req = new IfaWholePortalHomeSql006RequestModel();
            DataList<IfaWholePortalHomeSql006ResponseModel> selSql006Res = new DataList<IfaWholePortalHomeSql006ResponseModel>();

            selSql006Req.setUserId(userAccount.getUserId());
            selSql006Res = dao.selectIfaWholePortalHomeSql006(selSql006Req);

            for (IfaWholePortalHomeSql006ResponseModel d : selSql006Res.getDataList()) {
                IfaWholePortalHomeA001ResponseDtoNotification notification = new IfaWholePortalHomeA001ResponseDtoNotification();
                notification.setNotificationId(d.getNotificationId());
                notification.setTitle(d.getTitle());
                notification.setCategoryName(d.getCategoryName());
                notification.setUpdateTime(d.getUpdateTime());
                notification.setRegisterDayTime(d.getRegisterDayTime());
                notificationList.add(notification);
            }
        }
        res.setNotificationList(notificationList);

        //SQL007
        IfaWholePortalHomeSql007RequestModel selSql007Req = new IfaWholePortalHomeSql007RequestModel();
        selSql007Req.setCategoryId("0");
        selSql007Req.setFunctionId("0");
        DataList<IfaWholePortalHomeSql007ResponseModel> selSql007Res = dao.selectIfaWholePortalHomeSql007(selSql007Req);
        res.setFileDirectory(selSql007Res.get(0).getFileDirectory());

        resMainList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A011
     * アクション名：債券満期償還
     * Dto リクエスト：IfaWholePortalHomeA011RequestDto
     * Dto レスポンス：wholePortalA011DtoResponse
     */
    public DataList<IfaWholePortalHomeA011ResponseDto> bondMaturityRedemptionA011(
            IfaWholePortalHomeA011RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWholePortalHomeServiceImplL.bondMaturityRedemptionA011");
        }

        // Fct007
        InputFct007Dto inpFct007Dto = new InputFct007Dto();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(dtoReq.getPeriodDesignationFrom());
        inpFct007Dto.setStandardDate(date);
        inpFct007Dto.setDay(11);
        inpFct007Dto.setCalendarType("0");
        OutputFct007Dto outputFct007Dto = fct007.getData(inpFct007Dto);

        IfaWholePortalHomeA011ResponseDto res = new IfaWholePortalHomeA011ResponseDto();
        res.setDesignatedDate(DateUtil.format(outputFct007Dto.getDesignatedDate(), DateUtil.YYYYMMDD));

        DataList<IfaWholePortalHomeA011ResponseDto> dtoRes = new DataList<IfaWholePortalHomeA011ResponseDto>();
        List<IfaWholePortalHomeA011ResponseDto> resMainList = new ArrayList<>();
        resMainList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A018
     * アクション名：更新
     * Dto リクエスト：IfaWholePortalHomeA018RequestDto
     * Dto レスポンス：IfaWholePortalHomeA018ResponseDto
     */
    public DataList<IfaWholePortalHomeA018ResponseDto> updateA018(IfaWholePortalHomeA018RequestDto dtoReq)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWholePortalHomeServiceImplL.updateA018");
        }

        IfaWholePortalHomeA018ResponseDto res = new IfaWholePortalHomeA018ResponseDto();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        MedUsers medUsers = userAccount.getMedUsers();

        //今月を算出
        String thisMonth = ifaDateUtil.format(IfaDateUtil.YYYYMM);
        int sysDateInt = Integer.parseInt(thisMonth);

        if (SQL002_PRIV_ID.contains(medUsers.getPrivId())) {
            //SQL002
            IfaWholePortalHomeSql002RequestModel selSql002Req = new IfaWholePortalHomeSql002RequestModel();
            selSql002Req.setPrivId(medUsers.getPrivId());
            selSql002Req.setUserId(userAccount.getUserId());

            DataList<IfaWholePortalHomeSql002ResponseModel> selSql002Res = dao
                    .selectIfaWholePortalHomeSql002(selSql002Req);
            List<IfaWholePortalHomeSql002ResponseModel> sql002ResList = selSql002Res.getDataList();

            IfaWholePortalHomeA018ResponseDtoManagerAlertComplianceReport managerAlertComplianceReport = new IfaWholePortalHomeA018ResponseDtoManagerAlertComplianceReport();

            //最小の通信年月を算出
            List<Integer> yearMonthList = new ArrayList<>();

            for (IfaWholePortalHomeSql002ResponseModel resYearMonth : sql002ResList) {
                if (resYearMonth.getYearMonth() != null && !resYearMonth.getYearMonth().isEmpty()) {
                    yearMonthList.add(Integer.parseInt(resYearMonth.getYearMonth()));
                }
            }
            Integer minYearMonth = yearMonthList.stream().min(Comparator.naturalOrder()).orElse(null);

            for (IfaWholePortalHomeSql002ResponseModel sql002Res : sql002ResList) {
                if (sql002Res.getYearMonth() != null && !sql002Res.getYearMonth().isEmpty()) {
                    int resDateInt = Integer.parseInt(sql002Res.getYearMonth());
                    //通信年月＝今月未閲覧の営業員あり
                    if (resDateInt == sysDateInt
                            && !UNREPLY_FLAG_TRUE.equals(managerAlertComplianceReport.getThisMonthUnreplyFlag())) {
                        managerAlertComplianceReport.setThisMonthUnreplyFlag(UNREPLY_FLAG_TRUE);
                        managerAlertComplianceReport.setTitleThisMonth(sql002Res.getTitle());
                    }
                    //通信年月＜今月未閲覧の営業員あり、かつ最小の通信年月の場合
                    if (resDateInt < sysDateInt && (minYearMonth != null && resDateInt == minYearMonth)
                            && !UNREPLY_FLAG_TRUE.equals(managerAlertComplianceReport.getLastMonthUnreplyFlag())) {
                        managerAlertComplianceReport.setLastMonthUnreplyFlag(UNREPLY_FLAG_TRUE);
                        managerAlertComplianceReport.setTitleLastMonth(sql002Res.getTitle());
                    }

                    // 当月未回答、過去月未回答がどちらも1件ずつ取得できたら、それ以上は検索を行わない
                    if (UNREPLY_FLAG_TRUE.equals(managerAlertComplianceReport.getThisMonthUnreplyFlag())
                            && UNREPLY_FLAG_TRUE.equals(managerAlertComplianceReport.getLastMonthUnreplyFlag())) {
                        break;
                    }
                }
            }
            res.setManagerAlertComplianceReport(managerAlertComplianceReport);
        }

        if (SQL003_PRIV_ID.contains(medUsers.getPrivId())) {
            //SQL003
            IfaWholePortalHomeSql003RequestModel selSql003Req = new IfaWholePortalHomeSql003RequestModel();
            selSql003Req.setBrokerCode(userAccount.getBrokerCode());
            selSql003Req.setPrivId(userAccount.getPrivId());
            DataList<IfaWholePortalHomeSql003ResponseModel> selSql003Res = dao.selectIfaWholePortalHomeSql003(selSql003Req);
            List<IfaWholePortalHomeSql003ResponseModel> sql003ResList = selSql003Res.getDataList();

            //最小の登録年月
            List<Integer> registerDateList = new ArrayList<Integer>();
            for (IfaWholePortalHomeSql003ResponseModel resRegisterDate : sql003ResList) {
                if (resRegisterDate.getRegisterDate() != null && !resRegisterDate.getRegisterDate().isEmpty()) {
                    registerDateList.add(Integer.parseInt(resRegisterDate.getRegisterDate()));
                }
            }

            Integer minRegisterDate = registerDateList.stream().min(Comparator.naturalOrder()).orElse(null);

            String registerYearMonthThisMonth = null;
            String registerYearMonthLastMonth = null;

            for (IfaWholePortalHomeSql003ResponseModel sql003Res : sql003ResList) {
                if (sql003Res.getRegisterDate() != null && !sql003Res.getRegisterDate().isEmpty()) {
                    int resDateInt = Integer.parseInt(sql003Res.getRegisterDate());
                    //登録年月＝今月の誤答または未回答あり
                    if (resDateInt == sysDateInt && StringUtil.isNullOrEmpty(registerYearMonthThisMonth)) {
                        registerYearMonthThisMonth = sql003Res.getRegisterDate();
                    }
                    //登録年月＜今月の誤答または未回答あり、かつ最小の登録年月の場合
                    if (resDateInt < sysDateInt && (minRegisterDate != null && resDateInt == minRegisterDate)
                            && StringUtil.isNullOrEmpty(registerYearMonthLastMonth)) {
                        registerYearMonthLastMonth = String.valueOf(minRegisterDate);
                    }

                    // 当月および過去月の誤答または未回答がどちらも1件ずつ取得できたら、それ以上は検索を行わない
                    if (UNREPLY_FLAG_TRUE.equals(registerYearMonthThisMonth)
                            && UNREPLY_FLAG_TRUE.equals(registerYearMonthLastMonth)) {
                        break;
                    }
                }
            }

            IfaWholePortalHomeA018ResponseDtoManagerAlertSelfAssessment managerAlertSelfAssessment = new IfaWholePortalHomeA018ResponseDtoManagerAlertSelfAssessment();
            managerAlertSelfAssessment.setRegisterYearMonthLastMonth(registerYearMonthLastMonth);
            managerAlertSelfAssessment.setRegisterYearMonthThisMonth(registerYearMonthThisMonth);
            res.setManagerAlertSelfAssessment(managerAlertSelfAssessment);
        }

        DataList<IfaWholePortalHomeA018ResponseDto> dtoRes = new DataList<IfaWholePortalHomeA018ResponseDto>();
        List<IfaWholePortalHomeA018ResponseDto> resMainList = new ArrayList<>();
        resMainList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
     * アクションID：A021
     * アクション名：検索表示
     * Dto リクエスト：IfaWholePortalHomeA021RequestDto
     * Dto レスポンス：IfaWholePortalHomeA021ResponseDto
     */
    public DataList<IfaWholePortalHomeA021ResponseDto> searchDisplayA021(IfaWholePortalHomeA021RequestDto dtoReq)
            throws Exception {

        DataList<IfaWholePortalHomeA021ResponseDto> dtoRes = new DataList<IfaWholePortalHomeA021ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaWholePortalHomeServiceImplL.searchDisplayA021");
        }

        List<IfaWholePortalHomeA021ResponseDto> resMainList = new ArrayList<>();
        IfaWholePortalHomeA021ResponseDto res = new IfaWholePortalHomeA021ResponseDto();

        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        MedUsers medUsers = userAccount.getMedUsers();

        List<IfaWholePortalHomeA021ResponseDtoNotification> notificationList = new ArrayList<IfaWholePortalHomeA021ResponseDtoNotification>();

        //ログイン者の権限が'1'（本店）または'2'（支店）の場合
        if (PrivId.HEAD_OFFICE.getId().equals(medUsers.getPrivId())
                || PrivId.BRANCH.getId().equals(medUsers.getPrivId())) {
            IfaWholePortalHomeSql005RequestModel selSql005Req = new IfaWholePortalHomeSql005RequestModel();
            DataList<IfaWholePortalHomeSql005ResponseModel> selSql005Res = new DataList<IfaWholePortalHomeSql005ResponseModel>();

            if (CollectionUtils.isNotEmpty(dtoReq.getCategoryIdList())) {
                String categoryIdCat1 = dtoReq.getCategoryIdList().get(0).getCategoryId();
                selSql005Req.setCategoryIdCat1(categoryIdCat1);
            }
            selSql005Req.setUserId(userAccount.getUserId());
            selSql005Res = dao.selectIfaWholePortalHomeSql005(selSql005Req);

            if (selSql005Res == null || selSql005Res.getDataList().size() == 0) {
                // 業務エラーメッセージの取得
                String message = IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND, new String[] {});
                dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.INFO, ErrorLevel.INFO.name(), message);
                return dtoRes;
            } else {
                for (IfaWholePortalHomeSql005ResponseModel d : selSql005Res.getDataList()) {
                    IfaWholePortalHomeA021ResponseDtoNotification notification = new IfaWholePortalHomeA021ResponseDtoNotification();

                    notification.setNotificationId(d.getNotificationId());
                    notification.setTitle(d.getTitle());
                    notification.setCategoryName(d.getCategoryName());
                    notification.setUpdateTime(d.getUpdateTime());
                    notification.setRegisterDayTime(d.getRegisterDayTime());
                    notificationList.add(notification);
                }
            }
        } else {
            //ログイン者の権限が'1'（本店）'2'（支店）以外の場合
            //SQL006
            IfaWholePortalHomeSql006RequestModel selSql006Req = new IfaWholePortalHomeSql006RequestModel();
            DataList<IfaWholePortalHomeSql006ResponseModel> selSql006Res = new DataList<IfaWholePortalHomeSql006ResponseModel>();

            if (CollectionUtils.isNotEmpty(dtoReq.getCategoryIdList())) {
                String categoryIdCat1 = dtoReq.getCategoryIdList().get(0).getCategoryId();
                selSql006Req.setCategoryIdCat1(categoryIdCat1);
            }

            selSql006Req.setUserId(userAccount.getUserId());
            selSql006Res = dao.selectIfaWholePortalHomeSql006(selSql006Req);

            if (selSql006Res == null || selSql006Res.getDataList().size() == 0) {
                // 業務エラーメッセージの取得
                String message = IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND, new String[] {});
                dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.INFO, ErrorLevel.INFO.name(), message);
                return dtoRes;
            } else {
                for (IfaWholePortalHomeSql006ResponseModel d : selSql006Res.getDataList()) {
                    IfaWholePortalHomeA021ResponseDtoNotification notification = new IfaWholePortalHomeA021ResponseDtoNotification();

                    notification.setNotificationId(d.getNotificationId());
                    notification.setTitle(d.getTitle());
                    notification.setCategoryName(d.getCategoryName());
                    notification.setUpdateTime(d.getUpdateTime());
                    notification.setRegisterDayTime(d.getRegisterDayTime());
                    notificationList.add(notification);
                }
            }
        }
        res.setNotificationList(notificationList);
        resMainList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

}

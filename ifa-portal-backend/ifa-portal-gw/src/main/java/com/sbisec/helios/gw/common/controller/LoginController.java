package com.sbisec.helios.gw.common.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.dao.model.MedInformation;
import com.sbisec.helios.ap.common.dto.IfaLoginA001DtoRequest;
import com.sbisec.helios.ap.common.dto.PreviousBusinessDateDtoRequest;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.MenuId;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.AuthCtrlListExclusionSettings;
import com.sbisec.helios.ap.common.model.FrameworkSessionInfo;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.MedVerifyCode;
import com.sbisec.helios.ap.common.model.MedVerifyUser;
import com.sbisec.helios.ap.common.model.ReleaseNoteViewInfo;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.constants.LoginMessageKeyConstants;
import com.sbisec.helios.gw.common.enums.LoginReturnDataEnums;
import com.sbisec.helios.gw.common.form.AddEmailForm;
import com.sbisec.helios.gw.common.form.LoginForm;
import com.sbisec.helios.gw.common.form.LoginResponseForm;
import com.sbisec.helios.gw.common.service.TokenService;
import com.sbisec.helios.gw.common.util.CommonControllerHelper;
import com.sbisec.helios.gw.common.util.EncryptPassword;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;

/**
 * ログイン共通コントローラ
 *
 * @author SCSK
 *
 */
@RestController
@ScreenId(groupId = "COMMON", id = "Login", ignoreCheck = true)
@RequestMapping(value = "/login", method = { RequestMethod.POST })
public class LoginController extends BaseController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    private static final String CODE_TYPE_VERIFY_CODE = "37";
    
    private static final String CODE_1_VAILD_TIME = "2";
    
    private static final String CODE_2 = "GAP";
    
    private static final String[] MESSAGE_PARAM_ENVIRONMENT = { "環境変数" };
    
    private static volatile Map<String, Map<String, BaseController>> _controllerMapByMenuId = null; // Don't reference directlly.
    
    @Autowired
    private TokenService tokenService;
    
    /** Controller for enforcing compliance registration */
    @Autowired
    private ComplianceReminderController complianceReminderController;
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * ログイン前チェック（初回ログイン判定）
     *
     * @param loginForm ログイン入力項目
     * @param result バリデーションチェック
     * @param request HttpRequest
     * @param model Model
     * @return 結果文字列
     * @throws Exception 例外
     */
    @PostMapping("/ifaAuthCodeInputAuthA002")
    @ResponseJson
    public String preLogin(@RequestBody @Validated LoginForm loginForm, BindingResult result,
            HttpServletRequest request, Model model) throws Exception {
        
        if (result.hasFieldErrors("userId")) {
            LOGGER.debug("Something wrong with userId format.");
            return formatJsonString("badLogin", null, request);
        }
        
        if (result.hasFieldErrors("password")) {
            LOGGER.debug("Something wrong with password format.");
            return formatJsonString("badLogin", null, request);
        }
        
        String userId = loginForm.getUserId();
        String password = EncryptPassword.encrypt(loginForm.getPassword());
        
        try {
            
            // Matching userId and password.
            UserAccount userAccount = ApiRequestUtil.invoke(ServiceNameConstants.USER_ADMINISTRATION_SERVICE,
                    "getUserAccount", new TypeReference<UserAccount>() {
                    }, userId, null);
            
            // User is not found, or failed to verify password.
            if (userAccount == null || !userAccount.getPassword().equals(password)) {
                model.addAttribute("message", getMessage(LoginMessageKeyConstants.ERRORS_LOGIN_UNMATCH, null));
                LOGGER.debug("Wrong password, redirecting.");
                return formatJsonString("badLogin", null, request);
            }
            
            // Get verify user info.
            MedVerifyUser verifyUserInfo = ApiRequestUtil.invoke(ServiceNameConstants.CERTIFY_SERVICE,
                    "getVerifyUserInfo", new TypeReference<MedVerifyUser>() {
                    }, userId);
            
            if (verifyUserInfo != null) {
                if (StringUtils.isBlank(verifyUserInfo.getMailAddress())) {
                    //User first verify.
                    LOGGER.debug("User first verify.");
                    return formatJsonString("firstverify", null, request);
                } else {
                    //send verify code
                    ApiRequestUtil.invoke(ServiceNameConstants.CERTIFY_SERVICE, "sendVerifyCode",
                            new TypeReference<Boolean>() {
                            }, verifyUserInfo.getUserId(), userAccount.getUserNm(), verifyUserInfo.getMailAddress());
                    LOGGER.debug("Send verify code sucessfully.");
                    return formatJsonString("verify", null, request);
                }
            } else {
                //User is not a verifiable user.
                LOGGER.debug("User is not a verifiable user.");
                return formatJsonString("notvu", null, request);
            }
            
        } catch (Exception e) {
            //error.
            LOGGER.warn("Exception occured at preLogin. {}", e);
            return formatJsonString("systemError", null, request);
        }
    }
    
    /**
     * 初回ログイン時のメールアドレス登録
     *
     * @param addEmailForm 入力項目
     * @param result バリデーションチェック
     * @param request HttpRequest
     * @param model Model
     * @return 結果文字列
     * @throws Exception 例外
     */
    @PostMapping("/ifaMailAddressRegisterRegisterA005")
    @ResponseJson
    public String addVerifyMailAddress(@RequestBody @Validated AddEmailForm addEmailForm, BindingResult result,
            HttpServletRequest request, Model model) throws Exception {
        
        if (result.hasFieldErrors("userId")) {
            LOGGER.debug("Something wrong with userId format.");
            return formatJsonString("badLogin", null, request);
        }
        
        if (result.hasFieldErrors("password")) {
            LOGGER.debug("Something wrong with password format.");
            return formatJsonString("badLogin", null, request);
        }
        
        if (result.hasFieldErrors("mailAddress")) {
            LOGGER.debug("Something wrong with mailAdress format.");
            return formatJsonString("mailError", null, request);
        }
        
        String userId = addEmailForm.getUserId();
        String password = EncryptPassword.encrypt(addEmailForm.getPassword());
        String mailAddress = addEmailForm.getMailAddress();
        
        // Matching userId and password.
        UserAccount userAccount = ApiRequestUtil.invoke(ServiceNameConstants.USER_ADMINISTRATION_SERVICE,
                "getUserAccount", new TypeReference<UserAccount>() {
                }, userId, null);
        
        // User is not found, or failed to verify password.
        if (userAccount == null || !userAccount.getPassword().equals(password)) {
            model.addAttribute("message", getMessage(LoginMessageKeyConstants.ERRORS_LOGIN_UNMATCH, null));
            LOGGER.debug("Wrong password, redirecting.");
            return formatJsonString("badLogin", null, request);
        }
        
        try {
            
            int resultCount = ApiRequestUtil.invoke(ServiceNameConstants.CERTIFY_SERVICE, "updateMedMailAddress",
                    new TypeReference<Integer>() {
                    }, userId, mailAddress);
            
            // We expect a change to one row
            if (resultCount == 1) {
                //send verify code
                ApiRequestUtil.invoke(ServiceNameConstants.CERTIFY_SERVICE, "sendVerifyCode",
                        new TypeReference<Boolean>() {
                        }, userId, userAccount.getUserNm(), mailAddress);
            } else {
                LOGGER.debug("Update mailaddress fail.");
                return formatJsonString("updateFail", null, request);
            }
            return formatJsonString("updateSucc", null, request);
            // Failed to touch session, redirect to GET@login.
        } catch (Exception e) {
            //error.
            LOGGER.warn("Exception occured at addVerifyMailAddress. {}", e);
            return formatJsonString("systemError", null, request);
        }
    }
    
    /**
     * 通常ログイン認証
     *
     * @param loginForm 入力項目
     * @param result バリデーションチェック結果
     * @param request HttpRequest
     * @param response HttpResponse
     * @param model モデル
     * @return 結果文字列
     * @throws Exception 例外
     */
    @PostMapping("/ifaLoginLoginA008")
    public String login(@RequestBody @Validated LoginForm loginForm, BindingResult result, HttpServletRequest request,
            HttpServletResponse response, Model model) throws Exception {
        
        if (result.hasFieldErrors("userId")) {
            LOGGER.debug("Something wrong with userId format.");
            return formatJsonString("badLogin", null, request);
        }
        if (result.hasFieldErrors("password")) {
            LOGGER.debug("Something wrong with password format.");
            return formatJsonString("badLogin", null, request);
        }
        
        String userId = loginForm.getUserId();
        String password = EncryptPassword.encrypt(loginForm.getPassword());
        
        // Get verify user info.
        MedVerifyUser verifyUserInfo = ApiRequestUtil.invoke(ServiceNameConstants.CERTIFY_SERVICE, "getVerifyUserInfo",
                new TypeReference<MedVerifyUser>() {
                }, loginForm.getUserId());
        
        //User is a verifiable user.
        if (verifyUserInfo != null) {
            
            if (result.hasFieldErrors("verifyCode") || StringUtils.isBlank(loginForm.getVerifyCode())) {
                LOGGER.debug("Something wrong with verifyCode format.");
                return formatJsonString("verifyCodeError", null, request);
            }
            
            String verifyCode = loginForm.getVerifyCode();
            
            MedVerifyCode medVerifyCode = ApiRequestUtil.invoke(ServiceNameConstants.CERTIFY_SERVICE,
                    "getVerifyCodeInfo", new TypeReference<MedVerifyCode>() {
                    }, userId);
            
            if (medVerifyCode != null) {
                if (hasExpiredVerifyCode(medVerifyCode.getNowDate(), medVerifyCode.getVerifyDate())) {
                    //Verify code has expired.
                    LOGGER.debug("Verify code has expired.");
                    return formatJsonString("codeExpired", null, request);
                } else {
                    if (!verifyCode.toUpperCase().equals(medVerifyCode.getVerifyCode())) {
                        //Verify code does not match
                        LOGGER.debug("Verify code does not match.");
                        return formatJsonString("notMatch", null, request);
                    }
                }
            } else {
                //Verify error.
                LOGGER.debug("Verify error.");
                return formatJsonString("verifyError", null, request);
            }
        }
        
        // Matching userId and password.
        String jsonString = IfaGwCommonUtil.getDataFromRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_ENVIRONMENT, false,
                "authCtrlListExclusionSettings", String.class);
        // メニュー除外設定情報が取得できる場合は、JSONから変換する。取得駅ない場合はnullとする。
        AuthCtrlListExclusionSettings settings = null;
        if (!StringUtils.isBlank(jsonString)) {
            settings = JsonConverter.getInstance().toObject(jsonString, AuthCtrlListExclusionSettings.class);
        }
        UserAccount userAccount = ApiRequestUtil.invoke(ServiceNameConstants.USER_ADMINISTRATION_SERVICE,
                "getUserAccount", new TypeReference<UserAccount>() {
                }, userId, settings);
        
        // User is not found, or failed to verify password.
        if (userAccount == null || !userAccount.getPassword().equals(password)) {
            model.addAttribute("message", getMessage(LoginMessageKeyConstants.ERRORS_LOGIN_UNMATCH, null));
            LOGGER.debug("Wrong password, redirecting.");
            return formatJsonString("badLogin", null, request);
        }
        
        try {
            
            // Set organization.
            CommonControllerHelper.setOrganization(userAccount);
            
            // フレームワークセッションIDの生成。
            String frameworkSessionIdString = UUID.randomUUID().toString();
            // 認証チェック用情報の生成とキャッシュ。
            tokenService.createSessionInfo(frameworkSessionIdString, userAccount);
            // リクエストスコープにセット
            request.setAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, frameworkSessionIdString);
            request.setAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
            
            // Record if we should remind user to confirm new information
            complianceReminderController.updateNeedForComplianceReminder(model, request);
            
            // 最新のセッション情報を取得し、Redisを更新する
            FrameworkSessionInfo sessionInfo = tokenService.getSessionInfo(frameworkSessionIdString);
            tokenService.setSessionInfoToCache(sessionInfo);
            
            // 前営業日を取得する
            PreviousBusinessDateDtoRequest req = new PreviousBusinessDateDtoRequest();
            String previousBusinessDay = ApiRequestUtil.invoke("systemDateService", "getPreviousBusinessDate",
                    new TypeReference<String>() {
                    }, req);
            
            // 目安箱未読件数を取得する（権限コード3～9）
            int sugBoxUnreadItems = 0;
            List<String> brokerPrivId = Arrays.asList("3", "4", "5", "6", "7", "8", "9");
            if (brokerPrivId.contains(userAccount.getPrivId())) {
                sugBoxUnreadItems = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalDetailService", "getSugBoxUnreadItems",
                        new TypeReference<Integer>() {
                        }, userAccount.getUserId());
            }
            
            // リリースノート画面の自動表示条件を満たすかチェックする
            boolean releaseNoteDispFlg = false;
            List<String> releaseNoteBrokerPrivId = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
            boolean hasPrivId = releaseNoteBrokerPrivId.contains(userAccount.getPrivId());
            boolean hasViewAccess = userAccount.getAccessibleViewList().contains("SUB00-07_1");
            // 1.ユーザー共通情報.ユーザ表示可能画面に 'SUB00-07_1'(リリースノート) が含まれている
            if (hasPrivId && hasViewAccess) {
                // 2.SQL005の取得結果が空でない
                ReleaseNoteViewInfo releaseNoteViewInfo = ApiRequestUtil.invoke("cmpIfaReleaseNoteAutoDispService", "getReleaseNoteViewInfo",
                        new TypeReference<ReleaseNoteViewInfo>() {
                        }, userAccount.getUserId());
                if (releaseNoteViewInfo != null && releaseNoteViewInfo.getReleaseNoteLatestTime() != null) {
                    // 最終閲覧日時
                    Date lastReadTime = releaseNoteViewInfo.getLastReadTime();
                    // リリースノート最新日時
                    Date releaseNoteLatestTime = releaseNoteViewInfo.getReleaseNoteLatestTime();
                    // 3.最終閲覧日時が空でない
                    if (lastReadTime != null) {
                        // 4.最終閲覧日時 >= リリースノート最新日時
                        if (lastReadTime.compareTo(releaseNoteLatestTime) >= 0) {
                            // 最終閲覧日時が現在日付より前 （その日の初回ログイン）
                            LocalDate today = LocalDate.now();
                            LocalDate lastReadDate = lastReadTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            // かつ 次回表示フラグが '1' (表示)
                            boolean nextDispFlg = "1".equals(releaseNoteViewInfo.getNextDispFlg());
                            // かつ リリースノート最新日時の日付が現在日を0日目として7日以内
                            LocalDate releaseNoteLatestDate = releaseNoteLatestTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            long daysDifference = ChronoUnit.DAYS.between(releaseNoteLatestDate, today);
                            boolean isWithinSevenDays = daysDifference >= 0 && daysDifference <= 7;
                            
                            releaseNoteDispFlg = lastReadDate.isBefore(today) && nextDispFlg && isWithinSevenDays;
                        } else {
                            // No (最终閲覧日時 < リリースノート最新日時) 次回表示フラグを更新する(SQL006)
                            ApiRequestUtil.invoke("cmpIfaReleaseNoteAutoDispService", "updateReleaseNoteNextDispFlg",
                                    new TypeReference<Integer>() {
                                    }, userAccount.getUserId());
                            releaseNoteDispFlg = true;
                        }
                    } else {
                        // No (最終閲覧日時が空)
                        releaseNoteDispFlg = true;
                    }
                }
            }
            
            // Checking for expired passwords
            if (hasExpiredPassword(userAccount, request)) {
                LOGGER.debug("User password has expired.");
                return formatJsonString("expired", sessionInfo, request, previousBusinessDay, sugBoxUnreadItems, releaseNoteDispFlg);
            }
            // Output login information
            LOGGER.info("User Id:[{}] Session Id:[{}] User-Agent:[{}] ", userId, frameworkSessionIdString,
                    request.getHeader("user-agent"));
            
            return formatJsonString("loginSucc", sessionInfo, request, previousBusinessDay, sugBoxUnreadItems, releaseNoteDispFlg);
            
            // Failed to touch session, redirect to GET@login.
        } catch (IllegalStateException e) {
            
            return formatJsonString("systemError", null, request);
        }
        
    }
    
    /**
     * A001 初期化 (お知らせ情報取得)
     *
     * @return お知らせ一覧リスト
     * @throws Exception 例外
     */
    @PostMapping("/ifaLoginInitializeA001")
    @ResponseJson
    public String handleGet() throws Exception {
        
        // Get information list
        IfaLoginA001DtoRequest ifaLoginA001DtoRequest = new IfaLoginA001DtoRequest();
        List<MedInformation> infoList = ApiRequestUtil.invoke(ServiceNameConstants.MED_INFOMATION_SERVICE,
                "getMedInformationList", new TypeReference<List<MedInformation>>() {
                }, ifaLoginA001DtoRequest);
        // 
        DataList<MedInformation> dataList = IfaCommonUtil.createDataList(infoList, ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(), "");
        return jc.toString(dataList);
    }
    
    /**
     * JSON形式の文字列に変換する
     *
     * @param key キー
     * @param sessionInfo フレームワークセッション情報
     * @param request HttpRequest
     * @return JSON形式の文字列
     * @throws Exception 例外
     */
    private String formatJsonString(String key, FrameworkSessionInfo sessionInfo, HttpServletRequest request)
            throws Exception {
        
        return formatJsonString(key, sessionInfo, request, null, 0, false);
    }
    
    /**
     * JSON形式の文字列に変換する
     *
     * @param key キー
     * @param sessionInfo フレームワークセッション情報
     * @param request HTTPリクエスト
     * @param previousBusinessDay 前営業日
     * @param sugBoxUnreadItems 目安箱未読件数
     * @param releaseNoteDispFlg リリースノート表示フラグ
     * @return JSON形式のログインレスポンス文字列
     * @throws Exception 例外
     */
    private String formatJsonString(String key, FrameworkSessionInfo sessionInfo, HttpServletRequest request,
            String previousBusinessDay, int sugBoxUnreadItems, boolean releaseNoteDispFlg) throws Exception {
        
        // レスポンスオブジェクトの生成。
        LoginResponseForm loginResponseForm = new LoginResponseForm();
        loginResponseForm.setRequestedTime(getFormattedRequestedTime(request));
        
        // セッション情報が存在する場合、以下の値をセットする。
        if (sessionInfo != null) {
            loginResponseForm.setFrameworkSessionId(sessionInfo.getFrameworkSessionId());
            loginResponseForm.setAuthToken(sessionInfo.getEncryptedAuthenticationToken());
            loginResponseForm.setLastRequestSec((Date) request.getAttribute("RequestTime"));
            loginResponseForm.setUserAccount(tokenService.getUserAccountObjectFromJwt(sessionInfo));
        }
        // 前営業日をセットする。
        if (previousBusinessDay != null) {
            loginResponseForm.setPreviousBusinessDay(previousBusinessDay);
        }
        
        // 目安箱未読件数をセットする。
        loginResponseForm.setSugBoxUnreadItems(sugBoxUnreadItems);
        
        // リリースノート表示フラグをセットする。
        loginResponseForm.setReleaseNoteDispFlg(releaseNoteDispFlg);
        
        LoginReturnDataEnums enums = LoginReturnDataEnums.valueOfId(key);
        if ("loginSucc".equals(key)) {
            loginResponseForm.setReturnCode(enums.getReturnCd());
            
            if (!IfaCommonUtil.hasDebugEnvironmentVariable()) {
                // ログイン成功時かつデバッグフラグがOFFの場合のみ、以下の値を返却する
                String requestTimeOutSecStr = IfaGwCommonUtil.getDataFromRedis(
                        IfaGwCommonUtil.ATTR_REDIS_GROUP_ENVIRONMENT, false, "requestTimeOutSec", String.class);
                if (StringUtils.isNumeric(requestTimeOutSecStr)) {
                    loginResponseForm.setRequestTimeOutSec(Integer.parseInt(requestTimeOutSecStr));
                } else {
                    throw new IfaRuntimeException(ERRORS_TARGET_PICKUP_FAILURE, MESSAGE_PARAM_ENVIRONMENT);
                }
                
                String idleLimitSec = IfaGwCommonUtil.getDataFromRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_ENVIRONMENT,
                        false, "idleLimitSec", String.class);
                if (StringUtils.isNumeric(idleLimitSec)) {
                    loginResponseForm.setIdleLimitSec(Integer.parseInt(idleLimitSec));
                } else {
                    throw new IfaRuntimeException(ERRORS_TARGET_PICKUP_FAILURE, MESSAGE_PARAM_ENVIRONMENT);
                }
                
                String rppOptionsStr = IfaGwCommonUtil.getDataFromRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_ENVIRONMENT,
                        false, "rPPOptions", String.class);
                if (!StringUtils.isEmpty(rppOptionsStr)) {
                    loginResponseForm.setRppOptions(rppOptionsStr);
                } else {
                    throw new IfaRuntimeException(ERRORS_TARGET_PICKUP_FAILURE, MESSAGE_PARAM_ENVIRONMENT);
                }
            }
        } else {
            loginResponseForm.setReturnCode(enums.getReturnCd());
        }
        loginResponseForm.setErrorLevel(enums.getErrorLevel());
        if (!StringUtil.isNullOrEmpty(enums.getMessage())) {
            loginResponseForm.setMessage(getMessage(enums.getMessage(), null));
        }
        return jc.toString(loginResponseForm);
    }
    
    /**
     * [PROTO2]認可情報取得エンドポイント。
     * @param loginInfo
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/permisson")
    public String getPermission(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return "{\r\n" + "  \"medUsers\": {\r\n" + "    \"userId\": \"U123456\",\r\n"
                + "    \"userNm\": \"ユーザの名前\",\r\n" + "    \"password\": \"このフィールド値は非公開です\",\r\n"
                + "    \"lastpwuptime\": \"2021/01/04 08:30:00\",\r\n" + "    \"privId\": \"1\",\r\n"
                + "    \"branchId\": \"001\",\r\n" + "    \"brokerId\": \"2023\",\r\n"
                + "    \"subBrokerId\": \"011\",\r\n" + "    \"employeeId\": \"0093\",\r\n"
                + "    \"employeeName\": \"雇用者の名前\",\r\n" + "    \"governorFlag\": \"0\",\r\n"
                + "    \"loginStatus\": null,\r\n" + "    \"partnerUserId\": null,\r\n"
                + "    \"partnerUserPw\": \"このフィールド値は非公開です\",\r\n" + "    \"ccsUserId\": null,\r\n"
                + "    \"ccsUserPw\": \"このフィールド値は非公開です\",\r\n" + "    \"createUser\": \"H123456\",\r\n"
                + "    \"createTime\": \"2020/12/08 12:23:34\",\r\n" + "    \"uptimestampUser\": \"X123456\",\r\n"
                + "    \"uptimestampTime\": \"2020/12/17 15:05:15\",\r\n" + "    \"mailAddress\": \"aho@foo.var\"\r\n"
                + "  },\r\n" + "  \"userPermissionInfo\": {\r\n" + "    \"accessibleViewList\": [\r\n"
                + "      \"SUB001\",\r\n" + "      \"SUB002\",\r\n" + "      \"SUB003\",\r\n" + "      \"SUB00\",\r\n"
                + "      \"SUB005\",\r\n" + "      \"SUB006\"\r\n" + "    ]\r\n" + "  },\r\n"
                + "  \"accControls\": [\r\n" + "    {\r\n" + "      \"menuId\": \"SUB005\",\r\n"
                + "      \"itemId\": \"brokerCode\",\r\n" + "      \"userId\": \"U123456\",\r\n"
                + "      \"accControl\": 2\r\n" + "    },\r\n" + "    {\r\n" + "      \"menuId\": \"SUB005\",\r\n"
                + "      \"itemId\": \"empCode\",\r\n" + "      \"userId\": \"U123456\",\r\n"
                + "      \"accControl\": 2\r\n" + "    },\r\n" + "    {\r\n" + "      \"menuId\": \"SUB005\",\r\n"
                + "      \"itemId\": \"btnCsvDownload\",\r\n" + "      \"userId\": \"U123456\",\r\n"
                + "      \"accControl\": 3\r\n" + "    }\r\n" + "  ],\r\n" + "  \"accessibleToCcs\": false,\r\n"
                + "  \"userNeedsToReadComplianceLetters\": false,\r\n"
                + "  \"userNeedsToReadMonthlySelfcheck\": true,\r\n" + "  \"csvDownloadAllowed\": true,\r\n"
                + "  \"csvDownloadPrivacyConfirmationRequired\": true,\r\n" + "  \"searchByBrokerCode\": true,\r\n"
                + "  \"searchByEmployee\": true,\r\n" + "  \"searchByjointBranchCode\": false\r\n" + "}";
        
    }
    
    @PostMapping("logout")
    public void logout() throws Exception {
        
        System.out.println("Do Logout.");
        
    }
    
    @Data
    public class ReturnModel {
        
        String Token;
        
        public ReturnModel(String token) {
            
            this.Token = token;
        }
    }
    
    private Map<String, Map<String, BaseController>> getControllerMapByMenuId() {
        
        synchronized (this) {
            
            if (_controllerMapByMenuId == null) {
                
                _controllerMapByMenuId = new HashMap<String, Map<String, BaseController>>();
                
                // Get Spring context.
                WebApplicationContext context = getWebApplicationContext();
                // Get controller names.
                String[] controllerNames = context.getBeanNamesForAnnotation(ScreenId.class);
                // Create controller list.
                List<BaseController> controllerList = new ArrayList<BaseController>();
                
                for (int i = 0; i < controllerNames.length; i++) {
                    
                    Object someController = context.getBean(controllerNames[i]);
                    // Choose controller which extends BaseController.
                    if (!(someController instanceof BaseController)) {
                        continue;
                    }
                    
                    BaseController ourController = (BaseController) someController;
                    
                    // Choose controller which will marked check screen id.
                    if (AopProxyUtils.ultimateTargetClass(ourController).getAnnotation(ScreenId.class).ignoreCheck()) {
                        continue;
                    }
                    controllerList.add(ourController);
                }
                
                MenuId[] menuIds = MenuId.values();
                
                for (int i = 0; i < menuIds.length; i++) {
                    
                    Map<String, BaseController> controllerMapById = new HashMap<String, BaseController>();
                    _controllerMapByMenuId.put(menuIds[i].getId(), controllerMapById);
                    int size = controllerList.size();
                    
                    for (int j = 0; j < size; j++) {
                        
                        BaseController ourController = controllerList.get(j);
                        // Get annotation
                        ScreenId screenId = AopProxyUtils.ultimateTargetClass(ourController)
                                .getAnnotation(ScreenId.class);
                        if (screenId.groupId().equals(menuIds[i].getId()) && !screenId.ignoreCheck()) {
                            controllerMapById.put(screenId.id(), ourController);
                        }
                    }
                }
                
                LOGGER.info("LoginController.getControllerMapByMenuId(): Controller map creation completed.");
            }
        }
        
        return _controllerMapByMenuId;
    }
    
    /**
     * パスワード期限切れチェック
     *
     * @param userAccount ユーザ共通情報
     * @param request HttpRequest
     * @return チェック結果
     * @throws Exception 例外
     */
    @PostMapping("/hasExpiredPassword")
    @ResponseJson
    public boolean hasExpiredPassword(UserAccount userAccount, HttpServletRequest request) throws Exception {
        
        LOGGER.debug("Checking if password has expired.");
        
        Date lastUpdated = userAccount.getLastpwuptime();
        if (lastUpdated == null) {
            return true;
        }
        
        Date now = new Date();
        long daysAgo = TimeUnit.MILLISECONDS.toDays(now.getTime() - lastUpdated.getTime());
        
        DataList<String> returnData = new DataList<String>();
        returnData.setRequestedTime(getFormattedRequestedTime(request));
        
        return daysAgo > 90;
    }
    
    private boolean hasExpiredVerifyCode(String nowDateTxt, String verifyDateTxt) throws Exception {
        
        LOGGER.debug("Checking if verify code has expired.");
        
        String timeoutMinutesTxt = "";
        
        // M_CODEを取得するM_CODE_SERVICE
        List<MCode> mCodeList = ApiRequestUtil.invoke(ServiceNameConstants.M_CODE_SERVICE, "getMCodeList",
                new TypeReference<List<MCode>>() {
                }, CODE_TYPE_VERIFY_CODE, CODE_1_VAILD_TIME);
        if (mCodeList != null && !mCodeList.isEmpty()) {
            for (MCode mCode : mCodeList) {
                if (CODE_2.equals(mCode.getCode2())) {
                    timeoutMinutesTxt = mCode.getName();
                }
            }
        }
        
        if (StringUtils.isEmpty(timeoutMinutesTxt) || StringUtils.isEmpty(verifyDateTxt)) {
            return true;
        }
        int timeoutMinutes = Integer.parseInt(timeoutMinutesTxt);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        Date verifyDate = df.parse(verifyDateTxt);
        Date now = df.parse(nowDateTxt);
        long minuteAgo = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - verifyDate.getTime());
        
        return minuteAgo > timeoutMinutes;
    }
}

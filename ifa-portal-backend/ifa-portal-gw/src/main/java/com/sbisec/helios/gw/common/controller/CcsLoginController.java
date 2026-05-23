package com.sbisec.helios.gw.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.FrameworkSessionInfo;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.service.TokenService;
import com.sbisec.helios.ap.common.dto.UserHasCcsDataRequestDto;
import com.sbisec.helios.ap.common.dto.UserHasCcsDataResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.dto.CcsLoginRequestDto;
import com.sbisec.helios.ap.common.dto.CcsLoginResponseDto;
import com.sbisec.helios.ap.common.dto.UpdateCcsDataRequestDto;
import com.sbisec.helios.ap.common.dto.UpdateCcsDataResponseDto;
import com.sbisec.helios.ap.common.dto.ClearCcsDataSelfRequestDto;
import com.sbisec.helios.ap.common.dto.ClearCcsDataSelfResponseDto;
import com.sbisec.helios.gw.common.form.UserHasCcsDataRequestForm;
import com.sbisec.helios.gw.common.form.UserHasCcsDataResponseForm;
import com.sbisec.helios.gw.common.form.CcsLoginRequestForm;
import com.sbisec.helios.gw.common.form.CcsLoginResponseForm;
import com.sbisec.helios.gw.common.form.UpdateCcsDataRequestForm;
import com.sbisec.helios.gw.common.form.UpdateCcsDataResponseForm;
import com.sbisec.helios.gw.common.form.ClearCcsDataSelfRequestForm;
import com.sbisec.helios.gw.common.form.ClearCcsDataSelfResponseForm;

/**
 * CCSログイン共通コントローラ
 *
 * @author SCSK 矢口
 2024/07/24 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "CcsLogin", ignoreCheck = true)
public class CcsLoginController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    @Autowired
    private TokenService tokenService;
    
    /**
     * アクセス：/sso/UserHasCcsData
     * アクションID：画面共通部品_CCSログインユーザー情報登録 A001
     * アクション名：CCSログイン情報チェック
     * APIリクエスト：UserHasCcsDataRequestForm
     * Apiレスポンス：UserHasCcsDataResponseForm
     * Dtoリクエスト：UserHasCcsDataRequestDto
     * Dtoレスポンス：UserHasCcsDataResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/sso/userHasCcsData")
    public String userHasCcsData(@RequestBody UserHasCcsDataRequestForm apiReq) throws Exception {
        
        UserHasCcsDataRequestDto appReq = new UserHasCcsDataRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<UserHasCcsDataResponseDto> appRes = ApiRequestUtil.invoke("cmpCcsLoginService", "userHasCcsData",
                new TypeReference<DataList<UserHasCcsDataResponseDto>>() {
                }, appReq);
        
        DataList<UserHasCcsDataResponseForm> apiRes = new DataList<UserHasCcsDataResponseForm>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/sso/CcsLogin
     * アクションID：画面共通部品_CCSログインユーザー情報登録 A002
     * アクション名：CCSログイン
     * APIリクエスト：CcsLoginRequestForm
     * Apiレスポンス：CcsLoginResponseForm
     * Dtoリクエスト：CcsLoginRequestDto
     * Dtoレスポンス：CcsLoginResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/sso/ccsLogin")
    public String ccsLogin(@RequestBody CcsLoginRequestForm apiReq) throws Exception {
        
        CcsLoginRequestDto appReq = new CcsLoginRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<CcsLoginResponseDto> appRes = ApiRequestUtil.invoke("cmpCcsLoginService", "ccsLogin",
                new TypeReference<DataList<CcsLoginResponseDto>>() {
                }, appReq);
        
        DataList<CcsLoginResponseForm> apiRes = new DataList<CcsLoginResponseForm>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/sso/UpdateCcsData
     * アクションID：画面共通部品_CCSログインユーザー情報登録 A003
     * アクション名：CCSログイン情報登録
     * APIリクエスト：UpdateCcsDataRequestForm
     * Apiレスポンス：UpdateCcsDataResponseForm
     * Dtoリクエスト：UpdateCcsDataRequestDto
     * Dtoレスポンス：UpdateCcsDataResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/sso/updateCcsData")
    public String updateCcsData(@RequestBody UpdateCcsDataRequestForm apiReq, HttpServletRequest request)
            throws Exception {
        
        UpdateCcsDataRequestDto appReq = new UpdateCcsDataRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<UpdateCcsDataResponseDto> appRes = ApiRequestUtil.invoke("cmpCcsLoginService", "updateCcsData",
                new TypeReference<DataList<UpdateCcsDataResponseDto>>() {
                }, appReq);
        
        DataList<UpdateCcsDataResponseForm> apiRes = new DataList<UpdateCcsDataResponseForm>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        if (appRes.getErrorLevel() == ErrorLevel.SUCCESS.getId()) {
            // ユーザ共通情報.CCSログイン用パスワードに画面で入力されたCCSパスワードの値を設定する。
            UserAccount userAccount = IfaCommonUtil.getUserAccount();
            userAccount.getMedUsers().setCcsUserPw(appReq.getCcsUserPw());
            
            // ユーザ共通情報を更新する。
            FrameworkSessionInfo sessionInfo = tokenService.getSessionInfo(
                    IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
            tokenService.updateSessionInfo(sessionInfo, userAccount);
            request.setAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/sso/ClearCcsDataSelf
     * アクションID：画面共通部品_CC017_共通ヘッダ A005
     * アクション名：CCS情報リセット
     * APIリクエスト：ClearCcsDataSelfRequestForm
     * Apiレスポンス：ClearCcsDataSelfResponseForm
     * Dtoリクエスト：ClearCcsDataSelfRequestDto
     * Dtoレスポンス：ClearCcsDataSelfResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/sso/clearCcsDataSelf")
    public String clearCcsDataSelf(@RequestBody ClearCcsDataSelfRequestForm apiReq, HttpServletRequest request)
            throws Exception {
        
        ClearCcsDataSelfRequestDto appReq = new ClearCcsDataSelfRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<ClearCcsDataSelfResponseDto> appRes = ApiRequestUtil.invoke("cmpCcsLoginService", "clearCcsDataSelf",
                new TypeReference<DataList<ClearCcsDataSelfResponseDto>>() {
                }, appReq);
        
        DataList<ClearCcsDataSelfResponseForm> apiRes = new DataList<ClearCcsDataSelfResponseForm>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        if (appRes.getErrorLevel() == ErrorLevel.SUCCESS.getId()) {
            // ユーザ共通情報.CCSログイン用パスワードを初期化する。
            UserAccount userAccount = IfaCommonUtil.getUserAccount();
            userAccount.getMedUsers().setCcsUserPw("");
            
            // ユーザ共通情報を更新する。
            FrameworkSessionInfo sessionInfo = tokenService.getSessionInfo(
                    IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
            tokenService.updateSessionInfo(sessionInfo, userAccount);
            request.setAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        }
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}

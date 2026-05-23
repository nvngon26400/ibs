package com.sbisec.helios.ap.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.dao.CcsLoginDao;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql001RequestModel;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql002RequestModel;
import com.sbisec.helios.ap.common.dao.model.CcsLoginSql002ResponseModel;
import com.sbisec.helios.ap.common.dto.CcsLoginRequestDto;
import com.sbisec.helios.ap.common.dto.CcsLoginResponseDto;
import com.sbisec.helios.ap.common.dto.ClearCcsDataSelfRequestDto;
import com.sbisec.helios.ap.common.dto.ClearCcsDataSelfResponseDto;
import com.sbisec.helios.ap.common.dto.UpdateCcsDataRequestDto;
import com.sbisec.helios.ap.common.dto.UpdateCcsDataResponseDto;
import com.sbisec.helios.ap.common.dto.UserHasCcsDataRequestDto;
import com.sbisec.helios.ap.common.dto.UserHasCcsDataResponseDto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CcsLoginService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * CCSログイン共通サービス
 *
 * @author SCSK 矢口
 2024/07/24 新規作成
 */
@Component(value = "cmpCcsLoginService")
public class CcsLoginServiceImpL implements CcsLoginService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CcsLoginServiceImpL.class);
    
    private static final char CHAR_ZERO = '0';
    
    private static final String BLANK = "";
    
    /** {0}が未登録です。 */
    private static final String ERRORS_CMN_INFORMATION_UNREGISTERED = "errors.cmn.information.unregistered";
    
    /** セッションタイムアウト */
    private static final String ERRORS_SESSION_LOST = "errors.session.lost";
    
    /** アクセス権限エラー */
    private static final String ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE = "errors.cmn.loginUsers.insufficientPrivilege";
    
    /** エラーメッセージ用　"CCSユーザーID" */
    private static final String ERROR_MESSAGE_CCS_USER_ID = "CCSユーザーID";
    
    /** SQL002 設定値：ログアウトURL検索用 */
    private static final String SQL002_CCS_LOGOUT_URL = "CCS_LOGOUT_URL";
    
    /** SQL002 設定値：ログインURL検索用 */
    private static final String SQL002_CCS_LOGIN_URL = "CCS_LOGIN_URL";
    
    /** CCS画面ID：CCS_ACCOUNT_OPEN_PRE_PRINT */
    private static final String PAGE_KEY_CCS_ACCOUNT_OPEN_PRE_PRINT = "CCS_ACCOUNT_OPEN_PRE_PRINT";
    
    /** CCS画面ID：CCS_TRDE_HISTORY */
    private static final String PAGE_KEY_CCS_TRDE_HISTORY = "CCS_TRDE_HISTORY";
    
    /** CCS画面ID：CCS_DETAIL_CUSTOMER_PROPERTY */
    private static final String PAGE_KEY_CCS_DETAIL_CUSTOMER_PROPERTY = "CCS_DETAIL_CUSTOMER_PROPERTY";
    
    /** CCS画面ID：CCS_CONTACT_HISTORY */
    private static final String PAGE_KEY_CCS_CONTACT_HISTORY = "CCS_CONTACT_HISTORY";
    
    /** CCS画面ID：CCS_INPUT_STOCK_ORDER */
    private static final String PAGE_KEY_CCS_INPUT_STOCK_ORDER = "CCS_INPUT_STOCK_ORDER";
    
    /** CCS画面ID：CCS_SHOW_ACCOUNT_SUMMARY_BY_BATCH */
    private static final String PAGE_KEY_CCS_SHOW_ACCOUNT_SUMMARY_BY_BATCH = "CCS_SHOW_ACCOUNT_SUMMARY_BY_BATCH";
    
    /** CCS画面ID：CCS_WITHDRAWAL_RECORDING_DATE */
    private static final String PAGE_KEY_CCS_WITHDRAWAL_RECORDING_DATE = "CCS_WITHDRAWAL_RECORDING_DATE";
    
    /** CCS画面ID：CCS_DOCUMENT_REAUEST_LIST */
    private static final String PAGE_KEY_CCS_DOCUMENT_REAUEST_LIST = "CCS_DOCUMENT_REAUEST_LIST";
    
    /** URLパラメータ設定用　user_id */
    private static final String PARAM_KEY_USER_ID = "user_id";
    
    /** URLパラメータ設定用　customer_id */
    private static final String PARAM_KEY_CUSTOMER_ID = "customer_id";
    
    /** URLパラメータ設定用　branch_no */
    private static final String PARAM_KEY_BUTEN_CODE = "branch_no";
    
    /** URLパラメータ設定用　account_no */
    private static final String PARAM_KEY_ACCOUNT_NUMBER = "account_no";
    
    @Autowired
    private CcsLoginDao dao;
    
    /**
     * アクションID：画面共通部品_CCSログインユーザー情報登録 A001
     * アクション名：CCSログイン情報チェック
     * Dto リクエスト：UserHasCcsDataRequestDto
     * Dto レスポンス：UserHasCcsDataResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<UserHasCcsDataResponseDto> userHasCcsData(UserHasCcsDataRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CcsLoginServiceImplL.userHasCcsData");
        }
        
        DataList<UserHasCcsDataResponseDto> dtoRes = new DataList<UserHasCcsDataResponseDto>();
        List<UserHasCcsDataResponseDto> resDtoList = new ArrayList<UserHasCcsDataResponseDto>();
        UserHasCcsDataResponseDto resDto = new UserHasCcsDataResponseDto();
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // ユーザ共通情報.CCSログイン用IDの設定有無の確認
        // 未設定の場合：エラーメッセージを表示して処理を終了
        if (StringUtils.isEmpty(userAccount.getCcsUserId())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_UNREGISTERED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_UNREGISTERED,
                            new String[] { ERROR_MESSAGE_CCS_USER_ID }));
            return dtoRes;
        }
        
        // ユーザ共通情報.CCSログイン用パスワードの設定有無の確認
        // 未設定の場合　：レスポンス.CCSパスワード設定有無フラグ=falseをセット
        // 設定ありの場合：レスポンス.CCSパスワード設定有無フラグ=trueをセット
        if (StringUtils.isEmpty(userAccount.getCcsUserPw())) {
            resDto.setHasCcsData("false");
        } else {
            resDto.setHasCcsData("true");
        }
        
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
        
    }
    
    /**
     * アクションID：画面共通部品_CCSログインユーザー情報登録 A002
     * アクション名：CCSログイン
     * Dto リクエスト：CcsLoginRequestDto
     * Dto レスポンス：CcsLoginResponseDto
     * model リクエスト：CcsLoginSql002RequestModel
     * model レスポンス：CcsLoginSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<CcsLoginResponseDto> ccsLogin(CcsLoginRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CcsLoginServiceImplL.ccsLogin");
        }
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        if (userAccount == null) {
            // ユーザ共通情報が取得できなかった場合、タイムアウトエラー
            LOGGER.debug(
                    "CcsLoginController.validateAndGetRegisteredInfo: Can't get UserAccount, cause by might be this session is invalidate.");
            return IfaCommonUtil.createDtaList(null, ErrorLevel.SYSTEM_ERROR, ERRORS_SESSION_LOST);
        }
        if (!userAccount.isAccessibleToCcs()) {
            // CCSアクセス不可の場合、アクセス権限エラー
            LOGGER.debug("CcsLoginController.validateAndGetRegisteredInfo: {} is'nt accessible to CCS.",
                    userAccount.getUserId());
            return IfaCommonUtil.createDtaList(null, ErrorLevel.FATAL, ERRORS_CMN_LOGINUSERS_INSUFFICIENTPRIVILEGE);
        }
        
        CcsLoginResponseDto resDto = new CcsLoginResponseDto();
        CcsLoginSql002RequestModel selSql002Req = new CcsLoginSql002RequestModel();
        
        // CCSのURLを取得する
        // ログアウトURL
        selSql002Req.setTargetUrl(SQL002_CCS_LOGOUT_URL);
        DataList<CcsLoginSql002ResponseModel> selSql002Res = dao.selectCcsLoginSql002(selSql002Req);
        resDto.setLogoutUrl(selSql002Res.getDataList().get(0).getVarValue());
        
        // ログインURL
        selSql002Req.setTargetUrl(SQL002_CCS_LOGIN_URL);
        selSql002Res = dao.selectCcsLoginSql002(selSql002Req);
        resDto.setLoginUrl(selSql002Res.getDataList().get(0).getVarValue());
        
        // 遷移先URL
        selSql002Req.setTargetUrl(dtoReq.getCcsDispId());
        selSql002Res = dao.selectCcsLoginSql002(selSql002Req);
        StringBuilder tmpTransitionUrl = new StringBuilder(selSql002Res.getDataList().get(0).getVarValue());
        
        // リクエスト.CCS画面IDが"CCS_INPUT_STOCK_ORDER"の場合、一時遷移先URLを取得する。
        if (PAGE_KEY_CCS_INPUT_STOCK_ORDER.equals(dtoReq.getCcsDispId())) {
            // 一時遷移先URL
            selSql002Req.setTargetUrl(PAGE_KEY_CCS_DETAIL_CUSTOMER_PROPERTY);
            selSql002Res = dao.selectCcsLoginSql002(selSql002Req);
            
            StringBuilder tmpTempUrl = new StringBuilder(selSql002Res.getDataList().get(0).getVarValue());
            tmpTempUrl.append("&");
            
            // user_id：ユーザ共通情報.CCSログイン用ID
            tmpTempUrl.append(PARAM_KEY_USER_ID).append("=").append(userAccount.getCcsUserId()).append("&");
            
            // customer_id：顧客共通情報.顧客コード
            tmpTempUrl.append(PARAM_KEY_CUSTOMER_ID).append("=").append(customerCommon.getCustomerCode()).append("&");
            
            // branch_no：顧客共通情報.部店コード
            tmpTempUrl.append(PARAM_KEY_BUTEN_CODE).append("=").append(customerCommon.getButenCode()).append("&");
            
            // account_no：顧客共通情報.口座番号（前ゼロ埋め7桁固定に編集）
            tmpTempUrl.append(PARAM_KEY_ACCOUNT_NUMBER).append("=")
                    .append(StringUtil.fillLeft(customerCommon.getAccountNumber(), CHAR_ZERO, 7));
            resDto.setTempUrl(tmpTempUrl.toString());
        }
        
        // 遷移先URLにパラメータを付与
        if (!PAGE_KEY_CCS_INPUT_STOCK_ORDER.equals(dtoReq.getCcsDispId())
                && !PAGE_KEY_CCS_ACCOUNT_OPEN_PRE_PRINT.equals(dtoReq.getCcsDispId())) {
            
            if (-1 < tmpTransitionUrl.toString().indexOf('?')) {
                tmpTransitionUrl.append("&");
            } else {
                tmpTransitionUrl.append("?");
            }
            
            if (PAGE_KEY_CCS_DETAIL_CUSTOMER_PROPERTY.equals(dtoReq.getCcsDispId())
                    || PAGE_KEY_CCS_CONTACT_HISTORY.equals(dtoReq.getCcsDispId())
                    || PAGE_KEY_CCS_SHOW_ACCOUNT_SUMMARY_BY_BATCH.equals(dtoReq.getCcsDispId())
                    || PAGE_KEY_CCS_WITHDRAWAL_RECORDING_DATE.equals(dtoReq.getCcsDispId())
                    || PAGE_KEY_CCS_DOCUMENT_REAUEST_LIST.equals(dtoReq.getCcsDispId())
                    || PAGE_KEY_CCS_TRDE_HISTORY.equals(dtoReq.getCcsDispId())) {
                // user_id：ユーザ共通情報.CCSログイン用ID
                tmpTransitionUrl.append(PARAM_KEY_USER_ID);
            }
            tmpTransitionUrl.append("=").append(userAccount.getCcsUserId()).append("&");
            
            // customer_id：顧客共通情報.顧客コード
            tmpTransitionUrl.append(PARAM_KEY_CUSTOMER_ID).append("=").append(customerCommon.getCustomerCode())
                    .append("&");
            
            // branch_no：顧客共通情報.部店コード
            tmpTransitionUrl.append(PARAM_KEY_BUTEN_CODE).append("=").append(customerCommon.getButenCode()).append("&");
            
            // account_no：顧客共通情報.口座番号（前ゼロ埋め7桁固定に編集）
            tmpTransitionUrl.append(PARAM_KEY_ACCOUNT_NUMBER).append("=")
                    .append(StringUtil.fillLeft(customerCommon.getAccountNumber(), CHAR_ZERO, 7));
        }
        
        resDto.setTransitionUrl(tmpTransitionUrl.toString());
        
        DataList<CcsLoginResponseDto> dtoRes = new DataList<CcsLoginResponseDto>();
        List<CcsLoginResponseDto> resDtoList = new ArrayList<CcsLoginResponseDto>();
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
    
    /**
     * アクションID：画面共通部品_CCSログインユーザー情報登録 A003
     * アクション名：CCSログイン情報登録
     * Dto リクエスト：UpdateCcsDataRequestDto
     * Dto レスポンス：UpdateCcsDataResponseDto
     * model リクエスト：CcsLoginSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<UpdateCcsDataResponseDto> updateCcsData(UpdateCcsDataRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CcsLoginServiceImplL.updateCcsData");
        }
        
        // 画面から入力されたCCSパスワード情報を登録
        CcsLoginSql001RequestModel updSql001Req = new CcsLoginSql001RequestModel();
        updSql001Req.setCcsUserPw(dtoReq.getCcsUserPw());
        updSql001Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        dao.updateCcsLoginSql001(updSql001Req);
        
        return IfaCommonUtil.createDataList(new ArrayList<UpdateCcsDataResponseDto>(), ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(), "");
    }
    
    /**
     * アクションID：画面共通部品_CC017_共通ヘッダ A005
     * アクション名：CCS情報リセット
     * Dto リクエスト：ClearCcsDataSelfRequestDto
     * Dto レスポンス：ClearCcsDataSelfResponseDto
     * model リクエスト：CcsLoginSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<ClearCcsDataSelfResponseDto> clearCcsDataSelf(ClearCcsDataSelfRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CcsLoginServiceImplL.clearCcsDataSelf");
        }
        
        // CCSログイン用パスワードを空文字で初期化
        CcsLoginSql001RequestModel updSql001Req = new CcsLoginSql001RequestModel();
        updSql001Req.setCcsUserPw(BLANK);
        updSql001Req.setUserId(IfaCommonUtil.getUserAccount().getUserId());
        dao.updateCcsLoginSql001(updSql001Req);
        
        return IfaCommonUtil.createDataList(new ArrayList<ClearCcsDataSelfResponseDto>(), ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(), "");
    }
    
}

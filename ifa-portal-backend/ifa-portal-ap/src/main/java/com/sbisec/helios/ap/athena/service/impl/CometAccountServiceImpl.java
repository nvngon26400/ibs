package com.sbisec.helios.ap.athena.service.impl;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.AccountProfileName;
import com.sbisec.helios.ap.athena.enums.MarginAccountType;
import com.sbisec.helios.ap.athena.protocol.account.CheckMarginAccountOpenReq;
import com.sbisec.helios.ap.athena.protocol.account.CheckMarginAccountOpenResp;
import com.sbisec.helios.ap.athena.protocol.account.GetAccountProfileReq;
import com.sbisec.helios.ap.athena.protocol.account.GetAccountProfileResp;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusReq;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusRes;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsRes;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleWithdrawalInput;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesRes;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometAccountService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CometAccountServiceImpl extends AbstractBaseService implements CometAccountService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometAccountServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    /** APIタイプ：Athena */
    private static final String ATHENA = "Athena";
    
    /** 区分.外国株式取引口座開設状況 */
    private static final String FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS = "FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS";
    
    /** 区分.外貨建商品取引口座開設状況 */
    private static final String FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS = "FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS";
    
    /** TRADE_FSTOCK_US_PERMISSION */
    private static final String TRADE_FSTOCK_US_PERMISSION = "TRADE_FSTOCK_US_PERMISSION";
    
    /** TRADE_FXBOND_PERMISSION */
    private static final String TRADE_FXBOND_PERMISSION = "TRADE_FXBOND_PERMISSION";
    
    /** 区分.信用口座区分(外国) */
    private static final String FOREIGN_MARGIN_ACCOUNT_TYPE = "FOREIGN_MARGIN_ACCOUNT_TYPE";
    
    
    public ListOperatorScopesRes listOperatorScopes(ListOperatorScopesReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometAccountServiceImpl.listOperatorScopes : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // Token not empty check
            String token = req.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            
            // 必須入力チェック「ccsUserID」
            if (StringUtil.isNullOrEmpty(req.getParameter().getOperatorId())) {
                warnMsg = "OperatorId is not exists or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.list_operator_scopes());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        ListOperatorScopesRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListOperatorScopesRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返す
        return resp;
    }
    
    public MultiGetPossibleWithdrawalsRes multiGetPossibleWithdrawals(MultiGetPossibleWithdrawalsReq req)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometAccountServiceImpl.multiGetPossibleWithdrawals : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }

            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(req.getHeader().getToken())) {
                warnMsg = "Token is not exists or empty!";
                break;
            }
            if (CollectionUtils.isEmpty(req.getParameter().getPossibleWithdrawals())) {
                warnMsg = "PossibleWithdrawals is not exists or empty!";
                break;
            }
            for (PossibleWithdrawalInput input : req.getParameter().getPossibleWithdrawals()) {
                // 必須入力チェック「通貨コード」
                if (StringUtil.isNullOrEmpty(input.getCurrencyCode())) {
                    warnMsg = "CurrencyCode is not exists or empty!";
                    break;
                }
                // 必須入力チェック「出金予定日」
                if (StringUtil.isNullOrEmpty(input.getWithdrawalDate())) {
                    warnMsg = "WithdrawalDate is not exists or empty!";
                    break;
                }
                // 必須入力チェック「口座分類」
                if (StringUtil.isNullOrEmpty(input.getAccountKind())) {
                    warnMsg = "AccountKind is not exists or empty!";
                    break;
                }
                // 必須入力チェック「入出金仕訳種別」
                if (StringUtil.isNullOrEmpty(input.getCashingJournalType())) {
                    warnMsg = "getCashingJournalType is not exists or empty!";
                    break;
                }
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.multi_get_possible_withdrawals());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        MultiGetPossibleWithdrawalsRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(MultiGetPossibleWithdrawalsRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返す
        return resp;
    }
    
    public GetJrNisaAccountStatusRes getJrNisaAccountStatus(GetJrNisaAccountStatusReq req) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometAccountServiceImpl.getJrNisaAccountStatus : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check
            if (null == req) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // 必須入力チェック「トークン」
            if (StringUtil.isNullOrEmpty(req.getHeader().getToken())) {
                warnMsg = "Token is not exists or empty!";
                break;
            }
            
            // 必須入力チェック「営業日」
            if (StringUtil.isNullOrEmpty(req.getParameter().getBaseDate())) {
                warnMsg = "BaseDate is not exists or empty!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.get_jrnisa_account_status());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, req);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // 設定応答メッセージ
        GetJrNisaAccountStatusRes resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetJrNisaAccountStatusRes.class);
            
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返す
        return resp;
    }
    
    /**
     * @Description:口座情報サービス - アカウントプロファイル取得API.
     * 
     * @param getAccountProfileReq Httpリクエスト
     * @return GetAccountProfileResp アカウントプロファイル取得
     * @throws Exception 異常
     */
    @Override
    public GetAccountProfileResp getAccountProfile(GetAccountProfileReq getAccountProfileReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometAccountServiceImpl.getAccountProfile : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // checkNisaOpenReq not empty check
            if (getAccountProfileReq == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // token not empty check
            if (StringUtil.isNullOrEmpty(getAccountProfileReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // token format check
            if (!checkToken(getAccountProfileReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // profileName not empty check
            if (StringUtil.isNullOrEmpty(getAccountProfileReq.getParameter().getProfileName())
                    || null == AccountProfileName.getById(getAccountProfileReq.getParameter().getProfileName())) {
                warnMsg = "ProfileName is not exists or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getAcc_accounts_profiles_profile_name());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, getAccountProfileReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        GetAccountProfileResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetAccountProfileResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        /* 外部コード→内部コード変換 */
        if (!StringUtil.isNullOrEmpty(resp.getProfileValue()) && !StringUtil.isNullOrEmpty(resp.getProfileName())) {
            if (TRADE_FSTOCK_US_PERMISSION.equals(resp.getProfileName())) {
                String convProfileValue = codeListService.convertExtKeyToKey(FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS,
                        ATHENA, resp.getProfileValue());
                resp.setProfileValue(convProfileValue);
            } else if (TRADE_FXBOND_PERMISSION.equals(resp.getProfileName())) {
                String convProfileValue = codeListService.convertExtKeyToKey(FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS,
                        ATHENA, resp.getProfileValue());
                resp.setProfileValue(convProfileValue);
            }
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    @Override
    public CheckMarginAccountOpenResp checkMarginAccountOpen(CheckMarginAccountOpenReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometAccountServiceImpl.checkMarginAccountOpen : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request not empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // Token not empty check
            if (StringUtil.isNullOrEmpty(request.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(request.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // MarginAccountType not empty check
            String marginAccountType = request.getParameter().getMarginAccountType();
            if (!StringUtil.isNullOrEmpty(marginAccountType) && null == MarginAccountType.getById(marginAccountType)) {
                warnMsg = "MarginAccountType is not exists!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getAcc_accounts_check_margin_account_open());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        CheckMarginAccountOpenResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(CheckMarginAccountOpenResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // String型の「opened」を作成
        if (resp.getOpened() != null) {
            if (resp.getOpened()) {
                resp.setConvOpened("true");
            } else {
                resp.setConvOpened("false");
            }
        }
        
        /* 外部コード→内部コード変換 */
        if (!StringUtil.isNullOrEmpty(resp.getConvOpened())) {
            resp.setConvOpened(codeListService.convertExtKeyToKey(FOREIGN_MARGIN_ACCOUNT_TYPE, ATHENA,
                        resp.getConvOpened()));
        }
        
        // 結果を返します
        return resp;
    }
}

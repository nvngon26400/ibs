package com.sbisec.helios.ap.athena.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerReferenceReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerReferenceResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometForeignStockMarginBalanceService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;

/**
 * 余力サービス Service implements.
 * 
 * @author mengzhe.li
 * @date 03/09/2022
 */
@Service
public class CometForeignStockMarginBalanceServiceImpl extends AbstractBaseService
        implements CometForeignStockMarginBalanceService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometForeignStockMarginBalanceServiceImpl.class);
    
    @Override
    public GetMarginPowerHeadlineResp getMarginPowerHeadline(GetMarginPowerHeadlineReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginBalanceServiceImpl.getMarginPowerHeadline : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(request.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // tokenの正確性チェックを行う。
            if (!checkToken(request.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // 必須入力チェック「countryCode」
            if (StringUtil.isNullOrEmpty(request.getParameter().getCountryCode())) {
                warnMsg = "countryCode is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getAcc_account_balance_margin_powers_getHeadline());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        GetMarginPowerHeadlineResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(GetMarginPowerHeadlineResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public GetMarginPowerReferenceResp getMarginPowerReference(GetMarginPowerReferenceReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginBalanceServiceImpl.getMarginPowerReference : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(request.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // tokenの正確性チェックを行う。
            if (!checkToken(request.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // 必須入力チェック「countryCode」
            String countryCode = request.getParameter().getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode) || null == CountryCode.getById(countryCode)) {
                warnMsg = "CountryCode is not exists or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getAcc_account_balance_margin_powers_getReference());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        GetMarginPowerReferenceResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(GetMarginPowerReferenceResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public GetMarginPowerSummaryResp getMarginPowerSummary(GetMarginPowerSummaryReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometForeignStockMarginBalanceServiceImpl.getMarginPowerSummary : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(request.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // tokenの正確性チェックを行う。
            if (!checkToken(request.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // 必須入力チェック「countryCode」
            if (StringUtil.isNullOrEmpty(request.getParameter().getCountryCode())) {
                warnMsg = "countryCode is null or empty!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getAcc_account_balance_margin_powers_getSummary());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        GetMarginPowerSummaryResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(GetMarginPowerSummaryResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
}

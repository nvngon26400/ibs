package com.sbisec.helios.ap.athena.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.enums.CurrencyCode;
import com.sbisec.helios.ap.athena.enums.ProductCode;
import com.sbisec.helios.ap.athena.enums.SpecificAccount;
import com.sbisec.helios.ap.athena.protocol.account.GetSecuritiesBalanceReq;
import com.sbisec.helios.ap.athena.protocol.account.GetSecuritiesBalanceResp;
import com.sbisec.helios.ap.athena.protocol.account.ListSecuritiesBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListSecuritiesBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.SecuritiesBalances;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometSecuritiesBalanceService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.TrimUtil;

@Service
public class CometSecuritiesBalanceServiceImpl extends AbstractBaseService implements CometSecuritiesBalanceService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometSecuritiesBalanceServiceImpl.class);
    
    @Autowired
    private CodeListService codelistservice;
    
    /** APIタイプ：Athena */
    private static final String ATHENA = "Athena";
    
    /** 区分.預り区分（外国） */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    /** 区分.商品コード（外国） */
    private static final String SEC_TYPE_CODE = "SEC_TYPE_CODE";
    
    @Override
    public ListSecuritiesBalancesResp listSecuritiesBalances(ListSecuritiesBalancesReq listSecuritiesBalancesReq)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometSecuritiesBalanceServiceImpl.listSecuritiesBalances : {}", hashCode());
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // listSecuritiesBalancesReq not empty check
            if (listSecuritiesBalancesReq == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // token not empty check
            if (StringUtil.isNullOrEmpty(listSecuritiesBalancesReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // token format check
            if (!checkToken(listSecuritiesBalancesReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // productCode check
            if (!StringUtil.isNullOrEmpty(listSecuritiesBalancesReq.getParameter().getProductCode())
                    && null == ProductCode.getById(listSecuritiesBalancesReq.getParameter().getProductCode())) {
                warnMsg = "ProductCode is not exists!";
                break;
            }
            // countryCode check
            if (!StringUtil.isNullOrEmpty(listSecuritiesBalancesReq.getParameter().getCountryCode())
                    && null == CountryCode.getById(listSecuritiesBalancesReq.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is not exists!";
                break;
            }
            // currencyCode check
            if (!StringUtil.isNullOrEmpty(listSecuritiesBalancesReq.getParameter().getCurrencyCode())
                    && null == CurrencyCode.getById(listSecuritiesBalancesReq.getParameter().getCurrencyCode())) {
                warnMsg = "CurrencyCode is not exists!";
                break;
            }
            // specificAccountCode check
            if (!StringUtil.isNullOrEmpty(listSecuritiesBalancesReq.getParameter().getSpecificAccountCode())
                    && null == SpecificAccount
                            .getById(listSecuritiesBalancesReq.getParameter().getSpecificAccountCode())) {
                warnMsg = "SpecificAccountCode is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getAcc_balance_securities_balances());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, listSecuritiesBalancesReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ListSecuritiesBalancesResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListSecuritiesBalancesResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        // コード変換
        if (!ObjectUtils.isEmpty(resp) && !ObjectUtils.isEmpty(resp.getSecuritiesBalances())) {
            for (SecuritiesBalances security : resp.getSecuritiesBalances()) {
                // 預り区分
                if (!StringUtil.isNullOrEmpty(security.getSpecificAccountCode())) {
                    security.setSpecificAccountCode(codelistservice.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                            security.getSpecificAccountCode()));
                }
                // 銘柄情報.商品コード
                if (!ObjectUtils.isEmpty(security.getSecurities())) {
                    if (!StringUtil.isNullOrEmpty(security.getSecurities().getProductCode())) {
                        security.getSecurities()
                                .setProductCode((codelistservice.convertExtKeyToKey(SEC_TYPE_CODE, ATHENA,
                                        security.getSecurities().getProductCode())));
                    }
                }
                
                // Trim処理
                String[] excludeList = { "acquisitionExchangeRate", "attentionSecurities" };
                TrimUtil.trimStringFieldsConvrertZero(security, excludeList);
                if (!ObjectUtils.isEmpty(security.getStockPrice())) {
                    String[] excludeList2 = { "lastDatetime", "tickArrow", "changePercent", "openDatetime",
                            "highDatetime", "lowDatetime", "prevCloseDate" };
                    TrimUtil.trimStringFieldsConvrertZero(security.getStockPrice(), excludeList2);
                }
                if (!ObjectUtils.isEmpty(security.getEvaluationProfitLoss())) {
                    TrimUtil.trimStringFieldsConvrertZero(security.getEvaluationProfitLoss(), "evaluationExchangeRate");
                }
            }
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    /**
     * 余力サービス - 外貨建商品保有証券取得API.
     * 
     * @param getSecuritiesBalanceReq Httpリクエスト
     * @return GetSecuritiesBalanceResp 外貨建商品保有証券取得
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.GetSecuritiesBalanceReq
     * @see com.sbibits.horus.ap.athena.protocol.account.GetSecuritiesBalanceResp
     */
    @Override
    public GetSecuritiesBalanceResp getSecuritiesBalance(GetSecuritiesBalanceReq getSecuritiesBalanceReq)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometSecuritiesBalanceServiceImpl.GetSecuritiesBalance : {}", hashCode());
        }
        
        /* 内部コード→外部コード変換 */
        // 商品コード
        if (!StringUtil.isNullOrEmpty(getSecuritiesBalanceReq.getParameter().getProductCode())) {
            String productCode = codelistservice.convertKeyToExtKey(SEC_TYPE_CODE, ATHENA,
                    getSecuritiesBalanceReq.getParameter().getProductCode());
            getSecuritiesBalanceReq.getParameter().setProductCode(productCode);
        }

        /* 内部コード→外部コード変換 */
        // 預り区分
        if (!StringUtil.isNullOrEmpty(getSecuritiesBalanceReq.getParameter().getSpecificAccountCode())) {
            String specificAccountCode = codelistservice.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    getSecuritiesBalanceReq.getParameter().getSpecificAccountCode());
            getSecuritiesBalanceReq.getParameter().setSpecificAccountCode(specificAccountCode);
        }

        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // listSecuritiesBalancesReq not empty check
            if (getSecuritiesBalanceReq == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // token not empty check
            if (StringUtil.isNullOrEmpty(getSecuritiesBalanceReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // token format check
            if (!checkToken(getSecuritiesBalanceReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // 必須入力チェック「productCode」
            if (StringUtil.isNullOrEmpty(getSecuritiesBalanceReq.getParameter().getProductCode())) {
                warnMsg = "productCode is null or empty!";
                break;
            }
            // 必須入力チェック「countryCode」
            if (StringUtil.isNullOrEmpty(getSecuritiesBalanceReq.getParameter().getCountryCode())) {
                warnMsg = "countryCode is null or empty!";
                break;
            }
            // 必須入力チェック「currencyCode」
            if (StringUtil.isNullOrEmpty(getSecuritiesBalanceReq.getParameter().getCurrencyCode())) {
                warnMsg = "currencyCode is null or empty!";
                break;
            }
            // 必須入力チェック「specificAccountCode」
            if (StringUtil.isNullOrEmpty(getSecuritiesBalanceReq.getParameter().getSpecificAccountCode())) {
                warnMsg = "specificAccountCode is null or empty!";
                break;
            }
            // 必須入力チェック「securitiesCode」
            if (StringUtil.isNullOrEmpty(getSecuritiesBalanceReq.getParameter().getSecuritiesCode())) {
                warnMsg = "securitiesCode is null or empty!";
                break;
            }
            // ProductCode.getById check
            if (null == ProductCode.getById(getSecuritiesBalanceReq.getParameter().getProductCode())) {
                warnMsg = "ProductCode is not exists!";
                break;
            }
            // countryCode.getById check
            if (null == CountryCode.getById(getSecuritiesBalanceReq.getParameter().getCountryCode())) {
                warnMsg = "CountryCode is not exists!";
                break;
            }
            // currencyCode.getById check
            if (null == CurrencyCode.getById(getSecuritiesBalanceReq.getParameter().getCurrencyCode())) {
                warnMsg = "CurrencyCode is not exists!";
                break;
            }
            // specificAccountCode.getById check
            if (null == SpecificAccount.getById(getSecuritiesBalanceReq.getParameter().getSpecificAccountCode())) {
                warnMsg = "SpecificAccountCode is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getAcc_balance_securities());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, getSecuritiesBalanceReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        GetSecuritiesBalanceResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetSecuritiesBalanceResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        // コード変換
        if (!ObjectUtils.isEmpty(resp)) {
            // 銘柄情報.預り区分
            if (!ObjectUtils.isEmpty(resp.getSpecificAccountCode())) {
                if (!StringUtil.isNullOrEmpty(resp.getSpecificAccountCode())) {
                    String specificAccountCode = codelistservice.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, 
                            ATHENA, resp.getSpecificAccountCode());
                    resp.setSpecificAccountCode(specificAccountCode);
                }
            }
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
}

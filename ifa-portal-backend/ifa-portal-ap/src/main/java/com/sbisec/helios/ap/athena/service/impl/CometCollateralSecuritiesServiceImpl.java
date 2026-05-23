package com.sbisec.helios.ap.athena.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.enums.CurrencyCode;
import com.sbisec.helios.ap.athena.enums.ProductCode;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersReq;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransfer;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleCollateralSecuritiesTransfer;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometCollateralSecuritiesService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

@Service
public class CometCollateralSecuritiesServiceImpl extends AbstractBaseService
        implements CometCollateralSecuritiesService {
    
    @Autowired
    private CodeListService codeListService;
    
    /** 区分ID:預り区分（外国） */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    /** APIタイプ：Athena */
    private static final String ATHENA = "Athena";
    
    private static final Logger LOG = LoggerFactory.getLogger(CometCollateralSecuritiesServiceImpl.class);
    
    @Override
    public ListPossibleCollateralSecuritiesTransfersResp listPossibleCollateralSecuritiesTransfers(
            ListPossibleCollateralSecuritiesTransfersReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometCollateralSecuritiesServiceImpl.listPossibleCollateralSecuritiesTransfers : {}",
                    hashCode());
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
            String token = request.getHeader().getToken();
            if (StringUtil.isNullOrEmpty(token)) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(token)) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // ProductCode not empty check
            String productCode = request.getParameter().getProductCode();
            if (StringUtil.isNullOrEmpty(productCode)) {
                warnMsg = "ProductCode is null or empty!";
                break;
            }
            if (null == ProductCode.getById(productCode)) {
                warnMsg = "ProductCode " + productCode + " is not exists!";
                break;
            }
            // CountryCode not empty check
            String countryCode = request.getParameter().getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode)) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            if (null == CountryCode.getById(countryCode)) {
                warnMsg = "CountryCode " + countryCode + " is not exists!";
                break;
            }
            // CurrencyCode not empty check
            String currencyCode = request.getParameter().getCurrencyCode();
            if (StringUtil.isNullOrEmpty(currencyCode)) {
                warnMsg = "CurrencyCode is null or empty!";
                break;
            }
            if (null == CurrencyCode.getById(currencyCode)) {
                warnMsg = "CurrencyCode " + currencyCode + " is not exists!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getAcc_balance_possible_collateral_securities_balances());
        
        // get要求を送信
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ListPossibleCollateralSecuritiesTransfersResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ListPossibleCollateralSecuritiesTransfersResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        // 預り区分の区分値を内部コード変換する
        for (PossibleCollateralSecuritiesTransfer transfer : resp.getProtectionTransfers()) {
            // 保護→代用
            transfer.setSpecificAccountCode(codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    transfer.getSpecificAccountCode()));
        }
        for (PossibleCollateralSecuritiesTransfer transfer : resp.getCollateralTransfers()) {
            // 代用→保護
            transfer.setSpecificAccountCode(codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    transfer.getSpecificAccountCode()));
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
}

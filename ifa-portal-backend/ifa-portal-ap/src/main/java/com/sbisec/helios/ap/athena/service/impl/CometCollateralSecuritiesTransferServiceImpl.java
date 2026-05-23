package com.sbisec.helios.ap.athena.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.enums.CashTransferType;
import com.sbisec.helios.ap.athena.enums.CurrencyCode;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmCollateralSecuritiesTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.ConfirmMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.CreateMarginTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.CreateMarginTransferResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransfer;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransferDetailInput;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransferInput;
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferReq;
import com.sbisec.helios.ap.athena.protocol.account.CreateCollateralSecuritiesTransferResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometCollateralSecuritiesTransferService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

@Service
public class CometCollateralSecuritiesTransferServiceImpl extends AbstractBaseService
        implements CometCollateralSecuritiesTransferService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometCollateralSecuritiesTransferServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    /** APIタイプ：Athena */
    private static final String ATHENA = "Athena";
    
    /** 区分.保証金振替区分 */
    private static final String DEPOSIT_TRANSFER_TYPE = "DEPOSIT_TRANSFER_TYPE";
    
    /** 保護区分：保護預り */
    private static final String PROTECTION_KEEPING = "PROTECTION_KEEPING";
    
    /** 保護区分：代用預り */
    private static final String COLLATERAL_SECURITIES = "COLLATERAL_SECURITIES";
    
    /** 区分ID:預り区分（外国） */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    @Override
    public ConfirmMarginTransferResp confirmMarginTransfer(ConfirmMarginTransferReq confirmMarginTransferReq)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometCollateralSecuritiesTransferServiceImpl.confirmMarginTransfer : {}", hashCode());
        }
        
        /* 内部コード→外部コード変換 */
        // 委託保証金振替区分
        if (!StringUtil.isNullOrEmpty(confirmMarginTransferReq.getParameter().getCashTransferType())) {
            String cashTransferType = codeListService.convertKeyToExtKey(DEPOSIT_TRANSFER_TYPE, ATHENA,
                    confirmMarginTransferReq.getParameter().getCashTransferType());
            confirmMarginTransferReq.getParameter().setCashTransferType(cashTransferType);
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // checkNisaOpenReq not empty check
            if (null == confirmMarginTransferReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // token not empty check
            if (StringUtil.isNullOrEmpty(confirmMarginTransferReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // token format check
            if (!checkToken(confirmMarginTransferReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // CurrencyCode not empty check
            String currencyCode = confirmMarginTransferReq.getParameter().getCurrencyCode();
            if (StringUtil.isNullOrEmpty(currencyCode) || null == CurrencyCode.getById(currencyCode)) {
                warnMsg = "CurrencyCode is not exists or empty!";
                break;
            }
            // CashTransferType not empty check
            String cashTransferType = confirmMarginTransferReq.getParameter().getCashTransferType();
            if (StringUtil.isNullOrEmpty(cashTransferType) || null == CashTransferType.getById(cashTransferType)) {
                warnMsg = "CashTransferType is not exists or empty!";
                break;
            }
            // Amount non empty check
            String amount = confirmMarginTransferReq.getParameter().getAmount();
            if (StringUtil.isNullOrEmpty(amount)) {
                warnMsg = "Amount is null or empty!";
                break;
            }
            if (!checkRange(new BigDecimal(amount), TRANSFERAMOUNT_MIN_VALUE, TRANSFERAMOUNT_MAX_VALUE)) {
                warnMsg = "Amount " + amount + " is out of this range!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getAcc_client_entry_margin_transfers_confirm());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, confirmMarginTransferReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ConfirmMarginTransferResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ConfirmMarginTransferResp.class);
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
    public CreateMarginTransferResp createMarginTransfer(CreateMarginTransferReq createMarginTransferReq)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometCollateralSecuritiesTransferServiceImpl.createMarginTransfer : {}", hashCode());
        }
        
        /* 内部コード→外部コード変換 */
        // 委託保証金振替区分
        if (!StringUtil.isNullOrEmpty(createMarginTransferReq.getParameter().getCashTransferType())) {
            String cashTransferType = codeListService.convertKeyToExtKey(DEPOSIT_TRANSFER_TYPE, ATHENA,
                    createMarginTransferReq.getParameter().getCashTransferType());
            createMarginTransferReq.getParameter().setCashTransferType(cashTransferType);
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // checkNisaOpenReq not empty check
            if (null == createMarginTransferReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            // token not empty check
            if (StringUtil.isNullOrEmpty(createMarginTransferReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // token format check
            if (!checkToken(createMarginTransferReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // CurrencyCode not empty check
            String currencyCode = createMarginTransferReq.getParameter().getCurrencyCode();
            if (StringUtil.isNullOrEmpty(currencyCode) || null == CurrencyCode.getById(currencyCode)) {
                warnMsg = "CurrencyCode is not exists or empty!";
                break;
            }
            // CashTransferType not empty check
            String cashTransferType = createMarginTransferReq.getParameter().getCashTransferType();
            if (StringUtil.isNullOrEmpty(cashTransferType) || null == CashTransferType.getById(cashTransferType)) {
                warnMsg = "CashTransferType is not exists or empty!";
                break;
            }
            // Amount non empty check
            String amount = createMarginTransferReq.getParameter().getAmount();
            if (StringUtil.isNullOrEmpty(amount)) {
                warnMsg = "Amount is null or empty!";
                break;
            }
            if (!checkRange(new BigDecimal(amount), TRANSFERAMOUNT_MIN_VALUE, TRANSFERAMOUNT_MAX_VALUE)) {
                warnMsg = "Amount " + amount + " is out of this range!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getAcc_client_entry_margin_transfers());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, createMarginTransferReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        CreateMarginTransferResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(CreateMarginTransferResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        /* 外部コード→内部コード変換 */
        // 委託保証金振替区分
        if (StringUtil.isNullOrEmpty(resp.getCashTransferType())) {
            String cashTransferType = codeListService.convertExtKeyToKey(DEPOSIT_TRANSFER_TYPE, ATHENA,
                    resp.getCashTransferType());
            resp.setCashTransferType(cashTransferType);
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public ConfirmCollateralSecuritiesTransferResp confirmCollateralSecuritiesTransfer(
            ConfirmCollateralSecuritiesTransferReq request) throws Exception {

        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometCollateralSecuritiesTransferServiceImpl.confirmCollateralSecuritiesTransfer : {}",
                    hashCode());
        }
        
        // 預り区分の外部コード変換
        for (CollateralSecuritiesTransferInput transfer : request.getParameter().getTransfers()) {
            for (CollateralSecuritiesTransferDetailInput transferDetails : transfer.getTransferDetails()) {
                transferDetails.setSpecificAccountCode(codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                        transferDetails.getSpecificAccountCode()));
            }
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request non empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            ConfirmCollateralSecuritiesTransferReq.Header header = request.getHeader();
            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(header.getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // tokenの正確性チェックを行う。
            if (!checkToken(header.getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「代用有価証券振替入力情報」：空チェック
            List<CollateralSecuritiesTransferInput> transfers = request.getParameter().getTransfers();
            if (ObjectUtils.isEmpty(transfers)) {
                warnMsg = "Transfers is null!";
                break;
            } else {
                for (CollateralSecuritiesTransferInput transfer : transfers) {
                    // 「代用有価証券振替区分」：必須入力チェック、存在チェック
                    if (StringUtil.isNullOrEmpty(transfer.getSecuritiesTransferType())) {
                        warnMsg = "SecuritiesTransferType is null or empty!";
                        break;
                    }
                    
                    // 「振替詳細情報」：空チェック
                    List<CollateralSecuritiesTransferDetailInput> transferDetails = transfer.getTransferDetails();
                    if (ObjectUtils.isEmpty(transferDetails)) {
                        warnMsg = "TransferDetails is null!";
                        break;
                    } else {
                        for (CollateralSecuritiesTransferDetailInput transferDetail : transferDetails) {
                            // 「国コード」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getCountryCode())) {
                                warnMsg = "CountryCode is null or empty!";
                                break;
                            }
                            
                            // 「商品コード」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getProductCode())) {
                                warnMsg = "ProductCode is null or empty!";
                                break;
                            }
                            
                            // 「銘柄コード」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getSecuritiesCode())) {
                                warnMsg = "SecuritiesCode is null or empty!";
                                break;
                            }
                            
                            // 「預り区分」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getSpecificAccountCode())) {
                                warnMsg = "SpecificAccountCode is null or empty!";
                                break;
                            }
                            
                            // 「保護区分」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getDepositType())) {
                                warnMsg = "DepositType is null or empty!";
                                break;
                            }
                            
                            // 「保護区分」：許可値チェック
                            if (!PROTECTION_KEEPING.equals(transferDetail.getDepositType())
                                    && !COLLATERAL_SECURITIES.equals(transferDetail.getDepositType())) {
                                warnMsg = "DepositType " + transferDetail.getDepositType() + " is not allowed!";
                                break;
                            }
                        }
                    }
                }
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getAcc_client_entry_collateral_securities_transfers_confirm());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        ConfirmCollateralSecuritiesTransferResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(ConfirmCollateralSecuritiesTransferResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        // 預り区分リストの区分値を変換する
        for (CollateralSecuritiesTransfer transfer : resp.getTransfers()) {
            transfer.setSpecificAccountCode(codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    transfer.getSpecificAccountCode()));
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public CreateCollateralSecuritiesTransferResp createCollateralSecuritiesTransfer(
            CreateCollateralSecuritiesTransferReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometCollateralSecuritiesTransferServiceImpl.createCollateralSecuritiesTransfer : {}",
                    hashCode());
        }
        
        // 預り区分の外部コード変換
        for (CollateralSecuritiesTransferInput transfer : request.getParameter().getTransfers()) {
            for (CollateralSecuritiesTransferDetailInput transferDetails : transfer.getTransferDetails()) {
                transferDetails.setSpecificAccountCode(codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                        transferDetails.getSpecificAccountCode()));
            }
        }
        
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // request non empty check.
            if (null == request) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // headerを設定する
            CreateCollateralSecuritiesTransferReq.Header header = request.getHeader();
            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(header.getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // tokenの正確性チェックを行う。
            if (!checkToken(header.getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「代用有価証券振替入力情報」：空チェック
            List<CollateralSecuritiesTransferInput> transfers = request.getParameter().getTransfers();
            if (ObjectUtils.isEmpty(transfers)) {
                warnMsg = "Transfers is null!";
                break;
            } else {
                for (CollateralSecuritiesTransferInput transfer : transfers) {
                    // 「代用有価証券振替区分」：必須入力チェック、存在チェック
                    if (StringUtil.isNullOrEmpty(transfer.getSecuritiesTransferType())) {
                        warnMsg = "SecuritiesTransferType is null or empty!";
                        break;
                    }
                    
                    // 「振替詳細情報」：空チェック
                    List<CollateralSecuritiesTransferDetailInput> transferDetails = transfer.getTransferDetails();
                    if (ObjectUtils.isEmpty(transferDetails)) {
                        warnMsg = "TransferDetails is null!";
                        break;
                    } else {
                        for (CollateralSecuritiesTransferDetailInput transferDetail : transferDetails) {
                            // 「国コード」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getCountryCode())) {
                                warnMsg = "CountryCode is null or empty!";
                                break;
                            }
                            
                            // 「商品コード」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getProductCode())) {
                                warnMsg = "ProductCode is null or empty!";
                                break;
                            }
                            
                            // 「銘柄コード」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getSecuritiesCode())) {
                                warnMsg = "SecuritiesCode is null or empty!";
                                break;
                            }
                            
                            // 「預り区分」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getSpecificAccountCode())) {
                                warnMsg = "SpecificAccountCode is null or empty!";
                                break;
                            }
                            
                            // 「保護区分」：必須入力チェック、存在チェック
                            if (StringUtil.isNullOrEmpty(transferDetail.getDepositType())) {
                                warnMsg = "DepositType is null or empty!";
                                break;
                            }
                            
                            // 「保護区分」：許可値チェック
                            if (!PROTECTION_KEEPING.equals(transferDetail.getDepositType())
                                    && !COLLATERAL_SECURITIES.equals(transferDetail.getDepositType())) {
                                warnMsg = "DepositType " + transferDetail.getDepositType() + " is not allowed!";
                                break;
                            }
                        }
                    }
                }
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        String url = this.getUrl(CometApiUtil.getAcc_client_entry_collateral_securities_transfers());
        
        // post要求を送信
        OkHttpResponse httpResp = this.post(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        // 設定応答メッセージ
        CreateCollateralSecuritiesTransferResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(CreateCollateralSecuritiesTransferResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        // 預り区分リストの区分値を変換する
        for (CollateralSecuritiesTransfer transfer : resp.getTransfers()) {
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

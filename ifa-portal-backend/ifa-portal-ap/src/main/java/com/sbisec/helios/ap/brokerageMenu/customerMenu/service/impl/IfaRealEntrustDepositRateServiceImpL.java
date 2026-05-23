package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaRealEntrustDepositRateService;
import com.sbisec.helios.ap.athena.protocol.account.ListDepositRateBasisResp;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.athena.protocol.account.dto.DepositRateBasis;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 画面ID：SUB0202_010304-02
 * 画面名：リアル委託保証金率

 * SCSK　大崎辰弥
 * 2023/12/01 新規作成
 */
@Component(value = "cmpIfaRealEntrustDepositRateService")
public class IfaRealEntrustDepositRateServiceImpL implements IfaRealEntrustDepositRateService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaRealEntrustDepositRateServiceImpL.class);
    
    /** 権限チェック値 「権限なし」 */
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG_0 = "0";
    
    /** 権限チェックエラー */
    public static final String ERRORS_BUTEN_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 外国信用口座開設状況チェック値 「未開設」 */
    public static final String FOREIGN_MARGIN_ACCOUNT_TYPE_VALUE = " ";
    
    /** 外国信用口座未開設エラー */
    public static final String ERRORS_FRS_FOREIGN_STOCK_ACCOUNT_NOT_OPEN = "errors.frs.foreignStockAccount.notOpen#1";
    
    /** 国コード 「US」 */
    public static final String COUNTRY_CODE_US = "US";
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private ForeignAccountService foreignAccountService;

    @Autowired
    private CometCommonService cometCommonService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaRealEntrustDepositRateA001DtoRequest
     * Dto レスポンス：IfaRealEntrustDepositRateA001DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
    
     * @param dtoReq リクエスト
     * @return IfaRealEntrustDepositRateA001DtoResponse レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaRealEntrustDepositRateA001DtoResponse> initializeA001(
            IfaRealEntrustDepositRateA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRealEntrustDepositRateServiceImplL.initializeA001");
        }
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // 利用者の口座に対する権限チェックを行う。
        
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(butenCode);
        inputFct001Dto.setAccountNumber(accountNumber);
        
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        if (TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            String message = IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                    new String[] { butenCode, accountNumber });
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXISTS, message);
        }
        
        // 顧客共通情報の「信用口座区分(外国)」より、外国信用口座開設状況をチェックを行う。
        
        // 顧客共通情報の取得
        String foreignMarginAccountType = cc.getForeignMarginAccountType();
        
        if (FOREIGN_MARGIN_ACCOUNT_TYPE_VALUE.equals(foreignMarginAccountType)) {
            String message = IfaCommonUtil.getMessage(ERRORS_FRS_FOREIGN_STOCK_ACCOUNT_NOT_OPEN);
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_FRS_FOREIGN_STOCK_ACCOUNT_NOT_OPEN,
                    message);
        }
        
        // リアルタイム委託保証金率情報を取得する。
        
        // API001呼び出し
        ListDepositRateBasisResp api001Response = null;
        try {            
            api001Response = callApi001(butenCode, accountNumber);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        IfaRealEntrustDepositRateA001DtoResponse realEntrustDepositRate = new IfaRealEntrustDepositRateA001DtoResponse();
        realEntrustDepositRate = setRealEntrustDepositRate(api001Response);
        
        // DataListの作成
        List<IfaRealEntrustDepositRateA001DtoResponse> dtoResList = new ArrayList<IfaRealEntrustDepositRateA001DtoResponse>();
        dtoResList.add(realEntrustDepositRate);
        
        return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), null);
    }
    
    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaRealEntrustDepositRateA002DtoRequest
     * Dto レスポンス：IfaRealEntrustDepositRateA002DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
    
     * @param dtoReq リクエスト
     * @return IfaRealEntrustDepositRateA002DtoResponse レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaRealEntrustDepositRateA002DtoResponse> updateA002(IfaRealEntrustDepositRateA002DtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRealEntrustDepositRateServiceImplL.updateA002");
        }
        
        // リアルタイム委託保証金率情報を取得する。
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        // API001呼び出し
        ListDepositRateBasisResp api001Response = null;
        try {
            api001Response = callApi001(butenCode, accountNumber);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        IfaRealEntrustDepositRateA001DtoResponse realEntrustDepositRate = new IfaRealEntrustDepositRateA001DtoResponse();
        realEntrustDepositRate = setRealEntrustDepositRate(api001Response);
        
        // A001用のモデルをA002用に変換
        IfaRealEntrustDepositRateA002DtoResponse realEntrustDepositRateA002 = new IfaRealEntrustDepositRateA002DtoResponse();
        BeanUtils.copyProperties(realEntrustDepositRateA002, realEntrustDepositRate);
        
        // DataListの作成
        List<IfaRealEntrustDepositRateA002DtoResponse> dtoResList = new ArrayList<IfaRealEntrustDepositRateA002DtoResponse>();
        dtoResList.add(realEntrustDepositRateA002);
        
        return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), null);
    }
    
    /**
     * リアルタイム委託保証金率取得API
    
     * @param butenCode 部店コード
     * @param butenCode 口座番号
     * @return ListDepositRateBasisResp APIレスポンス
     */
    
    private ListDepositRateBasisResp callApi001(String butenCode, String accountNumber) throws Exception {
        
        ListDepositRateBasisResp listDepositRateBasisResp = null;
        try {
            listDepositRateBasisResp = foreignAccountService.listDepositRateBasis(butenCode, accountNumber,
                    COUNTRY_CODE_US);
        } catch (Exception e) {
            throw e;
        }
        return listDepositRateBasisResp;
    }
    
    /**
     *　APIのレスポンスをDTOレスポンスにセット
    
     * @param ListDepositRateBasisResp APIレスポンス
     * @return IfaRealEntrustDepositRateA001DtoResponse DTOレスポンス
     */
    
    private IfaRealEntrustDepositRateA001DtoResponse setRealEntrustDepositRate(
            ListDepositRateBasisResp api001Response) {
        
        IfaRealEntrustDepositRateA001DtoResponse realEntrustDepositRate = new IfaRealEntrustDepositRateA001DtoResponse();
        DepositRateBasis depositRateBasis = new DepositRateBasis();
        // リアルタイム委託保証金率リスト【0】
        depositRateBasis = api001Response.getDepositRateBasis().get(0);
        
        // 当日基準_委託保証金率
        realEntrustDepositRate.setTodayBaseMarginDepositRate8(depositRateBasis.getMarginRate());
        // 当日基準_(A)実質保証金
        realEntrustDepositRate.setTodayBaseForeignCurrencyactualDeposit(depositRateBasis.getActualMargin());
        // 当日基準_委託保証金現金
        realEntrustDepositRate.setTodayBaseMarginDepositCache(depositRateBasis.getMarginCash());
        // 当日基準_代用有価証券評価額合計
        realEntrustDepositRate
                .setTodayBaseAmericaAlternativeSecuritiesTotal(depositRateBasis.getTotalCollateralValue());
        // 当日基準_建玉評価損合計
        realEntrustDepositRate.setTodayBaseTotalOpenInterestValuationLoss(depositRateBasis.getTotalEvaluationAmount());
        // 当日基準_決済損益合計
        realEntrustDepositRate.setTodayBaseTotalSettlementProfitLoss(depositRateBasis.getTotalClosedProfitLoss());
        // 当日基準_支払諸経費等合計
        realEntrustDepositRate.setTodayBaseTotalExpensesPaid(depositRateBasis.getTotalExpenses());
        // 当日基準_参考委託保証金率
        realEntrustDepositRate.setTodayBaseForeignReferenceMarginDeposit(depositRateBasis.getReferenceDepositRate());
        // 当日基準_参考保証金
        realEntrustDepositRate.setTodayBaseReferenceSecurityDeposit(depositRateBasis.getReferenceMargin());
        // 当日基準_(C)預り金
        realEntrustDepositRate.setTodayBaseForeignCurrencydeposit(depositRateBasis.getTransferPossibleAmount());
        // 当日基準_(D)保護預り有価証券評価額合計
        realEntrustDepositRate.setTodayBaseTotalAppraisalValueOfSecuritiesInCustody(
                depositRateBasis.getTotalProtectionEvaluationAmount());
        // 当日基準_(B)建代金合計
        realEntrustDepositRate.setTodayBaseConstructionPriceTotal(depositRateBasis.getTotalPositionAmount());
        // 値洗い基準_委託保証金率 
        realEntrustDepositRate.setMarkToBaseMarginDepositRate8(api001Response.getMarginRate());
        // 値洗い基準_(A)実質保証金
        realEntrustDepositRate.setMarkToBaseForeignCurrencyactualDeposit(api001Response.getActualMargin());
        // 値洗い基準_委託保証金現金
        realEntrustDepositRate.setMarkToBaseMarginDepositCache(api001Response.getMarginCash());
        // 値洗い基準_代用有価証券評価額合計
        realEntrustDepositRate.setMarkToBaseAmericaAlternativeSecuritiesTotal(api001Response.getTotalCollateralValue());
        // 値洗い基準_建玉評価損合計
        realEntrustDepositRate.setMarkToBaseTotalOpenInterestValuationLoss(api001Response.getTotalEvaluationAmount());
        // 値洗い基準_決済損益合計
        realEntrustDepositRate.setMarkToBaseTotalSettlementProfitLoss(api001Response.getTotalClosedProfitLoss());
        // 値洗い基準_支払諸経費等合計
        realEntrustDepositRate.setMarkToBaseTotalExpensesPaid(api001Response.getTotalExpenses());
        // 値洗い基準_参考委託保証金率
        realEntrustDepositRate.setMarkToBaseForeignReferenceMarginDeposit("-");
        // 値洗い基準_参考保証金
        realEntrustDepositRate.setMarkToBaseReferenceSecurityDeposit("-");
        // 値洗い基準_(C)預り金
        realEntrustDepositRate.setMarkToBaseForeignCurrencydeposit("-");
        // 値洗い基準_(D)保護預り有価証券評価額合計
        realEntrustDepositRate.setMarkToBaseTotalAppraisalValueOfSecuritiesInCustody("-");
        // 値洗い基準_(B)建代金合計
        realEntrustDepositRate.setMarkToBaseConstructionPriceTotal(api001Response.getTotalPositionAmount());
        
        return realEntrustDepositRate;
    }
}

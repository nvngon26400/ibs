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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticX001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticX001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRealEntrustDepositRateDomesticDtoResponseDetailT;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaRealEntrustDepositRateDomesticService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;

import jp.co.sbisec.pcenter.dto.yanap.QueryRealCapabilityIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryRealCapabilityInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryRealCapabilityOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryRealCapabilityOutDetailT;


/**
 * 画面ID：SUB0202_010302-03
 * 画面名：リアル委託保証金率（国内）
 
 * SCSK
 * 2024/07/29 新規作成
 */
@Component(value = "cmpIfaRealEntrustDepositRateDomesticService")
public class IfaRealEntrustDepositRateDomesticServiceImpL implements IfaRealEntrustDepositRateDomesticService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaRealEntrustDepositRateDomesticServiceImpL.class);
    
    /** 権限チェック値 「権限なし」 */
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG_0 = "0";
    
    /** 権限チェックエラー */
    public static final String ERRORS_BUTEN_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /**
     * NRI_APIラッパー
     */
    @Autowired
    private ApiWrapper apiWrapper;

    @Autowired
    private Fct001 fct001;
    
    /**
     * アクションID：X001
     * アクション名：初期化
     * Dto リクエスト：IfaRealEntrustDepositRateDomesticX001DtoRequest
     * Dto レスポンス：IfaRealEntrustDepositRateDomesticX001DtoResponse

     * @param dtoReq リクエスト
     * @return IfaRealEntrustDepositRateDomesticX001DtoResponse レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaRealEntrustDepositRateDomesticX001DtoResponse> initializeX001(
            IfaRealEntrustDepositRateDomesticX001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRealEntrustDepositRateDomesticServiceImplL.initializeX001");
        }

        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        
        //==============================
        // 利用者の口座に対する権限チェックを行う。(FTC001)
        //==============================
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(butenCode);
        inputFct001Dto.setAccountNumber(accountNumber);
        
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        if (TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            String message = IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                    new String[] { butenCode, accountNumber });
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXISTS, message);
        }
        
        //==============================
        // API001（リアル維持率リクエスト）呼び出し
        //==============================
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();

        QueryRealCapabilityOutData api001Output = new QueryRealCapabilityOutData();
        
        try {
            api001Output = callApi001(butenCode, accountNumber);
            if (api001Output != null) {
                // APIレスポンスチェック
                apiErrorUtil.checkApiResponse(api001Output.getShubetu(), api001Output.getCode(), api001Output.getMessage());
                if (apiErrorUtil.isFatal()) {
                    return apiErrorUtil.createDataList(List.of(), "");
                }
            }
        } catch (ApiError ae) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ae.getMessage(), "");
        }

        //==============================
        // API001レスポンスセット
        //==============================
        List<IfaRealEntrustDepositRateDomesticDtoResponseDetailT> detailTList = getDetailT(api001Output);
        IfaRealEntrustDepositRateDomesticX001DtoResponse dtoRes = new IfaRealEntrustDepositRateDomesticX001DtoResponse();
        dtoRes.setButenCode(butenCode); // 部店コード
        dtoRes.setAccountNumber(accountNumber); // 口座番号
        // API001.OUT.out_detail_t[]
        // 0：値洗い基準
        // 1：当日基準(T+0)
        // 2：翌日基準(T+1)
        dtoRes.setMarkToBase(detailTList.get(0)); // 値洗い基準
        dtoRes.setTodayBase(detailTList.get(1)); // 当日基準
        dtoRes.setNextBusinessDayBase(detailTList.get(2)); // 翌営業日基準

        // DataListの作成
        return IfaCommonUtil.createDataList(List.of(dtoRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), null);
    }
    
    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaRealEntrustDepositRateDomesticA002DtoRequest
     * Dto レスポンス：IfaRealEntrustDepositRateDomesticA002DtoResponse

     * @param dtoReq リクエスト
     * @return IfaRealEntrustDepositRateDomesticA002DtoResponse レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaRealEntrustDepositRateDomesticA002DtoResponse> updateA002(IfaRealEntrustDepositRateDomesticA002DtoRequest dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRealEntrustDepositRateDomesticServiceImplL.updateA002");
        }
        
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        
        //==============================
        // API001（リアル維持率リクエスト）呼び出し
        //==============================
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();     
        
        QueryRealCapabilityOutData api001Output = new QueryRealCapabilityOutData();
        
        try {
            api001Output = callApi001(butenCode, accountNumber);
            if (api001Output != null) {
                // APIレスポンスチェック
                apiErrorUtil.checkApiResponse(api001Output.getShubetu(), api001Output.getCode(), api001Output.getMessage());
                if (apiErrorUtil.isFatal()) {
                    return apiErrorUtil.createDataList(List.of(), "");
                }
            }
        } catch (ApiError ae) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ae.getMessage(), "");
        }

        //==============================
        // API001レスポンスセット
        //==============================
        List<IfaRealEntrustDepositRateDomesticDtoResponseDetailT> detailTList = getDetailT(api001Output);
        IfaRealEntrustDepositRateDomesticA002DtoResponse dtoRes = new IfaRealEntrustDepositRateDomesticA002DtoResponse();
        dtoRes.setButenCode(butenCode); // 部店コード
        dtoRes.setAccountNumber(accountNumber); // 口座番号
        // API001.OUT.out_detail_t[]
        // 0：値洗い基準
        // 1：当日基準(T+0)
        // 2：翌日基準(T+1)
        dtoRes.setMarkToBase(detailTList.get(0)); // 値洗い基準
        dtoRes.setTodayBase(detailTList.get(1)); // 当日基準
        dtoRes.setNextBusinessDayBase(detailTList.get(2)); // 翌営業日基準

        // DataListの作成
        return IfaCommonUtil.createDataList(List.of(dtoRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), null);
    }

    /**
     * API001（QueryRealCapability）の呼び出し
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return API001レスポンス
     * @throws Exception API001の呼び出しで例外が発生した場合
     */
    private QueryRealCapabilityOutData callApi001(String butenCode, String accountNumber) throws Exception {
        
        QueryRealCapabilityInData inData = new QueryRealCapabilityInData();
        inData.setOfficeCode(butenCode.substring(0, 3)); // 部店コード(3桁)
        inData.setAccountNo(String.format("%7s", accountNumber).replace(" ", "0")); // 口座番号（7桁、右詰め、前ZERO）
        inData.setInputKbn("OPT"); // 入力区分（'OPT'：オペレータ 固定）

        QueryRealCapabilityIn input = new QueryRealCapabilityIn();
        
        input.setIndata(inData);
        QueryRealCapabilityOutData api001Output = apiWrapper.queryRealCapability(input);

        return api001Output;
    }

    /**
     * API001.OUT.out_detail_tを取得
     *
     * @param api001Output API001レスポンス
     * @return out_detail_tのリスト
     */
    private List<IfaRealEntrustDepositRateDomesticDtoResponseDetailT> getDetailT(QueryRealCapabilityOutData api001Output) {
        List<IfaRealEntrustDepositRateDomesticDtoResponseDetailT> detailTList = new ArrayList<IfaRealEntrustDepositRateDomesticDtoResponseDetailT>();
        for (QueryRealCapabilityOutDetailT apiDetail : api001Output.getOutDetailT()) {
            IfaRealEntrustDepositRateDomesticDtoResponseDetailT detailT = new IfaRealEntrustDepositRateDomesticDtoResponseDetailT();
            detailT.setEntrustDepositRate(apiDetail.getKeepRate()); // 委託保証金率
            detailT.setEntrustDepositRateType(apiDetail.getKeepRateKbn()); // 委託保証金率区分
            detailT.setMarginDepositCache(apiDetail.getBalanceSbi()); // 委託保証金現金
            detailT.setAlternativeSecuritiesTotal(apiDetail.getSecuritySubstitute()); // 代用有価証券評価額合計
            detailT.setTotalOpenInterestValuationLoss(apiDetail.getApprLoss()); // 建玉評価損合計
            detailT.setTotalSettlementProfitLoss(apiDetail.getSettleLoss()); // 決済損益合計
            detailT.setTotalExpensesPaid(apiDetail.getPaymentCost()); // 支払諸経費等合計
            detailT.setCurrencyActualDeposit(apiDetail.getRealGuaranteeTotal()); // 実質保証金
            detailT.setConstructionPriceTotal(apiDetail.getUnclosedAmount()); // 建代金合計
            detailT.setReferenceMarginDeposit(apiDetail.getRefKeepRate()); // 参考委託保証金率
            detailT.setReferenceMarginDepositType(apiDetail.getRefKeepRateKbn()); // 参考委託保証金率区分
            detailT.setHybridDepositBalance(apiDetail.getBalanceSsnb()); // SBIハイブリッド預金残高
            detailTList.add(detailT);
        }

        return detailTList;
    }
}

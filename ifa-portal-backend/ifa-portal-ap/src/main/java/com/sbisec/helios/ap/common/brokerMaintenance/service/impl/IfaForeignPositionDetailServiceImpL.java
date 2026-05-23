package com.sbisec.helios.ap.common.brokerMaintenance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionResp;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignPositionDetailX001RequestDto;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignPositionDetailX001ResponseDto;
import com.sbisec.helios.ap.common.brokerMaintenance.service.IfaForeignPositionDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB07-06
 * 画面名：建玉詳細(米株)
 * @author 松田
 *
 * 2023/11/24 新規作成
 */
@Component(value = "cmpIfaForeignPositionDetailService")
public class IfaForeignPositionDetailServiceImpL implements IfaForeignPositionDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignPositionDetailServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。 */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 該当する情報は存在しません。 */
    private static final String ERRORS_CMN_INFORMATION_NOTFOUND = "errors.cmn.information.notfound";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    /** 一括表示 */
    private static final String BATCH_DISP = "0";
    
    /** 個別表示 */
    private static final String INDIVIDUAL_DISP = "1";
    
    /** 日付最大値時の設定値 */
    private static final String UNLIMITED_DATE_FORMAT = "----/--/--";
    
    /** 日付最大値 */
    private static final String UNLIMITED_DATE = "9999/12/31";
    
    /**
     * 共通関数Function001クラス
     */
    @Autowired
    Fct001 fct001;
    
    /**
     * Athena API呼び出しクラス
     */
    @Autowired
    ForeignAccountService foreignAccountService;
    
    /**
     * アクションID：X001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignPositionDetailX001DtoRequest
     * Dto レスポンス：IfaForeignPositionDetailX001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignPositionDetailX001ResponseDto> initializeX001(
            IfaForeignPositionDetailX001RequestDto dtoReq) throws Exception {
        
        List<IfaForeignPositionDetailX001ResponseDto> dtoList = new ArrayList<IfaForeignPositionDetailX001ResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaForeignPositionDetailServiceImplL.initializeX001");

        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();

        // 利用者の口座に対する権限チェックを行う。
        if (callFCT001(butenCode, accountNumber)) {
            return IfaCommonUtil.createDataList(dtoList, ErrorLevel.FATAL, ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
        }
        // 一括表示/個別表示判定
        IfaForeignPositionDetailX001ResponseDto response = new IfaForeignPositionDetailX001ResponseDto();
        if (StringUtils.equals(INDIVIDUAL_DISP, dtoReq.getIndividualBatchJudge())) {
            // 個別表示処理
            response = setResponseIndividual(dtoReq, butenCode, accountNumber);
        } else if (StringUtils.equals(BATCH_DISP, dtoReq.getIndividualBatchJudge())) {
            // 一括表示処理
            response = setResponseBatch(dtoReq, butenCode, accountNumber);
        }
        // レスポンスがnullならエラーを返す
        if (response == null) {
            return IfaCommonUtil.createDataList(dtoList, ErrorLevel.FATAL, ERRORS_CMN_INFORMATION_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_NOTFOUND));
        }
        dtoList.add(response);
        
        return IfaCommonUtil.createDataList(dtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                null);
    }
    
    /**
     * レスポンス設定（一括）
     * @param dtoReq
     * @param butenCode
     * @param accountNumber
     * @return
     * @throws Exception
     */
    private IfaForeignPositionDetailX001ResponseDto setResponseBatch(IfaForeignPositionDetailX001RequestDto dtoReq, String butenCode, String accountNumber)
            throws Exception {
        
        IfaForeignPositionDetailX001ResponseDto response = new IfaForeignPositionDetailX001ResponseDto();
        // API002 建玉残高明細情報を取得
        GetMarginPositionSummaryResp api002Res = getApi002Response(dtoReq, butenCode, accountNumber);
        // 未取得の場合はエラー
        if (api002Res == null || api002Res.getPositionSummary() == null) {
            return null;
        }
        // 一括個別表示フラグ
        response.setBatchIndividualDisplayFlag(BATCH_DISP);
        // 銘柄コード（半角英数字）
        response.setBrandCode(api002Res.getPositionSummary().getSecurities().getSecuritiesCode());
        // 銘柄名（全角半角）
        response.setBrandName(api002Res.getPositionSummary().getSecurities().getSecuritiesName());
        // 市場略名
        response.setForeignMarket(api002Res.getPositionSummary().getMarket().getMarketShortName());
        // 売買区分（全角半角）
        response.setTradeKbn(api002Res.getPositionSummary().getBuySellCode());
        // 信用期日
        response.setMarginDueDate(api002Res.getPositionSummary().getMarginCloseLimitType());
        // 国内新規約定日
        response.setDomesticTradeDate(convertDate(api002Res.getPositionSummary().getTradeDate(), false));
        // 現地決済期日
        response.setLastTradeDate(convertDate(api002Res.getPositionSummary().getFrnCloseLimitDate(), true));
        // 預り区分（全角半角）
        response.setDepositType(api002Res.getPositionSummary().getSpecificAccountCode());
        // 建玉残数量
        response.setPositionRestQuantity(api002Res.getPositionSummary().getQuantity());
        // 新規建単価（外貨）
        response.setPreviousDayValue(api002Res.getPositionSummary().getFrnPositionPrice());
        // 新規建代金（外貨）（数値(小数)）
        response.setForeignNewPositionAmount(api002Res.getPositionSummary().getFrnPositionAmount());
        // 新規建手数料（税込）
        response.setForeignNewComm(
                StringUtil.isNullOrEmpty(checkNumberStr(api002Res.getPositionSummary().getFrnOpenCommission())) ? null
                        : sumValue(api002Res.getPositionSummary().getFrnOpenCommission(),
                                api002Res.getPositionSummary().getFrnOpenCommissionTax()));
        // 金利（外貨）
        response.setInterestForeign(checkNumberStr(api002Res.getPositionSummary().getFrnInterestRate()));
        // 貸株料（外貨）
        response.setStockLendingPriceForeign(checkNumberStr(api002Res.getPositionSummary().getFrnLendingFee()));
        // 管理料（外貨）
        response.setManagePriceForeign(
                StringUtil.isNullOrEmpty(checkNumberStr(api002Res.getPositionSummary().getFrnManagementFee())) ? null
                        : sumValue(api002Res.getPositionSummary().getFrnManagementFee(),
                                api002Res.getPositionSummary().getFrnManagementFeeTax()));
        // 書換料（外貨）
        response.setTransferPriceForeign(
                StringUtil.isNullOrEmpty(checkNumberStr(api002Res.getPositionSummary().getFrnTransferFee())) ? null
                        : sumValue(api002Res.getPositionSummary().getFrnTransferFee(),
                                api002Res.getPositionSummary().getFrnTransferFeeTax()));
        // 諸経費合計額（外貨）
        response.setCostForeignLink(checkNumberStr(api002Res.getPositionSummary().getFrnTotalExpenses()));
        
        return response;
    }
    
    /**
     * レスポンス設定（個別）
     * @param dtoReq
     * @param butenCode
     * @param accountNumber
     * @return
     * @throws Exception
     */
    private IfaForeignPositionDetailX001ResponseDto setResponseIndividual(IfaForeignPositionDetailX001RequestDto dtoReq, String butenCode, String accountNumber)
            throws Exception {
        
        IfaForeignPositionDetailX001ResponseDto response = new IfaForeignPositionDetailX001ResponseDto();
        
        // API001 建玉残高明細情報を取得
        GetMarginPositionResp api001Res = getApi001Response(dtoReq, butenCode, accountNumber);
        
        // 未取得の場合はエラー
        if (api001Res == null || api001Res.getSecurities() == null) {
            return null;
        }
        // 一括個別表示フラグ
        response.setBatchIndividualDisplayFlag(INDIVIDUAL_DISP);
        // 銘柄コード（半角英数字）
        response.setBrandCode(api001Res.getSecurities().getSecuritiesCode());
        // 銘柄名（全角半角）
        response.setBrandName(api001Res.getSecurities().getSecuritiesName());
        // 銘柄略名
        response.setForeignMarket(api001Res.getMarket().getMarketShortName());
        // 売買区分（全角半角）
        response.setTradeKbn(api001Res.getBuySellCode());
        // 信用期日
        response.setMarginDueDate(api001Res.getMarginCloseLimitType());
        // 国内新規約定日
        response.setDomesticTradeDate(convertDate(api001Res.getTradeDate(), false));
        // 現地決済期日
        response.setLastTradeDate(convertDate(api001Res.getFrnCloseLimitDate(), true));
        // 預り区分（全角半角）
        response.setDepositType(api001Res.getSpecificAccountCode());
        // 建玉残数量
        response.setPositionRestQuantity(api001Res.getQuantity());
        // 新規建単価（外貨）
        response.setPreviousDayValue(api001Res.getFrnPositionPrice());
        // 新規建代金（外貨）（数値(小数)）
        response.setForeignNewPositionAmount(api001Res.getFrnPositionAmount());
        // 新規建手数料（税込）
        response.setForeignNewComm(StringUtil.isNullOrEmpty(checkNumberStr(api001Res.getFrnOpenCommission())) ? null
                : sumValue(api001Res.getFrnOpenCommission(), api001Res.getFrnOpenCommissionTax()));
        // 金利（外貨）
        response.setInterestForeign(checkNumberStr(api001Res.getFrnInterestRate()));
        // 貸株料（外貨）
        response.setStockLendingPriceForeign(checkNumberStr(api001Res.getFrnLendingFee()));
        // 管理料（外貨）
        response.setManagePriceForeign(
                StringUtil.isNullOrEmpty(checkNumberStr(api001Res.getFrnManagementFee())) ? null
                        : sumValue(api001Res.getFrnManagementFee(),
                                api001Res.getFrnManagementFeeTax()));
        // 書換料（外貨）
        response.setTransferPriceForeign(
                StringUtil.isNullOrEmpty(checkNumberStr(api001Res.getFrnTransferFee())) ? null
                        : sumValue(api001Res.getFrnTransferFee(),
                                api001Res.getFrnTransferFeeTax()));
        // 諸経費合計額（外貨）
        response.setCostForeignLink(checkNumberStr(api001Res.getFrnTotalExpenses()));
        
        return response;
    }
    
    /**
     * 合計値を算出後、文字列変換する処理
     * 
     * @param targetValue1
     * @param targetValue2
     * @return
     */
    private String sumValue(String targetValue1, String targetValue2) {
        
        BigDecimal value1 = BigDecimal.ZERO;
        BigDecimal value2 = BigDecimal.ZERO;
        
        value1 = StringUtil.isNullOrEmpty(checkNumberStr(targetValue1)) ? BigDecimal.ZERO
                : StringUtil.parseBigDecimal(targetValue1);
        value2 = StringUtil.isNullOrEmpty(checkNumberStr(targetValue2)) ? BigDecimal.ZERO
                : StringUtil.parseBigDecimal(targetValue2);
        
        return value1.add(value2).toString();
    }
    
    /**
     * API001情報取得処理
     * @param dtoReq
     * @param butenCode
     * @param accountNumber
     * @return
     * @throws Exception
     */
    private GetMarginPositionResp getApi001Response(IfaForeignPositionDetailX001RequestDto dtoReq, String butenCode, String accountNumber) throws Exception {
        
        return foreignAccountService.getMarginPosition(dtoReq.getCountryCode(), dtoReq.getBrandCode(),
                dtoReq.getOpenTradeKbn(), dtoReq.getPaymentDeadline(), dtoReq.getDepositType(),
                dtoReq.getBusinessDaysAfterOrder(), dtoReq.getLocalTradeDate(), dtoReq.getPreviousDayValue(),
                dtoReq.getJpyAmountNewPositionPrice(), butenCode, accountNumber);
    }
    
    /**
     * API002情報取得処理
     * @param dtoReq
     * @param butenCode
     * @param accountNumber
     * @return
     * @throws Exception
     */
    private GetMarginPositionSummaryResp getApi002Response(IfaForeignPositionDetailX001RequestDto dtoReq, String butenCode, String accountNumber)
            throws Exception {
        
        return foreignAccountService.getMarginPositionSummaryForPositionDetails(butenCode,
                accountNumber,
                dtoReq.getCountryCode(), dtoReq.getBrandCode(), dtoReq.getOpenTradeKbn(), dtoReq.getPaymentDeadline(),
                dtoReq.getDepositType(), dtoReq.getBusinessDaysAfterOrder(), dtoReq.getLocalTradeDate());
    }
    
    /**
     * 日付変換
     * 
     * @param targetDate
     * @param checkUnlimited 最大値の場合指定の日付を変換するか
     * @return
     */
    private String convertDate(String targetDate, boolean checkUnlimited) {
        
        String convertSlashDate = DateFormatUtil.dateFormatToSlash(targetDate);
        
        if (checkUnlimited && StringUtils.equals(convertSlashDate, UNLIMITED_DATE)) {
            convertSlashDate = UNLIMITED_DATE_FORMAT;
        }
        
        return convertSlashDate;
    }
    
    /**
     * 数値チェック
     * @param targetStr
     * @return　数値：入力値　数値以外：null
     */
    private String checkNumberStr(String targetStr) {
        
        String result = null;
        
        try {
            new BigDecimal(targetStr);
            result = targetStr;
        } catch (NumberFormatException e) {
            result = null;
        }
        
        return result;
    }
    
    /**
     * FCT001チェック
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return 権限あり:false, 権限なし:true 
     */
    private boolean callFCT001(String butenCode, String accountNumber) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            return StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId());
        }
        return true;
    }
    
}

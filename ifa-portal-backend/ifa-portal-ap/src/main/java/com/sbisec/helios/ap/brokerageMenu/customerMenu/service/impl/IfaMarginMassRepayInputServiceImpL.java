package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct020;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA010ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputDtoPositionDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginMassRepayInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.nri.ifa.NriApiService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OoutVec;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OutData;

/**
 * 画面ID：SUB0202_0212-05_1
 * 画面名：信用一括返済入力
 * 2024/04/15 新規作成
 *
 * @author 池亀　緑
 */
@Component(value = "cmpIfaMarginMassRepayInputService")
public class IfaMarginMassRepayInputServiceImpL implements IfaMarginMassRepayInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginMassRepayInputServiceImpL.class);
    
    /** 権限チェックエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOTEXISTS = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 媒介不可エラー */
    private static final String ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 取引対象無しエラー */
    private static final String ERRORS_DMS_ORDERABLEPOSITION_NOTHING = "errors.dms.orderablePosition.nothing";
    
    /** 区分値:対象取引（メッセージ表示用）: 区分=国内信用取引(3)　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE = "3";
    
    /** 区分値:対象取引（メッセージ表示用）: 表示パターン:1　*/
    private static final String DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE = "1";
    
    /** 区分値：証券金銭種別.国内株式 */
    private static final String DOMESTIC_STOCK = "01";
    
    /** 新規建売買区分="0":（買建） */
    private static final String OPENTRADEKBN_BUY = "0";
    
    /** 区分.対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 新規市場:全市場 */
    private static final String NEW_MARKET_ALL = "ALL";
    
    /** 区分.取引種別（国内株式）: 信用返済買(5) */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY = "5";
    
    /** 区分.取引種別（国内株式）: 信用返済売(6) */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL = "6";
    
    /** 区分値：返済順序.評価益順 */
    private static final String CODE_VAL_REPAY_ORDER_EVAL_PROFIT_DESC = "61";
    
    /** 区分値：返済順序.評価損順 */
    private static final String CODE_VAL_REPAY_ORDER_EVAL_LOSS_DESC = "51";
    
    /** 区分値：返済順序.建日新しい順 */
    private static final String CODE_VAL_REPAY_ORDER_OPEN_DATE_ASC = "26";
    
    /** 区分値：返済順序.建日古い順 */
    private static final String CODE_VAL_REPAY_ORDER_OPEN_DATE_DESC = "16";
    
    /** 区分値：返済方法.個別指定 */
    private static final String REPAY_METHOD_INDIVIDUAL = "1";
    
    /** リクエストタイプ:"31":新規単価の安い順＋新規約定日の古い順 */
    private static final String REQUEST_TYPE_NEW_PRICE_ASC = "31";
    
    /** リクエストタイプ:"41":新規単価の高い順＋新規約定日の古い順 */
    private static final String REQUEST_TYPE_NEW_PRICE_DESC = "41";
    
    /** リクエストタイプ:"16":建日古い順 */
    private static final String REQUEST_TYPE_OPEN_DATE_ASC = "16";
    
    /** リクエストタイプ:"26":建日新しい順 */
    private static final String REQUEST_TYPE_OPEN_DATE_DESC = "26";
    
    /** 建市場:'PTS'：PTS */
    private static final String MARKET_PTS = "7";
    
    /** 建市場:'TKY'：東証 */
    private static final String MARKET_TKY = "0";
    
    /** 注文株数:初期値 */
    private static final String QUANTIT_DEFAULT = "0";
    
    /** 取得単価の除数 */
    private static final BigDecimal OPEN_PRICE_DIVISOR = new BigDecimal("100");
    
    /** 保証金率の小数部桁数 */
    private static final int SEC_RATE_PRECISION = 2;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct020 fct020;
    
    @Autowired
    private Fct027 fct027;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private NriApiService nriApiService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginMassRepayInputA001RequestDto
     * Dto レスポンス：IfaMarginMassRepayInputA001ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginMassRepayInputA001ResponseDto> initializeA001(IfaMarginMassRepayInputA001RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginMassRepayInputServiceImpL.initializeA001");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inpFct001Dto = new InputFct001Dto();
        inpFct001Dto.setButenCode(cc.getButenCode());
        inpFct001Dto.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto outFct001Dto = fct001.doCheck(inpFct001Dto);
        // 対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outFct001Dto.getTargetCustomerRefAuthFlag())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        // 取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す
        if (Fct001.TRADE_SUSPEND_FLAG_1.equals(outFct001Dto.getTradeSuspendFlag())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
        }
        
        //　②取引コース媒介可否チェックを行う。
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(cc.getButenCode());
        inpFct003Dto.setAccountNumber(cc.getAccountNumber());
        inpFct003Dto.setProductCd(DOMESTIC_STOCK);
        if (OPENTRADEKBN_BUY.equals(dtoReq.getNewCreditOrderType())) {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL);
        } else {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY);
        }
        if (isFct003Error(fct003.doCheck(inpFct003Dto))) {
            return IfaCommonUtil
                    .createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                            CODE_VAL_MSG_DISPLAY_TARGET_TRADE,
                                            DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE) }));
        }
        
        // ④建玉残高を取得する。
        // リクエスト.新規建売買区分='1'（売建）　の場合 '41':新規単価の高い順＋新規約定日の古い順
        String requestType = REQUEST_TYPE_NEW_PRICE_DESC;
        if (OPENTRADEKBN_BUY.equals(dtoReq.getNewCreditOrderType())) {
            // リクエスト.新規建売買区分='0'（買建）　の場合 '31':新規単価の安い順＋新規約定日の古い順
            requestType = REQUEST_TYPE_NEW_PRICE_ASC;
        }
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // API001.(建玉残高明細)を呼び出す
        List<QueryMarginContract1OutData> outputList = nriApiService.queryMarginContract1List(cc.getButenCode(),
                cc.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getNewCreditOrderType(), NEW_MARKET_ALL,
                dtoReq.getPaymentDeadline(), requestType);
        for (QueryMarginContract1OutData outdata : outputList) {
            apiErrorUtil.checkApiResponse(outdata.getShubetu(), outdata.getCode(), outdata.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), null);
        }
        // 返済対象の建玉が存在しなければエラーを返す。
        if (outputList == null || outputList.get(0) == null || outputList.get(0).getQueryMarginContract1Data() == null
                || outputList.get(0).getQueryMarginContract1Data().size() == 0) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTHING,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTHING));
        }
        
        // ④銘柄名、売買単位を取得する。(FCT027)
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(dtoReq.getBrandCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        
        QueryMarginContract1OutData output = outputList.get(0);
        IfaMarginMassRepayInputA001ResponseDto resDto = new IfaMarginMassRepayInputA001ResponseDto();
        // 銘柄コード
        resDto.setBrandCode(dtoReq.getBrandCode());
        // 銘柄名
        resDto.setBrandName(outputFct027Dto.getBrandName());
        // 売買単位
        resDto.setUnit(outputFct027Dto.getUnit());
        // 新規建売買区分
        resDto.setNewCreditOrderType(dtoReq.getNewCreditOrderType());
        // 弁済期限
        resDto.setPaymentDeadline(dtoReq.getPaymentDeadline());
        // 建玉件数
        resDto.setPositionCount(output.getHitNumber());
        // 東証明細.建玉金額合計
        resDto.setTokyoSecurityDetailTotalPrice(output.getContractTotal());
        // 東証明細.評価額合計（前日）
        resDto.setTokyoSecurityDetailValuationTotalPreviousDay(output.getValueTotal());
        // 東証明細.諸経費合計
        resDto.setTokyoSecurityDetailCostTotalYen(output.getCostTotal());
        // 東証明細.評価損益合計（前日）
        resDto.setTokyoSecurityDetailDomesticPositionValuationTotalPreviousDay(output.getUnrealizedPlTotal());
        // PTS明細.建玉金額合計
        resDto.setPtsDetailTotalPrice(output.getContractTotalPts());
        // PTS明細.評価額合計（前日）
        resDto.setPtsDetailValuationTotalPreviousDay(output.getValueTotalPts());
        // PTS明細.諸経費合計
        resDto.setPtsDetailCostTotalYen(output.getCostTotalPts());
        // PTS明細.評価損益合計（前日）
        resDto.setPtsDetailDomesticPositionValuationTotalPreviousDay(output.getUnrealizedPlTotalPts());
        
        // 建玉残高明細の全件を結合したリストを作成する
        final List<QueryMarginContract1OoutVec> api001PositionList = outputList.stream()
                .flatMap(api001Res -> api001Res.getQueryMarginContract1Data().stream()).collect(Collectors.toList());
        
        List<QueryMarginContract1OoutVec> tkyVecList = new ArrayList<>();
        List<QueryMarginContract1OoutVec> ptsVecList = new ArrayList<>();
        int orderAbleStockQuantity = 0;
        for (QueryMarginContract1OoutVec vec : api001PositionList) {
            if (MARKET_TKY.equals(vec.getBargainMarket())) {
                tkyVecList.add(vec);
            }
            if (MARKET_PTS.equals(vec.getBargainMarket())) {
                ptsVecList.add(vec);
            }
            // 注文可能株数 : Σ（建玉明細.残高数量-建玉明細.返済注文済未出来数量）　※全ての建玉明細を対象に集計
            orderAbleStockQuantity += Long.valueOf(vec.getContPosition()) - Long.valueOf(vec.getUnactualQuantity());
        }
        // 建玉件数（東証） 建玉明細.建市場='TKY'：東証　の件数
        resDto.setPositionCountTokyoSecurity(String.valueOf(tkyVecList.size()));
        // 建玉件数（PTS） 建玉明細.建市場='PTS'：PTS　の件数
        resDto.setPositionCountPts(String.valueOf(ptsVecList.size()));
        // 注文可能株数
        resDto.setOrderAbleStockQuantity(String.valueOf(orderAbleStockQuantity));
        
        List<IfaMarginMassRepayInputDtoPositionDetail> tkyDetailList = getDetails(tkyVecList, dtoReq.getBrandCode(),
                dtoReq.getNewCreditOrderType(), output.getNightBatchEndFlg());
        List<IfaMarginMassRepayInputDtoPositionDetail> ptsDetailList = getDetails(ptsVecList, dtoReq.getBrandCode(),
                dtoReq.getNewCreditOrderType(), output.getNightBatchEndFlg());
        
        // 東証明細.評価損益合計（リアル）
        resDto.setTokyoSecurityDetailDomesticPositionValuationTotalReal(getTotalProfitAndLossReal(tkyDetailList));
        // 東証明細.評価額合計（リアル）
        resDto.setTokyoSecurityDetailValuationTotalReal(getTotalValuationReal(tkyDetailList));
        // PTS明細.評価損益合計（リアル）
        resDto.setPtsDetailDomesticPositionValuationTotalReal(getTotalProfitAndLossReal(ptsDetailList));
        // PTS明細.評価額合計（リアル）
        resDto.setPtsDetailValuationTotalReal(getTotalValuationReal(ptsDetailList));
        
        List<IfaMarginMassRepayInputDtoPositionDetail> totalDetailList = new ArrayList<>();
        totalDetailList.addAll(tkyDetailList);
        totalDetailList.addAll(ptsDetailList);
        resDto.setPositionDetailList(totalDetailList);
        List<IfaMarginMassRepayInputA001ResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(resDto);
        return apiErrorUtil.createDataList(resDtoList, null);
    }
    
    /**
    * アクションID：A003
    * アクション名：返済順序
    
    * Dto リクエスト：IfaMarginMassRepayInputA003RequestDto
    * Dto レスポンス：IfaMarginMassRepayInputA003ResponseDto
    *
    * @param dtoReq リクエストDto
    * @return レスポンスDto
    * @exception Exception 例外が発生した場合
    */
    @Override
    public DataList<IfaMarginMassRepayInputA003ResponseDto> repaymentOrderA003(
            IfaMarginMassRepayInputA003RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginMassRepayInputServiceImpL.repaymentOrderA003");
        }
        // 顧客共通情報の取得
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ④建玉残高を取得する。
        String requestType = StringUtils.EMPTY;
        if (CODE_VAL_REPAY_ORDER_EVAL_PROFIT_DESC.equals(dtoReq.getRepaymentOrder())) {
            // ■リクエスト.返済順序=評価益順("61")　の場合
            if (OPENTRADEKBN_BUY.equals(dtoReq.getNewCreditOrderType())) {
                // ■リクエスト.新規建売買区分='0'（買建）　の場合 '31':新規単価の安い順＋新規約定日の古い順
                requestType = REQUEST_TYPE_NEW_PRICE_ASC;
            } else {
                // ■リクエスト.新規建売買区分='1'（売建）　の場合 '41':新規単価の高い順＋新規約定日の古い順
                requestType = REQUEST_TYPE_NEW_PRICE_DESC;
            }
        } else if (CODE_VAL_REPAY_ORDER_EVAL_LOSS_DESC.equals(dtoReq.getRepaymentOrder())) {
            // ■リクエスト.返済順序=評価損順("51")　の場合
            if (OPENTRADEKBN_BUY.equals(dtoReq.getNewCreditOrderType())) {
                // ■リクエスト.新規建売買区分='0'（買建）　の場合 '31':新規単価の安い順＋新規約定日の古い順
                requestType = REQUEST_TYPE_NEW_PRICE_DESC;
            } else {
                // ■リクエスト.新規建売買区分='1'（売建）　の場合 '41':新規単価の高い順＋新規約定日の古い順
                requestType = REQUEST_TYPE_NEW_PRICE_ASC;
            }
        } else if (CODE_VAL_REPAY_ORDER_OPEN_DATE_DESC.equals(dtoReq.getRepaymentOrder())) {
            // ■リクエスト.返済順序=建日古い順("16")　の場合 '16':建日古い順
            requestType = REQUEST_TYPE_OPEN_DATE_ASC;
        } else if (CODE_VAL_REPAY_ORDER_OPEN_DATE_ASC.equals(dtoReq.getRepaymentOrder())) {
            // ■リクエスト.返済順序=建日新しい順("26")　の場合 '26':建日新しい順
            requestType = REQUEST_TYPE_OPEN_DATE_DESC;
        }
        // API001.(建玉残高明細)を呼び出す
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        List<QueryMarginContract1OutData> outputList = nriApiService.queryMarginContract1List(cc.getButenCode(),
                cc.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getNewCreditOrderType(), NEW_MARKET_ALL,
                dtoReq.getPaymentDeadline(), requestType);
        for (QueryMarginContract1OutData outdata : outputList) {
            apiErrorUtil.checkApiResponse(outdata.getShubetu(), outdata.getCode(), outdata.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), null);
        }
        // 返済対象の建玉が存在しなければエラーを返す。
        if (outputList == null || outputList.get(0) == null || outputList.get(0).getQueryMarginContract1Data() == null
                || outputList.get(0).getQueryMarginContract1Data().size() == 0) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTHING,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTHING));
        }
        
        QueryMarginContract1OutData output = outputList.get(0);
        IfaMarginMassRepayInputA003ResponseDto resDto = new IfaMarginMassRepayInputA003ResponseDto();
        // 建玉件数
        resDto.setPositionCount(output.getHitNumber());
        // 東証明細.建玉金額合計
        resDto.setTokyoSecurityDetailTotalPrice(output.getContractTotal());
        // 東証明細.評価額合計（前日）
        resDto.setTokyoSecurityDetailValuationTotalPreviousDay(output.getValueTotal());
        // 東証明細.諸経費合計
        resDto.setTokyoSecurityDetailCostTotalYen(output.getCostTotal());
        // 東証明細.評価損益合計（前日）
        resDto.setTokyoSecurityDetailDomesticPositionValuationTotalPreviousDay(output.getUnrealizedPlTotal());
        // PTS明細.建玉金額合計
        resDto.setPtsDetailTotalPrice(output.getContractTotalPts());
        // PTS明細.評価額合計（前日）
        resDto.setPtsDetailValuationTotalPreviousDay(output.getValueTotalPts());
        // PTS明細.諸経費合計
        resDto.setPtsDetailCostTotalYen(output.getCostTotalPts());
        // PTS明細.評価損益合計（前日）
        resDto.setPtsDetailDomesticPositionValuationTotalPreviousDay(output.getUnrealizedPlTotalPts());
        
        // 建玉残高明細の全件を結合したリストを作成する
        final List<QueryMarginContract1OoutVec> api001PositionList = outputList.stream()
                .flatMap(api001Res -> api001Res.getQueryMarginContract1Data().stream()).collect(Collectors.toList());
        
        List<QueryMarginContract1OoutVec> tkyVecList = new ArrayList<>();
        List<QueryMarginContract1OoutVec> ptsVecList = new ArrayList<>();
        int orderAbleStockQuantity = 0;
        for (QueryMarginContract1OoutVec vec : api001PositionList) {
            if (MARKET_TKY.equals(vec.getBargainMarket())) {
                tkyVecList.add(vec);
            }
            if (MARKET_PTS.equals(vec.getBargainMarket())) {
                ptsVecList.add(vec);
            }
            // 注文可能株数 : Σ（建玉明細.残高数量-建玉明細.返済注文済未出来数量）　※全ての建玉明細を対象に集計
            orderAbleStockQuantity += Long.valueOf(vec.getContPosition()) - Long.valueOf(vec.getUnactualQuantity());
        }
        // 建玉件数（東証） 建玉明細.建市場='TKY'：東証　の件数
        resDto.setPositionCountTokyoSecurity(String.valueOf(tkyVecList.size()));
        // 建玉件数（PTS） 建玉明細.建市場='PTS'：PTS　の件数
        resDto.setPositionCountPts(String.valueOf(ptsVecList.size()));
        // 注文可能株数
        resDto.setOrderAbleStockQuantity(String.valueOf(orderAbleStockQuantity));
        
        List<IfaMarginMassRepayInputDtoPositionDetail> tkyDetailList = getDetails(tkyVecList, dtoReq.getBrandCode(),
                dtoReq.getNewCreditOrderType(), output.getNightBatchEndFlg());
        List<IfaMarginMassRepayInputDtoPositionDetail> ptsDetailList = getDetails(ptsVecList, dtoReq.getBrandCode(),
                dtoReq.getNewCreditOrderType(), output.getNightBatchEndFlg());
        
        // 東証明細.評価損益合計（リアル）
        resDto.setTokyoSecurityDetailDomesticPositionValuationTotalReal(getTotalProfitAndLossReal(tkyDetailList));
        // 東証明細.評価額合計（リアル）
        resDto.setTokyoSecurityDetailValuationTotalReal(getTotalValuationReal(tkyDetailList));
        // PTS明細.評価損益合計（リアル）
        resDto.setPtsDetailDomesticPositionValuationTotalReal(getTotalProfitAndLossReal(ptsDetailList));
        // PTS明細.評価額合計（リアル）
        resDto.setPtsDetailValuationTotalReal(getTotalValuationReal(ptsDetailList));
        
        List<IfaMarginMassRepayInputDtoPositionDetail> totalDetailList = new ArrayList<>();
        totalDetailList.addAll(tkyDetailList);
        totalDetailList.addAll(ptsDetailList);
        resDto.setPositionDetailList(totalDetailList);
        List<IfaMarginMassRepayInputA003ResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(resDto);
        return apiErrorUtil.createDataList(resDtoList, null);
    }
    
    /**
     * アクションID：A010
     * アクション名：初期化（戻り）
     * Dto リクエスト：IfaMarginMassRepayInputA010RequestDto
     * Dto レスポンス：IfaMarginMassRepayInputA010ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    @Override
    public DataList<IfaMarginMassRepayInputA010ResponseDto> initializeReturnA010(
            IfaMarginMassRepayInputA010RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginMassRepayInputServiceImpL.initializeReturnA010");
        }
        
        // 顧客共通情報の取得
        final CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ①利用者の口座に対する権限チェックを行う。
        InputFct001Dto inpFct001Dto = new InputFct001Dto();
        inpFct001Dto.setButenCode(cc.getButenCode());
        inpFct001Dto.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto outFct001Dto = fct001.doCheck(inpFct001Dto);
        // 対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0.equals(outFct001Dto.getTargetCustomerRefAuthFlag())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        // 取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す
        if (Fct001.TRADE_SUSPEND_FLAG_1.equals(outFct001Dto.getTradeSuspendFlag())) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
        }
        
        //　②取引コース媒介可否チェックを行う。
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(cc.getButenCode());
        inpFct003Dto.setAccountNumber(cc.getAccountNumber());
        inpFct003Dto.setProductCd(DOMESTIC_STOCK);
        if (OPENTRADEKBN_BUY.equals(dtoReq.getNewCreditOrderType())) {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_SELL);
        } else {
            inpFct003Dto.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_MARGIN_REPAY_BUY);
        }
        if (isFct003Error(fct003.doCheck(inpFct003Dto))) {
            return IfaCommonUtil
                    .createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                            IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                            CODE_VAL_MSG_DISPLAY_TARGET_TRADE,
                                            DISPLAY_VAL_MSG_DISPLAY_TARGET_TRADE) }));
        }
        
        // ④建玉残高を取得する。
        String requestType = StringUtils.EMPTY;
        if (CODE_VAL_REPAY_ORDER_EVAL_PROFIT_DESC.equals(dtoReq.getRepaymentOrder())) {
            // ■リクエスト.返済順序=評価益順("61")　の場合
            if (OPENTRADEKBN_BUY.equals(dtoReq.getNewCreditOrderType())) {
                // ■リクエスト.新規建売買区分='0'（買建）　の場合 '31':新規単価の安い順＋新規約定日の古い順
                requestType = REQUEST_TYPE_NEW_PRICE_ASC;
            } else {
                // ■リクエスト.新規建売買区分='1'（売建）　の場合 '41':新規単価の高い順＋新規約定日の古い順
                requestType = REQUEST_TYPE_NEW_PRICE_DESC;
            }
        } else if (CODE_VAL_REPAY_ORDER_EVAL_LOSS_DESC.equals(dtoReq.getRepaymentOrder())) {
            // ■リクエスト.返済順序=評価損順("51")　の場合
            if (OPENTRADEKBN_BUY.equals(dtoReq.getNewCreditOrderType())) {
                // ■リクエスト.新規建売買区分='0'（買建）　の場合 '31':新規単価の安い順＋新規約定日の古い順
                requestType = REQUEST_TYPE_NEW_PRICE_DESC;
            } else {
                // ■リクエスト.新規建売買区分='1'（売建）　の場合 '41':新規単価の高い順＋新規約定日の古い順
                requestType = REQUEST_TYPE_NEW_PRICE_ASC;
            }
        } else if (CODE_VAL_REPAY_ORDER_OPEN_DATE_DESC.equals(dtoReq.getRepaymentOrder())) {
            // ■リクエスト.返済順序=建日古い順("16")　の場合 '16':建日古い順
            requestType = REQUEST_TYPE_OPEN_DATE_ASC;
        } else if (CODE_VAL_REPAY_ORDER_OPEN_DATE_ASC.equals(dtoReq.getRepaymentOrder())) {
            // ■リクエスト.返済順序=建日新しい順("26")　の場合 '26':建日新しい順
            requestType = REQUEST_TYPE_OPEN_DATE_DESC;
        }
        // API001.(建玉残高明細)を呼び出す
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        List<QueryMarginContract1OutData> outputList = nriApiService.queryMarginContract1List(cc.getButenCode(),
                cc.getAccountNumber(), dtoReq.getBrandCode(), dtoReq.getNewCreditOrderType(), NEW_MARKET_ALL,
                dtoReq.getPaymentDeadline(), requestType);
        for (QueryMarginContract1OutData outdata : outputList) {
            apiErrorUtil.checkApiResponse(outdata.getShubetu(), outdata.getCode(), outdata.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(Collections.emptyList(), null);
        }
        // 返済対象の建玉が存在しなければエラーを返す。
        if (outputList == null || outputList.get(0) == null || outputList.get(0).getQueryMarginContract1Data() == null
                || outputList.get(0).getQueryMarginContract1Data().size() == 0) {
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_DMS_ORDERABLEPOSITION_NOTHING,
                    IfaCommonUtil.getMessage(ERRORS_DMS_ORDERABLEPOSITION_NOTHING));
        }
        
        // ④銘柄名、売買単位を取得する。(FCT027)
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(dtoReq.getBrandCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        
        QueryMarginContract1OutData output = outputList.get(0);
        IfaMarginMassRepayInputA010ResponseDto resDto = new IfaMarginMassRepayInputA010ResponseDto();
        // 銘柄コード
        resDto.setBrandCode(dtoReq.getBrandCode());
        // 銘柄名
        resDto.setBrandName(outputFct027Dto.getBrandName());
        // 売買単位
        resDto.setUnit(outputFct027Dto.getUnit());
        // 新規建売買区分
        resDto.setNewCreditOrderType(dtoReq.getNewCreditOrderType());
        // 弁済期限
        resDto.setPaymentDeadline(dtoReq.getPaymentDeadline());
        // 建玉件数
        resDto.setPositionCount(output.getHitNumber());
        // 東証明細.建玉金額合計
        resDto.setTokyoSecurityDetailTotalPrice(output.getContractTotal());
        // 東証明細.評価額合計（前日）
        resDto.setTokyoSecurityDetailValuationTotalPreviousDay(output.getValueTotal());
        // 東証明細.諸経費合計
        resDto.setTokyoSecurityDetailCostTotalYen(output.getCostTotal());
        // 東証明細.評価損益合計（前日）
        resDto.setTokyoSecurityDetailDomesticPositionValuationTotalPreviousDay(output.getUnrealizedPlTotal());
        // PTS明細.建玉金額合計
        resDto.setPtsDetailTotalPrice(output.getContractTotalPts());
        // PTS明細.評価額合計（前日）
        resDto.setPtsDetailValuationTotalPreviousDay(output.getValueTotalPts());
        // PTS明細.諸経費合計
        resDto.setPtsDetailCostTotalYen(output.getCostTotalPts());
        // PTS明細.評価損益合計（前日）
        resDto.setPtsDetailDomesticPositionValuationTotalPreviousDay(output.getUnrealizedPlTotalPts());
        // 返済方法
        resDto.setRepayMethod(dtoReq.getRepayMethod());
        // 返済順序
        resDto.setRepaymentOrder(dtoReq.getRepaymentOrder());
        // 合計数量
        resDto.setTotalQuantity(dtoReq.getTotalQuantity());
        
        // 建玉残高明細の全件を結合したリストを作成する
        final List<QueryMarginContract1OoutVec> api001PositionList = outputList.stream()
                .flatMap(api001Res -> api001Res.getQueryMarginContract1Data().stream()).collect(Collectors.toList());
        
        List<QueryMarginContract1OoutVec> tkyVecList = new ArrayList<>();
        List<QueryMarginContract1OoutVec> ptsVecList = new ArrayList<>();
        int orderAbleStockQuantity = 0;
        for (QueryMarginContract1OoutVec vec : api001PositionList) {
            if (MARKET_TKY.equals(vec.getBargainMarket())) {
                tkyVecList.add(vec);
            }
            if (MARKET_PTS.equals(vec.getBargainMarket())) {
                ptsVecList.add(vec);
            }
            // 注文可能株数 : Σ（建玉明細.残高数量-建玉明細.返済注文済未出来数量）　※全ての建玉明細を対象に集計
            orderAbleStockQuantity += Long.valueOf(vec.getContPosition()) - Long.valueOf(vec.getUnactualQuantity());
        }
        // 建玉件数（東証） 建玉明細.建市場='TKY'：東証　の件数
        resDto.setPositionCountTokyoSecurity(String.valueOf(tkyVecList.size()));
        // 建玉件数（PTS） 建玉明細.建市場='PTS'：PTS　の件数
        resDto.setPositionCountPts(String.valueOf(ptsVecList.size()));
        // 注文可能株数
        resDto.setOrderAbleStockQuantity(String.valueOf(orderAbleStockQuantity));
        
        List<IfaMarginMassRepayInputDtoPositionDetail> tkyDetailList = getDetails(tkyVecList, dtoReq.getBrandCode(),
                dtoReq.getNewCreditOrderType(), output.getNightBatchEndFlg());
        List<IfaMarginMassRepayInputDtoPositionDetail> ptsDetailList = getDetails(ptsVecList, dtoReq.getBrandCode(),
                dtoReq.getNewCreditOrderType(), output.getNightBatchEndFlg());
        
        // 東証明細.評価損益合計（リアル）
        resDto.setTokyoSecurityDetailDomesticPositionValuationTotalReal(getTotalProfitAndLossReal(tkyDetailList));
        // 東証明細.評価額合計（リアル）
        resDto.setTokyoSecurityDetailValuationTotalReal(getTotalValuationReal(tkyDetailList));
        // PTS明細.評価損益合計（リアル）
        resDto.setPtsDetailDomesticPositionValuationTotalReal(getTotalProfitAndLossReal(ptsDetailList));
        // PTS明細.評価額合計（リアル）
        resDto.setPtsDetailValuationTotalReal(getTotalValuationReal(ptsDetailList));
        
        List<IfaMarginMassRepayInputDtoPositionDetail> totalDetailList = new ArrayList<>();
        totalDetailList.addAll(tkyDetailList);
        totalDetailList.addAll(ptsDetailList);
        // 建玉明細.注文株数
        if (REPAY_METHOD_INDIVIDUAL.equals(dtoReq.getRepayMethod())) {
            updateQuantity(dtoReq.getPositionDetailList(), totalDetailList);
        }
        resDto.setPositionDetailList(totalDetailList);
        List<IfaMarginMassRepayInputA010ResponseDto> resDtoList = new ArrayList<>();
        resDtoList.add(resDto);
        return apiErrorUtil.createDataList(resDtoList, null);
    }
    
    /**
     * FCT003の結果から、媒介可否リスト.媒介可否に媒介可が存在するか判定する.
     *
     * @param outFct003Dto FCT003実行結果
     * @return 判定結果
     */
    private boolean isFct003Error(OutputFct003Dto outFct003Dto) {
        
        return outFct003Dto.getMediateProprietyList().stream()
                .map(mediateProprieties -> mediateProprieties.getMediatePropriety())
                .noneMatch(mediatePropriety -> MediateAbleTradeFlag.ARI.getId().equals(mediatePropriety));
    }
    
    /**
     * 返却用の建玉明細一覧を取得する。
     *
     * @param vecList APIの建玉明細一覧
     * @param brandCode 銘柄コード
     * @param newCreditOrderType 新規建売買区分
     * @param nightBatchEndFlg CT夜間バッチ終了フラグ
     * @return 建玉明細一覧
     */
    private List<IfaMarginMassRepayInputDtoPositionDetail> getDetails(List<QueryMarginContract1OoutVec> vecList,
            String brandCode, String newCreditOrderType, String nightBatchEndFlg) {
        
        List<IfaMarginMassRepayInputDtoPositionDetail> result = new ArrayList<>();
        for (QueryMarginContract1OoutVec vec : vecList) {
            IfaMarginMassRepayInputDtoPositionDetail detail = new IfaMarginMassRepayInputDtoPositionDetail();
            // ⑤現在値（リアル）を取得し、評価額（リアル）、評価損益（リアル）を計算する。
            // 建玉明細.現在値（リアル）
            final String currentPriceReal = getCurrentValueForEvaluation(brandCode, nightBatchEndFlg,
                    vec.getStRightId());
            detail.setCurrentPriceReal(currentPriceReal);
            // 建玉明細.評価額（リアル）
            detail.setValuationReal(getValuationReal(vec.getContPosition(), currentPriceReal));
            // 建玉明細.評価損益（リアル）
            detail.setProfitAndLossReal(getProfitAndLossReal(newCreditOrderType, vec, currentPriceReal));
            // 建玉明細.市場
            detail.setMarket(vec.getBargainMarket());
            // 建玉明細.新規建日
            detail.setConstructionDate(vec.getOpenTradeDate());
            // 建玉明細.返済期限
            detail.setLastTradeDate(vec.getLastTradeDate());
            // 建玉明細.期日短縮区分
            detail.setDueDateShortenClassification(vec.getChangeLastTradeKbn());
            // 建玉明細.親株新規約定日
            detail.setParentStockTradeDate(vec.getOrgNewTradeDate());
            // 建玉明細.特定・一般
            detail.setAccountType(vec.getTokuteiContractId());
            // 建玉明細.担保
            detail.setSecurity(StringUtils.left(vec.getBargainNeedSecRate(),
                    vec.getBargainNeedSecRate().length() - SEC_RATE_PRECISION));
            // 建玉明細.担保区分
            detail.setSecurityClassification(vec.getRegulateKbn());
            // 建玉明細.建株数
            detail.setContPositionTotal(vec.getContPosition());
            // 建玉明細.注文中
            detail.setUnactualQuantity(vec.getUnactualQuantity());
            // 建玉明細.新規単価
            detail.setNewPrice(new BigDecimal(vec.getOpenPrice()).divide(OPEN_PRICE_DIVISOR).toPlainString());
            // 建玉明細.評価単価（前日）
            detail.setDayBeforeValuationPrice(vec.getMarketPrice());
            // 建玉明細.建玉金額
            detail.setOpenInterestAmount(vec.getOpenAmount());
            // 建玉明細.評価額（前日）
            detail.setValuationPreviousDay(vec.getMarketContractValue());
            // 建玉明細.諸経費
            detail.setCost(vec.getCost());
            // 建玉明細.評価損益（前日）
            detail.setProfitAndLossPrevDay(vec.getUnrealizedPl());
            // 建玉明細.現金拘束金
            detail.setCashBond(vec.getCashHold());
            // 建玉明細.新規建玉指定番号
            detail.setNewOpenInterestNumber(vec.getOpenContractNo());
            result.add(detail);
        }
        return result;
    }
    
    /**
     * 評価損益合計（リアル）を取得する.
     *
     * @param details 建玉明細
     * @return 評価損益合計（リアル）
     */
    private String getTotalProfitAndLossReal(List<IfaMarginMassRepayInputDtoPositionDetail> details) {
        
        BigDecimal result = details.stream().map(detail -> detail.getProfitAndLossReal())
                .filter(StringUtils::isNotEmpty).map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return result.toString();
    }
    
    /**
     * 評価額（リアル）合計を取得する.
     *
     * @param details 建玉明細
     * @return 評価額（リアル）合計
     */
    private String getTotalValuationReal(List<IfaMarginMassRepayInputDtoPositionDetail> details) {
        
        BigDecimal result = details.stream().map(detail -> detail.getValuationReal()).filter(StringUtils::isNotEmpty)
                .map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return result.toString();
    }
    
    /**
     * 建玉明細.評価額（リアル）を取得する.
     *
     * @param contPosition 残高数量
     * @param currentValueForEvaluation 評価用現在値
     * @return 評価額（リアル）
     */
    private String getValuationReal(String contPosition, String currentValueForEvaluation) {
        
        // 建玉明細.評価額（リアル）
        // 計算項目が無効の場合は、値をセットしない。
        if (currentValueForEvaluation == null) {
            return null;
        }
        // 建玉明細.評価額（リアル） : 評価用現在値 ｘ 建玉明細.残高数量
        return new BigDecimal(currentValueForEvaluation).multiply(new BigDecimal(contPosition)).toString();
    }
    
    /**
     * 建玉明細.評価損益（リアル）を取得する.
     *
     * @param brandCode 銘柄コード
     * @param newCreditOrderType 新規建売買区分
     * @param vec 建玉明細
     * @param currentValueForEvaluation 評価用現在値
     * @return 評価損益（リアル）
     */
    private String getProfitAndLossReal(String newCreditOrderType, QueryMarginContract1OoutVec vec,
            String currentValueForEvaluation) {
        
        // 建玉明細.評価損益（リアル）
        // 計算項目が無効の場合は、値をセットしない。
        if (currentValueForEvaluation == null) {
            return null;
        }
        // 新規建売買区分='0'：買建　の場合
        // 評価用現在値 × 建玉明細.残高数量 - 建玉明細.建玉金額 - 建玉明細.諸経費
        if (OPENTRADEKBN_BUY.equals(newCreditOrderType)) {
            return new BigDecimal(currentValueForEvaluation).multiply(new BigDecimal(vec.getContPosition()))
                    .subtract(new BigDecimal(vec.getOpenAmount())).subtract(new BigDecimal(vec.getCost())).toString();
        }
        // 新規建売買区分='1'：売建　の場合　の場合
        // 建玉明細.建玉金額 - 評価用現在値 × 建玉明細.残高数量 - 建玉明細.諸経費　
        return new BigDecimal(vec.getOpenAmount())
                .subtract(new BigDecimal(currentValueForEvaluation).multiply(new BigDecimal(vec.getContPosition())))
                .subtract(new BigDecimal(vec.getCost())).toString();
    }
    
    /**
     * 評価用現在値を取得する.
     *
     * @param brandCode 銘柄コード
     * @param ctNightBatchFinishFlag CT夜間バッチ終了フラグ
     * @param rightType 権利区分
     * @return 評価用現在値
     */
    private String getCurrentValueForEvaluation(String brandCode, String ctNightBatchFinishFlag, String rightType) {
        
        InputFct020Dto fct020Req = new InputFct020Dto();
        fct020Req.setBrandCode(brandCode);
        fct020Req.setCtNightBatchEndFlag(ctNightBatchFinishFlag);
        fct020Req.setRightType(rightType);
        return fct020.getData(fct020Req).getCurrentValueForEvaluation();
    }
    
    /**
     * 注文株数を設定する.
     * <p/>
     *　以下の全てに合致するデータが対象。
     * ・API001.建玉明細.建市場 =　リクエスト.返済建玉明細.建市場
     * ・API001.建玉明細.新規約定日　　　 =　リクエスト.返済建玉明細.新規約定日
     * ・API001.建玉明細.親株新規約定日　=　リクエスト.返済建玉明細.親株新規約定日
     * ・API001.建玉明細.取得単価/100　　　　　=　リクエスト.返済建玉明細.新規単価
     *
     * @param requestList リクエストの建玉明細
     * @param responseList レスポンスの建玉明細
     */
    private void updateQuantity(List<IfaMarginMassRepayInputDtoPositionDetail> requestList,
            List<IfaMarginMassRepayInputDtoPositionDetail> responseList) {
        
        for (IfaMarginMassRepayInputDtoPositionDetail response : responseList) {
            response.setQuantity(QUANTIT_DEFAULT);
            for (IfaMarginMassRepayInputDtoPositionDetail request : requestList) {
                if (request.getMarket().equals(response.getMarket())
                        && request.getConstructionDate()
                                .equals(response.getConstructionDate())
                        && request.getParentStockTradeDate()
                                .equals(response.getParentStockTradeDate())
                        && new BigDecimal(request.getNewPrice())
                                .compareTo(new BigDecimal(response.getNewPrice())) == 0) {
                    response.setQuantity(request.getQuantity());
                    break;
                }
            }
        }
    }
}

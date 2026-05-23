package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct017;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto.InquiryMutualFundBrand;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMutualFundAccumulateSettingBrandListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBrandListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBrandListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListNextOrderPlan;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeTargetDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingBrandListService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaMutualFundAccumulateSettingUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums.AccumulateCourse;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingReq;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataListReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataListRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryRes;
import com.sbisec.helios.ap.safe.service.SafeAccountService;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.account.dto.ReserveBuySettingGetApiIn;
import com.sbisec.helios.ap.safe.service.account.dto.ReserveBuySettingGetApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulk;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkItem;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSumAmount;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.NextOrderPlan;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingData;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingSummaryApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingSummaryApiOut;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil;

/**
 * 画面ID：SUB0202_0403-01
 * 画面名：投信積立設定済銘柄一覧
 * アクションID：A001
 * アクション名：初期化
 * アクションID：A002
 * アクション名：初期化
　*　@author nicksen.li 
 * 2025/04/02 新規作成
 */
@Component(value = "cmpIfaMutualFundAccumulateSettingBrandListService")
public class IfaMutualFundAccumulateSettingBrandListServiceImpl implements IfaMutualFundAccumulateSettingBrandListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMutualFundAccumulateSettingBrandListServiceImpl.class);

    /** 正常終了のリターンコード   */
    private static final String RETURN_CODE_SUCCESS = "0";

    /** 正常終了のメッセージ   */
    private static final String RETURN_MESSEAGE_SUCCESS = "";

    /** 権限チェックエラー   */
    private static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";

    /** 投信目論見書交付チェックエラー 目論見書の交付記録がないため取引はできません。 */
    private static final String ERRORS_FND_SELECTEDBRAND_NOISSUANCERECORD = "errors.fnd.selectedBrand.noIssuanceRecord";

    /** 目論見書の交付記録がないため取引はできません。<br>銘柄コード：{0} */
    private static final String ERRORS_FND_SELECTEDBRANDS_NOISSUANCERECORD = "errors.fnd.selectedBrands.noIssuanceRecord";

    /** 銘柄マスタ取得エラー(errors.fnd.selectedBrand.noInformation)を返す */
    private static final String ERRORS_NOINFORMATION = "errors.fnd.selectedBrand.noInformation";

    /** 対象顧客参照なし */
    private static final String AUTH_ERROR_VALUE = "0";

    /** 証券金銭種別- 国内投信 */
    private static final String DOMESTIOC_MUTUAL_FUND = "06";

    /** 媒介可否:媒介可 (FCT003) */
    private static final String MEDIATE_PROPRIETY_ABLE = "1";
    
    /** 国内投信:取引種別 "2"：購入（積立） */
    private static final String MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE = "2";

    /** FCT017：確認書受入要否 */
    private enum AcceptanceNecessiy {

        // 不要
        UNNECESSARY("0"),
        // 必要
        NECESSITY("1");

        private final String value;

        AcceptanceNecessiy(final String value) {

            this.value = value;
        }

        private String getValue() {

            return value;
        }
    }

    @Autowired
    Fct001 fct001;

    @Autowired
    Fct003 fct003;

    @Autowired
    Fct017 fct017;

    @Autowired
    private SafeFundTradeService safeFundTradeService;

    @Autowired
    private SafeAccountService safeAccountService;

    @Autowired
    private SafeCommonService safeCommonService;

    @Autowired
    private IfaMutualFundAccumulateSettingBrandListDao ifaMutualFundAccumulateSettingBrandListDao;

    /**
     * コードマスタ
     */
    @Autowired
    private MCodeService mcodeService;

    @Autowired
    private CodeListService codeListService;

    /**
    
     * Dto リクエスト：IfaMutualFundAccumulateSettingBrandListA001RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingBrandListA001ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA001ResponseDto> initializeA001(
            IfaMutualFundAccumulateSettingBrandListA001RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingBrandListServiceImpl.initializeA001");
        }

        DataList<IfaMutualFundAccumulateSettingBrandListA001ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingBrandListA001ResponseDto>();
        List<IfaMutualFundAccumulateSettingBrandListA001ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingBrandListA001ResponseDto>();

        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String kouzaNumber = customerCommon.getAccountNumber();

        // ➀利用者の口座に対する権限チェックを行う。
        // 権限あり：次の処理へ。
        // 上記以外：権限なしエラーを返す。
        InputFct001Dto inFct001Dto = new InputFct001Dto();
        inFct001Dto.setButenCode(butenCode);
        inFct001Dto.setAccountNumber(kouzaNumber);

        OutputFct001Dto outFct001Dto = fct001.doCheck(inFct001Dto);
        if (StringUtils.equals(outFct001Dto.getTargetCustomerRefAuthFlag(), AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(ERRORS_ACCOUNT_NOT_EXISTS, new String[] { butenCode, kouzaNumber }));
            return dtoRes;
        }

        // ②取引コース媒介可否チェックの結果を取得する。
        // FCT003の呼び出し
        InputFct003Dto inFct003Dto = new InputFct003Dto();
        inFct003Dto.setButenCode(butenCode);
        inFct003Dto.setAccountNumber(kouzaNumber);
        inFct003Dto.setProductCd(DOMESTIOC_MUTUAL_FUND);
        inFct003Dto.setTradeCd(MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE);
        final OutputFct003Dto outFct003Dto = fct003.doCheck(inFct003Dto);

        IfaMutualFundAccumulateSettingBrandListA001ResponseDto a001ResponseDto = new IfaMutualFundAccumulateSettingBrandListA001ResponseDto();

        // 積立設定サマリ情報を取得する。 API001
        // Safe Api: /safe/fundTrade/fund/reserve/setting_data_summary
        GetFundReserveSettingDataSummaryReq safeApi001Req = new GetFundReserveSettingDataSummaryReq();
        safeApi001Req.getHeader().setToken(SafeApiUtil.getToken(butenCode,kouzaNumber));
        safeApi001Req.setParameter(new ReserveSettingSummaryApiIn());
        GetFundReserveSettingDataSummaryRes safeApi001Res = null;

        try {
            safeApi001Res = safeFundTradeService.getSettinSummary(safeApi001Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveSettingSummaryApiOut reserveSettingSummaryApiOut = safeApi001Res.getReserveSettingSummaryApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveSettingSummaryApiOut);

        IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneMonthSumAmount = new IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount();
        FundReserveSumAmount oneMonthSumAmountObj = reserveSettingSummaryApiOut.getOneMonthSumAmount();
        oneMonthSumAmount.setSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setCashSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getCashSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setCreditCardSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getCreditCardSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setNormalSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNormalSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setNisaGrowthSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNisaGrowthSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setNisaReserveSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNisaReserveSettingAmount).orElse(new BigDecimal(0)).toPlainString());

        IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneYearSumAmount = new IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount();
        FundReserveSumAmount oneYearSumAmountObj = reserveSettingSummaryApiOut.getOneYearSumAmount();
        oneYearSumAmount.setSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setCashSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getCashSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setCreditCardSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getCreditCardSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setNormalSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNormalSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setNisaGrowthSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNisaGrowthSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setNisaReserveSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNisaReserveSettingAmount).orElse(new BigDecimal(0)).toPlainString());

        a001ResponseDto.setOneMonthSumAmount(oneMonthSumAmount);
        a001ResponseDto.setOneYearSumAmount(oneYearSumAmount);

        IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo bonusMonthSumAmountInfo = new IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo();
        // ボーナス月設定の合計金額
        bonusMonthSumAmountInfo.setBonusSumAmount(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmount())
                .orElse(new BigDecimal(0)).toPlainString());
        // 特定／一般 ボーナス月設定の合計金額
        bonusMonthSumAmountInfo
                .setBonusSumAmountNormal(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNormal())
                        .orElse(new BigDecimal(0)).toPlainString());
        // NISA（成長投資枠） ボーナス月設定の合計金額
        bonusMonthSumAmountInfo.setBonusSumAmountNisaGrowth(
                Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNisaGrowth()).orElse(new BigDecimal(0))
                        .toPlainString());
        // NISA（つみたて投資枠） ボーナス月設定の合計金額
        bonusMonthSumAmountInfo.setBonusSumAmountNisaReserve(
                Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNisaReserve())
                        .orElse(new BigDecimal(0)).toPlainString());

        a001ResponseDto.setBonusMonthSettingInfo(bonusMonthSumAmountInfo);

        // 1ヶ月あたりの積立金額の概算 コメント
        List<MCode> selSql002_1Res = mcodeService.getMCodeList("99", "01", "21");
        if (selSql002_1Res != null && 0 < selSql002_1Res.size()) {
            a001ResponseDto.setOneMonthComment(selSql002_1Res.get(0).getName());
        }

        // 1ヶ月あたりの積立金額の概算 コメント
        List<MCode> selSql002_2Res = mcodeService.getMCodeList("99", "01", "22");
        if (selSql002_2Res != null && 0 < selSql002_2Res.size()) {
            a001ResponseDto.setOneYearComment(selSql002_2Res.get(0).getName());
        }

        // ■1年あたりの積立金額（概算）.NISA（つみたて投資枠）上限金額＝値ありの場合
        // 1年あたりの積立金額（概算）.NISA（つみたて投資枠）上限金額÷12
        // ※ 小数点以下切り捨て
        BigDecimal oneYearLimitNisaReserveAmount = reserveSettingSummaryApiOut.getOneYearLimitNisaReserveAmount();

        if (oneYearLimitNisaReserveAmount != null) {
            a001ResponseDto.setOneMonthLimitNisaReserveAmount(oneYearLimitNisaReserveAmount.divide(new BigDecimal(12))
                    .setScale(0, RoundingMode.DOWN).toPlainString());
        } else {
            a001ResponseDto.setOneMonthLimitNisaReserveAmount("0");
        }

        a001ResponseDto.setOneYearLimitNisaReserveAmount(
                Optional.ofNullable(reserveSettingSummaryApiOut.getOneYearLimitNisaReserveAmount())
                        .orElse(new BigDecimal(0)).toPlainString());

        // ボーナス月の設定金額
        a001ResponseDto.setBonusSumAmount(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmount())
                .orElse(new BigDecimal(0)).toPlainString());

        // 発注予定日
        NextOrderPlan nextOrderPlan = reserveSettingSummaryApiOut.getNextOrderPlan();
        IfaMutualFundAccumulateSettingBrandListNextOrderPlan nextOrderPlanDto = new IfaMutualFundAccumulateSettingBrandListNextOrderPlan();
        if (nextOrderPlan != null) {
            // 次回発注予定日
            // 発注予定日
            if (nextOrderPlan.getOrderPlanDate() != null && nextOrderPlan.getOrderPlanDate().trim().length() == 8) {
                String orderPlanDate = nextOrderPlan.getOrderPlanDate().trim();
                nextOrderPlanDto.setOrderPlanDate(orderPlanDate.substring(0, 4) + "年" + orderPlanDate.substring(4, 6)
                        + "月" + orderPlanDate.substring(6) + "日");
            } else {
                // ■発注予定日＝値なしの場合 ”--年--月--日"
                nextOrderPlanDto.setOrderPlanDate("--年--月--日");
            }

            // 発注金額
            nextOrderPlanDto.setOrderPlanAmount(
                    Optional.ofNullable(nextOrderPlan.getOrderPlanAmount()).orElse(new BigDecimal(0)).toPlainString());
        } else {
            // 次回発注予定日
            nextOrderPlanDto.setOrderPlanDate("--年--月--日");
            // 発注金額
            nextOrderPlanDto.setOrderPlanAmount("0");
        }
        a001ResponseDto.setNextOrderPlan(nextOrderPlanDto);

        // 積立買付ポイント利用状況を取得する。 API002
        // Safe Api: /safe/account/account/point/get_reserve_buy_setting
        GetAccountPointGetReserveBuySettingReq safeApi002Req = new GetAccountPointGetReserveBuySettingReq();
        safeApi002Req.getHeader().setToken(SafeApiUtil.getToken(butenCode,kouzaNumber));
        safeApi002Req.setParameter(new ReserveBuySettingGetApiIn());
        GetAccountPointGetReserveBuySettingRes safeApi002Res = null;

        try {
            safeApi002Res = safeAccountService.getReserveBuySetting(safeApi002Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveBuySettingGetApiOut reserveBuySettingGetApiOut = safeApi002Res.getReserveBuySettingGetApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveBuySettingGetApiOut);

        // ■ポイント利用設定が”利用ポイント指定”の場合 "毎月の利用上限："+ポイント利用上限+"ポイント"
        if ("SOME_USED".equals(reserveBuySettingGetApiOut.getPointUseType())) {
            a001ResponseDto
                    .setPointUseUpperLimit(Optional.ofNullable(reserveBuySettingGetApiOut.getPointUseUpperLimit())
                            .orElse(new BigDecimal(0)).toPlainString());
            a001ResponseDto.setPointSetting("毎月の利用上限：" + "&{pointLimit}" + "ポイント");
        } else if ("ALL_USED".equals(reserveBuySettingGetApiOut.getPointUseType())) {
            // ■ポイント利用設定が”全ポイント利用”の場合 ”すべての利用可能ポイントを使う”
            a001ResponseDto.setPointSetting("すべての利用可能ポイントを使う");
        } else if ("UNUSED".equals(reserveBuySettingGetApiOut.getPointUseType())) {
            // ■ポイント利用設定が”設定登録なし”の場合 "利用しない"
            a001ResponseDto.setPointSetting("利用しない");
        } else {
            // ■上記以外（ポイント利用設定の取得時にエラーが発生した場合も） ”確認できません”
            a001ResponseDto.setPointSetting("確認できません");
        }

        // 積立設定一覧情報を取得する。 API003
        // Safe Api: /safe/fundTrade/fund/trade/reserve/setting_data_list
        GetFundReserveSettingDataListReq safeApi003Req = new GetFundReserveSettingDataListReq();
        safeApi003Req.getHeader().setToken(SafeApiUtil.getToken(butenCode,kouzaNumber));

        ReserveSettingDataListApiIn reserveSettingDataListApiIn = new ReserveSettingDataListApiIn();
        reserveSettingDataListApiIn.setOffset(0);
        reserveSettingDataListApiIn.setLimit(0);
        reserveSettingDataListApiIn.setSortOrder("ASC_KEY");
        reserveSettingDataListApiIn.setSortField("NEXT_RESERVE_DATE");
        safeApi003Req.setParameter(reserveSettingDataListApiIn);
        GetFundReserveSettingDataListRes safeApi003Res = null;
        try {
            safeApi003Res = safeFundTradeService.getSettinDataList(safeApi003Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveSettingDataListApiOut reserveSettingDataListApiOut = safeApi003Res.getReserveSettingDataListApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveSettingDataListApiOut);

        List<ReserveSettingData> apiOutDetailList = reserveSettingDataListApiOut.getReserveOrderList();
        List<IfaMutualFundAccumulateSettingBrandListDetail> dtoDetails = new ArrayList<>();

        if (!CollectionUtils.isEmpty(apiOutDetailList)) {

            IfaMutualFundAccumulateSettingBrandListDetail detail = null;
            List<String> fundCodeList = new ArrayList<>();

            long hitNumber = 0;

            for (ReserveSettingData reserveSettingData : apiOutDetailList) {

                hitNumber++;

                detail = new IfaMutualFundAccumulateSettingBrandListDetail();
                // 積立設定リスト.協会コード
                detail.setFundCode(Optional.ofNullable(reserveSettingData.getFundCode()).orElse(""));
                fundCodeList.add(detail.getFundCode());

                // 積立設定リスト.ファンド名
                detail.setFundName(Optional.ofNullable(reserveSettingData.getFundName()).orElse(""));
                // 積立設定リスト.非特定預り区分
                if (reserveSettingData.getAccountType() != null) {
                    String ifaAccountTypeCode = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(reserveSettingData.getAccountType());
                    detail.setAccountType(ifaAccountTypeCode);
                    detail.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", ifaAccountTypeCode, "3"));
                } else {
                    detail.setAccountType("");
                }

                // 積立設定リスト.決済方法
                if (reserveSettingData.getPaymentMethod() != null) {
                    String ifaPaymentMethod = SafeType2IfaTypeUtil.IfaPaymentMethod.getIfaValueBySafeEnum(reserveSettingData.getPaymentMethod());
                    detail.setPaymentMethod(ifaPaymentMethod);
                    detail.setPaymentMethodName(codeListService.getValue("FUND_RESERVE_PAYMENT_METHOD", ifaPaymentMethod, "1"));
                } else {
                    detail.setPaymentMethodName("");
                }

                // 明細リスト.積立コース
                String courseType = reserveSettingData.getCourseType();
                if (courseType != null) {

                    // ■積立コース＝毎日の場合
                    // "毎日"
                    if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_DAY.getSafeEnum().equals(courseType)) {
                        detail.setCourseType(AccumulateCourse.getEveryDayCourse());
                    } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_WEEK.getSafeEnum().equals(courseType)) {
                        // ■積立コース＝毎週の場合
                        // ”毎週”+"（"+申込設定日(毎週用)+"）"

                        String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                                .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveWeek());

                        detail.setCourseType(AccumulateCourse.getEveryWeekCourse(ifaSettingReserveWeek));
                    } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_MONTH.getSafeEnum().equals(courseType)) {
                        // ■積立コース＝毎月の場合
                        // ”毎月”+"（"+申込設定日(毎月、隔月用)+"）日"
                        // ※積立コースが毎月、隔月かつ３１日の場合：設定日は"月末"

                        detail.setCourseType(
                                AccumulateCourse.getEveryMonthCourse(reserveSettingData.getSettingReserveDay()));
                    } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.BIMONTHLY.getSafeEnum().equals(courseType)) {
                        // ■積立コース＝隔月の場合
                        // 積立奇偶月設定区分+"（"+申込設定日(毎月、隔月用)+"）日"
                        // ※積立コースが毎月、隔月かつ３１日の場合：設定日は"月末"
                        String ifaBimonthlyCourse = SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                                .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveBimonthly());

                        if (ifaBimonthlyCourse != null) {
                            detail.setCourseType(AccumulateCourse.getBimonthlyCourse(ifaBimonthlyCourse,
                                    reserveSettingData.getSettingReserveDay()));
                        } else {
                            detail.setCourseType("");
                        }
                    } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.MULTIPLE_DAYS.getSafeEnum().equals(courseType)) {
                        // ■積立コース＝複数日の場合
                        // ”複数日”+"（"+申込設定日(複数日用)+"）日"

                        detail.setCourseType(
                                AccumulateCourse.getMultipleDaysCourse(reserveSettingData.getSettingReserveMultiDay()));
                    }
                }

                // 積立設定リスト.設定金額
                detail.setSettingAmount(Optional.ofNullable(reserveSettingData.getSettingAmount())
                        .orElse(new BigDecimal(0)).toPlainString());
                // 明細リスト.ボーナス設定
                // ■決済方法＝現金かつボーナス設定金額、ボーナス1設定月、ボーナス1設定日＝値ありの場合
                if ("CASH".equals(reserveSettingData.getPaymentMethod())
                        && reserveSettingData.getSettingBonusAmount() != null
                        && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus1Month())
                        && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus1Day())) {
                    // 上段：ボーナス設定金額＋”円”
                    detail.setSettingBonusAmount(Optional.ofNullable(reserveSettingData.getSettingBonusAmount())
                            .orElse(new BigDecimal(0)).toPlainString());
                    // 下段：
                    // ■ボーナス2設定月、ボーナス2設定日＝値ありの場合
                    // ”(”+ボーナス１設定月+"/"+ボーナス１設定日＋”、”+ボーナス2設定月+"/"+ボーナス2設定日＋")"
                    if (StringUtils.isNotEmpty(reserveSettingData.getSettingBonus2Month())
                            && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus2Day())) {
                        detail.setSettingBonusMonthDay("(" + reserveSettingData.getSettingBonus1Month() + "/"
                                + ("31".equals(reserveSettingData.getSettingBonus1Day()) ? "月末"
                                        : reserveSettingData.getSettingBonus1Day())
                                + "," + reserveSettingData.getSettingBonus2Month() + "/"
                                + ("31".equals(reserveSettingData.getSettingBonus2Day()) ? "月末"
                                        : reserveSettingData.getSettingBonus2Day())
                                + ")");
                    } else {
                        // ■上記以外
                        // ”(”+ボーナス１設定月+"/"+ボーナス１設定日+")"
                        detail.setSettingBonusMonthDay("(" + reserveSettingData.getSettingBonus1Month() + "/"
                                + ("31".equals(reserveSettingData.getSettingBonus1Day()) ? "月末"
                                        : reserveSettingData.getSettingBonus1Day())
                                + ")");
                    }
                    // ※設定日が31の場合：MM/月末
                } else {
                    // ■上記以外
                    detail.setSettingBonusMonthDay("-");
                }

                // 明細.NISA枠ぎりぎり注文
                /**
                 * USE 2
                 * UNUSED 1
                 * UNSUPPORTED 0
                 */
                String ifaNisaBarelyBuyingType = SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                        .getIfaValueBySafeEnum(reserveSettingData.getNisaBarelyBuyingType());
                /**
                 * 設定する、設定しない、-
                 * ＠取得パターン:1
                 * ＠表示パターン:1
                 */
                detail.setNisaBarelyBuyingType(codeListService.getValue("FUND_RESERVE_NISA_BARELY_BUYING_KBN",
                        ifaNisaBarelyBuyingType, "1"));

                // 明細.課税枠シフト注文
                /**
                 * USE 2
                 * UNUSED 1
                 * UNSUPPORTED 0
                 */
                String ifaTaxShiftType = SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                        .getIfaValueBySafeEnum(reserveSettingData.getTaxShiftType());
                /**
                 * 設定する、設定しない、-
                 * ＠取得パターン:1
                 * ＠表示パターン:1
                 */
                detail.setTaxShiftType(codeListService.getValue("FUND_RESERVE_TAX_SHIFT_KBN",
                        ifaTaxShiftType, "1"));

                // 明細.1ヵ月あたりの積立概算
                detail.setOneMonthSumAmount(Optional.ofNullable(reserveSettingData.getOneMonthSumAmount())
                        .orElse(new BigDecimal(0)).toPlainString());

                // 明細.次回発注予定日
                /*
                 * ■次回発注予定日＝値なしの場合
                　* "----/--/--"
                 * ■上記以外 次回発注予定日
                 */
                if (StringUtils.isNotEmpty(reserveSettingData.getNextReserveDate())
                        && reserveSettingData.getNextReserveDate().length() == 8) {
                    detail.setNextReserveDate(LocalDate
                            .parse(reserveSettingData.getNextReserveDate(),
                                    DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD))
                            .format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
                } else {
                    detail.setNextReserveDate("----/--/--");
                }

                // 明細リスト.積立可否
                // ■FCT001.取引停止フラグ ="0"（取引停止口座ではない)かつ
                // 　FCT003.媒介可否リスト.媒介可否="1"(媒介可)かつ
                // 　FCT017.銘柄リスト.NRIコード.書類コード.受入要否=不要の場合
                // 　　"1"(可能)
                // ■上記以外
                // 　　"2"(不可)
                detail.setAccumulateAvailability("2");

                // 明細リスト.積立変更可否
                // ■FCT001.取引停止フラグ ="0"（取引停止口座ではない)かつ
                // 　FCT003.媒介可否リスト.媒介可否="1"(媒介可)かつ
                // 　FCT017.銘柄リスト.NRIコード.書類コード.受入要否=不要かつ
                // 　API003.積立設定リスト.決済方法＝”CASH”(現金)の場合
                // 　　"1"(可能)
                // ■上記以外
                // 　　"2"(不可)
                detail.setAccumulateChangeAvailability("2");

                // 明細リスト.積立解除可否
                // ■API003.積立設定リスト.決済方法＝”CASH”(現金)の場合
                // "1"(可能)
                // ■上記以外
                // "2"(不可)
                if ("CASH".equals(reserveSettingData.getPaymentMethod())) {
                    detail.setAccumulateCancelAvailability("1");
                } else {
                    detail.setAccumulateCancelAvailability("2");
                }

                dtoDetails.add(detail);
            }

            a001ResponseDto.setHitNumber(String.valueOf(hitNumber));

            IfaMutualFundAccumulateSettingBrandListSql001RequestModel sql001RequestModel = new IfaMutualFundAccumulateSettingBrandListSql001RequestModel();
            sql001RequestModel.setFundCodeList(fundCodeList);
            DataList<IfaMutualFundAccumulateSettingBrandListSql001ResponseModel> fundCodeResponseModelList = ifaMutualFundAccumulateSettingBrandListDao.selectIfaMutualFundAccumulateSettingBrandListSql001(sql001RequestModel);

            List<InquiryMutualFundBrand> inquiryMutualfundBrandList = new ArrayList<InquiryMutualFundBrand>();

            Map<String, IfaMutualFundAccumulateSettingBrandListSql001ResponseModel> mFCode2MFNricodeMap = new HashMap<>();

            if (fundCodeResponseModelList != null && fundCodeResponseModelList.getDataList() != null && fundCodeResponseModelList.getDataList().size() > 0) {

                IfaMutualFundAccumulateSettingBrandListSql001ResponseModel sql001ResponseModel = null;
                InquiryMutualFundBrand inquiryMutualFundBrand = null;

                for (IfaMutualFundAccumulateSettingBrandListSql001ResponseModel model : fundCodeResponseModelList.getDataList()) {
                    sql001ResponseModel = new IfaMutualFundAccumulateSettingBrandListSql001ResponseModel();
                    sql001ResponseModel.setMfcode(model.getMfcode());
                    sql001ResponseModel.setMfkaisu(model.getMfkaisu());
                    sql001ResponseModel.setMfgo(model.getMfgo());
                    sql001ResponseModel.setMfnricode(model.getMfnricode());

                    mFCode2MFNricodeMap.put(model.getMfcode(), sql001ResponseModel);

                    // 回数(4桁)＋号(3桁)
                    inquiryMutualFundBrand = new InquiryMutualFundBrand();
                    inquiryMutualFundBrand.setNriCd(model.getMfkaisu() + " " + model.getMfgo());
                    inquiryMutualfundBrandList.add(inquiryMutualFundBrand);
                }
            }

            Map<String, String> nriCd2AcceptanceNecessityMap = new HashMap<>();

            if (inquiryMutualfundBrandList.size() > 0) {
                InputFct017Dto inFct017Dto = new InputFct017Dto();
                inFct017Dto.setButenCode(butenCode);
                inFct017Dto.setAccountNumber(kouzaNumber);
                inFct017Dto.setInquiryMutualFundBrandList(inquiryMutualfundBrandList);

                final OutputFct017Dto outFct017Dto = fct017.getData(inFct017Dto);

                if (outFct017Dto != null && outFct017Dto.getBrandList() != null) {
                    for (OutputFct017Dto.Brand brand : outFct017Dto.getBrandList()) {
                        if (brand.getNriCd() != null) {
                            nriCd2AcceptanceNecessityMap.put(brand.getNriCd(), brand.getAcceptanceNecessity());
                        }
                    }
                }
            }

            for (IfaMutualFundAccumulateSettingBrandListDetail dto : dtoDetails) {

                String nriCd = null;
                String mfkaisu = null;
                String mfgo = null;

                if (mFCode2MFNricodeMap.get(dto.getFundCode()) == null) {
                    // 銘柄マスタ取得エラー(errors.fnd.selectedBrand.noInformation)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_NOINFORMATION,
                            IfaCommonUtil.getMessage(ERRORS_NOINFORMATION));
                    return dtoRes;
                } else {

                    IfaMutualFundAccumulateSettingBrandListSql001ResponseModel sql001ResponseModel = mFCode2MFNricodeMap
                            .get(dto.getFundCode());

                    mfkaisu = Optional.ofNullable(sql001ResponseModel.getMfkaisu()).orElse("");
                    mfgo = " " + Optional.ofNullable(sql001ResponseModel.getMfgo()).orElse("").trim();
                    nriCd = mfkaisu + mfgo;
                }

                dto.setMfkaisu(mfkaisu);
                dto.setMfgo(mfgo);

                String detailID = dto.getMfkaisu() + "#" + dto.getMfgo() + "#" + dto.getFundCode() + "#"
                        + dto.getAccountType() + "#" + dto.getPaymentMethod();
                dto.setId(detailID);

                if (Fct001.TRADE_SUSPEND_FLAG_0.equals(outFct001Dto.getTradeSuspendFlag())
                        && getMediateProprietyFromOutputFct003Dto(outFct003Dto)) {
                    if (nriCd != null && nriCd.length() > 0) {
                        /**
                         * ■FCT001.取引停止フラグ =""0""（取引停止口座ではない)かつ
                         *    FCT003.媒介可否リスト.媒介可否=""1""(媒介可)かつ
                         *    FCT017.銘柄リスト.NRIコード.書類コード.受入要否=不要の場合
                         *    ""1""(可能)
                         * ■上記以外
                         *    ""2""(不可)
                         */
                        if (StringUtils.equals(nriCd2AcceptanceNecessityMap.get(nriCd),
                                AcceptanceNecessiy.UNNECESSARY.getValue())) {
                            dto.setAccumulateAvailability("1");
                        }

                        /**
                         * ■FCT001.取引停止フラグ =""0""（取引停止口座ではない)かつ
                         *    FCT003.媒介可否リスト.媒介可否=""1""(媒介可)かつ
                         *    FCT017.銘柄リスト.NRIコード.書類コード.受入要否=不要かつ
                         *    API003.積立設定リスト.決済方法＝”CASH”(現金)の場合
                         *    ""1""(可能)
                         * ■上記以外
                         *    ""2""(不可)
                         */
                        if (StringUtils.equals(nriCd2AcceptanceNecessityMap.get(nriCd),
                                AcceptanceNecessiy.UNNECESSARY.getValue())
                                && SafeType2IfaTypeUtil.IfaPaymentMethod.CASH.getValue()
                                        .equals(dto.getPaymentMethod())) {
                            dto.setAccumulateChangeAvailability("1");
                        }
                    }
                }
            }

            a001ResponseDto.setDetailList(dtoDetails);

        } else {
            a001ResponseDto.setDetailList(null);
        }

        resDtoList.add(a001ResponseDto);

        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
    
     * Dto リクエスト：IfaMutualFundAccumulateSettingBrandListA002RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingBrandListA002ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA002ResponseDto> initializeA002(
            IfaMutualFundAccumulateSettingBrandListA002RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingBrandListServiceImplL.initializeA002");
        }

        DataList<IfaMutualFundAccumulateSettingBrandListA002ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingBrandListA002ResponseDto>();
        List<IfaMutualFundAccumulateSettingBrandListA002ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingBrandListA002ResponseDto>();

        IfaMutualFundAccumulateSettingBrandListA002ResponseDto errorReturnDto = new IfaMutualFundAccumulateSettingBrandListA002ResponseDto();
        errorReturnDto.setSource(dtoReq.getSource());
        errorReturnDto.setStep(dtoReq.getStep());
        errorReturnDto.setListFlag(dtoReq.isListFlag());
        errorReturnDto.setMfkaisu(dtoReq.getMfkaisu());
        errorReturnDto.setMfgo(dtoReq.getMfgo());

        resDtoList.add(errorReturnDto);

        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String kouzaNumber = customerCommon.getAccountNumber();

        // ➀利用者の口座に対する権限チェックを行う。
        // 権限あり：次の処理へ。
        // 上記以外：権限なしエラーを返す。
        InputFct001Dto inFct001Dto = new InputFct001Dto();
        inFct001Dto.setButenCode(butenCode);
        inFct001Dto.setAccountNumber(kouzaNumber);

        OutputFct001Dto outFct001Dto = fct001.doCheck(inFct001Dto);
        if (StringUtils.equals(outFct001Dto.getTargetCustomerRefAuthFlag(), AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(ERRORS_ACCOUNT_NOT_EXISTS, new String[] { butenCode, kouzaNumber }));
            return dtoRes;
        }

        // ②取引コース媒介可否チェックの結果を取得する。
        // FCT003の呼び出し
        InputFct003Dto inFct003Dto = new InputFct003Dto();
        inFct003Dto.setButenCode(butenCode);
        inFct003Dto.setAccountNumber(kouzaNumber);
        inFct003Dto.setProductCd(DOMESTIOC_MUTUAL_FUND);
        // ・レスポンス.取引種別
        inFct003Dto.setTradeCd(MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE);

        final OutputFct003Dto outFct003Dto = fct003.doCheck(inFct003Dto);

        IfaMutualFundAccumulateSettingBrandListA002ResponseDto a002ResponseDto = new IfaMutualFundAccumulateSettingBrandListA002ResponseDto();

        // 積立設定サマリ情報を取得する。 API001
        // Safe Api: /safe/fundTrade/fund/reserve/setting_data_summary
        GetFundReserveSettingDataSummaryReq safeApi001Req = new GetFundReserveSettingDataSummaryReq();
        safeApi001Req.getHeader().setToken(SafeApiUtil.getToken(butenCode,kouzaNumber));
        safeApi001Req.setParameter(new ReserveSettingSummaryApiIn());
        GetFundReserveSettingDataSummaryRes safeApi001Res = null;
        try {
            safeApi001Res = safeFundTradeService.getSettinSummary(safeApi001Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveSettingSummaryApiOut reserveSettingSummaryApiOut = safeApi001Res.getReserveSettingSummaryApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveSettingSummaryApiOut);

        IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneMonthSumAmount = new IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount();
        FundReserveSumAmount oneMonthSumAmountObj = reserveSettingSummaryApiOut.getOneMonthSumAmount();
        oneMonthSumAmount.setSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setCashSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getCashSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setCreditCardSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getCreditCardSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setNormalSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNormalSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setNisaGrowthSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNisaGrowthSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneMonthSumAmount.setNisaReserveSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNisaReserveSettingAmount).orElse(new BigDecimal(0)).toPlainString());

        IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneYearSumAmount = new IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount();
        FundReserveSumAmount oneYearSumAmountObj = reserveSettingSummaryApiOut.getOneYearSumAmount();
        oneYearSumAmount.setSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setCashSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getCashSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setCreditCardSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getCreditCardSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setNormalSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNormalSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setNisaGrowthSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNisaGrowthSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        oneYearSumAmount.setNisaReserveSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNisaReserveSettingAmount).orElse(new BigDecimal(0)).toPlainString());

        a002ResponseDto.setOneMonthSumAmount(oneMonthSumAmount);
        a002ResponseDto.setOneYearSumAmount(oneYearSumAmount);

        IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo bonusMonthSumAmountInfo = new IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo();
        // ボーナス月設定の合計金額
        bonusMonthSumAmountInfo.setBonusSumAmount(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmount())
                .orElse(new BigDecimal(0)).toPlainString());
        // 特定／一般 ボーナス月設定の合計金額
        bonusMonthSumAmountInfo
                .setBonusSumAmountNormal(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNormal())
                        .orElse(new BigDecimal(0)).toPlainString());
        // NISA（成長投資枠） ボーナス月設定の合計金額
        bonusMonthSumAmountInfo.setBonusSumAmountNisaGrowth(
                Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNisaGrowth()).orElse(new BigDecimal(0))
                        .toPlainString());
        // NISA（つみたて投資枠） ボーナス月設定の合計金額
        bonusMonthSumAmountInfo.setBonusSumAmountNisaReserve(
                Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNisaReserve())
                        .orElse(new BigDecimal(0)).toPlainString());

        a002ResponseDto.setBonusMonthSettingInfo(bonusMonthSumAmountInfo);

        // 1ヶ月あたりの積立金額の概算 コメント
        List<MCode> selSql002_1Res = mcodeService.getMCodeList("99", "01", "21");
        if (selSql002_1Res != null && 0 < selSql002_1Res.size()) {
            a002ResponseDto.setOneMonthComment(selSql002_1Res.get(0).getName());
        }

        // 1ヶ月あたりの積立金額の概算 コメント
        List<MCode> selSql002_2Res = mcodeService.getMCodeList("99", "01", "22");
        if (selSql002_2Res != null && 0 < selSql002_2Res.size()) {
            a002ResponseDto.setOneYearComment(selSql002_2Res.get(0).getName());
        }

        // ■1年あたりの積立金額（概算）.NISA（つみたて投資枠）上限金額＝値ありの場合
        // 1年あたりの積立金額（概算）.NISA（つみたて投資枠）上限金額÷12
        // ※ 小数点以下切り捨て
        BigDecimal oneYearLimitNisaReserveAmount = reserveSettingSummaryApiOut.getOneYearLimitNisaReserveAmount();

        if (oneYearLimitNisaReserveAmount != null) {
            a002ResponseDto.setOneMonthLimitNisaReserveAmount(oneYearLimitNisaReserveAmount.divide(new BigDecimal(12))
                    .setScale(0, RoundingMode.DOWN).toPlainString());
        } else {
            a002ResponseDto.setOneMonthLimitNisaReserveAmount("0");
        }

        a002ResponseDto.setOneYearLimitNisaReserveAmount(
                Optional.ofNullable(reserveSettingSummaryApiOut.getOneYearLimitNisaReserveAmount())
                        .orElse(new BigDecimal(0)).toPlainString());

        // ボーナス月の設定金額
        a002ResponseDto.setBonusSumAmount(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmount())
                .orElse(new BigDecimal(0)).toPlainString());

        // 発注予定日
        NextOrderPlan nextOrderPlan = reserveSettingSummaryApiOut.getNextOrderPlan();
        IfaMutualFundAccumulateSettingBrandListNextOrderPlan nextOrderPlanDto = new IfaMutualFundAccumulateSettingBrandListNextOrderPlan();
        if (nextOrderPlan != null) {
            // 次回発注予定日
            // 発注予定日
            if (nextOrderPlan.getOrderPlanDate() != null && nextOrderPlan.getOrderPlanDate().length() == 8) {
                String orderPlanDate = nextOrderPlan.getOrderPlanDate();
                nextOrderPlanDto.setOrderPlanDate(orderPlanDate.substring(0, 4) + "年" + orderPlanDate.substring(4, 6)
                        + "月" + orderPlanDate.substring(6) + "日");
            } else {
                // ■発注予定日＝値なしの場合 ”--年--月--日"
                nextOrderPlanDto.setOrderPlanDate("--年--月--日");
            }

            // 発注金額
            nextOrderPlanDto.setOrderPlanAmount(
                    Optional.ofNullable(nextOrderPlan.getOrderPlanAmount()).orElse(new BigDecimal(0)).toPlainString());
        } else {
            // 次回発注予定日
            nextOrderPlanDto.setOrderPlanDate("--年--月--日");
            // 発注金額
            nextOrderPlanDto.setOrderPlanAmount("0");
        }
        a002ResponseDto.setNextOrderPlan(nextOrderPlanDto);

        // 積立買付ポイント利用状況を取得する。 API002
        // Safe Api: /safe/account/account/point/get_reserve_buy_setting
        GetAccountPointGetReserveBuySettingReq safeApi002Req = new GetAccountPointGetReserveBuySettingReq();
        safeApi002Req.getHeader().setToken(SafeApiUtil.getToken(butenCode,kouzaNumber));
        safeApi002Req.setParameter(new ReserveBuySettingGetApiIn());
        GetAccountPointGetReserveBuySettingRes safeApi002Res = null;

        try {
            safeApi002Res = safeAccountService.getReserveBuySetting(safeApi002Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveBuySettingGetApiOut reserveBuySettingGetApiOut = safeApi002Res.getReserveBuySettingGetApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveBuySettingGetApiOut);

        // ■ポイント利用設定が”利用ポイント指定”の場合 "毎月の利用上限："+ポイント利用上限+"ポイント"
        if ("SOME_USED".equals(reserveBuySettingGetApiOut.getPointUseType())) {
            a002ResponseDto
                    .setPointUseUpperLimit(Optional.ofNullable(reserveBuySettingGetApiOut.getPointUseUpperLimit())
                            .orElse(new BigDecimal(0)).toPlainString());
            a002ResponseDto.setPointSetting("毎月の利用上限：" + "&{pointLimit}" + "ポイント");
        } else if ("ALL_USED".equals(reserveBuySettingGetApiOut.getPointUseType())) {
            // ■ポイント利用設定が”全ポイント利用”の場合 ”すべての利用可能ポイントを使う”
            a002ResponseDto.setPointSetting("すべての利用可能ポイントを使う");
        } else if ("UNUSED".equals(reserveBuySettingGetApiOut.getPointUseType())) {
            // ■ポイント利用設定が”設定登録なし”の場合 "利用しない"
            a002ResponseDto.setPointSetting("利用しない");
        } else {
            // ■上記以外（ポイント利用設定の取得時にエラーが発生した場合も） ”確認できません”
            a002ResponseDto.setPointSetting("確認できません");
        }

        // 積立設定一覧情報を取得する。 API003
        // Safe Api: /safe/fundTrade/fund/trade/reserve/setting_data_list
        GetFundReserveSettingDataListReq safeApi003Req = new GetFundReserveSettingDataListReq();
        safeApi003Req.getHeader().setToken(SafeApiUtil.getToken(butenCode,kouzaNumber));

        ReserveSettingDataListApiIn reserveSettingDataListApiIn = new ReserveSettingDataListApiIn();
        reserveSettingDataListApiIn.setOffset(0);
        reserveSettingDataListApiIn.setLimit(0);
        reserveSettingDataListApiIn.setFundCode(dtoReq.getFundCode());
        reserveSettingDataListApiIn.setSortOrder("ASC_KEY");
        reserveSettingDataListApiIn.setSortField("NEXT_RESERVE_DATE");
        safeApi003Req.setParameter(reserveSettingDataListApiIn);
        GetFundReserveSettingDataListRes safeApi003Res = null;

        try {
            safeApi003Res = safeFundTradeService.getSettinDataList(safeApi003Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveSettingDataListApiOut reserveSettingDataListApiOut = safeApi003Res.getReserveSettingDataListApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveSettingDataListApiOut);


        List<ReserveSettingData> apiOutDetailList = reserveSettingDataListApiOut.getReserveOrderList();
        List<IfaMutualFundAccumulateSettingBrandListDetail> dtoDetails = new ArrayList<>();

        if (!CollectionUtils.isEmpty(apiOutDetailList)) {

            IfaMutualFundAccumulateSettingBrandListDetail detail = null;
            List<String> fundCodeList = new ArrayList<>();

            long hitNumber = 0;

            for (ReserveSettingData reserveSettingData : apiOutDetailList) {

                hitNumber++;

                detail = new IfaMutualFundAccumulateSettingBrandListDetail();
                // 積立設定リスト.協会コード
                detail.setFundCode(Optional.ofNullable(reserveSettingData.getFundCode()).orElse(""));
                fundCodeList.add(detail.getFundCode());

                // 積立設定リスト.ファンド名
                detail.setFundName(Optional.ofNullable(reserveSettingData.getFundName()).orElse(""));
                // 積立設定リスト.非特定預り区分
                if (reserveSettingData.getAccountType() != null) {
                    String ifaAccountTypeCode = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(reserveSettingData.getAccountType());
                    detail.setAccountType(ifaAccountTypeCode);
                    detail.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", ifaAccountTypeCode, "3"));
                } else {
                    detail.setAccountType("");
                }

                // 積立設定リスト.決済方法
                if (reserveSettingData.getPaymentMethod() != null) {
                    String ifaPaymentMethod = SafeType2IfaTypeUtil.IfaPaymentMethod.getIfaValueBySafeEnum(reserveSettingData.getPaymentMethod());
                    detail.setPaymentMethod(ifaPaymentMethod);
                    detail.setPaymentMethodName(codeListService.getValue("FUND_RESERVE_PAYMENT_METHOD", ifaPaymentMethod, "1"));
                } else {
                    detail.setPaymentMethodName("");
                }

                // 明細リスト.積立コース
                String courseType = reserveSettingData.getCourseType();
                if (courseType != null) {

                    // ■積立コース＝毎日の場合
                    // "毎日"
                    if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_DAY.getSafeEnum().equals(courseType)) {
                        detail.setCourseType(AccumulateCourse.getEveryDayCourse());
                    } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_WEEK.getSafeEnum().equals(courseType)) {
                        // ■積立コース＝毎週の場合
                        // ”毎週”+"（"+申込設定日(毎週用)+"）"

                        String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                                .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveWeek());

                        detail.setCourseType(AccumulateCourse.getEveryWeekCourse(ifaSettingReserveWeek));
                    } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_MONTH.getSafeEnum().equals(courseType)) {
                        // ■積立コース＝毎月の場合
                        // ”毎月”+"（"+申込設定日(毎月、隔月用)+"）日"
                        // ※積立コースが毎月、隔月かつ３１日の場合：設定日は"月末"

                        detail.setCourseType(
                                AccumulateCourse.getEveryMonthCourse(reserveSettingData.getSettingReserveDay()));
                    } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.BIMONTHLY.getSafeEnum().equals(courseType)) {
                        // ■積立コース＝隔月の場合
                        // 積立奇偶月設定区分+"（"+申込設定日(毎月、隔月用)+"）日"
                        // ※積立コースが毎月、隔月かつ３１日の場合：設定日は"月末"
                        String ifaBimonthlyCourse = SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                                .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveBimonthly());

                        if (ifaBimonthlyCourse != null) {
                            detail.setCourseType(AccumulateCourse.getBimonthlyCourse(ifaBimonthlyCourse,
                                    reserveSettingData.getSettingReserveDay()));
                        } else {
                            detail.setCourseType("");
                        }
                    } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.MULTIPLE_DAYS.getSafeEnum().equals(courseType)) {
                        // ■積立コース＝複数日の場合
                        // ”複数日”+"（"+申込設定日(複数日用)+"）日"

                        detail.setCourseType(
                                AccumulateCourse.getMultipleDaysCourse(reserveSettingData.getSettingReserveMultiDay()));
                    }
                }

                // 積立設定リスト.設定金額
                detail.setSettingAmount(Optional.ofNullable(reserveSettingData.getSettingAmount())
                        .orElse(new BigDecimal(0)).toPlainString());
                // 明細リスト.ボーナス設定
                // ■決済方法＝現金かつボーナス設定金額、ボーナス1設定月、ボーナス1設定日＝値ありの場合
                if ("CASH".equals(reserveSettingData.getPaymentMethod())
                        && reserveSettingData.getSettingBonusAmount() != null
                        && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus1Month())
                        && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus1Day())) {
                    // 上段：ボーナス設定金額＋”円”
                    detail.setSettingBonusAmount(Optional.ofNullable(reserveSettingData.getSettingBonusAmount())
                            .orElse(new BigDecimal(0)).toPlainString());
                    // 下段：
                    // ■ボーナス2設定月、ボーナス2設定日＝値ありの場合
                    // ”(”+ボーナス１設定月+"/"+ボーナス１設定日＋”、”+ボーナス2設定月+"/"+ボーナス2設定日＋")"
                    if (StringUtils.isNotEmpty(reserveSettingData.getSettingBonus2Month())
                            && StringUtils.isNotEmpty(reserveSettingData.getSettingBonus2Day())) {
                        detail.setSettingBonusMonthDay("(" + reserveSettingData.getSettingBonus1Month() + "/"
                                + ("31".equals(reserveSettingData.getSettingBonus1Day()) ? "月末"
                                        : reserveSettingData.getSettingBonus1Day())
                                + "," + reserveSettingData.getSettingBonus2Month() + "/"
                                + ("31".equals(reserveSettingData.getSettingBonus2Day()) ? "月末"
                                        : reserveSettingData.getSettingBonus2Day())
                                + ")");
                    } else {
                        // ■上記以外
                        // ”(”+ボーナス１設定月+"/"+ボーナス１設定日+")"
                        detail.setSettingBonusMonthDay("(" + reserveSettingData.getSettingBonus1Month() + "/"
                                + ("31".equals(reserveSettingData.getSettingBonus1Day()) ? "月末"
                                        : reserveSettingData.getSettingBonus1Day())
                                + ")");
                    }
                    // ※設定日が31の場合：MM/月末
                } else {
                    // ■上記以外
                    detail.setSettingBonusMonthDay("-");
                }

                // 明細.NISA枠ぎりぎり注文
                /**
                 * USE 2
                 * UNUSED 1
                 * UNSUPPORTED 0
                 */
                String ifaNisaBarelyBuyingType = SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                        .getIfaValueBySafeEnum(reserveSettingData.getNisaBarelyBuyingType());
                /**
                 * 設定する、設定しない、-
                 * ＠取得パターン:1
                 * ＠表示パターン:1
                 */
                detail.setNisaBarelyBuyingType(codeListService.getValue("FUND_RESERVE_NISA_BARELY_BUYING_KBN",
                        ifaNisaBarelyBuyingType, "1"));

                // 明細.課税枠シフト注文
                /**
                 * USE 2
                 * UNUSED 1
                 * UNSUPPORTED 0
                 */
                String ifaTaxShiftType = SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                        .getIfaValueBySafeEnum(reserveSettingData.getTaxShiftType());
                /**
                 * 設定する、設定しない、-
                 * ＠取得パターン:1
                 * ＠表示パターン:1
                 */
                detail.setTaxShiftType(codeListService.getValue("FUND_RESERVE_TAX_SHIFT_KBN",
                        ifaTaxShiftType, "1"));

                // 明細.1ヵ月あたりの積立概算
                detail.setOneMonthSumAmount(Optional.ofNullable(reserveSettingData.getOneMonthSumAmount())
                        .orElse(new BigDecimal(0)).toPlainString());

                // 明細.次回発注予定日
                /*
                 * ■次回発注予定日＝値なしの場合
                　* "----/--/--"
                 * ■上記以外 次回発注予定日
                 */
                if (StringUtils.isNotEmpty(reserveSettingData.getNextReserveDate())
                        && reserveSettingData.getNextReserveDate().length() == 8) {
                    detail.setNextReserveDate(LocalDate
                            .parse(reserveSettingData.getNextReserveDate(),
                                    DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD))
                            .format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
                } else {
                    detail.setNextReserveDate("----/--/--");
                }


                // 明細リスト.積立可否
                // ■FCT001.取引停止フラグ ="0"（取引停止口座ではない)かつ
                // 　FCT003.媒介可否リスト.媒介可否="1"(媒介可)かつ
                // 　FCT017.銘柄リスト.NRIコード.書類コード.受入要否=不要の場合
                // 　　"1"(可能)
                // ■上記以外
                // 　　"2"(不可)
                detail.setAccumulateAvailability("2");

                // 明細リスト.積立変更可否
                // ■FCT001.取引停止フラグ ="0"（取引停止口座ではない)かつ
                // 　FCT003.媒介可否リスト.媒介可否="1"(媒介可)かつ
                // 　FCT017.銘柄リスト.NRIコード.書類コード.受入要否=不要かつ
                // 　API003.積立設定リスト.決済方法＝”CASH”(現金)の場合
                // 　　"1"(可能)
                // ■上記以外
                // 　　"2"(不可)
                detail.setAccumulateChangeAvailability("2");

                // 明細リスト.積立解除可否
                // ■API003.積立設定リスト.決済方法＝”CASH”(現金)の場合
                // "1"(可能)
                // ■上記以外
                // "2"(不可)
                if ("CASH".equals(reserveSettingData.getPaymentMethod())) {
                    detail.setAccumulateCancelAvailability("1");
                } else {
                    detail.setAccumulateCancelAvailability("2");
                }

                dtoDetails.add(detail);
            }

            a002ResponseDto.setHitNumber(String.valueOf(hitNumber));

            IfaMutualFundAccumulateSettingBrandListSql001RequestModel sql001RequestModel = new IfaMutualFundAccumulateSettingBrandListSql001RequestModel();
            sql001RequestModel.setFundCodeList(fundCodeList);
            DataList<IfaMutualFundAccumulateSettingBrandListSql001ResponseModel> fundCodeResponseModelList = ifaMutualFundAccumulateSettingBrandListDao.selectIfaMutualFundAccumulateSettingBrandListSql001(sql001RequestModel);

            List<InquiryMutualFundBrand> inquiryMutualfundBrandList = new ArrayList<InquiryMutualFundBrand>();

            Map<String, IfaMutualFundAccumulateSettingBrandListSql001ResponseModel> mFCode2MFNricodeMap = new HashMap<>();
            if (fundCodeResponseModelList != null && fundCodeResponseModelList.getDataList() != null && fundCodeResponseModelList.getDataList().size() > 0) {

                IfaMutualFundAccumulateSettingBrandListSql001ResponseModel sql001ResponseModel = null;
                InquiryMutualFundBrand inquiryMutualFundBrand = null;
                for (IfaMutualFundAccumulateSettingBrandListSql001ResponseModel model : fundCodeResponseModelList.getDataList()) {
                    sql001ResponseModel = new IfaMutualFundAccumulateSettingBrandListSql001ResponseModel();
                    sql001ResponseModel.setMfcode(model.getMfcode());
                    sql001ResponseModel.setMfkaisu(model.getMfkaisu());
                    sql001ResponseModel.setMfgo(model.getMfgo());
                    sql001ResponseModel.setMfnricode(model.getMfnricode());

                    mFCode2MFNricodeMap.put(model.getMfcode(), sql001ResponseModel);

                    // 回数(4桁)＋号(3桁)
                    inquiryMutualFundBrand = new InquiryMutualFundBrand();
                    inquiryMutualFundBrand.setNriCd(model.getMfkaisu() + " " + model.getMfgo());
                    inquiryMutualfundBrandList.add(inquiryMutualFundBrand);
                }
            }

            Map<String, String> nriCd2AcceptanceNecessityMap = new HashMap<>();

            if (inquiryMutualfundBrandList.size() > 0) {
                InputFct017Dto inFct017Dto = new InputFct017Dto();
                inFct017Dto.setButenCode(butenCode);
                inFct017Dto.setAccountNumber(kouzaNumber);
                inFct017Dto.setInquiryMutualFundBrandList(inquiryMutualfundBrandList);
                
                final OutputFct017Dto outFct017Dto = fct017.getData(inFct017Dto);
                
                if (outFct017Dto != null && outFct017Dto.getBrandList() != null) {
                    for (OutputFct017Dto.Brand brand : outFct017Dto.getBrandList()) {
                        if (brand.getNriCd() != null) {
                            nriCd2AcceptanceNecessityMap.put(brand.getNriCd(), brand.getAcceptanceNecessity());
                        }
                    }
                }
            }


            for (IfaMutualFundAccumulateSettingBrandListDetail dto : dtoDetails) {

                String nriCd = null;
                String mfkaisu = null;
                String mfgo = null;

                if (mFCode2MFNricodeMap.get(dto.getFundCode()) == null) {
                    // 銘柄マスタ取得エラー(errors.fnd.selectedBrand.noInformation)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            ERRORS_NOINFORMATION,
                            IfaCommonUtil.getMessage(ERRORS_NOINFORMATION));
                    return dtoRes;
                } else {

                    IfaMutualFundAccumulateSettingBrandListSql001ResponseModel sql001ResponseModel = mFCode2MFNricodeMap.get(dto.getFundCode());

                    mfkaisu = Optional.ofNullable(sql001ResponseModel.getMfkaisu()).orElse("");
                    mfgo = " " + Optional.ofNullable(sql001ResponseModel.getMfgo()).orElse("").trim();
                    nriCd = mfkaisu + mfgo;
                }

                dto.setMfkaisu(mfkaisu);
                dto.setMfgo(mfgo);

                String detailID = dto.getMfkaisu()+"#"+ dto.getMfgo()+"#"+dto.getFundCode()+"#"+dto.getAccountType()+"#"+dto.getPaymentMethod();
                dto.setId(detailID);

                if (Fct001.TRADE_SUSPEND_FLAG_0.equals(outFct001Dto.getTradeSuspendFlag()) && getMediateProprietyFromOutputFct003Dto(outFct003Dto)) {
                    if (nriCd != null && nriCd.length() > 0) {
                        /**
                         * ■FCT001.取引停止フラグ =""0""（取引停止口座ではない)かつ
                         *    FCT003.媒介可否リスト.媒介可否=""1""(媒介可)かつ
                         *    FCT017.銘柄リスト.NRIコード.書類コード.受入要否=不要の場合
                         *    ""1""(可能)
                         * ■上記以外
                         *    ""2""(不可)
                         */
                        if (StringUtils.equals(nriCd2AcceptanceNecessityMap.get(nriCd),
                                AcceptanceNecessiy.UNNECESSARY.getValue())) {
                            dto.setAccumulateAvailability("1");
                        }

                        /**
                         * ■FCT001.取引停止フラグ =""0""（取引停止口座ではない)かつ
                         *    FCT003.媒介可否リスト.媒介可否=""1""(媒介可)かつ
                         *    FCT017.銘柄リスト.NRIコード.書類コード.受入要否=不要かつ
                         *    API003.積立設定リスト.決済方法＝”CASH”(現金)の場合
                         *    ""1""(可能)
                         * ■上記以外
                         *    ""2""(不可)
                         */
                        if (StringUtils.equals(nriCd2AcceptanceNecessityMap.get(nriCd),
                                AcceptanceNecessiy.UNNECESSARY.getValue())
                                && SafeType2IfaTypeUtil.IfaPaymentMethod.CASH.getValue()
                                        .equals(dto.getPaymentMethod())) {
                            dto.setAccumulateChangeAvailability("1");
                        }
                    }
                }
            }

            a002ResponseDto.setDetailList(dtoDetails);

        } else {
            a002ResponseDto.setDetailList(null);
        }

        if (resDtoList != null) {
            resDtoList.clear();
        }

        resDtoList.add(a002ResponseDto);

        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }

    /**
    
     * Dto リクエスト：IfaMutualFundAccumulateSettingBrandListA003RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingBrandListA003ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA003ResponseDto> addA003(
            IfaMutualFundAccumulateSettingBrandListA003RequestDto dtoReq) throws Exception {

        DataList<IfaMutualFundAccumulateSettingBrandListA003ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingBrandListA003ResponseDto>();
        List<IfaMutualFundAccumulateSettingBrandListA003ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingBrandListA003ResponseDto>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingBrandListServiceImplL.addA003");
        }

        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String kouzaNumber = customerCommon.getAccountNumber();

        // 投信閲覧履歴一括照会。 API004
        // Safe Api: /safe/fundTrade/fund/fund_doc_read_history/bulk
        GetFundFundDocReadHistoryBulkReq safeApi004Req = new GetFundFundDocReadHistoryBulkReq();
        safeApi004Req.getHeader().setToken(SafeApiUtil.getToken(butenCode,kouzaNumber));

        FundDocReadHistoryBulkApiIn fundDocReadHistoryBulkApiIn = new FundDocReadHistoryBulkApiIn();
        fundDocReadHistoryBulkApiIn.setDataOutputKbn("1"); // 1：全ての目論見書出力

        FundDocReadHistoryBulkItem fundDocReadHistoryBulkItem = new FundDocReadHistoryBulkItem();
        fundDocReadHistoryBulkItem.setFundType(" "); // △:未入力、口数/金額の一括取得
        fundDocReadHistoryBulkItem.setFundCode(dtoReq.getFundCode()); // リクエスト.協会コード
        List<FundDocReadHistoryBulkItem> fundsList = new ArrayList<>();
        fundsList.add(fundDocReadHistoryBulkItem);
        fundDocReadHistoryBulkApiIn.setFunds(fundsList);
        safeApi004Req.setParameter(fundDocReadHistoryBulkApiIn);
        GetFundFundDocReadHistoryBulkRes safeApi004Res = null;

        try {
            safeApi004Res = safeFundTradeService.getBulkFundDocReadHistory(safeApi004Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        FundDocReadHistoryBulkApiOut fundDocReadHistoryBulkApiOut = safeApi004Res.getFundDocReadHistoryBulkApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, fundDocReadHistoryBulkApiOut);

        boolean prospectus = false;
        if (fundDocReadHistoryBulkApiOut != null && fundDocReadHistoryBulkApiOut.getFunds() != null) {
            List<FundDocReadHistoryBulk> funds = fundDocReadHistoryBulkApiOut.getFunds();

            if (funds.size() > 0) {
                for(FundDocReadHistoryBulk fund : funds) {
                    if ("2".equals(fund.getFundType()) && "0".equals(fund.getProspectus()) && dtoReq.getFundCode().equals(fund.getFundCode())) {
                        prospectus = true;
                    }
                }
            }
        }

        if (!prospectus) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    ERRORS_FND_SELECTEDBRAND_NOISSUANCERECORD,
                    IfaCommonUtil.getMessage(ERRORS_FND_SELECTEDBRAND_NOISSUANCERECORD));
            return dtoRes;
        }

        IfaMutualFundAccumulateSettingBrandListA003ResponseDto a003ResponseDto = new IfaMutualFundAccumulateSettingBrandListA003ResponseDto();
        a003ResponseDto.setFundCode(dtoReq.getFundCode());
        a003ResponseDto.setMfkaisu(dtoReq.getMfkaisu());
        a003ResponseDto.setMfgo(dtoReq.getMfgo());

        resDtoList.add(a003ResponseDto);

        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, RETURN_CODE_SUCCESS,
                RETURN_MESSEAGE_SUCCESS);
        return dtoRes;
    }

    /**
    
     * Dto リクエスト：IfaMutualFundAccumulateSettingBrandListA004RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingBrandListA004ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA004ResponseDto> changeA004(
            IfaMutualFundAccumulateSettingBrandListA004RequestDto dtoReq) throws Exception {

        //ApiErrorUtil apiErrorUtil = new ApiErrorUtil();

        DataList<IfaMutualFundAccumulateSettingBrandListA004ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingBrandListA004ResponseDto>();
        List<IfaMutualFundAccumulateSettingBrandListA004ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingBrandListA004ResponseDto>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingBrandListServiceImplL.changeA004");
        }

        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String kouzaNumber = customerCommon.getAccountNumber();

        // 投信閲覧履歴一括照会。 API004
        // Safe Api: /safe/fundTrade/fund/fund_doc_read_history/bulk
        GetFundFundDocReadHistoryBulkReq safeApi004Req = new GetFundFundDocReadHistoryBulkReq();
        safeApi004Req.getHeader().setToken(SafeApiUtil.getToken(butenCode,kouzaNumber));

        FundDocReadHistoryBulkApiIn fundDocReadHistoryBulkApiIn = new FundDocReadHistoryBulkApiIn();
        fundDocReadHistoryBulkApiIn.setDataOutputKbn("1"); // 1：全ての目論見書出力

        FundDocReadHistoryBulkItem fundDocReadHistoryBulkItem = new FundDocReadHistoryBulkItem();
        fundDocReadHistoryBulkItem.setFundType(" "); // △:未入力、口数/金額の一括取得
        fundDocReadHistoryBulkItem.setFundCode(dtoReq.getFundCode()); // リクエスト.協会コード
        List<FundDocReadHistoryBulkItem> fundsList = new ArrayList<>();
        fundsList.add(fundDocReadHistoryBulkItem);
        fundDocReadHistoryBulkApiIn.setFunds(fundsList);
        safeApi004Req.setParameter(fundDocReadHistoryBulkApiIn);
        GetFundFundDocReadHistoryBulkRes safeApi004Res = null;

        try {
            safeApi004Res = safeFundTradeService.getBulkFundDocReadHistory(safeApi004Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        FundDocReadHistoryBulkApiOut fundDocReadHistoryBulkApiOut = safeApi004Res.getFundDocReadHistoryBulkApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, fundDocReadHistoryBulkApiOut);

        boolean prospectus = false;
        if (fundDocReadHistoryBulkApiOut != null && fundDocReadHistoryBulkApiOut.getFunds() != null) {
            List<FundDocReadHistoryBulk> funds = fundDocReadHistoryBulkApiOut.getFunds();

            if (funds.size() > 0) {
                for(FundDocReadHistoryBulk fund : funds) {
                    if ("2".equals(fund.getFundType()) && "0".equals(fund.getProspectus()) && dtoReq.getFundCode().equals(fund.getFundCode())) {
                        prospectus = true;
                    }
                }
            }
        }

        if (!prospectus) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    ERRORS_FND_SELECTEDBRAND_NOISSUANCERECORD,
                    IfaCommonUtil.getMessage(ERRORS_FND_SELECTEDBRAND_NOISSUANCERECORD));
            return dtoRes;
        }

        IfaMutualFundAccumulateSettingBrandListA004ResponseDto a004ResponseDto = new IfaMutualFundAccumulateSettingBrandListA004ResponseDto();
        a004ResponseDto.setFundCode(dtoReq.getFundCode());
        a004ResponseDto.setMfkaisu(dtoReq.getMfkaisu());
        a004ResponseDto.setMfgo(dtoReq.getMfgo());
        a004ResponseDto.setAccountType(dtoReq.getAccountType());
        a004ResponseDto.setPaymentMethod(dtoReq.getPaymentMethod());

        resDtoList.add(a004ResponseDto);

        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, RETURN_CODE_SUCCESS,
                RETURN_MESSEAGE_SUCCESS);
        return dtoRes;
    }

    /**
    
     * Dto リクエスト：IfaMutualFundAccumulateSettingBrandListA007RequestDto
     * Dto レスポンス：IfaMutualFundAccumulateSettingBrandListA007ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingBrandListA007ResponseDto> bulkChangeA007(
            IfaMutualFundAccumulateSettingBrandListA007RequestDto dtoReq) throws Exception {

        DataList<IfaMutualFundAccumulateSettingBrandListA007ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingBrandListA007ResponseDto>();
        List<IfaMutualFundAccumulateSettingBrandListA007ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingBrandListA007ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingBrandListServiceImplL.bulkChangeA007");
        }

        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String kouzaNumber = customerCommon.getAccountNumber();

        // 投信閲覧履歴一括照会。 API004
        // Safe Api: /safe/fundTrade/fund/fund_doc_read_history/bulk
        GetFundFundDocReadHistoryBulkReq safeApi004Req = new GetFundFundDocReadHistoryBulkReq();
        safeApi004Req.getHeader().setToken(SafeApiUtil.getToken(butenCode,kouzaNumber));

        FundDocReadHistoryBulkApiIn fundDocReadHistoryBulkApiIn = new FundDocReadHistoryBulkApiIn();
        fundDocReadHistoryBulkApiIn.setDataOutputKbn("1"); // 1：全ての目論見書出力

        List<IfaMutualFundAccumulateSettingBulkChangeTargetDetail> changeTargetAccumulateSettingDetailList = dtoReq
                .getChangeTargetAccumulateSettingList();

        List<FundDocReadHistoryBulkItem> fundsList = new ArrayList<>();
        List<FundDocReadHistoryBulkItem> nextTimeFundsList = null;
        Map<String, String> entryKeyMap = new HashMap<>();
        boolean nextTimeCallApi004 = false;

        if (!CollectionUtils.isEmpty(changeTargetAccumulateSettingDetailList)) {

            int callApiMaxParamCnt = 0;
            FundDocReadHistoryBulkItem fundDocReadHistoryBulkItem = null;

            for (IfaMutualFundAccumulateSettingBulkChangeTargetDetail detail : changeTargetAccumulateSettingDetailList) {
                String mfkaisu = detail.getMfkaisu();
                String mfgo = detail.getMfgo();
                String fundCode = detail.getFundCode();

                String brandCode = mfkaisu + "." + mfgo;

                if (!entryKeyMap.containsKey(fundCode)) {
                    // 協会コード : 銘柄コード
                    entryKeyMap.put(fundCode, brandCode);

                    fundDocReadHistoryBulkItem = new FundDocReadHistoryBulkItem();
                    fundDocReadHistoryBulkItem.setFundType(" "); // △:未入力、口数/金額の一括取得
                    fundDocReadHistoryBulkItem.setFundCode(fundCode); // リクエスト.協会コード

                    callApiMaxParamCnt++;

                    // 同時に設定するのは15件まで。16件以上は、再度API取得。
                    if (callApiMaxParamCnt <= 15) {
                        fundsList.add(fundDocReadHistoryBulkItem);
                    } else {

                        if (nextTimeFundsList == null) {
                            nextTimeCallApi004 = true;
                            nextTimeFundsList = new ArrayList<>();
                        }
                        nextTimeFundsList.add(fundDocReadHistoryBulkItem);
                    }
                }
            }

            fundDocReadHistoryBulkApiIn.setFunds(fundsList);
        }

        safeApi004Req.setParameter(fundDocReadHistoryBulkApiIn);
        GetFundFundDocReadHistoryBulkRes safeApi004Res = null;

        try {
            safeApi004Res = safeFundTradeService.getBulkFundDocReadHistory(safeApi004Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        FundDocReadHistoryBulkApiOut fundDocReadHistoryBulkApiOut = safeApi004Res.getFundDocReadHistoryBulkApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, fundDocReadHistoryBulkApiOut);

        if (fundDocReadHistoryBulkApiOut != null && fundDocReadHistoryBulkApiOut.getFunds() != null) {
            List<FundDocReadHistoryBulk> funds = fundDocReadHistoryBulkApiOut.getFunds();

            if (funds.size() > 0) {
                for(FundDocReadHistoryBulk fund : funds) {

                    if ("2".equals(fund.getFundType()) && "0".equals(fund.getProspectus())) {
                        String matchKeyByFundCode = fund.getFundCode();

                        if(entryKeyMap.containsKey(matchKeyByFundCode)) {
                            entryKeyMap.remove(matchKeyByFundCode);
                        }
                    }
                }
            }
        }

        // 16件以上は、再度API取得。
        if (nextTimeCallApi004) {
            fundDocReadHistoryBulkApiIn.setFunds(nextTimeFundsList);
            safeApi004Req.setParameter(fundDocReadHistoryBulkApiIn);

            try {
                safeApi004Res = safeFundTradeService.getBulkFundDocReadHistory(safeApi004Req);
            } catch (Exception e) {
                return safeCommonService.checkSafeBussinessException(resDtoList, e);
            }

            fundDocReadHistoryBulkApiOut = safeApi004Res.getFundDocReadHistoryBulkApiOut();
            dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, fundDocReadHistoryBulkApiOut);

            if (fundDocReadHistoryBulkApiOut != null && fundDocReadHistoryBulkApiOut.getFunds() != null) {
                List<FundDocReadHistoryBulk> funds = fundDocReadHistoryBulkApiOut.getFunds();

                if (funds.size() > 0) {
                    for(FundDocReadHistoryBulk fund : funds) {

                        if ("2".equals(fund.getFundType()) && "0".equals(fund.getProspectus())) {
                            String matchKeyByFundCode = fund.getFundCode();

                            if(entryKeyMap.containsKey(matchKeyByFundCode)) {
                                entryKeyMap.remove(matchKeyByFundCode);
                            }
                        }
                    }
                }
            }
        }

        if (!entryKeyMap.isEmpty()) {

            String messageAppendStr = IfaMutualFundAccumulateSettingUtil.getBrandCodesStrByList(entryKeyMap, "VAL", "ASC");

            /**
             * 目論見書の交付記録がないため取引はできません。 
             * <br>銘柄コード:[{0}]、[{1}]、・・・・・・・・・ [{19}]
             *  [{0}]～[{19}]：画面.銘柄コード(エラー分のみ)
             * ※銘柄コードを順番にセット(銘柄コードが重複した場合はスキップ)
             * 
             */
            // errors.fnd.selectedBrands.noIssuanceRecord
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    ERRORS_FND_SELECTEDBRAND_NOISSUANCERECORD, IfaCommonUtil
                            .getMessage(ERRORS_FND_SELECTEDBRANDS_NOISSUANCERECORD, new String[] { messageAppendStr }));
            return dtoRes;
        }

        IfaMutualFundAccumulateSettingBrandListA007ResponseDto a007ResponseDto = new IfaMutualFundAccumulateSettingBrandListA007ResponseDto();
        a007ResponseDto.setChangeTargetAccumulateSettingList(dtoReq.getChangeTargetAccumulateSettingList());

        resDtoList.add(a007ResponseDto);

        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, RETURN_CODE_SUCCESS,
                RETURN_MESSEAGE_SUCCESS);
        return dtoRes;
    }

    /**
     * FCT003の媒介可否リストをもとに媒介可否を取得する
     *
     * @param fundType 買付可能な明細のファンドタイプ
     * @param outFct003Dto 取引コースの媒介可否リスト
     * @return 媒介可否(false:媒介不可、true:媒介可)
    
     */
    private boolean getMediateProprietyFromOutputFct003Dto(OutputFct003Dto outFct003Dto) {
        return StringUtils.equals(outFct003Dto.getMediateAbleTradeFlag(), MEDIATE_PROPRIETY_ABLE);
    }

}

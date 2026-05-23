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
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct017;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto.InquiryMutualFundBrand;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto.Brand;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBonusMonthSumAmountInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeConfirmData;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeData;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeInputData;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingBulkChangeTargetDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingBulkChangeInputService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaMutualFundAccumulateSettingUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.common.exception.dto.ErrorInfo;
import com.sbisec.helios.ap.safe.dao.model.IfaSafeErrorMessageSql001ResponseModel;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingReq;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveGetReserveSettingForBulkUpdateReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveGetReserveSettingForBulkUpdateRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingBulkChangeConfirmReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingBulkChangeConfirmRes;
import com.sbisec.helios.ap.safe.service.SafeAccountService;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.account.dto.ReserveBuySettingGetApiIn;
import com.sbisec.helios.ap.safe.service.account.dto.ReserveBuySettingGetApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ChangeReserveSettingInfo;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBaseOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBulkChangeConfirmApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBulkChangeConfirmApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSumAmount;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingChangeInfo;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingData;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataForBulkUpdate;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListForBulkUpdateApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingErrorInfo;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingSummaryApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingSummaryApiOut;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;
import com.sbisec.helios.ap.safe.utils.SafeType2IfaTypeUtil;

/**
 * 画面ID：SUB0202_0403-03_1
 * 画面名：投信積立設定変更入力
 *
 * @author nicksen.li
 */
@Component(value = "cmpIfaMutualFundAccumulateSettingBulkChangeInputService")
public class IfaMutualFundAccumulateSettingBulkChangeInputServiceImpL implements IfaMutualFundAccumulateSettingBulkChangeInputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMutualFundAccumulateSettingInputServiceImpL.class);

    @Autowired
    private CodeListService codelistservice;

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct006 fct006;

    @Autowired
    private Fct003 fct003;

    @Autowired
    private Fct017 fct017;

    @Autowired
    private Fct021 fct021;

    @Autowired
    private SafeAccountService safeAccountService;
    
    @Autowired
    private SafeFundTradeService safeFundTradeService;

    @Autowired
    private SafeCommonService safeCommonService;

    @Autowired
    private CodeListService codeListService;

    /** 権限なし */
    private static final String NO_AUTHORITY = "0";

    /** 対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";

    /** 取引停止口座 */
    private static final String STOP_TRADE_ACCOUNT = "1";

    /** 区分値.証券金銭種別_国内投信 */
    private static final String DOMESTIC_MUTUALFUND = "06";

    /** 国内投信:取引種別 "2"：購入（積立） */
    private static final String MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE = "2";

    /** なし */
    private static final String NONE = "0";

    /** 受入要否.不要 */
    private static final String ACCEPTANCE_FALSE = "0";

    /** 投信銘柄種別.通貨選択型 */
    private static final String BRAND_CLASS_SELECT = "1";

    /** 投信銘柄種別.複雑投信*/
    private static final String BRAND_CLASS_COMPLICATED = "2";

    /** "1" */
    private static final String ONE = "1";

    /** 国籍コード */
    private static final String NATION_CODE = "99";

    /** 通貨コード */
    private static final String CURRENCY_CODE = "999";

    /** "0":国内 */
    private static final String DOMESTIC = "0";

    /** "3△":投信 */
    private static final String MUTUALFUND = "3 ";

    /** "1":買付注文 */
    private static final String PURCHASE_ORDER = "1";

    /** 取引停止口座 */
    private static final String SUSPENSION_ACCOUNT = "1";

    /** FUND（投資信託）：'2' を設定 */
    private static final String PRODUCTTYPE_FUND = "FUND";

    // 判定結果
    private enum JudgementResult {

        // ノーマル
        NORMAL("0"),
        // アラート
        ALERT("1"),
        // エラー
        ERROR("2");

        private String key;

        private JudgementResult(String key) {
            this.key = key;
        }
    }

    // メッセージID
    private enum MessageId {

        // errors.butenAccountNotExist
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.selectedAccountCourse.unavailable
        ERRORS_SELECTEDACCOUNT_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        // errors.fnd.selectedBrands.currencySelectionTypeComplexType
        ERRORS_CURRENCYSELECTIONTYPE("errors.fnd.selectedBrands.currencySelectionTypeComplexType"),
        // errors.fnd.selectedBrands.complexType
        ERRORS_COMPLEXTYPE("errors.fnd.selectedBrands.complexType"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICEERRORCHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATIONCHECK("errors.informationCheck"),
        // warnings.cmn.noticeWarningCheck
        WRANINGS_NOTICEWARNINGCHECK("warnings.cmn.noticeWarningCheck"),
        // warnings.cmm.informationCheck
        WARNINGS_INFORMATIONCHECK("warnings.cmm.informationCheck"),
        // warnings.fndReserve.confirmationCheck
        WARNINGS_FNDRESERVE_CONFIRMATIONCHECK("warnings.fndReserve.confirmationCheck"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CCSIDUNREGISTERED("errors.cmn.ccsid.unregistered"),
        // errors.cmn.notSettingFund
        ERRORS_NOTSETTINGFUND("errors.cmn.notSettingFund"),
        // errors.cmn.settingExecutionChange.failed
        ERRORS_SETTINGEXECUTIONCHANGE_FAILED("errors.cmn.settingExecutionChange.failed"),
        // errors.cmn.settingExecutionChanges.failed  【{0}△{1}】<br>エラーコード：{2}<br>エラーメッセージ：{3}
        ERRORS_SETTINGEXECUTIONCHANGES_FAILED("errors.cmn.settingExecutionChanges.failed"),
        // errors.cmn.settingExecutionChanges.failed#2  エラーコード：{0}<br>エラーメッセージ：{1}
        ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2("errors.cmn.settingExecutionChanges.failed#2");

        private String key;

        private MessageId(String key) {

            this.key = key;
        }
    }

    @Override
    public DataList<IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto> initializeA001(
            IfaMutualFundAccumulateSettingBulkChangeInputA001RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingBulkChangeInputServiceImpL.initializeA001");
        }

        DataList<IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto>();
        List<IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto>();

        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto a001ResponseDto = new IfaMutualFundAccumulateSettingBulkChangeInputA001ResponseDto();

        List<IfaMutualFundAccumulateSettingBulkChangeTargetDetail> changeTargetAccumulateSettingList = dtoReq.getChangeTargetAccumulateSettingList();
        Map<String, String> fundCode2BrandCodeMap = new HashMap<>();
        Map<String, String> nriCode2BrandCodeMap = new HashMap<>();

        /**
         * ① 利用者の口座に対する権限チェックを行う。 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
         * 取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。 上記以外：次の処理へ
         */
        // ①利用者の口座に対する権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 == "0"(権限なし)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // レスポンスに権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT001.取引停止フラグ == "1"(取引停止口座)の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), SUSPENSION_ACCOUNT)) {
            // レスポンスに取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUTOFSERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFSERVICE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }

        /**
         * ② 取引コース媒介可否チェックを行う。 取引可：次の処理へ。
         * 上記以外：取引不可エラーを返す。
         */
        // ③取引コース媒介可否チェックを行う。（FCT003）
        // 以下を入力値としてFCT003を実行
        InputFct003Dto fct003Req = new InputFct003Dto();
        // ・顧客共通情報.部店コード
        fct003Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // ・証券金銭種別="06"(国内投信)
        fct003Req.setProductCd(DOMESTIC_MUTUALFUND);
        // ・レスポンス.取引種別
        fct003Req.setTradeCd(MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE);
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // if(媒介可取引有無 = "0"(なし)){
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), NONE)) {
            // 取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "10", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, IfaCommonUtil
                            .getMessage(MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, new String[] { codeName }));
            return dtoRes;
        }

        /**
         * ③ 通貨選択型投信 または 複雑型投信チェックを行う。 銘柄リスト.NRIコード.書類コード.受入要否=不要：次の処理へ。
         * 銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型：エラーを返す。
         * 銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信：エラーを返す。
         */
        // 以下を入力値としてFCT017を実行
        InputFct017Dto fct017Req = new InputFct017Dto();
        // ・顧客共通情報.部店コード
        fct017Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct017Req.setAccountNumber(cc.getAccountNumber());
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        List<InquiryMutualFundBrand> inquiryMutualFundBrandList = new ArrayList<>();

        InquiryMutualFundBrand inquiryMutualFundBrand = null;
        for (IfaMutualFundAccumulateSettingBulkChangeTargetDetail changeDataInfo : changeTargetAccumulateSettingList) {
            String brandCode = changeDataInfo.getMfkaisu() + "." + changeDataInfo.getMfgo();
            String nriCode = changeDataInfo.getMfkaisu() + changeDataInfo.getMfgo();

            // API003レスポンスの積立設定リスト 「銘柄コード」 を利用必要です。
            fundCode2BrandCodeMap.put(changeDataInfo.getFundCode(), brandCode);

            nriCode2BrandCodeMap.put(nriCode, brandCode);

            // FCT017を利用必要です。
            inquiryMutualFundBrand = new InquiryMutualFundBrand();
            inquiryMutualFundBrand.setNriCd(nriCode);
            inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
        }

        fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
        OutputFct017Dto fct017Res = new OutputFct017Dto();
        fct017Res = fct017.getData(fct017Req);

        if (!ObjectUtils.isEmpty(fct017Res.getBrandList())) {
            Map<String, String> brandClassSelectMap = new HashMap<>();
            Map<String, String> brandClassComplicatedMap = new HashMap<>();

            for (Brand brand : fct017Res.getBrandList()) {
                // if(銘柄リスト.NRIコード.書類コード.受入要否=不要){
                if (StringUtils.equals(brand.getAcceptanceNecessity(), ACCEPTANCE_FALSE)) {
                    continue;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_SELECT) && brand.getNriCd() != null) {
                    brandClassSelectMap.put(brand.getNriCd(), nriCode2BrandCodeMap.get(brand.getNriCd()));
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_COMPLICATED) && brand.getNriCd() != null) {
                    brandClassComplicatedMap.put(brand.getNriCd(), nriCode2BrandCodeMap.get(brand.getNriCd()));
                }
            }

            if (!brandClassSelectMap.isEmpty()) {
                // 銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合
                // 強制注文対象エラー(errors.fnd.selectedBrand.currencySelectionType)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CURRENCYSELECTIONTYPE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CURRENCYSELECTIONTYPE.key,
                                new String[] { IfaMutualFundAccumulateSettingUtil
                                        .getBrandCodesStrByList(brandClassSelectMap, "VAL", "ASC") }));
                return dtoRes;
            }

            if (!brandClassComplicatedMap.isEmpty()) {
                // 銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合
                // 強制注文対象エラー(errors.fnd.selectedBrand.complexType)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_COMPLEXTYPE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_COMPLEXTYPE.key,
                                new String[] { IfaMutualFundAccumulateSettingUtil
                                        .getBrandCodesStrByList(brandClassComplicatedMap, "VAL", "ASC") }));
                return dtoRes;
            }
        }

        /**
         * ④ 積立設定サマリを取得する。 
         * 積立設定サマリの取得APIの呼び出し 
         */
        // /safe/fundTrade/fund/reserve/setting_data_summary
        GetFundReserveSettingDataSummaryReq api001Req = new GetFundReserveSettingDataSummaryReq();
        api001Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        api001Req.setParameter(new ReserveSettingSummaryApiIn());
        GetFundReserveSettingDataSummaryRes api001Res = null;
        try {
            api001Res = safeFundTradeService.getSettinSummary(api001Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveSettingSummaryApiOut reserveSettingSummaryApiOut = api001Res.getReserveSettingSummaryApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveSettingSummaryApiOut);

        IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneMonthSumAmount = new IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount();
        // >1ヶ月あたりの積立金額の概算 API003 1カ月あたりの積立金額（概算）
        // oneMonthSumAmount 1カ月あたりの積立金額（概算）
        FundReserveSumAmount oneMonthSumAmountObj = reserveSettingSummaryApiOut.getOneMonthSumAmount();
        // > 決済方法毎の金額 現金 API003 積立金額
        // settingAmount 積立金額
        oneMonthSumAmount.setSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > 決済方法毎の金額 クレジットカード API003 現金決済積立金額
        // cashSettingAmount 現金決済積立金額
        oneMonthSumAmount.setCashSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getCashSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > 預り区分毎の金額 特定/一般 API003 クレカ決済積立金額
        // creditCardSettingAmount クレカ決済積立金額
        oneMonthSumAmount.setCreditCardSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getCreditCardSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > 預り区分毎の金額 NISA成長投資枠 API003 特定／一般積立金額
        // normalSettingAmount 特定／一般積立金額
        oneMonthSumAmount.setNormalSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNormalSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > 預り区分毎の金額 NISAつみたて投資枠 API003 NISA（成長投資枠）積立金額
        // nisaGrowthSettingAmount NISA（成長投資枠）積立金額
        oneMonthSumAmount.setNisaGrowthSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNisaGrowthSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > NISAつみたて投資枠の限度額 API003 NISA（つみたて投資枠）積立金額
        // nisaReserveSettingAmount NISA（つみたて投資枠）積立金額
        oneMonthSumAmount.setNisaReserveSettingAmount(Optional.ofNullable(oneMonthSumAmountObj)
                .map(FundReserveSumAmount::getNisaReserveSettingAmount).orElse(new BigDecimal(0)).toPlainString());

        IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount oneYearSumAmount = new IfaMutualFundAccumulateSettingBrandListFundReserveSumAmount();
        // >1年あたりの積立金額の概算 API003 1年あたりの積立金額（概算）
        // oneYearSumAmount 1年あたりの積立金額（概算）
        FundReserveSumAmount oneYearSumAmountObj = reserveSettingSummaryApiOut.getOneYearSumAmount();
        // > 決済方法毎の金額 現金 API003 積立金額
        // settingAmount 積立金額
        oneYearSumAmount.setSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > 決済方法毎の金額 クレジットカード API003 現金決済積立金額
        // cashSettingAmount 現金決済積立金額
        oneYearSumAmount.setCashSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getCashSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > 預り区分毎の金額 特定/一般 API003 クレカ決済積立金額
        // creditCardSettingAmount クレカ決済積立金額
        oneYearSumAmount.setCreditCardSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getCreditCardSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > 預り区分毎の金額 NISA成長投資枠 API003 特定／一般積立金額
        // normalSettingAmount 特定／一般積立金額
        oneYearSumAmount.setNormalSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNormalSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > 預り区分毎の金額 NISAつみたて投資枠 API003 NISA（成長投資枠）積立金額
        // nisaGrowthSettingAmount NISA（成長投資枠）積立金額
        oneYearSumAmount.setNisaGrowthSettingAmount(Optional.ofNullable(oneYearSumAmountObj)
                .map(FundReserveSumAmount::getNisaGrowthSettingAmount).orElse(new BigDecimal(0)).toPlainString());
        // > NISAつみたて投資枠の限度額 API003 NISA（つみたて投資枠）積立金額
        // nisaReserveSettingAmount NISA（つみたて投資枠）積立金額
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

        // >ボーナス月設定金額 API003 ボーナス月設定の合計金額
        // bonusSumAmount ボーナス月設定の合計金額
        a001ResponseDto.setBonusSumAmount(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmount())
                .orElse(new BigDecimal(0)).toPlainString());

        // NISA（つみたて投資枠）ボーナス月設定の合計金額
        a001ResponseDto.setBonusSumAmountNisaReserve(Optional.ofNullable(reserveSettingSummaryApiOut.getBonusSumAmountNisaReserve())
                .orElse(new BigDecimal(0)).toPlainString());

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

        /**
         * ⑤ ポイント_積立買付利用設定を取得する。 
         * ポイント_積立買付利用設定の取得APIの呼び出し 
         * データあり：次の処理へ。
         * データなし：ポイント_積立買付利用設定取得エラーを返す。 
         * W0002 
         * {0}情報の取得ができませんでした。<br>部店コード:[{1}] 口座番号:[{2}]
         * {0}：ポイント_積立買付利用設定 {1}：部店コード {2}：口座番号"
         */
        // /safe/account/account/point/get_reserve_buy_setting
        GetAccountPointGetReserveBuySettingReq api002Req = new GetAccountPointGetReserveBuySettingReq();
        api002Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        ReserveBuySettingGetApiIn api002ApiIn = new ReserveBuySettingGetApiIn();
        api002ApiIn.setProductType(PRODUCTTYPE_FUND);
        api002Req.setParameter(api002ApiIn);
        GetAccountPointGetReserveBuySettingRes api002Res = null;
        try {
            api002Res = safeAccountService.getReserveBuySetting(api002Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveBuySettingGetApiOut reserveBuySettingGetApiOut = api002Res.getReserveBuySettingGetApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveBuySettingGetApiOut);

        // ■ポイント利用設定が”利用ポイント指定”の場合 "毎月の利用上限："+ポイント利用上限+"ポイント"
        /**
            ■ポイント利用設定が”利用ポイント指定”の場合
            　"毎月の利用上限："+ポイント利用上限+"ポイント"
            ■ポイント利用設定が”全ポイント利用”の場合
            　”すべての利用可能ポイントを使う”
            ■ポイント利用設定が”設定登録なし”の場合
            　"利用しない"
            ■上記以外（ポイント利用設定の取得時にエラーが発生した場合も）
            　”確認できません”
         */
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

        /**
         * ⑥ 積立設定一覧(変更用)を取得する。
         */
        // ★★★積立設定一覧(変更用)を取得する。★★★
        // Safe Api: /safe/fundTrade/fund/reserve/get-reserve-setting-for-bulk-update
        GetFundReserveGetReserveSettingForBulkUpdateReq safeApi003Req = new GetFundReserveGetReserveSettingForBulkUpdateReq();
        safeApi003Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        ReserveSettingDataListForBulkUpdateApiIn reserveSettingDataListForBulkUpdateApiIn = new ReserveSettingDataListForBulkUpdateApiIn();
        List<ReserveSettingDataForBulkUpdate> reserveOrderList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(changeTargetAccumulateSettingList)) {
            ReserveSettingDataForBulkUpdate safaApiInput = null;
            for (IfaMutualFundAccumulateSettingBulkChangeTargetDetail inputDataFromPage : changeTargetAccumulateSettingList) {
                safaApiInput = new ReserveSettingDataForBulkUpdate();
                // リクエスト.預り区分
                safaApiInput.setAccountType(SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum
                        .getSafeEnumByIfaValue(inputDataFromPage.getAccountType()));
                // リクエスト.協会コード
                safaApiInput.setFundCode(inputDataFromPage.getFundCode());
                // リクエスト.決済方法
                safaApiInput.setPaymentMethod(SafeType2IfaTypeUtil.IfaPaymentMethod
                        .getSafeEnumByIfaValue(inputDataFromPage.getPaymentMethod()));
                reserveOrderList.add(safaApiInput);
            }
        }

        reserveSettingDataListForBulkUpdateApiIn.setReserveOrderList(reserveOrderList);
        safeApi003Req.setParameter(reserveSettingDataListForBulkUpdateApiIn);
        GetFundReserveGetReserveSettingForBulkUpdateRes safeApi003Res = null;

        try {
            safeApi003Res = safeFundTradeService.getReserveSettingForBulkUpdate(safeApi003Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveSettingDataListApiOut reserveSettingDataListApiOut = safeApi003Res.getReserveSettingDataListApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveSettingDataListApiOut);

        /**
         * データあり：次の処理へ。
         * データなし：エラーを返す。
         *   errors.cmn.settingExecutionChange
         *   当該銘柄は積立変更できません。
         */
        if (safeApi003Res == null || reserveSettingDataListApiOut == null
                || reserveSettingDataListApiOut.getReserveOrderList() == null
                || reserveSettingDataListApiOut.getReserveOrderList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_NOTSETTINGFUND.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOTSETTINGFUND.key));
            return dtoRes;
        }

        List<ReserveSettingData> reserveSettingDataList = reserveSettingDataListApiOut.getReserveOrderList();

        a001ResponseDto.setOpenedJnisa(reserveSettingDataListApiOut.isOpenedJnisa() ? "1" : "0");

        List<IfaMutualFundAccumulateSettingBulkChangeData> bulkChangeList = new ArrayList<>();
        IfaMutualFundAccumulateSettingBulkChangeData bulkChangeData = null;
        for (ReserveSettingData reserveSettingData : reserveSettingDataList) {
            bulkChangeData = new IfaMutualFundAccumulateSettingBulkChangeData();

             // 銘柄コード: ファンドコード（回数） + ファンドコード（号）
            bulkChangeData.setBrandCode(Optional.ofNullable(fundCode2BrandCodeMap.get(reserveSettingData.getFundCode())).orElse(""));
            String[] MfkaisuAndMfgo = getMfkaisuAndMfgoFromBrandCode(bulkChangeData.getBrandCode());
            bulkChangeData.setMfkaisu(MfkaisuAndMfgo[0]);
            bulkChangeData.setMfgo(MfkaisuAndMfgo[1]);

             // 銘柄名
            bulkChangeData.setFundName(Optional.ofNullable(reserveSettingData.getFundName()).orElse(""));
             // 預り区分
            bulkChangeData.setAccountType(SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(reserveSettingData.getAccountType()));
             // NISA枠ぎりぎり買付区分
            if(IfaMutualFundAccumulateSettingUtil.isNisaBarelyBuyingTypeObject(bulkChangeData.getAccountType())) {
                /**
                    ■預り区分="H"、"I”の場合
                    　■NISA枠ぎりぎり買付区分＝"0"もしくはnullの場合
                    　　NISA枠ぎりぎり注文のラベルも含め表示しない
                    　■上記以外
                    　　@取得パターン:2
                    　　@表示パターン:2
                    ■上記以外
                    　　NISA枠ぎりぎり注文のラベルも含め表示しない
                 */
                String ifaNisaBarelyBuyingType = SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                        .getIfaValueBySafeEnum(reserveSettingData.getNisaBarelyBuyingType());
                if (ifaNisaBarelyBuyingType == null || "0".equals(ifaNisaBarelyBuyingType)) {
                    // ■上記以外
                    // "0”
                    bulkChangeData.setNisaBarelyBuyingType("0");
                } else {
                    // ■NISAぎりぎり注文チェック＝チェックありの場合
                    // ”2”
                    // ■NISAぎりぎり注文チェック＝チェックなしの場合
                    // ”1”
                    bulkChangeData.setNisaBarelyBuyingType(ifaNisaBarelyBuyingType);
                }
            }
             // NISA枠超過時買付区分
            if(IfaMutualFundAccumulateSettingUtil.isTaxShiftTypeObject(bulkChangeData.getAccountType())) {
                /**
                    ■預り区分="H"の場合
                    　■NISA枠超過時買付区分＝"0"もしくはnullの場合
                    　　課税枠シフト注文のラベルも含め表示しない
                    　■上記以外
                    　　@取得パターン:2
                    　　@表示パターン:2
                    ■上記以外
                    　課税枠シフト注文のラベルも含め表示しない
                 */
                String ifaTaxShiftType = SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                        .getIfaValueBySafeEnum(reserveSettingData.getTaxShiftType());
                if (ifaTaxShiftType == null || "0".equals(ifaTaxShiftType)) {
                    // ■上記以外
                    // "0”
                    bulkChangeData.setTaxShiftType("0");
                } else {
                    // ■課税枠シフト注文チェック＝チェックありの場合
                    // ”2”
                    // ■課税枠シフト注文文チェック＝チェックなしの場合
                    // ”1”
                    bulkChangeData.setTaxShiftType(ifaTaxShiftType);
                }
            }
             // 積立コース
            String courseType = reserveSettingData.getCourseType();
            if (courseType != null) {
                /**
                 * EVERY_DAY 1
                 * EVERY_WEEK 2
                 * EVERY_MONTH 3
                 * MULTIPLE_DAYS 4
                 * BIMONTHLY && ODD_MONTH 5
                 * BIMONTHLY && EVEN_MONTH 6
                 */
                bulkChangeData.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getIfaValueBySafeEnum(courseType,
                        reserveSettingData.getSettingReserveBimonthly()));
            }
             // 積立毎週設定
            String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                    .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveWeek());
            if (ifaSettingReserveWeek != null) {
                bulkChangeData.setSettingReserveWeek(ifaSettingReserveWeek);
            }
             // 積立毎月設定
            String ifaSettingReserveBimonthly = SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                    .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveBimonthly());
            if (ifaSettingReserveBimonthly != null) {
                //奇数月 1
                //偶数月 2
                bulkChangeData.setSettingReserveBimonthly(ifaSettingReserveBimonthly);
            }

            // 積立日付
            bulkChangeData.setSettingReserveDay(reserveSettingData.getSettingReserveDay());

             // 積立複数日設定
            if(reserveSettingData.getSettingReserveMultiDay() != null && reserveSettingData.getSettingReserveMultiDay().length() > 0) {
                bulkChangeData.setSettingReserveMultiDay(reserveSettingData.getSettingReserveMultiDay());
            }
             // 設定金額
            if (reserveSettingData.getSettingAmount() != null) {
                bulkChangeData.setSettingAmount(reserveSettingData.getSettingAmount().toPlainString());
            }
            /** ■ボーナス設定金額 > 0の場合、積立金額、設定日を表示 */
            /** ■上記以外、"設定なし"を表示 */
            if (reserveSettingData.getSettingBonusAmount() != null
                    && reserveSettingData.getSettingBonusAmount().compareTo(new BigDecimal(0)) == 1) {

                // ■ボーナス設定＝するの場合
                bulkChangeData.setSettingBonusFlag("1");

                // ボーナス設定金額
                bulkChangeData
                        .setSettingBonusAmount(Optional.ofNullable(reserveSettingData.getSettingBonusAmount())
                                .orElse(new BigDecimal(0)).toPlainString());

                // ボーナス１設定月
                if(reserveSettingData.getSettingBonus1Month() != null && reserveSettingData.getSettingBonus1Month().length() > 0) {
                    bulkChangeData.setSettingBonus1Month(reserveSettingData.getSettingBonus1Month());
                } else {
                    bulkChangeData.setSettingBonus1Month(null);
                }
                // ボーナス１設定日
                if(reserveSettingData.getSettingBonus1Day() != null && reserveSettingData.getSettingBonus1Day().length() > 0) {
                    bulkChangeData.setSettingBonus1Day(reserveSettingData.getSettingBonus1Day());
                } else {
                    bulkChangeData.setSettingBonus1Day(null);
                }

                // ボーナス２設定月
                if(reserveSettingData.getSettingBonus2Month() != null && reserveSettingData.getSettingBonus2Month().length() > 0) {
                    bulkChangeData.setSettingBonus2Month(reserveSettingData.getSettingBonus2Month());
                } else {
                    bulkChangeData.setSettingBonus2Month(null);
                }
                // ボーナス２設定日
                if(reserveSettingData.getSettingBonus2Day() != null && reserveSettingData.getSettingBonus2Day().length() > 0) {
                    bulkChangeData.setSettingBonus2Day(reserveSettingData.getSettingBonus2Day());
                } else {
                    bulkChangeData.setSettingBonus2Day(null);
                }
            } else {
                bulkChangeData.setSettingBonusFlag("2");
            }
             // 協会コード
            bulkChangeData.setFundCode(reserveSettingData.getFundCode());

            bulkChangeList.add(bulkChangeData);
        }

        // 積立設定リスト
        a001ResponseDto.setBulkChangeList(bulkChangeList);

        // レスポンスをコントローラーに返却する。
        BeanUtils.copyProperties(a001ResponseDto, dtoRes);

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

    @Override
    public DataList<IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto> settingChangeConfirmA005(
            IfaMutualFundAccumulateSettingBulkChangeInputA005RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingBulkChangeInputServiceImpL.settingChangeConfirmA005");
        }

        DataList<IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto>();
        List<IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto>();
        IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto a005Res = new IfaMutualFundAccumulateSettingBulkChangeInputA005ResponseDto();

        List<IfaMutualFundAccumulateSettingBulkChangeInputData> changeTargetAccumulateSettingList = dtoReq.getBulkChangeInputDataList();
        Map<String, String> fundCode2BrandCodeMap = new HashMap<>();
        Map<String, String> nriCode2BrandCodeMap = new HashMap<>();

        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 口座番号: 部店コード + "-" + 口座番号
        a005Res.setAccountNumber(cc.getButenCode() + "-" + org.apache.commons.lang3.StringUtils.leftPad(Optional.ofNullable(cc.getAccountNumber()).orElse(""), 7, "0"));
        // 個人・法人アイコン
        a005Res.setCorporationKbn(Optional.ofNullable(cc.getCorporationType()).orElse(""));
        // 顧客名: 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
        a005Res.setCustomerName(cc.getCustomerNameKanji() + "(" + cc.getCustomerNameKana() + ")");

        /**
        ①   利用者の口座に対する権限チェックを行う。
            対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
            取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。
            上記以外：次の処理へ
         */
        // ①利用者の口座に対する権限チェックを行う。
        // 以下を入力値としてFCT001を実行
        InputFct001Dto fct001Req = new InputFct001Dto();
        // ・顧客共通情報.部店コード
        fct001Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct001Req.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // if(FCT001.対象顧客参照権限有無＝"0"（権限なし）の場合)｛
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // 権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        // if(FCT001.取引停止フラグ＝"1"（取引停止口座））｛
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), STOP_TRADE_ACCOUNT)) {
            // 取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUTOFSERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFSERVICE.key));
            return dtoRes;
        }

        /**
        ②   取引コース媒介可否チェックを行う。
            取引可：次の処理へ。
            上記以外：取引不可エラーを返す。
         */
        // ②取引コース媒介可否チェックを行う。
        // 以下を入力値としてFCT003を実行
        InputFct003Dto fct003Req = new InputFct003Dto();
        // ・顧客共通情報.部店コード
        fct003Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // ・証券金銭種別="06"(国内投信)
        fct003Req.setProductCd(DOMESTIC_MUTUALFUND);
        // ・レスポンス.取引種別
        fct003Req.setTradeCd(MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE);

        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // if(媒介可取引有無 = "0"(なし)){
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), NONE)) {
            // 取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "10", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, IfaCommonUtil
                            .getMessage(MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, new String[] { codeName }));
            return dtoRes;
        }

        /**
        ③   ユーザ共通情報.CCSログイン用IDのチェックを行う。
            未設定(Null または空文字）の場合：取引不可エラーを返す。
            上記以外：次の処理へ。
         */
        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CCSIDUNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CCSIDUNREGISTERED.key));
        }

        /**
        ④   通貨選択型投信　または　複雑型投信チェックを行う。
            銘柄リスト.NRIコード.書類コード.受入要否=不要：次の処理へ。
            銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型：エラーを返す。
            銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信：エラーを返す。
         */
        // 通貨選択型投信　または　複雑型投信チェックを行う（FCT017）
        // 以下を入力値としてFCT017を実行
        InputFct017Dto fct017Req = new InputFct017Dto();
        // ・顧客共通情報.部店コード
        fct017Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct017Req.setAccountNumber(cc.getAccountNumber());
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        List<InquiryMutualFundBrand> inquiryMutualFundBrandList = new ArrayList<>();

        InquiryMutualFundBrand inquiryMutualFundBrand = null;
        for(IfaMutualFundAccumulateSettingBulkChangeInputData changeDataInfo : changeTargetAccumulateSettingList) {
            String brandCode = changeDataInfo.getMfkaisu() + "." + changeDataInfo.getMfgo();
            String nriCode = changeDataInfo.getMfkaisu() + changeDataInfo.getMfgo();

            // API003レスポンスの積立設定リスト　「銘柄コード」　を利用必要です。
            fundCode2BrandCodeMap.put(changeDataInfo.getFundCode(), brandCode);

            nriCode2BrandCodeMap.put(nriCode, brandCode);

            // FCT017を利用必要です。
            inquiryMutualFundBrand = new InquiryMutualFundBrand();
            inquiryMutualFundBrand.setNriCd(nriCode);
            inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
        }

        fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
        OutputFct017Dto fct017Res = new OutputFct017Dto();
        fct017Res = fct017.getData(fct017Req);

        if (!ObjectUtils.isEmpty(fct017Res.getBrandList())) {

            Map<String, String> brandClassSelectMap = new HashMap<>();
            Map<String, String> brandClassComplicatedMap = new HashMap<>();

            for (Brand brand : fct017Res.getBrandList()) {
                // if(銘柄リスト.NRIコード.書類コード.受入要否=不要){
                if (StringUtils.equals(brand.getAcceptanceNecessity(), ACCEPTANCE_FALSE)) {
                    continue;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_SELECT) && brand.getNriCd() != null) {
                    brandClassSelectMap.put(brand.getNriCd(), nriCode2BrandCodeMap.get(brand.getNriCd()));
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_COMPLICATED) && brand.getNriCd() != null) {
                    brandClassComplicatedMap.put(brand.getNriCd(), nriCode2BrandCodeMap.get(brand.getNriCd()));
                }
            }

            if (!brandClassSelectMap.isEmpty()) {
                // 銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合
                // 強制注文対象エラー(errors.fnd.selectedBrand.currencySelectionType)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_CURRENCYSELECTIONTYPE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CURRENCYSELECTIONTYPE.key,
                                new String[] { IfaMutualFundAccumulateSettingUtil
                                        .getBrandCodesStrByList(brandClassSelectMap, "VAL", "ASC") }));
                return dtoRes;
            }

            if (!brandClassComplicatedMap.isEmpty()) {
                // 銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合
                // 強制注文対象エラー(errors.fnd.selectedBrand.complexType)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_COMPLEXTYPE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_COMPLEXTYPE.key,
                                new String[] { IfaMutualFundAccumulateSettingUtil
                                        .getBrandCodesStrByList(brandClassComplicatedMap, "VAL", "ASC") }));
                return dtoRes;
            }
        }

        /**
        ⑤   口座の取引制限チェックを行う。
            注意情報エラー有無="1"（あり）：エラーを返す。
            お知らせエラー有無="1"（あり）：エラーを返す。
            注意情報アラート有無="1"（あり）：アラート情報を格納する。
            お知らせアラート有無="1"（あり）：アラート情報を格納する。
            上記チェックを実施したら、次の処理へ。
         */
        // ⑥口座の取引制限チェックを行う（FCT021）
        // 以下を入力値としてFCT021を実行
        InputFct021Dto fct021Req = new InputFct021Dto();
        // ・顧客共通情報.部店コード
        fct021Req.setButenCode(cc.getButenCode());
        // ・顧客共通情報.口座番号
        fct021Req.setAccountNumber(cc.getAccountNumber());
        // ・証券金銭種別="06"(国内投信)
        fct021Req.setProductCd(DOMESTIC_MUTUALFUND);
        // ・取引種別
        fct021Req.setTradeCd(MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE);
        // ・国籍コード
        fct021Req.setCountryCd(NATION_CODE);
        // ・通貨コード
        fct021Req.setCurrencyCode(CURRENCY_CODE);
        OutputFct021Dto fct021Res = new OutputFct021Dto();
        fct021Res = fct021.doCheck(fct021Req);
        // 注意情報エラー有無="1"（あり）：エラーを返す。
        if (StringUtils.equals(fct021Res.getNoteInfoErrFlag(), ONE)) {
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "10", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOTICEERRORCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOTICEERRORCHECK.key, new String[] { codeName }));
            return dtoRes;
        }
        // お知らせエラー有無="1"（あり）：エラーを返す。
        if (StringUtils.equals(fct021Res.getNoteLimitErrFlag(), ONE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATIONCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATIONCHECK.key));
            return dtoRes;
        }
        // 注意情報アラート有無="1"（あり）：アラート情報を格納する。
        if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), ONE)) {
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "10", "1");
            a005Res.setNoticeInfoAlert(
                    IfaCommonUtil.getMessage(MessageId.WRANINGS_NOTICEWARNINGCHECK.key, new String[] { codeName }));
            // お知らせアラート
        }
        // お知らせアラート有無="1"（あり）：アラート情報を格納する。
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), ONE)) {
            a005Res.setNoticeAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_INFORMATIONCHECK.key));
        }

        /**
         * ⑥ 確認書受け入れチェックを行う。 
         * 勧誘区分＝”勧誘あり”かつ受付方法＝店頭もしくは訪問：アラート情報を格納する。 
         * 上記チェックを実施したら、次の処理へ。
         */
        // warnings.fndReserve.confirmationCheck
        // 受付方法が店頭または訪問になっているため、確認書の受け入れが必要です。
        String kanyuKbn = dtoReq.getKanyuKbn();
        String receiveMethod = dtoReq.getReceiveMethod();
        a005Res.setKanyuKbn(kanyuKbn);
        a005Res.setReceiveMethod(receiveMethod);
        // 1:勧誘あり;2:勧誘なし
        // 1:店頭,2:訪問,3:電話他
        if ("1".equals(kanyuKbn) && ("1".equals(receiveMethod) || "2".equals(receiveMethod))) {
            a005Res.setConfirmDocumentAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_FNDRESERVE_CONFIRMATIONCHECK.key));
        }

        // ジュニアNISA口座開設有無
        a005Res.setOpenedJnisa(dtoReq.getOpenedJnisa());
        // ご注意事項
        a005Res.setCheckMadoAki(dtoReq.getCheckMadoAki());

        /**
         * ⑦ 設定変更確認を行う。
         *  積立設定一括変更確認APIの呼出し。
         *    エラー：エラーを返す。
         *    errors.cmn.settingExecutionChange.failed
         *    設定変更処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) {0}:APIエラーコード {1}:APIエラーメッセージ
         *  正常かつエラー情報リストにエラー情報ありの場合：以下のエラーを返す。
         *    エラー情報リスト.ファンド名≠nullの場合
         *    errors.cmn.settingExecutionChanges.failed 
         *    【{0}△{1}】<br>エラーコード：{2}<br>エラーメッセージ：{3}
         *      {0}:エラー情報リスト.ファンド名 
         *      {1}:区分.預り区分（投信積立）区分値：エラー情報リスト.預り区分 取得パターン：2 表示パターン：3 
         *      {2}:APIエラーコード {3}:APIエラーメッセージ
         * 
         *    エラー情報リスト.ファンド名=nullの場合 
         *    errors.cmn.settingExecutionChanges.failed#2
         *    エラーコード：{0}<br>エラーメッセージ：{1}
         *      {0}:APIエラーコード 
         *      {1}:APIエラーメッセージ 
         *  ※最大3メッセージまで表示
         *  正常かつエラー情報リストにエラー情報なし：次の処理へ。
         */
        PostFundReserveSettingBulkChangeConfirmReq api004Req = new PostFundReserveSettingBulkChangeConfirmReq();
        api004Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        FundReserveSettingBulkChangeConfirmApiIn api004ApiIn = new FundReserveSettingBulkChangeConfirmApiIn();
        // ユーザ共通情報.CCSログイン用ID
        // オペレーターID
        api004ApiIn.setOperatorId(IfaCommonUtil.getUserAccount().getCcsUserId());
        // 受付経路区分
        // ”2"　(BRANCH_OFFICE) ：支店
        api004ApiIn.setRouteType("BRANCH_OFFICE");

        List<ChangeReserveSettingInfo> reserveOrderList = new ArrayList<>();

        ChangeReserveSettingInfo apiInputData = null;
        for(IfaMutualFundAccumulateSettingBulkChangeInputData inputData : changeTargetAccumulateSettingList ) {
            apiInputData = new ChangeReserveSettingInfo();
            // 協会コード
            apiInputData.setFundCode(inputData.getFundCode());
            // 決済方法
            apiInputData.setPaymentMethod("CASH");
            // 預り区分
            apiInputData.setAccountType(SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getSafeEnumByIfaValue(inputData.getAccountType()));
            // 設定金額
            BigDecimal settingAmount = new BigDecimal(inputData.getSettingAmount().trim());
            apiInputData.setSettingAmount(settingAmount);
            // コース区分
            /**
             * 1 毎日(EVERY_DAY) 
             * 2 毎週(EVERY_WEEK) 
             * 3 毎月(EVERY_MONTH) 
             * 4 複数日(MULTIPLE_DAYS)
             * 5 奇数月(BIMONTHLY)
             * 6 偶数月(BIMONTHLY)
             */
            apiInputData.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getSafeEnumByIfaValue(inputData.getCourseType()));
            // 積立日付
            apiInputData.setSettingReserveDay(inputData.getSettingReserveDay());
            // 積立隔月設定
            /**
             * 1: 奇数月
             * 2: 偶数月
             */
            if ("5".equals(inputData.getCourseType())) {
                apiInputData.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.getSafeEnumByIfaValue("1"));
            } else if ("6".equals(inputData.getCourseType())) {
                apiInputData.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.getSafeEnumByIfaValue("2"));
            } else {
                apiInputData.setSettingReserveBimonthly(null);
            }
            // 積立毎週設定
            /**
             * 1: MONDAY
             * 2: TUESDAY
             * 3: WEDNESDAY
             * 4: THURSDAY
             * 5: FRIDAY
             */
            apiInputData.setSettingReserveWeek(SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum.getSafeEnumByIfaValue(inputData.getSettingReserveWeek()));
            // 積立複数日設定
            if(inputData.getSettingReserveMultiDay() != null && inputData.getSettingReserveMultiDay().length() > 0) {
                apiInputData.setSettingReserveMultiDay(inputData.getSettingReserveMultiDay());
            } else {
                apiInputData.setSettingReserveMultiDay(null);
            }
            reserveOrderList.add(apiInputData);
        }

        api004ApiIn.setReserveOrderList(reserveOrderList);

        api004Req.setParameter(api004ApiIn);
        PostFundReserveSettingBulkChangeConfirmRes  api004Res = null;

        try {
            api004Res = safeFundTradeService.reserveBulkChangeConfirm(api004Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e, MessageId.ERRORS_SETTINGEXECUTIONCHANGE_FAILED.key);
        }

        FundReserveSettingBulkChangeConfirmApiOut api004Out= api004Res.getFundReserveSettingBulkChangeConfirmApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, api004Out);

        List<IfaMutualFundAccumulateSettingBulkChangeConfirmData> bulkChangeList = new ArrayList<>();
        List<ReserveSettingErrorInfo> errorInfos = api004Out.getErrorInfos();

        if(errorInfos == null || errorInfos.isEmpty()) {

            List<ReserveSettingChangeInfo> reserveSettingList= api004Out.getReserveOrderList();

            if (!ObjectUtils.isEmpty(reserveSettingList)) {

                IfaMutualFundAccumulateSettingBulkChangeConfirmData bulkChangeConfirmData = null;

                for (ReserveSettingChangeInfo reserveSetting : reserveSettingList) {
                    bulkChangeConfirmData = new IfaMutualFundAccumulateSettingBulkChangeConfirmData();
                    IfaMutualFundAccumulateSettingBulkChangeData before = new IfaMutualFundAccumulateSettingBulkChangeData();
                    FundReserveSettingBaseOut apiBefore = reserveSetting.getReserveSettingChangeBefore();

                    if (apiBefore != null) {
                        // 銘柄名
                        /** 投資信託協会名（投資信託名） */
                        before.setFundName(apiBefore.getFundName());
                        // 協会コード
                        /** 投資信託協会コード */
                        before.setFundCode(apiBefore.getFundCode());
                        // 銘柄コード
                        before.setBrandCode(Optional.ofNullable(fundCode2BrandCodeMap.get(apiBefore.getFundCode())).orElse(""));
                        String[] mfkaisuAndMfgo = getMfkaisuAndMfgoFromBrandCode(before.getBrandCode());
                        before.setMfkaisu(mfkaisuAndMfgo[0]);
                        before.setMfgo(mfkaisuAndMfgo[1]);
                        /** 預り区分 */
                        String accountType = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(apiBefore.getAccountType());
                        // NISA（成長投資枠） "H"
                        // NISA（つみたて投資枠） "I"
                        // 特定/一般 " "
                        // Jr特定/Jr一般 "5"
                        before.setAccountType(accountType);
                        /** 設定金額 */
                        before.setSettingAmount(Optional.ofNullable(apiBefore.getSettingAmount())
                                .orElse(new BigDecimal(0)).toPlainString());
                        /** 設定金額概算手数料 */
                        before.setEstimateFundOrder(Optional.ofNullable(apiBefore.getEstimateFundOrder())
                                .orElse(new BigDecimal(0)).toPlainString());
                        // 積立コース
                        /** コース区分 */
                        String courseType = apiBefore.getCourseType();

                        if (courseType != null) {
                            // ■積立コース＝毎日の場合
                            // "毎日"
                            if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_DAY.getSafeEnum().equals(courseType)) {
                                before.setCourseType("1");
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_WEEK.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝毎週の場合
                                before.setCourseType("2");
                                /** 積立毎週設定 */
                                String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                                        .getIfaValueBySafeEnum(apiBefore.getSettingReserveWeek());
                                if (ifaSettingReserveWeek != null) {
                                    before.setSettingReserveWeek(ifaSettingReserveWeek);
                                }
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_MONTH.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝毎月の場合
                                before.setCourseType("3");
                                /** 積立日付 */
                                before.setSettingReserveDay(apiBefore.getSettingReserveDay());
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.BIMONTHLY.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝隔月の場合
                                if (SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.ODD_MONTH.getSafeEnum()
                                        .equals(apiBefore.getSettingReserveBimonthly())) {
                                    // 奇数月
                                    before.setCourseType("5");
                                    /** 積立隔月設定 */
                                    before.setSettingReserveBimonthly("1");
                                    /** 積立日付 */
                                    before.setSettingReserveDay(apiBefore.getSettingReserveDay());
                                } else if (SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.EVEN_MONTH.getSafeEnum()
                                        .equals(apiBefore.getSettingReserveBimonthly())) {
                                    // 偶数月
                                    before.setCourseType("6");
                                    /** 積立隔月設定 */
                                    before.setSettingReserveBimonthly("2");
                                    /** 積立日付 */
                                    before.setSettingReserveDay(apiBefore.getSettingReserveDay());
                                }
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.MULTIPLE_DAYS.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝複数日の場合
                                before.setCourseType("4");
                                /** 積立複数日設定 */
                                if (apiBefore.getSettingReserveMultiDay() != null) {
                                    before.setSettingReserveMultiDay(apiBefore.getSettingReserveMultiDay());
                                }
                            }
                        }
                        /** 1ヵ月あたりの設定金額（概算） */
                        before.setOneMonthSumAmount(Optional.ofNullable(apiBefore.getOneMonthSumAmount())
                                .orElse(new BigDecimal(0)).toPlainString());
                        // 買付予定日
                        if (apiBefore.getPlanDate() != null && apiBefore.getPlanDate().length() == 8) {
                            before.setPlanDate(LocalDate.parse(apiBefore.getPlanDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
                        }
                    }

                    bulkChangeConfirmData.setReserveSettingChangeBefore(before);

                    IfaMutualFundAccumulateSettingBulkChangeData after = new IfaMutualFundAccumulateSettingBulkChangeData();
                    FundReserveSettingBaseOut apiAfter = reserveSetting.getReserveSettingChangeAfter();
                    if (apiAfter != null) {
                        // 銘柄名
                        /** 投資信託協会名（投資信託名） */
                        after.setFundName(apiAfter.getFundName());
                        // 協会コード
                        /** 投資信託協会コード */
                        after.setFundCode(apiAfter.getFundCode());
                        // 銘柄コード
                        after.setBrandCode(Optional.ofNullable(fundCode2BrandCodeMap.get(apiAfter.getFundCode())).orElse(""));
                        String[] mfkaisuAndMfgo = getMfkaisuAndMfgoFromBrandCode(after.getBrandCode());
                        after.setMfkaisu(mfkaisuAndMfgo[0]);
                        after.setMfgo(mfkaisuAndMfgo[1]);
                        /** 預り区分 */
                        String accountType = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(apiAfter.getAccountType());
                        // NISA（成長投資枠） "H"
                        // NISA（つみたて投資枠） "I"
                        // 特定/一般 " "
                        // Jr特定/Jr一般 "5"
                        after.setAccountType(accountType);
                        /** 設定金額 */
                        after.setSettingAmount(Optional.ofNullable(apiAfter.getSettingAmount())
                                .orElse(new BigDecimal(0)).toPlainString());
                        /** 設定金額概算手数料 */
                        after.setEstimateFundOrder(Optional.ofNullable(apiAfter.getEstimateFundOrder())
                                .orElse(new BigDecimal(0)).toPlainString());
                        // 積立コース
                        /** コース区分 */
                        String courseType = apiAfter.getCourseType();

                        if (courseType != null) {
                            // ■積立コース＝毎日の場合
                            // "毎日"
                            if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_DAY.getSafeEnum().equals(courseType)) {
                                after.setCourseType("1");
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_WEEK.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝毎週の場合
                                after.setCourseType("2");
                                /** 積立毎週設定 */
                                String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                                        .getIfaValueBySafeEnum(apiAfter.getSettingReserveWeek());
                                if (ifaSettingReserveWeek != null) {
                                    after.setSettingReserveWeek(ifaSettingReserveWeek);
                                }
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.EVERY_MONTH.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝毎月の場合
                                after.setCourseType("3");
                                /** 積立日付 */
                                after.setSettingReserveDay(apiAfter.getSettingReserveDay());
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.BIMONTHLY.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝隔月の場合
                                if (SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.ODD_MONTH.getSafeEnum()
                                        .equals(apiAfter.getSettingReserveBimonthly())) {
                                    // 奇数月
                                    after.setCourseType("5");
                                    /** 積立隔月設定 */
                                    after.setSettingReserveBimonthly("1");
                                    /** 積立日付 */
                                    after.setSettingReserveDay(apiAfter.getSettingReserveDay());
                                } else if (SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.EVEN_MONTH.getSafeEnum()
                                        .equals(apiAfter.getSettingReserveBimonthly())) {
                                    // 偶数月
                                    after.setCourseType("6");
                                    /** 積立隔月設定 */
                                    after.setSettingReserveBimonthly("2");
                                    /** 積立日付 */
                                    after.setSettingReserveDay(apiAfter.getSettingReserveDay());
                                }
                            } else if (SafeType2IfaTypeUtil.IfaCourseTypeEnum.MULTIPLE_DAYS.getSafeEnum().equals(courseType)) {
                                // ■積立コース＝複数日の場合
                                after.setCourseType("4");
                                /** 積立複数日設定 */
                                if (apiAfter.getSettingReserveMultiDay() != null) {
                                    after.setSettingReserveMultiDay(apiAfter.getSettingReserveMultiDay());
                                }
                            }
                        }
                        /** 1ヵ月あたりの設定金額（概算） */
                        after.setOneMonthSumAmount(Optional.ofNullable(apiAfter.getOneMonthSumAmount())
                                .orElse(new BigDecimal(0)).toPlainString());
                        // 買付予定日
                        if (apiAfter.getPlanDate() != null && apiAfter.getPlanDate().length() == 8) {
                            after.setPlanDate(LocalDate.parse(apiAfter.getPlanDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
                        }
                    }
                    bulkChangeConfirmData.setReserveSettingChangeAfter(after);
                    bulkChangeList.add(bulkChangeConfirmData);
                }
            }

            a005Res.setBulkChangeList(bulkChangeList);

        } else {

            int maxErrorCnt = 3;
            String apiAddMessge = "";
            /**
             *  正常かつエラー情報リストにエラー情報ありの場合：以下のエラーを返す。
             *    エラー情報リスト.ファンド名≠nullの場合
             *    errors.cmn.settingExecutionChanges.failed 
             *    【{0}△{1}】<br>エラーコード：{2}<br>エラーメッセージ：{3}
             *      {0}:エラー情報リスト.ファンド名 
             *      {1}:区分.預り区分（投信積立）区分値：エラー情報リスト.預り区分 取得パターン：2 表示パターン：3 
             *      {2}:APIエラーコード 
             *      {3}:APIエラーメッセージ
             * 
             *    エラー情報リスト.ファンド名=nullの場合 
             *    errors.cmn.settingExecutionChanges.failed#2
             *    エラーコード：{0}<br>エラーメッセージ：{1}
             *      {0}:APIエラーコード 
             *      {1}:APIエラーメッセージ 
             *  ※最大3メッセージまで表示
             */
            for (ReserveSettingErrorInfo reserveSettingErrorInfo : errorInfos) {
                maxErrorCnt--;
                // ※最大3メッセージまで表示
                if (maxErrorCnt < 0) {
                    break;
                }

                ErrorInfo errorInfo = reserveSettingErrorInfo.getErrorInfo();

                // エラーコードありの場合、
                if (errorInfo != null && !StringUtil.isNullOrEmpty(errorInfo.getCode())) {

                    String fundName = Optional.ofNullable(reserveSettingErrorInfo.getFundName()).orElse("");
                    String accountTypeName = Optional
                            .ofNullable(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE",
                                    SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum
                                            .getIfaValueBySafeEnum(reserveSettingErrorInfo.getAccountType()),
                                    "3"))
                            .orElse("");

                    IfaSafeErrorMessageSql001ResponseModel safeErrorInfoModel = safeCommonService
                            .getSafeErrorCodeAndMessage(errorInfo.getCode(), errorInfo.getErrorLevel(),
                                    errorInfo.getServiceType());

                    // エラーメッセージを取得できるの場合
                    if (safeErrorInfoModel != null) {

                        // エラー情報リスト.ファンド名≠nullの場合
                        if (!StringUtil.isNullOrEmpty(fundName)) {
                            // 【{0}△{1}】<br>エラーコード：{2}<br>エラーメッセージ：{3}
                            // {0}:エラー情報リスト.ファンド名
                            // {1}:区分.預り区分（投信積立）区分値：エラー情報リスト.預り区分 取得パターン：2 表示パターン：3
                            // {2}:APIエラーコード
                            // {3}:APIエラーメッセージ
                            apiAddMessge = (maxErrorCnt == 2)
                                    ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key,
                                            new String[] { fundName, accountTypeName, errorInfo.getCode(),
                                                    safeErrorInfoModel.getErrorMessage() })
                                    : String.join("<sep>", apiAddMessge,
                                            IfaCommonUtil
                                                    .getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key,
                                                            new String[] { fundName, accountTypeName,
                                                                    errorInfo.getCode(),
                                                                    safeErrorInfoModel.getErrorMessage() }));
                        } else {
                            // エラー情報リスト.ファンド名=nullの場合

                            // エラーコード：{0}<br>エラーメッセージ：{1}
                            // {0}:APIエラーコード
                            // {1}:APIエラーメッセージ
                            apiAddMessge = (maxErrorCnt == 2)
                                    ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                            new String[] { errorInfo.getCode(), safeErrorInfoModel.getErrorMessage() })
                                    : String.join("<sep>", apiAddMessge, IfaCommonUtil.getMessage(
                                            MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key, new String[] {
                                                    errorInfo.getCode(), safeErrorInfoModel.getErrorMessage() }));
                        }
                    } else {
                        // エラーメッセージを取得できないの場合

                        // エラー情報リスト.ファンド名≠nullの場合
                        if (!StringUtil.isNullOrEmpty(fundName)) {
                            // 【{0}△{1}】<br>エラーコード：{2}<br>エラーメッセージ：{3}
                            // {0}:エラー情報リスト.ファンド名
                            // {1}:区分.預り区分（投信積立）区分値：エラー情報リスト.預り区分 取得パターン：2 表示パターン：3
                            // {2}:APIエラーコード
                            // {3}:''
                            apiAddMessge = (maxErrorCnt == 2)
                                    ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key,
                                            new String[] { fundName, accountTypeName, errorInfo.getCode(), "" })
                                    : String.join("<sep>", apiAddMessge, IfaCommonUtil.getMessage(
                                            MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key,
                                            new String[] { fundName, accountTypeName, errorInfo.getCode(), "" }));
                        } else {
                            // エラー情報リスト.ファンド名=nullの場合

                            // エラーコード：{0}<br>エラーメッセージ：{1}
                            // {0}:APIエラーコード
                            // {1}:''
                            apiAddMessge = (maxErrorCnt == 2)
                                    ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                            new String[] { errorInfo.getCode(), "" })
                                    : String.join("<sep>", apiAddMessge,
                                            IfaCommonUtil.getMessage(
                                                    MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                                    new String[] { errorInfo.getCode(), "" }));
                        }
                    }
                } else if (errorInfo != null && StringUtil.isNullOrEmpty(errorInfo.getCode()) && !StringUtil.isNullOrEmpty(errorInfo.getMessage())) {
                    // エラーコードなしの場合、エラーコード：{0}<br>エラーメッセージ：{1}
                    // {0}:''
                    // {1}:APIエラーメッセージ
                    String apiMessage = errorInfo.getMessage();
                    apiAddMessge = (maxErrorCnt == 2)
                            ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                    new String[] { "", apiMessage })
                            : String.join("<sep>", apiAddMessge,
                                    IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                            new String[] { "", apiMessage }));
                } else {
                    // その他の場合、エラーコード：{0}<br>エラーメッセージ：{1}
                    // {0}:''
                    // {1}:''
                    apiAddMessge = (maxErrorCnt == 2)
                            ? IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key,
                                    new String[] { "", "" })
                            : String.join("<sep>", apiAddMessge, IfaCommonUtil.getMessage(
                                    MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED_2.key, new String[] { "", "" }));
                }
            }

            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_SETTINGEXECUTIONCHANGES_FAILED.key, apiAddMessge);
            return dtoRes;
        }

        Map<String, List<String>> fct006ErrorMessageIdMap = new HashMap<>();
        /**
         * ⑧ コンプラランクチェックを行う。 FCT006.判定結果=0：ノーマル：FCT006.開始基準確認メッセージIDを格納し、次の処理へ
         * FCT006.判定結果=1：アラート：FCT006.メッセージID、FCT006.チェックボックス文言 および
         * FCT006.開始基準確認メッセージIDを格納し、次の処理へ FCT006.判定結果=2：エラー：エラーを返す。
         * FCT006.判定結果=上記以外：エラーを返す。
         */
        InputFct006Dto fct006Req = new InputFct006Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct006Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct006Req.setAccountNumber(cc.getAccountNumber());
        // 国内外国区分("0":国内)をセットする
        fct006Req.setBrDomesticFgnInd(DOMESTIC);
        // 商品区分("1△":株式)をセットする
        fct006Req.setBrBrandInd(MUTUALFUND);
        // 勧誘区分(リクエスト.勧誘区分)をセットする
        fct006Req.setInvitationType(kanyuKbn);
        // 受注方法(パラメータ.受注方法)をセットする
        fct006Req.setOrderMethod(receiveMethod);
        // コンプラチェック種類("1":買付注文)をセットする
        fct006Req.setComplaCheckKind(PURCHASE_ORDER);

        for (IfaMutualFundAccumulateSettingBulkChangeConfirmData apiAfterData : a005Res.getBulkChangeList()) {
            IfaMutualFundAccumulateSettingBulkChangeData apiAfter4Fct006 = apiAfterData.getReserveSettingChangeAfter();
            String fundCode = apiAfter4Fct006.getFundCode();
            String brandCode = Optional.ofNullable(fundCode2BrandCodeMap.get(fundCode)).orElse("");
            String[] mfkaisuAndMfgo = getMfkaisuAndMfgoFromBrandCode(brandCode);
            // 銘柄コード1(リクエスト.銘柄コード)をセットする
            fct006Req.setBrandCode1(mfkaisuAndMfgo[0]);
            fct006Req.setBrandCode2(mfkaisuAndMfgo[1]);
            // 共通関数FCT006を呼び出す
            OutputFct006Dto fct006Res = fct006.doCheck(fct006Req);

            IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck complianceRankCheck = new IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck();

            // if(FCT006.判定結果=0：ノーマル){
            if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.NORMAL.key)) {
                // 開始基準確認メッセージ
                // FCT006.開始基準確認メッセージIDが設定されている場合のみメッセージを設定する
                if (!StringUtil.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId())) {
                    complianceRankCheck.setStartCriteriaConfirmMsg(
                            IfaCommonUtil.getMessage(fct006Res.getStartCriteriaConfirmMsgId()));
                }

                // FCT006.判定結果=1：アラート：FCT006.メッセージID、FCT006.チェックボックス文言 および
                // FCT006.開始基準確認メッセージIDを格納し、次の処理へ
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ALERT.key)) {
                // メッセージ
                complianceRankCheck.setMessage(IfaCommonUtil.getMessage(fct006Res.getMessageId()));
                // チェックボックス文言
                complianceRankCheck.setInvitationCheck(fct006Res.getChkBoxLabel());
                // 開始基準確認メッセージ
                // FCT006.開始基準確認メッセージIDが設定されている場合のみメッセージを設定する
                if (!StringUtil.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId())) {
                    complianceRankCheck.setStartCriteriaConfirmMsg(
                            IfaCommonUtil.getMessage(fct006Res.getStartCriteriaConfirmMsgId()));
                }

                // FCT006.判定結果=2：エラー：エラーを返す。
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ERROR.key)) {

                String messageId = fct006Res.getMessageId();

                if (!fct006ErrorMessageIdMap.containsKey(messageId)) {
                    List<String> fct006ErrorBrandCodeList = new ArrayList<>();
                    fct006ErrorBrandCodeList.add(brandCode);
                    fct006ErrorMessageIdMap.put(messageId, fct006ErrorBrandCodeList);
                } else {
                    List<String> fct006ErrorBrandCodeList = fct006ErrorMessageIdMap.get(messageId);
                    fct006ErrorBrandCodeList.add(brandCode);
                }

                // FCT006.判定結果=上記以外：エラーを返す。
            } else {

                String messageId = fct006Res.getMessageId();

                if (!fct006ErrorMessageIdMap.containsKey(messageId)) {
                    List<String> fct006ErrorBrandCodeList = new ArrayList<>();
                    fct006ErrorBrandCodeList.add(brandCode);
                    fct006ErrorMessageIdMap.put(messageId, fct006ErrorBrandCodeList);
                } else {
                    List<String> fct006ErrorBrandCodeList = fct006ErrorMessageIdMap.get(messageId);
                    fct006ErrorBrandCodeList.add(brandCode);
                }
            }

            // コンプラランクチェック
            apiAfterData.setComplianceRankCheck(complianceRankCheck);
        }

        if (!fct006ErrorMessageIdMap.isEmpty()) {
            String fct006ErrorMessages = null;

            for (String messageIdKey : fct006ErrorMessageIdMap.keySet()) {
                if (fct006ErrorMessages != null) {
                    fct006ErrorMessages += "<sep>";
                } else {
                    fct006ErrorMessages = "";
                }

                fct006ErrorMessages += IfaCommonUtil.getMessage(messageIdKey);
                List<String> itemBrands = fct006ErrorMessageIdMap.get(messageIdKey);
                String brandsStr = "";

                if (itemBrands != null) {
                    List<String> messageItems = itemBrands.stream().filter(e -> e != null).distinct().sorted()
                            .collect(Collectors.toList());

                    if (messageItems != null && messageItems.size() > 0) {
                        for (String item : messageItems) {
                            brandsStr += (item + "、");
                        }
                    }

                    if (!"".equals(brandsStr)) {
                        brandsStr = brandsStr.substring(0, brandsStr.length() - 1);
                    }

                    fct006ErrorMessages += ("<br>銘柄コード： " + brandsStr);
                }
            }

            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, null, fct006ErrorMessages);
            return dtoRes;
        }

        /**
         * ⑨ 投信積立設定一括変更確認画面を表示する。
         */
        // レスポンスをコントローラーに返却する。
        resDtoList.add(a005Res);

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

    private String[] getMfkaisuAndMfgoFromBrandCode(String brandCodeStr) {
        String[] mfkaisuAndMfgo = new String[2];
        int idx = brandCodeStr.indexOf(".");

        if (idx >= 0) {
            mfkaisuAndMfgo[0] = brandCodeStr.substring(0, idx);
            mfkaisuAndMfgo[1] = brandCodeStr.substring(idx + 1);
        }

        return mfkaisuAndMfgo;
    }

}

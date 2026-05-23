package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeData;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA009RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputA009ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMutualFundAccumulateSettingChangeInputService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaMutualFundAccumulateSettingUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingReq;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingRes;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundBasicReq;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundBasicRes;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundDetailReq;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundDetailRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataListReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataListRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundReserveSettingDataSummaryRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundTradeReserveSettingGetTradeTypeReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundTradeReserveSettingGetTradeTypeRes;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingChangeConfirmReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.PostFundReserveSettingChangeConfirmRes;
import com.sbisec.helios.ap.safe.service.SafeAccountService;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundProductService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.account.dto.ReserveBuySettingGetApiIn;
import com.sbisec.helios.ap.safe.service.account.dto.ReserveBuySettingGetApiOut;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundBasicInfoApiIn;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundBasicInfoApiOut;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundDetailInfoApiIn;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundDetailInfoApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveCanTradeTypeApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveCanTradeTypeApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingBaseOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingChangeConfirmApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSettingChangeConfirmApiOut;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundReserveSumAmount;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingData;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.ReserveSettingDataListApiOut;
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
@Component(value = "cmpIfaMutualFundAccumulateSettingChangeInputService")
public class IfaMutualFundAccumulateSettingChangeInputServiceImpL implements IfaMutualFundAccumulateSettingChangeInputService {

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
    private SafeFundProductService safeFundProductService;

    @Autowired
    private SafeCommonService safeCommonService;

    @Autowired
    private MCodeService mcodeService;

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
        // errors.fnd.selectedBrand.currencySelectionType
        ERRORS_CURRENCYSELECTIONTYPE("errors.fnd.selectedBrand.currencySelectionType"),
        // errors.fnd.selectedBrand.complexType
        ERRORS_COMPLEXTYPE("errors.fnd.selectedBrand.complexType"),
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
        // errors.cmn.settingExecutionChange
        ERRORS_SETTINGEXECUTIONCHANGE("errors.cmn.settingExecutionChange"),
        // errors.cmn.settingExecutionChange.failed
        ERRORS_SETTINGEXECUTIONCHANGE_FAILED("errors.cmn.settingExecutionChange.failed");

        private String key;

        private MessageId(String key) {

            this.key = key;
        }
    }

    @Override
    public DataList<IfaMutualFundAccumulateSettingChangeInputA001ResponseDto> initializeA001(
            IfaMutualFundAccumulateSettingChangeInputA001RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingChangeInputServiceImpL.initializeA001");
        }

        DataList<IfaMutualFundAccumulateSettingChangeInputA001ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingChangeInputA001ResponseDto>();
        List<IfaMutualFundAccumulateSettingChangeInputA001ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingChangeInputA001ResponseDto>();

        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();

        String mFKaisu = dtoReq.getMfkaisu();
        String mFGo = dtoReq.getMfgo();

        IfaMutualFundAccumulateSettingChangeInputA001ResponseDto a001ResponseDto = new IfaMutualFundAccumulateSettingChangeInputA001ResponseDto();

        a001ResponseDto.setFundCode(dtoReq.getFundCode());
        a001ResponseDto.setMfkaisu(mFKaisu);
        a001ResponseDto.setMfgo(mFGo);

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
         * ② 取引コース媒介可否チェックを行う。 取引可：次の処理へ。 上記以外：取引不可エラーを返す。
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
         * ④ 通貨選択型投信 または 複雑型投信チェックを行う。 銘柄リスト.NRIコード.書類コード.受入要否=不要：次の処理へ。
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
        InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
        inquiryMutualFundBrand.setNriCd(dtoReq.getMfkaisu() + dtoReq.getMfgo());
        inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
        fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
        OutputFct017Dto fct017Res = new OutputFct017Dto();
        fct017Res = fct017.getData(fct017Req);

        if (!ObjectUtils.isEmpty(fct017Res.getBrandList())) {
            for (Brand brand : fct017Res.getBrandList()) {
                // if(銘柄リスト.NRIコード.書類コード.受入要否=不要){
                if (StringUtils.equals(brand.getAcceptanceNecessity(), ACCEPTANCE_FALSE)) {
                    continue;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_SELECT)) {
                    // 強制注文対象エラー(errors.fnd.selectedBrand.currencySelectionType)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_CURRENCYSELECTIONTYPE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_CURRENCYSELECTIONTYPE.key));
                    return dtoRes;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_COMPLICATED)) {
                    // 強制注文対象エラー(errors.fnd.selectedBrand.complexType)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_COMPLEXTYPE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_COMPLEXTYPE.key));
                    return dtoRes;
                }
            }
        }

        /** ⑦   投信基本を取得する。 */
        // 投信基本の取得APIの呼び出し
        // /safe/fundProduct/fund/basic
        GetFundBasicReq api001Req = new GetFundBasicReq();
        api001Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        FundBasicInfoApiIn api001In = new FundBasicInfoApiIn();
        api001In.setFundCode(dtoReq.getFundCode());
        api001Req.setParameter(api001In);
        GetFundBasicRes api001Res = null;

        try {
            api001Res = safeFundProductService.getBasicInfo(api001Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        FundBasicInfoApiOut api001Out= api001Res.getFundBasicInfoApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, api001Out);

        // >投資信託協会名（投資信託名） API001 投資信託協会名（投資信託名）
        // fundName 投資信託協会名（投資信託名）
        String fundName = Optional.ofNullable(api001Out.getFundName()).orElse("");
        a001ResponseDto.setFundName(fundName);

        // >基準価額 API001 基準価額
        /**
         * ■無効値（数値以外）の場合 ""-"" 
         * ■上記以外 基準価格+""円""
         */
        if (api001Out.getStandardPrice() == null) {
            a001ResponseDto.setStandardPriceStr("-");
        } else {
            a001ResponseDto.setStandardPriceStr(api001Out.getStandardPrice().toPlainString());
        }

        // 基準価額増減区分
        /**
         * ↑,↓,△" 
         * ＠取得パターン:1 ＠表示パターン:1
         */
        String tick = null;
        /**
         * 変更なし NO_CHANGE, 増加 INCREASE, 下降 DECLINE
         */
        if ("INCREASE".equals(api001Out.getPreviousStatus())) {
            // 1=↑
            tick = "1";
        } else if ("DECLINE".equals(api001Out.getPreviousStatus())) {
            // 2=↓
            tick = "2";
        } else {
            // △
            tick = " ";
        }
        a001ResponseDto.setTick(tick);

        // 基準価額単位
        /**
         * "/" +基準価額単位
         */
        if (api001Out.getStandardPriceUnit() != null) {
            a001ResponseDto.setStandardPriceUnitStr(api001Out.getStandardPriceUnit().toPlainString());
        }

        // 基準価額対象日付
        String priceDate = api001Out.getPriceDate();
        a001ResponseDto.setPriceDate(priceDate);

        // 前日比
        /**
         * ■基準価額データがない場合 "-"を表示 
         * ■上記以外 前日比（+は赤、-は青0は黒）
         */
        String previousChangeStr;
        String previousChangeSign;
        if (api001Out.getStandardPrice() == null) {
            previousChangeStr = "-";
            previousChangeSign = "";
        } else {
            BigDecimal previousChange = api001Out.getPreviousChange();
            previousChangeStr = previousChange.toPlainString();
            if (previousChange.compareTo(new BigDecimal(0)) == 1) {
                previousChangeSign = "+";
                ;
            } else if (previousChange.compareTo(new BigDecimal(0)) == -1) {
                previousChangeSign = "-";
            } else {
                previousChangeSign = "0";
            }
        }
        a001ResponseDto.setPreviousChangeStr(previousChangeStr);
        a001ResponseDto.setPreviousChangeSign(previousChangeSign);

        // 前日比率
        /**
         * ■基準価額増減区分＝NO_CHANGEの場合 "（-%）"を表示 
         * ■上記以外 前日比率+"%"（+は赤、-は青、0は黒）
         */
        String previousRatio;
        if ("NO_CHANGE".equals(api001Out.getPreviousStatus())) {
            previousRatio = "-";
        } else {
            previousRatio = api001Out.getPreviousRatio();
        }
        a001ResponseDto.setPreviousRatio(previousRatio);

        // 純資産
        /**
         * ■純資産=値なし の場合 "-"を表示 
         * ■上記以外 純資産+"百万円"
         */
        String netAssetStr = "-";
        BigDecimal netAsset = api001Out.getNetAsset();
        if (netAsset != null) {
            netAssetStr = netAsset.toPlainString();
        }
        a001ResponseDto.setNetAssetStr(netAssetStr);

        // 基準価額日付
        /**
         * ■0桁文字の場合 "（--/--/-- 現在）"”を表示 
         * ■上記以外 "（"+基準価額日付+"現在） "
         */
        if (api001Out.getPriceDate() == null || api001Out.getPriceDate().trim().length() == 0) {
            a001ResponseDto.setPriceDate("（--/--/-- 現在）");
        } else {
            a001ResponseDto.setPriceDate("（" + api001Out.getPriceDate() + "現在）");
        }

        // 投信詳細の取得APIの呼び出し
        // /safe/fundProduct/fund/detail
        GetFundDetailReq api002Req = new GetFundDetailReq();
        api002Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        FundDetailInfoApiIn api002In = new FundDetailInfoApiIn();
        api002In.setFundCode(dtoReq.getFundCode());
        api002Req.setParameter(api002In);
        GetFundDetailRes api002Res = null;

        try {
            api002Res = safeFundProductService.getDetailInfo(api002Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        FundDetailInfoApiOut api002Out= api002Res.getFundDetailInfoApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, api002Out);

        // 積立買付単位
        String reserveOrderUnit = Optional.ofNullable(api002Out.getReserveOrderUnit()).orElse("");
        a001ResponseDto.setReserveOrderUnit(reserveOrderUnit);

        // 最低申込金額
        String minApplyAmount = null;
        if ("0".equals(reserveOrderUnit)) {
            // '■積立買付単位＝０の場合'
            // ”100円”
            minApplyAmount = "100円";
        } else if ("1".equals(reserveOrderUnit)) {
            // ■積立買付単位＝１の場合
            // ”1万円”
            minApplyAmount = "1万円";
        } else if ("2".equals(reserveOrderUnit)) {
            // ■積立買付単位＝２の場合
            // ”1千円”
            minApplyAmount = "1千円";
        } else if ("3".equals(reserveOrderUnit)) {
            // ■積立買付単位＝３の場合
            // ”500円”
            minApplyAmount = "500円";
        }
        a001ResponseDto.setMinApplyAmount(minApplyAmount);

        // 申込単位
        String applyUnit;
        if ("1".equals(reserveOrderUnit)) {
            // ■積立買付単位＝１の場合
            //　”1千円”
            applyUnit = "1千円";
        } else {
            // ■上記以外
            //　”1円”
            applyUnit = "1円";
        }
        a001ResponseDto.setApplyUnit(applyUnit);

        /**
         * ② 積立設定サマリを取得する。 
         * 積立設定サマリの取得APIの呼び出し 
         */
        // /safe/fundTrade/fund/reserve/setting_data_summary
        GetFundReserveSettingDataSummaryReq api003Req = new GetFundReserveSettingDataSummaryReq();
        api003Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        api003Req.setParameter(new ReserveSettingSummaryApiIn());
        GetFundReserveSettingDataSummaryRes api003Res = null;
        try {
            api003Res = safeFundTradeService.getSettinSummary(api003Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveSettingSummaryApiOut reserveSettingSummaryApiOut = api003Res.getReserveSettingSummaryApiOut();
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
        GetAccountPointGetReserveBuySettingReq api004Req = new GetAccountPointGetReserveBuySettingReq();
        api004Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        api004Req.setParameter(new ReserveBuySettingGetApiIn());
        GetAccountPointGetReserveBuySettingRes api004Res = null;
        try {
            api004Res = safeAccountService.getReserveBuySetting(api004Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveBuySettingGetApiOut reserveBuySettingGetApiOut = api004Res.getReserveBuySettingGetApiOut();
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
         * 旧ジュニアNISA口座開設有無
         */
        // /safe/fundTrade/fund/reserve/setting/get_trade_types
        GetFundTradeReserveSettingGetTradeTypeReq api006Req = new GetFundTradeReserveSettingGetTradeTypeReq();
        api006Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));
        FundReserveCanTradeTypeApiIn api006In = new FundReserveCanTradeTypeApiIn();
        api006In.setFundCode(dtoReq.getFundCode());
        api006Req.setParameter(api006In);
        GetFundTradeReserveSettingGetTradeTypeRes api006Res = null;

        try {
            api006Res = safeFundTradeService.getSettinDataList(api006Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        FundReserveCanTradeTypeApiOut fundReserveCanTradeTypeApiOut = api006Res.getFundReserveCanTradeTypeApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, fundReserveCanTradeTypeApiOut);

        // 各項目の状態によってコード値をセット(配列で保持)する
        // ■旧ジュニアNISA口座開設有無=""true""の場合
        /** openedJnisa 旧ジュニアNISA口座開設有無 */
        if (fundReserveCanTradeTypeApiOut.isOpenedJnisa()) {
            // 旧ジュニアNISA口座開設有無
            a001ResponseDto.setOpenedJnisa("1");
        } else {
            // 旧ジュニアNISA口座開設有無
            a001ResponseDto.setOpenedJnisa("0");
        }

        /**
         * ⑦ 積立設定一覧情報を取得する。
         */
        // ★★★積立設定一覧情報を取得する。★★★
        // Safe Api: /safe/fundTrade/fund/trade/reserve/setting_data_list
        GetFundReserveSettingDataListReq safeApi007Req = new GetFundReserveSettingDataListReq();
        safeApi007Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));

        ReserveSettingDataListApiIn reserveSettingDataListApiIn = new ReserveSettingDataListApiIn();
        // 協会コード
        reserveSettingDataListApiIn.setFundCode(dtoReq.getFundCode());
        // 預り区分
        List<String> accountTypesList = new ArrayList<>();
        // H -> NISA（成長投資枠）
        // I -> NISA（つみたて投資枠）
        // SPACE -> 特定/一般
        // 5 -> Jr特定/Jr一般
        accountTypesList.add(SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getSafeEnumByIfaValue(dtoReq.getAccountType()));
        reserveSettingDataListApiIn.setAccountTypes(accountTypesList);

        // 決済方法
        reserveSettingDataListApiIn.setPaymentMethod("CASH");

        reserveSettingDataListApiIn.setOffset(0);
        reserveSettingDataListApiIn.setLimit(0);

        safeApi007Req.setParameter(reserveSettingDataListApiIn);
        GetFundReserveSettingDataListRes safeApi007Res = null;

        try {
            safeApi007Res = safeFundTradeService.getSettinDataList(safeApi007Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        ReserveSettingDataListApiOut reserveSettingDataListApiOut = safeApi007Res.getReserveSettingDataListApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, reserveSettingDataListApiOut);

        /**
         * データあり：次の処理へ。
         * データなし：エラーを返す。
         *   errors.cmn.settingExecutionChange
         *   当該銘柄は積立変更できません。
         */
        if (safeApi007Res == null || reserveSettingDataListApiOut == null
                || reserveSettingDataListApiOut.getReserveOrderList() == null
                || reserveSettingDataListApiOut.getReserveOrderList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SETTINGEXECUTIONCHANGE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SETTINGEXECUTIONCHANGE.key));
            return dtoRes;
        }

        ReserveSettingData reserveSettingData = reserveSettingDataListApiOut.getReserveOrderList().get(0);

        // 積立設定リスト.ファンド名
        a001ResponseDto.setFundName(Optional.ofNullable(reserveSettingData.getFundName()).orElse(""));

        // 非特定預り区分
        String ifaAccountTypeCode = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum
                .getIfaValueBySafeEnum(reserveSettingData.getAccountType());
        a001ResponseDto.setAccountType(ifaAccountTypeCode);

        if("1".equals(a001ResponseDto.getOpenedJnisa())) {
            a001ResponseDto.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", ifaAccountTypeCode, "2"));
        } else {
            a001ResponseDto.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", ifaAccountTypeCode, "1"));
        }

        // 明細リスト.積立コース
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
            a001ResponseDto.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getIfaValueBySafeEnum(courseType,
                    reserveSettingData.getSettingReserveBimonthly()));
        }

        // 積立設定リスト.設定金額
        if (reserveSettingData.getSettingAmount() != null) {
            a001ResponseDto.setSettingAmount(reserveSettingData.getSettingAmount().toPlainString());
        }

        // 積立設定リスト.積立日付
        a001ResponseDto.setSettingReserveDay(reserveSettingData.getSettingReserveDay());

        // 積立設定リスト.積立隔月設定
        String ifaSettingReserveBimonthly = SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum
                .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveBimonthly());
        if (ifaSettingReserveBimonthly != null) {
            //奇数月 1
            //偶数月 2
            a001ResponseDto.setSettingReserveBimonthly(ifaSettingReserveBimonthly);
        }

        // API007 積立毎週設定
        /**
         * MONDAY 1 
         * TUESDAY 2 
         * WEDNESDAY 3 
         * THURSDAY 4 
         * FRIDAY 5
         */
        String ifaSettingReserveWeek = SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum
                .getIfaValueBySafeEnum(reserveSettingData.getSettingReserveWeek());
        if (ifaSettingReserveWeek != null) {
            a001ResponseDto.setSettingReserveWeek(ifaSettingReserveWeek);
        }

        // API007  積立複数日設定
        if(reserveSettingData.getSettingReserveMultiDay() != null && reserveSettingData.getSettingReserveMultiDay().length() > 0) {
            a001ResponseDto.setSettingReserveMultiDay(reserveSettingData.getSettingReserveMultiDay());
        }

        // ■ボーナス設定
        /**
         * ■ボーナス設定金額＝1円以上の場合 設定するをチェック 
         * ■上記以外 設定しないをチェック
         */
        if (reserveSettingData.getSettingBonusAmount() != null
                && (reserveSettingData.getSettingBonusAmount().compareTo(new BigDecimal(0)) == 1
                        || reserveSettingData.getSettingBonusAmount().compareTo(new BigDecimal(0)) == 0)) {

            // ■ボーナス設定＝するの場合
            a001ResponseDto.setSettingBonusFlag("1");

            // API007  ボーナス設定金額
            a001ResponseDto
                    .setSettingBonusAmount(Optional.ofNullable(reserveSettingData.getSettingBonusAmount())
                            .orElse(new BigDecimal(0)).toPlainString());

            // API007  ボーナス１設定月
            if(reserveSettingData.getSettingBonus1Month() != null && reserveSettingData.getSettingBonus1Month().length() > 0) {
                a001ResponseDto.setSettingBonus1Month(reserveSettingData.getSettingBonus1Month());
            } else {
                a001ResponseDto.setSettingBonus1Month(null);
            }
            // API007  ボーナス１設定日
            if(reserveSettingData.getSettingBonus1Day() != null && reserveSettingData.getSettingBonus1Day().length() > 0) {
                a001ResponseDto.setSettingBonus1Day(reserveSettingData.getSettingBonus1Day());
            } else {
                a001ResponseDto.setSettingBonus1Day(null);
            }

            // API007  ボーナス２設定月
            if(reserveSettingData.getSettingBonus2Month() != null && reserveSettingData.getSettingBonus2Month().length() > 0) {
                a001ResponseDto.setSettingBonus2Month(reserveSettingData.getSettingBonus2Month());
            } else {
                a001ResponseDto.setSettingBonus2Month(null);
            }
            // API007  ボーナス２設定日
            if(reserveSettingData.getSettingBonus2Day() != null && reserveSettingData.getSettingBonus2Day().length() > 0) {
                a001ResponseDto.setSettingBonus2Day(reserveSettingData.getSettingBonus2Day());
            } else {
                a001ResponseDto.setSettingBonus2Day(null);
            }
        } else {
            a001ResponseDto.setSettingBonusFlag("2");
        }

        // 明細.NISA枠ぎりぎり注文
        /**
         * USE 2
         * UNUSED 1
         * UNSUPPORTED 0
         */
        String ifaNisaBarelyBuyingType = SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                .getIfaValueBySafeEnum(reserveSettingData.getNisaBarelyBuyingType());
        if (ifaNisaBarelyBuyingType == null || "0".equals(ifaNisaBarelyBuyingType)) {
            // ■上記以外
            // "0”
            a001ResponseDto.setNisaBarelyBuyingType("0");
        } else {
            // ■NISAぎりぎり注文チェック＝チェックありの場合
            // ”2”
            // ■NISAぎりぎり注文チェック＝チェックなしの場合
            // ”1”
            a001ResponseDto.setNisaBarelyBuyingType(ifaNisaBarelyBuyingType);
        }

        // 明細.課税枠シフト注文
        /**
         * USE 2
         * UNUSED 1
         * UNSUPPORTED 0
         */
        String ifaTaxShiftType = SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                .getIfaValueBySafeEnum(reserveSettingData.getTaxShiftType());
        if (ifaTaxShiftType == null || "0".equals(ifaTaxShiftType)) {
            // ■上記以外
            // "0”
            a001ResponseDto.setTaxShiftType("0");
        } else {
            // ■課税枠シフト注文チェック＝チェックありの場合
            // ”2”
            // ■課税枠シフト注文文チェック＝チェックなしの場合
            // ”1”
            a001ResponseDto.setTaxShiftType(ifaTaxShiftType);
        }

        // 1年あたりの設定金額（概算） API007から
        String oneYearSumAmountApi007 = Optional.ofNullable(reserveSettingData.getOneYearSumAmount())
                .orElse(new BigDecimal(0)).toPlainString();
        a001ResponseDto.setOneYearSumAmountTotal(oneYearSumAmountApi007);

        /**
         * ⑧ 「コメント」テキストエリアにコメントを表示する。 
         * コードマスタ（M_CODE）より対象コメントを取得して表示
         */
        // NISA枠ぎりぎり注文 コメント
        List<MCode> selSql002_1Res = mcodeService.getMCodeList("99", "01", "23");
        if (selSql002_1Res != null && 0 < selSql002_1Res.size()) {
            a001ResponseDto.setNisaBarelyBuyingTypeComment(selSql002_1Res.get(0).getName());

        }

        // 課税枠シフト注文 コメント
        List<MCode> selSql002_2Res = mcodeService.getMCodeList("99", "01", "24");
        if (selSql002_2Res != null && 0 < selSql002_2Res.size()) {
            a001ResponseDto.setTaxShiftTypeComment(selSql002_2Res.get(0).getName());

        }

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
    public DataList<IfaMutualFundAccumulateSettingChangeInputA009ResponseDto> settingConfirmA009(
            IfaMutualFundAccumulateSettingChangeInputA009RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundAccumulateSettingChangeInputServiceImpL.settingConfirmA009");
        }

        DataList<IfaMutualFundAccumulateSettingChangeInputA009ResponseDto> dtoRes = new DataList<IfaMutualFundAccumulateSettingChangeInputA009ResponseDto>();
        List<IfaMutualFundAccumulateSettingChangeInputA009ResponseDto> resDtoList = new ArrayList<IfaMutualFundAccumulateSettingChangeInputA009ResponseDto>();
        IfaMutualFundAccumulateSettingChangeInputA009ResponseDto a009Res = new IfaMutualFundAccumulateSettingChangeInputA009ResponseDto();

        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 口座番号: 部店コード + "-" + 口座番号
        a009Res.setAccountNumber(cc.getButenCode() + "-" + org.apache.commons.lang3.StringUtils.leftPad(Optional.ofNullable(cc.getAccountNumber()).orElse(""), 7, "0"));
        // 個人・法人アイコン
        a009Res.setCorporationKbn(Optional.ofNullable(cc.getCorporationType()).orElse(""));
        // 顧客名: 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
        a009Res.setCustomerName(cc.getCustomerNameKanji() + "(" + cc.getCustomerNameKana() + ")");

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
        InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
        inquiryMutualFundBrand.setNriCd(dtoReq.getMfkaisu() + dtoReq.getMfgo());
        inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
        fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
        OutputFct017Dto fct017Res = new OutputFct017Dto();
        fct017Res = fct017.getData(fct017Req);

        if (!ObjectUtils.isEmpty(fct017Res.getBrandList())) {
            for (Brand brand : fct017Res.getBrandList()) {
                // if(銘柄リスト.NRIコード.書類コード.受入要否=不要){
                if (StringUtils.equals(brand.getAcceptanceNecessity(), ACCEPTANCE_FALSE)) {
                    continue;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_SELECT)) {
                    // 強制注文対象エラー(errors.fnd.selectedBrand.currencySelectionType)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_CURRENCYSELECTIONTYPE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_CURRENCYSELECTIONTYPE.key));
                    return dtoRes;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), BRAND_CLASS_COMPLICATED)) {
                    // 強制注文対象エラー(errors.fnd.selectedBrand.complexType)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_COMPLEXTYPE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_COMPLEXTYPE.key));
                    return dtoRes;
                }
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
            a009Res.setNoticeInfoAlert(
                    IfaCommonUtil.getMessage(MessageId.WRANINGS_NOTICEWARNINGCHECK.key, new String[] { codeName }));
            // お知らせアラート
        }
        // お知らせアラート有無="1"（あり）：アラート情報を格納する。
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), ONE)) {
            a009Res.setNoticeAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_INFORMATIONCHECK.key));
        }

        a009Res.setFundCode(dtoReq.getFundCode());
        a009Res.setMfkaisu(dtoReq.getMfkaisu());
        a009Res.setMfgo(dtoReq.getMfgo());
        a009Res.setBrandCode(dtoReq.getMfkaisu() + "." + dtoReq.getMfgo());

        /**
         * ⑥ 確認書受け入れチェックを行う。 
         * 勧誘区分＝”勧誘あり”かつ受付方法＝店頭もしくは訪問：アラート情報を格納する。 
         * 上記チェックを実施したら、次の処理へ。
         */
        // warnings.fndReserve.confirmationCheck
        // 受付方法が店頭または訪問になっているため、確認書の受け入れが必要です。
        String kanyuKbn = dtoReq.getKanyuKbn();
        String receiveMethod = dtoReq.getReceiveMethod();
        a009Res.setKanyuKbn(kanyuKbn);
        a009Res.setReceiveMethod(receiveMethod);
        // 1:勧誘あり;2:勧誘なし
        // 1:店頭,2:訪問,3:電話他
        if ("1".equals(kanyuKbn) && ("1".equals(receiveMethod) || "2".equals(receiveMethod))) {
            a009Res.setConfirmDocumentAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_FNDRESERVE_CONFIRMATIONCHECK.key));
        }

        /**
         * ⑦ コンプラランクチェックを行う。 
         * FCT006.判定結果=0：ノーマル：FCT006.開始基準確認メッセージIDを格納し、次の処理へ
         * FCT006.判定結果=1：アラート：FCT006.メッセージID、FCT006.チェックボックス文言 および
         * FCT006.開始基準確認メッセージIDを格納し、次の処理へ 
         * FCT006.判定結果=2：エラー：エラーを返す。
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
        // 銘柄コード1(リクエスト.銘柄コード)をセットする
        fct006Req.setBrandCode1(dtoReq.getMfkaisu());
        fct006Req.setBrandCode2(dtoReq.getMfgo());

        // 勧誘区分(リクエスト.勧誘区分)をセットする
        fct006Req.setInvitationType(kanyuKbn);
        // 受注方法(パラメータ.受注方法)をセットする
        fct006Req.setOrderMethod(receiveMethod);
        // コンプラチェック種類("1":買付注文)をセットする
        fct006Req.setComplaCheckKind(PURCHASE_ORDER);
        // 共通関数FCT006を呼び出す
        OutputFct006Dto fct006Res = fct006.doCheck(fct006Req);

        IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck complianceRankCheck = new IfaMutualFundAccumulateSettingChangeInputComplianceRankCheck();

        // if(FCT006.判定結果=0：ノーマル){
        if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.NORMAL.key)) {
            // 開始基準確認メッセージ
            // FCT006.開始基準確認メッセージIDが設定されている場合のみメッセージを設定する
            if (!StringUtil.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId())) {
                complianceRankCheck.setStartCriteriaConfirmMsg(IfaCommonUtil.getMessage(fct006Res.getStartCriteriaConfirmMsgId()));
            }

            // FCT006.判定結果=1：アラート：FCT006.メッセージID、FCT006.チェックボックス文言　および　FCT006.開始基準確認メッセージIDを格納し、次の処理へ
        } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ALERT.key)) {
            // メッセージ
            complianceRankCheck.setMessage(IfaCommonUtil.getMessage(fct006Res.getMessageId()));
            // チェックボックス文言
            complianceRankCheck.setInvitationCheck(fct006Res.getChkBoxLabel());
            // 開始基準確認メッセージ
            // FCT006.開始基準確認メッセージIDが設定されている場合のみメッセージを設定する
            if (!StringUtil.isNullOrEmpty(fct006Res.getStartCriteriaConfirmMsgId())) {
                complianceRankCheck.setStartCriteriaConfirmMsg(IfaCommonUtil.getMessage(fct006Res.getStartCriteriaConfirmMsgId()));
            }

            // FCT006.判定結果=2：エラー：エラーを返す。
        } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ERROR.key)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    fct006Res.getMessageId(), IfaCommonUtil.getMessage(fct006Res.getMessageId()));
            return dtoRes;

            // FCT006.判定結果=上記以外：エラーを返す。
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    fct006Res.getMessageId(), IfaCommonUtil.getMessage(fct006Res.getMessageId()));
            return dtoRes;
        }

        // コンプラランクチェック
        a009Res.setComplianceRankCheck(complianceRankCheck);

        /**
         * ⑧ 設定変更確認を行う。 
         * 積立設定変更確認APIの呼出し 正常：次の処理へ。 
         * エラー：エラーを返す。
         * errors.cmn.settingExecutionChange.failed
         * 設定変更処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1})
         * {0}:APIエラーコード {1}:APIエラーメッセージ
         */
        PostFundReserveSettingChangeConfirmReq api005Req = new PostFundReserveSettingChangeConfirmReq();
        api005Req.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));

        FundReserveSettingChangeConfirmApiIn api005ApiIn = new FundReserveSettingChangeConfirmApiIn();

        api005ApiIn.setFundCode(dtoReq.getFundCode());    // 協会コード
        api005ApiIn.setPrePaymentMethod("CASH");
        api005ApiIn.setPaymentMethod("CASH");   // 決済方法

        // 預り区分
        /** [ ] -> NORMAL 非NISA注文 */
        /** [H] -> NISA_GROWTH NISA（成長投資枠） */
        /** [I] -> NISA_RESERVE NISA（つみたて投資枠）  */
        /** [5] -> JNISA_NORMAL ジュニアNISA口座 特定/一般 */
        api005ApiIn.setAccountType(SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getSafeEnumByIfaValue(dtoReq.getAccountType()));

        // 設定金額
        if (dtoReq.getSettingAmount() != null && dtoReq.getSettingAmount().trim().length() > 0) {
            BigDecimal settingAmount = new BigDecimal(dtoReq.getSettingAmount().trim());
            api005ApiIn.setSettingAmount(settingAmount);
        }

        // コース区分
        /**
         * 1 毎日(EVERY_DAY) 
         * 2 毎週(EVERY_WEEK) 
         * 3 毎月(EVERY_MONTH) 
         * 4 複数日(MULTIPLE_DAYS)
         * 5 奇数月(BIMONTHLY)
         * 6 偶数月(BIMONTHLY)
         */
        api005ApiIn.setCourseType(SafeType2IfaTypeUtil.IfaCourseTypeEnum.getSafeEnumByIfaValue(dtoReq.getCourseType()));

        // 積立日付
        api005ApiIn.setSettingReserveDay(dtoReq.getSettingReserveDay());

        // 積立隔月設定
        /**
         * 1: 奇数月
         * 2: 偶数月
         */
        if ("5".equals(dtoReq.getCourseType())) {
            api005ApiIn.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.getSafeEnumByIfaValue("1"));
        } else if ("6".equals(dtoReq.getCourseType())) {
            api005ApiIn.setSettingReserveBimonthly(SafeType2IfaTypeUtil.IfaSettingReserveBimonthlyEnum.getSafeEnumByIfaValue("2"));
        } else {
            api005ApiIn.setSettingReserveBimonthly(null);
        }

        // 積立毎週設定
        /**
         * 1: MONDAY
         * 2: TUESDAY
         * 3: WEDNESDAY
         * 4: THURSDAY
         * 5: FRIDAY
         */
        api005ApiIn.setSettingReserveWeek(SafeType2IfaTypeUtil.IfaSettingReserveWeeklyEnum.getSafeEnumByIfaValue(dtoReq.getSettingReserveWeek()));

        // 積立複数日設定
        if(dtoReq.getSettingReserveMultiDay() != null && dtoReq.getSettingReserveMultiDay().length() > 0) {
            api005ApiIn.setSettingReserveMultiDay(dtoReq.getSettingReserveMultiDay());
        } else {
            api005ApiIn.setSettingReserveMultiDay(null);
        }

        // NISA枠ぎりぎり注文
        if ("1".equals(dtoReq.getNisaBarelyBuyingTypeShow())) {
            if("2".equals(dtoReq.getNisaBarelyBuyingType())) {
                // ■NISAぎりぎり注文チェック＝チェックありの場合
                // 　”2”
                api005ApiIn.setNisaBarelyBuyingType("USE");
            } else {
                // ■NISAぎりぎり注文チェック＝チェックなしの場合
                // 　”1”
                api005ApiIn.setNisaBarelyBuyingType("UNUSED");
            }
        } else {
            // ■上記以外
            // 　"0”
            api005ApiIn.setNisaBarelyBuyingType("UNSUPPORTED");
        }

        // 課税枠シフト注文
        if ("1".equals(dtoReq.getTaxShiftTypeShow())) {
            if("2".equals(dtoReq.getTaxShiftType())) {
                // ■課税枠シフト注文チェック＝チェックありの場合
                // 　”2”
                api005ApiIn.setTaxShiftType("USE");
            } else {
                // ■課税枠シフト注文文チェック＝チェックなしの場合
                // 　”1”
                api005ApiIn.setTaxShiftType("UNUSED");
            }
        } else {
            // ■上記以外
            // 　"0”
            api005ApiIn.setTaxShiftType("UNSUPPORTED");
        }

        // ボーナス設定有無
        if ("1".equals(dtoReq.getSettingBonusFlag())) {
            // ■ボーナス設定＝するの場合
            // 　"true"
            api005ApiIn.setSettingBonusFlag(true);

              // ボーナス設定金額
            if (dtoReq.getSettingBonusAmount() != null && dtoReq.getSettingBonusAmount().trim().length() > 0) {
                BigDecimal settingBonusAmount = new BigDecimal(dtoReq.getSettingBonusAmount().trim());
                api005ApiIn.setSettingBonusAmount(settingBonusAmount);
            }

            // ボーナス１設定月
            if(dtoReq.getSettingBonus1Month() != null && dtoReq.getSettingBonus1Month().length() > 0) {
                api005ApiIn.setSettingBonus1Month(dtoReq.getSettingBonus1Month());
            } else {
                api005ApiIn.setSettingBonus1Month(null);
            }
            // ボーナス１設定日
            if(dtoReq.getSettingBonus1Day() != null && dtoReq.getSettingBonus1Day().length() > 0) {
                api005ApiIn.setSettingBonus1Day(dtoReq.getSettingBonus1Day());
            } else {
                api005ApiIn.setSettingBonus1Day(null);
            }

            // ボーナス２設定月
            if(dtoReq.getSettingBonus2Month() != null && dtoReq.getSettingBonus2Month().length() > 0) {
                api005ApiIn.setSettingBonus2Month(dtoReq.getSettingBonus2Month());
            } else {
                api005ApiIn.setSettingBonus2Month(null);
            }
            // ボーナス２設定日
            if(dtoReq.getSettingBonus2Day() != null && dtoReq.getSettingBonus2Day().length() > 0) {
                api005ApiIn.setSettingBonus2Day(dtoReq.getSettingBonus2Day());
            } else {
                api005ApiIn.setSettingBonus2Day(null);
            }
        } else {
            // ■ボーナス設定＝しないの場合
            // 　”false”
            api005ApiIn.setSettingBonusFlag(false);
        }

        // 受付経路区分
        // ”2"　(BRANCH_OFFICE) ：支店
        api005ApiIn.setRouteType("BRANCH_OFFICE");

        // ユーザ共通情報.CCSログイン用ID
        // オペレーターID
        api005ApiIn.setOperatorId(IfaCommonUtil.getUserAccount().getCcsUserId());
        api005Req.setParameter(api005ApiIn);
        PostFundReserveSettingChangeConfirmRes  api005Res = null;

        try {
            api005Res = safeFundTradeService.reserveChangeConfirm(api005Req);
        } catch (Exception e) {
            return safeCommonService.checkSafeBussinessException(resDtoList, e, MessageId.ERRORS_SETTINGEXECUTIONCHANGE_FAILED.key);
        }

        FundReserveSettingChangeConfirmApiOut api005Out= api005Res.getFundReserveSettingChangeConfirmApiOut();
        dtoRes = safeCommonService.checkSafeBussinessWarning(dtoRes, api005Out);

        if(api005Out.getCreditCardCompany() != null) {
            // クレジットカード会社
            a009Res.setCreditCardCompany(api005Out.getCreditCardCompany().toString());
        }

        // 積立買付単位
        a009Res.setReserveOrderUnit(dtoReq.getReserveOrderUnit());

        IfaMutualFundAccumulateSettingChangeData before = new IfaMutualFundAccumulateSettingChangeData();
        FundReserveSettingBaseOut apiBefore = api005Out.getReserveSettingChangeBefore();

        if (apiBefore != null) {
            /** 投資信託協会コード */
            before.setFundCode(apiBefore.getFundCode());
            /** 投資信託協会名（投資信託名） */
            before.setFundName(apiBefore.getFundName());

            /** 決済方法 */
            before.setPaymentMethod(
                    SafeType2IfaTypeUtil.IfaPaymentMethod.getIfaValueBySafeEnum(apiBefore.getPaymentMethod()));

            /** 預り区分 */
            String accountType = SafeType2IfaTypeUtil.IfaReserveTradeTypesEnum.getIfaValueBySafeEnum(apiBefore.getAccountType());
            // NISA（成長投資枠） "H"
            // NISA（つみたて投資枠） "I"
            // 特定/一般 " "
            // Jr特定/Jr一般 "5"
            before.setAccountType(accountType);

            if (accountType != null) {
                a009Res.setAccountType(accountType);

                if ("1".equals(dtoReq.getOpenedJnisa())) {
                    a009Res.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", accountType, "2"));
                } else {
                    a009Res.setAccountTypeName(codeListService.getValue("RESERVE_TRADE_DEPOSIT_TYPE", accountType, "1"));
                }
            }

            /** 設定金額 */
            before.setSettingAmount(Optional.ofNullable(apiBefore.getSettingAmount())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** 1ヵ月あたりの設定金額（概算） */
            before.setOneMonthSumAmount(Optional.ofNullable(apiBefore.getOneMonthSumAmount())
                    .orElse(new BigDecimal(0)).toPlainString());

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

            /** NISA枠ぎりぎり注文 */
            before.setNisaBarelyBuyingType(SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                    .getIfaValueBySafeEnum(apiBefore.getNisaBarelyBuyingType()));


            /** 課税枠シフト注文 */
            before.setTaxShiftType(SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                    .getIfaValueBySafeEnum(apiBefore.getTaxShiftType()));

            /** ボーナス設定有無 */
            if (apiBefore.isSettingBonusFlag()) {
                before.setSettingBonusFlag("1");

                /** ボーナス設定金額 */
                before.setSettingBonusAmount(Optional.ofNullable(apiBefore.getSettingBonusAmount())
                        .orElse(new BigDecimal(0)).toPlainString());

                /** ボーナス１設定月 */
                before.setSettingBonus1Month(apiBefore.getSettingBonus1Month());

                /** ボーナス１設定日 */
                before.setSettingBonus1Day(apiBefore.getSettingBonus1Day());

                /** ボーナス２設定月 */
                before.setSettingBonus2Month(apiBefore.getSettingBonus2Month());

                /** ボーナス２設定日 */
                before.setSettingBonus2Day(apiBefore.getSettingBonus2Day());
            } else {
                before.setSettingBonusFlag("2");
            }

            /** 次の買付予定日 */
            if (apiBefore.getPlanDate() != null && apiBefore.getPlanDate().length() == 8) {
                before.setPlanDate(LocalDate.parse(apiBefore.getPlanDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            /** ボーナス月次の買付予定日１ */
            if (apiBefore.getBonusPlanDate1() != null && apiBefore.getBonusPlanDate1().length() == 8) {
                before.setBonusPlanDate1(LocalDate.parse(apiBefore.getBonusPlanDate1(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            /** ボーナス月次の買付予定日２ */
            if (apiBefore.getBonusPlanDate2() != null && apiBefore.getBonusPlanDate2().length() == 8) {
                before.setBonusPlanDate1(LocalDate.parse(apiBefore.getBonusPlanDate2(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            // ボーナス買付予定日
            before.setBonusPlanDate(IfaMutualFundAccumulateSettingUtil.getBonusPlanDate(apiBefore.getBonusPlanDate1(), apiBefore.getBonusPlanDate2()));

            /** 設定金額概算手数料 */
            before.setEstimateFundOrder(Optional.ofNullable(apiBefore.getEstimateFundOrder())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** ボーナス概算手数料 */
            before.setEstimateFundOrderBonus(Optional.ofNullable(apiBefore.getEstimateFundOrderBonus())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** 設定金額の次回買付日 */
            if (apiBefore.getNextReserveDate() != null && apiBefore.getNextReserveDate().length() == 8) {
                before.setNextReserveDate(LocalDate.parse(apiBefore.getNextReserveDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }
        }

        IfaMutualFundAccumulateSettingChangeData after = new IfaMutualFundAccumulateSettingChangeData();
        FundReserveSettingBaseOut apiAfter = api005Out.getReserveSettingChangeAfter();

        if (apiAfter != null) {
            /** 投資信託協会コード */
            after.setFundCode(apiAfter.getFundCode());
            /** 投資信託協会名（投資信託名） */
            after.setFundName(apiAfter.getFundName());

            a009Res.setBrandName(apiAfter.getFundName());

            /** 決済方法 */
            after.setPaymentMethod(
                    SafeType2IfaTypeUtil.IfaPaymentMethod.getIfaValueBySafeEnum(apiAfter.getPaymentMethod()));

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

            /** 1ヵ月あたりの設定金額（概算） */
            after.setOneMonthSumAmount(Optional.ofNullable(apiAfter.getOneMonthSumAmount())
                    .orElse(new BigDecimal(0)).toPlainString());

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

            /** NISA枠ぎりぎり注文 */
            after.setNisaBarelyBuyingType(SafeType2IfaTypeUtil.IfaNisaBuyableTypeEnum
                    .getIfaValueBySafeEnum(apiAfter.getNisaBarelyBuyingType()));


            /** 課税枠シフト注文 */
            after.setTaxShiftType(SafeType2IfaTypeUtil.IfaNisaExcessBuyableTypeEnum
                    .getIfaValueBySafeEnum(apiAfter.getTaxShiftType()));

            /** ボーナス設定有無 */
            if (apiAfter.isSettingBonusFlag()) {
                after.setSettingBonusFlag("1");

                /** ボーナス設定金額 */
                after.setSettingBonusAmount(Optional.ofNullable(apiAfter.getSettingBonusAmount())
                        .orElse(new BigDecimal(0)).toPlainString());

                /** ボーナス１設定月 */
                after.setSettingBonus1Month(apiAfter.getSettingBonus1Month());

                /** ボーナス１設定日 */
                after.setSettingBonus1Day(apiAfter.getSettingBonus1Day());

                /** ボーナス２設定月 */
                after.setSettingBonus2Month(apiAfter.getSettingBonus2Month());

                /** ボーナス２設定日 */
                after.setSettingBonus2Day(apiAfter.getSettingBonus2Day());
            } else {
                after.setSettingBonusFlag("2");
            }

            /** 次の買付予定日 */
            if (apiAfter.getPlanDate() != null && apiAfter.getPlanDate().length() == 8) {
                after.setPlanDate(LocalDate.parse(apiAfter.getPlanDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            /** ボーナス月次の買付予定日１ */
            if (apiAfter.getBonusPlanDate1() != null && apiAfter.getBonusPlanDate1().length() == 8) {
                after.setBonusPlanDate1(LocalDate.parse(apiAfter.getBonusPlanDate1(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            /** ボーナス月次の買付予定日２ */
            if (apiAfter.getBonusPlanDate2() != null && apiAfter.getBonusPlanDate2().length() == 8) {
                after.setBonusPlanDate1(LocalDate.parse(apiAfter.getBonusPlanDate2(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }

            // ボーナス買付予定日
            after.setBonusPlanDate(IfaMutualFundAccumulateSettingUtil.getBonusPlanDate(apiAfter.getBonusPlanDate1(), apiAfter.getBonusPlanDate2()));

            /** 設定金額概算手数料 */
            after.setEstimateFundOrder(Optional.ofNullable(apiAfter.getEstimateFundOrder())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** ボーナス概算手数料 */
            after.setEstimateFundOrderBonus(Optional.ofNullable(apiAfter.getEstimateFundOrderBonus())
                    .orElse(new BigDecimal(0)).toPlainString());

            /** 設定金額の次回買付日 */
            if (apiAfter.getNextReserveDate() != null && apiAfter.getNextReserveDate().length() == 8) {
                after.setNextReserveDate(LocalDate.parse(apiAfter.getNextReserveDate(), DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD)).format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD)));
            }
        }

        a009Res.setReserveSettingChangeBefore(before);
        a009Res.setReserveSettingChangeAfter(after);

        /**
         * ⑨ 【IFA】投信積立設定変更確認画面を表示する。
         */
        // レスポンスをコントローラーに返却する。
        resDtoList.add(a009Res);

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
}

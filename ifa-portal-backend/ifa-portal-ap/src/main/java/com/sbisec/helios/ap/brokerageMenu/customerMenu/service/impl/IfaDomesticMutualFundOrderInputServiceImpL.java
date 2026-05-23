package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.math.BigDecimal;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct017;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct023;
import com.sbisec.helios.ap.bizcommon.component.Fct024;
import com.sbisec.helios.ap.bizcommon.component.Fct025;
import com.sbisec.helios.ap.bizcommon.component.Fct039;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto.InquiryMutualFundBrand;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct023Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct024Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct025Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct039Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto.Brand;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct023Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct024Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct025Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct039Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticMutualFundOrderInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA009RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA009ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputA010ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputBrand;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputBuyingPower;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputCommRate;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputComplianceRankCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputDepositInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputPoint;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderInputSwitchingFavorableTreatmentLimit;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticMutualFundOrderInputService;
import com.sbisec.helios.ap.common.dao.SystemDateDao;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkRes;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkItem;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulk;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.SafeCommonService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.AccountSumWebData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateFundOrder1In;
import jp.co.sbisec.pcenter.dto.yanap.EstimateFundOrder1InData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateFundOrder1OutData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateFundOrder2ExtIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateFundOrder2ExtInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateFundOrder2ExtOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionSumWebInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionSumWebOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryFund;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundSettlementDateIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundSettlementDateInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundSettlementDateOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryIsaFundMaxBuyAmountIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryIsaFundMaxBuyAmountInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryIsaFundMaxBuyAmountOutData;
import jp.co.sbisec.pcenter.dto.yanap.ReinvestEntryInData;
import jp.co.sbisec.pcenter.dto.yanap.ReinvestEntryOutData;
import jp.co.sbisec.pcenter.dto.yanap.SimReinvestEntryDataI;
import jp.co.sbisec.pcenter.dto.yanap.SimReinvestEntryDataO;

/**
 * 画面ID：SUB0202_0401-02_1
 * 画面名：国内投信注文入力
 *
 * @author SCSK
 */
@Component(value = "cmpIfaDomesticMutualFundOrderInputService")
public class IfaDomesticMutualFundOrderInputServiceImpL implements IfaDomesticMutualFundOrderInputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticMutualFundOrderInputServiceImpL.class);

    @Autowired
    private IfaDomesticMutualFundOrderInputDao dao;

    @Autowired
    private CodeListService codelistservice;

    @Autowired
    private ApiWrapper apiWrapper;

    @Autowired
    private SystemDateDao systemDateDao;

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct006 fct006;

    @Autowired
    private Fct023 fct023;

    @Autowired
    private Fct003 fct003;

    @Autowired
    private Fct024 fct024;

    @Autowired
    private Fct017 fct017;

    @Autowired
    private Fct021 fct021;

    @Autowired
    private Fct025 fct025;

    @Autowired
    private Fct039 fct039;
    
	@Autowired
    private SafeFundTradeService safeFundTradeService;
	
    @Autowired
    private SafeCommonService safeCommonService;

    /** 対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";

    /** 権限なし */
    private static final String NO_AUTHORITY = "0";

    /** 取引停止口座 */
    private static final String STOP_TRADE_ACCOUNT = "1";

    /** 区分値.売買区分（投信） 買（購入） */
    private static final String BUY = "1";

    /** 区分値.売買区分（投信） 売（解約） */
    private static final String SELL = "2";

    /** 区分値.証券金銭種別_国内投信 */
    private static final String DOMESTIC_MUTUALFUND = "06";

    /** なし */
    private static final String NONE = "0";

    /** 契約 */
    private static final String CONTRACT = "1";

    /** 総合口座:△ */
    private static final String WHOLE_AMOUNT = " ";

    /** ジュニアNISA口座：1 */
    private static final String JR_NISA_ACCOUNT = "1";

    /** 可（NISA） */
    private static final String CAN_NISA = "3";

    /** 受入要否.不要 */
    private static final String ACCEPTANCE_FALSE = "0";

    /** 投信銘柄種別.通貨選択型 */
    private static final String BRAND_CLASS_SELECT = "1";

    /** 投信銘柄種別.複雑投信*/
    private static final String BRAND_CLASS_COMPLICATED = "2";

    /** CODE_正常終了 */
    private static final String CODE_SUCCESS = "      ";

    /** △ */
    private static final String SPACE = " ";

    /** "1" */
    private static final String ONE = "1";

    /** "△":通常口座およびJrNISA口座の第一口座 */
    private static final String GENERAL_JRNISA_SPACE = " ";

    /** "1"：JrNISA口座(第二口座のみ) */
    private static final String SECOND_ACCOUNT_JRNISA = "1";

    /** "2"：JrNISA口座(第一、第二口座両方) */
    private static final String FIRST_SECOND_ACCOUNT_JRNISA = "2";

    /** １：注文受付停止 */
    private static final String ORDER_STOP = "1";

    /** "T0"(国内投信（一般口投信・汎用累投）) */
    private static final String MUTUALFUND_GENERAL = "T0";

    /** "S":参照 */
    private static final String REFERENCE = "S";

    /** "1":コールセンター */
    private static final String CALLCENTER = "1";

    /** "001" */
    private static final String TOTAL_NUMBER = "001";

    /** ピリオド */
    private static final String PERIOD = ".";

    /** "0" */
    private static final String ZERO = "0";

    /** "00" */
    private static final String DOUBLE_ZERO = "00";

    /** "0000" */
    private static final String QUADRUPLE_ZERO = "0000";

    /** リアルデータ*/
    private static final int REAL_DATA = 1;

    /** "適用外" */
    private static final String NOT_APPLICAVLE = "適用外";

    /** "999.99%" */
    private static final String RATE_MAX_PERCENT = "999.99%";

    /** api004_ref-to */
    private static final int MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA = 100;

    /** EN0041（NISARO申込中） */
    private static final String EN0041 = "EN0041";

    /** EN0045（NISA勘定変更申込中） */
    private static final String EN0045 = "EN0045";

    /** EG0028（保有残高なし） */
    private static final String EG0028 = "EG0028";

    /** '1'：分配金受取 */
    private static final String REINVEST_RECEIPT = "1";

    /** '2'：分配金再投資 */
    private static final String REINVEST_RE = "2";

    /** "受取" */
    private static final String RECEIPT = "受取";

    /** "再投資"*/
    private static final String RE = "再投資";

    /** "設定なし" */
    private static final String NO_SETTING = "設定なし";

    /** 項目表示 */
    private static final String DISPLAY = "項目表示";

    /** 項目非表示 */
    private static final String NO_DISPLAY = "項目非表示";

    /** 項目非活性 */
    private static final String NO_ACTIVE = "項目非活性";

    /** 一部使用する */
    private static final String PART_USE = "1";

    /** 国籍コード */
    private static final String NATION_CODE = "99";

    /** 通貨コード */
    private static final String CURRENCY_CODE = "999";

    /** POINT_TYPE */
    private static final String POINT_TYPE = "POINT_TYPE";

    /** "0":国内 */
    private static final String DOMESTIC = "0";

    /** "3△":投信 */
    private static final String MUTUALFUND = "3 ";

    /** "1":買付注文 */
    private static final String PURCHASE_ORDER = "1";

    /** 該当する */
    private static final String CONCERNED = "1";

    /** 金額指定 */
    private static final String AMOUNT_SELECT = "1";

    /** 口数指定 */
    private static final String UNIT_SELECT = "2";

    /** 全額売却 */
    private static final String AMOUNT_SELL = "3";

    /** API001_QUERYFUND */
    private static final int MAX_QUERY_FUND = 100;

    /** 検索番号int->String変換用 */
    private static final DecimalFormat DF = new DecimalFormat("00000");

    // ファンドタイプ
    private enum FundType {

        // 一般
        GENERAL("1"),
        // 累投
        CUMULATIVE_PITCH("2"),
        // 積立
        ACCUMULATE("3"),
        // 累投／積立
        CUMULATIVE_PITCH_AND_ACCUMULATE("4");

        private String key;

        private FundType(String key) {

            this.key = key;
        }
    }

    // 取引種別
    private enum TradeCd {

        // 購入(一般)
        BUY_GENERAL("0"),
        // 購入(累投)
        BUY_CUMULATIVE("1"),
        // 解約（一般）
        CANCEL_GENERAL("7"),
        // 解約（累投）
        CANCEL_CUMULATIVE("8"),
        // 買取（一般）
        PURCHASE_GENERAL("3"),
        // 買取（累投）
        PURCHASE_CUMULATIVE("4"),
        // 全額買取
        AMOUNT_PURCHASE("6"),
        // 全額解約
        AMOUNT_CANCEL("10");

        private final String key;

        TradeCd(final String key) {

            this.key = key;
        }
    }

    // 預り区分
    private enum MutualFundDeposit {

        // 特定/一般
        SPECIFIC_GENERAL(" "),
        // 特定
        SPECIFIC("0"),
        // 一般
        GENERAL("1"),
        // 非特定口座預り又は未登録口座預り又はカバードワラント商品の場合
        NON_SPECIFIC_ACCOUNT("-"),
        // 旧NISA
        OLD_NISA("4"),
        // Jr特定/Jr一般
        JR_SPECIFIC_JR_GENERAL("5"),
        // Jr一般
        JR_GENERAL("6"),
        // JrNISA
        JR_NISA("7"),
        // 旧つみたてNISA
        OLD_NISA_RESERVE("8"),
        // 総合NISA（成長投資枠）
        WHOLE_NISA("H"),
        // 総合NISA（つみたて投資枠）
        WHOLE_NISA_RESERVE("I"),
        // JrNISA（継続管理勘定）
        JR_NISA_CONTINUOUS("J");

        private String key;

        private MutualFundDeposit(String key) {

            this.key = key;
        }
    }

    // 特定口座区分
    private enum SpecificAccount {

        // 特定口座(代行納付)
        SPECIFIC_AGENCY("1"),
        // 特定口座(確定申告)
        SPECIFIC_DEFINITION("2"),
        // 非特定口座
        NO_SPECIFIC("3"),
        // 未登録
        NON(" ");

        private String key;

        private SpecificAccount(String key) {

            this.key = key;
        }
    }

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
        // errors.fnd.selectedBrand.noInformation
        ERRORS_NOINFORMATION("errors.fnd.selectedBrand.noInformation"),
        // errors.cmn.selectedAccountCourse.unavailable
        ERRORS_SELECTEDACCOUNT_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        // errors.fnd.selectedBrand.noPriceAndCommission
        ERRORS_NOPRICEANDCOMMISSION("errors.fnd.selectedBrand.noPriceAndCommission"),
        // errors.fnd.selectedBrand.currencySelectionType
        ERRORS_CURRENCYSELECTIONTYPE("errors.fnd.selectedBrand.currencySelectionType"),
        // errors.fnd.selectedBrand.complexType
        ERRORS_COMPLEXTYPE("errors.fnd.selectedBrand.complexType"),
        // errors.fnd.selectedBrand.orderStopped
        ERRORS_ORDERSTOPPED("errors.fnd.selectedBrand.orderStopped"),
        // errors.fnd.selectedBrand.notTradeable
        ERRORS_NOTTRADEABLE("errors.fnd.selectedBrand.notTradeable"),
        // errors.fnd.selectedBrand.outOfIfaTrade
        ERRORS_OUTOFIFATRADE("errors.fnd.selectedBrand.outOfIfaTrade"),
        // errors.unitCheck
        ERRORS_UNITCHECT("errors.unitCheck"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICEERRORCHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATIONCHECK("errors.informationCheck"),
        // warnings.cmn.noticeWarningCheck
        WRANINGS_NOTICEWARNINGCHECK("warnings.cmn.noticeWarningCheck"),
        // warnings.cmm.informationCheck
        WARNINGS_INFORMATIONCHECK("warnings.cmm.informationCheck"),
        // warnings.fnd.selectedBrand.needShortTermSaleApproval
        WARNINGS_SALEAPPROVAL("warnings.fnd.selectedBrand.needShortTermSaleApproval"),
        // warnings.fnd.selectedBrand.saleBeforeRedemption
        WARNIGNS_SALEBEFOREREDEMPTION("warnings.fnd.selectedBrand.saleBeforeRedemption"),
        // errors.cmn.orderExecution.failed
        ERRORS_ORDEREXECUTION("errors.cmn.orderExecution.failed"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CCSIDUNREGISTERED("errors.cmn.ccsid.unregistered"),
    	// errors.fnd.selectedBrand.noInformation
        ERRORS_NO_INFO("errors.fnd.selectedBrand.noInformation"),
        // warnings.cmn.referPoint.systemError
        WARNIGNS_REFERPOINT_SYSTEMERROR("warnings.cmn.referPoint.systemError");

        private String key;

        private MessageId(String key) {

            this.key = key;
        }
    }
        
    /** 目論見書閲覧区分 */
    private enum ProspecFundsProspectus {
        	
        // 閲覧済・郵送完了
        PROSPECTUS("0");

        private final String value;
            
        ProspecFundsProspectus(final String value) {
                
            this.value = value;
        }
            
        private String getValue() {
                
            return value;
        }
    }
 

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA001RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA001ResponseDto
     * model リクエスト：IfaDomesticMutualFundOrderInputSql001RequestModel, IfaDomesticMutualFundOrderInputSql002RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderInputSql001ResponseModel, IfaDomesticMutualFundOrderInputSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    @Override
    public DataList<IfaDomesticMutualFundOrderInputA001ResponseDto> initializeA001(
            IfaDomesticMutualFundOrderInputA001RequestDto dtoReq) throws Exception {

        DataList<IfaDomesticMutualFundOrderInputA001ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderInputA001ResponseDto>();
        List<IfaDomesticMutualFundOrderInputA001ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderInputA001ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundOrderInputServiceImplL.initializeA001");
        }

        // ①利用者の口座に対する権限チェックを行う。（FCT001）
        // 以下を入力値としてFCT001を実行
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
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

        // ②銘柄情報を取得する（FCT023）
        // 以下を入力値としてFCT023を実行
        InputFct023Dto fct023Req = new InputFct023Dto();
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        fct023Req.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        OutputFct023Dto fct023Res = new OutputFct023Dto();
        fct023Res = fct023.getData(fct023Req);
        // if(FCT023の取得データなし){
        // FCT023上、データ取得できなかったらNRIコード以外全部null
        if (StringUtil.isNullOrEmpty(fct023Res.getFundOfficalName()) && fct023Res.getBasePriceUnit() == null
                && fct023Res.getNumberOfSalesMinimum() == null && fct023Res.getSalesMinimunUnitPriceAfter2nd() == null
                && fct023Res.getNumberOfSalesUnit() == null && fct023Res.getSalesTradingUnitPriceAfter2nd() == null
                && fct023Res.getNumberOfCancelTradingUnit() == null
                && fct023Res.getCancelTradingUnitPriceAfter2nd() == null
                && StringUtil.isNullOrEmpty(fct023Res.getOrderDeadlineTime())
                && StringUtil.isNullOrEmpty(fct023Res.getSaleMethod())
                && StringUtil.isNullOrEmpty(fct023Res.getFundType())
                && StringUtil.isNullOrEmpty(fct023Res.getReinvestmentDivison())
                && StringUtil.isNullOrEmpty(fct023Res.getSpecialType())) {
            // 銘柄マスタ取得エラー(errors.fnd.selectedBrand.noInformation)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOINFORMATION.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOINFORMATION.key));
            return dtoRes;
        }

        // 取引種別算出
        String tradeCd = getTradeCd(dtoReq.getMutualFundSellBuyType(), fct023Res.getFundType());

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
        fct003Req.setTradeCd(tradeCd);
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // if(媒介可取引有無 = "0"(なし)){
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), NONE)) {
            // 取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "4", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, IfaCommonUtil
                            .getMessage(MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, new String[] { codeName }));
            return dtoRes;
        }

        // ④選択口座、選択預り区分、預り区分リストを取得する。以下にレスポンスパラメータを記載
        Map<String, Object> a001Parameter = new HashMap<>();
        a001Parameter = getParameter(cc, dtoReq.getDepositType(), dtoReq.getMutualFundSellBuyType(),
                fct023Res.getFundType());

        // ⑤銘柄の基準価額、手数料率リスト、乗換優遇率および扱者個別データ有無を取得する（FCT024）
        // 以下を入力値としてFCT024を実行
        InputFct024Dto fct024Req = new InputFct024Dto();
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        fct024Req.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        // ・顧客共通情報.扱者コード
        fct024Req.setDealerNumber(cc.getDealerNumber());
        OutputFct024Dto fct024Res = new OutputFct024Dto();
        fct024Res = fct024.getData(fct024Req);
        // FCT024.協会コードがセットされていない = データなしし
        if (StringUtil.isNullOrEmpty(fct024Res.getKyoukaiCd())) {
            // 銘柄基準価格、手数料取得エラー(errors.fnd.selectedBrand.noPriceAndCommission)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_NOPRICEANDCOMMISSION.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOPRICEANDCOMMISSION.key));
            return dtoRes;
        }
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // ⑥買いの場合、通貨選択型投信　または　複雑型投信チェックを行う（FCT017）
            // 以下を入力値としてFCT017を実行
            InputFct017Dto fct017Req = new InputFct017Dto();
            // ・顧客共通情報.部店コード
            fct017Req.setButenCode(cc.getButenCode());
            // ・顧客共通情報.口座番号
            fct017Req.setAccountNumber(cc.getAccountNumber());
            // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
            List<InquiryMutualFundBrand> inquiryMutualFundBrandList = new ArrayList<>();
            InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
            inquiryMutualFundBrand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
            fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
            OutputFct017Dto fct017Res = new OutputFct017Dto();
            fct017Res = fct017.getData(fct017Req);
            // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
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
            // ⑦買いの場合、注文停止および買付可能チェックを行う（API001）(チェック処理)
            QueryFundOutData api001Res = api001(cc, apiErrorUtil, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues());
            if (api001Res == null) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // データなし：次の処理へ。
            if (!ObjectUtils.isEmpty(api001Res.getQueryFund())) {
                for (QueryFund queryFund : api001Res.getQueryFund()) {
                    // 注文停止フラグ=１：注文受付停止：注文停止エラーを返す。
                    if (StringUtils.equals(queryFund.getStopOrderFlag(), ORDER_STOP)) {
                        // 注文停止エラー(errors.fnd.selectedBrand.orderStopped)を返す。
                        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                MessageId.ERRORS_ORDERSTOPPED.key,
                                IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERSTOPPED.key));
                        return dtoRes;
                    }
                }
            }

            // ⑧買いの場合、Indigo銘柄設定チェックを行う。（FCT025）
            // 以下を入力値としてFCT025を実行
            InputFct025Dto fct025Req = new InputFct025Dto();
            // ・顧客共通情報.仲介業者コード
            fct025Req.setBrokerCode(cc.getBrokerCode());
            // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
            List<com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand> fct025BrandList = new ArrayList<com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand>();
            com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand fct025Brand = new com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand();
            fct025Brand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            fct025BrandList.add(fct025Brand);
            fct025Req.setBrandList(fct025BrandList);
            OutputFct025Dto fct025Res = new OutputFct025Dto();
            fct025Res = fct025.doCheck(fct025Req);
            for (com.sbisec.helios.ap.bizcommon.model.OutputFct025Dto.Brand brand : fct025Res.getBrandList()) {
                // 銘柄リスト.E*TRADE扱い可否=0:不可 の場合
                if (StringUtils.equals(brand.getIsEtradeService(), NONE)) {
                    // 銘柄ステータス取引不可エラー（errors.fnd.selectedBrand.notTradeable）を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_NOTTRADEABLE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_NOTTRADEABLE.key));
                    return dtoRes;
                }
                // 銘柄リスト.仲介業者扱可否=0:不可：仲介業者取引不可エラーを返す。
                if (StringUtils.equals(brand.getIsBrokerService(), NONE)) {
                    // 仲介業者取引不可エラー(errors.fnd.selectedBrand.outOfIfaTrade)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_OUTOFIFATRADE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFIFATRADE.key));
                    return dtoRes;
                }
            }
        }

        QueryFundOutData api001Res2 = new QueryFundOutData();
        QueryFundSettlementDateOutData api002Res = new QueryFundSettlementDateOutData();
        QueryAccountBalanceOutData api003Res = new QueryAccountBalanceOutData();
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // api001実行(乗換優遇限度額を取得)
            api001Res2 = api001(cc, apiErrorUtil, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues());
            if (api001Res2 == null) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // api002実行
            api002Res = api002(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), fct023Res.getFundType());
            if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }

            // api003実行
            api003Res = api003(cc);
            if (apiErrorUtil.isError(api003Res.getShubetu(), api003Res.getCode(), api003Res.getMessage())) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
        }

        // 選択口座
        String selectAccountType = (String) a001Parameter.get("selectAccountType");

        QueryAccountPositionSumWebOutData api004Res = new QueryAccountPositionSumWebOutData();
        if (StringUtils.equals(tradeCd, TradeCd.PURCHASE_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.PURCHASE_CUMULATIVE.key)
                || StringUtils.equals(tradeCd, TradeCd.AMOUNT_PURCHASE.key)
                || StringUtils.equals(tradeCd, TradeCd.CANCEL_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.CANCEL_CUMULATIVE.key)
                || StringUtils.equals(tradeCd, TradeCd.AMOUNT_CANCEL.key)) {
            // api004実行
            api004Res = api004(cc, selectAccountType, apiErrorUtil);
            if (api004Res == null) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
        }

        QueryIsaFundMaxBuyAmountOutData api005Res = new QueryIsaFundMaxBuyAmountOutData();
        ReinvestEntryOutData api006Res = new ReinvestEntryOutData();
        OutputFct039Dto fct039Res = new OutputFct039Dto();

        // 取引種別（国内投信）
        String tradeCdMutualFund = getTradeCdMutualFund(dtoReq.getMutualFundSellBuyType(), fct023Res);
        // 乗換優遇枠適用表示状態
        String transfersPreferentialQuotaApplication = getTransfersPreferentialQuotaApplication(api001Res2, api002Res, selectAccountType, tradeCdMutualFund);

        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // api005実行
            String api005transfersPreferentialQuotaApplication = null;
            // 乗換優遇枠適用表示状態="項目非活性"の場合、乗換優遇枠を適用しない
            if (StringUtils.equals(transfersPreferentialQuotaApplication, NO_ACTIVE)) {
                api005transfersPreferentialQuotaApplication = ZERO;
            }
            api005Res = api005(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), api005transfersPreferentialQuotaApplication, selectAccountType);
            if (!EN0041.equals(api005Res.getCode()) && !EN0045.equals(api005Res.getCode())
                    && apiErrorUtil.isError(api005Res.getShubetu(), api005Res.getCode(), api005Res.getMessage())) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // api006実行
            if (StringUtils.equals(fct023Res.getFundType(), FundType.CUMULATIVE_PITCH.key)) {
                api006Res = api006(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues());
                if (!EG0028.equals(api006Res.getCode())
                        && apiErrorUtil.isError(api006Res.getShubetu(), api006Res.getCode(), api006Res.getMessage())) {
                    return apiErrorUtil.createDataList(Collections.emptyList(), null);
                }
            }
            // fct039実行
            fct039Res = fct039(cc);
            if (!StringUtil.isNullOrEmpty(fct039Res.getScreenMessage())) {
                // 画面メッセージ
                apiErrorUtil.addPomApiError(MessageId.WARNIGNS_REFERPOINT_SYSTEMERROR.key, fct039Res.getScreenMessage());
            }
        }

        // SQL001実行
        IfaDomesticMutualFundOrderInputSql001ResponseModel selSql001Res = dao
                .selectIfaDomesticMutualFundOrderInputSql001();

        // SQL002実行
        IfaDomesticMutualFundOrderInputSql002ResponseModel selSql002Res = dao
                .selectIfaDomesticMutualFundOrderInputSql002();

        // 選択預り区分
        String selectDepositType = (String) a001Parameter.get("selectDepositType");
        // 預り区分リスト
        List<?> depositTypeListTemp = (List<?>) a001Parameter.get("depositTypeList");
        List<String> depositTypeList = new ArrayList<String>();
        if (!ObjectUtils.isEmpty(depositTypeListTemp)) {
            for (Object depositType : depositTypeListTemp) {
                depositTypeList.add((String) depositType);
            }
        }

        // レスポンス設定
        IfaDomesticMutualFundOrderInputA001ResponseDto resDto = setResponse(cc, dtoReq.getMutualFundSellBuyType(),
                dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), dtoReq.getDepositType(), selectAccountType,
                selectDepositType, depositTypeList, fct023Res, fct024Res, api001Res2, api002Res, api003Res, api004Res,
                api005Res, api006Res, fct039Res, selSql001Res, selSql002Res, tradeCd, tradeCdMutualFund, transfersPreferentialQuotaApplication);
        // 目論見書チェック区分
        resDto.setDispatchId(dtoReq.getDispatchId());
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);

        return dtoRes;
    }

    /**
     * アクションID：A004
     * アクション名：リセット
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA004RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception リセットの際、例外が発生した場合
     */
    @Override
    public DataList<IfaDomesticMutualFundOrderInputA004ResponseDto> resetA004(
            IfaDomesticMutualFundOrderInputA004RequestDto dtoReq) throws Exception {

        DataList<IfaDomesticMutualFundOrderInputA004ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderInputA004ResponseDto>();
        List<IfaDomesticMutualFundOrderInputA004ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderInputA004ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundOrderInputServiceImplL.resetA004");
        }

        // A001の②、④～⑰を行う。
        // ②銘柄情報を取得する（FCT023）
        // 以下を入力値としてFCT023を実行
        InputFct023Dto fct023Req = new InputFct023Dto();
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        fct023Req.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        OutputFct023Dto fct023Res = new OutputFct023Dto();
        fct023Res = fct023.getData(fct023Req);
        // if(FCT023の取得データなし){
        // FCT023上、データ取得できなかったらNRIコード以外全部null
        if (StringUtil.isNullOrEmpty(fct023Res.getFundOfficalName()) && fct023Res.getBasePriceUnit() == null
                && fct023Res.getNumberOfSalesMinimum() == null && fct023Res.getSalesMinimunUnitPriceAfter2nd() == null
                && fct023Res.getNumberOfSalesUnit() == null && fct023Res.getSalesTradingUnitPriceAfter2nd() == null
                && fct023Res.getNumberOfCancelTradingUnit() == null
                && fct023Res.getCancelTradingUnitPriceAfter2nd() == null
                && StringUtil.isNullOrEmpty(fct023Res.getOrderDeadlineTime())
                && StringUtil.isNullOrEmpty(fct023Res.getSaleMethod())
                && StringUtil.isNullOrEmpty(fct023Res.getFundType())
                && StringUtil.isNullOrEmpty(fct023Res.getReinvestmentDivison())
                && StringUtil.isNullOrEmpty(fct023Res.getSpecialType())) {
            // 銘柄マスタ取得エラー(errors.fnd.selectedBrand.noInformation)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOINFORMATION.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOINFORMATION.key));
            return dtoRes;
        }

        // ④選択口座、選択預り区分、預り区分リストを取得する。以下にレスポンスパラメータを記載
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        Map<String, Object> a001Parameter = new HashMap<>();
        a001Parameter = getParameter(cc, dtoReq.getDepositType(), dtoReq.getMutualFundSellBuyType(),
                fct023Res.getFundType());

        // ⑤銘柄の基準価額、手数料率リスト、乗換優遇率および扱者個別データ有無を取得する（FCT024）
        // 以下を入力値としてFCT024を実行
        InputFct024Dto fct024Req = new InputFct024Dto();
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        fct024Req.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        // ・顧客共通情報.扱者コード
        fct024Req.setDealerNumber(cc.getDealerNumber());
        OutputFct024Dto fct024Res = new OutputFct024Dto();
        fct024Res = fct024.getData(fct024Req);
        // FCT024.協会コードがセットされていない = データなしし
        if (StringUtil.isNullOrEmpty(fct024Res.getKyoukaiCd())) {
            // 銘柄基準価格、手数料取得エラー(errors.fnd.selectedBrand.noPriceAndCommission)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_NOPRICEANDCOMMISSION.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOPRICEANDCOMMISSION.key));
            return dtoRes;
        }
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 取引種別算出
        String tradeCd = getTradeCd(dtoReq.getMutualFundSellBuyType(), fct023Res.getFundType());

        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // ⑥買いの場合、通貨選択型投信　または　複雑型投信チェックを行う（FCT017）
            // 以下を入力値としてFCT017を実行
            InputFct017Dto fct017Req = new InputFct017Dto();
            // ・顧客共通情報.部店コード
            fct017Req.setButenCode(cc.getButenCode());
            // ・顧客共通情報.口座番号
            fct017Req.setAccountNumber(cc.getAccountNumber());
            // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
            List<InquiryMutualFundBrand> inquiryMutualFundBrandList = new ArrayList<>();
            InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
            inquiryMutualFundBrand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
            fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
            OutputFct017Dto fct017Res = new OutputFct017Dto();
            fct017Res = fct017.getData(fct017Req);
            // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
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

            // ⑦買いの場合、注文停止および買付可能チェックを行う（API001）(チェック処理)
            QueryFundOutData api001Res = api001(cc, apiErrorUtil, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues());
            if (api001Res == null) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // データなし：次の処理へ。
            if (!ObjectUtils.isEmpty(api001Res.getQueryFund())) {
                for (QueryFund queryFund : api001Res.getQueryFund()) {
                    // 注文停止フラグ=１：注文受付停止：注文停止エラーを返す。
                    if (StringUtils.equals(queryFund.getStopOrderFlag(), ORDER_STOP)) {
                        // 注文停止エラー(errors.fnd.selectedBrand.orderStopped)を返す。
                        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                MessageId.ERRORS_ORDERSTOPPED.key,
                                IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERSTOPPED.key));
                        return dtoRes;
                    }
                }
            }

            // ⑧買いの場合、Indigo銘柄設定チェックを行う。（FCT025）
            // 以下を入力値としてFCT025を実行
            InputFct025Dto fct025Req = new InputFct025Dto();
            // ・顧客共通情報.仲介業者コード
            fct025Req.setBrokerCode(cc.getBrokerCode());
            // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
            List<com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand> fct025BrandList = new ArrayList<com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand>();
            com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand fct025Brand = new com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand();
            fct025Brand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            fct025BrandList.add(fct025Brand);
            fct025Req.setBrandList(fct025BrandList);
            OutputFct025Dto fct025Res = new OutputFct025Dto();
            fct025Res = fct025.doCheck(fct025Req);
            for (com.sbisec.helios.ap.bizcommon.model.OutputFct025Dto.Brand brand : fct025Res.getBrandList()) {
                // 銘柄リスト.E*TRADE扱い可否=0:不可 の場合
                if (StringUtils.equals(brand.getIsEtradeService(), NONE)) {
                    // 銘柄ステータス取引不可エラー（errors.fnd.selectedBrand.notTradeable）を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_NOTTRADEABLE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_NOTTRADEABLE.key));
                    return dtoRes;
                }
                // 銘柄リスト.仲介業者扱可否=0:不可：仲介業者取引不可エラーを返す。
                if (StringUtils.equals(brand.getIsBrokerService(), NONE)) {
                    // 仲介業者取引不可エラー(errors.fnd.selectedBrand.outOfIfaTrade)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_OUTOFIFATRADE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFIFATRADE.key));
                    return dtoRes;
                }
            }
        }

        QueryFundOutData api001Res2 = new QueryFundOutData();
        QueryFundSettlementDateOutData api002Res = new QueryFundSettlementDateOutData();
        QueryAccountBalanceOutData api003Res = new QueryAccountBalanceOutData();
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // api001実行(乗換優遇限度額を取得)
            api001Res2 = api001(cc, apiErrorUtil, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues());
            if (api001Res2 == null) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // api002実行
            api002Res = api002(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), fct023Res.getFundType());
            if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // api003実行
            api003Res = api003(cc);
            if (apiErrorUtil.isError(api003Res.getShubetu(), api003Res.getCode(), api003Res.getMessage())) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
        }

        // 選択口座
        String selectAccountType = (String) a001Parameter.get("selectAccountType");

        QueryAccountPositionSumWebOutData api004Res = new QueryAccountPositionSumWebOutData();
        if (StringUtils.equals(tradeCd, TradeCd.PURCHASE_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.PURCHASE_CUMULATIVE.key)
                || StringUtils.equals(tradeCd, TradeCd.AMOUNT_PURCHASE.key)
                || StringUtils.equals(tradeCd, TradeCd.CANCEL_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.CANCEL_CUMULATIVE.key)
                || StringUtils.equals(tradeCd, TradeCd.AMOUNT_CANCEL.key)) {
            // api004実行
            api004Res = api004(cc, selectAccountType, apiErrorUtil);
            if (api004Res == null) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
        }

        QueryIsaFundMaxBuyAmountOutData api005Res = new QueryIsaFundMaxBuyAmountOutData();
        ReinvestEntryOutData api006Res = new ReinvestEntryOutData();
        OutputFct039Dto fct039Res = new OutputFct039Dto();

        // 取引種別（国内投信）
        String tradeCdMutualFund = getTradeCdMutualFund(dtoReq.getMutualFundSellBuyType(), fct023Res);
        // 乗換優遇枠適用表示状態
        String transfersPreferentialQuotaApplication = getTransfersPreferentialQuotaApplication(api001Res2, api002Res, selectAccountType, tradeCdMutualFund);

        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // api005実行
            String api005transfersPreferentialQuotaApplication = null;
            // 乗換優遇枠適用表示状態="項目非活性"の場合、乗換優遇枠を適用しない
            if (StringUtils.equals(transfersPreferentialQuotaApplication, NO_ACTIVE)) {
                api005transfersPreferentialQuotaApplication = ZERO;
            }
            api005Res = api005(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), api005transfersPreferentialQuotaApplication, selectAccountType);
            if (!EN0041.equals(api005Res.getCode()) && !EN0045.equals(api005Res.getCode())
                    && apiErrorUtil.isError(api005Res.getShubetu(), api005Res.getCode(), api005Res.getMessage())) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // api006実行
            if (StringUtils.equals(fct023Res.getFundType(), FundType.CUMULATIVE_PITCH.key)) {
                api006Res = api006(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues());
                if (!EG0028.equals(api006Res.getCode())
                        && apiErrorUtil.isError(api006Res.getShubetu(), api006Res.getCode(), api006Res.getMessage())) {
                    return apiErrorUtil.createDataList(Collections.emptyList(), null);
                }
            }
            // fct039実行
            fct039Res = fct039(cc);
            if (!StringUtil.isNullOrEmpty(fct039Res.getScreenMessage())) {
                // 画面メッセージ
                apiErrorUtil.addPomApiError(MessageId.WARNIGNS_REFERPOINT_SYSTEMERROR.key, fct039Res.getScreenMessage());
            }
        }

        // SQL001実行
        IfaDomesticMutualFundOrderInputSql001ResponseModel selSql001Res = dao
                .selectIfaDomesticMutualFundOrderInputSql001();

        // SQL002実行
        IfaDomesticMutualFundOrderInputSql002ResponseModel selSql002Res = dao
                .selectIfaDomesticMutualFundOrderInputSql002();

        // 選択預り区分
        String selectDepositType = (String) a001Parameter.get("selectDepositType");
        // 預り区分リスト
        List<?> depositTypeListTemp = (List<?>) a001Parameter.get("depositTypeList");
        List<String> depositTypeList = new ArrayList<String>();
        if (!ObjectUtils.isEmpty(depositTypeListTemp)) {
            for (Object depositType : depositTypeListTemp) {
                depositTypeList.add((String) depositType);
            }
        }

        // レスポンス設定
        IfaDomesticMutualFundOrderInputA001ResponseDto a001ResDto = setResponse(cc, dtoReq.getMutualFundSellBuyType(),
                dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), dtoReq.getDepositType(), selectAccountType,
                selectDepositType, depositTypeList, fct023Res, fct024Res, api001Res2, api002Res, api003Res, api004Res,
                api005Res, api006Res, fct039Res, selSql001Res, selSql002Res, tradeCd, tradeCdMutualFund, transfersPreferentialQuotaApplication);
        IfaDomesticMutualFundOrderInputA004ResponseDto resDto = new IfaDomesticMutualFundOrderInputA004ResponseDto();
        BeanUtils.copyProperties(resDto, a001ResDto);

        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        return apiErrorUtil.createDataList(resDtoList, null);
    }

    /**
     * アクションID：A005
     * アクション名：口座選択
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA005RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA005ResponseDto
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 口座選択の際、例外が発生した場合
     */
    @Override
    public DataList<IfaDomesticMutualFundOrderInputA005ResponseDto> accountSelectionA005(
            IfaDomesticMutualFundOrderInputA005RequestDto dtoReq) throws Exception {

        DataList<IfaDomesticMutualFundOrderInputA005ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderInputA005ResponseDto>();
        List<IfaDomesticMutualFundOrderInputA005ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderInputA005ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundOrderInputServiceImplL.accountSelectionA005");
        }
        // A001の②、④～⑰を行う。
        // ②銘柄情報を取得する（FCT023）
        // 以下を入力値としてFCT023を実行
        InputFct023Dto fct023Req = new InputFct023Dto();
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        fct023Req.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        OutputFct023Dto fct023Res = new OutputFct023Dto();
        fct023Res = fct023.getData(fct023Req);
        // if(FCT023の取得データなし){
        // FCT023上、データ取得できなかったらNRIコード以外全部null
        if (StringUtil.isNullOrEmpty(fct023Res.getFundOfficalName()) && fct023Res.getBasePriceUnit() == null
                && fct023Res.getNumberOfSalesMinimum() == null && fct023Res.getSalesMinimunUnitPriceAfter2nd() == null
                && fct023Res.getNumberOfSalesUnit() == null && fct023Res.getSalesTradingUnitPriceAfter2nd() == null
                && fct023Res.getNumberOfCancelTradingUnit() == null
                && fct023Res.getCancelTradingUnitPriceAfter2nd() == null
                && StringUtil.isNullOrEmpty(fct023Res.getOrderDeadlineTime())
                && StringUtil.isNullOrEmpty(fct023Res.getSaleMethod())
                && StringUtil.isNullOrEmpty(fct023Res.getFundType())
                && StringUtil.isNullOrEmpty(fct023Res.getReinvestmentDivison())
                && StringUtil.isNullOrEmpty(fct023Res.getSpecialType())) {
            // 銘柄マスタ取得エラー(errors.fnd.selectedBrand.noInformation)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOINFORMATION.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOINFORMATION.key));
            return dtoRes;
        }

        // ④選択口座、選択預り区分、預り区分リストを取得する。以下にレスポンスパラメータを記載
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        Map<String, Object> a001Parameter = new HashMap<>();
        a001Parameter = getParameter(cc, dtoReq.getDepositType(), dtoReq.getMutualFundSellBuyType(),
                fct023Res.getFundType());

        // ⑤銘柄の基準価額、手数料率リスト、乗換優遇率および扱者個別データ有無を取得する（FCT024）
        // 以下を入力値としてFCT024を実行
        InputFct024Dto fct024Req = new InputFct024Dto();
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        fct024Req.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        // ・顧客共通情報.扱者コード
        fct024Req.setDealerNumber(cc.getDealerNumber());
        OutputFct024Dto fct024Res = new OutputFct024Dto();
        fct024Res = fct024.getData(fct024Req);
        // FCT024.協会コードがセットされていない = データなしし
        if (StringUtil.isNullOrEmpty(fct024Res.getKyoukaiCd())) {
            // 銘柄基準価格、手数料取得エラー(errors.fnd.selectedBrand.noPriceAndCommission)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_NOPRICEANDCOMMISSION.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOPRICEANDCOMMISSION.key));
            return dtoRes;
        }

        // 取引種別算出
        String tradeCd = getTradeCd(dtoReq.getMutualFundSellBuyType(), fct023Res.getFundType());
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // ⑥買いの場合、通貨選択型投信　または　複雑型投信チェックを行う（FCT017）
            // 以下を入力値としてFCT017を実行
            InputFct017Dto fct017Req = new InputFct017Dto();
            // ・顧客共通情報.部店コード
            fct017Req.setButenCode(cc.getButenCode());
            // ・顧客共通情報.口座番号
            fct017Req.setAccountNumber(cc.getAccountNumber());
            // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
            List<InquiryMutualFundBrand> inquiryMutualFundBrandList = new ArrayList<>();
            InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
            inquiryMutualFundBrand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
            fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
            OutputFct017Dto fct017Res = new OutputFct017Dto();
            fct017Res = fct017.getData(fct017Req);
            // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
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

            // ⑦買いの場合、注文停止および買付可能チェックを行う（API001）(チェック処理)
            QueryFundOutData api001Res = api001(cc, apiErrorUtil, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues());
            if (api001Res == null) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // データなし：次の処理へ。
            if (!ObjectUtils.isEmpty(api001Res.getQueryFund())) {
                for (QueryFund queryFund : api001Res.getQueryFund()) {
                    // 注文停止フラグ=１：注文受付停止：注文停止エラーを返す。
                    if (StringUtils.equals(queryFund.getStopOrderFlag(), ORDER_STOP)) {
                        // 注文停止エラー(errors.fnd.selectedBrand.orderStopped)を返す。
                        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                                MessageId.ERRORS_ORDERSTOPPED.key,
                                IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERSTOPPED.key));
                        return dtoRes;
                    }
                }
            }

            // ⑧買いの場合、Indigo銘柄設定チェックを行う。（FCT025）
            // 以下を入力値としてFCT025を実行
            InputFct025Dto fct025Req = new InputFct025Dto();
            // ・顧客共通情報.仲介業者コード
            fct025Req.setBrokerCode(cc.getBrokerCode());
            // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
            List<com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand> fct025BrandList = new ArrayList<com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand>();
            com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand fct025Brand = new com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand();
            fct025Brand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            fct025BrandList.add(fct025Brand);
            fct025Req.setBrandList(fct025BrandList);
            OutputFct025Dto fct025Res = new OutputFct025Dto();
            fct025Res = fct025.doCheck(fct025Req);
            for (com.sbisec.helios.ap.bizcommon.model.OutputFct025Dto.Brand brand : fct025Res.getBrandList()) {
                // 銘柄リスト.E*TRADE扱い可否=0:不可 の場合
                if (StringUtils.equals(brand.getIsEtradeService(), NONE)) {
                    // 銘柄ステータス取引不可エラー（errors.fnd.selectedBrand.notTradeable）を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_NOTTRADEABLE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_NOTTRADEABLE.key));
                    return dtoRes;
                }
                // 銘柄リスト.仲介業者扱可否=0:不可：仲介業者取引不可エラーを返す。
                if (StringUtils.equals(brand.getIsBrokerService(), NONE)) {
                    // 仲介業者取引不可エラー(errors.fnd.selectedBrand.outOfIfaTrade)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_OUTOFIFATRADE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFIFATRADE.key));
                    return dtoRes;
                }
            }
        }

        QueryFundOutData api001Res2 = new QueryFundOutData();
        QueryFundSettlementDateOutData api002Res = new QueryFundSettlementDateOutData();
        QueryAccountBalanceOutData api003Res = new QueryAccountBalanceOutData();
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // api001実行(乗換優遇限度額を取得)
            api001Res2 = api001(cc, apiErrorUtil, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues());
            if (api001Res2 == null) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // api002実行
            api002Res = api002(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), fct023Res.getFundType());
            if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // api003実行
            api003Res = api003(cc);
            if (apiErrorUtil.isError(api003Res.getShubetu(), api003Res.getCode(), api003Res.getMessage())) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
        }

        // 選択口座
        String selectAccountType = (String) a001Parameter.get("selectAccountType");

        QueryAccountPositionSumWebOutData api004Res = new QueryAccountPositionSumWebOutData();
        if (StringUtils.equals(tradeCd, TradeCd.PURCHASE_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.PURCHASE_CUMULATIVE.key)
                || StringUtils.equals(tradeCd, TradeCd.AMOUNT_PURCHASE.key)
                || StringUtils.equals(tradeCd, TradeCd.CANCEL_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.CANCEL_CUMULATIVE.key)
                || StringUtils.equals(tradeCd, TradeCd.AMOUNT_CANCEL.key)) {
            // api004実行
            api004Res = api004(cc, selectAccountType, apiErrorUtil);
            if (api004Res == null) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
        }

        QueryIsaFundMaxBuyAmountOutData api005Res = new QueryIsaFundMaxBuyAmountOutData();
        ReinvestEntryOutData api006Res = new ReinvestEntryOutData();
        OutputFct039Dto fct039Res = new OutputFct039Dto();

        // 取引種別（国内投信）
        String tradeCdMutualFund = getTradeCdMutualFund(dtoReq.getMutualFundSellBuyType(), fct023Res);
        // 乗換優遇枠適用表示状態
        String transfersPreferentialQuotaApplication = getTransfersPreferentialQuotaApplication(api001Res2, api002Res, selectAccountType, tradeCdMutualFund);

        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // api005実行
            String api005transfersPreferentialQuotaApplication = null;
            // 乗換優遇枠適用表示状態="項目非活性"の場合、乗換優遇枠を適用しない
            if (StringUtils.equals(transfersPreferentialQuotaApplication, NO_ACTIVE)) {
                api005transfersPreferentialQuotaApplication = ZERO;
            }
            api005Res = api005(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), api005transfersPreferentialQuotaApplication, selectAccountType);
            if (!EN0041.equals(api005Res.getCode()) && !EN0045.equals(api005Res.getCode())
                    && apiErrorUtil.isError(api005Res.getShubetu(), api005Res.getCode(), api005Res.getMessage())) {
                return apiErrorUtil.createDataList(Collections.emptyList(), null);
            }
            // api006実行
            if (StringUtils.equals(fct023Res.getFundType(), FundType.CUMULATIVE_PITCH.key)) {
                api006Res = api006(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues());
                if (!EG0028.equals(api006Res.getCode())
                        && apiErrorUtil.isError(api006Res.getShubetu(), api006Res.getCode(), api006Res.getMessage())) {
                    return apiErrorUtil.createDataList(Collections.emptyList(), null);
                }
            }
            // fct039実行
            fct039Res = fct039(cc);
            if (!StringUtil.isNullOrEmpty(fct039Res.getScreenMessage())) {
                // 画面メッセージ
                apiErrorUtil.addPomApiError(MessageId.WARNIGNS_REFERPOINT_SYSTEMERROR.key, fct039Res.getScreenMessage());
            }
        }

        // SQL001実行
        IfaDomesticMutualFundOrderInputSql001ResponseModel selSql001Res = dao
                .selectIfaDomesticMutualFundOrderInputSql001();

        // SQL002実行
        IfaDomesticMutualFundOrderInputSql002ResponseModel selSql002Res = dao
                .selectIfaDomesticMutualFundOrderInputSql002();

        // 選択預り区分
        String selectDepositType = (String) a001Parameter.get("selectDepositType");
        // 預り区分リスト
        List<?> depositTypeListTemp = (List<?>) a001Parameter.get("depositTypeList");
        List<String> depositTypeList = new ArrayList<String>();
        if (!ObjectUtils.isEmpty(depositTypeListTemp)) {
            for (Object depositType : depositTypeListTemp) {
                depositTypeList.add((String) depositType);
            }
        }

        // レスポンス設定
        IfaDomesticMutualFundOrderInputA001ResponseDto a001ResDto = setResponse(cc, dtoReq.getMutualFundSellBuyType(),
                dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(), dtoReq.getDepositType(), selectAccountType,
                selectDepositType, depositTypeList, fct023Res, fct024Res, api001Res2, api002Res, api003Res, api004Res,
                api005Res, api006Res, fct039Res, selSql001Res, selSql002Res, tradeCd, tradeCdMutualFund, transfersPreferentialQuotaApplication);
        IfaDomesticMutualFundOrderInputA005ResponseDto resDto = new IfaDomesticMutualFundOrderInputA005ResponseDto();
        BeanUtils.copyProperties(resDto, a001ResDto);

        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        return apiErrorUtil.createDataList(resDtoList, null);
    }

    /**
     * アクションID：A006
     * アクション名：乗換優遇枠適用変更
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA006RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA006ResponseDto
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 口座選択の際、例外が発生した場合
     */
    @Override
    public DataList<IfaDomesticMutualFundOrderInputA006ResponseDto> transferPreferentialFrameChangeA006(
            IfaDomesticMutualFundOrderInputA006RequestDto dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundOrderInputServiceImplL.transferPreferentialFrameChangeA006");
        }

        // 銘柄情報を取得する。
        // 以下を入力値としてFCT023を実行
        InputFct023Dto fct023Req = new InputFct023Dto();
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        fct023Req.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        OutputFct023Dto fct023Res = new OutputFct023Dto();
        fct023Res = fct023.getData(fct023Req);
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // api002実行
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        QueryFundSettlementDateOutData api002Res = api002(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(),
                fct023Res.getFundType());
        if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
            return apiErrorUtil.createDataList(Collections.emptyList(), null);
        }
        // api005実行（A006が実行される状態が「買いの場合」なので判別不要_QA2279）
        QueryIsaFundMaxBuyAmountOutData api005Res = api005(cc, dtoReq.getFundCodeTimes(), dtoReq.getFundCodeIssues(),
                dtoReq.getTransfersPreferentialQuotaApplication(), dtoReq.getSelectAccountType());
        if (apiErrorUtil.isError(api005Res.getShubetu(), api005Res.getCode(), api005Res.getMessage())) {
            return apiErrorUtil.createDataList(Collections.emptyList(), null);
        }
        IfaDomesticMutualFundOrderInputA006ResponseDto resDto = new IfaDomesticMutualFundOrderInputA006ResponseDto();
        // NISA買付可能最大額文言
        // ■API正常応答
        DecimalFormat format = new DecimalFormat("#,###");
        if (StringUtils.equals(api005Res.getCode(), CODE_SUCCESS)) {
            // "NISA成長投資枠買付可能最大額:"+NISA買付可能最大額+"円（"+ISA非課税枠の買付年(受渡日で判定)+"年投資可能枠基準）"
            String isaMaxBuyAmount = formatNumber(api005Res.getIsaMaxBuyAmount().trim(), format);
            resDto.setNisaBuy("NISA成長投資枠買付可能最大額:" + isaMaxBuyAmount + "円（"
                    + api005Res.getIsaKaitukeY() + "年投資可能枠基準）");

            // ■APIのエラーコード=EN0041（NISARO申込中）の場合
        } else if (StringUtils.equals(api005Res.getCode(), EN0041)) {
            // "RO申込中（"+API002.受渡日の年+"年投資可能枠基準）"
            resDto.setNisaBuy("RO申込中（" + api002Res.getSettlementDate().substring(0, 4) + "年投資可能枠基準）");

            // ■APIのエラーコード=EN0045（NISA勘定変更申込中）の場合
        } else if (StringUtils.equals(api005Res.getCode(), EN0045)) {
            // "勘定変更申込中（"+API002.受渡日の年+"年投資可能枠基準）"
            resDto.setNisaBuy("勘定変更申込中（" + api002Res.getSettlementDate().substring(0, 4) + "年投資可能枠基準）");

            // ■APIのエラーコード=上記以外
        } else {
            // "NISA買付可能最大額 --円（--年投資可能枠基準）"
            resDto.setNisaBuy("NISA買付可能最大額 --円（--年投資可能枠基準）");
        }

        // レスポンスをコントローラーに返却する。
        List<IfaDomesticMutualFundOrderInputA006ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderInputA006ResponseDto>();
        resDtoList.add(resDto);
        return apiErrorUtil.createDataList(resDtoList, null);
    }

    /**
     * アクションID：A009
     * アクション名：預り区分変更
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA009RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA009ResponseDto
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 預り区分変更の際、例外が発生した場合
     */
    @Override
    public DataList<IfaDomesticMutualFundOrderInputA009ResponseDto> depositCategoryChangeA009(
            IfaDomesticMutualFundOrderInputA009RequestDto dtoReq) throws Exception {

        DataList<IfaDomesticMutualFundOrderInputA009ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderInputA009ResponseDto>();
        List<IfaDomesticMutualFundOrderInputA009ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderInputA009ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundOrderInputServiceImplL.depositCategoryChangeA009");
        }

        // ⑤銘柄の基準価額、手数料率リスト、乗換優遇率および扱者個別データ有無を取得する（FCT024）
        // 以下を入力値としてFCT024を実行
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        InputFct024Dto fct024Req = new InputFct024Dto();
        // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        fct024Req.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        // ・顧客共通情報.扱者コード
        fct024Req.setDealerNumber(cc.getDealerNumber());
        OutputFct024Dto fct024Res = new OutputFct024Dto();
        fct024Res = fct024.getData(fct024Req);
        // FCT024.協会コードがセットされていない = データなしし
        if (StringUtil.isNullOrEmpty(fct024Res.getKyoukaiCd())) {
            // 銘柄基準価格、手数料取得エラー(errors.fnd.selectedBrand.noPriceAndCommission)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_NOPRICEANDCOMMISSION.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOPRICEANDCOMMISSION.key));
            return dtoRes;
        }

        List<IfaDomesticMutualFundOrderInputCommRate> commRateList = getCommRateList(fct024Res,
                dtoReq.getSelectAccountType(), dtoReq.getFundType(), dtoReq.getSelectDepositType(),
                dtoReq.getMutualFundSellBuyType());

        IfaDomesticMutualFundOrderInputA009ResponseDto resDto = new IfaDomesticMutualFundOrderInputA009ResponseDto();
        // 手数料率リスト(n)
        resDto.setCommRateList(commRateList);

        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        return dtoRes;
    }

    /**
     * アクションID：A010
     * アクション名：注文確認
     * Dto リクエスト：IfaDomesticMutualFundOrderInputA010RequestDto
     * Dto レスポンス：IfaDomesticMutualFundOrderInputA010ResponseDto
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 注文確認の際、例外が発生した場合
     */
    @Override
    public DataList<IfaDomesticMutualFundOrderInputA010ResponseDto> orderConfirmA010(
            IfaDomesticMutualFundOrderInputA010RequestDto dtoReq) throws Exception {

        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        DataList<IfaDomesticMutualFundOrderInputA010ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundOrderInputA010ResponseDto>();
        List<IfaDomesticMutualFundOrderInputA010ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundOrderInputA010ResponseDto>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundOrderInputServiceImplL.orderConfirmA010");
        }

        // ①利用者の口座に対する権限チェックを行う。
        // 以下を入力値としてFCT001を実行
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
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
        fct003Req.setTradeCd(dtoReq.getTradeCd());
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // if(媒介可取引有無 = "0"(なし)){
        if (StringUtils.equals(fct003Res.getMediateAbleTradeFlag(), NONE)) {
            // 取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "4", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, IfaCommonUtil
                            .getMessage(MessageId.ERRORS_SELECTEDACCOUNT_UNAVAILABLE.key, new String[] { codeName }));
            return dtoRes;
        }

        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CCSIDUNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CCSIDUNREGISTERED.key));
        }

        if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_CUMULATIVE.key)) {
            // ③買いの場合、通貨選択型投信　または　複雑型投信チェックを行う。
            // 以下を入力値としてFCT017を実行
            InputFct017Dto fct017Req = new InputFct017Dto();
            // ・顧客共通情報.部店コード
            fct017Req.setButenCode(cc.getButenCode());
            // ・顧客共通情報.口座番号
            fct017Req.setAccountNumber(cc.getAccountNumber());
            // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
            List<InquiryMutualFundBrand> inquiryMutualFundBrandList = new ArrayList<>();
            InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
            inquiryMutualFundBrand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            inquiryMutualFundBrandList.add(inquiryMutualFundBrand);
            fct017Req.setInquiryMutualFundBrandList(inquiryMutualFundBrandList);
            OutputFct017Dto fct017Res = new OutputFct017Dto();
            fct017Res = fct017.getData(fct017Req);
            // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
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

            // ④買いの場合、Indigo銘柄設定チェックを行う。
            // 以下を入力値としてFCT025を実行
            InputFct025Dto fct025Req = new InputFct025Dto();
            // ・顧客共通情報.仲介業者コード
            fct025Req.setBrokerCode(cc.getBrokerCode());
            // ・リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
            List<com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand> fct025BrandList = new ArrayList<com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand>();
            com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand fct025Brand = new com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand();
            fct025Brand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
            fct025BrandList.add(fct025Brand);
            fct025Req.setBrandList(fct025BrandList);
            OutputFct025Dto fct025Res = new OutputFct025Dto();
            fct025Res = fct025.doCheck(fct025Req);
            for (com.sbisec.helios.ap.bizcommon.model.OutputFct025Dto.Brand brand : fct025Res.getBrandList()) {
                // 銘柄リスト.E*TRADE扱い可否=0:不可 の場合
                if (StringUtils.equals(brand.getIsEtradeService(), NONE)) {
                    // 銘柄ステータス取引不可エラー（errors.fnd.selectedBrand.notTradeable）を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_NOTTRADEABLE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_NOTTRADEABLE.key));
                    return dtoRes;
                }
                // 銘柄リスト.仲介業者扱可否=0:不可：仲介業者取引不可エラーを返す。
                if (StringUtils.equals(brand.getIsBrokerService(), NONE)) {
                    // 仲介業者取引不可エラー(errors.fnd.selectedBrand.outOfIfaTrade)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_OUTOFIFATRADE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFIFATRADE.key));
                    return dtoRes;
                }
            }
        }

        OutputFct039Dto fct039Res = null;
        // ⑤リクエスト.ポイント利用=一部使用する　の場合、ポイントチェックを行う。
        if (StringUtils.equals(dtoReq.getPointFlg(), PART_USE)) {
            // fct039実行
            fct039Res = fct039(cc);
            // リクエスト.ポイント　÷　FCT039.利用ポイント単位の剰余がある場合、エラーを返す。
            if (!StringUtils.equals(fct039Res.getUsePointUnit(), ZERO)
                    && !StringUtil.isNullOrEmpty(fct039Res.getUsePointUnit())) {
                int point = Integer.parseInt(dtoReq.getPoint()) % Integer.parseInt(fct039Res.getUsePointUnit());
                if (point != 0) {
                    String codeName1 = codelistservice.getValue(POINT_TYPE, fct039Res.getPointType(), "1");
                    String codeName2 = codelistservice.getValue(POINT_TYPE, fct039Res.getPointType(), "2");
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_UNITCHECT.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_UNITCHECT.key,
                                    new String[] { codeName1, fct039Res.getUsePointUnit(), codeName2 }));
                    return dtoRes;
                }
            }
        }

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
        fct021Req.setTradeCd(dtoReq.getTradeCd());
        // ・国籍コード
        fct021Req.setCountryCd(NATION_CODE);
        // ・通貨コード
        fct021Req.setCurrencyCode(CURRENCY_CODE);
        OutputFct021Dto fct021Res = new OutputFct021Dto();
        fct021Res = fct021.doCheck(fct021Req);
        // 注意情報エラー有無="1"（あり）：エラーを返す。
        if (StringUtils.equals(fct021Res.getNoteInfoErrFlag(), ONE)) {
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "4", "1");
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
        IfaDomesticMutualFundOrderInputA010ResponseDto resDto = new IfaDomesticMutualFundOrderInputA010ResponseDto();
        if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), ONE)) {
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "4", "1");
            resDto.setNoticeInfoAlert(
                    IfaCommonUtil.getMessage(MessageId.WRANINGS_NOTICEWARNINGCHECK.key, new String[] { codeName }));
            // お知らせアラート
        }
        // お知らせアラート有無="1"（あり）：アラート情報を格納する。
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), ONE)) {
            resDto.setNoticeAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_INFORMATIONCHECK.key));
        }

        IfaDomesticMutualFundOrderInputComplianceRankCheck complianceRankCheck = new IfaDomesticMutualFundOrderInputComplianceRankCheck();
        if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_CUMULATIVE.key)) {
            // ⑦買いの場合、コンプラランクチェックを行う（FCT006）
            // 以下を入力値としてFCT006を実行
            InputFct006Dto fct006Req = new InputFct006Dto();
            // ・顧客共通情報.部店コード
            fct006Req.setButenCode(cc.getButenCode());
            // ・顧客共通情報.口座番号
            fct006Req.setAccountNumber(cc.getAccountNumber());
            // ・国内外国区分
            fct006Req.setBrDomesticFgnInd(DOMESTIC);
            // ・商品区分
            fct006Req.setBrBrandInd(MUTUALFUND);
            // ・リクエスト.ファンドコード（回数）
            fct006Req.setBrandCode1(dtoReq.getFundCodeTimes());
            // ・リクエスト.ファンドコード（号）
            fct006Req.setBrandCode2(dtoReq.getFundCodeIssues());
            // ・リクエスト.勧誘区分
            fct006Req.setInvitationType(dtoReq.getKanyuKbn());
            // ・リクエスト.受注方法
            fct006Req.setOrderMethod(dtoReq.getReceiveOrderType());
            // ・"1"（買付注文）[区分.コンプラチェック種類]
            fct006Req.setComplaCheckKind(PURCHASE_ORDER);
            OutputFct006Dto fct006Res = new OutputFct006Dto();
            fct006Res = fct006.doCheck(fct006Req);
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

            // コンプラランクチェック.コンプラチェック用資金性格
            complianceRankCheck.setFundCharacter(fct006Res.getComplaCheckFundCharacter());
        }

        // コンプラランクチェック
        resDto.setComplianceRankCheck(complianceRankCheck);

        // ⑧短期売却、償還前売却のチェックを行う
        // if(リクエスト.短期売却確認=該当する){
        if (StringUtils.equals(dtoReq.getTankiSellKbn(), CONCERNED)) {
            // 短期売却確認アラートメッセージ（warnings.fnd.selectedBrand.needShortTermSaleApproval）を設定
            resDto.setShortTermSellConfirmMsg(IfaCommonUtil.getMessage(MessageId.WARNINGS_SALEAPPROVAL.key));
        }
        // if(リクエスト.償還前売却確認=該当する){
        if (StringUtils.equals(dtoReq.getShokanMaeKbn(), CONCERNED)) {
            resDto.setPreRedemptionSellConfirmAlertMsg(
                    IfaCommonUtil.getMessage(MessageId.WARNIGNS_SALEBEFOREREDEMPTION.key));
        }
        
        //買いの場合、目論見書チェック用情報を取得
        if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_CUMULATIVE.key)) {
            
            // SQL003:協会コード と ファンドタイプ の取得
            var sql003resList = executeSql003(Optional.ofNullable(dtoReq.getFundCodeTimes()).orElse(""), Optional.ofNullable(dtoReq.getFundCodeIssues()).orElse(""), dtoReq.getFundType());
        
            if (sql003resList.size() != 1) {
        	     // 取得エラーを返却する
                return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_NO_INFO.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_NO_INFO.key));
            }
        
            // 投信閲覧履歴一括照会の取得
            Boolean continueFlg = false;
   	 	    GetFundFundDocReadHistoryBulkReq getFundDocReadHistoryBulkReq = new GetFundFundDocReadHistoryBulkReq();
            getFundDocReadHistoryBulkReq.getHeader().setToken(SafeApiUtil.getToken(cc.getButenCode(), cc.getAccountNumber()));  // トークン
            FundDocReadHistoryBulkApiIn fundDocReadHistoryBulkApiIn = new FundDocReadHistoryBulkApiIn();
            fundDocReadHistoryBulkApiIn.setDataOutputKbn("1");  // データ出力区分,1:データ出力区分
        
            List<FundDocReadHistoryBulkItem> fundDocReadHistoryBulkList = new ArrayList<>();
            FundDocReadHistoryBulkItem fundDocReadHistoryBulkItem = new FundDocReadHistoryBulkItem();
            fundDocReadHistoryBulkItem.setFundCode(sql003resList.get(0).getMFCode());  // 協会コード
        
            fundDocReadHistoryBulkItem.setFundType(dtoReq.getFundType());  // ファンドタイプ
            fundDocReadHistoryBulkList.add(fundDocReadHistoryBulkItem);
            fundDocReadHistoryBulkApiIn.setFunds(fundDocReadHistoryBulkList);

            getFundDocReadHistoryBulkReq.setParameter(fundDocReadHistoryBulkApiIn);
        
            GetFundFundDocReadHistoryBulkRes getFundDocReadHistoryBulkres = new GetFundFundDocReadHistoryBulkRes();
        
            try {
            
                 getFundDocReadHistoryBulkres = safeFundTradeService.getBulkFundDocReadHistory(getFundDocReadHistoryBulkReq); 
        	
            } catch (Exception e) {
        	    // 取得エラーを返却する
            	return safeCommonService.checkSafeBussinessException(resDtoList, e);
            }

            if(getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds() != null 
           		 && !CollectionUtils.isEmpty(getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds()) ) {
                for (FundDocReadHistoryBulk fund :  getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds()) {
                 	//  目論見書閲覧区分が0(閲覧済・郵送完了)が存在の場合、次の処理へ
             	    if(StringUtils.equals(fund.getProspectus(),ProspecFundsProspectus.PROSPECTUS.getValue())) {
             		    continueFlg = true;
             		    break;
                     }	
                 } 
            }
        
            // 目論見書チェック区分の設定
            if(!continueFlg) {
                 // 目論見書チェック区分
       	         // 画面.目論見書チェック区分に"△"（半角スペース1バイト）を設定し、次の処理へ
            	dtoReq.setDispatchId(" ");
            }else{
                // 目論見書チェック区分
            	dtoReq.setDispatchId("1");
            }
        }
        
        // ⑨注文内容に応じて、注文確認を行う
        // if(銘柄情報.ファンドタイプ=1:一般 の場合){
        EstimateFundOrder1OutData api008Res = new EstimateFundOrder1OutData();
        EstimateFundOrder2ExtOutData api009Res = new EstimateFundOrder2ExtOutData();
        if (StringUtils.equals(dtoReq.getFundType(), FundType.GENERAL.key)) {
            // 以下を入力値としてAPI008を実行
            EstimateFundOrder1InData api008InData = new EstimateFundOrder1InData();
            // ・顧客共通情報.部店コード
            api008InData.setButenCd(cc.getButenCode());
            // ・顧客共通情報.口座番号
            api008InData.setKozaNo(cc.getAccountNumber());
            // "00000000000"
            api008InData.setAccountId("00000000000");
            // "0000000"
            api008InData.setNumber("0000000");
            // "0"
            api008InData.setOrigin(ZERO);
            // ファンドコード（回数）
            api008InData.setKaisu(dtoReq.getFundCodeTimes());
            // ファンドコード（号）
            api008InData.setGou(dtoReq.getFundCodeIssues());
            // "0000"
            api008InData.setFundClosedTime("0000");
            // 売買区分
            api008InData.setTradeKbn(dtoReq.getTradeKbn());
            // 口数
            api008InData.setQuantity(dtoReq.getUnit());
            // "1"
            api008InData.setUkewHoho(ONE);
            // "△"
            api008InData.setKaiyakuKbn(SPACE);
            // "0"
            api008InData.setComSiteiKbn(ZERO);
            // "00"
            api008InData.setComRate_1(DOUBLE_ZERO);
            // "00"
            api008InData.setComRate_2(DOUBLE_ZERO);
            // ■取引種別=購入（累投）　または　購入（一般）　の場合
            if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_CUMULATIVE.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)) {
                // ■乗換優遇区分=勧誘あり（記録表、確認書受入）の場合
                if (StringUtils.equals(dtoReq.getNorikaeYuguKbn(), ONE)) {
                    // "1"
                    api008InData.setNorikaeYuguKbn(ONE);

                    // ■上記以外
                } else {
                    // "0"
                    api008InData.setNorikaeYuguKbn(ZERO);
                }

                // ■上記以外
            } else {
                // "0"
                api008InData.setNorikaeYuguKbn(ZERO);
            }
            // "0000"
            api008InData.setSwFundCd("0000");
            // "△"
            api008InData.setPaymentKbn(SPACE);
            // "△△△△△△△△△△"
            api008InData.setPaymentMethod("          ");
            // "△"
            api008InData.setBankKbn(SPACE);
            // "△△△△△△△△△△△△△△△△△△△△"
            api008InData.setBankName("                    ");
            // "0"：支店
            api008InData.setCallcenterKbn(ZERO);
            // ユーザ共通情報.CCSログイン用ID
            api008InData.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
            // "△"
            api008InData.setVettingKbn(SPACE);
            // "0000000000"
            api008InData.setCheckPrice("0000000000");
            // "△"
            api008InData.setCheckId(SPACE);
            // "△△△△△△△△"
            api008InData.setExOrderDate("        ");
            // 預り区分
            api008InData.setIsaKbn(dtoReq.getDepositType());
            // "△"
            api008InData.setSwIsaKbn(SPACE);
            //目論見書チェック区分
            api008InData.setDispatchId(dtoReq.getDispatchId());
            EstimateFundOrder1In api008Req = new EstimateFundOrder1In();
            api008Req.setIndata(api008InData);
            api008Res = apiWrapper.estimateFundOrder1(api008Req);
            // APIレスポンスチェック
            apiErrorUtil.checkApiResponse(api008Res.getShubetu(), api008Res.getCode(), api008Res.getMessage());
            if (apiErrorUtil.isFatal()) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_ORDEREXECUTION.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_ORDEREXECUTION.key,
                                new String[] { api008Res.getCode().trim(), api008Res.getMessage().trim() }));
                return dtoRes;
            }

            // 銘柄情報.ファンドタイプ=2:累投　の場合
        } else if (StringUtils.equals(dtoReq.getFundType(), FundType.CUMULATIVE_PITCH.key)) {
            EstimateFundOrder2ExtInData api009InData = new EstimateFundOrder2ExtInData();
            // ・顧客共通情報.部店コード
            api009InData.setButenCd(cc.getButenCode());
            // ・顧客共通情報.口座番号
            api009InData.setKozaNo(cc.getAccountNumber());
            // "00000000000"
            api009InData.setAccountId("00000000000");
            // "0000000"
            api009InData.setNumber("0000000");
            // "0"
            api009InData.setOrigin(ZERO);
            // ファンドコード（回数）
            api009InData.setKaisu(dtoReq.getFundCodeTimes());
            // ファンドコード（号）
            api009InData.setGou(dtoReq.getFundCodeIssues());
            // "0000"
            api009InData.setFundClosedTime("0000");
            // 売買区分
            api009InData.setTradeKbn(dtoReq.getTradeKbn());
            // 購入解約方法
            // ■取引種別=購入（累投）または
            // （取引種別=解約（累投）または買取（累投））かつ
            // 売却指定=金額指定　の場合
            if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_CUMULATIVE.key)
                    || (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.CANCEL_CUMULATIVE.key)
                            || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.PURCHASE_CUMULATIVE.key))
                            && StringUtils.equals(dtoReq.getSellDesignated(), AMOUNT_SELECT)) {
                // "1"
                api009InData.setBuySellMtd(AMOUNT_SELECT);

                // ■取引種別=購入（一般）　または
                // 解約（一般）または
                // 買取（一般）または
                // （取引種別=解約（累投）または　買取（累投））かつ
                // 売却指定=口数指定　の場合
            } else if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.CANCEL_GENERAL.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.PURCHASE_GENERAL.key)
                    || (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.CANCEL_CUMULATIVE.key)
                            || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.PURCHASE_CUMULATIVE.key))
                            && StringUtils.equals(dtoReq.getSellDesignated(), UNIT_SELECT)) {
                // "2"
                api009InData.setBuySellMtd(UNIT_SELECT);

                // ■取引種別=全額買取　または全額解約　の場合
            } else if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.AMOUNT_PURCHASE.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.AMOUNT_CANCEL.key)) {
                // 売却指定
                api009InData.setBuySellMtd(AMOUNT_SELL);
            }
            // 購入解約の金額・口数
            // ■購入解約方法=口数指定　の場合
            if (StringUtils.equals(api009InData.getBuySellMtd(), UNIT_SELECT)) {
                // リクエスト.口数
                api009InData.setPaymentQuantity(String.format("%011d", Long.parseLong(dtoReq.getUnit())));

                // ■購入解約方法=金額指定　の場合
            } else if (StringUtils.equals(api009InData.getBuySellMtd(), AMOUNT_SELECT)) {
                // リクエスト.金額
                api009InData.setPaymentQuantity(String.format("%011d", Long.parseLong(dtoReq.getAmount())));

                // ■購入解約方法=ALL指定　の場合
            } else if (StringUtils.equals(api009InData.getBuySellMtd(), AMOUNT_SELL)) {
                api009InData.setPaymentQuantity("00000000000");
            }
            // "1"
            api009InData.setUkewHoho(ONE);
            // "△"
            api009InData.setKaiyakuKbn(SPACE);
            // "0"
            api009InData.setComSiteiKbn(ZERO);
            // "00"
            api009InData.setComRate_1(DOUBLE_ZERO);
            // "00"
            api009InData.setComRate_2(DOUBLE_ZERO);
            // ■取引種別=購入（累投）　または　購入（一般）　の場合
            if (StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_CUMULATIVE.key)
                    || StringUtils.equals(dtoReq.getTradeCd(), TradeCd.BUY_GENERAL.key)) {
                // ■乗換優遇区分=勧誘あり（記録表、確認書受入）の場合
                if (StringUtils.equals(dtoReq.getNorikaeYuguKbn(), ONE)) {
                    // "1"
                    api009InData.setNorikaeYuguKbn(ONE);

                    // ■上記以外
                } else {
                    // "0"
                    api009InData.setNorikaeYuguKbn(ZERO);
                }

                // ■上記以外
            } else {
                // "0"
                api009InData.setNorikaeYuguKbn(ZERO);
            }
            // "0000"
            api009InData.setSwFundCd("0000");
            // "△"
            api009InData.setPaymentKbn(SPACE);
            // "△△△△△△△△△△"
            api009InData.setPaymentMethod("          ");
            // "△"
            api009InData.setBankKbn(SPACE);
            // "△△△△△△△△△△△△△△△△△△△△"
            api009InData.setBankName("                    ");
            // "0"：支店
            api009InData.setCallcenterKbn(ZERO);
            // ユーザ共通情報.CCSログイン用ID
            api009InData.setUserId(IfaCommonUtil.getUserAccount().getCcsUserId());
            // "△"
            api009InData.setVettingKbn(SPACE);
            // "0000000000"
            api009InData.setCheckPrice("0000000000");
            // "△"
            api009InData.setCheckId(SPACE);
            // "△△△△△△△△"
            api009InData.setExOrderDate("        ");
            // 分配金受取方法
            api009InData.setReinvest(dtoReq.getDistributionReceiveMethodWord());
            // 預り区分
            api009InData.setIsaKbn(dtoReq.getDepositType());
            // "△"
            api009InData.setSwIsaKbn(SPACE);
            //  目論見書チェック区分
            api009InData.setDispatchId(dtoReq.getDispatchId());
            // ポイント利用 ※値がなければ"0"をセット
            if (!StringUtil.isNullOrEmpty(dtoReq.getPointFlg())) {
                api009InData.setPointFlg(dtoReq.getPointFlg());
            } else {
                api009InData.setPointFlg(ZERO);
            }
            // ポイント ※値がなければALL"0"をセット
            if (!StringUtil.isNullOrEmpty(dtoReq.getPoint())) {
                api009InData.setPointOrder(dtoReq.getPoint());
            } else {
                api009InData.setPointOrder("00000000");
            }
            // ポイント種別 ※値がなければ"0"をセット
            if (!StringUtil.isNullOrEmpty(dtoReq.getPointType())) {
                api009InData.setPointType(dtoReq.getPointType());
            } else {
                api009InData.setPointType(ZERO);
            }
            EstimateFundOrder2ExtIn api009Req = new EstimateFundOrder2ExtIn();
            api009Req.setIndata(api009InData);
            api009Res = apiWrapper.estimateFundOrder2Ext(api009Req);
            // APIレスポンスチェック
            apiErrorUtil.checkApiResponse(api009Res.getShubetu(), api009Res.getCode(), api009Res.getMessage());
            if (apiErrorUtil.isFatal()) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_ORDEREXECUTION.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_ORDEREXECUTION.key,
                                new String[] { api009Res.getCode().trim(), api009Res.getMessage().trim() }));
                return dtoRes;
            }
        }

        // レスポンス設定
        if (StringUtils.equals(dtoReq.getFundType(), FundType.GENERAL.key)) {
            // 種別
            resDto.setShubetu(api008Res.getShubetu());
            // エラーコード
            resDto.setCode(api008Res.getCode());
            // エラーメッセージ
            resDto.setErrMessage(api008Res.getMessage());
            // 見積単価
            resDto.setQuoteUnitPrice(api008Res.getEstimatePrice());
            // 約定金額
            resDto.setContractAmount(api008Res.getAmount());
            // 手数料/諸費用
            resDto.setCharge(api008Res.getCommission());
            // 注文後のNISA投資可能枠(YYYY年)
            resDto.setNisaInvestableQuote(api008Res.getIsaBuyLimitAfter());
            // 消費税
            resDto.setConsumptionTax(api008Res.getConsumptionTax());
            // 讓渡益税
            resDto.setYieldTax(api008Res.getCapitalGainTax());
            // 精算金額
            resDto.setSettlementAmount(api008Res.getNetAmount());
            // 約定予定日
            resDto.setContractDate(api008Res.getTradeDate());
            // 受渡予定日
            resDto.setDeliveryDate(api008Res.getSettlementDate());
            // 受注日
            resDto.setOrderDate(api008Res.getAcceptDate());
            // 受注時刻
            resDto.setOrderDayTime(api008Res.getAcceptTime());
            
        } else if (StringUtils.equals(dtoReq.getFundType(), FundType.CUMULATIVE_PITCH.key)) {
            // 種別
            resDto.setShubetu(api009Res.getShubetu());
            // エラーコード
            resDto.setCode(api009Res.getCode());
            // エラーメッセージ
            resDto.setErrMessage(api009Res.getMessage());
            // 見積単価
            resDto.setQuoteUnitPrice(api009Res.getEstimatePrice());
            // 約定金額
            resDto.setContractAmount(api009Res.getAmount());
            // 手数料/諸費用
            resDto.setCharge(api009Res.getCommission());
            // 注文後のNISA投資可能枠(YYYY年)
            resDto.setNisaInvestableQuote(api009Res.getIsaBuyLimitAfter());
            // 消費税
            resDto.setConsumptionTax(api009Res.getConsumptionTax());
            // 讓渡益税
            resDto.setYieldTax(api009Res.getCapitalGainTax());
            // 精算金額
            resDto.setSettlementAmount(api009Res.getNetAmount());
            // 約定予定日
            resDto.setContractDate(api009Res.getTradeDate());
            // 受渡予定日
            resDto.setDeliveryDate(api009Res.getSettlementDate());
            // 受注日
            resDto.setOrderDate(api009Res.getAcceptDate());
            // 受注時刻
            resDto.setOrderDayTime(api009Res.getAcceptTime());
            // 受注数量
            resDto.setOrderQuantity(api009Res.getQuantity());
            // 利用ポイント
            resDto.setPoint(api009Res.getPointEstimate());
        }
        // 取引種別
        resDto.setTradeCd(dtoReq.getTradeCd());
        // リクエスト内容
        resDto.setA010ApiRequest(dtoReq);
        // 目論見書チェック区分
        resDto.setDispatchId(dtoReq.getDispatchId());

        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);

        if (fct039Res != null && !StringUtil.isNullOrEmpty(fct039Res.getScreenMessage())) {
            // FCT039 ワーニングが存在する場合(ポップアップで表示する。)
            dtoRes = IfaCommonUtil.createDataList(
                    resDtoList, 
                    ErrorLevel.WARNING,
                    MessageId.WARNIGNS_REFERPOINT_SYSTEMERROR.key,
                    fct039Res.getScreenMessage());
        } else {
            // FCT039 正常終了の場合
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }

        return dtoRes;
    }

    /**
     * 取引種別算出
     *
     * @param mutualFundSellBuyType 売買区分（投信）
     * @param fundType ファンドタイプ
     * @return 取引種別
     */
    private String getTradeCd(String mutualFundSellBuyType, String fundType) {

        String tradeCd = "";

        // ■売買区分（投信）=1:買（購入）の場合
        if (StringUtils.equals(mutualFundSellBuyType, BUY)) {
            // ■ファンドタイプ=累投　または　積立　または　累投／積立　の場合
            if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.key)
                    || StringUtils.equals(fundType, FundType.ACCUMULATE.key)
                    || StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                // 1:購入（累投）
                tradeCd = TradeCd.BUY_CUMULATIVE.key;

                // ■ファンドタイプ=一般　の場合
            } else if (StringUtils.equals(fundType, FundType.GENERAL.key)) {
                // 0:購入（一般）
                tradeCd = TradeCd.BUY_GENERAL.key;
            }

            // ■売買区分（投信）=上記以外の場合
        } else {
            // ■ファンドタイプ=累投　または　積立　または　累投／積立　の場合
            if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.key)
                    || StringUtils.equals(fundType, FundType.ACCUMULATE.key)
                    || StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                // 8:解約（累投）
                tradeCd = TradeCd.CANCEL_CUMULATIVE.key;

                // ■ファンドタイプ=一般　の場合
            } else if (StringUtils.equals(fundType, FundType.GENERAL.key)) {
                // 7:解約（一般）
                tradeCd = TradeCd.CANCEL_GENERAL.key;
            }
        }
        return tradeCd;
    }

    /**
     * 選択口座、選択預り区分、預り区分リスト取得
     *
     * @param cc 顧客情報
     * @param depositType 預り区分
     * @param mutualFundSellBuyType 売買区分（投信）
     * @param fundType ファンドタイプ
     * @return 選択口座、選択預り区分、預り区分リスト
     */
    private Map<String, Object> getParameter(CustomerCommon cc, String depositType, String mutualFundSellBuyType,
            String fundType) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("[start] getParameter_InParameter");
            LOGGER.info("CustomerCommon_JrIsaContractType : {}", cc.getJrIsaContractType());
            LOGGER.info("CustomerCommon_NisaType : {}", cc.getNisaType());
            LOGGER.info("CustomerCommon_NisaTypeYearNext : {}", cc.getNisaTypeYearNext());
            LOGGER.info("CustomerCommon_SpecificAccountType : {}", cc.getSpecificAccountType());
            LOGGER.info("CustomerCommon_JrTokuteiKouzaKbn : {}", cc.getJrTokuteiKouzaKbn());
            LOGGER.info("depositType : {}", depositType);
            LOGGER.info("mutualFundSellBuyType : {}", mutualFundSellBuyType);
            LOGGER.info("fundType : {}", fundType);
            LOGGER.info("[end] getParameter_InParameter");
        }

        // 総合口座
        List<String> wholeAccountList = List.of(" ", "0", "1", "4", "8", "H", "I", "-");
        // ジュニアNISA口座
        List<String> jrNisaAccountList = List.of("5", "6", "7", "J");

        // ※選択口座
        String selectAccountType = "";
        // ■リクエスト.預り区分=未指定
        if (depositType == null || depositType.isEmpty()) {
            // ■顧客共通情報.ジュニアISA契約区分=1：契約の場合
            if (StringUtils.equals(cc.getJrIsaContractType(), CONTRACT)) {
                // ジュニアNISA口座
                selectAccountType = JR_NISA_ACCOUNT;

                // ■上記以外
            } else {
                // 総合口座
                selectAccountType = WHOLE_AMOUNT;
            }

            // ■預り区分=リクエストで指定あり
        } else {
            // ■預り区分がジュニアNISA口座
            if (jrNisaAccountList.contains(depositType)) {
                // ジュニアNISA口座
                selectAccountType = JR_NISA_ACCOUNT;

                // ■預り区分が総合口座
            } else if (wholeAccountList.contains(depositType)) {
                // 総合口座
                selectAccountType = WHOLE_AMOUNT;
            }
        }

        // ※選択預り区分, 預り区分リスト
        String selectDepositType = "";
        List<String> depositTypeList = new ArrayList<>();
        // ■選択口座=総合口座
        if (StringUtils.equals(selectAccountType, WHOLE_AMOUNT)) {
            // ■売買区分（投信）=買（購入）
            if (StringUtils.equals(mutualFundSellBuyType, BUY)) {
                // ■口数の場合
                if (StringUtils.equals(fundType, FundType.GENERAL.key)) {
                    // 選択預り区分=△:特定/一般
                    selectDepositType = MutualFundDeposit.SPECIFIC_GENERAL.key;
                // ■金額の場合
                } else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.key)
                        || StringUtils.equals(fundType, FundType.ACCUMULATE.key)
                        || StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                    // ■預り区分=指定なし または「H:総合NISA（成長投資枠）」以外
                    if ((depositType == null || depositType.isEmpty())
                            || !StringUtils.equals(depositType, MutualFundDeposit.WHOLE_NISA.key)) {
                        // ■NISA可能=不可（NISA）
                        if (!StringUtils.equals(cc.getNisaType(), CAN_NISA)
                                && !StringUtils.equals(cc.getNisaTypeYearNext(), CAN_NISA)) {
                            // ■特定の場合
                            if (StringUtils.equals(cc.getSpecificAccountType(), SpecificAccount.SPECIFIC_AGENCY.key)
                                    || StringUtils.equals(cc.getSpecificAccountType(),
                                            SpecificAccount.SPECIFIC_DEFINITION.key)) {
                                // 選択預り区分=△:特定/一般
                                selectDepositType = MutualFundDeposit.SPECIFIC_GENERAL.key;

                                // ■一般の場合
                            } else if (StringUtils.equals(cc.getSpecificAccountType(), SpecificAccount.NO_SPECIFIC.key)
                                    || StringUtils.equals(cc.getSpecificAccountType(), SpecificAccount.NON.key)) {
                                // 選択預り区分=1:一般
                                selectDepositType = MutualFundDeposit.SPECIFIC_GENERAL.key;
                            }

                            // ■NISA可能=可（NISA）
                        } else {
                            // ■特定の場合
                            if (StringUtils.equals(cc.getSpecificAccountType(), SpecificAccount.SPECIFIC_AGENCY.key)
                                    || StringUtils.equals(cc.getSpecificAccountType(),
                                            SpecificAccount.SPECIFIC_DEFINITION.key)) {
                                // 選択預り区分=△:特定/一般
                                selectDepositType = MutualFundDeposit.SPECIFIC_GENERAL.key;
                                // 預り区分リスト=△:特定/一般, H:総合NISA（成長投資枠）
                                depositTypeList.add(MutualFundDeposit.SPECIFIC_GENERAL.key);
                                depositTypeList.add(MutualFundDeposit.WHOLE_NISA.key);

                                // ■一般の場合
                            } else if (StringUtils.equals(cc.getSpecificAccountType(), SpecificAccount.NO_SPECIFIC.key)
                                    || StringUtils.equals(cc.getSpecificAccountType(), SpecificAccount.NON.key)) {
                                // 選択預り区分=1:一般
                                selectDepositType = MutualFundDeposit.SPECIFIC_GENERAL.key;
                                // 預り区分リスト=1:一般, H:総合NISA（成長投資枠）
                                depositTypeList.add(MutualFundDeposit.SPECIFIC_GENERAL.key);
                                depositTypeList.add(MutualFundDeposit.WHOLE_NISA.key);
                            }
                        }
                    // ■預り区分=H:総合NISA（成長投資枠）
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.WHOLE_NISA.key)) {
                        // ■特定の場合
                        if (StringUtils.equals(cc.getSpecificAccountType(), SpecificAccount.SPECIFIC_AGENCY.key)
                                || StringUtils.equals(cc.getSpecificAccountType(),
                                        SpecificAccount.SPECIFIC_DEFINITION.key)) {
                            // 選択預り区分=△:特定/一般
                            selectDepositType = MutualFundDeposit.WHOLE_NISA.key;
                            // 預り区分リスト=△:特定/一般, H:総合NISA（成長投資枠）
                            depositTypeList.add(MutualFundDeposit.SPECIFIC_GENERAL.key);
                            depositTypeList.add(MutualFundDeposit.WHOLE_NISA.key);

                        // ■一般の場合
                        } else if (StringUtils.equals(cc.getSpecificAccountType(), SpecificAccount.NO_SPECIFIC.key)
                                || StringUtils.equals(cc.getSpecificAccountType(), SpecificAccount.NON.key)) {
                            // 選択預り区分=1:一般
                            selectDepositType = MutualFundDeposit.WHOLE_NISA.key;
                            // 預り区分リスト=1:一般, H:総合NISA（成長投資枠）
                            depositTypeList.add(MutualFundDeposit.SPECIFIC_GENERAL.key);
                            depositTypeList.add(MutualFundDeposit.WHOLE_NISA.key);
                        }
                    }
                }

                // ■売買区分（投信）=売（解約）
            } else if (StringUtils.equals(mutualFundSellBuyType, SELL)) {
                // ■口数の場合
                if (StringUtils.equals(fundType, FundType.GENERAL.key)) {
                    // ■預り区分=0:特定
                    if (StringUtils.equals(depositType, MutualFundDeposit.SPECIFIC.key)) {
                        // 選択預り区分=0:特定
                        selectDepositType = MutualFundDeposit.SPECIFIC.key;

                        // ■預り区分=1:一般
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.GENERAL.key)
                            || StringUtils.equals(depositType, MutualFundDeposit.NON_SPECIFIC_ACCOUNT.key)) {
                        // 選択預り区分=1:一般
                        selectDepositType = MutualFundDeposit.GENERAL.key;
                    }

                    // ■金額の場合
                } else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.key)
                        || StringUtils.equals(fundType, FundType.ACCUMULATE.key)
                        || StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                    // ■預り区分=0:特定
                    if (StringUtils.equals(depositType, MutualFundDeposit.SPECIFIC.key)) {
                        // 選択預り区分=0:特定
                        selectDepositType = MutualFundDeposit.SPECIFIC.key;

                        // ■預り区分=1:一般
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.GENERAL.key)
                            || StringUtils.equals(depositType, MutualFundDeposit.NON_SPECIFIC_ACCOUNT.key)) {
                        // 選択預り区分=1:一般
                        selectDepositType = MutualFundDeposit.GENERAL.key;

                        // ■預り区分=4:旧NISA
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.OLD_NISA.key)) {
                        // 選択預り区分=4:旧NISA
                        selectDepositType = MutualFundDeposit.OLD_NISA.key;

                        // ■預り区分=8:旧つみたてNISA
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.OLD_NISA_RESERVE.key)) {
                        // 選択預り区分=8:旧つみたてNISA
                        selectDepositType = MutualFundDeposit.OLD_NISA_RESERVE.key;

                        // ■預り区分=H:総合NISA（成長投資枠）
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.WHOLE_NISA.key)) {
                        // 選択預り区分=H:総合NISA（成長投資枠）
                        selectDepositType = MutualFundDeposit.WHOLE_NISA.key;

                        // ■預り区分=I:総合NISA（つみたて投資枠）
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.WHOLE_NISA_RESERVE.key)) {
                        // 選択預り区分=I:総合NISA（つみたて投資枠）
                        selectDepositType = MutualFundDeposit.WHOLE_NISA_RESERVE.key;
                    }
                }
            }

            // ■選択口座=ジュニアNISA口座（契約のみ）
        } else if (StringUtils.equals(selectAccountType, JR_NISA_ACCOUNT)
                || StringUtils.equals(cc.getJrIsaContractType(), CONTRACT)) {
            // ■売買区分（投信）=買（購入）
            if (StringUtils.equals(mutualFundSellBuyType, BUY)) {
                // ■口数の場合
                if (StringUtils.equals(fundType, FundType.GENERAL.key)) {
                    // 選択預り区分=5:Jr特定/Jr一般
                    selectDepositType = MutualFundDeposit.JR_SPECIFIC_JR_GENERAL.key;

                // ■金額の場合
                } else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.key)
                        || StringUtils.equals(fundType, FundType.ACCUMULATE.key)
                        || StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                    // ■特定の場合
                    if (StringUtils.equals(cc.getJrTokuteiKouzaKbn(), SpecificAccount.SPECIFIC_AGENCY.key)
                            || StringUtils.equals(cc.getJrTokuteiKouzaKbn(),
                                    SpecificAccount.SPECIFIC_DEFINITION.key)) {
                        // 選択預り区分=5:Jr特定/Jr一般
                        selectDepositType = MutualFundDeposit.JR_SPECIFIC_JR_GENERAL.key;

                        // ■一般の場合
                    } else if (StringUtils.equals(cc.getJrTokuteiKouzaKbn(), SpecificAccount.NO_SPECIFIC.key)
                            || StringUtils.equals(cc.getJrTokuteiKouzaKbn(), SpecificAccount.NON.key)) {
                        // 選択預り区分=1:一般
                        selectDepositType = MutualFundDeposit.JR_SPECIFIC_JR_GENERAL.key;
                    }
                }

                // ■売買区分（投信）=売（解約）
            } else if (StringUtils.equals(mutualFundSellBuyType, SELL)) {
                // ■口数の場合
                if (StringUtils.equals(fundType, FundType.GENERAL.key)) {
                    // ■預り区分=5:Jr特定
                    if (StringUtils.equals(depositType, MutualFundDeposit.JR_SPECIFIC_JR_GENERAL.key)) {
                        // 選択預り区分=5:Jr特定
                        selectDepositType = MutualFundDeposit.JR_SPECIFIC_JR_GENERAL.key;

                        // ■預り区分=6:Jr一般
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.JR_GENERAL.key)) {
                        // 選択預り区分=6:Jr一般
                        selectDepositType = MutualFundDeposit.JR_GENERAL.key;
                    }

                    // ■金額の場合
                } else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.key)
                        || StringUtils.equals(fundType, FundType.ACCUMULATE.key)
                        || StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                    // ■預り区分=5:Jr特定
                    if (StringUtils.equals(depositType, MutualFundDeposit.JR_SPECIFIC_JR_GENERAL.key)) {
                        // 選択預り区分=5:Jr特定
                        selectDepositType = MutualFundDeposit.JR_SPECIFIC_JR_GENERAL.key;

                        // ■預り区分=6:Jr一般
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.JR_GENERAL.key)) {
                        // 選択預り区分=6:Jr一般
                        selectDepositType = MutualFundDeposit.JR_GENERAL.key;

                        // ■預り区分=7:JrNISA
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.JR_NISA.key)) {
                        // 選択預り区分=7:JrNISA
                        selectDepositType = MutualFundDeposit.JR_NISA.key;

                        // ■預り区分=J:JrNISA（継続管理勘定）
                    } else if (StringUtils.equals(depositType, MutualFundDeposit.JR_NISA_CONTINUOUS.key)) {
                        // 選択預り区分=J:JrNISA（継続管理勘定）
                        selectDepositType = MutualFundDeposit.JR_NISA_CONTINUOUS.key;
                    }
                }
            }
        }

        Map<String, Object> parameter = new HashMap<>();
        // 選択口座
        parameter.put("selectAccountType", selectAccountType);
        // 選択預り区分
        parameter.put("selectDepositType", selectDepositType);
        // 預り区分リスト
        parameter.put("depositTypeList", depositTypeList);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("[start] getParameter_OutParameter");
            LOGGER.info("selectAccountType : {}",
                    !StringUtils.equals(parameter.get("selectAccountType").toString(), " ")
                            ? parameter.get("selectAccountType")
                            : "△");
            LOGGER.info("selectDepositType : {}",
                    !StringUtils.equals(parameter.get("selectDepositType").toString(), " ")
                            ? parameter.get("selectDepositType")
                            : "△");
            LOGGER.info("depositTypeList : {}", parameter.get("depositTypeList"));
            LOGGER.info("[end] getParameter_OutParameter");
        }

        return parameter;
    }

    /**
     * api001（NRI_QueryFund）_買付可能一覧
     */
    private QueryFundOutData api001(CustomerCommon cc, ApiErrorUtil apiErrorUtil, String fundCodeTimes, String fundCodeIssues) throws Exception {

        QueryFundInData api001InData = new QueryFundInData();
        // ・顧客共通情報.部店コード
        api001InData.setButenCd(cc.getButenCode());
        // ・顧客共通情報.口座番号
        api001InData.setKozaNo(cc.getAccountNumber());
        // ・スペース埋め（5桁）
        api001InData.setAssetManagementId(SPACE);
        // ・取引口座区
        // ■顧客共通情報.ジュニアISA契約区分 ≠ "1":契約 の場合
        if (!StringUtils.equals(cc.getJrIsaContractType(), CONTRACT)) {
            // "△":通常口座およびJrNISA口座の第一口座
            api001InData.setAccountGetKbn(GENERAL_JRNISA_SPACE);

            // ■顧客共通情報.ジュニアISA契約区分 = "1":契約 の場合
        } else if (StringUtils.equals(cc.getJrIsaContractType(), CONTRACT)) {
            // "2"：JrNISA口座(第一、第二口座両方)
            api001InData.setAccountGetKbn(FIRST_SECOND_ACCOUNT_JRNISA);
        }
        QueryFundIn api001Req = new QueryFundIn();
        api001Req.setIndata(api001InData);
        QueryFundOutData api001ResRepeat = new QueryFundOutData();
        QueryFundOutData api001Res = new QueryFundOutData();

        int refFrom = 1;
        int refTo = MAX_QUERY_FUND;
        while (true) {
            api001InData.setRefFrom(DF.format(refFrom));
            api001InData.setRefTo(DF.format(refTo));

            api001ResRepeat = apiWrapper.queryFund(api001Req);
            if (apiErrorUtil.isError(api001ResRepeat.getShubetu(), api001ResRepeat.getCode(), api001ResRepeat.getMessage())) {
                return null;
            }
            // 初回のみ検索結果以外をセット
            if (refFrom == 1) {
                BeanUtils.copyProperties(api001Res, api001ResRepeat);
                api001Res.setQueryFund(new ArrayList<>());
            }

            // 検索結果から、ファンドコード(回数)とファンドコード(号)が一致するものを検索し
            // 1件見つけた時点で処理を終了する。
            Optional<QueryFund> searchResult = api001ResRepeat.getQueryFund().stream()
                    .filter(val -> StringUtils.equals(val.getFundCodeSerNo().trim(), fundCodeTimes.trim()))
                    .filter(val -> StringUtils.equals(val.getFundCodeSub().trim(), fundCodeIssues.trim()))
                    .findFirst();

            if (searchResult.isPresent()) {
                api001Res.setQueryFund(List.of(searchResult.get()));
                break;
            }

            if (refTo >= Integer.parseInt(api001ResRepeat.getHitNumber())) {
                break;
            }
            refFrom += MAX_QUERY_FUND;
            refTo += MAX_QUERY_FUND;
        }

        return api001Res;
    }

    /**
     * api002（NRI_QueryFundSettlementDate）_投信銘柄別受渡日算出
     */
    private QueryFundSettlementDateOutData api002(CustomerCommon cc, String fundCodeTimes, String fundCodeIssues,
            String fundType) throws Exception {

        QueryFundSettlementDateInData api002InData = new QueryFundSettlementDateInData();
        // 顧客共通情報.部店コード
        api002InData.setButenCd(cc.getButenCode());
        // 顧客共通情報.口座番号
        api002InData.setKozaNo(cc.getAccountNumber());
        // ファンドコード（回数）
        api002InData.setKaisu(fundCodeTimes);
        // ファンドコード（号）
        api002InData.setGou(fundCodeIssues);
        // FCT023.ファンドタイプ
        api002InData.setTypeKbn(fundType);
        QueryFundSettlementDateIn api002Req = new QueryFundSettlementDateIn();
        api002Req.setIndata(api002InData);
        QueryFundSettlementDateOutData api002Res = new QueryFundSettlementDateOutData();
        api002Res = apiWrapper.queryFundSettlementDate(api002Req);

        return api002Res;
    }

    /**
     * api003（NRI_QueryAccountBalance）_買付余力照会
     */
    private QueryAccountBalanceOutData api003(CustomerCommon cc) throws Exception {

        QueryAccountBalanceInData api003InData = new QueryAccountBalanceInData();
        // 顧客共通情報.部店コード
        api003InData.setButenCd(cc.getButenCode());
        // 顧客共通情報.口座番号
        api003InData.setKozaNo(cc.getAccountNumber());
        QueryAccountBalanceIn api003Req = new QueryAccountBalanceIn();
        api003Req.setIndata(api003InData);
        QueryAccountBalanceOutData api003Res = new QueryAccountBalanceOutData();
        api003Res = apiWrapper.queryAccountBalance(api003Req);

        return api003Res;
    }

    /**
     * api004（NRI_QueryAccountPositionSumWeb)_預り残高一覧リクエスト（サマリー）(次期Web）
     */
    private QueryAccountPositionSumWebOutData api004(CustomerCommon cc, String selectAccountType, ApiErrorUtil apiErrorUtil) throws Exception {

        QueryAccountPositionSumWebInData api004Req = new QueryAccountPositionSumWebInData();
        // 顧客共通情報.部店コード
        api004Req.setButenCd(cc.getButenCode());
        // 顧客共通情報.口座番号
        api004Req.setKozaNo(cc.getAccountNumber());
        //  "T0"(国内投信（一般口投信・汎用累投）)
        api004Req.setSecType(MUTUALFUND_GENERAL);
        // "△":全ての預り
        api004Req.setRequestType(SPACE);
        // 検索番号指定ＦＲＯＭ, 検索番号指定ＴＯ はAPI内でセット
        // ■選択口座=総合口座の場合
        if (StringUtils.equals(selectAccountType, WHOLE_AMOUNT)) {
            // "△"：通常口座およびJrNISA口座の第一口座
            api004Req.setAccountGetKbn(GENERAL_JRNISA_SPACE);

            // ■選択口座=ジュニアNISA口座の場合
        } else if (StringUtils.equals(selectAccountType, JR_NISA_ACCOUNT)) {
            // "1"：JrNISA口座(第二口座のみ)
            api004Req.setAccountGetKbn(SECOND_ACCOUNT_JRNISA);
        }
        List<QueryAccountPositionSumWebOutData> api004Res = new ArrayList<QueryAccountPositionSumWebOutData>();
        api004Res = apiWrapper.queryAccountPositionSumWeb(api004Req);

        QueryAccountPositionSumWebOutData api004data = new QueryAccountPositionSumWebOutData();
        if (api004Res.size() != 0) {
            // Beanコピー
            BeanUtils.copyProperties(api004data, api004Res.get(0));
            if (apiErrorUtil.isError(api004data.getShubetu(), api004data.getCode(), api004data.getMessage())) {
                return null;
            }
            if (MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA < Integer.parseInt(api004Res.get(0).getHitNumber())) {
                int count = 1;
                while (true) {
                    if (count == api004Res.size()) {
                        break;
                    }
                    for (AccountSumWebData accountSumWeb : api004Res.get(count).getAccountSumWebData()) {
                        api004data.getAccountSumWebData().add(accountSumWeb);
                    }
                    count++;
                }
            }
        }

        return api004data;
    }

    /**
     * api005（NRI_QueryIsaFundMaxBuyAmount)_NISA買付可能最大額リクエスト(汎用累投)
     */
    private QueryIsaFundMaxBuyAmountOutData api005(CustomerCommon cc, String fundCodeTimes, String fundCodeIssues,
            String transfersPreferentialQuotaApplication, String selectAccountType) throws Exception {

        QueryIsaFundMaxBuyAmountInData api005InData = new QueryIsaFundMaxBuyAmountInData();
        // 顧客共通情報.部店コード
        api005InData.setButenCd(cc.getButenCode());
        // 顧客共通情報.口座番号
        api005InData.setKozaNo(cc.getAccountNumber());
        // ファンドコード（回数）
        api005InData.setKaisu(fundCodeTimes);
        // ファンドコード（号）
        api005InData.setGou(fundCodeIssues);
        // "0":ﾌｧﾝﾄﾞ属性に登録された手数料率を使用
        api005InData.setComSiteiKbn(ZERO);
        // "00"
        api005InData.setComRate_1(DOUBLE_ZERO);
        // "00"
        api005InData.setComRate_2(DOUBLE_ZERO);
        // ■乗換優遇枠を適用する（パラメータなしの場合はこちらをセット）
        if (StringUtil.isNullOrEmpty(transfersPreferentialQuotaApplication)) {
            // "1":乗換優遇による購入
            api005InData.setNorikaeYuguKbn(ONE);
        } else {
            api005InData.setNorikaeYuguKbn(transfersPreferentialQuotaApplication);
        }
        // "0000"
        api005InData.setSwFundCd(QUADRUPLE_ZERO);
        // ■選択口座=総合口座の場合
        if (StringUtils.equals(selectAccountType, WHOLE_AMOUNT)) {
            // "△"：通常口座およびJrNISA口座の第一口座
            api005InData.setAccountGetKbn(GENERAL_JRNISA_SPACE);

            // ■選択口座=ジュニアNISA口座の場合
        } else if (StringUtils.equals(selectAccountType, JR_NISA_ACCOUNT)) {
            // "1"：JrNISA口座(第二口座のみ)
            api005InData.setAccountGetKbn(SECOND_ACCOUNT_JRNISA);
        }
        QueryIsaFundMaxBuyAmountIn api005Req = new QueryIsaFundMaxBuyAmountIn();
        api005Req.setIndata(api005InData);
        QueryIsaFundMaxBuyAmountOutData api005Res = new QueryIsaFundMaxBuyAmountOutData();
        api005Res = apiWrapper.queryIsaFundMaxBuyAmount(api005Req);

        return api005Res;
    }

    /**
     * api006（NRI_ReinvestEntry)_再投資停止情報参照・登録
     */
    private ReinvestEntryOutData api006(CustomerCommon cc, String fundCodeTimes, String fundCodeIssues)
            throws Exception {

        ReinvestEntryInData api006Req = new ReinvestEntryInData();
        // 顧客共通情報.部店コード
        api006Req.setButenCd(cc.getButenCode());
        // 顧客共通情報.口座番号
        api006Req.setKozaNo(cc.getAccountNumber());
        // "S":参照
        api006Req.setProcessId(REFERENCE);
        // "1":コールセンター
        api006Req.setCallcenterKbn(CALLCENTER);
        // "001"
        api006Req.setTotalNumber(TOTAL_NUMBER);
        SimReinvestEntryDataI api006InData = new SimReinvestEntryDataI();
        // ファンドコード（回数）
        api006InData.setKaisu(fundCodeTimes);
        // ファンドコード（号）
        api006InData.setGou(fundCodeIssues);
        // "△"
        api006InData.setReinvest(SPACE);
        List<SimReinvestEntryDataI> api006InDataList = new ArrayList<SimReinvestEntryDataI>();
        api006InDataList.add(api006InData);
        api006Req.setSimReinvestEntryDataI(api006InDataList);
        ReinvestEntryOutData api006Res = new ReinvestEntryOutData();
        api006Res = apiWrapper.reinvestEntry(api006Req);

        return api006Res;
    }

    /**
     * FCT039_ポイント照会
     */
    private OutputFct039Dto fct039(CustomerCommon cc) throws Exception {

        InputFct039Dto fct039Req = new InputFct039Dto();
        // 顧客共通情報.部店コード
        fct039Req.setButenCode(cc.getButenCode());
        // 顧客共通情報.口座番号
        fct039Req.setAccountNumber(cc.getAccountNumber());
        // 顧客共通情報.仲介業者コード
        fct039Req.setBrokerCode(cc.getBrokerCode());
        // 1:リアルデータ
        fct039Req.setLinkKbn(REAL_DATA);
        OutputFct039Dto fct039Res = new OutputFct039Dto();
        fct039Res = fct039.getData(fct039Req);

        return fct039Res;
    }

    /**
     * レスポンス設定
     *
     * @param cc 顧客情報
     * @param mutualFundSellBuyType 売買区分（投信）
     * @param fundCodeTimes ファンドコード（回数）
     * @param fundCodeIssues ファンドコード（号）
     * @param depositType 預り区分
     * @param selectAccountType 選択口座
     * @param selectDepositType 選択預り区分
     * @param depositTypeList 預り区分リスト
     * @param fct023Res fct023レスポンス
     * @param fct024Res fct024レスポンス
     * @param api001Res api001レスポンス
     * @param api002Res api002レスポンス
     * @param api003Res api003レスポンス
     * @param api004Res api004レスポンス
     * @param api005Res api005レスポンス
     * @param api006Res api006レスポンス
     * @param fct039Res fct039レスポンス
     * @param sql001Res sql001レスポンス
     * @param sql002Res sql002レスポンス
     * @param tradeCd 取引種別
     * @throws Exception システムエラー
     */
    private IfaDomesticMutualFundOrderInputA001ResponseDto setResponse(CustomerCommon cc, String mutualFundSellBuyType,
            String fundCodeTimes, String fundCodeIssues, String depositType, String selectAccountType,
            String selectDepositType, List<String> depositTypeList, OutputFct023Dto fct023Res,
            OutputFct024Dto fct024Res, QueryFundOutData api001Res, QueryFundSettlementDateOutData api002Res,
            QueryAccountBalanceOutData api003Res, QueryAccountPositionSumWebOutData api004Res,
            QueryIsaFundMaxBuyAmountOutData api005Res, ReinvestEntryOutData api006Res, OutputFct039Dto fct039Res,
            IfaDomesticMutualFundOrderInputSql001ResponseModel sql001Res,
            IfaDomesticMutualFundOrderInputSql002ResponseModel sql002Res, String tradeCd, String tradeCdMutualFund, String transfersPreferentialQuotaApplication) throws Exception {

        IfaDomesticMutualFundOrderInputA001ResponseDto resDto = new IfaDomesticMutualFundOrderInputA001ResponseDto();

        // 売買区分（投信）
        resDto.setMutualFundSellBuyType(mutualFundSellBuyType);
        // ファンドコード（回数）
        resDto.setFundCodeTimes(fundCodeTimes);
        // ファンドコード（号）
        resDto.setFundCodeIssues(fundCodeIssues);
        // 預り区分
        // '-'："非特定口座預り又は未登録口座預り又はカバードワラント商品の場合"　→　'1'："一般"（区分.預り区分（投信））
        if (StringUtils.equals(depositType, MutualFundDeposit.NON_SPECIFIC_ACCOUNT.key)) {
            resDto.setDepositType(MutualFundDeposit.GENERAL.key);
        } else {
            resDto.setDepositType(depositType);
        }
        // 銘柄コード
        resDto.setBrandCode(fundCodeTimes + PERIOD + fundCodeIssues);
        // 選択口座
        resDto.setSelectAccountType(selectAccountType);
        // 選択預り区分
        resDto.setSelectDepositType(selectDepositType);
        // 預り区分リスト
        resDto.setDepositTypeList(depositTypeList);
        // 銘柄情報
        IfaDomesticMutualFundOrderInputBrand brand = new IfaDomesticMutualFundOrderInputBrand();
        // 銘柄情報.銘柄名
        brand.setBrandName(fct023Res.getFundOfficalName());
        // 銘柄情報.基準価額単位
        brand.setPriceUnit(fct023Res.getBasePriceUnit());
        // 銘柄情報.販売最低口数
        brand.setMinSalesLot(fct023Res.getNumberOfSalesMinimum());
        // 銘柄情報.販売最小単位金額(2回目以降)
        brand.setMinSalesUnitAmount(fct023Res.getSalesMinimunUnitPriceAfter2nd());
        // 銘柄情報.販売単位口数
        brand.setSalesUnitLot(fct023Res.getNumberOfSalesUnit());
        // 銘柄情報.販売売買単位金額(2回目以降)
        brand.setSalesTradeUnitAmount(fct023Res.getSalesTradingUnitPriceAfter2nd());
        // 銘柄情報.解約売買単位口数
        brand.setCancelTradeUnitLot(fct023Res.getNumberOfCancelTradingUnit());
        // 銘柄情報.解約売買単位金額(2回目以降)
        brand.setCancelTradeUnitAmount(fct023Res.getCancelTradingUnitPriceAfter2nd());
        // 銘柄情報.注文締切時間
        brand.setDeadlines(fct023Res.getOrderDeadlineTime());
        // 銘柄情報.売却方法
        brand.setBuyMethodSelect(fct023Res.getSaleMethod());
        // 銘柄情報.ファンドタイプ
        brand.setFundType(fct023Res.getFundType());
        // 銘柄情報.再投資区分
        brand.setReInvestmentClassification(fct023Res.getReinvestmentDivison());
        // 銘柄情報.特殊区分
        brand.setBrandSpecialClassification(fct023Res.getSpecialType());
        // 銘柄情報.基準価格
        brand.setBasePrice(fct024Res.getBasePrice());
        // 銘柄情報.基準価額日付
        brand.setPriceDate(fct024Res.getBasePriceDate());
        resDto.setBrand(brand);
        // 手数料率リスト(n)
        List<IfaDomesticMutualFundOrderInputCommRate> commRateList = getCommRateList(fct024Res, selectAccountType,
                fct023Res.getFundType(), selectDepositType, mutualFundSellBuyType);
        resDto.setCommRateList(commRateList);
        // 乗換優遇率
        // ■選択口座=ジュニアNISA口座
        if (StringUtils.equals(selectAccountType, JR_NISA_ACCOUNT)) {
            // ■乗換優遇率・分子(特例特定/一般)≦0
            if (fct024Res.getTransfersPreferentialRateNumeratorToku().compareTo(BigDecimal.ZERO) <= 0) {
                // "適用外"
                resDto.setSwitchingFavorableTreatmentRate(NOT_APPLICAVLE);

                // ■乗換優遇率・分母(特例特定/一般)≦0
            } else if (fct024Res.getTransfersPreferentialRateDenominatorToku().compareTo(BigDecimal.ZERO) <= 0) {
                // "999.99%"
                resDto.setSwitchingFavorableTreatmentRate(RATE_MAX_PERCENT);

                // ■上記以外
            } else {
                // 乗換優遇率・分子(特例特定/一般)/乗換優遇率・分母(特例特定/一般)×100　※小数点以下3桁目を切上げ
                resDto.setSwitchingFavorableTreatmentRate(fct024Res.getTransfersPreferentialRateNumeratorToku()
                        .divide(fct024Res.getTransfersPreferentialRateDenominatorToku(), 6, RoundingMode.DOWN)
                        .multiply(new BigDecimal("100")).setScale(2, RoundingMode.UP).toString());
            }

            // ■選択口座≠ジュニアNISA口座
        } else {
            // ■乗換優遇率・分子≦0
            if (fct024Res.getTransfersPreferentialRateNumerator().compareTo(BigDecimal.ZERO) <= 0) {
                // "適用外"
                resDto.setSwitchingFavorableTreatmentRate(NOT_APPLICAVLE);

                // ■乗換優遇率・分母≦0
            } else if (fct024Res.getTransfersPreferentialRateDenominator().compareTo(BigDecimal.ZERO) <= 0) {
                // "999.99%"
                resDto.setSwitchingFavorableTreatmentRate(RATE_MAX_PERCENT);

                // ■上記以外
            } else {
                // 乗換優遇率・分子/乗換優遇率・分母×100　※小数点以下3桁目を切上げ
                resDto.setSwitchingFavorableTreatmentRate(fct024Res.getTransfersPreferentialRateNumerator()
                        .divide(fct024Res.getTransfersPreferentialRateDenominator(), 6, RoundingMode.DOWN)
                        .multiply(new BigDecimal("100")).setScale(2, RoundingMode.UP).toString());
            }
        }
        // 扱者個別データ有無
        resDto.setHandlerIndividualDataExistence(fct024Res.getDealer());
        // 乗換優遇限度額
        IfaDomesticMutualFundOrderInputSwitchingFavorableTreatmentLimit switchingFavorableTreatmentLimit = new IfaDomesticMutualFundOrderInputSwitchingFavorableTreatmentLimit();
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // 乗換優遇限度額.総合口座（今月残）
            switchingFavorableTreatmentLimit.setWholeAccountThisMonth(api001Res.getTreatmentPrice1().trim());
            // 乗換優遇限度額.総合口座（来月残）
            switchingFavorableTreatmentLimit.setWholeAccountNextMonth(api001Res.getTreatmentPrice2().trim());
            // 乗換優遇限度額.ジュニアNISA口座（今月残）
            switchingFavorableTreatmentLimit.setJrNisaAccountThisMonth(api001Res.getJrnisaTreatmentPrice1().trim());
            // 乗換優遇限度額.ジュニアNISA口座（来月残）
            switchingFavorableTreatmentLimit.setJrNisaAccountNextMonth(api001Res.getJrnisaTreatmentPrice2().trim());
        }
        resDto.setSwitchingFavorableTreatmentLimit(switchingFavorableTreatmentLimit);
        // 買付余力
        IfaDomesticMutualFundOrderInputBuyingPower buyingPower = new IfaDomesticMutualFundOrderInputBuyingPower();
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            buyingPower = getBuyingPower(api002Res, api003Res);
        }
        resDto.setBuyingPower(buyingPower);
        // 預り情報
        if (StringUtils.equals(tradeCd, TradeCd.PURCHASE_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.PURCHASE_CUMULATIVE.key)
                || StringUtils.equals(tradeCd, TradeCd.AMOUNT_PURCHASE.key)
                || StringUtils.equals(tradeCd, TradeCd.CANCEL_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.CANCEL_CUMULATIVE.key)
                || StringUtils.equals(tradeCd, TradeCd.AMOUNT_CANCEL.key)) {
            IfaDomesticMutualFundOrderInputDepositInfo depositInfo = getDepositInfo(api004Res, fct023Res, fct024Res,
                    selectDepositType, fundCodeTimes, fundCodeIssues);
            resDto.setDepositInfo(depositInfo);
        }
        // NISA買付可能最大額文言
        // ■API正常応答
        DecimalFormat format = new DecimalFormat("#,###");
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            if (StringUtils.equals(api005Res.getCode(), CODE_SUCCESS)) {
                // "NISA成長投資枠買付可能最大額:"+NISA買付可能最大額+"円（"+ISA非課税枠の買付年(受渡日で判定)+"年投資可能枠基準）"
                String isaMaxBuyAmount = formatNumber(api005Res.getIsaMaxBuyAmount().trim(), format);
                resDto.setNisaBuy("NISA成長投資枠買付可能最大額:" + isaMaxBuyAmount + "円（"
                        + api005Res.getIsaKaitukeY() + "年投資可能枠基準）");

                // ■APIのエラーコード=EN0041（NISARO申込中）の場合
            } else if (StringUtils.equals(api005Res.getCode(), EN0041)) {
                // "RO申込中（"+API002.受渡日の年+"年投資可能枠基準）"
                resDto.setNisaBuy("RO申込中（" + api002Res.getSettlementDate().substring(0, 4) + "年投資可能枠基準）");

                // ■APIのエラーコード=EN0045（NISA勘定変更申込中）の場合
            } else if (StringUtils.equals(api005Res.getCode(), EN0045)) {
                // "勘定変更申込中（"+API002.受渡日の年+"年投資可能枠基準）"
                resDto.setNisaBuy("勘定変更申込中（" + api002Res.getSettlementDate().substring(0, 4) + "年投資可能枠基準）");

                // ■APIのエラーコード=上記以外
            } else {
                // "NISA買付可能最大額 --円（--年投資可能枠基準）"
                resDto.setNisaBuy("NISA買付可能最大額 --円（--年投資可能枠基準）");
            }
        }
        // 分配金受取方法(現在の設定)
        // ■API正常応答
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            if (api006Res.getCode() != null) {
                if (StringUtils.equals(api006Res.getCode(), CODE_SUCCESS)) {
                    for (SimReinvestEntryDataO data : api006Res.getSimReinvestEntryDataO()) {
                        // ■再投資停止情報=1
                        if (StringUtils.equals(data.getReinvest(), REINVEST_RECEIPT)) {
                            // "受取"
                            resDto.setDistributionReceiveMethodWord(RECEIPT);

                            // ■再投資停止情報=2
                        } else if (StringUtils.equals(data.getReinvest(), REINVEST_RE)) {
                            // "再投資"
                            resDto.setDistributionReceiveMethodWord(RE);
                        }
                    }

                    // ■API業務エラー=EG0028（保有残高なし）の場合
                } else if (StringUtils.equals(api006Res.getCode(), EG0028)) {
                    // "設定なし"
                    resDto.setDistributionReceiveMethodWord(NO_SETTING);
                }
            } else {
                // "設定なし"
                resDto.setDistributionReceiveMethodWord(NO_SETTING);
            }
        }
        // ポイント
        IfaDomesticMutualFundOrderInputPoint point = new IfaDomesticMutualFundOrderInputPoint();
        if (StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)) {
            // ポイント.ポイント種別
            point.setPointClass(fct039Res.getPointType());
            // ポイント.ポイント数
            point.setPointCount(fct039Res.getPointNumber());
            // ポイント.うち期間固定ポイント
            point.setFixedPeriodPoint(fct039Res.getRestrictPointSum());
            // ポイント.最短有効期限
            point.setShortestExpirationDate(fct039Res.getExpiredDate());
            // ポイント.最低利用ポイント数
            point.setMinUsePointCount(fct039Res.getMinimumUsePoint());
            // ポイント.利用ポイント単位
            point.setUsePointUnit(fct039Res.getUsePointUnit());
        }
        // ポイント.ポイント表示エリア表示可否
        point.setPointShowAreaFlag(fct039Res.getPointDisplayAreaAvailability());
        // ポイント.ポイント名表示可否
        point.setPointNameDisplayFlag(fct039Res.getPointNameDisplayAvailability());
        // ポイント.ポイント数表示可否
        point.setPointCountDisplayFlag(fct039Res.getPointNumberDisplayAvailability());
        // ポイント.うち期間固定ポイント表示可否
        point.setFixedPeriodPointDisplayFlag(fct039Res.getFixedTermPointDisplayAvailability());
        // ポイント.最短有効期限表示可否
        point.setShortestExpirationDateDisplayFlag(fct039Res.getPointShortLimitDisplayAvailability());
        // ポイント.ポイント利用エリア表示可否
        point.setPointUseAreaFlag(fct039Res.getPointUseAreaAvailability());
        resDto.setPoint(point);

        // 取引種別
        resDto.setTradeCd(tradeCdMutualFund);
        // 乗換優遇枠適用表示状態
        resDto.setTransfersPreferentialQuotaApplication(transfersPreferentialQuotaApplication);

        // 短期売却確認期間
        resDto.setShortTermSaleConfirm(sql001Res.getName());
        // 償還前売却確認期間
        resDto.setPreRedemptionSellConfirmSelect(sql002Res.getName());

        return resDto;
    }

    /**
     * 取引種別（国内投信）
     *
     * @param mutualFundSellBuyType 売買区分（投信）
     * @param fct023Res fct023レスポンス
     */

    private String getTradeCdMutualFund(String mutualFundSellBuyType, OutputFct023Dto fct023Res) {
        // 取引種別
        // ■売買区分（投信）=1:買（購入）の場合
        if (StringUtils.equals(mutualFundSellBuyType, BUY)) {
            // ■ファンドタイプ=累投　または　積立　または　累投／積立　の場合
            if (StringUtils.equals(fct023Res.getFundType(), FundType.CUMULATIVE_PITCH.key)
                    || StringUtils.equals(fct023Res.getFundType(), FundType.ACCUMULATE.key)
                    || StringUtils.equals(fct023Res.getFundType(), FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                // 1:購入（累投）
                return TradeCd.BUY_CUMULATIVE.key;

                // ■ファンドタイプ=一般　の場合
            } else if (StringUtils.equals(fct023Res.getFundType(), FundType.GENERAL.key)) {
                // 0:購入（一般）
                return TradeCd.BUY_GENERAL.key;
            }
        } else {
            // ■ファンドタイプ=累投　または　積立　または　累投／積立　の場合
            if (StringUtils.equals(fct023Res.getFundType(), FundType.CUMULATIVE_PITCH.key)
                    || StringUtils.equals(fct023Res.getFundType(), FundType.ACCUMULATE.key)
                    || StringUtils.equals(fct023Res.getFundType(), FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                // 8:解約（累投）
                return TradeCd.CANCEL_CUMULATIVE.key;

                // ■ファンドタイプ=一般　の場合
            } else if (StringUtils.equals(fct023Res.getFundType(), FundType.GENERAL.key)) {
                // 7:解約（一般）
                return TradeCd.CANCEL_GENERAL.key;
            }
        }

        return null;
    }

    /**
     * 乗換優遇枠適用表示状態
     *
     * @param api001Res api001レスポンス
     * @param api002Res api002レスポンス
     * @param tradeCd 取引種別
     * @throws Exception システムエラー
     */

    private String getTransfersPreferentialQuotaApplication(
            QueryFundOutData api001Res, QueryFundSettlementDateOutData api002Res, String selectAccountType, String tradeCd
    ) throws Exception {
        // 乗換優遇枠適用表示状態
        // ■取引種別=購入（累投）　または　購入（一般）　の場合
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 日付取得
        LocalDate thisMonth;
        try {
            Date systemDate = systemDateDao.getSystemDate();
            thisMonth = systemDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            LOGGER.error("IfaDomesticMutualFundOrderInput.systemDate Exception[{}]", e.getMessage());
            e.printStackTrace();
            throw e;
        }
        // 日付_来月
        LocalDate nextMonth = thisMonth.plusMonths(1);
        if (StringUtils.equals(tradeCd, TradeCd.BUY_CUMULATIVE.key)
                || StringUtils.equals(tradeCd, TradeCd.BUY_GENERAL.key)) {
            // 受渡日
            LocalDate settlement = LocalDate.parse(api002Res.getSettlementDate(), formatter);
            // ■選択口座=総合口座の場合
            if (StringUtils.equals(selectAccountType, WHOLE_AMOUNT)) {
                // ■受渡日=当月　の場合
                if (settlement.getMonth() == thisMonth.getMonth()) {
                    // ■当月投信償還乗換優遇枠>0 の場合
                    if (Long.parseLong(api001Res.getTreatmentPrice1().trim()) > 0) {
                        // 項目表示
                        return DISPLAY;
                    } else {
                        // 項目非活性
                        return NO_ACTIVE;
                    }

                    // ■受渡日=来月　の場合
                } else if (settlement.getMonth() == nextMonth.getMonth()) {
                    // ■来月投信償還乗換優遇枠>0 の場合
                    if (Long.parseLong(api001Res.getTreatmentPrice2().trim()) > 0) {
                        // 項目表示
                        return DISPLAY;
                    } else {
                        // 項目非活性
                        return NO_ACTIVE;
                    }
                }

                // ■選択口座=ジュニアNISA口座の場合
            } else if (StringUtils.equals(selectAccountType, JR_NISA_ACCOUNT)) {
                // ■受渡日=当月　の場合
                if (settlement.getMonth() == thisMonth.getMonth()) {
                    // ■当月投信償還乗換優遇枠(JrNISA)>0 の場合
                    if (Long.parseLong(api001Res.getJrnisaTreatmentPrice1().trim()) > 0) {
                        // 項目表示
                        return DISPLAY;
                    } else {
                        // 項目非活性
                        return NO_ACTIVE;
                    }

                    // ■受渡日=来月　の場合
                } else if (settlement.getMonth() == nextMonth.getMonth()) {
                    // ■来月投信償還乗換優遇枠(JrNISA)>0 の場合
                    if (Long.parseLong(api001Res.getJrnisaTreatmentPrice2().trim()) > 0) {
                        // 項目表示
                        return DISPLAY;
                    } else {
                        // 項目非活性
                        return NO_ACTIVE;
                    }
                }
            }
        }
        // ■取引種別=上記以外
        // 項目非表示
        return NO_DISPLAY;
    }

    /**
     * 手数料率リスト(n)算出
     *
     * @param fct024Res fct024レスポンス
     * @param selectAccountType 選択口座
     * @param fundType ファンドタイプ
     * @param selectDepositType  選択預り区分
     * @param mutualFundSellBuyType 売買区分（投信）
     */
    private List<IfaDomesticMutualFundOrderInputCommRate> getCommRateList(OutputFct024Dto fct024Res,
            String selectAccountType, String fundType, String selectDepositType, String mutualFundSellBuyType) {

        IfaDomesticMutualFundOrderInputCommRate commRate1 = new IfaDomesticMutualFundOrderInputCommRate();
        IfaDomesticMutualFundOrderInputCommRate commRate2 = new IfaDomesticMutualFundOrderInputCommRate();
        IfaDomesticMutualFundOrderInputCommRate commRate3 = new IfaDomesticMutualFundOrderInputCommRate();
        IfaDomesticMutualFundOrderInputCommRate commRate4 = new IfaDomesticMutualFundOrderInputCommRate();
        IfaDomesticMutualFundOrderInputCommRate commRate5 = new IfaDomesticMutualFundOrderInputCommRate();
        IfaDomesticMutualFundOrderInputCommRate commRate6 = new IfaDomesticMutualFundOrderInputCommRate();
        IfaDomesticMutualFundOrderInputCommRate commRate7 = new IfaDomesticMutualFundOrderInputCommRate();

        // 手数料率条件
        // ■選択口座=ジュニアNISA口座　の場合
        if (StringUtils.equals(selectAccountType, JR_NISA_ACCOUNT)) {
            // ■ファンドタイプ=一般　の場合　の場合
            if (StringUtils.equals(fundType, FundType.GENERAL.key)) {
                // 手数料条件n　＝　(販売手数料上限n(特例特定/一般)+1)　×　販売手数料単位口数
                // 販売手数料上限n(特例特定/一般)が0の場合、スキップ対象とするために手数料条件nを0にする
                if (fct024Res.getSalesCommLimitSpecificGeneral1().compareTo(BigDecimal.ZERO) == 0) {
                    commRate1.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate1.setCommRateConditions((fct024Res.getSalesCommLimitSpecificGeneral1().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral2().compareTo(BigDecimal.ZERO) == 0) {
                    commRate2.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate2.setCommRateConditions((fct024Res.getSalesCommLimitSpecificGeneral2().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral3().compareTo(BigDecimal.ZERO) == 0) {
                    commRate3.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate3.setCommRateConditions((fct024Res.getSalesCommLimitSpecificGeneral3().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral4().compareTo(BigDecimal.ZERO) == 0) {
                    commRate4.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate4.setCommRateConditions((fct024Res.getSalesCommLimitSpecificGeneral4().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral5().compareTo(BigDecimal.ZERO) == 0) {
                    commRate5.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate5.setCommRateConditions((fct024Res.getSalesCommLimitSpecificGeneral5().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral6().compareTo(BigDecimal.ZERO) == 0) {
                    commRate6.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate6.setCommRateConditions((fct024Res.getSalesCommLimitSpecificGeneral6().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral7().compareTo(BigDecimal.ZERO) == 0) {
                    commRate7.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate7.setCommRateConditions((fct024Res.getSalesCommLimitSpecificGeneral7().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }

                // ■ファンドタイプ=累投　または　積立　または　累投／積立　の場合
            } else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.key)
                    || StringUtils.equals(fundType, FundType.ACCUMULATE.key)
                    || StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                // 手数料条件n　＝　販売手数料上限n(特例特定/一般)+1
                if (fct024Res.getSalesCommLimitSpecificGeneral1().compareTo(BigDecimal.ZERO) == 0) {
                    commRate1.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate1.setCommRateConditions(fct024Res.getSalesCommLimitSpecificGeneral1().add(BigDecimal.ONE));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral2().compareTo(BigDecimal.ZERO) == 0) {
                    commRate2.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate2.setCommRateConditions(fct024Res.getSalesCommLimitSpecificGeneral2().add(BigDecimal.ONE));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral3().compareTo(BigDecimal.ZERO) == 0) {
                    commRate3.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate3.setCommRateConditions(fct024Res.getSalesCommLimitSpecificGeneral3().add(BigDecimal.ONE));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral4().compareTo(BigDecimal.ZERO) == 0) {
                    commRate4.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate4.setCommRateConditions(fct024Res.getSalesCommLimitSpecificGeneral4().add(BigDecimal.ONE));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral5().compareTo(BigDecimal.ZERO) == 0) {
                    commRate5.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate5.setCommRateConditions(fct024Res.getSalesCommLimitSpecificGeneral5().add(BigDecimal.ONE));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral6().compareTo(BigDecimal.ZERO) == 0) {
                    commRate6.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate6.setCommRateConditions(fct024Res.getSalesCommLimitSpecificGeneral6().add(BigDecimal.ONE));
                }
                if (fct024Res.getSalesCommLimitSpecificGeneral7().compareTo(BigDecimal.ZERO) == 0) {
                    commRate7.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate7.setCommRateConditions(fct024Res.getSalesCommLimitSpecificGeneral7().add(BigDecimal.ONE));
                }
            }

            // ■選択口座＝ジュニアNISA口座　以外　の場合
        } else {
            // ■ファンドタイプ=一般　の場合　の場合
            if (StringUtils.equals(fundType, FundType.GENERAL.key)) {
                // 手数料条件n　=　(販売手数料上限n+1)　×　販売手数料単位口数
                if (fct024Res.getSalesCommLimit1().compareTo(BigDecimal.ZERO) == 0) {
                    commRate1.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate1.setCommRateConditions((fct024Res.getSalesCommLimit1().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimit2().compareTo(BigDecimal.ZERO) == 0) {
                    commRate2.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate2.setCommRateConditions((fct024Res.getSalesCommLimit2().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimit3().compareTo(BigDecimal.ZERO) == 0) {
                    commRate3.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate3.setCommRateConditions((fct024Res.getSalesCommLimit3().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimit4().compareTo(BigDecimal.ZERO) == 0) {
                    commRate4.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate4.setCommRateConditions((fct024Res.getSalesCommLimit4().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimit5().compareTo(BigDecimal.ZERO) == 0) {
                    commRate5.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate5.setCommRateConditions((fct024Res.getSalesCommLimit5().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimit6().compareTo(BigDecimal.ZERO) == 0) {
                    commRate6.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate6.setCommRateConditions((fct024Res.getSalesCommLimit6().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }
                if (fct024Res.getSalesCommLimit7().compareTo(BigDecimal.ZERO) == 0) {
                    commRate7.setCommRateConditions(BigDecimal.ZERO);
                } else {
                    commRate7.setCommRateConditions((fct024Res.getSalesCommLimit7().add(BigDecimal.ONE)
                            .multiply(fct024Res.getSalesCommTanikuchi())));
                }

                // ■ファンドタイプ=累投　または　積立　または　累投／積立　の場合
            } else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.key)
                    || StringUtils.equals(fundType, FundType.ACCUMULATE.key)
                    || StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.key)) {
                // ■選択預り区分=△:特定/一般　の場合
                if (StringUtils.equals(selectDepositType, MutualFundDeposit.SPECIFIC_GENERAL.key)) {
                    // 手数料条件n　=　販売手数料上限n+1
                    if (fct024Res.getSalesCommLimit1().compareTo(BigDecimal.ZERO) == 0) {
                        commRate1.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate1.setCommRateConditions(fct024Res.getSalesCommLimit1().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit2().compareTo(BigDecimal.ZERO) == 0) {
                        commRate2.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate2.setCommRateConditions(fct024Res.getSalesCommLimit2().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit3().compareTo(BigDecimal.ZERO) == 0) {
                        commRate3.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate3.setCommRateConditions(fct024Res.getSalesCommLimit3().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit4().compareTo(BigDecimal.ZERO) == 0) {
                        commRate4.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate4.setCommRateConditions(fct024Res.getSalesCommLimit4().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit5().compareTo(BigDecimal.ZERO) == 0) {
                        commRate5.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate5.setCommRateConditions(fct024Res.getSalesCommLimit5().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit6().compareTo(BigDecimal.ZERO) == 0) {
                        commRate6.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate6.setCommRateConditions(fct024Res.getSalesCommLimit6().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit7().compareTo(BigDecimal.ZERO) == 0) {
                        commRate7.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate7.setCommRateConditions(fct024Res.getSalesCommLimit7().add(BigDecimal.ONE));
                    }

                    // ■選択預り区分=H:総合NISA（成長投資枠）　の場合
                } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.WHOLE_NISA.key)) {
                    // 手数料条件n　=　販売手数料上限n(総合NISA・成長投資枠)+1
                    if (fct024Res.getSalesCommLimitSeichou1().compareTo(BigDecimal.ZERO) == 0) {
                        commRate1.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate1.setCommRateConditions(fct024Res.getSalesCommLimitSeichou1().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimitSeichou2().compareTo(BigDecimal.ZERO) == 0) {
                        commRate2.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate2.setCommRateConditions(fct024Res.getSalesCommLimitSeichou2().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimitSeichou3().compareTo(BigDecimal.ZERO) == 0) {
                        commRate3.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate3.setCommRateConditions(fct024Res.getSalesCommLimitSeichou3().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimitSeichou4().compareTo(BigDecimal.ZERO) == 0) {
                        commRate4.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate4.setCommRateConditions(fct024Res.getSalesCommLimitSeichou4().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimitSeichou5().compareTo(BigDecimal.ZERO) == 0) {
                        commRate5.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate5.setCommRateConditions(fct024Res.getSalesCommLimitSeichou5().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimitSeichou6().compareTo(BigDecimal.ZERO) == 0) {
                        commRate6.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate6.setCommRateConditions(fct024Res.getSalesCommLimitSeichou6().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimitSeichou7().compareTo(BigDecimal.ZERO) == 0) {
                        commRate7.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate7.setCommRateConditions(fct024Res.getSalesCommLimitSeichou7().add(BigDecimal.ONE));
                    }

                    // ■上記以外
                } else {
                    // 手数料条件n　=　販売手数料上限n+1
                    if (fct024Res.getSalesCommLimit1().compareTo(BigDecimal.ZERO) == 0) {
                        commRate1.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate1.setCommRateConditions(fct024Res.getSalesCommLimit1().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit2().compareTo(BigDecimal.ZERO) == 0) {
                        commRate2.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate2.setCommRateConditions(fct024Res.getSalesCommLimit2().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit3().compareTo(BigDecimal.ZERO) == 0) {
                        commRate3.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate3.setCommRateConditions(fct024Res.getSalesCommLimit3().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit4().compareTo(BigDecimal.ZERO) == 0) {
                        commRate4.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate4.setCommRateConditions(fct024Res.getSalesCommLimit4().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit5().compareTo(BigDecimal.ZERO) == 0) {
                        commRate5.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate5.setCommRateConditions(fct024Res.getSalesCommLimit5().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit6().compareTo(BigDecimal.ZERO) == 0) {
                        commRate6.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate6.setCommRateConditions(fct024Res.getSalesCommLimit6().add(BigDecimal.ONE));
                    }
                    if (fct024Res.getSalesCommLimit7().compareTo(BigDecimal.ZERO) == 0) {
                        commRate7.setCommRateConditions(BigDecimal.ZERO);
                    } else {
                        commRate7.setCommRateConditions(fct024Res.getSalesCommLimit7().add(BigDecimal.ONE));
                    }
                }
            }
        }

        // 手数料率
        // ■選択口座=ジュニアNISA口座　の場合
        BigDecimal tax = new BigDecimal("1.10");
        if (StringUtils.equals(selectAccountType, JR_NISA_ACCOUNT)) {
            // ■売買区分（投信）=1:買（購入）の場合
            if (StringUtils.equals(mutualFundSellBuyType, BUY)) {
                // 手数料n　＝　販売手数料率n(特例特定/一般)　×　1.10
                commRate1.setCommRate(fct024Res.getSalesCommRateSpecificGeneral1().multiply(tax));
                commRate2.setCommRate(fct024Res.getSalesCommRateSpecificGeneral2().multiply(tax));
                commRate3.setCommRate(fct024Res.getSalesCommRateSpecificGeneral3().multiply(tax));
                commRate4.setCommRate(fct024Res.getSalesCommRateSpecificGeneral4().multiply(tax));
                commRate5.setCommRate(fct024Res.getSalesCommRateSpecificGeneral5().multiply(tax));
                commRate6.setCommRate(fct024Res.getSalesCommRateSpecificGeneral6().multiply(tax));
                commRate7.setCommRate(fct024Res.getSalesCommRateSpecificGeneral7().multiply(tax));

                // ■売買区分（投信）=1:買（購入）以外の場合     　※要素数は1
            } else {
                // ■選択預り区分=　5:Jr特定　または　6:Jr一般　の場合
                if (StringUtils.equals(selectDepositType, MutualFundDeposit.JR_SPECIFIC_JR_GENERAL.key)
                        || StringUtils.equals(selectDepositType, MutualFundDeposit.JR_GENERAL.key)) {
                    // 手数料1　＝　解約手数料率1(特例特定/一般)　×　1.10
                    commRate1.setCommRate(
                            fct024Res.getCancelCommRateSpecificGeneral1().multiply(tax));
                    commRate2.setCommRate(BigDecimal.ZERO);
                    commRate3.setCommRate(BigDecimal.ZERO);
                    commRate4.setCommRate(BigDecimal.ZERO);
                    commRate5.setCommRate(BigDecimal.ZERO);
                    commRate6.setCommRate(BigDecimal.ZERO);
                    commRate7.setCommRate(BigDecimal.ZERO);

                    // ■選択預り区分=J:JrNISA（継続管理勘定）　の場合
                } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.JR_NISA_CONTINUOUS.key)) {
                    // 手数料1　＝　解約手数料率1(継続管理勘定)　×　1.10
                    commRate1.setCommRate(fct024Res.getCancelCommRateKeizoku1().multiply(tax));
                    commRate2.setCommRate(BigDecimal.ZERO);
                    commRate3.setCommRate(BigDecimal.ZERO);
                    commRate4.setCommRate(BigDecimal.ZERO);
                    commRate5.setCommRate(BigDecimal.ZERO);
                    commRate6.setCommRate(BigDecimal.ZERO);
                    commRate7.setCommRate(BigDecimal.ZERO);

                    // ■選択預り区分=7:JrNISA　の場合
                } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.JR_NISA.key)) {
                    // 手数料1　＝　解約手数料率1(継続管理勘定)　×　1.10
                    commRate1.setCommRate(fct024Res.getCancelCommRateJrNisa1().multiply(tax));
                    commRate2.setCommRate(BigDecimal.ZERO);
                    commRate3.setCommRate(BigDecimal.ZERO);
                    commRate4.setCommRate(BigDecimal.ZERO);
                    commRate5.setCommRate(BigDecimal.ZERO);
                    commRate6.setCommRate(BigDecimal.ZERO);
                    commRate7.setCommRate(BigDecimal.ZERO);
                }
            }

            // ■選択口座=ジュニアNISA口座　以外　の場合
        } else {
            // ■売買区分（投信）=1:買（購入）の場合
            if (StringUtils.equals(mutualFundSellBuyType, BUY)) {
                // ■選択預り区分=　△:特定/一般　または　1:一般　の場合
                if (StringUtils.equals(selectDepositType, MutualFundDeposit.SPECIFIC_GENERAL.key)
                        || StringUtils.equals(selectDepositType, MutualFundDeposit.GENERAL.key)) {
                    // 手数料n　＝　販売手数料率n　×　1.10
                    commRate1.setCommRate(fct024Res.getSalesCommRate1().multiply(tax));
                    commRate2.setCommRate(fct024Res.getSalesCommRate2().multiply(tax));
                    commRate3.setCommRate(fct024Res.getSalesCommRate3().multiply(tax));
                    commRate4.setCommRate(fct024Res.getSalesCommRate4().multiply(tax));
                    commRate5.setCommRate(fct024Res.getSalesCommRate5().multiply(tax));
                    commRate6.setCommRate(fct024Res.getSalesCommRate6().multiply(tax));
                    commRate7.setCommRate(fct024Res.getSalesCommRate7().multiply(tax));

                    // ■選択預り区分=　H:総合NISA（成長投資枠）　の場合
                } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.WHOLE_NISA.key)) {
                    // 手数料n　＝　販売手数料率n(総合NISA・成長投資枠)　×　1.10
                    commRate1.setCommRate(fct024Res.getSalesCommRateSeichou1().multiply(tax));
                    commRate2.setCommRate(fct024Res.getSalesCommRateSeichou2().multiply(tax));
                    commRate3.setCommRate(fct024Res.getSalesCommRateSeichou3().multiply(tax));
                    commRate4.setCommRate(fct024Res.getSalesCommRateSeichou4().multiply(tax));
                    commRate5.setCommRate(fct024Res.getSalesCommRateSeichou5().multiply(tax));
                    commRate6.setCommRate(fct024Res.getSalesCommRateSeichou6().multiply(tax));
                    commRate7.setCommRate(fct024Res.getSalesCommRateSeichou7().multiply(tax));
                }

                // ■売買区分（投信）=1:買（購入）以外の場合     　※要素数は1
            } else {
                // ■選択預り区分=　0:特定　または　1:一般　の場合
                if (StringUtils.equals(selectDepositType, MutualFundDeposit.SPECIFIC.key)
                        || StringUtils.equals(selectDepositType, MutualFundDeposit.GENERAL.key)) {
                    // 手数料1　＝　解約手数料率1　×　1.10
                    commRate1.setCommRate(fct024Res.getCancelCommRate1().multiply(tax));
                    commRate2.setCommRate(BigDecimal.ZERO);
                    commRate3.setCommRate(BigDecimal.ZERO);
                    commRate4.setCommRate(BigDecimal.ZERO);
                    commRate5.setCommRate(BigDecimal.ZERO);
                    commRate6.setCommRate(BigDecimal.ZERO);
                    commRate7.setCommRate(BigDecimal.ZERO);

                    // ■選択預り区分=　4:旧NISA　の場合
                } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.OLD_NISA.key)) {
                    // 手数料1　＝　解約手数料率1(ISA)　×　1.10
                    commRate1.setCommRate(fct024Res.getCancelCommRateIsa1().multiply(tax));
                    commRate2.setCommRate(BigDecimal.ZERO);
                    commRate3.setCommRate(BigDecimal.ZERO);
                    commRate4.setCommRate(BigDecimal.ZERO);
                    commRate5.setCommRate(BigDecimal.ZERO);
                    commRate6.setCommRate(BigDecimal.ZERO);
                    commRate7.setCommRate(BigDecimal.ZERO);

                    // ■選択預り区分=　H:総合NISA（成長投資枠）　の場合
                } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.WHOLE_NISA.key)) {
                    // 手数料1　＝　解約手数料率1(総合NISA・成長投資枠)　×　1.10
                    commRate1.setCommRate(fct024Res.getCancelCommRateSeichou1().multiply(tax));
                    commRate2.setCommRate(BigDecimal.ZERO);
                    commRate3.setCommRate(BigDecimal.ZERO);
                    commRate4.setCommRate(BigDecimal.ZERO);
                    commRate5.setCommRate(BigDecimal.ZERO);
                    commRate6.setCommRate(BigDecimal.ZERO);
                    commRate7.setCommRate(BigDecimal.ZERO);

                    // ■選択預り区分=　8:つみたてNISA　または　I:総合NISA（つみたて投資枠）　の場合
                } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.OLD_NISA_RESERVE.key)
                        || StringUtils.equals(selectDepositType, MutualFundDeposit.WHOLE_NISA_RESERVE.key)) {
                    // 手数料1　=　0
                    commRate1.setCommRate(BigDecimal.ZERO);
                    commRate2.setCommRate(BigDecimal.ZERO);
                    commRate3.setCommRate(BigDecimal.ZERO);
                    commRate4.setCommRate(BigDecimal.ZERO);
                    commRate5.setCommRate(BigDecimal.ZERO);
                    commRate6.setCommRate(BigDecimal.ZERO);
                    commRate7.setCommRate(BigDecimal.ZERO);
                }
            }
        }

        List<IfaDomesticMutualFundOrderInputCommRate> commRateList = new ArrayList<IfaDomesticMutualFundOrderInputCommRate>();

        // 手数料条件nと手数料率nが両方とも0の場合は、n-1を要素数とする。
        // ※手数料条件は処理の途中で+1されている
        if (commRate1.getCommRate() != null && commRate1.getCommRateConditions() != null) {
            if (!(commRate1.getCommRate().compareTo(BigDecimal.ZERO) == 0
                    && commRate1.getCommRateConditions().compareTo(BigDecimal.ZERO) == 0)) {
                commRateList.add(commRate1);
            }
        }
        if (commRate2.getCommRate() != null && commRate2.getCommRateConditions() != null) {
            if (!(commRate2.getCommRate().compareTo(BigDecimal.ZERO) == 0
                    && commRate2.getCommRateConditions().compareTo(BigDecimal.ZERO) == 0)) {
                commRateList.add(commRate2);
            }
        }
        if (commRate3.getCommRate() != null && commRate3.getCommRateConditions() != null) {
            if (!(commRate3.getCommRate().compareTo(BigDecimal.ZERO) == 0
                    && commRate3.getCommRateConditions().compareTo(BigDecimal.ZERO) == 0)) {
                commRateList.add(commRate3);
            }
        }
        if (commRate4.getCommRate() != null && commRate4.getCommRateConditions() != null) {
            if (!(commRate4.getCommRate().compareTo(BigDecimal.ZERO) == 0
                    && commRate4.getCommRateConditions().compareTo(BigDecimal.ZERO) == 0)) {
                commRateList.add(commRate4);
            }
        }
        if (commRate5.getCommRate() != null && commRate5.getCommRateConditions() != null) {
            if (!(commRate5.getCommRate().compareTo(BigDecimal.ZERO) == 0
                    && commRate5.getCommRateConditions().compareTo(BigDecimal.ZERO) == 0)) {
                commRateList.add(commRate5);
            }
        }
        if (commRate6.getCommRate() != null && commRate6.getCommRateConditions() != null) {
            if (!(commRate6.getCommRate().compareTo(BigDecimal.ZERO) == 0
                    && commRate6.getCommRateConditions().compareTo(BigDecimal.ZERO) == 0)) {
                commRateList.add(commRate6);
            }
        }
        if (commRate7.getCommRate() != null && commRate7.getCommRateConditions() != null) {
            if (!(commRate7.getCommRate().compareTo(BigDecimal.ZERO) == 0
                    && commRate7.getCommRateConditions().compareTo(BigDecimal.ZERO) == 0)) {
                commRateList.add(commRate7);
            }
        }

        // 要素数0となった場合は強制的に1とする。
        if (commRateList.size() == 0) {
            IfaDomesticMutualFundOrderInputCommRate commRate = new IfaDomesticMutualFundOrderInputCommRate();
            commRate.setCommRate(BigDecimal.ZERO);
            commRateList.add(commRate);
        }

        return commRateList;
    }

    /**
     * 買付余力算出
     *
     * @param api002Res api002レスポンス
     * @param api003Res api003レスポンス
     */
    private IfaDomesticMutualFundOrderInputBuyingPower getBuyingPower(QueryFundSettlementDateOutData api002Res,
            QueryAccountBalanceOutData api003Res) {

        IfaDomesticMutualFundOrderInputBuyingPower buyingPower = new IfaDomesticMutualFundOrderInputBuyingPower();
        // 総合口座
        if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                .parseInt(api003Res.getT5().getSettlementDateT())) {
            // 受渡日以降の受渡日(T+n).買付余力の最小値をセット
            List<BigDecimal> settlement = new ArrayList<BigDecimal>();
            if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                    .parseInt(api003Res.getT1().getSettlementDateT())) {
                settlement.add(new BigDecimal(api003Res.getT1().getBuyingPowerTotal()));
            }
            if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                    .parseInt(api003Res.getT2().getSettlementDateT())) {
                settlement.add(new BigDecimal(api003Res.getT2().getBuyingPowerTotal()));
            }
            if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                    .parseInt(api003Res.getT3().getSettlementDateT())) {
                settlement.add(new BigDecimal(api003Res.getT3().getBuyingPowerTotal()));
            }
            if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                    .parseInt(api003Res.getT4().getSettlementDateT())) {
                settlement.add(new BigDecimal(api003Res.getT4().getBuyingPowerTotal()));
            }
            settlement.add(new BigDecimal(api003Res.getT5().getBuyingPowerTotal()));
            BigDecimal low = settlement.get(0);
            for (BigDecimal settle : settlement) {
                if (settle.compareTo(low) < 0) {
                    low = settle;
                }
            }
            buyingPower.setWholeAccount(low.toString());

            // ■上記以外
        } else {
            buyingPower.setWholeAccount(api003Res.getT5().getBuyingPowerTotal());
        }

        // ジュニアNISA口座
        if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                .parseInt(api003Res.getT5().getSettlementDateT())) {
            // 受渡日以降の受渡日(T+n).買付余力の最小値をセット
            List<BigDecimal> settlement = new ArrayList<BigDecimal>();
            if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                    .parseInt(api003Res.getT1().getSettlementDateT())) {
                settlement.add(new BigDecimal(api003Res.getT1Jr().getBuyingPowerTotalJrnisa()));
            }
            if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                    .parseInt(api003Res.getT2().getSettlementDateT())) {
                settlement.add(new BigDecimal(api003Res.getT2Jr().getBuyingPowerTotalJrnisa()));
            }
            if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                    .parseInt(api003Res.getT3().getSettlementDateT())) {
                settlement.add(new BigDecimal(api003Res.getT3Jr().getBuyingPowerTotalJrnisa()));
            }
            if (Integer.parseInt(api002Res.getSettlementDate()) <= Integer
                    .parseInt(api003Res.getT4().getSettlementDateT())) {
                settlement.add(new BigDecimal(api003Res.getT4Jr().getBuyingPowerTotalJrnisa()));
            }
            settlement.add(new BigDecimal(api003Res.getT5Jr().getBuyingPowerTotalJrnisa()));
            BigDecimal low = settlement.get(0);
            for (BigDecimal settle : settlement) {
                if (settle.compareTo(low) < 0) {
                    low = settle;
                }
            }
            buyingPower.setJrNisaAccount(low.toString());

            // ■上記以外
        } else {
            buyingPower.setJrNisaAccount(api003Res.getT5Jr().getBuyingPowerTotalJrnisa());
        }

        return buyingPower;
    }

    /**
     * 預り情報設定
     *
     * @param api004Res api004レスポンス
     * @param fct023Res fct023レスポンス
     * @param fct024Res fct024レスポンス
     * @param selectDepositType 選択預り区分
     * @param fundCodeTimes ファンドコード（回数）
     * @param fundCodeIssues ファンドコード（号）
     */
    private IfaDomesticMutualFundOrderInputDepositInfo getDepositInfo(QueryAccountPositionSumWebOutData api004Res,
            OutputFct023Dto fct023Res, OutputFct024Dto fct024Res, String selectDepositType, String fundCodeTimes,
            String fundCodeIssues) {

        BigDecimal sum = BigDecimal.ZERO;
        // ■検索結果.非特定預り区分=選択預り区分　かつ
        // 検索結果.回数=リクエスト.ファンドコード（回数）　かつ
        // 検索結果.号1=リクエスト.ファンドコード（号）の前1桁　かつ
        // 検索結果.号2='リクエスト.ファンドコード（号）の後2桁　の明細を対象に以下を算出
        if (api004Res.getAccountSumWebData() != null) {
            for (AccountSumWebData data : api004Res.getAccountSumWebData()) {
                // 検索結果.非特定預り区分の'-'は'1'に置き換えて比較する
                String apiDepositType = data.getHitokuteiKbn();
                if (StringUtils.equals(apiDepositType, MutualFundDeposit.NON_SPECIFIC_ACCOUNT.key)) {
                    apiDepositType = MutualFundDeposit.GENERAL.key;
                }
                if (StringUtils.equals(apiDepositType, selectDepositType)
                        && StringUtils.equals(data.getSerNo(), fundCodeTimes)
                        && StringUtils.equals(data.getSubCode1(), fundCodeIssues.substring(0, 1)) && StringUtils
                                .equals(data.getSubCode2(), fundCodeIssues.substring(fundCodeIssues.length() - 2))) {
                    // Σ(残高数量-売却発注済数量)
                    sum = sum.add(new BigDecimal(data.getPosition().trim()))
                            .subtract(new BigDecimal(data.getOrderedQuantity().trim()));
                }
            }
        }
        IfaDomesticMutualFundOrderInputDepositInfo depositInfo = new IfaDomesticMutualFundOrderInputDepositInfo();
        // 売却可能（口数）
        depositInfo.setSellAbleLot(sum.toString());

        BigDecimal cal1;
        BigDecimal cal2;
        BigDecimal cancelComm = null;
        // Σ(残高数量-売却発注済数量)×基準価額/基準価額単位×0.95　(小数点以下を四捨五入)
        cal1 = sum.multiply(fct024Res.getBasePrice()).divide(fct023Res.getBasePriceUnit(), 20, RoundingMode.DOWN)
                .multiply(new BigDecimal("0.95")).setScale(0, RoundingMode.HALF_UP);
        // 選択預り区分=△:特定/一般、1:一般、0:特定の場合
        if (StringUtils.equals(selectDepositType, MutualFundDeposit.SPECIFIC_GENERAL.key)
                || StringUtils.equals(selectDepositType, MutualFundDeposit.SPECIFIC.key)
                || StringUtils.equals(selectDepositType, MutualFundDeposit.GENERAL.key)) {
            // 解約手数料率＝解約手数料率1
            cancelComm = fct024Res.getCancelCommRate1();

            // 選択預り区分=4:旧NISA、8:旧つみたてNISAの場合
        } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.OLD_NISA.key)
                || StringUtils.equals(selectDepositType, MutualFundDeposit.OLD_NISA_RESERVE.key)) {
            // 解約手数料率＝解約手数料率1(ISA)
            cancelComm = fct024Res.getCancelCommRateIsa1();

            // 選択預り区分=H:総合NISA（成長投資枠）の場合
        } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.WHOLE_NISA.key)) {
            // 解約手数料率＝解約手数料率1(総合NISA・成長投資枠)
            cancelComm = fct024Res.getCancelCommRateSeichou1();

            // 選択預り区分=I:総合NISA（つみたて投資枠）の場合
        } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.WHOLE_NISA_RESERVE.key)) {
            // 解約手数料率＝0
            cancelComm = BigDecimal.ZERO;

            // 選択預り区分=5:Jr特定/Jr一般、5:Jr特定、6:Jr一般の場合
        } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.JR_SPECIFIC_JR_GENERAL.key)
                || StringUtils.equals(selectDepositType, MutualFundDeposit.JR_GENERAL.key)) {
            // 解約手数料率＝解約手数料率1(特例特定/一般)
            cancelComm = fct024Res.getCancelCommRateSpecificGeneral1();

            // 選択預り区分=J:JrNISA（継続管理勘定）の場合
        } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.JR_NISA_CONTINUOUS.key)) {
            // 解約手数料率＝解約手数料率1(継続管理勘定)
            cancelComm = fct024Res.getCancelCommRateKeizoku1();

            // 選択預り区分=7:JrNISAの場合
        } else if (StringUtils.equals(selectDepositType, MutualFundDeposit.JR_NISA.key)) {
            // 解約手数料率＝解約手数料率1(継続管理勘定)
            cancelComm = fct024Res.getCancelCommRateJrNisa1();
        }

        if (cancelComm != null) {
            // cal1×解約手数料率※×0.01　(小数点以下を切捨て)
            cal2 = cal1.multiply(cancelComm).multiply(new BigDecimal("0.01")).setScale(0, RoundingMode.DOWN);
            // cal2×0.10　(小数点以下を切捨て)
            BigDecimal cal3;
            cal3 = cal2.multiply(new BigDecimal("0.10")).setScale(0, RoundingMode.DOWN);
            // 売却可能（金額）=cal1-cal2-cal3
            depositInfo.setSellAbleAmount(cal1.subtract(cal2).subtract(cal3).toString());
        }

        // 概算評価金額
        depositInfo.setApproximateValuation(sum.multiply(fct024Res.getBasePrice())
                .divide(fct023Res.getBasePriceUnit(), 0, RoundingMode.DOWN).toString());
        // 概算評価金額日付
        depositInfo.setApproximateValuationDate(fct024Res.getBasePriceDate());

        return depositInfo;
    }
    /**
     * SQL003の処理
     *
     * @param fundCodeTimes ファンドコード（回数）
     * @param fundCodeIssues ファンドコード（号）
     * @param fundType ファンドタイプ
     * @return データありの場合レスポンス
     * @exception Exception 実行時例外
     */
    private DataList<IfaDomesticMutualFundOrderInputSql003ResponseModel> executeSql003(String fundCodeTimes, String fundCodeIssues, String fundType) throws Exception {
    	DataList<IfaDomesticMutualFundOrderInputSql003ResponseModel> fundInfoList = new DataList<IfaDomesticMutualFundOrderInputSql003ResponseModel>();
    	
    	DataList<IfaDomesticMutualFundOrderInputSql003ResponseModel> sql0003Res = dao
                .selectIfaDomesticMutualFundOrderInputSql003(new IfaDomesticMutualFundOrderInputSql003RequestModel(fundCodeTimes, fundCodeIssues, fundType));
        
    	if (sql0003Res != null && !CollectionUtils.isEmpty(sql0003Res.getDataList())) {
    		fundInfoList = sql0003Res;
        }
                
        return fundInfoList;
    }


    /**
     * フォーマット処理
     *
     * @param value 文字列
     * @param format フォーマット
     * @return フォーマット後文字列
     */
    private String formatNumber(String value, DecimalFormat format) {

        if (StringUtil.isNullOrEmpty(value)) {
            return "";
        }
        try {
            // 文字列を数値に変換してフォーマットする
            return format.format(new BigDecimal((value)));
        } catch (NumberFormatException e) {
            // 数値に変換できない場合は空文字列を返す
            return "";
        }
    }
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.bizcommon.util.OrderStatusUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA010BeforeCorrectPriceDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA010DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectInputA010DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticStockOrderCorrectInputService;
import com.sbisec.helios.ap.common.enums.ComplaCheckJudgmentResult;
import com.sbisec.helios.ap.common.enums.ComplaCheckKind;
import com.sbisec.helios.ap.common.enums.DomesticForeignType;
import com.sbisec.helios.ap.common.enums.DomesticStockTrade;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ExecuteMethod;
import com.sbisec.helios.ap.common.enums.JrIsaContractType;
import com.sbisec.helios.ap.common.enums.LimitMarketType;
import com.sbisec.helios.ap.common.enums.NationalityCode;
import com.sbisec.helios.ap.common.enums.OrderClass;
import com.sbisec.helios.ap.common.enums.SecurityType;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.EstimateStockModifyOrderAutoIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockModifyOrderAutoInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockModifyOrderAutoOutData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockModifyOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockModifyOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockModifyOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOrd;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;

import lombok.Data;

/**
 * 画面ID：SUB0202_0208-03
 * 画面名：国内株式注文訂正入力
 * 2023/01/09 新規作成
 *
 * @author 齋藤
 */
@Component(value = "cmpIfaDomesticStockOrderCorrectInputService")
public class IfaDomesticStockOrderCorrectInputServiceImpL implements IfaDomesticStockOrderCorrectInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticStockOrderCorrectInputServiceImpL.class);
    
    /**
     * FCT001
     */
    @Autowired
    private Fct001 fct001;
    
    /**
     * FCT003
     */
    @Autowired
    private Fct003 fct003;
    
    /**
     * FCT006
     */
    @Autowired
    private Fct006 fct006;
    
    /**
     * FCT021
     */
    @Autowired
    private Fct021 fct021;
    
    /**
     * FCT027
     */
    @Autowired
    private Fct027 fct027;
    
    /**
     * NRI_APIラッパー
     */
    @Autowired
    private ApiWrapper apiWrapper;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
    /**
     * 訂正/取消ボタン表示の判定クラス
     */
    @Autowired
    private OrderStatusUtil orderStatusUtil;
    
    // --------------------------------
    // 固定値
    // --------------------------------
    
    /**　対象取引　ドメインID*/
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引　エラーメッセージ表示区分値　2*/
    private static final String MSG_DISPLAY_TARGET_TRADE_ID = "2";
    
    /** FCT001:対象顧客参照権限有無*/
    private static final String TARGET_CUSTOMER_REF_AUTH_FLAG = "0";
    
    /** FCT001:取引停止フラグ*/
    private static final String TRADE_SUSPEND_FLAG = "1";
    
    /** FCT003:媒介可取引有無*/
    private static final String MEDIATE_AVLE_TRADE_FLAG = "1";
    
    /** FCT006: 取引種別 現物買付 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY = "1";
    
    /** FCT021：通貨コード　設定値 */
    private static final String CURRENCY_CODE_VALUE = "999";
    
    /** FCT021：国内株式 設定値 */
    private static final String DOMESTICSTOCK = "01";
    
    /** FCT021:注意情報アラートフラグ有り */
    private static final String NOTE_INFO_ALERT_FLAG = "1";
    
    /** FCT021:お知らせアラートフラグ有り */
    private static final String NOTE_LIMIT_ALERT_FLAG = "1";
    
    /** FCT021:注意情報エラーフラグ有り */
    private static final String NOTE_INFO_ERR_FLAG = "1";
    
    /** FCT021:お知らせエラーフラグ有り */
    private static final String NOTE_LIMIT_ERR_FLAG = "1";
    
    /** API004input:リクエスト区分 "2"受注番号指定*/
    private static final String EXEC_ORDER = "2";
    
    /** API004input:検索番号指定FROM ALL"△"*/
    private static final String REF_FROM = StringUtils.repeat(" ", 5);
    
    /** API004input:検索番号指定TO ALL"△"*/
    private static final String REF_TO = StringUtils.repeat(" ", 5);
    
    /** API004input:取引区分 '"1":現物信用注文明細（上場CW含まない）*/
    private static final String TORIHIKI_KBN = "1";
    
    /** API004input:約定取得区分 "1":約定明細取得*/
    private static final String TRADE_GET_KBN = "1";
    
    /** API004input:銘柄コード ""*/
    private static final String BRAND_CD = "";
    
    /** API004input:取得口座区分 "△":通常口座およびJrNISA口座の第一口座*/
    private static final String ACCOUNT_GET_KBN_NORMAL = "2";
    
    /** API004input:取得口座区分 "2"：JrNISA口座(第一、第二口座両方)*/
    private static final String ACCOUNT_GET_KBN_JRNISA = " ";
    
    /** API004:売買区分　買付 */
    private static final String BUY_SELL_TYPE_BUY = "K";
    
    /** API004:売買区分　売却 */
    private static final String BUY_SELL_TYPE_SELL = "U";
    
    /** API004:自動注文種別 通常注文/逆指注文/OCO注文 */
    private static final String AUTO_ORDER_KIND_NORMAL = "    ";
    
    /**　API004:自動注文種別 IFD親注文　*/
    private static final String AUTO_ORDER_KIND_PARENT = "IF  ";
    
    /**　API004:自動注文種別 IFD子注文　*/
    private static final String AUTO_ORDER_KIND_CHILED = "DONE";
    
    /** API004:RBE注文ステータス 発火済 */
    private static final String RBE_ORDER_STATUS_IGNITION = "1";
    
    /** API:RBE注文種別 通常注文*/
    private static final String RBE_ORDER_KIND_NORMAL = "   ";
    
    /** API:RBE注文種別 逆指値注文*/
    private static final String RBE_ORDER_KIND_SLO = "SLO";
    
    /** API:RBE注文種別 OCO注文*/
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** API:DONE RBE注文種別 通常注文*/
    private static final String DONE_RBE_ORDER_KIND_NORMAL = "   ";
    
    /** API:DONE RBE注文種別 逆指値注文*/
    private static final String DONE_RBE_ORDER_KIND_SLO = "SLO";
    
    /** API:DONE RBE注文種別 OCO注文*/
    private static final String DONE_RBE_ORDER_KIND_OCO = "OCO";
    
    /** API:通常/逆指値.執行方法 逆指値 */
    private static final String SASINARI_HOUHOU_SLO = "3";
    
    /** API:トリガ発動ゾーン　以上*/
    private static final String TRIGGER_ZONE_OVER = "0";
    
    /** API:トリガ発動ゾーン　以下*/
    private static final String TRIGGER_ZONE_UNDER = "1";
    
    /** API:トリガ発動ゾーン　指定無し*/
    private static final String TRIGGER_ZONE_NONE = " ";
    
    /** A001:発火状態 発火前*/
    private static final String WORKING_STATUS_IGNITION_BEFORE = "false";
    
    /** A001:発火状態 発火後*/
    private static final String WORKING_STATUS_IGNITION_AFTER = "true";
    
    /**FCT027:規制銘柄区分 規制銘柄*/
    private static final String REG_KBN_TRUE = "1";
    
    /**　データの有無 */
    private static final int DATA_ZERO = 0;
    
    /** 選択市場：当社優先市場／SOR */
    private static final String SELECT_MARKET_SOR = "A";
    
    //API001・API002リクエスト設定値(固定値)
    
    /** API001・API002 リクエスト（アカウントID）：ALL"0" */
    private static final String API_REQUEST_ORDER_ID_ACCOUNT_ID = StringUtils.repeat("0", 11);
    
    /** API001・API002　リクエスト（アカウント毎の連番）:ALL"0"*/
    private static final String API_REQUEST_ORDER_ID_NUMBER = StringUtils.repeat("0", 7);
    
    /** API001・API002　リクエスト（オリジン）:"0"*/
    private static final String API_REQUEST_ORDER_ID_ORIGIN = "0";
    
    /** API001・API002　リクエスト（商品区分）:"S"*/
    private static final String API_REQUEST_ORDER_TYPE = "S";
    
    /** API001・API002　リクエスト（訂正区分）:"PRICE△△△"*/
    private static final String API_REQUEST_MODIFY_TYPE = "PRICE" + String.format("%3s", StringUtil.EMPTY_STRING);
    
    /** API001・API002　リクエスト(指値）:"0000000000"*/
    private static final String API_REQUEST_PRICE = StringUtils.repeat("0", 10);
    
    /** API001・API002　リクエスト（受付経路区分）:"0"：支店*/
    private static final String API_REQUEST_CALLCENTER_KBN = "0";
    
    /** API001・API002　リクエスト（余力チェック区分）:'"△"：建玉余力または決済可能数量のチェック要*/
    private static final String API_REQUEST_CHECK_ID = " ";
    
    /** API001・API002　リクエスト(トリガ値段）:"0000000000"*/
    private static final String API_TRIGGER_PRICE = StringUtils.repeat("0", 10);
    
    /** API001・API002 リクエスト(OCO指値区分):" "*/
    private static final String API_OCO_SASINARI_KBN_NONE = " ";
    
    /** API001・API002 リクエスト(OCO指値): ALL"△" */
    private static final String API_OCO_PRICE_NONE = StringUtils.repeat(" ", 10);
    
    /** API001・API002　リクエスト（媒介）:"1"媒介注文*/
    private static final String API_INTERMEDIARY = "1";
    
    /** API001・API002　リクエスト（IPアドレス）:"ifap"*/
    private static final String API_REQUEST_IP_ADDRESS = StringUtils.rightPad("ifap", 39, " ");
    
    /** API001 リクエスト（訂正SOR注文区分）：訂正SOR"1" */
    private static final String API001_SOR_MODIFY_CODE_CORRECT_SOR = "1";
    
    /** API001 リクエスト（訂正SOR注文区分）：通常訂正" " */
    private static final String API001_SOR_MODIFY_CODE_NORMAL = " ";
    
    /** API002 リクエスト(自動注文種別):"IF△△"*/
    private static final String API002_AUTO_ORDER_KIND = "IF  ";
    
    /** API002 リクエスト(DONE指値（訂正値段）):"0000000000"*/
    private static final String API002_DONE_PRICE_EX = StringUtils.repeat("0", 10);
    
    /** API002 リクエスト(DONEトリガ値段（訂正値段）):"0000000000"*/
    private static final String API002_DONE_TRIGGER_PRICE = StringUtils.repeat("0", 10);
    
    /** API002 リクエスト(DONEOCO指成区分):"△"*/
    private static final String API002_DONE_OCO_SASINARI_KBN = " ";
    
    /** API002 リクエスト(DONEOCO指値（訂正値段）):"0000000000"*/
    private static final String API002_DONE_OCO_PRICE = StringUtils.repeat("0", 10);
    
    /** 半角スペース */
    private static final String SPACE = " ";
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 注文内容に変更がないため、注文訂正を行いません。 */
    private static final String ERRORS_CMN_ORDERMODIFY_NOMODIFY = "errors.cmn.orderModify.noModify";
    
    /** 指定した注文が見つかりません。 */
    private static final String ERRORS_CMN_ORDER_NOTFOUND = "errors.cmn.order.notFound";
    
    /** 指定した注文は訂正できません。 */
    private static final String ERRORS_CMN_ORDERMODIFY_OUTOFSERVICE = "errors.cmn.orderModify.outOfService";
    
    /** 入力した部店口座は存在しません。 */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** {0}ができないコースです。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。 */
    private static final String ERRORS_CMN_NOTICEERRORCHECK = "errors.cmn.noticeErrorCheck";
    
    /** 当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。 */
    private static final String ERRORS_INFORMATIONCHECK = "errors.informationCheck";
    
    /** 当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。 */
    private static final String WARNINGS_CMN_NOTICEWARNINGCHECK = "warnings.cmn.noticeWarningCheck";
    
    /** 未確認の重要なお知らせがあります。注意情報欄を確認してください。 */
    private static final String WARNINGS_CMM_INFORMATIONCHECK = "warnings.cmm.informationCheck";
    
    /** 注文銘柄に対する取引注意情報があります。取引注意情報の内容を顧客へ説明後に注文を執行してください。 */
    private static final String WARNINGS_DMS_INFORMATIONCHECK = "warnings.dms.informationCheck";
    
    /** 注文訂正処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) */
    private static final String ERRORS_CMN_ORDEREXECUTIONMODIFY_FAILED = "errors.cmn.orderExecutionModify.failed";
    
    /** CCSIDが未登録のためご利用できません。 */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** 市場訂正以外の注文内容に変更がないため、注文訂正を行いません。 */
    private static final String ERRORS_CMN_ORDERMODIFY_MARKETONLYMODIFY = "errors.cmn.orderModify.marketOnlyModify";
    
    /** SOR注文として受け付けられません。市場訂正をやり直してください。 */
    private static final String ERRORS_CMN_SORMODIFY_NOMODIFY = "errors.cmn.sorModify.noModify";

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticStockOrderCorrectInputA001DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectInputA001DtoResponse
     *
     * @param dtoReq リクエストボディ
     * @return 画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaDomesticStockOrderCorrectInputA001DtoResponse> initializeA001(
            IfaDomesticStockOrderCorrectInputA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderCorrectInputServiceImplL.initializeA001");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // FCT001
        DataList<IfaDomesticStockOrderCorrectInputA001DtoResponse> dtoResFct001 = checkFct001(cc.getButenCode(),
                cc.getAccountNumber());
        if (dtoResFct001 != null) {
            return dtoResFct001;
        }
        
        //API004の呼び出し処理および訂正ボタン表示判定の算出
        QueryStockUniteOrderPointOrd api004OutData = new QueryStockUniteOrderPointOrd();
        api004OutData = callApi004(dtoReq.getEcOrderNo(), cc, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        DataList<IfaDomesticStockOrderCorrectInputA001DtoResponse> dtoResApi004 = checkApi004Res(api004OutData);
        if (dtoResApi004 != null) {
            return dtoResApi004;
        }
        
        //取引種別の算出
        String tradeCd = null;
        if (StringUtils.equals(api004OutData.getBuySell(), BUY_SELL_TYPE_BUY)) {
            tradeCd = DomesticStockTrade.STOCK_BUY.getId();
        } else if (StringUtils.equals(api004OutData.getBuySell(), BUY_SELL_TYPE_SELL)) {
            tradeCd = DomesticStockTrade.STOCK_SELL.getId();
        }
        
        // FCT003
        DataList<IfaDomesticStockOrderCorrectInputA001DtoResponse> dtoResFct003 = checkFct003(cc.getButenCode(),
                cc.getAccountNumber(), tradeCd);
        if (dtoResFct003 != null) {
            return dtoResFct003;
        }
        
        // 買付余力の呼び出し（API003）
        QueryAccountBalanceOutData api003Res = null;
        api003Res = callApi003(cc.getButenCode(), cc.getAccountNumber());
        // エラーハンドリング
        apiErrorUtil.checkApiResponse(api003Res.getShubetu(), api003Res.getCode(), api003Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        // レスポンスの設定
        ResponseParam responseParam = getResponse(api003Res, api004OutData, tradeCd);
        IfaDomesticStockOrderCorrectInputA001DtoResponse innerData = new IfaDomesticStockOrderCorrectInputA001DtoResponse();
        
        BeanUtils.copyProperties(innerData, responseParam);
        
       // SOR取扱区分の設定
        OutputFct027Dto fct027Result = getDataFct027(api004OutData.getStockSecCode());

        innerData.setSorServiceKbn(fct027Result.getSorServiceKbn());
        
        return apiErrorUtil.createDataList(Arrays.asList(innerData), null);
    }
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaDomesticStockOrderCorrectInputA004DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectInputA004DtoResponse
     *
     * @param dtoReq リクエストボディ
     * @return 画面の更新に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaDomesticStockOrderCorrectInputA004DtoResponse> updateA004(
            IfaDomesticStockOrderCorrectInputA004DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderCorrectInputServiceImplL.updateA004");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        //API004の呼び出し処理および訂正ボタン表示判定の算出
        QueryStockUniteOrderPointOrd api004OutData = new QueryStockUniteOrderPointOrd();
        api004OutData = callApi004(dtoReq.getEcOrderNo(), cc, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        DataList<IfaDomesticStockOrderCorrectInputA004DtoResponse> dtoResApi004 = checkApi004Res(api004OutData);
        if (dtoResApi004 != null) {
            return dtoResApi004;
        }
        
        // SOR取扱区分の取得
        OutputFct027Dto fct027Result = getDataFct027(api004OutData.getStockSecCode());
        
        //取引種別の算出
        String tradeCd = null;
        if (StringUtils.equals(api004OutData.getBuySell(), BUY_SELL_TYPE_BUY)) {
            tradeCd = DomesticStockTrade.STOCK_BUY.getId();
        } else if (StringUtils.equals(api004OutData.getBuySell(), BUY_SELL_TYPE_SELL)) {
            tradeCd = DomesticStockTrade.STOCK_SELL.getId();
        }
        
        // 買付余力の呼び出し（API003）
        QueryAccountBalanceOutData api003Res = null;
        api003Res = callApi003(cc.getButenCode(), cc.getAccountNumber());
        // エラーハンドリング
        apiErrorUtil.checkApiResponse(api003Res.getShubetu(), api003Res.getCode(), api003Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        // レスポンスの設定
        ResponseParam responseParam = getResponse(api003Res, api004OutData, tradeCd);
        IfaDomesticStockOrderCorrectInputA004DtoResponse innerData = new IfaDomesticStockOrderCorrectInputA004DtoResponse();
        BeanUtils.copyProperties(innerData, responseParam);
        // SOR取扱区分の設定
        innerData.setSorServiceKbn(fct027Result.getSorServiceKbn());
        
        return apiErrorUtil.createDataList(Arrays.asList(innerData), null);
    }
    
    /**
     * アクションID：A010
     * アクション名：注文確認
     * Dto リクエスト：IfaDomesticStockOrderCorrectInputA010DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectInputA010DtoResponse
     *
     * @param dtoReq リクエストボディ
     * @return 国内株式注文訂正確認画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaDomesticStockOrderCorrectInputA010DtoResponse> orderConfirmA010(
            IfaDomesticStockOrderCorrectInputA010DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderCorrectInputServiceImplL.orderConfirmA010");
        }
        
        IfaDomesticStockOrderCorrectInputA010DtoResponse response = new IfaDomesticStockOrderCorrectInputA010DtoResponse();
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        //リクエスト項目の値をレスポンスの同名項目にコピー
        BeanUtils.copyProperties(response, dtoReq);
        
        // FCT001
        DataList<IfaDomesticStockOrderCorrectInputA010DtoResponse> dtoResFct001 = checkFct001(cc.getButenCode(),
                cc.getAccountNumber());
        if (dtoResFct001 != null) {
            return dtoResFct001;
        }
        
        // FCT003
        DataList<IfaDomesticStockOrderCorrectInputA010DtoResponse> dtoResFct003 = checkFct003(cc.getButenCode(),
                cc.getAccountNumber(), dtoReq.getTradeCd());
        if (dtoResFct003 != null) {
            return dtoResFct003;
        }
        
        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
            ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_CCSID_UNREGISTERED));
        }

        // FCT021
        OutputFct021Dto fct021Result = callFct021(cc.getButenCode(), cc.getAccountNumber(), dtoReq.getTradeCd(),
                dtoReq.getMarket());
        
        // 注意情報エラー有無="1"（あり）：エラーを返す。
        if (StringUtils.equals(fct021Result.getNoteInfoErrFlag(), NOTE_INFO_ERR_FLAG)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_CMN_NOTICEERRORCHECK,
                    IfaCommonUtil.getMessage(ERRORS_CMN_NOTICEERRORCHECK, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_ID) }));
        }
        
        // お知らせエラー有無="1"（あり）：エラーを返す。
        if (StringUtils.equals(fct021Result.getNoteLimitErrFlag(), NOTE_LIMIT_ERR_FLAG)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_INFORMATIONCHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATIONCHECK, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_ID) }));
        }
        
        // 注意情報アラート有無="1"（あり）：注意情報アラートを格納する。
        if (StringUtils.equals(fct021Result.getNoteInfoAlertFlag(), NOTE_INFO_ALERT_FLAG)) {
            response.setNoticeInfoAlert(IfaCommonUtil.getMessage(WARNINGS_CMN_NOTICEWARNINGCHECK,
                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_ID) }));
        }
        
        // お知らせアラート有無="1"（あり）：お知らせアラートを格納する。
        if (StringUtils.equals(fct021Result.getNoteLimitAlertFlag(), NOTE_LIMIT_ALERT_FLAG)) {
            response.setNoticeAlert(IfaCommonUtil.getMessage(WARNINGS_CMM_INFORMATIONCHECK,
                    new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_ID) }));
        }
        
        // FCT006(取引種別が現物買付の場合)
        if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SPOT_BUY)) {
            OutputFct006Dto fct006Result = callFct006(cc.getButenCode(), cc.getAccountNumber(), dtoReq.getBrandCode(),
                    dtoReq.getKanyuKbn(), dtoReq.getReceiveOrderType());
            
            ComplaCheckJudgmentResult result = ComplaCheckJudgmentResult.valueOfId(fct006Result.getJudgementResult());
            
            Boolean judgementResultErrorFlag = false;
            if (result == null) {
                judgementResultErrorFlag = true;
            } else {
                switch (result) {
                case NORMAL:
                    break;
                
                case ALERT:
                    // コンプラランクチェック.メッセージ
                    response.setMessageId(IfaCommonUtil.getMessage(fct006Result.getMessageId()));
                    // コンプラランクチェック.チェックボックス文言
                    response.setChkBoxLabel(fct006Result.getChkBoxLabel());
                    // コンプラランクチェック.コンプラチェック用資金性格
                    response.setFundComplianceCheck(fct006Result.getComplaCheckFundCharacter());
                    break;
                
                case ERROR:
                default:
                    judgementResultErrorFlag = true;
                }
            }
            
            // FCT006.判定結果がエラー
            if (judgementResultErrorFlag) {
                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, fct006Result.getMessageId(),
                        IfaCommonUtil.getMessage(fct006Result.getMessageId()));
            }
        }
        
        // FCT027
        OutputFct027Dto fct027Result = getDataFct027(dtoReq.getBrandCode());
        if (fct027Result != null) {
            if (StringUtils.equals(fct027Result.getRegKbn(), REG_KBN_TRUE)) {
                response.setRegKbn(IfaCommonUtil.getMessage(WARNINGS_DMS_INFORMATIONCHECK));
            }
            response.setBrandName(fct027Result.getBrandName());
        }
        
        // 訂正有無チェック
        DataList<IfaDomesticStockOrderCorrectInputA010DtoResponse> dtoResCorrectExest = correctExestCheck(dtoReq);
        if (dtoResCorrectExest != null) {
            return dtoResCorrectExest;
        }
        
        //注文種別に応じて注文確認
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            // API001
            // 国内株式・信用訂正注文確認
            EstimateStockModifyOrderOutData api001Res = callApi001(dtoReq, cc);
            // エラーハンドリング
            if (apiErrorUtil.isError(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage())) {
                return apiErrorUtil.createDataList(new ArrayList<>(), ERRORS_CMN_ORDEREXECUTIONMODIFY_FAILED);
            }

            // 市場.訂正後市場=当社優先市場／SOR　かつ　訂正SOR注文結果区分="△"：SOR対象外の場合、エラーとする。
            if (SELECT_MARKET_SOR.equals(dtoReq.getAfterCorrectMarket()) && SPACE.equals(api001Res.getSorModifyStatus())) {
                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                                        ERRORS_CMN_SORMODIFY_NOMODIFY,
                                        IfaCommonUtil.getMessage(ERRORS_CMN_SORMODIFY_NOMODIFY));
            }
            
            // レスポンス設定
            setResponseDtoA010(response, fct027Result.getBrandName(), api001Res.getShubetu(), api001Res.getCode(),
                    api001Res.getMessage(), api001Res.getJrnisaTransferAmount(), api001Res.getEstimatePrice(),
                    api001Res.getIsaBuyLimit(), api001Res.getTradeDate(), api001Res.getSettlementDate(),
                    api001Res.getAcceptDate() + SPACE + api001Res.getAcceptTime(), api001Res.getAcBalanceAfter(),
                    api001Res.getModLimitFlg(), api001Res.getModLimit(), api001Res.getSorModifyStatus());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            // API002
            // 自動注文(国内株式)訂正注文確認
            EstimateStockModifyOrderAutoOutData api002Res = callApi002(dtoReq, cc);
            // エラーハンドリング
            if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
                return apiErrorUtil.createDataList(new ArrayList<>(), ERRORS_CMN_ORDEREXECUTIONMODIFY_FAILED);
            }
            
            // レスポンス設定
            setResponseDtoA010(response, fct027Result.getBrandName(), api002Res.getShubetu(), api002Res.getCode(),
                    api002Res.getMessage(), api002Res.getJrnisaTransferAmount(), api002Res.getEstimatePrice(),
                    api002Res.getIsaBuyLimit(), api002Res.getTradeDate(), api002Res.getSettlementDate(),
                    api002Res.getAcceptDate() + SPACE + api002Res.getAcceptTime(), api002Res.getAcBalanceAfter(),
                    api002Res.getModLimitFlg(), api002Res.getModLimit(), null);
        }
        List<IfaDomesticStockOrderCorrectInputA010DtoResponse> resDtoList = new ArrayList<>();
        resDtoList.add(response);
        DataList<IfaDomesticStockOrderCorrectInputA010DtoResponse> dtoRes = IfaCommonUtil.createDataList(resDtoList,
                ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), StringUtils.EMPTY);
        
        return dtoRes;
    }
    
    /**
     * API004の処理結果および訂正ボタン表示判定の確認取得
     *
     * @param ecOrderNo EC受注番号
     * @param cc 顧客共通情報
     * @return tradeCd(取引種別)
     */
    private <T> DataList<T> checkApi004Res(QueryStockUniteOrderPointOrd api004Res) throws Exception {
        
        // 取得件数0件の場合はエラー
        if (api004Res == null) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_NOTFOUND));
        }
        
        // 訂正ボタン表示の場合は正常
        if (orderStatusUtil.canCorrectOrder(api004Res)) {
            return null;
        }
        
        // 訂正ボタン非表示の場合はエラー
        return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_CMN_ORDERMODIFY_OUTOFSERVICE,
                IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_OUTOFSERVICE));
    }
    
    /**
     * A001、A004のレスポンスの格納
     *
     * @param api003Res API003の結果
     * @param api004Res API004の結果
     * @param tradeCd 取引種別
     * @return resDataList
     */
    private ResponseParam getResponse(QueryAccountBalanceOutData api003Res, QueryStockUniteOrderPointOrd api004OutData,
            String tradeCd) {
        
        ResponseParam response = new ResponseParam();
        
        //　API003の出力項目
        // 受渡日(T+2)
        response.setSettlementDateAfterBusinessDayT2(api003Res.getT2().getSettlementDateT());
        // 総合口座（T+2）
        response.setYenBuyingPowerGeneralAccountT2(api003Res.getT2().getBuyingPowerTotal().trim());
        // JrNISA口座（T+2）
        response.setYenBuyingPowerJrNisaT2(api003Res.getT2Jr().getBuyingPowerTotalJrnisa().trim());
        // 受渡日(T+3)
        response.setSettlementDateAfterBusinessDayT3(api003Res.getT3().getSettlementDateT());
        // 総合口座（T+3）
        response.setYenBuyingPowerGeneralAccountT3(api003Res.getT3().getBuyingPowerTotal().trim());
        // JrNISA口座（T+3）
        response.setYenBuyingPowerJrNisaT3(api003Res.getT3Jr().getBuyingPowerTotalJrnisa().trim());
        // NISA買付可能枠
        response.setNisaInvestableQuotaMessage(api003Res.getIsaSeityoBuyLimit().trim());
        // 銘柄コード（半角英数字）
        response.setBrandCode(api004OutData.getStockSecCode());
        // 銘柄名（全角半角）
        response.setBrandName(api004OutData.getSecName().trim());
        // 市場（全角）
        response.setMarket(api004OutData.getMarketId());
        // 取引種別　(算出結果)
        response.setTradeCd(tradeCd);
        // 注文数量
        response.setOrderQuantity(api004OutData.getQuantity().trim());
        // 未約定数量（数字）
        response.setUnTradeQuantity(api004OutData.getExecLeftQuantity().trim());
        // 期間
        response.setPeriod(api004OutData.getLimit());
        // 預り区分
        response.setDepositType(api004OutData.getHitokuteiTradeKbn());
        // 注文種別 (算出結果)
        String orderKind = getOrderKind(api004OutData);
        response.setOrderKind(orderKind);
        
        //注文種別=通常/逆指値の場合のみセットする項目
        if (StringUtils.equals(orderKind, OrderClass.NORMAL.getId())) {
            //通常/逆指値.執行方法
            response.setSasinariHouhou(getSasinarihouho(api004OutData));
            //通常/逆指値.執行条件
            response.setSasinariJyouken(api004OutData.getSasinariId());
            //通常/逆指値.発火条件価格（逆指値）
            response.setTriggerPrice(api004OutData.getLatestTriggerPrice().trim());
            //通常/逆指値.発火条件価格（逆指値）ゾーン
            response.setStopOrderPriceText2(api004OutData.getLatestTriggerZone());
            //通常/逆指値.執行方法（逆指値）
            response.setGyakusasiHouhou(getGyakusasiHouhou(api004OutData));
            //通常/逆指値.注文単価
            response.setPrice(api004OutData.getLimitPrice().trim());
        }
        
        //注文種別=OCOの場合
        if (StringUtils.equals(orderKind, OrderClass.OCO.getId())) {
            //OCO1.執行方法
            response.setOco1SasinariHouhou(ExecuteMethod.LIMIT.getId());
            //OCO1.執行条件
            response.setOco1SasinariJyouken(api004OutData.getSasinariId());
            //OCO1.注文単価
            response.setOco1Price(api004OutData.getLimitPrice().trim());
            //OCO2.発火条件価格（逆指値）
            response.setOco2TriggerPrice(api004OutData.getLatestTriggerPrice().trim());
            //OCO2.発火条件価格（逆指値）ゾーン
            response.setOco2StopOrderPriceText2(api004OutData.getLatestTriggerZone());
            //OCO2.執行方法（逆指値）
            response.setOco2GyakusasiHouhou(
                    getOco2GyakusasiHouhou(api004OutData, api004OutData.getLatestOcoSasinariId()));
            //OCO2.執行条件（逆指値）
            response.setOco2GyakusasiJyouken(api004OutData.getLatestOcoSasinariId());
            //OCO2.注文単価
            response.setOco2Price(api004OutData.getLatestOcoPrice().trim());
            
        } else if (StringUtils.equals(orderKind, OrderClass.IFDOCO.getId())) { //注文種別=IFDOCOの場合
            //OCO1.執行方法
            response.setOco1SasinariHouhou(ExecuteMethod.LIMIT.getId());
            //OCO1.執行条件
            response.setOco1SasinariJyouken(api004OutData.getDoneSasinariId());
            //OCO1.注文単価
            response.setOco1Price(api004OutData.getDonePrice().trim());
            //OCO2.発火条件価格（逆指値）
            response.setOco2TriggerPrice(api004OutData.getDoneTriggerPrice().trim());
            //OCO2.発火条件価格（逆指値）ゾーン
            response.setOco2StopOrderPriceText2(api004OutData.getDoneTriggerZone());
            //OCO2.執行方法（逆指値）
            response.setOco2GyakusasiHouhou(
                    getOco2GyakusasiHouhou(api004OutData, api004OutData.getDoneOcoSasinariId()));
            //OCO2.執行条件（逆指値）
            response.setOco2GyakusasiJyouken(api004OutData.getDoneOcoSasinariId());
            //OCO2.注文単価
            response.setOco2Price(api004OutData.getDoneOcoPrice().trim());
        }
        //注文種別=IFD　または　IFDOCOの場合のみセットする項目
        if (StringUtils.equals(orderKind, OrderClass.IFD.getId())
                || StringUtils.equals(orderKind, OrderClass.IFDOCO.getId())) {
            //IFD1.執行方法
            response.setIfd1SasinariHouhou(getSasinarihouho(api004OutData));
            //IFD1.執行条件
            response.setIfd1SasinariJyouken(api004OutData.getSasinariId());
            //IFD1.発火条件価格（逆指値）
            response.setIfd1TriggerPrice(api004OutData.getLatestTriggerPrice().trim());
            //IFD1.発火条件価格（逆指値）ゾーン
            response.setIfd1StopOrderPriceText2(api004OutData.getLatestTriggerZone());
            //IFD1.執行方法（逆指値）
            response.setIfd1GyakusasiHouhou(getGyakusasiHouhou(api004OutData));
            //IFD1.注文単価
            response.setIfd1Price(api004OutData.getLimitPrice().trim());
            //IFD2.期間.日付
            response.setIfd2Limit(api004OutData.getDoneLimit());
        }
        //注文種別=IFDの場合のみセットする項目
        if (StringUtils.equals(orderKind, OrderClass.IFD.getId())) {
            //IFD2.執行方法
            response.setIfd2SasinariHouhou(getIfd2SasinariHouhou(api004OutData));
            //IFD2.執行条件
            response.setIfd2SasinariJyouken(api004OutData.getDoneSasinariId());
            //IFD2.発火条件価格（逆指値）
            response.setIfd2TriggerPrice(api004OutData.getDoneTriggerPrice().trim());
            //IFD2.発火条件価格（逆指値）ゾーン
            response.setIfd2StopOrderPriceText2(api004OutData.getDoneTriggerZone());
            //IFD2.執行方法（逆指値）
            response.setIfd2GyakusasiHouhou(getIfd2GyakusasiHouhou(api004OutData));
            //IFD2.注文単価
            response.setIfd2Price(api004OutData.getDonePrice().trim());
        }
        // 発火状態
        if (StringUtils.equals(api004OutData.getRbeOrderStatus(), RBE_ORDER_STATUS_IGNITION)) {
            response.setWorkingStatus(WORKING_STATUS_IGNITION_AFTER);
        } else {
            response.setWorkingStatus(WORKING_STATUS_IGNITION_BEFORE);
        }
        
        // DONE状態
        response.setDoneState(api004OutData.getAutoOrderKind());
        //RBE注文ステータス
        response.setRbeOrderStatus(api004OutData.getRbeOrderStatus());
        //手数料区分
        response.setTesuuryouKbn(api004OutData.getComId());
        // 受注日
        response.setOrderDate(StringUtils.substring(api004OutData.getInputDate(), 0, 8));
        // 約定ステータス
        response.setTradeStatus(api004OutData.getExecStatus());
        // 直近市場
        response.setLatestMarketId(api004OutData.getLatestMarketId());
        // SOR注文区分
        response.setSorOrderClassification(api004OutData.getSorKbn());

        return response;
    }
    
    /**
     * API010の訂正有無チェック
     *
     * @param dtoReq DTOリクエスト
     * @param cc 顧客共通情報
     * @return 正常：null、異常：DataList
     */
    private <T> DataList<T> correctExestCheck(IfaDomesticStockOrderCorrectInputA010DtoRequest dtoReq) {
        
        OrderClass orderClass = OrderClass.valueOfId(dtoReq.getOrderKind());
        if (orderClass != null) {
            IfaDomesticStockOrderCorrectInputA010BeforeCorrectPriceDtoRequest bbPrice = dtoReq.getBeforeCorrectPrice()
                    .get(0);
            if (bbPrice != null) {
                List<String> beforeData = new ArrayList<String>();
                List<String> afterData = new ArrayList<String>();
                switch (orderClass) {
                    case NORMAL:
                        //訂正後データ
                        afterData = new ArrayList<String>(List.of(dtoReq.getSasinariHouhou()));
                        //訂正前データ
                        beforeData = new ArrayList<String>(List.of(bbPrice.getSasinariHouhou()));
                        
                        // 訂正前の執行方法が逆指値の場合
                        if (ExecuteMethod.STOP.getId().equals(bbPrice.getSasinariHouhou())) {
                            afterData.add(dtoReq.getTriggerPrice());
                            afterData.add(dtoReq.getGyakusasiHouhou());
                            beforeData.add(bbPrice.getTriggerPrice());
                            beforeData.add(bbPrice.getGyakusasiHouhou());
                        }
                        
                        // 訂正前の執行方法が指値　または　執行方法（逆指値）が指値の場合
                        if (ExecuteMethod.LIMIT.getId().equals(bbPrice.getSasinariHouhou()) 
                                || ExecuteMethod.LIMIT.getId().equals(bbPrice.getGyakusasiHouhou())) {
                            afterData.add(dtoReq.getPrice());
                            beforeData.add(bbPrice.getPrice());
                        }
                        
                        //データを比較し、完全一致したらエラー
                        if (beforeData.equals(afterData)) {
                            // 市場訂正が行われていない場合、「注文内容に変更がないため、注文訂正を行いません。」のエラーメッセージを表示する
                            if (StringUtil.isNullOrEmpty(dtoReq.getAfterCorrectMarket()) || dtoReq.getAfterCorrectMarket().equals(dtoReq.getMarket())) {
                                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                                        ERRORS_CMN_ORDERMODIFY_NOMODIFY,
                                        IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
                            // 市場訂正が行われている場合、「市場訂正以外の注文内容に変更がないため、注文訂正を行いません。」のエラーメッセージを表示する
                            } else {
                                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                                        ERRORS_CMN_ORDERMODIFY_MARKETONLYMODIFY,
                                        IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_MARKETONLYMODIFY));
                            }
                        }
                        break;
                    case OCO:
                        //訂正後データ
                        afterData = new ArrayList<String>(List.of(dtoReq.getOco1Price(), 
                                dtoReq.getOco2TriggerPrice(), dtoReq.getOco2GyakusasiHouhou()));
                        //訂正前データ
                        beforeData = new ArrayList<String>(List.of(bbPrice.getOco1Price(), 
                                bbPrice.getOco2TriggerPrice(), bbPrice.getOco2GyakusasiHouhou()));
                        
                        // 訂正前のOCO2.執行方法（逆指値）が指値の場合
                        if (ExecuteMethod.LIMIT.getId().equals(bbPrice.getOco2GyakusasiHouhou())) {
                            afterData.add(dtoReq.getOco2Price());
                            beforeData.add(bbPrice.getOco2Price());
                        }
                        
                        //データを比較し、完全一致したらエラー
                        if (beforeData.equals(afterData)) {
                            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                                    ERRORS_CMN_ORDERMODIFY_NOMODIFY,
                                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
                        }
                        break;
                    case IFD:
                        //訂正後データ
                        afterData = new ArrayList<String>(List.of(dtoReq.getIfd1SasinariHouhou()));
                        //訂正前データ
                        beforeData = new ArrayList<String>(List.of(bbPrice.getIfd1SasinariHouhou()));
                        
                        // 訂正前のIFD1.執行方法が逆指値の場合
                        if (ExecuteMethod.STOP.getId().equals(bbPrice.getIfd1SasinariHouhou())) {
                            afterData.add(dtoReq.getIfd1TriggerPrice());
                            afterData.add(dtoReq.getIfd1GyakusasiHouhou());
                            beforeData.add(bbPrice.getIfd1TriggerPrice());
                            beforeData.add(bbPrice.getIfd1GyakusasiHouhou());
                        }
                        
                        // 訂正前のIFD1.執行方法が指値　または　IFD1.執行方法（逆指値）が指値の場合
                        if (ExecuteMethod.LIMIT.getId().equals(bbPrice.getIfd1SasinariHouhou()) 
                                || ExecuteMethod.LIMIT.getId().equals(bbPrice.getIfd1GyakusasiHouhou())) {
                            afterData.add(dtoReq.getIfd1Price());
                            beforeData.add(bbPrice.getIfd1Price());
                        }
                        
                        // 訂正前のIFD2.執行方法が逆指値の場合
                        if (ExecuteMethod.STOP.getId().equals(bbPrice.getIfd2SasinariHouhou())) {
                            afterData.add(dtoReq.getIfd2TriggerPrice());
                            afterData.add(dtoReq.getIfd2GyakusasiHouhou());
                            beforeData.add(bbPrice.getIfd2TriggerPrice());
                            beforeData.add(bbPrice.getIfd2GyakusasiHouhou());
                        }
                        
                        // 訂正前のIFD2.執行方法が指値　または　IFD2.執行方法（逆指値）が指値の場合
                        if (ExecuteMethod.LIMIT.getId().equals(bbPrice.getIfd2SasinariHouhou()) 
                                || ExecuteMethod.LIMIT.getId().equals(bbPrice.getIfd2GyakusasiHouhou())) {
                            afterData.add(dtoReq.getIfd2Price());
                            beforeData.add(bbPrice.getIfd2Price());
                        } 
                        
                        //データを比較し、完全一致したらエラー
                        if (beforeData.equals(afterData)) {
                            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                                    ERRORS_CMN_ORDERMODIFY_NOMODIFY,
                                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
                        }
                        break;
                    case IFDOCO:
                        //訂正後データ
                        afterData = new ArrayList<String>(List.of(dtoReq.getIfd1SasinariHouhou(),
                                dtoReq.getOco1Price(), dtoReq.getOco2TriggerPrice(), 
                                dtoReq.getOco2GyakusasiHouhou()));
                        //訂正前データ
                        beforeData = new ArrayList<String>(List.of(bbPrice.getIfd1SasinariHouhou(),
                                bbPrice.getOco1Price(), bbPrice.getOco2TriggerPrice(), 
                                bbPrice.getOco2GyakusasiHouhou()));
                        
                        // 訂正前のIFD1.執行方法が逆指値の場合
                        if (ExecuteMethod.STOP.getId().equals(bbPrice.getIfd1SasinariHouhou())) {
                            afterData.add(dtoReq.getIfd1TriggerPrice());
                            afterData.add(dtoReq.getIfd1GyakusasiHouhou());
                            beforeData.add(bbPrice.getIfd1TriggerPrice());
                            beforeData.add(bbPrice.getIfd1GyakusasiHouhou());
                        }
                        
                        // 訂正前のIFD1.執行方法が指値　または　IFD1.執行方法（逆指値）が指値の場合
                        if (ExecuteMethod.LIMIT.getId().equals(bbPrice.getIfd1SasinariHouhou()) 
                                || ExecuteMethod.LIMIT.getId().equals(bbPrice.getIfd1GyakusasiHouhou())) {
                            afterData.add(dtoReq.getIfd1Price());
                            beforeData.add(bbPrice.getIfd1Price());
                        }
                        
                        // 訂正前のOCO2.執行方法（逆指値）が指値の場合
                        if (ExecuteMethod.LIMIT.getId().equals(bbPrice.getOco2GyakusasiHouhou())) {
                            afterData.add(dtoReq.getOco2Price());
                            beforeData.add(bbPrice.getOco2Price());
                        }
                        
                        //データを比較し、完全一致したらエラー
                        if (beforeData.equals(afterData)) {
                            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                                    ERRORS_CMN_ORDERMODIFY_NOMODIFY,
                                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
                        }
                        break;
                    
                    default:
                        break;
                }
                //データを比較し、完全一致したらエラー
                if (beforeData.equals(afterData) && beforeData.size() > DATA_ZERO) {
                    return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                            ERRORS_CMN_ORDERMODIFY_NOMODIFY, IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
                }
            }
        }
        return null;
    }
    
    /**
     * API001呼び出し処理
     *
     * @param dtoReq DTOリクエスト
     * @param cc 顧客共通情報
     * @return API001の呼び出し結果
     */
    private EstimateStockModifyOrderOutData callApi001(IfaDomesticStockOrderCorrectInputA010DtoRequest dtoReq,
            CustomerCommon cc) throws Exception {
        
        EstimateStockModifyOrderOutData result = new EstimateStockModifyOrderOutData();
        EstimateStockModifyOrderInData inData = new EstimateStockModifyOrderInData();
        
        //ユーザー共通情報取得
        final UserAccount ua = IfaCommonUtil.getUserAccount();
        
        //inDataの設定
        //部店コード3桁
        inData.setButenCd(cc.getButenCode());
        //口座番号7桁
        inData.setKozaNo(StringUtils.leftPad(cc.getAccountNumber(), 7, "0"));
        //アカウントID
        inData.setAccountId(API_REQUEST_ORDER_ID_ACCOUNT_ID);
        //アカウント毎の連番
        inData.setNumber(API_REQUEST_ORDER_ID_NUMBER);
        //オリジン
        inData.setOrigin(API_REQUEST_ORDER_ID_ORIGIN);
        //商品区分
        inData.setOrderType(API_REQUEST_ORDER_TYPE);
        //訂正区分
        inData.setModifyType(API_REQUEST_MODIFY_TYPE);
        //EC受注番号
        inData.setOrderNo(StringUtils.leftPad(dtoReq.getEcOrderNo(), 6, "0"));
        //注文数量
        inData.setQuantity(StringUtils.leftPad(dtoReq.getQuantity(), 8, "0"));
        //指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            inData.setSasinariKbn(dtoReq.getSasinariJyouken());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            inData.setSasinariKbn(dtoReq.getOco1SasinariJyouken());
        }
        //指値
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            inData.setPrice(StringUtils.leftPad(dtoReq.getPrice(), 10, "0"));
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            inData.setPrice(StringUtils.leftPad(dtoReq.getOco1Price(), 10, "0"));
        } else {
            inData.setPrice(API_REQUEST_PRICE);
        }
        //受付経路区分
        inData.setCallcenterKbn(API_REQUEST_CALLCENTER_KBN);
        //ユーザーID
        inData.setUserId(ua.getCcsUserId());
        //手数料区分
        inData.setComId(dtoReq.getTesuuryouKbn());
        //余力チェック区分
        inData.setCheckId(API_REQUEST_CHECK_ID);
        //RBE注文種別
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.LIMIT.getId())
                    || StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.MARKET.getId())) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
            } else if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
            }
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_OCO);
        }
        //トリガ発動ゾーン
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_BEFORE)) {
            
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                    && StringUtils.equals(dtoReq.getSasinariHouhou(), SASINARI_HOUHOU_SLO)) {
                if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTrade.STOCK_BUY.getId())) {
                    inData.setTriggerZone(TRIGGER_ZONE_OVER);
                } else if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTrade.STOCK_SELL.getId())) {
                    inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                }
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
                if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTrade.STOCK_BUY.getId())) {
                    inData.setTriggerZone(TRIGGER_ZONE_OVER);
                } else if (StringUtils.equals(dtoReq.getTradeCd(), DomesticStockTrade.STOCK_SELL.getId())) {
                    inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                }
            } else {
                inData.setTriggerZone(TRIGGER_ZONE_NONE);
            }
        } else if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_AFTER)) {
            inData.setTriggerZone(TRIGGER_ZONE_NONE);
        }
        
        //トリガ値段
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_BEFORE)) {
            
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                    inData.setTriggerPrice(StringUtils.leftPad(dtoReq.getTriggerPrice(), 10, "0"));
                    
                } else {
                    inData.setTriggerPrice(API_TRIGGER_PRICE);
                }
                
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
                inData.setTriggerPrice(StringUtils.leftPad(dtoReq.getOco2TriggerPrice(), 10, "0"));
            } else {
                inData.setTriggerPrice(API_TRIGGER_PRICE);
            }
        } else if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_AFTER)) {
            inData.setTriggerPrice(API_TRIGGER_PRICE);
        }
        //OCO指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            inData.setOcoSasinariKbn(API_OCO_SASINARI_KBN_NONE);
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            inData.setOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
        }
        //OCO指値
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            inData.setOcoPrice(API_OCO_PRICE_NONE);
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            inData.setOcoPrice(StringUtils.leftPad(dtoReq.getOco2Price(), 10, "0"));
        }
        //媒介
        inData.setIntermediary(API_INTERMEDIARY);
        //IPアドレス
        inData.setIpAddress(API_REQUEST_IP_ADDRESS);
        
        // 訂正SOR注文区分
        // 市場が「当社優先市場／SOR」に変更された場合
        if (SELECT_MARKET_SOR.equals(dtoReq.getAfterCorrectMarket())) {
            // "1"：訂正SOR
            inData.setSorModifyCode(API001_SOR_MODIFY_CODE_CORRECT_SOR);
        // 上記以外
        } else {
            // "△"：通常訂正
            inData.setSorModifyCode(API001_SOR_MODIFY_CODE_NORMAL);
        }
        
        EstimateStockModifyOrderIn input = new EstimateStockModifyOrderIn();
        input.setIndata(inData);
        
        // 自動注文(国内株式現物・信用)新規注文確認情報を取得する。
        result = apiWrapper.estimateStockModifyOrder(input);
        
        return result;
        
    }
    
    /**
     * API002呼び出し処理
     *
     * @param dtoReq DTOリクエスト
     * @param cc 顧客共通情報
     * @return API002の呼び出し結果
     */
    private EstimateStockModifyOrderAutoOutData callApi002(IfaDomesticStockOrderCorrectInputA010DtoRequest dtoReq,
            CustomerCommon cc) throws Exception {
        
        EstimateStockModifyOrderAutoOutData result = new EstimateStockModifyOrderAutoOutData();
        EstimateStockModifyOrderAutoInData inData = new EstimateStockModifyOrderAutoInData();
        
        //ユーザー共通情報取得
        final UserAccount ua = IfaCommonUtil.getUserAccount();
        
        //inDataの設定
        //部店コード3桁
        inData.setButenCd(cc.getButenCode());
        //口座番号7桁
        inData.setKozaNo(StringUtils.leftPad(cc.getAccountNumber(), 7, "0"));
        //アカウントID
        inData.setAccountId(API_REQUEST_ORDER_ID_ACCOUNT_ID);
        //アカウント毎の連番
        inData.setNumber(API_REQUEST_ORDER_ID_NUMBER);
        //オリジン
        inData.setOrigin(API_REQUEST_ORDER_ID_ORIGIN);
        //商品区分
        inData.setOrderType(API_REQUEST_ORDER_TYPE);
        //訂正区分
        inData.setModifyType(API_REQUEST_MODIFY_TYPE);
        //EC受注番号
        inData.setOrderNo(StringUtils.leftPad(dtoReq.getEcOrderNo(), 6, "0"));
        //注文数量
        inData.setQuantity(StringUtils.leftPad(dtoReq.getQuantity(), 8, "0"));
        //指成区分
        inData.setSasinariKbn(dtoReq.getIfd1SasinariJyouken());
        //指値
        if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), ExecuteMethod.LIMIT.getId())) {
            inData.setPrice(StringUtils.leftPad(dtoReq.getIfd1Price(), 10, "0"));
        } else if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), ExecuteMethod.STOP.getId())) {
            if (StringUtils.equals(dtoReq.getIfd1GyakusasiHouhou(), ExecuteMethod.LIMIT.getId())) {
                inData.setPrice(StringUtils.leftPad(dtoReq.getIfd1Price(), 10, "0"));
            } else {
                inData.setPrice(API_REQUEST_PRICE);
            }
        } else {
            inData.setPrice(API_REQUEST_PRICE);
        }
        //受付経路区分
        inData.setCallcenterKbn(API_REQUEST_CALLCENTER_KBN);
        //ユーザーID
        inData.setUserId(ua.getCcsUserId());
        //手数料区分
        inData.setComId(dtoReq.getTesuuryouKbn());
        //余力チェック区分
        inData.setCheckId(API_REQUEST_CHECK_ID);
        //RBE注文種別
        if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), ExecuteMethod.LIMIT.getId())
                || StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), ExecuteMethod.MARKET.getId())) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_NORMAL);
        } else if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), ExecuteMethod.STOP.getId())) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
        }
        //トリガ発動ゾーン
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_BEFORE)) {
            if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), ExecuteMethod.STOP.getId())) {
                inData.setTriggerZone(TRIGGER_ZONE_OVER);
            } else {
                inData.setTriggerZone(TRIGGER_ZONE_NONE);
            }
        } else if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_AFTER)) {
            inData.setTriggerZone(TRIGGER_ZONE_NONE);
        }
        
        //トリガ値段
        if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_BEFORE)) {
            if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), ExecuteMethod.STOP.getId())) {
                inData.setTriggerPrice(StringUtils.leftPad(dtoReq.getIfd1TriggerPrice(), 10, "0"));
            } else {
                inData.setTriggerPrice(API_TRIGGER_PRICE);
            }
        } else if (StringUtils.equals(dtoReq.getWorkingStatus(), WORKING_STATUS_IGNITION_AFTER)) {
            inData.setTriggerPrice(API_TRIGGER_PRICE);
        }
        //OCO指成区分
        inData.setOcoSasinariKbn(API_OCO_SASINARI_KBN_NONE);
        //OCO指値
        inData.setOcoPrice(API_OCO_PRICE_NONE);
        //自動注文種別
        inData.setAutoOrderKind(API002_AUTO_ORDER_KIND);
        //DONE指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())) {
            inData.setDoneSasinariKbn(dtoReq.getIfd2SasinariJyouken());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            inData.setDoneSasinariKbn(dtoReq.getOco1SasinariJyouken());
        }
        //DONE指値（訂正値段）
        inData.setDonePrice(API002_DONE_PRICE_EX);
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())) {
            if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), ExecuteMethod.LIMIT.getId())) {
                inData.setDonePrice(StringUtils.leftPad(dtoReq.getIfd2Price(), 10, "0"));
            } else if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), ExecuteMethod.STOP.getId())
                    && StringUtils.equals(dtoReq.getIfd2GyakusasiHouhou(), ExecuteMethod.LIMIT.getId())) {
                inData.setDonePrice(StringUtils.leftPad(dtoReq.getIfd2Price(), 10, "0"));
            }
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            if (StringUtils.equals(dtoReq.getOco1SasinariHouhou(), ExecuteMethod.LIMIT.getId())) {
                inData.setDonePrice(StringUtils.leftPad(dtoReq.getOco1Price(), 10, "0"));
            }
        }
        //DONERBE注文種別
        inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_NORMAL);
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())) {
            if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), ExecuteMethod.LIMIT.getId())
                    || StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), ExecuteMethod.MARKET.getId())) {
                inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_NORMAL);
            } else if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), ExecuteMethod.STOP.getId())) {
                inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_SLO);
            }
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_OCO);
        }
        //DONEトリガ発動ゾーン
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                && StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), ExecuteMethod.STOP.getId())) {
            inData.setDoneTriggerZone(TRIGGER_ZONE_UNDER);
            
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            inData.setDoneTriggerZone(TRIGGER_ZONE_UNDER);
            
        } else {
            inData.setDoneTriggerZone(TRIGGER_ZONE_NONE);
        }
        //DONEトリガ値段（訂正値段）
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                && StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), ExecuteMethod.STOP.getId())) {
            inData.setDoneTriggerPrice(StringUtils.leftPad(dtoReq.getIfd2TriggerPrice(), 10, "0"));
            
        } else if (
                StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())
                && StringUtils.equals(dtoReq.getOco1SasinariHouhou(), ExecuteMethod.LIMIT.getId())
        ) {
            inData.setDoneTriggerPrice(StringUtils.leftPad(dtoReq.getOco2TriggerPrice(), 10, "0"));
            
        } else {
            inData.setDoneTriggerPrice(API002_DONE_TRIGGER_PRICE);
        }
        //DONEOCO指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            inData.setDoneOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
        } else {
            inData.setDoneOcoSasinariKbn(API002_DONE_OCO_SASINARI_KBN);
        }
        //DONEOCO指値（訂正値段）
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            inData.setDoneOcoPrice(StringUtils.leftPad(dtoReq.getOco2Price(), 10, "0"));
        } else {
            inData.setDoneOcoPrice(API002_DONE_OCO_PRICE);
        }
        //媒介
        inData.setIntermediary(API_INTERMEDIARY);
        //IPアドレス
        inData.setIpAddress(API_REQUEST_IP_ADDRESS);
        
        EstimateStockModifyOrderAutoIn input = new EstimateStockModifyOrderAutoIn();
        input.setIndata(inData);
        
        // 自動注文(国内株式現物・信用)新規注文確認情報を取得する。
        result = apiWrapper.estimateStockModifyOrderAuto(input);
        
        return result;
    }
    
    /**
     * API003呼び出し処理
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return API結果
     */
    private QueryAccountBalanceOutData callApi003(String butenCode, String accountNumber) throws Exception {
        
        QueryAccountBalanceOutData result = new QueryAccountBalanceOutData();
        QueryAccountBalanceIn input = new QueryAccountBalanceIn();
        QueryAccountBalanceInData inData = new QueryAccountBalanceInData();
        
        inData.setButenCd(butenCode);
        inData.setKozaNo(accountNumber);
        input.setIndata(inData);
        
        // NRI_APIから買付余力情報を取得する。
        result = apiWrapper.queryAccountBalance(input);
        
        return result;
    }
    
    /**
     * API004 呼び出し処理
     *
     * @param ecOrderNo EC受注番号
     * @param cc 顧客共通情報
     * @return API004の結果
     */
    private QueryStockUniteOrderPointOrd callApi004(String ecOrderNo, CustomerCommon cc, ApiErrorUtil apiErrorUtil)
            throws Exception {
        
        QueryStockUniteOrderPointOrd result = new QueryStockUniteOrderPointOrd();
        QueryStockUniteOrderPointInData inData = new QueryStockUniteOrderPointInData();
        
        inData.setButenCd(cc.getButenCode());
        inData.setKozaNo(StringUtils.leftPad(cc.getAccountNumber(), 7, "0"));
        inData.setExecOrder(EXEC_ORDER); // 受注番号指定
        inData.setRefFrom(REF_FROM);
        inData.setRefTo(REF_TO);
        inData.setOrderNo(StringUtils.leftPad(ecOrderNo, 6, "0"));
        inData.setTorihikiKbn(TORIHIKI_KBN); // 現物信用注文明細（上場CW含まない）
        inData.setTradeGetKbn(TRADE_GET_KBN); // 約定明細取得
        inData.setBrandCd(BRAND_CD);
        
        if (StringUtils.equals(cc.getJrIsaContractType(), JrIsaContractType.CONTRACT.getId())) {
            inData.setAccountGetKbn(ACCOUNT_GET_KBN_NORMAL); // JrNISA口座(第一、第二口座両方)
        } else {
            inData.setAccountGetKbn(ACCOUNT_GET_KBN_JRNISA); // 通常口座およびJrNISA口座の第一口座
        }
        
        QueryStockUniteOrderPointIn input = new QueryStockUniteOrderPointIn();
        input.setIndata(inData);
        
        // 自動注文(国内株式現物・信用)新規注文確認情報を取得する。
        QueryStockUniteOrderPointOutData api004Res = apiWrapper.queryStockUniteOrderPoint(input);
        apiErrorUtil.checkApiResponse(api004Res.getShubetu(),
                api004Res.getCode(), api004Res.getMessage());
        
        result = api004Res.getReqOrderDataExe().get(0);
        
        return result;
    }
    
    /**
     * FCT001チェック
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return 成功の場合：null、エラーの場合：レスポンス用DataList
     */
    private <T> DataList<T> checkFct001(String butenCode, String accountNumber) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        
        if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(), TARGET_CUSTOMER_REF_AUTH_FLAG)) {
            
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
            
            return dtoRes;
        }
        
        if (StringUtils.equals(output.getTradeSuspendFlag(), TRADE_SUSPEND_FLAG)) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * FCT003チェック
     *
     * @param butenCoden 部店コード
     * @param accountNumber 口座番号
     * @param tradeCd 取引種別
     * @return 成功の場合：null、エラーの場合：レスポンス用DataList
     */
    private <T> DataList<T> checkFct003(String butenCode, String accountNumber, String tradeCd) {
        
        InputFct003Dto input = new InputFct003Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        input.setProductCd(DOMESTICSTOCK);
        input.setTradeCd(tradeCd);
        
        OutputFct003Dto output = fct003.doCheck(input);
        
        //FCT003.媒介可取引有無が"1"（あり）以外の場合 = 取引不可エラー
        if (!StringUtils.equals(output.getMediateAbleTradeFlag(), MEDIATE_AVLE_TRADE_FLAG)) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_ID) }));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * FCT006
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param brandCode 銘柄コード
     * @param kanyuKbn 勧誘区分
     * @param receiveOrderType 受注方法
     * @return API結果
     */
    private OutputFct006Dto callFct006(String butenCode, String accountNumber, String brandCode, String kanyuKbn,
            String receiveOrderType) {
        
        InputFct006Dto input = new InputFct006Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        input.setBrDomesticFgnInd(DomesticForeignType.DOMESTIC.getId());
        input.setBrBrandInd(SecurityType.STOCK.getId());
        input.setBrandCode1(brandCode);
        input.setInvitationType(kanyuKbn);
        input.setOrderMethod(receiveOrderType);
        input.setComplaCheckKind(ComplaCheckKind.STOCK_BUY.getId());
        
        OutputFct006Dto output = fct006.doCheck(input);
        
        return output;
    }
    
    /**
     * FCT021
     *
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param tradeCd 取引種別
     * @param market 選択市場
     * @return API結果
     */
    private OutputFct021Dto callFct021(String butenCode, String accountNumber, String tradeCd, String market)
            throws Exception {
        
        InputFct021Dto input = new InputFct021Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        input.setTradeCd(tradeCd);
        input.setProductCd(DOMESTICSTOCK);
        input.setCountryCd(NationalityCode.OT.getId());
        input.setCurrencyCode(CURRENCY_CODE_VALUE);
        input.setTradeRestrictChkMarket(market);
        OutputFct021Dto output = fct021.doCheck(input);
        
        return output;
    }
    
    /**
     * FCT027呼び出し
     *
     * @param brandCode 銘柄コード
     * @return API結果
     */
    private OutputFct027Dto getDataFct027(String brandCode) {
        
        InputFct027Dto input = new InputFct027Dto();
        input.setBrandCode(brandCode);
        
        return fct027.getData(input);
        
    }
    
    /**
     * 注文種別算出
     *
     * @param api004OutData API004のレスポンスの注文部
     * @return orderKind 算出した注文種別
     */
    
    private String getOrderKind(QueryStockUniteOrderPointOrd api004OutData) {
        
        String orderKind = StringUtil.EMPTY_STRING;
        
        // API004.自動注文種別=通 常注文/逆指注文/OCO注文 または　IFD子注文 の場合
        if (StringUtils.equals(api004OutData.getAutoOrderKind(), AUTO_ORDER_KIND_NORMAL)
                || StringUtils.equals(api004OutData.getAutoOrderKind(), AUTO_ORDER_KIND_CHILED)) {
            //API004.RBE注文種別= 通常注文　または　逆指値注文 の場合
            if (StringUtils.equals(api004OutData.getRbeOrderKind(), RBE_ORDER_KIND_NORMAL)
                    || StringUtils.equals(api004OutData.getRbeOrderKind(), RBE_ORDER_KIND_SLO)) {
                orderKind = OrderClass.NORMAL.getId();
                
            } else if (StringUtils.equals(api004OutData.getRbeOrderKind(), RBE_ORDER_KIND_OCO)) {
                //API004.RBE注文種別= OCO注文 の場合
                orderKind = OrderClass.OCO.getId();
            }
            
        } else if (StringUtils.equals(api004OutData.getAutoOrderKind(), AUTO_ORDER_KIND_PARENT)) { //API004.自動注文種別=IFDIFD親注文の場合
            //API004.DONE　RBE注文種別=OCO注文 の場合
            if (StringUtils.equals(api004OutData.getDoneRbeOrderKind(), DONE_RBE_ORDER_KIND_OCO)) {
                orderKind = OrderClass.IFDOCO.getId();
            } else {
                orderKind = OrderClass.IFD.getId();
            }
        }
        
        return orderKind;
    }
    
    /**
     * 通常/逆指値.執行方法算出
     *
     * @param api004OutData API004のレスポンスの注文部
     * @return sasinarihouhou 算出した執行方法
     */
    private String getSasinarihouho(QueryStockUniteOrderPointOrd api004OutData) {
        
        String sasinarihouhou = StringUtil.EMPTY_STRING;
        //API004.RBE注文種別= 通常注文　の場合
        if (StringUtils.equals(api004OutData.getRbeOrderKind(), RBE_ORDER_KIND_NORMAL)) {
            
            LimitMarketType limitMarketType = LimitMarketType.valueOfId(api004OutData.getSasinariId());
            if (Objects.isNull(limitMarketType)) {
                return null;
            }
            switch (limitMarketType) {
            //注文部.指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
            case PRICE_LIMIT:
            case YORISASI:
            case HIKESASI:
            case FUNARI:
            case IOC_SASI:
                sasinarihouhou = ExecuteMethod.LIMIT.getId();
                break;
            
            //注文部.指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
            case MARKET_ORDER:
            case YORINARI:
            case HIKENARI:
            case IOC_NARI:
                sasinarihouhou = ExecuteMethod.MARKET.getId();
                break;
            
            default:
                break;
            }
            
        } else if (StringUtils.equals(api004OutData.getRbeOrderKind(), RBE_ORDER_KIND_SLO)) {
            sasinarihouhou = ExecuteMethod.STOP.getId();
        }
        return sasinarihouhou;
    }
    
    /**
     * 通常/逆指値.執行方法(逆指値)算出
     *
     * @param api004OutData API004のレスポンスの注文部
     * @return gyakusasiHouhou 算出した執行方法(逆指値)
     */
    private String getGyakusasiHouhou(QueryStockUniteOrderPointOrd api004OutData) {
        
        String gyakusasiHouhou = StringUtil.EMPTY_STRING;
        //API004.RBE注文種別= 逆指値注文 の場合
        if (StringUtils.equals(api004OutData.getRbeOrderKind(), RBE_ORDER_KIND_SLO)) {
            LimitMarketType limitMarketType = LimitMarketType.valueOfId(api004OutData.getSasinariId());
            if (Objects.isNull(limitMarketType)) {
                return null;
            }
            switch (limitMarketType) {
            //注文部.指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
            case PRICE_LIMIT:
            case YORISASI:
            case HIKESASI:
            case FUNARI:
            case IOC_SASI:
                gyakusasiHouhou = ExecuteMethod.LIMIT.getId();
                break;
            
            //注文部.指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
            case MARKET_ORDER:
            case YORINARI:
            case HIKENARI:
            case IOC_NARI:
                gyakusasiHouhou = ExecuteMethod.MARKET.getId();
                break;
            
            default:
                break;
            }
        }
        return gyakusasiHouhou;
    }
    
    /**
     * OCO2.執行方法（逆指値）算出
     *
     * @param api004OutData API004のレスポンスの注文部
     * @return oco2GyakusasiHouhou 算出した執行方法(逆指値)
     */
    private String getOco2GyakusasiHouhou(QueryStockUniteOrderPointOrd api004OutData, String limitMarketType) {
        
        String oco2GyakusasiHouhou = StringUtil.EMPTY_STRING;
        //指成区分=指値、不成(F)　の場合
        if (StringUtils.equals(limitMarketType, LimitMarketType.PRICE_LIMIT.getId())
                || StringUtils.equals(limitMarketType, LimitMarketType.FUNARI.getId())) {
            oco2GyakusasiHouhou = ExecuteMethod.LIMIT.getId();
        }
        //指成区分=成行　の場合
        if (StringUtils.equals(limitMarketType, LimitMarketType.MARKET_ORDER.getId())) {
            oco2GyakusasiHouhou = ExecuteMethod.MARKET.getId();
        }
        return oco2GyakusasiHouhou;
    }
    
    /**
     * IFD2.執行方法算出
     *
     * @param api004OutData API004のレスポンスの注文部
     * @return ifd2SasinariHouhou 算出した執行方法
     */
    private String getIfd2SasinariHouhou(QueryStockUniteOrderPointOrd api004OutData) {
        
        String ifd2SasinariHouhou = StringUtil.EMPTY_STRING;
        //DONE RBE注文種別=通常注文　の場合
        if (StringUtils.equals(api004OutData.getDoneRbeOrderKind(), DONE_RBE_ORDER_KIND_NORMAL)) {
            LimitMarketType limitMarketType = LimitMarketType.valueOfId(api004OutData.getDoneSasinariId());
            if (Objects.isNull(limitMarketType)) {
                return null;
            }
            switch (limitMarketType) {
            //注文部.DONE 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
            case PRICE_LIMIT:
            case YORISASI:
            case HIKESASI:
            case FUNARI:
            case IOC_SASI:
                ifd2SasinariHouhou = ExecuteMethod.LIMIT.getId();
                break;
            
            //注文部.DONE 指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
            case MARKET_ORDER:
            case YORINARI:
            case HIKENARI:
            case IOC_NARI:
                ifd2SasinariHouhou = ExecuteMethod.MARKET.getId();
                break;
            
            default:
                break;
            }
        }
        //DONE RBE注文種別=逆指値注文　の場合
        if (StringUtils.equals(api004OutData.getDoneRbeOrderKind(), DONE_RBE_ORDER_KIND_SLO)) {
            ifd2SasinariHouhou = ExecuteMethod.STOP.getId();
        }
        return ifd2SasinariHouhou;
    }
    
    /**
     * IFD2.執行方法(逆指値)算出
     *
     * @param api004OutData API004のレスポンスの注文部
     * @return ifd2GyakusasiHouhou 算出した執行方法
     */
    private String getIfd2GyakusasiHouhou(QueryStockUniteOrderPointOrd api004OutData) {
        
        String ifd2GyakusasiHouhou = StringUtil.EMPTY_STRING;
        //DONE RBE注文種別=逆指値注文　の場合
        if (StringUtils.equals(api004OutData.getDoneRbeOrderKind(), DONE_RBE_ORDER_KIND_SLO)) {
            LimitMarketType limitMarketType = LimitMarketType.valueOfId(api004OutData.getDoneSasinariId());
            if (Objects.isNull(limitMarketType)) {
                return null;
            }
            switch (limitMarketType) {
            //注文部.DONE 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
            case PRICE_LIMIT:
            case YORISASI:
            case HIKESASI:
            case FUNARI:
            case IOC_SASI:
                ifd2GyakusasiHouhou = ExecuteMethod.LIMIT.getId();
                break;
            
            //注文部.DONE 指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
            case MARKET_ORDER:
            case YORINARI:
            case HIKENARI:
            case IOC_NARI:
                ifd2GyakusasiHouhou = ExecuteMethod.MARKET.getId();
                break;
            
            default:
                break;
            }
        }
        return ifd2GyakusasiHouhou;
    }
    
    /**
     * A010のレスポンスを設定する
     */
    private void setResponseDtoA010(IfaDomesticStockOrderCorrectInputA010DtoResponse response, String brandName,
            String shubetu, String code, String message, String jrnisaTransferAmount, String estimatePrice,
            String isaBuyLimit, String tradeDate, String settlementDate, String acceptDateTime, String acBalanceAfter,
            String modLimitFlg, String modLimit, String sorModifyStatus) {
        
        // 銘柄名
        response.setBrandName(brandName);
        // 種別
        response.setShubetu(shubetu);
        // エラーコード
        response.setCode(code.trim());
        // エラーメッセージ
        response.setErrMessage(message.trim());
        // ジュニアNISA振替金額
        response.setJrnisaTransferAmount(jrnisaTransferAmount.trim());
        // 見積単価
        response.setQuoteUnitPrice(estimatePrice.trim());
        // 投資可能枠
        response.setNisaInvestableQuote(isaBuyLimit.trim());
        // 約定予定日
        response.setContractDate(tradeDate);
        // 受渡予定日
        response.setDeliveryDate(settlementDate);
        // 受注日時
        response.setOrderDayTime(acceptDateTime);
        // 訂正後買付余力
        response.setAfterCorrecBuyPower(acBalanceAfter.trim());
        // 有効期限変更フラグ
        response.setYukoKigenChange(modLimitFlg);
        // 有効期限
        response.setYukoKigen(modLimit);
        // 訂正SOR注文結果区分
        response.setCorrectSorOrderResultClassification(sorModifyStatus);
    }
    
    /**
     * A001とA004のレスポンスの処理を纏めるためのクラス
     */
    @Data
    public class ResponseParam {
        
        /** 受渡日(T+2) */
        private String settlementDateAfterBusinessDayT2;
        
        /** 受渡日(T+3) */
        private String settlementDateAfterBusinessDayT3;
        
        /** 総合口座（T+2） */
        private String yenBuyingPowerGeneralAccountT2;
        
        /** 総合口座（T+3） */
        private String yenBuyingPowerGeneralAccountT3;
        
        /** JrNISA口座（T+2） */
        private String yenBuyingPowerJrNisaT2;
        
        /** JrNISA口座（T+3） */
        private String yenBuyingPowerJrNisaT3;
        
        /** NISA買付可能枠（数字） */
        private String nisaInvestableQuotaMessage;
        
        /** 銘柄コード（半角英数字） */
        private String brandCode;
        
        /** 銘柄名（全角半角） */
        private String brandName;
        
        /** 市場（全角） */
        private String market;
        
        /** 取引種別 */
        private String tradeCd;
        
        /** 注文数量 */
        private String orderQuantity;
        
        /** 未約定数量（数字） */
        private String unTradeQuantity;
        
        /** 期間 */
        private String period;
        
        /** 預り区分 */
        private String depositType;
        
        /** 注文種別 */
        private String orderKind;
        
        /** 通常/逆指値.執行方法 */
        private String sasinariHouhou;
        
        /** 通常/逆指値.執行条件 */
        private String sasinariJyouken;
        
        /** 通常/逆指値.発火条件価格（逆指値） */
        private String triggerPrice;
        
        /** 通常/逆指値.発火条件価格（逆指値）ゾーン */
        private String stopOrderPriceText2;
        
        /** 通常/逆指値.執行方法（逆指値） */
        private String gyakusasiHouhou;
        
        /** 通常/逆指値.注文単価 */
        private String price;
        
        /** OCO1.執行方法 */
        private String oco1SasinariHouhou;
        
        /** OCO1.執行条件 */
        private String oco1SasinariJyouken;
        
        /** OCO1.注文単価 */
        private String oco1Price;
        
        /** OCO2.発火条件価格（逆指値） */
        private String oco2TriggerPrice;
        
        /** OCO2.発火条件価格（逆指値）ゾーン */
        private String oco2StopOrderPriceText2;
        
        /** OCO2.執行方法（逆指値） */
        private String oco2GyakusasiHouhou;
        
        /** OCO2.執行条件（逆指値） */
        private String oco2GyakusasiJyouken;
        
        /** OCO2.注文単価 */
        private String oco2Price;
        
        /** IFD1.執行方法 */
        private String ifd1SasinariHouhou;
        
        /** IFD1.執行条件 */
        private String ifd1SasinariJyouken;
        
        /** IFD1.発火条件価格（逆指値） */
        private String ifd1TriggerPrice;
        
        /** IFD1.発火条件価格（逆指値）ゾーン */
        private String ifd1StopOrderPriceText2;
        
        /** IFD1.執行方法（逆指値） */
        private String ifd1GyakusasiHouhou;
        
        /** IFD1.注文単価 */
        private String ifd1Price;
        
        /** IFD2.期間.日付 */
        private String ifd2Limit;
        
        /** IFD2.執行方法 */
        private String ifd2SasinariHouhou;
        
        /** IFD2.執行条件 */
        private String ifd2SasinariJyouken;
        
        /** IFD2.発火条件価格（逆指値） */
        private String ifd2TriggerPrice;
        
        /** IFD2.発火条件価格（逆指値）ゾーン */
        private String ifd2StopOrderPriceText2;
        
        /** IFD2.執行方法（逆指値） */
        private String ifd2GyakusasiHouhou;
        
        /** IFD2.注文単価 */
        private String ifd2Price;
        
        /** 発火状態 */
        private String workingStatus;
        
        /** DONE状態 */
        private String doneState;
        
        /**RBE注文ステータス */
        private String rbeOrderStatus;
        
        /**手数料区分 */
        private String tesuuryouKbn;
        
        /** 受注日 */
        private String orderDate;

        /** 約定ステータス */
        private String tradeStatus;

        /** 直近市場 */
        private String latestMarketId;

        /** SOR注文区分 */
        private String sorOrderClassification;
    }
}

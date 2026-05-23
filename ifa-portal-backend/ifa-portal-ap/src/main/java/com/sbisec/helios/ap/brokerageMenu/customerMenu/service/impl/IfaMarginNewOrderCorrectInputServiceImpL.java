package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.bizcommon.util.OrderStatusUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA010DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectInputA010DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertCalcDomesticMarginTradetypeText;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginNewOrderCorrectInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.MCodeService;
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
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOrd;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;

/**
 * 画面ID：SUB0202_0212-02_1
 * 画面名：信用新規注文訂正入力
 *
 * @author SCSK
   2024/03/28 新規作成
 */
@Component(value = "cmpIfaMarginNewOrderCorrectInputService")
public class IfaMarginNewOrderCorrectInputServiceImpL implements IfaMarginNewOrderCorrectInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginNewOrderCorrectInputServiceImpL.class);
    
    private static final String SPACE = " ";
    
    private static final String WARNING = "W";
    
    /** 算出処理にて半角スペースに対応する文字 */
    private static final String SPACE_TO = "S";
    
    /** "1": 発火済 */
    private static final String RBE_ORDER_STATUS_1 = "1";
    
    /** 国内株式:信用取引区分 年 */
    private static final String DOMESTIC_MARGINTRADETYPE_TEXT_YEAR = "年";
    
    /** 国内株式:信用取引区分 ヵ月 */
    private static final String DOMESTIC_MARGINTRADETYPE_TEXT_MONTH = "ヵ月";
    
    /** 国内株式:信用取引区分 日 */
    private static final String DOMESTIC_MARGINTRADETYPE_TEXT_DAY = "日";
    
    /** 国内株式:信用取引区分 無期限 */
    private static final String DOMESTIC_MARGINTRADETYPE_TEXT_UNLIMITED = "無期限";
    
    /** API003 一般信用売弁済期限年月日区分の出力値 "Y"：年単位 */
    private static final String IPPAN_MG_PAYMENTKBN_YEAR = "Y";
    
    /** API003 一般信用売弁済期限年月日区分の出力値 "M"：月単位 */
    private static final String IPPAN_MG_PAYMENTKBN_MONTH = "M";
    
    /** API003 一般信用売弁済期限年月日区分の出力値 "D"：日単位 */
    private static final String IPPAN_MG_PAYMENTKBN_DAY = "D";
    
    /** API003 一般信用売弁済期限年月日区分の出力値 "△"：無期限 */
    private static final String IPPAN_MG_PAYMENTKBN_UNLIMITED = " ";
    
    /** 算出結果が得られなかった時の設定値 -:(無し) */
    private static final String NONE_OUTPUT = "-";
    
    /** 注文部.売買区分="K":買い */
    private static final String BUY_CODE = "K";
    
    /** 注文部.売買区分="U":売り */
    private static final String SELL_CODE = "U";
    
    /** 信用新規買 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_BUY = "3";
    
    /** 信用新規売 */
    private static final String DOMESTIC_STOCK_TRADE_CLASS_SELL = "4";
    
    /** 媒介可否が"1"（媒介可） */
    private static final String INTERMEDIARY_VALUE_1 = "1";
    
    /** 権限なしエラー */
    private static final String NO_AUTHORIZED = "0";
    
    /** 取引停止口座エラー */
    private static final String TRADE_SUSPEND_FLAG = "1";
    
    /** 証券金銭種別(国内株式) */
    private static final String PRODUCT_CODE_01 = "01";
    
    /** 区分.対象取引（メッセージ表示用）（区分値：2　＠表示パターン：1　） */
    private static final String MSG_DISPLAY_TARGET_TRADE_2 = "2";
    
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 入力した部店口座は存在しません */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    private static final String ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 指定した注文が見つかりません。 */
    private static final String ERRORS_CMN_ORDER_NOTFOUND = "errors.cmn.order.notFound";
    
    /** 指定した注文は訂正できません。 */
    private static final String ERRORS_CMN_ORDERMODIFY_OUTOFSERVICE = "errors.cmn.orderModify.outOfService";
    
    /** {0}ができないコースです。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 信用口座が未開設です。 */
    private static final String ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN = "errors.dms.domesticMarginAccount.notOpen";

    /** CCSIDが未登録のためご利用できません。 */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** 当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。 */
    private static final String ERRORS_CMN_NOTICEERRORCHECK = "errors.cmn.noticeErrorCheck";
    
    /** 未確認の「重要なお知らせ」があります。 */
    private static final String ERRORS_INFORMATIONCHECK = "errors.informationCheck";
    
    /** 当該顧客の{0}に関わる注意情報があります。注意情報欄を確認してください。 */
    private static final String WARNINGS_CMN_NOTICEWARNINGCHECK = "warnings.cmn.noticeWarningCheck";
    
    /** 「重要なお知らせ」が未確認です。重要なお知らせ取引制限欄や注意情報欄を確認してください。 */
    private static final String WARNINGS_CMN_INFORMATIONCHECK = "warnings.cmm.informationCheck";
    
    /** 注文銘柄に対する取引注意情報があります。取引注意情報の内容を顧客へ説明後に注文を執行してください。 */
    private static final String WARNINGS_DMS_INFORMATIONCHECK = "warnings.dms.informationCheck";
    
    /** 注文内容に変更がないため、注文訂正を行いません。　*/
    private static final String ERRORS_CMN_ORDERMODIFY_NOMODIFY = "errors.cmn.orderModify.noModify";
    
    /** API004余力項目セット区分 */
    private static final String REQUEST_KBN1_A = "A";
    
    /** API004追証項目セット区分 */
    private static final String REQUEST_KBN2_N = "N";
    
    /** API003リクエスト区分 */
    private static final String EXEC_ORDER = "2";
    
    /** API003検索番号指定 */
    private static final String REF_FROM_AND_TO = "     ";
    
    /** API003取引区分 */
    private static final String TORIHIKI_KBN = "1";
    
    /** API003約定取得区分 */
    private static final String TRADE_GET_KBN = "1";
    
    /** 自動注文種別:通常注文/逆指注文/OCO注文 */
    private static final String SPACE_4 = "    ";
    
    /** 自動注文種別:IFD親注文 */
    private static final String IFD_FATHER_ORDER = "IF  ";
    
    /** 自動注文種別:IFD子注文 */
    private static final String IFD_SON_ORDER = "DONE";
    
    /** RBE注文種別 'SLO'：逆指値注文 */
    private static final String RBE_ORDER_KIND_SLO = "SLO";
    
    /** RBE注文種別 オール'△'：通常注文 */
    private static final String RBE_ORDER_KIND_SPACE3 = "   ";
    
    /** RBE注文種別 'OCO'：OCO注文 */
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** DONE RBE注文種別 'SLO'：逆指値注文 */
    private static final String DONE_RBE_ORDER_KIND_SLO = "SLO";
    
    /** DONE RBE注文種別 オール'△'：通常注文 */
    private static final String DONE_RBE_ORDER_KIND_SPACE3 = "   ";
    
    /** DONE RBE注文種別 OCO注文 */
    private static final String DONE_RBE_ORDER_KIND_OCO = "OCO";
    
    /** 区分.注文種別:通常/逆指値 */
    private static final String ORDER_CLASS_1 = "1";
    
    /** 区分.注文種別:OCO */
    private static final String ORDER_CLASS_2 = "2";
    
    /** 区分.注文種別:IFD */
    private static final String ORDER_CLASS_3 = "3";
    
    /** 区分.注文種別:IFDOCO */
    private static final String ORDER_CLASS_4 = "4";
    
    /** 指成区分:寄指(Y) */
    private static final String LIMIT_MARKET_TYPE_Z = "Z";
    
    /** 指成区分:引指(H) */
    private static final String LIMIT_MARKET_TYPE_I = "I";
    
    /** 指成区分:不成(F) */
    private static final String LIMIT_MARKET_TYPE_F = "F";
    
    /** 指成区分:IOC指(I) */
    private static final String LIMIT_MARKET_TYPE_P = "P";
    
    /** 指成区分:成行 */
    private static final String LIMIT_MARKET_TYPE_N = "N";
    
    /** 指成区分:寄成(Y) */
    private static final String LIMIT_MARKET_TYPE_Y = "Y";
    
    /** 指成区分:引成(H) */
    private static final String LIMIT_MARKET_TYPE_H = "H";
    
    /** 指成区分:IOC成(I) */
    private static final String LIMIT_MARKET_TYPE_O = "O";
    
    /** 執行方法:指値 */
    private static final String EXECUTE_METHOD_1 = "1";
    
    /** 執行方法:成行 */
    private static final String EXECUTE_METHOD_2 = "2";
    
    /** 執行方法:逆指値 */
    private static final String EXECUTE_METHOD_3 = "3";
    
    private static final String SASHINE = "1";
    
    private static final String NARIYUKI = "2";
    
    /** FCT021 国籍コード */
    private static final String COUNTRYCD_99 = "99";
    
    /** FCT021 通貨コード */
    private static final String CURRENCYCODE_999 = "999";
    
    /** FCT027 規制銘柄区分=1:規制銘柄 */
    private static final String REGKBN_1 = "1";
    
    /** 注文訂正処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) */
    private static final String ERRORS_CMN_ORDEREXECUTIONMODIFY_FAILED = "errors.cmn.orderExecutionModify.failed";
    
    private static final char CHAR_ZERO = '0';
    
    /** API001・API002 リクエスト（アカウントID）：ALL"0" */
    private static final String API_REQUEST_ORDER_ID_ACCOUNT_ID = String
            .format("%11s", StringUtil.EMPTY_STRING, StringUtil.EMPTY_STRING).replace(" ", "0");
    
    /** API001・API002　リクエスト（アカウント毎の連番）:ALL"0" */
    private static final String API_REQUEST_ORDER_ID_NUMBER = String.format("%7s", StringUtil.EMPTY_STRING).replace(" ",
            "0");
    
    /** API001・API002　リクエスト（オリジン）:"0" */
    private static final String API_REQUEST_ORDER_ID_ORIGIN = "0";
    
    /** API001・API002　リクエスト（商品区分）:"S" */
    private static final String API_REQUEST_ORDER_TYPE = "S";
    
    /** API001・API002　リクエスト（訂正区分）:"PRICE△△△" */
    private static final String API_REQUEST_MODIFY_TYPE = "PRICE" + String.format("%3s", StringUtil.EMPTY_STRING);
    
    /** API001・API002　リクエスト(指値）:"0000000000" */
    private static final String API_REQUEST_PRICE = String
            .format("%10s", StringUtil.EMPTY_STRING, StringUtil.EMPTY_STRING).replace(" ", "0");
    
    /** API001・API002　リクエスト（受付経路区分）:"0"：支店 */
    private static final String API_REQUEST_CALLCENTER_KBN = "0";
    
    /** API001・API002　リクエスト（余力チェック区分）: "△"：買付余力(売却可能数量) チェック要、  ISA買付可能枠チェック要 */
    private static final String API_REQUEST_CHECK_ID = " ";
    
    /** API001・API002　リクエスト(トリガ値段）:"0000000000" */
    private static final String API_TRIGGER_PRICE = String
            .format("%10s", StringUtil.EMPTY_STRING, StringUtil.EMPTY_STRING).replace(" ", "0");
    
    /** API:トリガ発動ゾーン　以上 */
    private static final String TRIGGER_ZONE_OVER = "0";
    
    /** API:トリガ発動ゾーン　以下 */
    private static final String TRIGGER_ZONE_UNDER = "1";
    
    /** API:トリガ発動ゾーン　指定無し */
    private static final String TRIGGER_ZONE_NONE = " ";
    
    /** API001・API002 リクエスト(OCO指値区分):" " */
    private static final String API_OCO_SASINARI_KBN_NONE = " ";
    
    /** API001・API002 リクエスト(OCO指値):　allスぺース */
    private static final String API_OCO_PRICE_NONE = "          ";
    
    /** API001・API002　リクエスト（媒介）:"1"媒介注文 */
    private static final String API_INTERMEDIARY = "1";
    
    /** API001・API002　リクエスト（IPアドレス）:"ifap" */
    private static final String API_REQUEST_IP_ADDRESS = "ifap";
    
    /** API002 リクエスト(自動注文種別):"IF△△"*/
    private static final String API002_AUTO_ORDER_KIND = "IF" + String.format("%2s", StringUtil.EMPTY_STRING);
    
    /** API002 リクエスト(DONE指値（訂正値段）):"0000000000"*/
    private static final String API002_DONE_PRICE_EX = String
            .format("%10s", StringUtil.EMPTY_STRING, StringUtil.EMPTY_STRING).replace(" ", "0");
    
    /** API002 リクエスト(DONEトリガ値段（訂正値段）):"0000000000"*/
    private static final String API002_DONE_TRIGGER_PRICE = String
            .format("%10s", StringUtil.EMPTY_STRING, StringUtil.EMPTY_STRING).replace(" ", "0");
    
    /** API002 リクエスト(DONEOCO指成区分):"△"*/
    private static final String API002_DONE_OCO_SASINARI_KBN = " ";
    
    /** API002 リクエスト(DONEOCO指値（訂正値段）):"0000000000"*/
    private static final String API002_DONE_OCO_PRICE = String
            .format("%10s", StringUtil.EMPTY_STRING, StringUtil.EMPTY_STRING).replace(" ", "0");
    
    /** 「空売り価格規制」に抵触する注文は失効されます。 */
    private static final String SHORT_SELLING_MESSAGE = "「空売り価格規制」に抵触する注文は失効されます。";

    /** コードテーブル.種別.項目説明文言 */
    private static final String M_CODE_CODE_TYPE_DESCRIPTION_MESSAGE = "58";

    /** コードテーブル.CODE_1.信用新規注文訂正入力 */
    private static final String M_CODE_CODE_1_MARGIN_NEW_ORDER_CORRECT_INPUT = "SUB0202_0212-02_1";

    /** コードテーブル.CODE_2.維持率(円貨) */
    private static final String M_CODE_CODE_2_MAINTENANCE_RATE_JPY = "maintenanceRateJPY";
    
    /** 選択市場：当社優先市場／SOR */
    private static final String SELECT_MARKET_SOR = "A";
    
    /** API001 リクエスト（訂正SOR注文区分）：訂正SOR"1" */
    private static final String API001_SOR_MODIFY_CODE_CORRECT_SOR = "1";
    
    /** API001 リクエスト（訂正SOR注文区分）：通常訂正" " */
    private static final String API001_SOR_MODIFY_CODE_NORMAL = " ";
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct027 fct027;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private OrderStatusUtil orderStatusUtil;
    
    @Autowired
    private CodeListService codeListService;

    @Autowired
    MCodeService mcodeService;
    
    /** 市場訂正以外の注文内容に変更がないため、注文訂正を行いません。 */
    private static final String ERRORS_CMN_ORDERMODIFY_MARKETONLYMODIFY = "errors.cmn.orderModify.marketOnlyModify";
    
    /** SOR注文として受け付けられません。市場訂正をやり直してください。 */
    private static final String ERRORS_CMN_SORMODIFY_NOMODIFY  = "errors.cmn.sorModify.noModify";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMarginNewOrderCorrectInputA001DtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectInputA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaMarginNewOrderCorrectInputA001DtoResponse
     * @exception Exception 例外
     */
    public DataList<IfaMarginNewOrderCorrectInputA001DtoResponse> initializeA001(
            IfaMarginNewOrderCorrectInputA001DtoRequest dtoReq) throws Exception {
        
        DataList<IfaMarginNewOrderCorrectInputA001DtoResponse> dtoRes = new DataList<IfaMarginNewOrderCorrectInputA001DtoResponse>();
        List<IfaMarginNewOrderCorrectInputA001DtoResponse> dtoResList = new ArrayList<IfaMarginNewOrderCorrectInputA001DtoResponse>();
        IfaMarginNewOrderCorrectInputA001DtoResponse ifaMarginNewOrderCorrectInputA001DtoResponse = new IfaMarginNewOrderCorrectInputA001DtoResponse();
        dtoResList.add(ifaMarginNewOrderCorrectInputA001DtoResponse);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginNewOrderCorrectInputServiceImplL.initializeA001");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // １．利用者の口座に対する権限チェックを行う。
        // 顧客共通情報取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setAccountNumber(accountNumber);
        inputFct001Dto.setButenCode(butenCode);
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        // 対象顧客参照権限有無＝"0"（権限なし） エラー
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        // 取引停止フラグ＝"1"（取引停止口座） エラー
        if (TRADE_SUSPEND_FLAG.equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        
        // ２．注文情報を取得する。
        QueryStockUniteOrderPointInData api003InData = new QueryStockUniteOrderPointInData();
        api003InData.setButenCd(butenCode);
        String kozaNo = StringUtil.fillLeft(accountNumber, CHAR_ZERO, 7);
        api003InData.setKozaNo(kozaNo);
        api003InData.setExecOrder(EXEC_ORDER);
        api003InData.setRefFrom(REF_FROM_AND_TO);
        api003InData.setRefTo(REF_FROM_AND_TO);
        api003InData.setOrderNo(dtoReq.getEcOrderNo());
        api003InData.setTorihikiKbn(TORIHIKI_KBN);
        api003InData.setTradeGetKbn(TRADE_GET_KBN);
        api003InData.setBrandCd(StringUtil.EMPTY_STRING);
        api003InData.setAccountGetKbn(SPACE);
        QueryStockUniteOrderPointIn api003Req = new QueryStockUniteOrderPointIn();
        api003Req.setIndata(api003InData);
        QueryStockUniteOrderPointOutData api003Res = new QueryStockUniteOrderPointOutData();
        api003Res = apiWrapper.queryStockUniteOrderPoint(api003Req);
        // エラーハンドリング
        apiErrorUtil.checkApiResponse(api003Res.getShubetu(),
                api003Res.getCode(), api003Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        if (api003Res.getReqOrderDataExe().get(0) == null) {
            // 指定した注文情報が取得できない場合、エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_NOTFOUND));
            return dtoRes;
        }
        
        // 訂正ボタン表示判定（算出）
        if (!orderStatusUtil.canCorrectOrder(api003Res.getReqOrderDataExe().get(0))) {
            // 指定した注文は訂正できません。
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_ORDERMODIFY_OUTOFSERVICE, IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_OUTOFSERVICE));
            return dtoRes;
        }
        
        // api003ResをDTOにセットする。
        setApi003ResToDto(api003Res.getReqOrderDataExe().get(0), ifaMarginNewOrderCorrectInputA001DtoResponse);
        
        // ３．取引コース媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(butenCode);
        inputFct003Dto.setAccountNumber(accountNumber);
        inputFct003Dto.setProductCd(PRODUCT_CODE_01);
        inputFct003Dto.setTradeCd(ifaMarginNewOrderCorrectInputA001DtoResponse.getTradeCd());
        OutputFct003Dto outputFct003Dto;
        outputFct003Dto = fct003.doCheck(inputFct003Dto);
        for (OutputFct003Dto.MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
            if (!StringUtils.equals(INTERMEDIARY_VALUE_1, mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                        IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                                new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                        MSG_DISPLAY_TARGET_TRADE_2) }));
                return dtoRes;
            }
        }
        
        // ４.顧客共通情報.信用口座区分（国内）より、国内信用口座開設状況のチェックを行う。
        if (SPACE.equals(customerCommon.getDomesticMarginAccountType())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN,
                    IfaCommonUtil.getMessage(ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN));
            return dtoRes;
        }
        // ５．新規建余力を取得する。
        QueryMgEstimateCapabilityInData api004InData = new QueryMgEstimateCapabilityInData();
        api004InData.setButenCd(butenCode);
        // 口座番号
        api004InData.setKozaNo(kozaNo);
        api004InData.setRequestKbn1(REQUEST_KBN1_A);
        api004InData.setRequestKbn2(REQUEST_KBN2_N);
        QueryMgEstimateCapabilityIn api004Req = new QueryMgEstimateCapabilityIn();
        api004Req.setIndata(api004InData);
        QueryMgEstimateCapabilityOutData api004Res = apiWrapper.queryMgEstimateCapability(api004Req);
        // エラーハンドリング
        apiErrorUtil.checkApiResponse(api004Res.getShubetu(), api004Res.getCode(), api004Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        ifaMarginNewOrderCorrectInputA001DtoResponse
                .setSettlementDate0(api004Res.getSettlementDateT().get(0).getSettlementDate());
        ifaMarginNewOrderCorrectInputA001DtoResponse
                .setSettlementDate1(api004Res.getSettlementDateT().get(1).getSettlementDate());
        ifaMarginNewOrderCorrectInputA001DtoResponse
                .setMarginCapacity0(api004Res.getSettlementDateT().get(0).getMarginCapacity());
        ifaMarginNewOrderCorrectInputA001DtoResponse
                .setMarginCapacity1(api004Res.getSettlementDateT().get(1).getMarginCapacity());
        ifaMarginNewOrderCorrectInputA001DtoResponse
                .setActualGrntRate0(api004Res.getSettlementDateT().get(0).getActualGrntRate());
        ifaMarginNewOrderCorrectInputA001DtoResponse
                .setActualGrntRate1(api004Res.getSettlementDateT().get(1).getActualGrntRate());

        // ６. 維持率(円貨)説明文言を取得する。(SQL001の代わりにMCodeServiceを使用)
        List<MCode> selSql001Res = mcodeService.getMCodeList(
                M_CODE_CODE_TYPE_DESCRIPTION_MESSAGE,
                M_CODE_CODE_1_MARGIN_NEW_ORDER_CORRECT_INPUT,
                M_CODE_CODE_2_MAINTENANCE_RATE_JPY
        );

        if (selSql001Res != null && 0 < selSql001Res.size()) {
            ifaMarginNewOrderCorrectInputA001DtoResponse.setMaintenanceRateJpyAmountDescriptionMessage(
                selSql001Res.get(0).getName()
            );
        }
        
        // 7．SOR取扱区分を取得する。
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(api003Res.getReqOrderDataExe().get(0).getStockSecCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        
        // レスポンス.SOR取扱区分をセットする。
        ifaMarginNewOrderCorrectInputA001DtoResponse.setSorServiceKbn(outputFct027Dto.getSorServiceKbn());

        dtoRes = apiErrorUtil.createDataList(dtoResList, null);
        return dtoRes;
    }
    
    /**
     * 国内株式：信用取引区分を取得
     *
     * @param api003res api003の出力
     * @return result 信用取引区分(算出)
     */
    private String getDomesticMarginTradeTypeText(QueryStockUniteOrderPointOrd api003res) {
        
        // 弁済期限
        String input = api003res.getPaymentLimit();
        if (input != null) {
            input = input.replace(" ", SPACE_TO);
        }
        
        // 判定(弁済期限のみから算出できる場合)
        ConvertCalcDomesticMarginTradetypeText outputValue = ConvertCalcDomesticMarginTradetypeText.valueOfId(input);
        if (outputValue != null) {
            return outputValue.getLabel();
        }
        
        // 一般信用弁済期限年月日区分
        String ymd = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(api003res.getIppanMgPaymentKbn(), IPPAN_MG_PAYMENTKBN_YEAR)) {
            ymd = DOMESTIC_MARGINTRADETYPE_TEXT_YEAR;
        } else if (StringUtils.equals(api003res.getIppanMgPaymentKbn(), IPPAN_MG_PAYMENTKBN_MONTH)) {
            ymd = DOMESTIC_MARGINTRADETYPE_TEXT_MONTH;
        } else if (StringUtils.equals(api003res.getIppanMgPaymentKbn(), IPPAN_MG_PAYMENTKBN_DAY)) {
            ymd = DOMESTIC_MARGINTRADETYPE_TEXT_DAY;
        } else if (StringUtils.equals(api003res.getIppanMgPaymentKbn(), IPPAN_MG_PAYMENTKBN_UNLIMITED)) {
            return DOMESTIC_MARGINTRADETYPE_TEXT_UNLIMITED;
        }
        // 一般信用売弁済期限年月日数
        String result = null;
        result = api003res.getIppanMgPaymentLimit().replaceFirst("^0+", "") + ymd;
        if (StringUtils.equals(api003res.getIppanMgPaymentLimit(), null)) {
            return NONE_OUTPUT;
        }
        return result;
    }
    
    /** api003ResをDTOにセットする。 */
    private void setApi003ResToDto(QueryStockUniteOrderPointOrd api003ResOrderData,
            IfaMarginNewOrderCorrectInputA001DtoResponse ifaMarginNewOrderCorrectInputA001DtoResponse) {
        
        //　銘柄コード
        ifaMarginNewOrderCorrectInputA001DtoResponse.setBrandCode(api003ResOrderData.getStockSecCode());
        // 銘柄名
        ifaMarginNewOrderCorrectInputA001DtoResponse.setBrandName(api003ResOrderData.getSecName());
        // 市場
        ifaMarginNewOrderCorrectInputA001DtoResponse.setMarket(api003ResOrderData.getMarketId());
        // 取引種別
        if (BUY_CODE.equals(api003ResOrderData.getBuySell())) {
            ifaMarginNewOrderCorrectInputA001DtoResponse.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_BUY);
        } else if (SELL_CODE.equals(api003ResOrderData.getBuySell())) {
            ifaMarginNewOrderCorrectInputA001DtoResponse.setTradeCd(DOMESTIC_STOCK_TRADE_CLASS_SELL);
        }
        ifaMarginNewOrderCorrectInputA001DtoResponse.setOrderQuantity(api003ResOrderData.getQuantity());
        ifaMarginNewOrderCorrectInputA001DtoResponse.setUnTradeQuantity(api003ResOrderData.getExecLeftQuantity());
        ifaMarginNewOrderCorrectInputA001DtoResponse.setPeriod(api003ResOrderData.getLimit());
        // 注文種別（区分.注文種別）
        if (SPACE_4.equals(api003ResOrderData.getAutoOrderKind())
                || IFD_SON_ORDER.equals(api003ResOrderData.getAutoOrderKind())) {
            if (RBE_ORDER_KIND_SLO.equals(api003ResOrderData.getRbeOrderKind())
                    || RBE_ORDER_KIND_SPACE3.equals(api003ResOrderData.getRbeOrderKind())) {
                ifaMarginNewOrderCorrectInputA001DtoResponse.setOrderKind(ORDER_CLASS_1);
            } else if (RBE_ORDER_KIND_OCO.equals(api003ResOrderData.getRbeOrderKind())) {
                ifaMarginNewOrderCorrectInputA001DtoResponse.setOrderKind(ORDER_CLASS_2);
            }
        } else if (IFD_FATHER_ORDER.equals(api003ResOrderData.getAutoOrderKind())) {
            if (DONE_RBE_ORDER_KIND_OCO.equals(api003ResOrderData.getDoneRbeOrderKind())) {
                ifaMarginNewOrderCorrectInputA001DtoResponse.setOrderKind(ORDER_CLASS_4);
            } else {
                ifaMarginNewOrderCorrectInputA001DtoResponse.setOrderKind(ORDER_CLASS_3);
            }
        }
        // 発火状態
        if (!RBE_ORDER_STATUS_1.equals(api003ResOrderData.getRbeOrderStatus())) {
            ifaMarginNewOrderCorrectInputA001DtoResponse.setWorkingStatus(false);
        } else {
            ifaMarginNewOrderCorrectInputA001DtoResponse.setWorkingStatus(true);
        }
        // DONE状態
        ifaMarginNewOrderCorrectInputA001DtoResponse.setDoneState(api003ResOrderData.getAutoOrderKind());
        // 弁済期限
        ifaMarginNewOrderCorrectInputA001DtoResponse.setPaymentDeadline(api003ResOrderData.getPaymentLimit());
        // 信用取引区分（算出）を算出する。
        ifaMarginNewOrderCorrectInputA001DtoResponse
                .setMarginTradeTypeTextCalculation(getDomesticMarginTradeTypeText(api003ResOrderData));
        // RBE注文ステータス
        ifaMarginNewOrderCorrectInputA001DtoResponse.setRbeOrderStatus(api003ResOrderData.getRbeOrderStatus());
        // 手数料区分
        ifaMarginNewOrderCorrectInputA001DtoResponse.setComId(api003ResOrderData.getComId());
        // 受注日
        ifaMarginNewOrderCorrectInputA001DtoResponse.setInputDate(api003ResOrderData.getInputDate().substring(0, 8));
        
        // 注文種別=通常/逆指値の場合のみセット
        if (ORDER_CLASS_1.equals(ifaMarginNewOrderCorrectInputA001DtoResponse.getOrderKind())) {
            // 通常/逆指値.執行方法
            if (RBE_ORDER_KIND_SPACE3.equals(api003ResOrderData.getRbeOrderKind())) {
                if (SPACE.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_Z.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_I.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_F.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_P.equals(api003ResOrderData.getSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setSasinariHouhou(EXECUTE_METHOD_1);
                } else if (LIMIT_MARKET_TYPE_N.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_Y.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_H.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_O.equals(api003ResOrderData.getSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setSasinariHouhou(EXECUTE_METHOD_2);
                }
            } else if (RBE_ORDER_KIND_SLO.equals(api003ResOrderData.getRbeOrderKind())) {
                ifaMarginNewOrderCorrectInputA001DtoResponse.setSasinariHouhou(EXECUTE_METHOD_3);
            }
            // 通常/逆指値.執行条件
            ifaMarginNewOrderCorrectInputA001DtoResponse.setSasinariJyouken(api003ResOrderData.getSasinariId());
            // 通常/逆指値.発火条件価格（逆指値）
            ifaMarginNewOrderCorrectInputA001DtoResponse.setTriggerPrice(api003ResOrderData.getLatestTriggerPrice());
            // 通常/逆指値.発火条件価格（逆指値）ゾーン
            ifaMarginNewOrderCorrectInputA001DtoResponse.setTriggerPriceZone(api003ResOrderData.getLatestTriggerZone());
            // 通常/逆指値.執行方法（逆指値）
            if (RBE_ORDER_KIND_SLO.equals(api003ResOrderData.getRbeOrderKind())) {
                if (SPACE.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_Z.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_I.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_F.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_P.equals(api003ResOrderData.getSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setGyakusasiHouhou(SASHINE);
                } else if (LIMIT_MARKET_TYPE_N.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_Y.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_H.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_O.equals(api003ResOrderData.getSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setGyakusasiHouhou(NARIYUKI);
                }
            }
            // 通常/逆指値.注文単価
            ifaMarginNewOrderCorrectInputA001DtoResponse.setPrice(api003ResOrderData.getLimitPrice());
        }
        
        // 注文種別=OCO　または　IFDOCOの場合のみセット
        if (ORDER_CLASS_2.equals(ifaMarginNewOrderCorrectInputA001DtoResponse.getOrderKind())
                || ORDER_CLASS_4.equals(ifaMarginNewOrderCorrectInputA001DtoResponse.getOrderKind())) {
            // OCO1.執行方法
            ifaMarginNewOrderCorrectInputA001DtoResponse.setOco1SasinariHouhou(EXECUTE_METHOD_1);
            if (ORDER_CLASS_2.equals(ifaMarginNewOrderCorrectInputA001DtoResponse.getOrderKind())) {
                // OCO1.執行条件
                ifaMarginNewOrderCorrectInputA001DtoResponse.setOco1SasinariJyouken(api003ResOrderData.getSasinariId());
                // OCO1.注文単価
                ifaMarginNewOrderCorrectInputA001DtoResponse.setOco1Price(api003ResOrderData.getLimitPrice());
                // OCO2.発火条件価格（逆指値）
                ifaMarginNewOrderCorrectInputA001DtoResponse
                        .setOco2TriggerPrice(api003ResOrderData.getLatestTriggerPrice());
                // OCO2.発火条件価格（逆指値）ゾーン
                ifaMarginNewOrderCorrectInputA001DtoResponse
                        .setOco2TriggerPriceZone(api003ResOrderData.getLatestTriggerZone());
                // OCO2.執行方法（逆指値）
                if (SPACE.equals(api003ResOrderData.getLatestOcoSasinariId())
                        || LIMIT_MARKET_TYPE_F.equals(api003ResOrderData.getLatestOcoSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setOco2GyakusasiHouhou(EXECUTE_METHOD_1);
                } else if (LIMIT_MARKET_TYPE_N.equals(api003ResOrderData.getLatestOcoSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setOco2GyakusasiHouhou(EXECUTE_METHOD_2);
                }
                // OCO2.執行条件（逆指値）
                ifaMarginNewOrderCorrectInputA001DtoResponse
                        .setOco2GyakusasiJyouken(api003ResOrderData.getLatestOcoSasinariId());
                // OCO2.注文単価
                ifaMarginNewOrderCorrectInputA001DtoResponse.setOco2Price(api003ResOrderData.getLatestOcoPrice());
            } else if (ORDER_CLASS_4.equals(ifaMarginNewOrderCorrectInputA001DtoResponse.getOrderKind())) {
                // OCO1.執行条件
                ifaMarginNewOrderCorrectInputA001DtoResponse
                        .setOco1SasinariJyouken(api003ResOrderData.getDoneSasinariId());
                // OCO1.注文単価
                ifaMarginNewOrderCorrectInputA001DtoResponse.setOco1Price(api003ResOrderData.getDonePrice());
                // OCO2.発火条件価格（逆指値）
                ifaMarginNewOrderCorrectInputA001DtoResponse
                        .setOco2TriggerPrice(api003ResOrderData.getDoneTriggerPrice());
                // OCO2.発火条件価格（逆指値）ゾーン
                ifaMarginNewOrderCorrectInputA001DtoResponse
                        .setOco2TriggerPriceZone(api003ResOrderData.getDoneTriggerZone());
                // OCO2.執行方法（逆指値）
                if (SPACE.equals(api003ResOrderData.getDoneOcoSasinariId())
                        || LIMIT_MARKET_TYPE_F.equals(api003ResOrderData.getDoneOcoSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setOco2GyakusasiHouhou(EXECUTE_METHOD_1);
                } else if (LIMIT_MARKET_TYPE_N.equals(api003ResOrderData.getDoneOcoSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setOco2GyakusasiHouhou(EXECUTE_METHOD_2);
                }
                // OCO2.執行条件（逆指値）
                ifaMarginNewOrderCorrectInputA001DtoResponse
                        .setOco2GyakusasiJyouken(api003ResOrderData.getDoneOcoSasinariId());
                // OCO2.注文単価
                ifaMarginNewOrderCorrectInputA001DtoResponse.setOco2Price(api003ResOrderData.getDoneOcoPrice());
            }
        }
        // 注文種別=IFD　または　IFDOCOの場合のみセット
        if (ORDER_CLASS_3.equals(ifaMarginNewOrderCorrectInputA001DtoResponse.getOrderKind())
                || ORDER_CLASS_4.equals(ifaMarginNewOrderCorrectInputA001DtoResponse.getOrderKind())) {
            // IFD1.執行方法
            if (RBE_ORDER_KIND_SPACE3.equals(api003ResOrderData.getRbeOrderKind())) {
                if (SPACE.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_Z.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_I.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_F.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_P.equals(api003ResOrderData.getSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd1SasinariHouhou(EXECUTE_METHOD_1);
                } else if (LIMIT_MARKET_TYPE_N.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_Y.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_H.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_O.equals(api003ResOrderData.getSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd1SasinariHouhou(EXECUTE_METHOD_2);
                }
            } else if (RBE_ORDER_KIND_SLO.equals(api003ResOrderData.getRbeOrderKind())) {
                ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd1SasinariHouhou(EXECUTE_METHOD_3);
            }
            // IFD1.執行条件
            ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd1SasinariJyouken(api003ResOrderData.getSasinariId());
            // IFD1.発火条件価格（逆指値）
            ifaMarginNewOrderCorrectInputA001DtoResponse
                    .setIfd1TriggerPrice(api003ResOrderData.getLatestTriggerPrice());
            // IFD1.発火条件価格（逆指値）ゾーン
            ifaMarginNewOrderCorrectInputA001DtoResponse
                    .setIfd1TriggerPriceZone(api003ResOrderData.getLatestTriggerZone());
            // IFD1.執行方法（逆指値）
            if (RBE_ORDER_KIND_SLO.equals(api003ResOrderData.getRbeOrderKind())) {
                if (SPACE.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_Z.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_I.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_F.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_P.equals(api003ResOrderData.getSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd1GyakusasiHouhou(SASHINE);
                } else if (LIMIT_MARKET_TYPE_N.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_Y.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_H.equals(api003ResOrderData.getSasinariId())
                        || LIMIT_MARKET_TYPE_O.equals(api003ResOrderData.getSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd1GyakusasiHouhou(NARIYUKI);
                }
            }
            // IFD1.注文単価
            ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd1Price(api003ResOrderData.getLimitPrice());
            // IFD2.期間.日付
            ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd2Limit(api003ResOrderData.getDoneLimit());
        }
        // 注文種別=IFDの場合のみセット
        if (ORDER_CLASS_3.equals(ifaMarginNewOrderCorrectInputA001DtoResponse.getOrderKind())) {
            // IFD2.執行方法
            if (RBE_ORDER_KIND_SPACE3.equals(api003ResOrderData.getDoneRbeOrderKind())) {
                if (SPACE.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_Z.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_I.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_F.equals(api003ResOrderData.getDoneSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd2SasinariHouhou(EXECUTE_METHOD_1);
                } else if (LIMIT_MARKET_TYPE_N.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_Y.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_H.equals(api003ResOrderData.getDoneSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd2SasinariHouhou(EXECUTE_METHOD_2);
                }
            } else if (RBE_ORDER_KIND_SLO.equals(api003ResOrderData.getDoneRbeOrderKind())) {
                ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd2SasinariHouhou(EXECUTE_METHOD_3);
            }
            // IFD2.執行条件
            ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd2SasinariJyouken(api003ResOrderData.getDoneSasinariId());
            // IFD2.発火条件価格（逆指値）
            ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd2TriggerPrice(api003ResOrderData.getDoneTriggerPrice());
            // IFD2.発火条件価格（逆指値）ゾーン
            ifaMarginNewOrderCorrectInputA001DtoResponse
                    .setIfd2TriggerPriceZone(api003ResOrderData.getDoneTriggerZone());
            // IFD2.執行方法（逆指値）
            if (RBE_ORDER_KIND_SLO.equals(api003ResOrderData.getDoneRbeOrderKind())) {
                if (SPACE.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_Z.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_I.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_F.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_P.equals(api003ResOrderData.getDoneSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd2GyakusasiHouhou(SASHINE);
                } else if (LIMIT_MARKET_TYPE_N.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_Y.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_H.equals(api003ResOrderData.getDoneSasinariId())
                        || LIMIT_MARKET_TYPE_O.equals(api003ResOrderData.getDoneSasinariId())) {
                    ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd2GyakusasiHouhou(NARIYUKI);
                }
            }
            // IFD2.注文単価
            ifaMarginNewOrderCorrectInputA001DtoResponse.setIfd2Price(api003ResOrderData.getDonePrice());
        }

        // 弁済期限年月日数
        ifaMarginNewOrderCorrectInputA001DtoResponse.setPaymentDeadlineDate(api003ResOrderData.getIppanMgPaymentLimit());
        // 年月日区分
        ifaMarginNewOrderCorrectInputA001DtoResponse.setDateKbn(api003ResOrderData.getIppanMgPaymentKbn());
        // 約定ステータス
        ifaMarginNewOrderCorrectInputA001DtoResponse.setTradeStatus(api003ResOrderData.getExecStatus());
        // 直近市場
        ifaMarginNewOrderCorrectInputA001DtoResponse.setLatestMarketId(api003ResOrderData.getLatestMarketId());
        // SOR注文区分
        ifaMarginNewOrderCorrectInputA001DtoResponse.setSorOrderClassification(api003ResOrderData.getSorKbn());

    }
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaMarginNewOrderCorrectInputA004DtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectInputA004DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaMarginNewOrderCorrectInputA004DtoResponse
     * @exception Exception 例外
     */
    public DataList<IfaMarginNewOrderCorrectInputA004DtoResponse> updateA004(
            IfaMarginNewOrderCorrectInputA004DtoRequest dtoReq) throws Exception {
        
        DataList<IfaMarginNewOrderCorrectInputA004DtoResponse> dtoRes = new DataList<IfaMarginNewOrderCorrectInputA004DtoResponse>();
        IfaMarginNewOrderCorrectInputA004DtoResponse ifaMarginNewOrderCorrectInputA004DtoResponse = new IfaMarginNewOrderCorrectInputA004DtoResponse();
        List<IfaMarginNewOrderCorrectInputA004DtoResponse> dtoResList = new ArrayList<IfaMarginNewOrderCorrectInputA004DtoResponse>();
        dtoResList.add(ifaMarginNewOrderCorrectInputA004DtoResponse);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginNewOrderCorrectInputServiceImplL.updateA004");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        
        // １．注文情報を取得する。
        QueryStockUniteOrderPointInData api003InData = new QueryStockUniteOrderPointInData();
        api003InData.setButenCd(butenCode);
        String kozaNo = StringUtil.fillLeft(accountNumber, CHAR_ZERO, 7);
        api003InData.setKozaNo(kozaNo);
        api003InData.setExecOrder(EXEC_ORDER);
        api003InData.setRefFrom(REF_FROM_AND_TO);
        api003InData.setRefTo(REF_FROM_AND_TO);
        api003InData.setOrderNo(dtoReq.getEcOrderNo());
        api003InData.setTorihikiKbn(TORIHIKI_KBN);
        api003InData.setTradeGetKbn(TRADE_GET_KBN);
        api003InData.setBrandCd(StringUtil.EMPTY_STRING);
        api003InData.setAccountGetKbn(SPACE);
        QueryStockUniteOrderPointIn api003Req = new QueryStockUniteOrderPointIn();
        api003Req.setIndata(api003InData);
        QueryStockUniteOrderPointOutData api003Res = new QueryStockUniteOrderPointOutData();
        api003Res = apiWrapper.queryStockUniteOrderPoint(api003Req);
        // エラーハンドリング
        apiErrorUtil.checkApiResponse(api003Res.getShubetu(),
                api003Res.getCode(), api003Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        if (api003Res.getReqOrderDataExe().get(0) == null) {
            // 指定した注文情報が取得できない場合、エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_NOTFOUND));
            return dtoRes;
        }
        
        // 訂正ボタン表示判定（算出）
        if (!orderStatusUtil.canCorrectOrder(api003Res.getReqOrderDataExe().get(0))) {
            // 指定した注文は訂正できません。
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_ORDERMODIFY_OUTOFSERVICE, IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_OUTOFSERVICE));
            return dtoRes;
        }
        
        // api003ResをDTOにセットする。
        IfaMarginNewOrderCorrectInputA001DtoResponse ifaMarginNewOrderCorrectInputA001DtoResponse = new IfaMarginNewOrderCorrectInputA001DtoResponse();
        setApi003ResToDto(api003Res.getReqOrderDataExe().get(0), ifaMarginNewOrderCorrectInputA001DtoResponse);
        
        BeanUtils.copyProperties(ifaMarginNewOrderCorrectInputA004DtoResponse,
                ifaMarginNewOrderCorrectInputA001DtoResponse);
        
        // ２．新規建余力を取得する。
        QueryMgEstimateCapabilityInData api004InData = new QueryMgEstimateCapabilityInData();
        api004InData.setButenCd(butenCode);
        // 口座番号
        api004InData.setKozaNo(kozaNo);
        api004InData.setRequestKbn1(REQUEST_KBN1_A);
        api004InData.setRequestKbn2(REQUEST_KBN2_N);
        QueryMgEstimateCapabilityIn api004Req = new QueryMgEstimateCapabilityIn();
        api004Req.setIndata(api004InData);
        QueryMgEstimateCapabilityOutData api004Res = apiWrapper.queryMgEstimateCapability(api004Req);
        // エラーハンドリング
        apiErrorUtil.checkApiResponse(api004Res.getShubetu(), api004Res.getCode(), api004Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        ifaMarginNewOrderCorrectInputA004DtoResponse
                .setSettlementDate0(api004Res.getSettlementDateT().get(0).getSettlementDate());
        ifaMarginNewOrderCorrectInputA004DtoResponse
                .setSettlementDate1(api004Res.getSettlementDateT().get(1).getSettlementDate());
        ifaMarginNewOrderCorrectInputA004DtoResponse
                .setMarginCapacity0(api004Res.getSettlementDateT().get(0).getMarginCapacity());
        ifaMarginNewOrderCorrectInputA004DtoResponse
                .setMarginCapacity1(api004Res.getSettlementDateT().get(1).getMarginCapacity());
        ifaMarginNewOrderCorrectInputA004DtoResponse
                .setActualGrntRate0(api004Res.getSettlementDateT().get(0).getActualGrntRate());
        ifaMarginNewOrderCorrectInputA004DtoResponse
                .setActualGrntRate1(api004Res.getSettlementDateT().get(1).getActualGrntRate());
        
        // 3.SOR取扱区分を取得する。
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(api003Res.getReqOrderDataExe().get(0).getStockSecCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        
        // レスポンス.SOR取扱区分をセットする。
        ifaMarginNewOrderCorrectInputA004DtoResponse.setSorServiceKbn(outputFct027Dto.getSorServiceKbn());
        
        dtoRes = apiErrorUtil.createDataList(dtoResList, null);
        return dtoRes;
    }
    
    /**
     * アクションID：A010
     * アクション名：訂正確認
     * Dto リクエスト：IfaMarginNewOrderCorrectInputA010DtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectInputA010DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaMarginNewOrderCorrectInputA010DtoResponse
     * @exception Exception 例外
     */
    public DataList<IfaMarginNewOrderCorrectInputA010DtoResponse> correctionConfirmA010(
            IfaMarginNewOrderCorrectInputA010DtoRequest dtoReq) throws Exception {
        
        DataList<IfaMarginNewOrderCorrectInputA010DtoResponse> dtoRes = new DataList<IfaMarginNewOrderCorrectInputA010DtoResponse>();
        IfaMarginNewOrderCorrectInputA010DtoResponse ifaMarginNewOrderCorrectInputA010DtoResponse = new IfaMarginNewOrderCorrectInputA010DtoResponse();
        List<IfaMarginNewOrderCorrectInputA010DtoResponse> dtoResList = new ArrayList<IfaMarginNewOrderCorrectInputA010DtoResponse>();
        dtoResList.add(ifaMarginNewOrderCorrectInputA010DtoResponse);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginNewOrderCorrectInputServiceImplL.correctionConfirmA010");
        }
        
        BeanUtils.copyProperties(ifaMarginNewOrderCorrectInputA010DtoResponse, dtoReq);
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // １．利用者の口座に対する権限チェックを行う。
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setAccountNumber(accountNumber);
        inputFct001Dto.setButenCode(butenCode);
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        // 対象顧客参照権限有無＝"0"（権限なし） エラー
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        // 取引停止フラグ＝"1"（取引停止口座） エラー
        if (TRADE_SUSPEND_FLAG.equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        // ２．取引コース媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(butenCode);
        inputFct003Dto.setAccountNumber(accountNumber);
        inputFct003Dto.setProductCd(PRODUCT_CODE_01);
        inputFct003Dto.setTradeCd(dtoReq.getTradeCd());
        OutputFct003Dto outputFct003Dto;
        outputFct003Dto = fct003.doCheck(inputFct003Dto);
        for (OutputFct003Dto.MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
            if (!StringUtils.equals(INTERMEDIARY_VALUE_1, mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                        IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                                new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                        MSG_DISPLAY_TARGET_TRADE_2) }));
                return dtoRes;
            }
        }

        // ３．ユーザ共通情報 .CCSログイン用IDが未設定(Null または空文字）の場合：取引不可エラーを返す
        UserAccount ua = IfaCommonUtil.getUserAccount();
        if (StringUtils.isEmpty(ua.getCcsUserId())) {
            DataList<IfaMarginNewOrderCorrectInputA010DtoResponse> response = IfaCommonUtil.createDataList(
                    List.of(),
                    ErrorLevel.FATAL,
                    ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_CCSID_UNREGISTERED)
            );

            return response;
        }

        // ４．口座の取引制限チェックを行う。
        InputFct021Dto inputFct021Dto = new InputFct021Dto();
        inputFct021Dto.setButenCode(butenCode);
        inputFct021Dto.setAccountNumber(accountNumber);
        inputFct021Dto.setProductCd(PRODUCT_CODE_01);
        inputFct021Dto.setTradeCd(dtoReq.getTradeCd());
        inputFct021Dto.setCountryCd(COUNTRYCD_99);
        inputFct021Dto.setCurrencyCode(CURRENCYCODE_999);
        inputFct021Dto.setTradeRestrictChkMarket(dtoReq.getMarket());
        inputFct021Dto.setPaymentLimit(dtoReq.getPaymentDeadline());
        
        OutputFct021Dto outputFct021Dto = fct021.doCheck(inputFct021Dto);
        // 注意情報エラー有無="1"（あり）：エラーを返す。
        if (outputFct021Dto.getNoteInfoErrFlag().equals(Fct021.EXIST)) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_NOTICEERRORCHECK,
                    IfaCommonUtil.getMessage(ERRORS_CMN_NOTICEERRORCHECK, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_2) }));
            return dtoRes;
        }
        // お知らせエラー有無="1"（あり）：エラーを返す。
        if (outputFct021Dto.getNoteLimitErrFlag().equals(Fct021.EXIST)) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_INFORMATIONCHECK,
                    IfaCommonUtil.getMessage(ERRORS_INFORMATIONCHECK));
            return dtoRes;
        }
        // 注意情報アラート有無="1"（あり）：注意情報アラートを格納する。
        if (outputFct021Dto.getNoteInfoAlertFlag().equals(Fct021.EXIST)) {
            ifaMarginNewOrderCorrectInputA010DtoResponse.setNoticeInfoAlert(IfaCommonUtil
                    .getMessage(WARNINGS_CMN_NOTICEWARNINGCHECK, new String[] {
                            codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_2) }));
        }
        // お知らせアラート有無="1"（あり）：お知らせアラートを格納する。
        if (outputFct021Dto.getNoteLimitAlertFlag().equals(Fct021.EXIST)) {
            ifaMarginNewOrderCorrectInputA010DtoResponse
                    .setNoticeAlert(IfaCommonUtil.getMessage(WARNINGS_CMN_INFORMATIONCHECK));
        }
        // ５．銘柄の取引注意情報を取得する。
        InputFct027Dto inputFct027Dto = new InputFct027Dto();
        inputFct027Dto.setBrandCode(dtoReq.getBrandCode());
        OutputFct027Dto outputFct027Dto = fct027.getData(inputFct027Dto);
        if (outputFct027Dto.getRegKbn().equals(REGKBN_1)) {
            ifaMarginNewOrderCorrectInputA010DtoResponse
                    .setTradeNoticeInfoBrandMsg(IfaCommonUtil.getMessage(WARNINGS_DMS_INFORMATIONCHECK));
        }
        // レスポンス.銘柄名をセットする。
        ifaMarginNewOrderCorrectInputA010DtoResponse.setBrandName(outputFct027Dto.getBrandName());
        // ６．訂正有無をチェックする。
        // 注文種別=通常/逆指値　の場合
        if (ORDER_CLASS_1.equals(dtoReq.getOrderKind())) {
            String before = new StringBuilder()
                    .append(dtoReq.getSasinariHouhou())
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceSasinariHouhou()) ? dtoReq.getTriggerPrice() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceSasinariHouhou()) ? dtoReq.getGyakusasiHouhou() : "")
                    .append((EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceSasinariHouhou()) || EXECUTE_METHOD_1.equals(dtoReq.getGyakusasiHouhou())) ? dtoReq.getPrice() : "")
                    .toString();
            String after = new StringBuilder()
                    .append(dtoReq.getCorrectBeforePriceSasinariHouhou())
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceSasinariHouhou()) ? dtoReq.getCorrectBeforePriceTriggerPrice() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceSasinariHouhou()) ? dtoReq.getCorrectBeforePriceGyakusasiHouhou() : "")
                    .append((EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceSasinariHouhou()) || EXECUTE_METHOD_1.equals(dtoReq.getGyakusasiHouhou())) ? dtoReq.getCorrectBeforePricePrice() : "")
                    .toString();
            if (StringUtils.equals(before, after)) {
                // 市場訂正が行われていない場合、「注文内容に変更がないため、注文訂正を行いません。」のエラーメッセージを表示する
                if (StringUtil.isNullOrEmpty(dtoReq.getAfterCorrectMarket()) || dtoReq.getAfterCorrectMarket().equals(dtoReq.getMarket()) ) {
                    dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                            ERRORS_CMN_ORDERMODIFY_NOMODIFY, IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
                    return dtoRes;
                // 市場訂正が行われている場合、「市場訂正以外の注文内容に変更がないため、注文訂正を行いません。」のエラーメッセージを表示する
                } else {
                    dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                            ERRORS_CMN_ORDERMODIFY_MARKETONLYMODIFY, IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_MARKETONLYMODIFY));
                    return dtoRes;               
                }
            }
        }
        // 注文種別=OCO　の場合
        if (ORDER_CLASS_2.equals(dtoReq.getOrderKind())) {
            String before = new StringBuilder()
                    .append(dtoReq.getOco1Price())
                    .append(dtoReq.getOco2TriggerPrice())
                    .append(dtoReq.getOco2GyakusasiHouhou())
                    .append(EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceOco2GyakusasiHouhou()) ? dtoReq.getOco2Price() : "")
                    .toString();
            String after = new StringBuilder()
                    .append(dtoReq.getCorrectBeforePriceOco1Price())
                    .append(dtoReq.getCorrectBeforePriceOco2TriggerPrice())
                    .append(dtoReq.getCorrectBeforePriceOco2GyakusasiHouhou())
                    .append(EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceOco2GyakusasiHouhou()) ? dtoReq.getCorrectBeforePriceOco2Price() : "")
                    .toString();
            if (StringUtils.equals(before, after)) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_ORDERMODIFY_NOMODIFY, IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
                return dtoRes;
            }
        }
        // 注文種別=IFD　の場合
        if (ORDER_CLASS_3.equals(dtoReq.getOrderKind())) {
            String before = new StringBuilder()
                    .append(dtoReq.getIfd1SasinariHouhou())
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) ? dtoReq.getIfd1TriggerPrice() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) ? dtoReq.getIfd1GyakusasiHouhou() : "")
                    .append((EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) || EXECUTE_METHOD_1.equals(dtoReq.getIfd1GyakusasiHouhou())) ? dtoReq.getIfd1Price() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd2SasinariHouhou()) ? dtoReq.getIfd2TriggerPrice() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd2SasinariHouhou()) ? dtoReq.getIfd2GyakusasiHouhou() : "")
                    .append((EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceIfd2SasinariHouhou()) || EXECUTE_METHOD_1.equals(dtoReq.getIfd2GyakusasiHouhou())) ? dtoReq.getIfd2Price() : "")
                    .toString();
            String after = new StringBuilder()
                    .append(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou())
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) ? dtoReq.getCorrectBeforePriceIfd1TriggerPrice() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) ? dtoReq.getCorrectBeforePriceIfd1GyakusasiHouhou() : "")
                    .append((EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) || EXECUTE_METHOD_1.equals(dtoReq.getIfd1GyakusasiHouhou())) ? dtoReq.getCorrectBeforePriceIfd1Price() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd2SasinariHouhou()) ? dtoReq.getCorrectBeforePriceIfd2TriggerPrice() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd2SasinariHouhou()) ? dtoReq.getCorrectBeforePriceIfd2GyakusasiHouhou() : "")
                    .append((EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceIfd2SasinariHouhou()) || EXECUTE_METHOD_1.equals(dtoReq.getIfd2GyakusasiHouhou())) ? dtoReq.getCorrectBeforePriceIfd2Price() : "")
                    .toString();
            if (StringUtils.equals(before, after)) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_ORDERMODIFY_NOMODIFY, IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
                return dtoRes;
            }
        }
        
        // 注文種別=IFDOCO　の場合
        if (ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            String before = new StringBuilder()
                    .append(dtoReq.getIfd1SasinariHouhou())
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) ? dtoReq.getIfd1TriggerPrice() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) ? dtoReq.getIfd1GyakusasiHouhou() : "")
                    .append((EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) || EXECUTE_METHOD_1.equals(dtoReq.getIfd1GyakusasiHouhou())) ? dtoReq.getIfd1Price() : "")
                    .append(dtoReq.getOco1Price())
                    .append(dtoReq.getOco2TriggerPrice())
                    .append(dtoReq.getOco2GyakusasiHouhou())
                    .append(EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceOco2GyakusasiHouhou()) ? dtoReq.getOco2Price() : "")
                    .toString();
            String after = new StringBuilder()
                    .append(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou())
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) ? dtoReq.getCorrectBeforePriceIfd1TriggerPrice() : "")
                    .append(EXECUTE_METHOD_3.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) ? dtoReq.getCorrectBeforePriceIfd1GyakusasiHouhou() : "")
                    .append((EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceIfd1SasinariHouhou()) || EXECUTE_METHOD_1.equals(dtoReq.getIfd1GyakusasiHouhou())) ? dtoReq.getCorrectBeforePriceIfd1Price() : "")
                    .append(dtoReq.getCorrectBeforePriceOco1Price())
                    .append(dtoReq.getCorrectBeforePriceOco2TriggerPrice())
                    .append(dtoReq.getCorrectBeforePriceOco2GyakusasiHouhou())
                    .append(EXECUTE_METHOD_1.equals(dtoReq.getCorrectBeforePriceOco2GyakusasiHouhou()) ? dtoReq.getCorrectBeforePriceOco2Price() : "")
                    .toString();
            if (StringUtils.equals(before, after)) {
                dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_ORDERMODIFY_NOMODIFY, IfaCommonUtil.getMessage(ERRORS_CMN_ORDERMODIFY_NOMODIFY));
                return dtoRes;
            }
        }
        
        // ７．注文種別に応じて、注文確認を行う。
        // 通常/逆指値、OCO注文： 国内株式・信用訂正注文確認APIの呼出し。
        if (ORDER_CLASS_1.equals(dtoReq.getOrderKind()) || ORDER_CLASS_2.equals(dtoReq.getOrderKind())) {
            // API001
            // 国内株式・信用訂正注文確認
            EstimateStockModifyOrderOutData api001Res = callApi001(dtoReq, customerCommon);
            // エラーハンドリング
            if (apiErrorUtil.isError(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage())) {
                return apiErrorUtil.createDataList(new ArrayList<>(), ERRORS_CMN_ORDEREXECUTIONMODIFY_FAILED);
            }
            //市場.訂正後市場=当社優先市場／SOR　かつ　訂正SOR注文結果区分="△"：SOR対象外の場合、エラーとする。
            if (SELECT_MARKET_SOR.equals(dtoReq.getAfterCorrectMarket()) && SPACE.equals(api001Res.getSorModifyStatus())) {
                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_CMN_SORMODIFY_NOMODIFY,
                        IfaCommonUtil.getMessage(ERRORS_CMN_SORMODIFY_NOMODIFY));
            }
            
            // レスポンス設定
            // 空売り規制抵触メッセージ
            if (CodeEnum.containsCode(api001Res.getCode())) {
                ifaMarginNewOrderCorrectInputA010DtoResponse.setShortSellingRegulationsMessage(SHORT_SELLING_MESSAGE);
            }
            // 種別
            ifaMarginNewOrderCorrectInputA010DtoResponse.setShubetu(api001Res.getShubetu());
            // エラーコード
            ifaMarginNewOrderCorrectInputA010DtoResponse.setCode(api001Res.getCode());
            // エラーメッセージ
            ifaMarginNewOrderCorrectInputA010DtoResponse.setErrMessage(api001Res.getMessage());
            // 約定予定日
            ifaMarginNewOrderCorrectInputA010DtoResponse.setContractDate(api001Res.getTradeDate());
            // 受渡予定日
            ifaMarginNewOrderCorrectInputA010DtoResponse.setDeliveryDate(api001Res.getSettlementDate());
            // 受注日時
            ifaMarginNewOrderCorrectInputA010DtoResponse
                    .setOrderDayTime(api001Res.getAcceptDate() + SPACE + api001Res.getAcceptTime());
            // 訂正後建玉余力
            ifaMarginNewOrderCorrectInputA010DtoResponse
                    .setAfterCorrectPositionPower(api001Res.getMarginCapabilityAfter());
            // 有効期限変更フラグ
            ifaMarginNewOrderCorrectInputA010DtoResponse.setYukoKigenChangeFlag(api001Res.getModLimitFlg());
            // 有効期限
            ifaMarginNewOrderCorrectInputA010DtoResponse.setYukoKigen(api001Res.getModLimit());
            // 訂正SOR注文結果区分
            ifaMarginNewOrderCorrectInputA010DtoResponse.setCorrectSorOrderResultClassification(api001Res.getSorModifyStatus()); 
            
        }
        // IFD、IFDOCO注文： 自動注文(国内株式)訂正注文確認APIの呼出し。
        if (ORDER_CLASS_3.equals(dtoReq.getOrderKind()) || ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            // API002
            // 自動注文(国内株式)訂正注文確認
            EstimateStockModifyOrderAutoOutData api002Res = callApi002(dtoReq, customerCommon);
            // エラーハンドリング
            if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
                return apiErrorUtil.createDataList(new ArrayList<>(), ERRORS_CMN_ORDEREXECUTIONMODIFY_FAILED);
            }
            
            // レスポンス設定
            // 空売り規制抵触メッセージ
            if (CodeEnum.containsCode(api002Res.getCode())) {
                ifaMarginNewOrderCorrectInputA010DtoResponse.setShortSellingRegulationsMessage(SHORT_SELLING_MESSAGE);
            }
            // 種別
            ifaMarginNewOrderCorrectInputA010DtoResponse.setShubetu(api002Res.getShubetu());
            // エラーコード
            ifaMarginNewOrderCorrectInputA010DtoResponse.setCode(api002Res.getCode());
            // エラーメッセージ
            ifaMarginNewOrderCorrectInputA010DtoResponse.setErrMessage(api002Res.getMessage());
            // 約定予定日
            ifaMarginNewOrderCorrectInputA010DtoResponse.setContractDate(api002Res.getTradeDate());
            // 受渡予定日
            ifaMarginNewOrderCorrectInputA010DtoResponse.setDeliveryDate(api002Res.getSettlementDate());
            // 受注日時
            ifaMarginNewOrderCorrectInputA010DtoResponse
                    .setOrderDayTime(api002Res.getAcceptDate() + SPACE + api002Res.getAcceptTime());
            // 訂正後建玉余力
            ifaMarginNewOrderCorrectInputA010DtoResponse
                    .setAfterCorrectPositionPower(api002Res.getMarginCapabilityAfter());
            // 有効期限変更フラグ
            ifaMarginNewOrderCorrectInputA010DtoResponse.setYukoKigenChangeFlag(api002Res.getModLimitFlg());
            // 有効期限
            ifaMarginNewOrderCorrectInputA010DtoResponse.setYukoKigen(api002Res.getModLimit());
        }
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
        return dtoRes;
    }
    
    private EstimateStockModifyOrderAutoOutData callApi002(IfaMarginNewOrderCorrectInputA010DtoRequest dtoReq,
            CustomerCommon customerCommon) throws Exception {
        
        EstimateStockModifyOrderAutoOutData result = new EstimateStockModifyOrderAutoOutData();
        EstimateStockModifyOrderAutoInData inData = new EstimateStockModifyOrderAutoInData();
        
        //inDataの設定
        //部店コード3桁
        inData.setButenCd(customerCommon.getButenCode());
        //口座番号7桁
        inData.setKozaNo(StringUtil.fillLeft(customerCommon.getAccountNumber(), CHAR_ZERO, 7));
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
        inData.setOrderNo(dtoReq.getEcOrderNo());
        //注文数量
        inData.setQuantity(StringUtil.fillLeft(dtoReq.getQuantity(), CHAR_ZERO, 8));
        //指成区分
        inData.setSasinariKbn(dtoReq.getIfd1SasinariJyouken());
        //指値
        if (EXECUTE_METHOD_1.equals(dtoReq.getIfd1SasinariHouhou())) {
            inData.setPrice(StringUtil.fillLeft(dtoReq.getIfd1Price(), CHAR_ZERO, 10));
        } else if (EXECUTE_METHOD_3.equals(dtoReq.getIfd1SasinariHouhou())) {
            if (EXECUTE_METHOD_1.equals(dtoReq.getIfd1GyakusasiHouhou())) {
                inData.setPrice(StringUtil.fillLeft(dtoReq.getIfd1Price(), CHAR_ZERO, 10));
            } else {
                inData.setPrice(API_REQUEST_PRICE);
            }
        } else {
            inData.setPrice(API_REQUEST_PRICE);
        }
        //受付経路区分
        inData.setCallcenterKbn(API_REQUEST_CALLCENTER_KBN);
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        //ユーザーID
        inData.setUserId(ua.getCcsUserId());
        //手数料区分
        inData.setComId(dtoReq.getComId());
        //余力チェック区分
        inData.setCheckId(API_REQUEST_CHECK_ID);
        //RBE注文種別
        if (dtoReq.getWorkingStatus()) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);

        } else {
            if (EXECUTE_METHOD_1.equals(dtoReq.getIfd1SasinariHouhou())
                    || EXECUTE_METHOD_2.equals(dtoReq.getIfd1SasinariHouhou())) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_SPACE3);
            } else if (EXECUTE_METHOD_3.equals(dtoReq.getIfd1SasinariHouhou())) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
            }
        }
        
        //トリガ発動ゾーン
        if (!dtoReq.getWorkingStatus()) {
            if (EXECUTE_METHOD_3.equals(dtoReq.getIfd1SasinariHouhou())) {
                if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                    inData.setTriggerZone(TRIGGER_ZONE_OVER);
                } else if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                    inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                }
            } else {
                inData.setTriggerZone(TRIGGER_ZONE_NONE);
            }
        } else {
            inData.setTriggerZone(TRIGGER_ZONE_NONE);
        }
        //トリガ値段
        if (!dtoReq.getWorkingStatus()) {
            if (EXECUTE_METHOD_3.equals(dtoReq.getIfd1SasinariHouhou())) {
                inData.setTriggerPrice(StringUtil.fillLeft(dtoReq.getIfd1TriggerPrice(), CHAR_ZERO, 10));
            } else {
                inData.setTriggerPrice(API_TRIGGER_PRICE);
            }
        } else {
            inData.setTriggerPrice(API_TRIGGER_PRICE);
        }
        //OCO指成区分
        inData.setOcoSasinariKbn(API_OCO_SASINARI_KBN_NONE);
        
        //OCO指値
        inData.setOcoPrice(API_OCO_PRICE_NONE);
        
        //自動注文種別
        inData.setAutoOrderKind(API002_AUTO_ORDER_KIND);
        //DONE指成区分
        if (ORDER_CLASS_3.equals(dtoReq.getOrderKind())) {
            inData.setDoneSasinariKbn(dtoReq.getIfd2SasinariJyouken());
        } else if (ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            inData.setDoneSasinariKbn(dtoReq.getOco1SasinariJyouken());
        }
        //DONE指値（訂正値段）
        if (ORDER_CLASS_3.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_1.equals(dtoReq.getIfd2SasinariHouhou())) {
                inData.setDonePrice(StringUtil.fillLeft(dtoReq.getIfd2Price(), CHAR_ZERO, 10));
            } else if (EXECUTE_METHOD_3.equals(dtoReq.getIfd2SasinariHouhou())) {
                if (EXECUTE_METHOD_1.equals(dtoReq.getIfd2GyakusasiHouhou())) {
                    inData.setDonePrice(StringUtil.fillLeft(dtoReq.getIfd2Price(), CHAR_ZERO, 10));
                } else {
                    inData.setDonePrice(API002_DONE_PRICE_EX);
                }
            }
        } else if (ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_1.equals(dtoReq.getOco1SasinariHouhou())) {
                inData.setDonePrice(StringUtil.fillLeft(dtoReq.getOco1Price(), CHAR_ZERO, 10));
            } else {
                inData.setDonePrice(API002_DONE_PRICE_EX);
            }
        } else {
            inData.setDonePrice(API002_DONE_PRICE_EX);
        }
        //DONERBE注文種別
        if (ORDER_CLASS_3.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_1.equals(dtoReq.getIfd2SasinariHouhou())
                    || EXECUTE_METHOD_2.equals(dtoReq.getIfd2SasinariHouhou())) {
                inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_SPACE3);
            } else if (EXECUTE_METHOD_3.equals(dtoReq.getIfd2SasinariHouhou())) {
                inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_SLO);
            }
        } else if (ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_OCO);
        } else {
            inData.setDoneRbeOrderKind(DONE_RBE_ORDER_KIND_SPACE3);
        }
        //DONEトリガ発動ゾーン
        if (ORDER_CLASS_3.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_3.equals(dtoReq.getIfd2SasinariHouhou())) {
                if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                    inData.setDoneTriggerZone(TRIGGER_ZONE_UNDER);
                } else if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                    inData.setDoneTriggerZone(TRIGGER_ZONE_OVER);
                }
            } else {
                inData.setDoneTriggerZone(TRIGGER_ZONE_NONE);
            }
        } else if (ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            if (DOMESTIC_STOCK_TRADE_CLASS_BUY.equals(dtoReq.getTradeCd())) {
                inData.setDoneTriggerZone(TRIGGER_ZONE_UNDER);
            } else if (DOMESTIC_STOCK_TRADE_CLASS_SELL.equals(dtoReq.getTradeCd())) {
                inData.setDoneTriggerZone(TRIGGER_ZONE_OVER);
            }
        } else {
            inData.setDoneTriggerZone(TRIGGER_ZONE_NONE);
        }
        //DONEトリガ値段（訂正値段）
        if (ORDER_CLASS_3.equals(dtoReq.getOrderKind())) {
            if (EXECUTE_METHOD_3.equals(dtoReq.getIfd2SasinariHouhou())) {
                inData.setDoneTriggerPrice(StringUtil.fillLeft(dtoReq.getIfd2TriggerPrice(), CHAR_ZERO, 10));
            } else {
                inData.setDoneTriggerPrice(API002_DONE_TRIGGER_PRICE);
            }
        } else if (ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            inData.setDoneTriggerPrice(StringUtil.fillLeft(dtoReq.getOco2TriggerPrice(), CHAR_ZERO, 10));
        } else {
            inData.setDoneTriggerPrice(API002_DONE_TRIGGER_PRICE);
        }
        //DONEOCO指成区分
        if (ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            inData.setDoneOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
        } else {
            inData.setDoneOcoSasinariKbn(API002_DONE_OCO_SASINARI_KBN);
        }
        //DONEOCO指値（訂正値段）
        if (ORDER_CLASS_4.equals(dtoReq.getOrderKind())) {
            inData.setDoneOcoPrice(StringUtil.fillLeft(dtoReq.getOco2Price(), CHAR_ZERO, 10));
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
    
    private EstimateStockModifyOrderOutData callApi001(IfaMarginNewOrderCorrectInputA010DtoRequest dtoReq,
            CustomerCommon customerCommon) throws Exception {
        
        EstimateStockModifyOrderOutData result = new EstimateStockModifyOrderOutData();
        EstimateStockModifyOrderInData inData = new EstimateStockModifyOrderInData();
        
        //inDataの設定
        //部店コード3桁
        inData.setButenCd(customerCommon.getButenCode());
        //口座番号7桁
        inData.setKozaNo(StringUtil.fillLeft(customerCommon.getAccountNumber(), CHAR_ZERO, 7));
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
        inData.setOrderNo(dtoReq.getEcOrderNo());
        //注文数量
        inData.setQuantity(StringUtil.fillLeft(dtoReq.getQuantity(), CHAR_ZERO, 8));
        //指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_1)) {
            inData.setSasinariKbn(dtoReq.getSasinariJyouken());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_2)) {
            inData.setSasinariKbn(dtoReq.getOco1SasinariJyouken());
        }
        //指値
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_1)) {
            inData.setPrice(StringUtil.fillLeft(dtoReq.getPrice(), CHAR_ZERO, 10));
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_2)) {
            inData.setPrice(StringUtil.fillLeft(dtoReq.getOco1Price(), CHAR_ZERO, 10));
        } else {
            inData.setPrice(API_REQUEST_PRICE);
        }
        //受付経路区分
        inData.setCallcenterKbn(API_REQUEST_CALLCENTER_KBN);
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        //ユーザーID
        inData.setUserId(ua.getCcsUserId());
        //手数料区分
        inData.setComId(dtoReq.getComId());
        //余力チェック区分
        inData.setCheckId(API_REQUEST_CHECK_ID);
        //RBE注文種別
        if (dtoReq.getWorkingStatus()) {
            inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);

        } else {
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_1)) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_1)
                        || StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_2)) {
                    inData.setRbeOrderKind(RBE_ORDER_KIND_SPACE3);
                } else if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_3)) {
                    inData.setRbeOrderKind(RBE_ORDER_KIND_SLO);
                }
            } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_2)) {
                inData.setRbeOrderKind(RBE_ORDER_KIND_OCO);
            }
        }
        //トリガ発動ゾーン
        if (!dtoReq.getWorkingStatus()) {
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_1)) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_3)) {
                    if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_BUY)) {
                        inData.setTriggerZone(TRIGGER_ZONE_OVER);
                    } else if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SELL)) {
                        inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                    }
                } else {
                    inData.setTriggerZone(TRIGGER_ZONE_NONE);
                }
            } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_2)) {
                if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_BUY)) {
                    inData.setTriggerZone(TRIGGER_ZONE_OVER);
                } else if (StringUtils.equals(dtoReq.getTradeCd(), DOMESTIC_STOCK_TRADE_CLASS_SELL)) {
                    inData.setTriggerZone(TRIGGER_ZONE_UNDER);
                }
            } else {
                inData.setTriggerZone(TRIGGER_ZONE_NONE);
            }
        } else {
            inData.setTriggerZone(TRIGGER_ZONE_NONE);
        }
        
        //トリガ値段
        if (!dtoReq.getWorkingStatus()) {
            if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_1)) {
                if (StringUtils.equals(dtoReq.getSasinariHouhou(), EXECUTE_METHOD_3)) {
                    inData.setTriggerPrice(StringUtil.fillLeft(dtoReq.getTriggerPrice(), CHAR_ZERO, 10));
                } else {
                    inData.setTriggerPrice(API_TRIGGER_PRICE);
                }
            } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_2)) {
                inData.setTriggerPrice(StringUtil.fillLeft(dtoReq.getOco2TriggerPrice(), CHAR_ZERO, 10));
            } else {
                inData.setTriggerPrice(API_TRIGGER_PRICE);
            }
        } else {
            inData.setTriggerPrice(API_TRIGGER_PRICE);
        }
        //OCO指成区分
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_1)) {
            inData.setOcoSasinariKbn(API_OCO_SASINARI_KBN_NONE);
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_2)) {
            inData.setOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
        }
        //OCO指値
        if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_1)) {
            inData.setOcoPrice(API_OCO_PRICE_NONE);
        } else if (StringUtils.equals(dtoReq.getOrderKind(), ORDER_CLASS_2)) {
            inData.setOcoPrice(StringUtil.fillLeft(dtoReq.getOco2Price(), CHAR_ZERO, 10));
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
    
    private enum CodeEnum {
        
        W09734("W09734"), W09034("W09034"), W09081("W09081"), W09082("W09082"), W09083("W09083"), W09084("W09084"),
        W090C6("W090c6"), W09085("W09085"), W09086("W09086"), W09087("W09087"), W090C7("W090c7"), W09088("W09088"),
        W09089("W09089"), W090C0("W090c0"), W090C8("W090c8"), W090C9("W090c9"), W090A0("W090a0"), W090C1("W090c1"),
        W090C2("W090c2"), W090A1("W090a1"), W090C3("W090c3"), W090A2("W090a2"), W090A3("W090a3"), W090C4("W090c4"),
        W090A4("W090A4"), W090A5("W090a5"), W090A6("W090a6"), W090C5("W090c5"), W090A7("W090a7");
        
        private final String code;
        
        CodeEnum(String code) {
            
            this.code = code;
        }
        
        public String getCode() {
            
            return code;
        }
        
        public static Boolean containsCode(String codeToCheck) {
            
            for (CodeEnum codeEnum : CodeEnum.values()) {
                if (codeEnum.getCode().equals(codeToCheck)) {
                    return true;
                }
            }
            return false;
        }
    }
}

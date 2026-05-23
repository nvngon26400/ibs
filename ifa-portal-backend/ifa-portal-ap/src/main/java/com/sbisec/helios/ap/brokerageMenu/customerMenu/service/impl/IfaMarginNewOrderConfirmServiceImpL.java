
package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaMarginNewOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmErrorModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.IfaMarginNewOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.IfaMarginNewOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMarginNewOrderConfirmService;
import com.sbisec.helios.ap.common.enums.DomesticMarginAccountType;
import com.sbisec.helios.ap.common.enums.DomesticStockTradeClass;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ExecuteMethod;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.enums.OrderClass;
import com.sbisec.helios.ap.common.enums.SelectMarket;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.MarginPlaceOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.MarginPlaceOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.MarginPlaceOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.StockPlaceOrderAutoIn;
import jp.co.sbisec.pcenter.dto.yanap.StockPlaceOrderAutoInData;
import jp.co.sbisec.pcenter.dto.yanap.StockPlaceOrderAutoOutData;

/**
 * 画面ID：SUB0202_0212-01_2
 * 画面名：信用新規注文確認
 * @author 卞
 *
 * 2023/10/13 新規作成
 */
@Component(value = "cmpIfaMarginNewOrderConfirmService")
public class IfaMarginNewOrderConfirmServiceImpL implements IfaMarginNewOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginNewOrderConfirmServiceImpL.class);
    
    /**
     * FCT001 利用者顧客参照権限チェック
     */
    @Autowired
    private Fct001 fct001;
    
    /**
     * FCT003 取引コース媒介可否チェック
     */
    @Autowired
    private Fct003 fct003;
    
    /**
     * FCT021 取引制限チェック
     */
    @Autowired
    private Fct021 fct021;
    
    /**
     * FCT027 国内株式情報取得
     */
    @Autowired
    private Fct027 fct027;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
    /**
     * DAO
     */
    @Autowired
    private IfaMarginNewOrderConfirmDao dao;
    
    /**
     * APIラッパークラス
     */
    @Autowired
    private ApiWrapper apiWrapper;
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** {0}ができないコースです。 \{0}：区分.対象取引（メッセージ表示用）（区分値：3 ＠表示パターン：1 ）*/
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 信用口座が未開設です。 */
    private static final String ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN = "errors.dms.domesticMarginAccount.notOpen";
    
    /** 当該顧客の{0}に関わる重要な注意情報があるため処理を進めることができません。注意情報欄をご確認ください。 \{0}：区分.対象取引（メッセージ表示用）（区分値：3 ＠表示パターン：1 ）*/
    private static final String ERRORS_CMN_NOTICEERRORCHECK = "errors.cmn.noticeErrorCheck";
    
    /** 「重要なお知らせ」が未確認です。重要なお知らせ取引制限欄や注意情報欄を確認してください。*/
    private static final String ERRORS_INFORMATIONCHECK = "errors.informationCheck";
    
    /** 確認が必要なアラートが新たに発生しました。注文入力画面に戻り再度注文確認を行ってください。 */
    private static final String ERRORS_CMN_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    /** 注文発注前の注文データが登録できないため、注文しませんでした。 */
    private static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** 注文発注後の注文データが更新できませんでした。注文は完了しています。*/
    private static final String WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
    
    /** 注文処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) {0}:APIエラーコード {1}:APIエラーメッセージ*/
    private static final String ERRORS_CMN_ORDEREXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    /** FCT021：通貨コード 設定値 */
    private static final String CURRENCY_CODE_VALUE = "999";
    
    /** FCT021：国籍コード 設定値 */
    private static final String NATIONALITY_CODE_VALUE = "99";
    
    /** FCT003, FCT021：証券金銭種別 設定値 国内株式*/
    private static final String DOMESTICSTOCK = "01";
    
    /** 区分定義.ドメインID_対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引 区分値：3 */
    private static final String MSG_DISPLAY_TARGET_TRADE_KUBUN = "3";
    
    /** 期間条件:当日中 */
    private static final String TODAY = "0";
    
    /** 注意情報アラート確認チェックボックス=OFF */
    private static final String NOTE_LIMIT_ALERT_CHECK_BOX = "0";
    
    /** お知らせアラート確認チェックボックス=OFF */
    private static final String NOTE_INFO_ALERT_CHECK_BOX = "0";
    
    /** 取引注意情報（銘柄）確認チェックボックス=OFF */
    private static final String NOTE_TRADINGCAUTIONINFORMATIO_ALERT_CHECK_BOX = "0";
    
    /** 規制銘柄区分=1:規制銘柄 */
    private static final String REG_KBN = "1";
    
    /** SQL:注文種別（一覧）　"1": 逆指値注文 */
    private static final String ORDER_STATUS_STOP = "1";
    
    /** SQL:注文種別（一覧）　"0": 通常注文 */
    private static final String ORDER_STATUS_NORMAL = "0";
    
    /** SQL:注文種別（一覧）　"2": OCO注文 */
    private static final String ORDER_STATUS_OCO = "2";
    
    /** SQL:注文種別（一覧）　"4":IFD1注文（逆指値） */
    private static final String ORDER_STATUS_IFD1_STOP = "4";
    
    /** SQL:注文種別（一覧）　"3":IFD1注文（通常） */
    private static final String ORDER_STATUS_IFD1_NORMAL = "3";
    
    /** APIステータス（SQL002リクエスト）：正常・ワーニング */
    private static final String API_STATUS_SUCCESS = "0";

    /** APIステータス（SQL002リクエスト）：エラー */
    private static final String API_STATUS_ERROR = "1";
    
    //API設定値(固定値)
    /** API "0":以上 */
    private static final String MORE = "0";
    
    /** API "1":以下 */
    private static final String LESS = "1";
    
    /** API "△"(指定なし) */
    private static final String NOT_SPECIFIED = " ";
    
    /** API 固定値:"△" */
    private static final String SPACE = " ";
    
    //---------------------
    //API001
    //---------------------
    /** API rbe注文種別: SLO */
    private static final String RBE_SHUBETSU = "SLO";
    
    /** API001 リクエスト（トランザクションID）：ALL"0" */
    private static final String API001_REQUEST_TRANSACTION_ID = String.format("%32s", StringUtil.EMPTY_STRING)
            .replace(" ", "0");
    
    /** API001 リクエスト（通番）：ALL"0" */
    private static final String API001_REQUEST_COMMAND_ID_COMMAND_NUM = String.format("%7s", StringUtil.EMPTY_STRING)
            .replace(" ", "0");
    
    /** API001 リクエスト（年月日）：ALL"0" */
    private static final String API001_REQUEST_COMMAND_ID_COMMAND_DATE = String.format("%8s", StringUtil.EMPTY_STRING)
            .replace(" ", "0");
    
    /** API001 リクエスト（アカウントID）：ALL"0" */
    private static final String API001_REQUEST_ORDER_ID_ACCOUNT_ID = String
            .format("%11s", StringUtil.EMPTY_STRING).replace(" ", "0");
    
    /** API001 リクエスト（アカウント毎の連番）：ALL"0" */
    private static final String API001_REQUEST_ORDER_ID_NUMBER = String.format("%7s", StringUtil.EMPTY_STRING)
            .replace(" ", "0");
    
    /** API001 リクエスト（オリジン）:"0"*/
    private static final String API001_REQUEST_ORDER_ID_ORIGIN = "0";
    
    /** API001 リクエスト（受渡方法-信用新規）:"6"*/
    private static final String API001_REQUEST_UKEW_HOHO = "6";
    
    /** API001 リクエスト（非特定預り売買区分）:"△"(：初期値)*/
    private static final String API001_REQUEST_HITOKUTEI_TRADE_KBN = " ";
    
    /** API001 リクエスト（摘要）:ALL"△"*/
    private static final String API001_REQUEST_SUMMARY = String.format("%30s", StringUtil.EMPTY_STRING);
    
    /** API001 リクエスト（決済方法区分）:ALL"△"*/
    private static final String API001_REQUEST_PAYMENT_KBN = " ";
    
    /** API001 リクエスト（決済方法）:ALL"△"*/
    private static final String API001_REQUEST_PAYMENT_METHOD = String.format("%10s", StringUtil.EMPTY_STRING);
    
    /** API001 リクエスト（振込先銀行区分）:ALL"△"*/
    private static final String API001_REQUEST_BANK_KBN = " ";
    
    /** API001 リクエスト（振込先銀行名）:ALL"△"*/
    private static final String API001_REQUEST_BANK_NAME = String.format("%20s", StringUtil.EMPTY_STRING);
    
    /** API001 リクエスト（受付経路区分）:"0"：支店*/
    private static final String API001_REQUEST_CALLCENTER_KBN = "0";
    
    /** API001 リクエスト（ベティング区分）:"△"*/
    private static final String API001_REQUEST_VETTING_KBN = " ";
    
    /** API001 リクエスト（与信チェック用時価）:ALL"0"*/
    private static final String API001_REQUEST_CHECK_PRICE = String.format("%10s", StringUtil.EMPTY_STRING).replace(" ",
            "0");
    
    /** API001 リクエスト（余力チェック区分）:"△"（建玉余力または決済可能数量のチェック要）*/
    private static final String API001_REQUEST_CHECK_ID = " ";
    
    /** API001 リクエスト（IPアドレス）:"ifap"*/
    private static final String API001_REQUEST_IP_ADDRESS = "ifap";
    
    /** API001 リクエスト（新規約定日）:ALL"△"*/
    private static final String API001_REQUEST_OPEN_TRADE_DATE = String.format("%8s", StringUtil.EMPTY_STRING);
    
    /** API001 リクエスト（新規単価）:ALL"△"*/
    private static final String API001_REQUEST_OPEN_PRICE = String.format("%12s", StringUtil.EMPTY_STRING);
    
    /** API001 リクエスト（一般信用売弁済期限年月日区分）:"△"*/
    private static final String API001_REQUEST_IPPAN_MG_PAYMENT_KBN = " ";
    
    /** API001 リクエスト（一般信用売弁済期限年月日数）:"△△"*/
    private static final String API001_REQUEST_IPPAN_MG_PAYMENT_LIMIT = "  ";
    
    /** API001 リクエスト 市場A:当社優先市場／SOR時の設定値 */
    private static final String API001_REQUEST_MARKET_SOR_VALUE = "tse";
    
    /** API001 レスポンス　OCO_PRICE（注文種別=通常/逆指値の場合：ALL"△") */
    private static final String API001_OCO_PRICE_SPACE = String.format("%10s", StringUtil.EMPTY_STRING);
    
    //---------------------
    //API002
    //---------------------
    /** API002 リクエスト（トランザクションID）：ALL"0" */
    private static final String API002_REQUEST_TRANSACTION_ID = String.format("%32s", StringUtil.EMPTY_STRING)
            .replace(" ", "0");
    
    /** API002 リクエスト（通番）：ALL"0" */
    private static final String API002_REQUEST_COMMAND_ID_COMMAND_NUM = String.format("%7s", StringUtil.EMPTY_STRING)
            .replace(" ", "0");
    
    /** API002 リクエスト（年月日）：ALL"0" */
    private static final String API002_REQUEST_COMMAND_ID_COMMAND_DATE = String.format("%8s", StringUtil.EMPTY_STRING)
            .replace(" ", "0");
    
    /** API002 リクエスト（アカウントID）：ALL"0" */
    private static final String API002_REQUEST_ORDER_ID_ACCOUNT_ID = String
            .format("%11s", StringUtil.EMPTY_STRING).replace(" ", "0");
    
    /** API002 リクエスト（アカウント毎の連番）：ALL"0" */
    private static final String API002_REQUEST_ORDER_ID_NUMBER = String.format("%7s", StringUtil.EMPTY_STRING)
            .replace(" ", "0");
    
    /** API002 リクエスト（オリジン）:ALL"0" 長さ1*/
    private static final String API002_REQUEST_ORDER_ID_ORIGIN = "0";
    
    /** API002 リクエスト（受渡方法-信用新規）:"6"(信用新規)*/
    private static final String API002_REQUEST_UKEW_HOHO = "6";
    
    /** API002 リクエスト（非特定預り売買区分）:"△"（信用新規)*/
    private static final String API002_REQUEST_HITOKUTEI_TRADE_KBN = " ";
    
    /** API002 リクエスト（摘要）:ALL"△"*/
    private static final String API002_REQUEST_SUMMARY = String.format("%30s", StringUtil.EMPTY_STRING);
    
    /** API002 リクエスト（決済方法区分）:"△"*/
    private static final String API002_REQUEST_PAYMENT_KBN = " ";
    
    /** API002 リクエスト（決済方法）:ALL"△"*/
    private static final String API002_REQUEST_PAYMENT_METHOD = String.format("%10s", StringUtil.EMPTY_STRING);
    
    /** API002 リクエスト（振込先銀行区分）:"△"*/
    private static final String API002_REQUEST_BANK_KBN = " ";
    
    /** API002 リクエスト（振込先銀行名）:'ALL"△"*/
    private static final String API002_REQUEST_BANK_NAME = String.format("%20s", StringUtil.EMPTY_STRING);
    
    /** API002 リクエスト（受付経路区分）:"0"（支店）*/
    private static final String API002_REQUEST_CALLCENTER_KBN = "0";
    
    /** API002 リクエスト（ベティング区分）:"△"*/
    private static final String API002_REQUEST_VETTING_KBN = " ";
    
    /** API002 リクエスト（与信チェック用時価）:ALL"△"*/
    private static final String API002_REQUEST_CHECK_PRICE = String.format("%10s", StringUtil.EMPTY_STRING);
    
    /** API002 リクエスト（余力チェック区分）:'"△"：建玉余力または決済可能数量のチェック要*/
    private static final String API002_REQUEST_CHECK_ID = " ";
    
    /** API002 リクエスト（OCO指成区分）:"△"(指定なし)*/
    private static final String API002_REQUEST_OCO_SASINARI_KBN = " ";
    
    /** API002 リクエスト（OCO指値）:ALL"△"*/
    private static final String API002_REQUEST_OCO_PRICE = String.format("%10s", StringUtil.EMPTY_STRING);
    
    /** API002 リクエスト（自動注文種別）:'"IF△△":IFD親注文*/
    private static final String API002_REQUEST_AUTO_ORDER_KIND = "IF  ";
    
    /** API002 リクエスト（IPアドレス）:"ifap"*/
    private static final String API002_REQUEST_IP_ADDRESS = "ifap";
    
    /** API002 リクエスト（引継ぎID）:"△"（未使用)*/
    private static final String API002_REQUEST_TRANS_ID = " ";
    
    /** API002 リクエスト（媒介）:"△"*/
    private static final String API002_REQUEST_INTERMEDIARY = " ";
    
    /** API002 リクエスト（新規約定日）:ALL"△"*/
    private static final String API002_REQUEST_OPEN_TRADE_DATE = String.format("%8s", StringUtil.EMPTY_STRING);
    
    /** API002 リクエスト（新規単価）:ALL"△"*/
    private static final String API002_REQUEST_OPEN_PRICE = String.format("%12s", StringUtil.EMPTY_STRING);
    
    /** API002 リクエスト（一般信用売弁済期限年月日区分）:"△"*/
    private static final String API002_REQUEST_IPPAN_MG_PAYMENT_KBN = " ";
    
    /** API002 リクエスト（一般信用売弁済期限年月日数）:ALL"△" 長さ2*/
    private static final String API002_REQUEST_IPPAN_MG_PAYMENT_LIMIT = "  ";
    
    /** API002 DONERBE注文種別 SLO(逆指値注文) */
    private static final String DONERBE_ORDER_SHUBETSU_IFD_STOP = "SLO";
    
    /** API002 DONERBE注文種別 OCO(DONE注文がOCO) */
    private static final String DONERBE_ORDER_SHUBETSU_IFDOCO = "OCO";
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：brokerageMenu.customerMenuA001aRequestDto
     * Dto レスポンス：brokerageMenu.customerMenuA001aResponseDto
     * model リクエスト：IfaMarginNewOrderConfirmSql001RequestModel
     * model レスポンス：IfaMarginNewOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderConfirmA001aResponseDto> orderPlacementA001a(
            IfaMarginNewOrderConfirmA001aRequestDto dtoReq) throws Exception {
        
        DataList<IfaMarginNewOrderConfirmA001aResponseDto> dtoRes = new DataList<IfaMarginNewOrderConfirmA001aResponseDto>();
        List<IfaMarginNewOrderConfirmA001aResponseDto> resDto = new ArrayList<IfaMarginNewOrderConfirmA001aResponseDto>();
        IfaMarginNewOrderConfirmA001aResponseDto response = new IfaMarginNewOrderConfirmA001aResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaMarginNewOrderConfirmServiceImplL.orderPlacementA001a");
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 共通関数のエラーメッセージを格納
        IfaMarginNewOrderConfirmErrorModel error = new IfaMarginNewOrderConfirmErrorModel();
        // FCT001
        if (!callFCT001(cc.getButenCode(), cc.getAccountNumber(), error)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        // FCT003
        if (!callFCT003(cc.getButenCode(), cc.getAccountNumber(), dtoReq.getTradeCd(), error)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        //国内信用口座開設状況のチェック
        if (StringUtils.equals(cc.getDomesticMarginAccountType(), DomesticMarginAccountType.CASH_ACCOUNT.getId())) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL,
            ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN, IfaCommonUtil.getMessage(ERRORS_CMN_DOMESTICMARGINACCOUNT_NOTOPEN));
        }
        
        // FCT021
        if (!callFCT021(cc.getButenCode(), cc.getAccountNumber(), dtoReq.getTradeCd(), dtoReq.getNoteInfoCheckbox(),
                dtoReq.getNoteLimitCheck(), dtoReq.getMarket(), dtoReq.getMarginTradeTypeText(), error)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        
        // FCT027
        OutputFct027Dto fct027Result = callFCT027(dtoReq.getBrandCode());
        if (!StringUtil.isNullOrEmpty(error.getErrorMessage())) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, error.getErrorCode(), error.getErrorMessage());
        }
        if (StringUtils.equals(fct027Result.getRegKbn(), REG_KBN)) {
            if (StringUtil.isNullOrEmpty(dtoReq.getTradingCautionInformation()) || StringUtils
                    .equals(dtoReq.getTradingCautionInformation(), NOTE_TRADINGCAUTIONINFORMATIO_ALERT_CHECK_BOX)) {
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL,
                ERRORS_CMN_INFORMATION_OCCURS, IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
            }
        }
        
        //SQL001からレスポンス
        String ifaOrderNo = null;
        try {
            ifaOrderNo = orderRegister(dtoReq, cc);
        } catch (Exception e) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL,
            ERRORS_FRS_PREORDEREXECUTION_FAILED, IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
        }
        
        //レスポンス項目の設定
        response.setIfaOrderNo(ifaOrderNo);
        
        resDto.add(response);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                null);
        return dtoRes;
    }
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：brokerageMenu.customerMenuA001bRequestDto
     * Dto レスポンス：brokerageMenu.customerMenuA001bResponseDto
     * model リクエスト：IfaMarginNewOrderConfirmSql002RequestModel
     * model レスポンス：IfaMarginNewOrderConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderConfirmA001bResponseDto> orderPlacementA001b(
            IfaMarginNewOrderConfirmA001bRequestDto dtoReq) throws Exception {
    	// APIエラーハンドラ
    	ApiErrorUtil apiutil = new ApiErrorUtil();
        
        DataList<IfaMarginNewOrderConfirmA001bResponseDto> dtoRes = new DataList<IfaMarginNewOrderConfirmA001bResponseDto>();
        List<IfaMarginNewOrderConfirmA001bResponseDto> resDto = new ArrayList<IfaMarginNewOrderConfirmA001bResponseDto>();
        IfaMarginNewOrderConfirmA001bResponseDto response = new IfaMarginNewOrderConfirmA001bResponseDto();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaMarginNewOrderConfirmServiceImplL.orderPlacementA001b");
        // API001
        MarginPlaceOrderOutData api001Res = new MarginPlaceOrderOutData();
        
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            try {
                api001Res = callAPI001(dtoReq);
                if (api001Res != null) {
                    apiutil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(),api001Res.getMessage());
                    setApi001Result(response, api001Res, dtoReq.getTradeCd());
                }
            } catch (Exception ae) {
            	// システムエラーの場合、DBに受注日を登録してエラーレスポンスを返す
                IfaMarginNewOrderConfirmSql002RequestModel sql002Req = new IfaMarginNewOrderConfirmSql002RequestModel();
                UserAccount ua = IfaCommonUtil.getUserAccount();
                sql002Req.setUpdateUser(ua.getUserId());
                sql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                
                try {
                    int insSql002Res = dao.updateIfaMarginNewOrderConfirmSql002b(sql002Req);

                } finally {
                    dtoRes = IfaCommonUtil.createDataList(
                            new ArrayList<>(),
                            ErrorLevel.FATAL,
                            ERRORS_CMN_ORDEREXECUTION_FAILED,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTION_FAILED)
                    );

                    return dtoRes;
                }
                
            } 

        }
        
        //API002
        StockPlaceOrderAutoOutData api002Res = new StockPlaceOrderAutoOutData();
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            try {
                api002Res = callAPI002(dtoReq);
                if (api002Res != null) {
                    apiutil.checkApiResponse(api002Res.getShubetu(), api002Res.getCode(),api002Res.getMessage());
                    setApi002Result(response, api002Res, dtoReq.getTradeCd());
                }
            } catch (Exception ae) {
                // システムエラーの場合、DBに受注日を登録してエラーレスポンスを返す
                IfaMarginNewOrderConfirmSql002RequestModel sql002Req = new IfaMarginNewOrderConfirmSql002RequestModel();
                UserAccount ua = IfaCommonUtil.getUserAccount();
                sql002Req.setUpdateUser(ua.getUserId());
                sql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
                
                try {
                    int insSql002Res = dao.updateIfaMarginNewOrderConfirmSql002b(sql002Req);

                } finally {
                    dtoRes = IfaCommonUtil.createDataList(
                            new ArrayList<>(),
                            ErrorLevel.FATAL,
                            ERRORS_CMN_ORDEREXECUTION_FAILED,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTION_FAILED)
                    );

                    return dtoRes;
                }
            } 
        }
        
        //SQL002
        try {
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                    || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
                updateOrderInfoForAPI002(api002Res, dtoReq, apiutil);
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                    || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
                updateOrderInfoForAPI001(api001Res, dtoReq, apiutil);
            }
            
        } catch (Exception e) {
            LOGGER.debug("SQL002 error.", e);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.WARNING, WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED,
                    IfaCommonUtil.getMessage(WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED));
        }
        
        //レスポンス設定
        /*銘柄コード*/
        response.setBrandCode(dtoReq.getBrandCode());
        /*市場*/
        response.setMarket(dtoReq.getMarket());
        /*取引種別*/
        response.setTradeCd(dtoReq.getTradeCd());
        /*受注数量*/
        response.setOrderQuantity(dtoReq.getOrderQuantity());
        /*期間.期間条件*/
        response.setPeriodRadio(dtoReq.getPeriodRadio());
        /*期間.日付（全角半角）*/
        response.setLimit(dtoReq.getLimit());
        /*注文種別*/
        response.setOrderKind(dtoReq.getOrderKind());
        /*通常/逆指値.執行方法*/
        response.setSasinariHouhou(dtoReq.getSasinariHouhou());
        /*通常/逆指値.執行条件*/
        response.setSasinariJyouken(dtoReq.getSasinariJyouken());
        /*通常/逆指値.発火条件価格（逆指値）（数値(整数)）*/
        response.setTriggerPrice(dtoReq.getTriggerPrice());
        /*通常/逆指値.執行方法（逆指値）*/
        response.setGyakusasiHouhou(dtoReq.getGyakusasiHouhou());
        /*通常/逆指値.注文単価*/
        response.setPrice(dtoReq.getPrice());
        /*OCO1.執行方法*/
        response.setOco1OrderExecuteMethodText(dtoReq.getOco1OrderExecuteMethodText());
        /*OCO1.執行条件*/
        response.setOco1SasinariJyouken(dtoReq.getOco1SasinariJyouken());
        /*OCO1.注文単価*/
        response.setOco1Price(dtoReq.getOco1Price());
        /*OCO2.発火条件価格（逆指値）*/
        response.setOco2TriggerPrice(dtoReq.getOco2TriggerPrice());
        /*OCO2.執行方法（逆指値）*/
        response.setOco2GyakusasiHouhou(dtoReq.getOco2GyakusasiHouhou());
        /*OCO2.執行条件（逆指値）*/
        response.setOco2GyakusasiJyouken(dtoReq.getOco2GyakusasiJyouken());
        /* OCO2.注文単価*/
        response.setOco2Price(dtoReq.getOco2Price());
        /*IFD1.執行方法*/
        response.setIfd1SasinariHouhou(dtoReq.getIfd1SasinariHouhou());
        /*IFD1.執行条件*/
        response.setIfd1SasinariJyouken(dtoReq.getIfd1SasinariJyouken());
        /*IFD1.発火条件価格（逆指値）*/
        response.setIfd1TriggerPrice(dtoReq.getIfd1TriggerPrice());
        /*IFD1.執行方法（逆指値）*/
        response.setIfd1GyakusasiHouhou(dtoReq.getIfd1GyakusasiHouhou());
        /*IFD1.注文単価*/
        response.setIfd1Price(dtoReq.getIfd1Price());
        /*IFD2.期間.期間条件*/
        response.setIfd2PeriodDate(dtoReq.getIfd2PeriodDate());
        /*IFD2.期間.日付*/
        response.setIfd2Limit(dtoReq.getIfd2Limit());
        /*IFD2.執行方法*/
        response.setIfd2SasinariHouhou(dtoReq.getIfd2SasinariHouhou());
        /*IFD2.執行条件*/
        response.setIfd2SasinariJyouken(dtoReq.getIfd2SasinariJyouken());
        /*IFD2.発火条件価格（逆指値）*/
        response.setIfd2TriggerPrice(dtoReq.getIfd2TriggerPrice());
        /*IFD2.執行方法（逆指値）*/
        response.setIfd2OrderExecuteMethodText(dtoReq.getIfd2OrderExecuteMethodText());
        /*IFD2.注文単価*/
        response.setIfd2Price(dtoReq.getIfd2Price());
        /*信用取引区分*/
        response.setMarginTradeTypeText(dtoReq.getMarginTradeTypeText());
        /*手数料区分*/
        response.setTesuuryouKbn(dtoReq.getTesuuryouKbn());
        /*勧誘区分*/
        response.setKanyuKbn(dtoReq.getKanyuKbn());
        /*受注方法*/
        response.setReceiveOrderTypeName(dtoReq.getReceiveOrderTypeName());
        /*確認項目.インサイダー確認*/
        response.setCheckInsider(dtoReq.getCheckInsider());
        /*確認項目.SOR確認*/
        response.setCheckSor(dtoReq.getCheckSor());
        /*アラート内容確認.取引注意情報（銘柄）確認*/
        response.setTradingCautionInformation(dtoReq.getTradingCautionInformation());
        /*アラート内容確認.注意情報アラート確認*/
        response.setNoteInfoCheckbox(dtoReq.getNoteInfoCheckbox());
        /*アラート内容確認.お知らせアラート確認*/
        response.setNoteLimitCheck(dtoReq.getNoteLimitCheck());
        /* アラート内容確認.内部者エラー確認*/
        response.setInsiderErrorConfirmationCheckbox(dtoReq.getInsiderErrorConfirmationCheckbox());
        /*アラート内容確認.空売り規制の抵触確認*/
        response.setShortSellingRegulationsCheckbox(dtoReq.getShortSellingRegulationsCheckbox());
        /*注意情報アラート（全角半角）*/
        response.setNoticeInfoAlert(CollectionUtils.isEmpty(dtoReq.getNoticeInfoAlert()) ? null:dtoReq.getNoticeInfoAlert().get(0));
        /*お知らせアラート（全角半角）*/
        response.setNoticeAlert(CollectionUtils.isEmpty(dtoReq.getNoticeAlert())? null:dtoReq.getNoticeAlert().get(0));
        /*内部者確認メッセージ*/
        response.setInsiderErrorMsg(CollectionUtils.isEmpty(dtoReq.getInsiderErrorMsg())? null:dtoReq.getInsiderErrorMsg().get(0));
        /*空売り規制の抵触確認メッセージ(空売り規制抵触メッセージ)*/
        response.setShortSellingRegulationsMessage(CollectionUtils.isEmpty(dtoReq.getShortSellingRegulationsMessage())? null:dtoReq.getShortSellingRegulationsMessage().get(0));
        /*取引注意情報（銘柄）メッセージ（全角半角）*/
        response.setTradeNoticeInfoBrandMsg(CollectionUtils.isEmpty(dtoReq.getTradeNoticeInfoBrandMsg())? null:dtoReq.getTradeNoticeInfoBrandMsg().get(0));
        /*銘柄名（全角半角）*/
        response.setBrandName(dtoReq.getBrandName());
        //適用金利
        response.setApplicableInterestRate(dtoReq.getApplicableInterestRate());
        //適用貸株料
        response.setApplicableStockLendingFees(dtoReq.getApplicableStockLendingFees());
        
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            setApi001Result(response, api001Res, dtoReq.getTradeCd());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            setApi002Result(response, api002Res, dtoReq.getTradeCd());
        }
        
        resDto.add(response);
        dtoRes = apiutil.createDataList(resDto,ERRORS_CMN_ORDEREXECUTION_FAILED);
        return dtoRes;
    }
    
    /**
     * FCT001チェック
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return チェック結果
     */
    private boolean callFCT001(String butenCode, String accountNumber, IfaMarginNewOrderConfirmErrorModel error) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
                error.setErrorCode(ERRORS_BUTENACCOUNTNOTEXIST);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
                return false;
            }
            if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
                error.setErrorCode(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE));
                return false;
            }
        }
        return true;
    }
    
    /**
     * FCT003チェック
     * 
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param tradeCd 取引種別
     * @return チェック結果
     */
    private boolean callFCT003(String butenCode, String accountNumber, String tradeCd, IfaMarginNewOrderConfirmErrorModel error) {
        
        InputFct003Dto input = new InputFct003Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        input.setProductCd(DOMESTICSTOCK);
        if (!StringUtil.isNullOrEmpty(tradeCd)) {
            input.setTradeCd(tradeCd);
        }
        
        OutputFct003Dto output = fct003.doCheck(input);
        boolean isExistsTradeFlag = false;
        // リスト情報無し、または媒介可取引有無=無しならNG
        if (output == null || CollectionUtils.isEmpty(output.getMediateProprietyList())
                || !StringUtils.equals(output.getMediateAbleTradeFlag(), MediateAbleTradeFlag.ARI.getId())) {
            isExistsTradeFlag = false;
        } else {
            // 媒介可否リスト.取引種別が　"信用新規買",または媒介可否リスト.取引種別が　"信用新規売"が存在したらOK
            isExistsTradeFlag = output.getMediateProprietyList().stream()
                    .filter(m -> StringUtils.equals(m.getMediatePropriety(), MediateAbleTradeFlag.ARI.getId())
                            && (StringUtils.equals(m.getTradeClass(), DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())
                                    || StringUtils.equals(m.getTradeClass(),
                                            DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())))
                    .findFirst().isPresent();
        }
        // NGの場合はエラーメッセージを設定
        if (!isExistsTradeFlag) {
            // 区分.対象取引（メッセージ表示用）（区分値：3　＠表示パターン：1　）を取得
            error.setErrorCode(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE);
            error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] {
                    codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) }));
        }
        
        return true;
    }
    
    /**
     * FCT021
     * 
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @param tradeCd 取引種別
     * @param noteInfoCheckbox アラート内容確認.注意情報アラート確認
     * @param noteLimitCheck アラート内容確認.お知らせアラート確認
     * @return fct021出力結果
     */
    private boolean callFCT021(String butenCode, String accountNumber, String tradeCd, String noteInfoCheckbox,
            String noteLimitCheck, String tradeRestrictChkMarket, String paymentLimit, IfaMarginNewOrderConfirmErrorModel error) throws Exception {
        
        InputFct021Dto input = new InputFct021Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        input.setTradeCd(tradeCd);
        input.setProductCd(Fct021.DOMESTIC_STOCK);
        input.setCountryCd(NATIONALITY_CODE_VALUE);
        input.setCurrencyCode(CURRENCY_CODE_VALUE);
        if (StringUtils.equals(input.getProductCd(), Fct021.DOMESTIC_STOCK)) {
            input.setTradeRestrictChkMarket(tradeRestrictChkMarket);
            input.setPaymentLimit(paymentLimit);
        }
        OutputFct021Dto output = fct021.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getNoteInfoErrFlag(), Fct021.EXIST)) {
                error.setErrorCode(ERRORS_CMN_NOTICEERRORCHECK);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_NOTICEERRORCHECK, new String[] {
                        codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_KUBUN) }));
                return false;
            }
            if (StringUtils.equals(output.getNoteLimitErrFlag(), Fct021.EXIST)) {
                error.setErrorCode(ERRORS_INFORMATIONCHECK);
                error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_INFORMATIONCHECK));
                return false;
            }
            if (StringUtils.equals(output.getNoteInfoAlertFlag(), Fct021.EXIST)) {
                if (StringUtils.equals(noteInfoCheckbox, NOTE_LIMIT_ALERT_CHECK_BOX)
                        || StringUtil.isNullOrEmpty(noteInfoCheckbox)) {
                    error.setErrorCode(ERRORS_CMN_INFORMATION_OCCURS);
                    error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                    return false;
                }
            }
            
            if (StringUtils.equals(output.getNoteLimitAlertFlag(), Fct021.EXIST)) {
                if (StringUtils.equals(noteLimitCheck, NOTE_INFO_ALERT_CHECK_BOX)
                        || StringUtil.isNullOrEmpty(noteLimitCheck)) {
                    error.setErrorCode(ERRORS_CMN_INFORMATION_OCCURS);
                    error.setErrorMessage(IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * FCT027呼び出し
     * 
     * @param brandCode 銘柄コード
     * @return fct027出力結果
     */
    private OutputFct027Dto callFCT027(String brandCode) {
        
        InputFct027Dto input = new InputFct027Dto();
        input.setBrandCode(brandCode);
        OutputFct027Dto output = fct027.getData(input);
        
        return output;
        
    }
    
    /**
     * 発注前の注文登録(SQL001の発行)
     * 
     * @param dtoReq A001aリクエストDTO
     * @param cc 顧客共通情報
     * @return ifaorderNo
     * @throws Exception
     */
    private String orderRegister(IfaMarginNewOrderConfirmA001aRequestDto dtoReq, CustomerCommon cc) throws Exception {
        
        IfaMarginNewOrderConfirmSql001RequestModel sql001Req = new IfaMarginNewOrderConfirmSql001RequestModel();
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        String ifaorderNo = dao.selectIfaMarginNewOrderConfirmSql003();
        
        //IFA注文番号
        sql001Req.setIfaOrderNo(ifaorderNo);
        //部店コード
        sql001Req.setButenCode(cc.getButenCode());
        //口座番号
        sql001Req.setAccountNumber(cc.getAccountNumber());
        //顧客ID
        sql001Req.setKokyakuId(cc.getCustomerCode());
        //特定口座区分
        sql001Req.setTokuteiKouzaKbn(cc.getSpecificAccountType());
        //銘柄コード
        sql001Req.setBrandCode(dtoReq.getBrandCode());
        //市場
        sql001Req.setMarket(dtoReq.getMarket());
        //取引種別
        sql001Req.setTradeCd(dtoReq.getTradeCd());
        //数量
        sql001Req.setQuantity(dtoReq.getOrderQuantity());
        //有効期限
        sql001Req.setYukoKigen(getLimit(dtoReq.getPeriodRadio(), dtoReq.getLimit()));
        
        //弁済期限
        sql001Req.setPaymentDeadline(dtoReq.getMarginTradeTypeText());
        //注文種別
        sql001Req.setOrderKind(dtoReq.getOrderKind());
        //注文種別 （一覧）
        String orderStatusListOrderClass = StringUtil.EMPTY_STRING;
        /* 注文周別=通常/逆指値　の場合 */
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())) {
            /* 通常/逆指値.執行方法=逆指値 の場合 */
            if (StringUtils.equals(dtoReq.getSasinariHouhou(), ExecuteMethod.STOP.getId())) {
                /*　"1": 逆指値注文 */
                orderStatusListOrderClass = ORDER_STATUS_STOP;
            } else {
                /* その以外 "0": 通常注文 */
                orderStatusListOrderClass = ORDER_STATUS_NORMAL;
            }
            /*　注文種別=OCO の場合 */
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            /* "2" : OCO注文 */
            orderStatusListOrderClass = ORDER_STATUS_OCO;
            /*　注文種別=IFD　または　IFDOCO の場合 */
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            /* IFD1.執行方法=逆指値　の場合 */
            if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), ExecuteMethod.STOP.getId())) {
                /* "4":IFD1注文（逆指値） */
                orderStatusListOrderClass = ORDER_STATUS_IFD1_STOP;
            } else {
                /* 3:IFD1注文（通常） */
                orderStatusListOrderClass = ORDER_STATUS_IFD1_NORMAL;
            }
        }
        sql001Req.setOrderStatusListOrderClass(orderStatusListOrderClass);
        
        //勧誘区分
        sql001Req.setKanyuKbn(dtoReq.getKanyuKbn());
        //受注方法
        sql001Req.setReceiveOrder(dtoReq.getReceiveOrderTypeName());
        //確認項目.インサイダー確認
        sql001Req.setCheckInsider(dtoReq.getCheckInsider());
        //確認項目.SOR確認
        sql001Req.setCheckSor(dtoReq.getCheckSor());
        
        //ユーザーID
        sql001Req.setUserId(ua.getCcsUserId());
        //手数料区分
        sql001Req.setTesuuryouKbn(cc.getCustomerAttribute());
        //自動注文種別
        String autoOrderClass = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            autoOrderClass = API002_REQUEST_AUTO_ORDER_KIND;
        }
        sql001Req.setAutoOrderClass(autoOrderClass);
        
        //RBE注文種別
        String rbeChumonShubetsu = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            rbeChumonShubetsu = getRbeOrderKindApi002(dtoReq.getIfd1SasinariHouhou());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            rbeChumonShubetsu = getRbeOrderKindApi001(dtoReq.getOrderKind(), dtoReq.getSasinariHouhou());
        }
        sql001Req.setRbeChumonShubetsu(rbeChumonShubetsu);
        
        //指成区分
        String sashinariKbn = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            sashinariKbn = dtoReq.getIfd1SasinariJyouken();
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            sashinariKbn = getSasinarikbnApi001(dtoReq.getOrderKind(), dtoReq.getSasinariJyouken(),
                    dtoReq.getOco1SasinariJyouken());
        }
        sql001Req.setSashinariKbn(sashinariKbn);
        //指値
        String sashine = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            sashine = getPriceApi002(dtoReq.getIfd1SasinariHouhou(), dtoReq.getIfd1Price(),
                    dtoReq.getIfd1GyakusasiHouhou());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            sashine = getPriceApi001(dtoReq.getOrderKind(), dtoReq.getSasinariHouhou(), dtoReq.getPrice(),
                    dtoReq.getGyakusasiHouhou(), dtoReq.getOco1Price());
        }
        sql001Req.setSashine(sashine);
        //トリガ発動ゾーン
        String triggerZone = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            triggerZone = getTriggerZoneApi002(dtoReq.getIfd1SasinariHouhou(), dtoReq.getTradeCd());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            triggerZone = getTriggerZoneApi001(dtoReq.getOrderKind(), dtoReq.getSasinariHouhou(), dtoReq.getTradeCd());
        }
        sql001Req.setTriggerZone(triggerZone);
        //トリガ値段
        String triggerNedan = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            triggerNedan = getTriggerPriceApi002(dtoReq.getIfd1SasinariHouhou(), dtoReq.getIfd1TriggerPrice());
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            triggerNedan = getTriggerPriceApi001(dtoReq.getOrderKind(), dtoReq.getSasinariHouhou(),
                    dtoReq.getTriggerPrice(), dtoReq.getOco2TriggerPrice());
        }
        sql001Req.setTriggerNedan(triggerNedan);
        //OCO指成区分
        String ocoSasinariKbn = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            ocoSasinariKbn = API002_REQUEST_OCO_SASINARI_KBN;
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            ocoSasinariKbn = getOcoSasinariKbnApi001(dtoReq.getOrderKind(), dtoReq.getOco2GyakusasiJyouken());
        }
        sql001Req.setOcoSasinariKbn(ocoSasinariKbn);
        //OCO指値
        String ocoSashine = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            ocoSashine = API002_REQUEST_OCO_PRICE;
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.NORMAL.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.OCO.getId())) {
            ocoSashine = getOcoPriceApi001(dtoReq.getOrderKind(), dtoReq.getOco2Price());
        }
        
        if (StringUtils.equals(ocoSashine, API002_REQUEST_OCO_PRICE) || StringUtils.equals(ocoSashine, API001_OCO_PRICE_SPACE)) {
            sql001Req.setOcoSashine(null);
        } else {
            sql001Req.setOcoSashine(ocoSashine);
        }
        
        //DONE指成区分
        String doneSasinariKbn = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneSasinariKbn = getDoneSasinariKbnApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariJyouken(),
                    dtoReq.getOco1SasinariJyouken());
        }
        sql001Req.setDoneSasinariKbn(doneSasinariKbn);
        //DONE指値
        String doneSashine = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneSashine = getDonePriceApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariHouhou(),
                    dtoReq.getIfd2Price(), dtoReq.getIfd2OrderExecuteMethodText(),
                    dtoReq.getOco1OrderExecuteMethodText(), dtoReq.getOco1Price());
        }
        sql001Req.setDoneSashine(doneSashine);
        //DONERBE注文種別
        String doneRbeOrderKind = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneRbeOrderKind = getDoneRbeOrderKindApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariHouhou());
        }
        sql001Req.setDoneRbeOrderKind(doneRbeOrderKind);
        //DONEトリガ発動ゾーン
        String doneTriggerZone = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneTriggerZone = getDoneTriggerZoneApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariHouhou(),
                    dtoReq.getOco2GyakusasiHouhou(), dtoReq.getTradeCd());
        }
        sql001Req.setDoneTriggerZone(doneTriggerZone);
        //DONEトリガ値段
        String doneTriggerNedan = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneTriggerNedan = getDoneTriggerPriceApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariHouhou(),
                    dtoReq.getIfd2TriggerPrice(), dtoReq.getOco2GyakusasiHouhou(), dtoReq.getOco2TriggerPrice());
        }
        sql001Req.setDoneTriggerNedan(doneTriggerNedan);
        //DONEOCO指成区分
        String doneOcoSasinariKbn = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneOcoSasinariKbn = getDoneOcoSasinariKbnApi002(dtoReq.getOrderKind(), dtoReq.getOco2GyakusasiJyouken());
        }
        sql001Req.setDoneOcoSasinariKbn(doneOcoSasinariKbn);
        //DONEOCO指値
        String doneOcoSashine = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneOcoSashine = getDoneOcoPriceApi002(dtoReq.getOrderKind(), dtoReq.getOco2Price());
        }
        sql001Req.setDoneOcoSashine(doneOcoSashine);
        //DONE有効期限
        String doneYuukouKigen = null;
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(dtoReq.getOrderKind(), OrderClass.IFDOCO.getId())) {
            doneYuukouKigen = getDoneLimitApi002(dtoReq.getIfd2PeriodDate(), dtoReq.getIfd2Limit());
        }
        sql001Req.setDoneYuukouKigen(doneYuukouKigen);
        //譲渡益税区分
        sql001Req.setJoutoekizeiKbn(" ");
        //仲介業者コード
        sql001Req.setBrokerCode(cc.getBrokerCode());
        //仲介業者営業員コード
        sql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());
        //作成者
        sql001Req.setCreateUser(ua.getUserId());
        //更新者
        sql001Req.setUpdateUser(ua.getUserId());
        
        int insSql001Res = dao.insertIfaMarginNewOrderConfirmSql001(sql001Req);
        if (insSql001Res != 1) {
            throw new Exception();
        }
        return ifaorderNo;
    }
    
    /**
     * API002呼び出したとき、発注後の注文登録(SQL002の発行)
     * 
     * @param api002Response API002出力データ
     * @param dtoReq A001bリクエストDTO
     * @throws Exception
     */
    private void updateOrderInfoForAPI002(StockPlaceOrderAutoOutData api002Response,
            IfaMarginNewOrderConfirmA001bRequestDto dtoReq, ApiErrorUtil apiutil) throws Exception {
        
        IfaMarginNewOrderConfirmSql002RequestModel sql002Req = new IfaMarginNewOrderConfirmSql002RequestModel();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        if (apiutil.isSuccess() || apiutil.isWarn()) {
            // APIステータスを更新
            sql002Req.setIsApiError(API_STATUS_SUCCESS);
            //商品区分
            sql002Req.setSecurityType(StringUtils.trim(api002Response.getOrderType()));
            //EC受注番号
            sql002Req.setEcOrderNo(StringUtils.trim(api002Response.getOrderNum()));
            //受注時刻
            sql002Req.setAcceptTime(StringUtils.trim(api002Response.getAcceptTime()));
            //種別
            sql002Req.setShubetu(api002Response.getShubetu());
            //エラーコード
            sql002Req.setCode(StringUtils.trim(api002Response.getCode()));
            //エラーメッセージ
            sql002Req.setErrMessage(StringUtils.trim(api002Response.getMessage()));
            //与信チェック用時価
            sql002Req.setEstimatePrice(StringUtils.trim(api002Response.getEstimatePrice()));
            //約定金額（概算）
            sql002Req.setAmount(StringUtils.trim(api002Response.getAmount()));
            //手数料（概算）
            sql002Req.setCommission(StringUtils.trim(api002Response.getCommission()));
            //消費税
            sql002Req.setConsumptionTax(StringUtils.trim(api002Response.getConsumptionTax()));
            //譲渡益税
            sql002Req.setCapitalGainTax(StringUtils.trim(api002Response.getCapitalGainTax()));
            //精算金額
            sql002Req.setNetAmount(StringUtils.trim(api002Response.getNetAmount()));
            //諸経費
            sql002Req.setCost(StringUtils.trim(api002Response.getCost()));
            //約定予定日
            sql002Req.setContractDate(StringUtils.trim(api002Response.getTradeDate()));
            //受渡予定日
            sql002Req.setDeliveryDate(StringUtils.trim(api002Response.getSettlementDate()));
            //受付有効期限
            sql002Req.setAcceptLimit(StringUtils.trim(api002Response.getAcceptLimit()));
            //DONE 受付有効期限
            sql002Req.setDoneAcceptLimit(StringUtils.trim(api002Response.getDoneAcceptLimit()));
            //手数料区分（採用）
            sql002Req.setComIdR(api002Response.getComIdR());
            //売却可能数量
            sql002Req.setAcPosition(StringUtils.trim(api002Response.getAcPosition()));
            //注文後の売却可能数量
            sql002Req.setAcPositionAfter(StringUtils.trim(api002Response.getAcPositionAfter()));
            //買付可能金額
            sql002Req.setAcBalance(StringUtils.trim(api002Response.getAcBalance()));
            //注文後の買付可能金額
            sql002Req.setAcBalanceAfter(StringUtils.trim(api002Response.getAcBalanceAfter()));
            //注文入力市場
            sql002Req.setOrderedMarket(api002Response.getOrderedMarket());
            //取引不足額
            sql002Req.setTradeDeficitAmount(StringUtils.trim(api002Response.getTradeDeficitAmount()));
            //ISA買付可能枠
            sql002Req.setIsaBuyLimit(StringUtils.trim(api002Response.getIsaBuyLimit()));
            //ジュニアNISA振替金額
            sql002Req.setJrnisaTransferAmount(StringUtils.trim(api002Response.getJrnisaTransferAmount()));
            //SOR連携区分
            sql002Req.setSorLinkKbn(api002Response.getSorLinkKbn());
            //決済可能数量
            sql002Req.setUnclosedQuantity(StringUtils.trim(api002Response.getUnclosedQuantity()));
            //注文後の決済可能数量
            sql002Req.setUnclosedQuantityAfter(StringUtils.trim(api002Response.getUnclosedQuantityAfter()));
            //建玉余力
            sql002Req.setMarginCapability(StringUtils.trim(api002Response.getMarginCapability()));
            //注文後の建玉余力
            sql002Req.setMarginCapabilityAfter(StringUtils.trim(api002Response.getMarginCapabilityAfter()));
            //維持率
            sql002Req.setDomesticMarginActualGrntRate(StringUtils.trim(api002Response.getActualGrntRate()));
            //注文後維持率
            sql002Req.setActualGrntRateAfter(StringUtils.trim(api002Response.getActualGrntRateAfter()));
            //適用金利
            sql002Req.setApplicableInterestRate(StringUtils.trim(dtoReq.getApplicableInterestRate()));
            //適用貸株料
            sql002Req.setApplicableStockLendingFees(StringUtils.trim(dtoReq.getApplicableStockLendingFees()));
            //プレミアム料
            sql002Req.setPremium(StringUtils.trim(api002Response.getPremium()));
        } else if (apiutil.isFatal()) {
            // APIステータスを更新
            sql002Req.setIsApiError(API_STATUS_ERROR);
            //種別
            sql002Req.setShubetu(StringUtils.trim(api002Response.getShubetu()));
            //エラーコード
            sql002Req.setCode(StringUtils.trim(api002Response.getCode()));
            //エラーメッセージ
            sql002Req.setErrMessage(StringUtils.trim(api002Response.getMessage()));
        }
        //受注日
        sql002Req.setOrderDate(StringUtils.trim(api002Response.getAcceptDate()));
        //更新者
        sql002Req.setUpdateUser(ua.getUserId());
        sql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
        
        int insSql002Res = dao.updateIfaMarginNewOrderConfirmSql002(sql002Req);
        if (insSql002Res != 1) {
            throw new Exception();
        }
    }
        
    /**
     * API001呼び出したとき、発注後の注文登録(SQL002の発行)
     * 
     * @param api001Response API001出力データ
     * @param dtoReq A001bリクエストDTO
     * @throws Exception
     */
    private void updateOrderInfoForAPI001(MarginPlaceOrderOutData api001Response,
            IfaMarginNewOrderConfirmA001bRequestDto dtoReq, ApiErrorUtil apiutil) throws Exception {
        
        IfaMarginNewOrderConfirmSql002RequestModel sql002Req = new IfaMarginNewOrderConfirmSql002RequestModel();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        if (apiutil.isSuccess() || apiutil.isWarn()) {
            // APIステータスを更新
            sql002Req.setIsApiError(API_STATUS_SUCCESS);
            //商品区分
            sql002Req.setSecurityType(StringUtils.trim(api001Response.getOrderType()));
            //EC受注番号
            sql002Req.setEcOrderNo(StringUtils.trim(api001Response.getOrderNum()));
            //受注時刻
            sql002Req.setAcceptTime(StringUtils.trim(api001Response.getAcceptTime()));
            //種別
            sql002Req.setShubetu(api001Response.getShubetu());
            //エラーコード
            sql002Req.setCode(StringUtils.trim(api001Response.getCode()));
            //エラーメッセージ
            sql002Req.setErrMessage(StringUtils.trim(api001Response.getMessage()));
            //与信チェック用時価
            sql002Req.setEstimatePrice(StringUtils.trim(api001Response.getEstimatePrice()));
            //約定金額（概算）
            sql002Req.setAmount(StringUtils.trim(api001Response.getAmount()));
            //手数料（概算）
            sql002Req.setCommission(StringUtils.trim(api001Response.getCommission()));
            //消費税
            sql002Req.setConsumptionTax(StringUtils.trim(api001Response.getConsumptionTax()));
            //譲渡益税
            sql002Req.setCapitalGainTax(StringUtils.trim(api001Response.getCapitalGainTax()));
            //精算金額
            sql002Req.setNetAmount(StringUtils.trim(api001Response.getNetAmount()));
            //諸経費
            sql002Req.setCost(StringUtils.trim(api001Response.getCost()));
            //約定予定日
            sql002Req.setContractDate(StringUtils.trim(api001Response.getTradeDate()));
            //受渡予定日
            sql002Req.setDeliveryDate(StringUtils.trim(api001Response.getSettlementDate()));
            //受付有効期限
            sql002Req.setAcceptLimit(StringUtils.trim(api001Response.getAcceptLimit()));
            //DONE 受付有効期限
            sql002Req.setDoneAcceptLimit(null);
            //手数料区分（採用）
            sql002Req.setComIdR(api001Response.getComIdR());
            //売却可能数量
            sql002Req.setAcPosition(null);
            //注文後の売却可能数量
            sql002Req.setAcPositionAfter(null);
            //買付可能金額
            sql002Req.setAcBalance(null);
            //注文後の買付可能金額
            sql002Req.setAcBalanceAfter(null);
            //注文入力市場
            sql002Req.setOrderedMarket(api001Response.getOrderedMarket());
            //取引不足額
            sql002Req.setTradeDeficitAmount(null);
            //ISA買付可能枠
            sql002Req.setIsaBuyLimit(null);
            //ジュニアNISA振替金額
            sql002Req.setJrnisaTransferAmount(null);
            //SOR連携区分
            sql002Req.setSorLinkKbn(api001Response.getSorLinkKbn());
            //決済可能数量
            sql002Req.setUnclosedQuantity(StringUtils.trim(api001Response.getUnclosedQuantity()));
            //注文後の決済可能数量
            sql002Req.setUnclosedQuantityAfter(StringUtils.trim(api001Response.getUnclosedQuantityAfter()));
            //建玉余力
            sql002Req.setMarginCapability(StringUtils.trim(api001Response.getMarginCapability()));
            //注文後の建玉余力
            sql002Req.setMarginCapabilityAfter(StringUtils.trim(api001Response.getMarginCapabilityAfter()));
            //維持率
            sql002Req.setDomesticMarginActualGrntRate(StringUtils.trim(api001Response.getActualGrntRate()));
            //注文後維持率
            sql002Req.setActualGrntRateAfter(StringUtils.trim(api001Response.getActualGrntRateAfter()));
            //適用金利
            sql002Req.setApplicableInterestRate(StringUtils.trim(dtoReq.getApplicableInterestRate()));
            //適用貸株料
            sql002Req.setApplicableStockLendingFees(StringUtils.trim(dtoReq.getApplicableStockLendingFees()));
            //プレミアム料
            sql002Req.setPremium(StringUtils.trim(api001Response.getPremium()));
        } else if (apiutil.isFatal()) {
            // APIステータスを更新
            sql002Req.setIsApiError(API_STATUS_ERROR);
            //種別
            sql002Req.setShubetu(StringUtils.trim(api001Response.getShubetu()));
            //エラーコード
            sql002Req.setCode(StringUtils.trim(api001Response.getCode()));
            //エラーメッセージ
            sql002Req.setErrMessage(StringUtils.trim(api001Response.getMessage()));
        }
        //受注日
        sql002Req.setOrderDate(StringUtils.trim(api001Response.getAcceptDate()));
        //更新者
        sql002Req.setUpdateUser(ua.getUserId());
        sql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
        
        int insSql002Res = dao.updateIfaMarginNewOrderConfirmSql002(sql002Req);
        if (insSql002Res != 1) {
            throw new Exception();
        }
    }
    
    /**
     * API001呼び出し処理
     * 
     * @param dtoReq A001bリクエストDTO
     * @return API出力結果
     * @throws Exception 
     */
    private MarginPlaceOrderOutData callAPI001(IfaMarginNewOrderConfirmA001bRequestDto dtoReq) throws Exception {
        
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        //API001 Response
        MarginPlaceOrderOutData result = new MarginPlaceOrderOutData();
        //API001 Input
        MarginPlaceOrderIn input = new MarginPlaceOrderIn();
        //API001 Request
        MarginPlaceOrderInData inData = new MarginPlaceOrderInData();
        
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        //トランザクションID
        inData.setTransactionId(API001_REQUEST_TRANSACTION_ID);
        //通番
        inData.setCommandNum(API001_REQUEST_COMMAND_ID_COMMAND_NUM);
        //年月日
        inData.setCommandDate(API001_REQUEST_COMMAND_ID_COMMAND_DATE);
        //部店コード3桁 
        inData.setButenCd(cc.getButenCode());
        //口座番号7桁 
        inData.setKozaNo(createApiRequestAccountNo(cc.getAccountNumber()));
        //アカウントID (固定値:ALL"0" ) 
        inData.setAccountId(API001_REQUEST_ORDER_ID_ACCOUNT_ID);
        //アカウント毎の連番 (固定値:ALL"0" 長さ7) 
        inData.setNumber(API001_REQUEST_ORDER_ID_NUMBER);
        //オリジン (固定値:"0") 
        inData.setOrigin(API001_REQUEST_ORDER_ID_ORIGIN);
        //銘柄コード 
        inData.setBrandCd(String.format("%-5s", dtoReq.getBrandCode()));
        //売買区分 
        inData.setTradeKbn(dtoReq.getTradeCd());
        //注文株数 
        inData.setQuantity(String.format("%8s", dtoReq.getOrderQuantity()).replace(" ", "0"));
        //指成区分
        inData.setSasinariKbn(getSasinarikbnApi001(dtoReq.getOrderKind(), dtoReq.getSasinariJyouken(),
                dtoReq.getOco1SasinariJyouken()));
        //指値
        inData.setPrice(getPriceApi001(dtoReq.getOrderKind(), dtoReq.getSasinariHouhou(), dtoReq.getPrice(),
                dtoReq.getGyakusasiHouhou(), dtoReq.getOco1Price()));
        //受渡方法 (固定値:"6"信用新規)
        inData.setUkewHoho(API001_REQUEST_UKEW_HOHO);
        //発注市場
        inData.setMarket(dtoReq.getMarket());
        //譲渡益税区分 (固定値:"△")
        inData.setJoZeiKbn(" ");
        //非特定預かり売買区分 (固定値:"△"初期値)
        inData.setHitokuteiTradeKbn(API001_REQUEST_HITOKUTEI_TRADE_KBN);
        // 弁済期限
        inData.setPaymentLimit(dtoReq.getMarginTradeTypeText());
        //有効期限
        inData.setLimit(getLimit(dtoReq.getPeriodRadio(), dtoReq.getLimit()));
        //摘要 (固定値:ALL"△"長さ30)
        inData.setSummary(API001_REQUEST_SUMMARY);
        //決済方法区分 (固定値:"△")
        inData.setPaymentKbn(API001_REQUEST_PAYMENT_KBN);
        //決済方法 (固定値ALL:"△"長さ10)
        inData.setPaymentMethod(API001_REQUEST_PAYMENT_METHOD);
        //振込先近郊区分 (固定値"△")
        inData.setBankKbn(API001_REQUEST_BANK_KBN);
        //振込先銀行名 (固定値:ALL"△" 長さ20)
        inData.setBankName(API001_REQUEST_BANK_NAME);
        //受付経路区分(固定値:"0":支店)
        inData.setCallcenterKbn(API001_REQUEST_CALLCENTER_KBN);
        //ユーザーID
        inData.setUserId(ua.getCcsUserId());
        //ぺディング区分 (固定値"△")
        inData.setVettingKbn(API001_REQUEST_VETTING_KBN);
        //与信チェック用時価 (固定値:ALL"0" 長さ10)
        inData.setCheckPrice(API001_REQUEST_CHECK_PRICE);
        //手数料区分
        inData.setComId(cc.getCustomerAttribute());
        //与信チェック区分 (固定値:"△"建玉余力または決済可能数量のチェック要)
        inData.setCheckId(API001_REQUEST_CHECK_ID);
        //RBE注文種別
        inData.setRbeOrderKind(getRbeOrderKindApi001(dtoReq.getOrderKind(), dtoReq.getSasinariHouhou()));
        //トリガ発動ゾーン
        inData.setTriggerZone(
                getTriggerZoneApi001(dtoReq.getOrderKind(), dtoReq.getSasinariHouhou(), dtoReq.getTradeCd()));
        //トリガ値段
        inData.setTriggerPrice(getTriggerPriceApi001(dtoReq.getOrderKind(), dtoReq.getSasinariHouhou(),
                dtoReq.getTriggerPrice(), dtoReq.getOco2TriggerPrice()));
        //OCO指成区分
        inData.setOcoSasinariKbn(getOcoSasinariKbnApi001(dtoReq.getOrderKind(), dtoReq.getOco2GyakusasiJyouken()));
        //OCO指値
        inData.setOcoPrice(getOcoPriceApi001(dtoReq.getOrderKind(), dtoReq.getOco2Price()));
        //IPアドレス (固定値:"ifap")
        inData.setIpAddress(String.format("%-39s", API001_REQUEST_IP_ADDRESS));
        //新規約定日 (固定値:ALL"△" 長さ8)
        inData.setOpenTradeDate(API001_REQUEST_OPEN_TRADE_DATE);
        //新規単価 (固定値:ALL"△" 長さ12)
        inData.setOpenPrice(API001_REQUEST_OPEN_PRICE);
        //一般信用売弁済期限年月日区分 (固定値:"△")
        inData.setIppanMgPaymentKbn(API001_REQUEST_IPPAN_MG_PAYMENT_KBN);
        //一般信用売弁済期限年月日数 (固定値:"△△")
        inData.setIppanMgPaymentLimit(API001_REQUEST_IPPAN_MG_PAYMENT_LIMIT);
        // SOR受注時板乗せ市場区分
        inData.setSorLastMarket(getSorLastMarket(dtoReq.getMarket()));
        input.setIndata(inData);
        
        // NRI_APIから国内株式注文確認(NEO)情報を取得する。
        result = apiWrapper.marginPlaceOrder(input);
        
        return result;
    }
    
    /**
     * API002呼び出し処理
     * 
     * @param dtoReq A001bリクエストDTO
     * @return API出力結果
     * @throws Exception 
     */
    private StockPlaceOrderAutoOutData callAPI002(IfaMarginNewOrderConfirmA001bRequestDto dtoReq) throws Exception {
        
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        StockPlaceOrderAutoOutData result = new StockPlaceOrderAutoOutData();
        StockPlaceOrderAutoIn input = new StockPlaceOrderAutoIn();
        StockPlaceOrderAutoInData inData = new StockPlaceOrderAutoInData();
        
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        //トランザクションID
        inData.setTransactionId(API002_REQUEST_TRANSACTION_ID);
        //通番
        inData.setCommandNum(API002_REQUEST_COMMAND_ID_COMMAND_NUM);
        //年月日
        inData.setCommandDate(API002_REQUEST_COMMAND_ID_COMMAND_DATE);
        //部店コード3桁
        inData.setButenCd(cc.getButenCode());
        //口座番号7桁
        inData.setKozaNo(createApiRequestAccountNo(cc.getAccountNumber()));
        //アカウントID (固定値:"")
        inData.setAccountId(API002_REQUEST_ORDER_ID_ACCOUNT_ID);
        //アカウント毎の連番 (固定値:"")
        inData.setNumber(API002_REQUEST_ORDER_ID_NUMBER);
        //オリジン (固定値:"")
        inData.setOrigin(API002_REQUEST_ORDER_ID_ORIGIN);
        //銘柄コード
        inData.setBrandCd(String.format("%-5s", dtoReq.getBrandCode()));
        //売買区分
        inData.setTradeKbn(dtoReq.getTradeCd());
        //注文株数
        inData.setQuantity(String.format("%8s", dtoReq.getOrderQuantity()).replace(" ", "0"));
        //指成区分
        inData.setSasinariKbn(dtoReq.getIfd1SasinariJyouken());
        //指値
        inData.setPrice(
                getPriceApi002(dtoReq.getIfd1SasinariHouhou(), dtoReq.getIfd1Price(), dtoReq.getIfd1GyakusasiHouhou()));
        //受渡方法 (固定値:"6"信用新規)
        inData.setUkewHoho(API002_REQUEST_UKEW_HOHO);
        //発注市場
        inData.setMarket(dtoReq.getMarket());
        //譲渡益税区分 (固定値:"△")
        inData.setJoZeiKbn(" ");
        //非特定預かり売買区分 (固定値:"△"初期値)
        inData.setHitokuteiTradeKbn(API002_REQUEST_HITOKUTEI_TRADE_KBN);
        // 弁済期限
        inData.setPaymentLimit(dtoReq.getMarginTradeTypeText());
        //有効期限
        inData.setLimit(getLimit(dtoReq.getPeriodRadio(), dtoReq.getLimit()));
        //摘要 (固定値:ALL"△"長さ30)
        inData.setSummary(API002_REQUEST_SUMMARY);
        //決済方法区分 (固定値:"△")
        inData.setPaymentKbn(API002_REQUEST_PAYMENT_KBN);
        //決済方法 (固定値ALL:"△"長さ10)
        inData.setPaymentMethod(API002_REQUEST_PAYMENT_METHOD);
        //振込先近郊区分 (固定値"△")
        inData.setBankKbn(API002_REQUEST_BANK_KBN);
        //振込先銀行名 (固定値:ALL"△" 長さ20)
        inData.setBankName(API002_REQUEST_BANK_NAME);
        //受付経路区分(固定値:"0":支店)
        inData.setCallcenterKbn(API002_REQUEST_CALLCENTER_KBN);
        //ユーザーID
        inData.setUserId(ua.getCcsUserId());
        //ぺディング区分 (固定値"△")
        inData.setVettingKbn(API002_REQUEST_VETTING_KBN);
        //与信チェック用時価 (固定値:ALL"0" 長さ10)
        inData.setCheckPrice(API002_REQUEST_CHECK_PRICE);
        //手数料区分
        inData.setComId(cc.getCustomerAttribute());
        //与信チェック区分 (固定値:"△"建玉余力または決済可能数量のチェック要)
        inData.setCheckId(API002_REQUEST_CHECK_ID);
        //RBE注文種別
        inData.setRbeOrderKind(getRbeOrderKindApi002(dtoReq.getIfd1SasinariHouhou()));
        //トリガ発動ゾーン
        inData.setTriggerZone(getTriggerZoneApi002(dtoReq.getIfd1SasinariHouhou(), dtoReq.getTradeCd()));
        //トリガ値段
        inData.setTriggerPrice(getTriggerPriceApi002(dtoReq.getIfd1SasinariHouhou(), dtoReq.getIfd1TriggerPrice()));
        //OCO指成区分 (固定値:"△"(指定無し))
        inData.setOcoSasinariKbn(API002_REQUEST_OCO_SASINARI_KBN);
        //OCO指値 (固定値:ALL"△" 長さ10)
        inData.setOcoPrice(API002_REQUEST_OCO_PRICE);
        //自動注文種別 (固定値:"IF△△"(IFD親注文))
        inData.setAutoOrderKind(API002_REQUEST_AUTO_ORDER_KIND);
        //DONE指成区分
        inData.setDoneSasinariKbn(getDoneSasinariKbnApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariJyouken(),
                dtoReq.getOco1SasinariJyouken()));
        //DONE指値
        inData.setDonePrice(getDonePriceApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariHouhou(),
                dtoReq.getIfd2Price(), dtoReq.getIfd2OrderExecuteMethodText(), dtoReq.getOco1OrderExecuteMethodText(),
                dtoReq.getOco1Price()));
        //DONE有効期限
        inData.setDoneLimit(getDoneLimitApi002(dtoReq.getIfd2PeriodDate(), dtoReq.getIfd2Limit()));
        //DONERBE注文種別
        inData.setDoneRbeOrderKind(getDoneRbeOrderKindApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariHouhou()));
        //DONEトリガ発動ゾーン
        inData.setDoneTriggerZone(getDoneTriggerZoneApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariHouhou(),
                dtoReq.getOco2GyakusasiHouhou(), dtoReq.getTradeCd()));
        //DONEトリガ値段
        inData.setDoneTriggerPrice(
                getDoneTriggerPriceApi002(dtoReq.getOrderKind(), dtoReq.getIfd2SasinariHouhou(),
                        dtoReq.getIfd2TriggerPrice(), dtoReq.getOco2GyakusasiHouhou(), dtoReq.getOco2TriggerPrice()));
        //DONEOCO指成区分
        inData.setDoneOcoSasinariKbn(
                getDoneOcoSasinariKbnApi002(dtoReq.getOrderKind(), dtoReq.getOco2GyakusasiJyouken()));
        //DONEOCO指値
        inData.setDoneOcoPrice(getDoneOcoPriceApi002(dtoReq.getOrderKind(), dtoReq.getOco2Price()));
        //IPアドレス (固定値:"ifap")
        inData.setIpAddress(String.format("%-39s", API002_REQUEST_IP_ADDRESS));
        //引継ぎID (固定値:"△")
        inData.setTransId(API002_REQUEST_TRANS_ID);
        //媒介 (固定値:"△")
        inData.setIntermediary(API002_REQUEST_INTERMEDIARY);
        //新規約定日 (固定値:ALL"△" 長さ8)
        inData.setOpenTradeDate(API002_REQUEST_OPEN_TRADE_DATE);
        //新規単価 (固定値:ALL"△" 長さ12)
        inData.setOpenPrice(API002_REQUEST_OPEN_PRICE);
        //一般信用売弁済期限年月日区分 (固定値:"△")
        inData.setIppanMgPaymentKbn(API002_REQUEST_IPPAN_MG_PAYMENT_KBN);
        //一般信用売弁済期限年月日数 (固定値:"△△")
        inData.setIppanMgPaymentLimit(API002_REQUEST_IPPAN_MG_PAYMENT_LIMIT);
        
        input.setIndata(inData);
        
        // 自動注文(国内株式現物・信用)新規注文確認情報を取得する。
        result = apiWrapper.stockPlaceOrderAuto(input);
        
        return result;
    }

    /**
     * API001の結果をDTOに格納する
     * 
     * @param res A001bレスポンスDTO
     * @param api001res API001出力データ
     * @param　tradeCd 取引種別
     * @return api001res API001の出力結果
     */
    
    private void setApi001Result(IfaMarginNewOrderConfirmA001bResponseDto res, MarginPlaceOrderOutData api001res,
            String tradeCd) {
        
        //EC受注番号
        res.setEcOrderNo(api001res.getOrderNum());
        //見積単価
        res.setQuoteUnitPrice(api001res.getEstimatePrice());
        //約定金額
        res.setContractAmount(api001res.getAmount());
        //手数料/諸費用
        res.setCharge(api001res.getCommission());
        //消費税
        res.setConsumptionTax(api001res.getConsumptionTax());
        
        //精算金額
        res.setSettlementAmount(api001res.getNetAmount());
        //約定予定日
        res.setContractDate(api001res.getTradeDate());
        //受渡予定日
        res.setDeliveryDate(api001res.getSettlementDate());
        //受注日時
        String dateTime = api001res.getAcceptDate() + " " + api001res.getAcceptTime();
        res.setOrderDayTime(dateTime);
        //注文入力市場
        res.setOrderedMarket(api001res.getOrderedMarket());
    }
    
    /**
     * API002の結果をDTOに格納する
     * 
     * @param res A001bレスポンスDTO
     * @param api002res API002出力データ
     * @param　tradeCd 取引種別
     */
    private void setApi002Result(IfaMarginNewOrderConfirmA001bResponseDto res, StockPlaceOrderAutoOutData api002res,
            String tradeCd) {
        
        //EC受注番号
        res.setEcOrderNo(api002res.getOrderNum());
        //見積単価
        res.setQuoteUnitPrice(api002res.getEstimatePrice());
        //約定金額
        res.setContractAmount(api002res.getAmount());
        //手数料/諸費用
        res.setCharge(api002res.getCommission());
        //消費税
        res.setConsumptionTax(api002res.getConsumptionTax());
        //精算金額
        res.setSettlementAmount(api002res.getNetAmount());
        //約定予定日
        res.setContractDate(api002res.getTradeDate());
        //受渡予定日
        res.setDeliveryDate(api002res.getSettlementDate());
        //受注日時
        String dateTime = api002res.getAcceptDate() + " " + api002res.getAcceptTime();
        res.setOrderDayTime(dateTime);
        //注文入力市場
        res.setOrderedMarket(api002res.getOrderedMarket());
    }
    
    /**
     * API001のリクエスト項目：指成区分の値を取得
     *
     * @param orderKind 注文種別
     * @param sasinariJyouken 通常/逆指値.執行条件
     * @param oco1SasinariJyouken OCO1.執行条件
     * @return sasinariKbn 指成区分の設定値
     */
    private String getSasinarikbnApi001(String orderKind, String sasinariJyouken, String oco1SasinariJyouken) {
        
        String sasinariKbn = null;
        String sasinariKey = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            return sasinariKbn;
        }
        switch (orderKindClass) {
        case NORMAL:
            sasinariKey = sasinariJyouken;
            break;
        case OCO:
            sasinariKey = oco1SasinariJyouken;
            break;
        default:
            break;
        }
        if (sasinariKey != null) {
            sasinariKbn = sasinariKey;
        }
        return sasinariKbn;
    }
    
    /**
     * API001のリクエスト項目：指値の値を取得
     *
     * @param orderKind 注文種別
     * @param sasinariHouhou 通常/逆指値.執行方法
     * @param price 通常/逆指値.注文単価
     * @param gyakusasiHouhou 通常/逆指値.執行方法（逆指値）
     * @param oco1Price OCO1.注文単価
     * @return price 指値の設定値
     */
    private String getPriceApi001(String orderKind, String sasinariHouhou, String price, String gyakusasiHouhou,
            String oco1Price) {
        
        String priceA = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (!Objects.isNull(orderKindClass)) {
            
        }
        switch (orderKindClass) {
        case NORMAL:
            //通常/逆指値.執行方法
            String sasinari = sasinariHouhou;
            if (StringUtils.equals(sasinari, ExecuteMethod.LIMIT.getId())) {
                priceA = price;
            } else if (StringUtils.equals(sasinari, ExecuteMethod.STOP.getId())) {
                if (StringUtils.equals(gyakusasiHouhou, ExecuteMethod.LIMIT.getId())) {
                    priceA = price;
                }
            }
            break;
        case OCO:
            priceA = oco1Price;
            break;
        default:
            break;
        }
        //上記のいずれでもない 
        if (priceA == null) {
            priceA = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", priceA).replace(" ", "0");
    }
    
    /**
     * APIのリクエスト項目：有効期限の値を取得
     *
     * @param periodRadio 期間.期間条件
     * @param limit 期間.日付
     * @return limit 有効期限の設定値
     */
    private String getLimit(String periodRadio, String limit) {
        
        String result = null;
        if (StringUtils.equals(periodRadio, TODAY)) {
            //固定値"△△△△△△△△" 
            result = String.format("%8s", StringUtil.EMPTY_STRING);
        } else {
            result = DateFormatUtil.dateStringYMD(limit);
        }
        return result;
    }
    
    /**
     * API001のリクエスト項目：rbe注文種別の値を取得
     * 
     * @param orderKind 注文種別
     * @param　sasinariHouhou 通常/逆指値.執行方法
     * @return rbeOrderKindrbe注文種別の設定値
     */
    private String getRbeOrderKindApi001(String orderKind, String sasinariHouhou) {
        
        String rbeOrderKind = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            return rbeOrderKind;
        }
        switch (orderKindClass) {
        case NORMAL:
            //通常/逆指値.執行方法
            String sasinari = sasinariHouhou;
            if (StringUtils.equals(sasinari, ExecuteMethod.LIMIT.getId())
                    || StringUtils.equals(sasinari, ExecuteMethod.MARKET.getId())) {
                //固定値:"△△△" 
                rbeOrderKind = String.format("%3s", StringUtil.EMPTY_STRING);
            } else if (StringUtils.equals(sasinari, ExecuteMethod.STOP.getId())) {
                //固定値:SLO
                rbeOrderKind = RBE_SHUBETSU;
            }
            break;
        case OCO:
            //固定値:OCO 
            rbeOrderKind = OrderClass.OCO.getLabel();
            break;
        default:
            break;
        }
        return rbeOrderKind;
    }
    
    /**
     * API001のリクエスト項目：トリガ発動ゾーンの値を取得
     * 
     * @param orderKind 注文種別
     * @param　sasinariHouhou 通常/逆指値.執行方法
     * @param tradeCd 取引種別
     * @return triggerZone トリガ発動ゾーンの設定値
     */
    private String getTriggerZoneApi001(String orderKind, String sasinariHouhou, String tradeCd) {
        
        String triggerZone = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            //"△"(指定なし)
            return NOT_SPECIFIED;
        }
        //取引種別(売買区分)
        String tradeCdA = tradeCd;
        switch (orderKindClass) {
        case NORMAL:
            //執行方法
            if (StringUtils.equals(sasinariHouhou, ExecuteMethod.STOP.getId())) {
                if (StringUtils.equals(tradeCdA, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                    //"0"(以上)
                    triggerZone = MORE;
                } else if (StringUtils.equals(tradeCdA, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                    //"１"(以下)
                    triggerZone = LESS;
                }
            }
            break;
        case OCO:
            if (StringUtils.equals(tradeCdA, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                //"0"(以上)
                triggerZone = MORE;
            } else if (StringUtils.equals(tradeCdA, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                //"１"(以下)
                triggerZone = LESS;
            }
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (triggerZone == null) {
            //"△"(指定なし) 
            triggerZone = NOT_SPECIFIED;
        }
        return triggerZone;
    }
    
    /**
     * API001のリクエスト項目：トリガ値段の値を取得
     * 
     * @param orderKind 注文種別
     * @param　sasinariHouhou 通常/逆指値.執行方法
     * @param　triggerPrice 通常/逆指値.発火条件価格（逆指値）
     * @param　oco2TriggerPrice OCO2.発火条件価格（逆指値）
     * @return triggerPrice トリガ値段の設定値
     */
    private String getTriggerPriceApi001(String orderKind, String sasinariHouhou, String triggerPrice,
            String oco2TriggerPrice) {
        
        String wkTriggerPrice = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            //固定値:"0000000000"
            wkTriggerPrice = StringUtil.EMPTY_STRING;
        }
        switch (orderKindClass) {
        case NORMAL:
            if (StringUtils.equals(sasinariHouhou, ExecuteMethod.STOP.getId())) {
                wkTriggerPrice = triggerPrice;
                break;
            }
            break;
        case OCO:
            wkTriggerPrice = oco2TriggerPrice;
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (StringUtils.isEmpty(wkTriggerPrice)) {
            //固定値:"0000000000"
            wkTriggerPrice = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", wkTriggerPrice).replace(" ", "0");
    }
    
    /**
     * APIのリクエスト項目：OCO指値の値を取得
     *
     * @param orderKind 注文種別
     * @param　oco2Price OCO2.注文単価
     * @return ocoPrice OCO指値の設定値
     */
    private String getOcoPriceApi001(String orderKind, String oco2Price) {
        
        String ocoPrice = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            return ocoPrice;
        }
        switch (orderKindClass) {
        case NORMAL:
            //固定値:ALL"△"
            ocoPrice = API001_OCO_PRICE_SPACE;
            break;
        case OCO:
            ocoPrice = String.format("%10s", oco2Price).replace(" ", "0");
            break;
        default:
            break;
        }
        return ocoPrice;
    }
    
    /**
     * API001のリクエスト項目：OCO指成区分の値を取得
     *
     * @param orderKind 注文種別
     * @param　oco2GyakusasiJyouken OCO2.執行条件（逆指値）
     * @return ocoSasine OCO指成区分の設定値
     */
    private String getOcoSasinariKbnApi001(String orderKind, String oco2GyakusasiJyouken) {
        
        String ocoSasinariKbn = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            return ocoSasinariKbn;
        }
        switch (orderKindClass) {
        case NORMAL:
            //固定値:"△"
            ocoSasinariKbn = SPACE;
            break;
        case OCO:
            ocoSasinariKbn = oco2GyakusasiJyouken;
            break;
        default:
            break;
        }
        return ocoSasinariKbn;
    }
    
    //---------------------------------
    
    /**
     * API002のリクエスト項目：指値の値を取得
     *
     * @param ifd1SasinariHouhou IFD1.執行方法
     * @param ifd1Price IFD1.注文単価
     * @param ifd1GyakusasiHouhou IFD1.執行方法（逆指値）
     * @return price 指値の設定値
     */
    private String getPriceApi002(String ifd1SasinariHouhou, String ifd1Price, String ifd1GyakusasiHouhou) {
        
        String price = null;
        //執行方法
        ExecuteMethod executeMethod = ExecuteMethod.valueOfId(ifd1SasinariHouhou);
        if (Objects.isNull(executeMethod)) {
            //固定値"0000000000"
            return String.format("%10s", StringUtil.EMPTY_STRING).replace(" ", "0");
        }
        switch (executeMethod) {
        case LIMIT:
            price = ifd1Price;
            break;
        case STOP:
            if (StringUtils.equals(ifd1GyakusasiHouhou, ExecuteMethod.LIMIT.getId()))
                price = ifd1Price;
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (price == null) {
            //固定値"0000000000"
            price = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", price).replace(" ", "0");
    }
    
    /**
     * API002のリクエスト項目：rbe注文種別の値を取得
     * 
     * @param ifd1SasinariHouhou IFD1.執行方法
     * @return rbeOrderKindrbe注文種別の設定値
     */
    private String getRbeOrderKindApi002(String ifd1SasinariHouhou) {
        
        String rbeOrderKind = null;
        //注文種別
        ExecuteMethod executeMethod = ExecuteMethod.valueOfId(ifd1SasinariHouhou);
        if (Objects.isNull(executeMethod)) {
            return rbeOrderKind;
        }
        switch (executeMethod) {
        case LIMIT:
        case MARKET:
            //固定値:"△△△"：通常注文
            rbeOrderKind = String.format("%3s", StringUtil.EMPTY_STRING);
            break;
        case STOP:
            //固定値:"SLO":逆指値注文
            rbeOrderKind = RBE_SHUBETSU;
            break;
        default:
            break;
        }
        return rbeOrderKind;
        
    }
    
    /**
     * API002のリクエスト項目：トリガ発動ゾーンの値を取得
     * 
     * @param ifd1SasinariHouhou IFD1.執行方法
     * @return triggerZone トリガ発動ゾーンの設定値
     */
    private String getTriggerZoneApi002(String ifd1SasinariHouhou, String tradeCd) {
        
        String triggerZone = null;
        //執行方法
        // IFD1.執行方法=逆指値
        if (StringUtils.equals(ifd1SasinariHouhou, ExecuteMethod.STOP.getId())) {
            // 取引種別 = 信用新規買
            if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                //固定値:"0"(以上)
                triggerZone = MORE;
            // 取引種別 = 信用新規売
            } else if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                //固定値:"１"(以下)
                triggerZone = LESS;
            }
        }
        //上記のいずれでもない
        if (triggerZone == null) {
            //固定値:"△"(指定なし)
            triggerZone = NOT_SPECIFIED;
        }
        return triggerZone;
    }
    
    /**
     * API002のリクエスト項目：トリガ値段の値を取得
     * 
     * @param ifd1SasinariHouhou IFD1.執行方法
     * @param ifd1TriggerPrice IFD1.発火条件価格（逆指値）
     * @return triggerPrice トリガ値段の設定値
     */
    private String getTriggerPriceApi002(String ifd1SasinariHouhou, String ifd1TriggerPrice) {
        
        String triggerPrice = null;
        //執行方法
        if (StringUtils.equals(ifd1SasinariHouhou, ExecuteMethod.STOP.getId())) {
            triggerPrice = ifd1TriggerPrice;
        }
        //上記のいずれでもない
        if (triggerPrice == null) {
            //固定値:"0000000000"
            triggerPrice = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", triggerPrice).replace(" ", "0");
    }
    
    /**
     * API002のリクエスト項目：DONE指成区分の値を取得
     *
     * @param orderKind 注文種別
     * @param　ifd2SasinariJyouken IFD2.執行条件
     * @param　oco1SasinariJyouken OCO1.執行条件
     * @return doneSasinariKbn DONE指成区分の設定値
     */
    private String getDoneSasinariKbnApi002(String orderKind, String ifd2SasinariJyouken, String oco1SasinariJyouken) {
        
        String doneSasinariKbn = null;
        String sasinariKey = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            return doneSasinariKbn;
        }
        switch (orderKindClass) {
        case IFD:
            sasinariKey = ifd2SasinariJyouken;
            break;
        case IFDOCO:
            sasinariKey = oco1SasinariJyouken;
            break;
        default:
            break;
        }
        if (sasinariKey != null) {
            doneSasinariKbn = sasinariKey;
        }
        return doneSasinariKbn;
    }
    
    /**
     * API002のリクエスト項目：Done指値の値を取得
     *
     * @param orderKind 注文種別
     * @param　ifd2SasinariHouhou IFD2.執行方法
     * @param　ifd2Price IFD2.注文単価
     * @param　ifd2OrderExecuteMethodText IFD2.執行方法（逆指値）
     * @param oco1OrderExecuteMethodText OCO1執行方法
     * @param oco1Price OCO1.注文単価
     * @return donePrice Done指値の設定値
     */
    private String getDonePriceApi002(String orderKind, String ifd2SasinariHouhou, String ifd2Price,
            String ifd2OrderExecuteMethodText, String oco1OrderExecuteMethodText, String oco1Price) {
        
        String donePrice = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            //固定値:"0000000000"
            return String.format("%10s", StringUtil.EMPTY_STRING).replace(" ", "0");
        }
        switch (orderKindClass) {
        case IFD:
            //IFD2.執行方法
            String exeMethod = ifd2SasinariHouhou;
            if (StringUtils.equals(exeMethod, ExecuteMethod.LIMIT.getId())) {
                donePrice = ifd2Price;
            } else if (StringUtils.equals(exeMethod, ExecuteMethod.STOP.getId())) {
                if (StringUtils.equals(ifd2OrderExecuteMethodText, ExecuteMethod.LIMIT.getId())) {
                    donePrice = ifd2Price;
                }
            }
            break;
        case IFDOCO:
            // OCO1執行方法=指値
            if (StringUtils.equals(oco1OrderExecuteMethodText, ExecuteMethod.LIMIT.getId())) {
                donePrice = oco1Price;
            }
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (donePrice == null) {
            //固定値:"0000000000"
            donePrice = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", donePrice).replace(" ", "0");
    }
    
    /**
     * API002のリクエスト項目：DONE有効期限の値を取得
     *
     * @param　ifd2PeriodDate IFD2.期間.期間条件
     * @param ifd2Limit IFD2.期間.日付
     * @return doneLimit DONE有効期限の設定値
     */
    private String getDoneLimitApi002(String ifd2PeriodDate, String ifd2Limit) {
        
        String doneLimit = null;
        //注文種別
        if (StringUtils.equals(ifd2PeriodDate, TODAY)) {
            //固定値"△△△△△△△△"
            doneLimit = String.format("%8s", StringUtil.EMPTY_STRING);
        } else {
            doneLimit = DateFormatUtil.dateStringYMD(ifd2Limit);
        }
        return doneLimit;
    }
    
    /**
     * API002のリクエスト項目：DONERBE注文種別の値を取得
     *
     * @param orderKind 注文種別
     * @param　ifd2SasinariHouhou IFD2.執行方法
     * @return doneRbeOrderKind DONERBE注文種別の設定値
     */
    private String getDoneRbeOrderKindApi002(String orderKind, String ifd2SasinariHouhou) {
        
        String doneRbeOrderKind = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            //"△△△"(通常注文)
            return String.format("%3s", StringUtil.EMPTY_STRING);
        }
        switch (orderKindClass) {
        case IFD:
            //IFD2.執行方法
            String exeMethod = ifd2SasinariHouhou;
            if (StringUtils.equals(exeMethod, ExecuteMethod.LIMIT.getId())
                    || StringUtils.equals(exeMethod, ExecuteMethod.MARKET.getId())) {
                //ALL"△"(通常注文)
                doneRbeOrderKind = String.format("%3s", StringUtil.EMPTY_STRING);
            } else if (StringUtils.equals(exeMethod, ExecuteMethod.STOP.getId())) {
                //SLO(逆指値注文)
                doneRbeOrderKind = DONERBE_ORDER_SHUBETSU_IFD_STOP;
            }
            break;
        case IFDOCO:
            //OCO(DONE注文がOCO)
            doneRbeOrderKind = DONERBE_ORDER_SHUBETSU_IFDOCO;
            break;
        default:
            break;
        }
        //上記以外
        if (doneRbeOrderKind == null) {
            //ALL"△"(通常注文)
            doneRbeOrderKind = String.format("%3s", StringUtil.EMPTY_STRING);
        }
        return doneRbeOrderKind;
    }
    
    /**
     * API002のリクエスト項目：DONEトリガ発動ゾーンの値を取得
     *
     * @param orderKind 注文種別
     * @param　ifd2SasinariHouhou IFD2.執行方法
     * @param　oco2GyakusasiHouhou OCO2.執行方法（逆指値）
     * @return doneTriggerZone DONEトリガ発動ゾーンの設定値
     */
    private String getDoneTriggerZoneApi002(String orderKind, String ifd2SasinariHouhou, String oco2GyakusasiHouhou, String tradeCd) {
        
        String doneTriggerZone = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            //固定値:"△"(指定なし)
            return NOT_SPECIFIED;
        }
        switch (orderKindClass) {
        case IFD:
            //執行方法
            // IFD2.執行方法=逆指値
            if (StringUtils.equals(ifd2SasinariHouhou, ExecuteMethod.STOP.getId())) {
                // 取引種別 = 信用新規買
                if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                    //固定値:"１"(以下)
                    doneTriggerZone = LESS;
                // 取引種別 = 信用新規売
                } else if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                    //固定値:"0"(以上)
                    doneTriggerZone = MORE;
                }
                break;
            }
            break;
        case IFDOCO:
            // 取引種別 = 信用新規買
            if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_BUY.getId())) {
                //固定値:"１"(以下)
                doneTriggerZone = LESS;
            // 取引種別 = 信用新規売
            } else if (StringUtils.equals(tradeCd, DomesticStockTradeClass.SHINYOSHINKI_SELL.getId())) {
                //固定値:"0"(以上)
                doneTriggerZone = MORE;
            }
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (doneTriggerZone == null) {
            //ALL"△"長さ1
            doneTriggerZone = SPACE;
        }
        return doneTriggerZone;
    }
    
    /**
     * API002のリクエスト項目：DONEトリガ値段の値を取得
     *
     * @param orderKind 注文種別
     * @param　ifd2SasinariHouhou IFD2.執行方法
     * @param　ifd2TriggerPrice IFD2.発火条件価格（逆指値）
     * @param　oco2GyakusasiHouhou OCO2.執行方法（逆指値）
     * @param oco2TriggerPrice OCO2.発火条件価格（逆指値）
     * @return doneTriggerPrice DONEトリガ値段の設定値
     */
    private String getDoneTriggerPriceApi002(String orderKind, String ifd2SasinariHouhou,
            String ifd2TriggerPrice, String oco2GyakusasiHouhou, String oco2TriggerPrice) {
        
        String doneTriggerPrice = null;
        //注文種別
        OrderClass orderKindClass = OrderClass.valueOfId(orderKind);
        if (Objects.isNull(orderKindClass)) {
            //"0000000000"
            doneTriggerPrice = String.format("%10s", StringUtil.EMPTY_STRING).replace(" ", "0");
        }
        switch (orderKindClass) {
        case IFD:
            if (StringUtils.equals(ifd2SasinariHouhou, ExecuteMethod.STOP.getId())) {
                doneTriggerPrice = ifd2TriggerPrice;
                break;
            }
            break;
        case IFDOCO:
            doneTriggerPrice = oco2TriggerPrice;
            break;
        default:
            break;
        }
        //上記のいずれでもない
        if (doneTriggerPrice == null) {
            //"0000000000"
            doneTriggerPrice = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", doneTriggerPrice).replace(" ", "0");
    }
    
    /**
     * API002のリクエスト項目：DONEOCO指値の値を取得
     *
     * @param orderKind 注文種別
     * @param　oco2Price　OCO2.注文単価
     * @return doneOcoPrice DONEOCO指値の設定値
     */
    private String getDoneOcoPriceApi002(String orderKind, String oco2Price) {
        
        String doneOcoPrice = null;
        if (StringUtils.equals(orderKind, OrderClass.IFDOCO.getId())) {
            doneOcoPrice = oco2Price;
        } else {
            //"0000000000"
            doneOcoPrice = StringUtil.EMPTY_STRING;
        }
        return String.format("%10s", doneOcoPrice).replace(" ", "0");
    }
    
    /**
     * SOR受注時板乗せ市場区分
     * @param market
     * @return
     */
    private String getSorLastMarket(String market) {
        
        return StringUtils.equals(SelectMarket.SOR.getId(), market) ? API001_REQUEST_MARKET_SOR_VALUE
                : String.format("%3s", StringUtil.EMPTY_STRING);
    }
    
    /**
     * API002のリクエスト項目：DONEOCO指成区分の値を取得
     * 
     * @param orderKind 注文種別
     * @param oco2GyakusasiJyouken　OCO2.執行条件（逆指値）
     * @return doneOcoSasinariKbn DONEOCO指成区分の設定値
     */
    private String getDoneOcoSasinariKbnApi002(String orderKind, String oco2GyakusasiJyouken) {
        
        String doneOcoSasinariKbn = null;
        //注文種別
        if (StringUtils.equals(orderKind, OrderClass.IFDOCO.getId())) {
            doneOcoSasinariKbn = oco2GyakusasiJyouken;
        } else {
            //" "
            doneOcoSasinariKbn = SPACE;
        }
        return doneOcoSasinariKbn;
    }
    
    /**
     * APIリクエスト項目：口座番号設定値作成
     * 
     * @param accountNo
     * @return
     */
    private static String createApiRequestAccountNo(String accountNo) {
        
        return String.format("%7s", accountNo).replace(" ", "0");
    }
    
}

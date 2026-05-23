package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderDecode;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetOffsetBusinessDateResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListForeignStockOrdersResp;
import com.sbisec.helios.ap.athena.service.CometForeignStockFrnTradeDateService;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct037;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct037Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct037Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaOrderStatusListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListDtoResponseDomesticStockOrder;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListDtoResponseForeignStockEntrustOrder;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListDtoResponseFsEntrustOrderStatus;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListDtoResponseFsStoreOrderStatus;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListDtoResponseIntermediaryValue;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListDtoResponseSubscriptOrderStatus;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListX001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOrderStatusListX001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertCalcDomesticBuySellTypeName;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertCalcDomesticMarginTradetypeText;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertCalcDomesticOrderType;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertCalcForeignBuySellTypeName;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertCalcMutualFundSellTypeName;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertCalcVarModStatus;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertNormal;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertOco;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertReceiptDelivery;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.enums.ConvertSlo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaOrderStatusListService;
import com.sbisec.helios.ap.common.enums.AccountType;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignSecurityTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.JrIsaContractType;
import com.sbisec.helios.ap.common.enums.NationalityCode;
import com.sbisec.helios.ap.common.enums.OrderListSecurityType;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.nri.ifa.NriApiService;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryFundOrderWebExtDetail;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundOrderWebExtIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundOrderWebExtInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundOrderWebExtOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOrd;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;
/**
 * 画面ID：SUB0202_0104-01 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *         2023/10/16 新規作成
 */
@Component(value = "cmpIfaOrderStatusListService")
public class IfaOrderStatusListServiceImpL implements IfaOrderStatusListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOrderStatusListServiceImpL.class);
    
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
     * FCT037 取引コース媒介可否チェック
     */
    @Autowired
    private Fct037 fct037;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
    /**
     * APIラッパークラス
     */
    @Autowired
    private ApiWrapper apiWrapper;
    @Autowired
    private NriApiService nriApiService;
    
    /**
     * DAO
     */
    @Autowired
    private IfaOrderStatusListDao dao;
    
    /**
     * AthenaAPI 外国株式取引サービス
     */
    @Autowired
    CometForeignStockFrnTradeDateService cometForeignStockFrnTradeDateService;
    
    /**
     * AthenaAPI 外国株式取引サービス（現物）
     */
    @Autowired
    ForeignStockService foreignStockService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    // --------------------------------
    // 設定値
    // -------------------------------
    
    /** 区分値名:国別通貨単位 */
    private static final String NATIONAL_CURRENCY_UNIT = "NATIONAL_CURRENCY_UNIT";
    
    /** 各種取得件数の初期設定値 */
    private static final String COUNT_DEFAULT = "0";
    
    /** 取得件数の期待値 */
    private static final int COUNT_VALUE = 1;
    
    /** 検索番号指定FROM値の初期値 */
    private static final String FROM_DEFAULT = "1";
    
    /** 検索番号指定TO値の初期値 */
    private static final String TO_DEFAULT = "100";
    
    /** API001のbrand_cdの設定値 */
    private static final String API001_BRAND_CD = "";
    
    /** API001の約定取得区分の設定値 "1":約定明細取得 */
    private static final String API001_TRADE_GET_KBN = "1";
    
    /** API001のリクエスト区分の設定値(X001から呼び出し) */
    private static final String X001_EXEC_ORDER = " ";
    
    /** API001のEC受注番号の設定値(X001から呼び出し) */
    private static final String X001_ORDER_NO = "";
    
    /** API001の取引区分の設定値(X001から呼び出し) */
    private static final String X001_TORIHIKI_KBN = " ";
    
    /** API001のリクエスト区分の設定値(A002から呼び出し) */
    private static final String A002_EXEC_ORDER = "2";
    
    /** SQL002売買区分　'3'買付*/
    private static final String SQL002_TRADEKBN_BUY = "3";
    
    /** SQL002売買区分　'1'売却*/
    private static final String SQL002_TRADEKBN_SELL = "1";
    
    /** 発注区分:現物 */
    private static final String HATTYU_SPOT = "1";
    
    /** 発注区分:信用 */
    private static final String HATTYU_MARGIN = "2";
    
    /** 発注区分:現引現渡 */
    private static final String HATTYU_GENHIKI = "3";
    
    /** API001の取引区分の設定値(A002から呼び出し：現物・信用) */
    private static final String A002_TORIHIKI_KBN = "1";
    
    /** API001の取引区分の設定値(A002から呼び出し:現引現渡) */
    private static final String A002_RECEPTDELIVERY_TORIHIKI_KBN = "2";
    
    /** API001の取得上限件数 */
    private static final String API001_GETNUM = "100";
    
    /** API002の取得上限件数 */
    private static final String API002_GETNUM = "100";
    
    /** API002の商品区分の設定値 */
    private static final String API002_SEQ_ID = " ";
    
    /** API002の約定注文区分の設定値 */
    private static final String API002_EXEC_ORDER = " ";
    
    /** API001とAPI002のfrom,toのフォーマット(5桁0埋め) */
    private static final String REF_FROMTO_FORMAT = "%05d";
    
    /** API001とAPI002の口座番号のフォーマット(7桁0埋め) */
    private static final String KOZA_NO_FORMAT = "%7s";
    
    /** API003の注文日基準種別の値 */
    private static final String API003_DATE_TYPE = "FRN_TRADE_DATE";
    
    /** API004の基準日から見た営業日日数:当日営業 **/
    private static final int BUSINESSDATE_TODAY = 0;
    
    /** API004の基準日から見た営業日日数:前営業日 **/
    private static final int BUSINESSDATE_PREVIOUS = -1;
    
    /** FCT37の注文訂正ステータス設定条件 */
    private static final String FCT37_ORDER = "訂正中";
    
    /** FCT37の注文訂正ステータス： 「1:訂正中」 */
    private static final String FCT37_STATUS_CORRECT = "1";
    
    /** FCT37の注文訂正ステータス： 「０:訂正中ではない」 */
    private static final String FCT37_STATUS_NOTCORRECT = "0";
    
    /** SQL004未取得時のエラーメッセージ内容 */
    private static final String ORDER = "注文";
    
    /** API001 商品区分の出力値 "S":株式 */
    private static final String SEC_ID_STOCK = "S";
    
    /** API001 商品区分の出力値 "G":現引現渡 */
    private static final String SEC_ID_GENHIKI = "G";
    
    /** API001 RBE注文種別の出力値 " 　　":通常注文 */
    private static final String RBE_ORDER_KIND_NORMAL = "   ";
    
    /** API001 RBE注文種別の出力値 "SLO":逆指値注文 */
    private static final String RBE_ORDER_KIND_SLO = "SLO";
    
    /** API001 RBE注文種別の出力値 "OCO":OCO注文 */
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** API001 RBE注文ステータス " ":通常注文 */
    private static final String RBE_ORDER_STATUS_NORMAL = " ";
    
    /** API001 RBE注文ステータス "1":発火済み */
    private static final String RBE_ORDER_STATUS_STOP = "1";
    
    /** API001 強制区分の出力値 "△" */
    private static final String FORCEKBN_DEFAULT = "0";
    
    /** API001 執行ステータスの出力値 "△":(有効) */
    private static final String REJECTSTATUS_ACTIVE = " ";
    
    /** API001 約定ステータスの出力値 "1":一部出来 */
    private static final String EXEC_STATUS_PART = "1";
    
    /** API001 約定ステータスの出力値 "2":全部出来 */
    private static final String EXEC_STATUS_ALL = "2";
    
    /** API001 約定取消区分の出力値 "1":取消し済み */
    private static final String TRADE_CXL_ID_CANCELLED = "1";
    
    /** API001 決済方法の出力値 "5":現引 */
    private static final String SETTLEMENT_ID_GENHIKI = "5";
    
    /** API001 決済方法の出力値 "1":現渡 */
    private static final String SETTLEMENT_ID_GENWATASI = "1";
    
    /** API001 端株注文フラグの出力値 "1":端株注文 */
    private static final String ODD_STACK_ORDERFLAG_ORDER = "1";
    
    /** API001 SOR連携区分の出力値 "1"：SOR回送中 */
    private static final String SORLINKKBN_FORWORD = "1";
    
    /** API001 SOR連携区分の出力値 "2"：SOR連携済 */
    private static final String SORLINKKBN_LINKED = "2";
    
    /** API001 一般信用売弁済期限年月日区分の出力値 "Y"：年単位 */
    private static final String IPPAN_MG_PAYMENTKBN_YEAR = "Y";
    
    /** API001 一般信用売弁済期限年月日区分の出力値 "M"：月単位 */
    private static final String IPPAN_MG_PAYMENTKBN_MONTH = "M";
    
    /** API001 一般信用売弁済期限年月日区分の出力値 "D"：日単位 */
    private static final String IPPAN_MG_PAYMENTKBN_DAY = "D";
    
    /** API001 一般信用売弁済期限年月日区分の出力値 "△"：無期限 */
    private static final String IPPAN_MG_PAYMENTKBN_UNLIMITED = " ";
    
    /** API001 特殊入力区分の出力値 "1":OCO発火訂正 */
    private static final String SP_INPUT_KBN_OCO_CORRECT = "1";
    
    /** API002 注文ステータスの出力値 "1":ACCEPTED */
    private static final String ORDER_STATUS_ACCEPTED = "1";
    
    /** API002 注文取消区分の出力値 "△":ノーマル */
    private static final String CXLORDERID_ACCEPTED_NORMAL = " ";
    
    /** API002 注文取消区分の出力値 "1":取り消し */
    private static final String CXLORDERID_ACCEPTED_CANCEL = "1";
    
    /** API002 受付経路区分の出力値 "8"：定額売却注文 */
    private static final String CALLCENTER_KBN_SELL = "8";
    
    /** API002 受付経路区分の出力値 "F"：定率売却注文 */
    private static final String CALLCENTER_KBN_SELL_FIXED = "F";
    
    /** API002 受付経路区分の出力値 "I"：期間指定売却注文 */
    private static final String CALLCENTER_KBN_SELL_LIMIT = "I";
    
    /** API002 受付経路区分の出力値 "9"：積立買付注文 */
    private static final String CALLCENTER_KBN_BUY = "9";
    
    /** API002 受付経路区分の出力値 "C"：積立買付注文 - クレジットカード決済 */
    private static final String CALLCENTER_KBN_BUY_CREDIT = "C";
    
    /** API002 売買区分の出力値 "U"：売（解約） */
    private static final String BUYSELL_SELL = "U";
    
    /** API002 売買区分の出力値 "V"：売（買取） */
    private static final String BUYSELL_SELL_BUY = "V";
    
    /** API002 売買区分の出力値 "A"：全解約 */
    private static final String BUYSELL_CANCELL = "A";
    
    /** API002 売買区分の出力値 "B"：全買取 */
    private static final String BUYSELL_ALL_BUY = "B";
    
    /** API002 約定区分の出力値 "△"未約定 */
    private static final String EXECSTATUS_NOT_CONVENTIONS = " ";
    
    /** API002 約定区分の出力値 "1"約定 */
    private static final String EXECSTATUS_CONVENTIONS = "1";
    
    /** API002 取消ステータスの出力値 "1":ACCEPTED */
    private static final String CXLSTATUS_ACCEPTED = "1";

    /** API002 取消ステータスの出力値 "△":(取消を行っていない状態) */
    private static final String CXLSTATUS_NOT_CANCELL = " ";
    
    /** API002 商品区分の出力値 "T":一般口 */
    private static final String SECID_GENERAL = "T";
    
    /** API002 商品区分の出力値 "Y":累投 */
    private static final String SECID_ACCUMULATION = "Y";
    
    /** API002 購入解約方法の出力値 "2":口座指定 */
    private static final String BUYSELLMTD_ACCOUNT = "2";
    
    /** WEB注文ステータスの出力値 "1":ACCEPTED */
    private static final String WEB_ORDER_STATUS_ACCEPTED = "1";
    
    /** WEB注文ステータスの出力値 "2":DEFFERED */
    private static final String WEB_ORDER_STATUS_DEFFERED = "2";
    
    /** WEB注文ステータスの出力値 "3":REJECTED */
    private static final String WEB_ORDER_STATUS_REJECTED = "3";
    
    /** WEB注文ステータスが"3"でない場合の算出のための設定値 */
    private static final String WEB_ORDER_STATUS_NOT_REJECTED = "N";
    
    /** 算出処理にて半角スペースに対応する文字 */
    private static final String SPACE_TO = "S";
    
    /** API003 自動買付区分の出力値 RESERVE_ORDER：定期買付 */
    private static final String AUTO_STOCK_ORDERTYPE_RESERVE_ORDER = "RESERVE_ORDER";
    
    /** API003 自動買付区分の出力値 NORMAL_ORDER：通常注文 */
    private static final String AUTO_STOCK_ORDERTYPE_NORMAL_ORDER = "NORMAL_ORDER";
    
    /** API003 価格条件の出力値 "1"指値 */
    private static final String ORDERPRICE_KINDCODE_PRICE_LIMIT = "1";
    
    /** API003 価格条件の出力値 "2"成行 */
    private static final String ORDERPRICE_KINDCODE_MARKET_ORDER = "2";
    
    /** API003 価格条件の出力値 "3"逆指値/指値 */
    private static final String ORDERPRICE_KINDCODE_REVERSE_PRICE_LIMIT = "3";
    
    /** API003 価格条件の出力値 "4"逆指値/成行 */
    private static final String ORDERPRICE_KINDCODE_REVERSE_MARKET_ORDER = "4";
    
    /** API003 発火条件価格の出力値 "TODAY_ORDER":当日注文 */
    private static final String STOP_PRICE_TODAY_ORDER = "TODAY_ORDER";
    
    /** API003 発火条件価格の出力値 "CARRY_OVER_ORDER"：期間指定注文 */
    private static final String STOP_PRICE_CARRY_OVER_ORDER = "CARRY_OVER_ORDER";
    
    /** API003 売買区分の出力値 "BUY"：買付 */
    private static final String BUYSELL_CODE_BUY = "BUY";
    
    /** API003 売買区分の出力値 "SELL"：売付 */
    private static final String BUYSELL_CODE_SELL = "SELL";
    
    // --------------------------------
    // 算出処理output及び算出値
    // カンマ区切りで各項目の産出値を持つ
    // [0]:注文状況(算出)
    // [1]:注文状況補足(算出)
    // [2]:訂正ボタン表示判定
    // [3]:取消ボタン表示判定
    // --------------------------------
    
    /** 算出結果が得られなかった時の設定値 :null */
    private static final String NONE_OUTPUT = null;

    /** 注文状況補足の算出結果が得られなかった時の設定値 -:(無し) */
    private static final String NONE_OUTPUT_HYPHEN = "-";
    
    /** 通常注文 強制区分が通常注文以外 かつ 約定ステータスが全部出来以外の時のoutput */
    private static final String NORMAL_FORCE_OUTPUT = "強制注文中,-,0,0";
    
    /** 通常注文 強制区分が通常注文以外 かつ 約定ステータスが全部出来の時のoutput */
    private static final String NORMAL_FORCE_EXECSTATUS_OUTPUT = "完了,-,0,0";

    /** 通常注文 失効ステータスが有効でないの時のoutput */
    private static final String NORMAL_REJECT_OUTPUT = "失効,-,0,0";
    
    /** 通常注文 約定ステータスが全部出来の時のoutput */
    private static final String NORMAL_EXECSTATUS_OUTPUT = "完了,-,0,0";
    
    /** 通常注文 有効期限がマーケット発注日より前の時のoutput */
    private static final String NORMAL_LIMIT_OUTPUT = "注文中,A,0,0";
    
    /** 逆指値注文 失効ステータスが有効で無い時のoutput */
    private static final String SLO_REJECT_OUTPUT = "失効,-,0,0";
    
    /** 逆指値注文 約定ステータスが全部出来の時のoutput */
    private static final String SLO_EXECSTATUS_OUTPUT = "完了,-,0,0";

    /** 逆指値注文 WEB注文ステータスがREJECTEDの時のoutput */
    private static final String SLO_WEB_OUTPUT = "注文不可,-,0,0";
    
    /** 逆指値注文 有効期限がマーケット発注日より前の時のoutput */
    private static final String SLO_LIMIT_OUTPUT = "待機中,-,0,0";
    
    /** OCO注文 強制区分が通常注文以外 かつ 約定ステータスが全部出来以外の時のoutput */
    private static final String OCO_FORCE_OUTPUT = "強制注文中,-,0,0";

    /** OCO注文 強制区分が通常注文以外 かつ 約定ステータスが全部出来の時のoutput */
    private static final String OCO_FORCE_EXECSTATUS_OUTPUT = "完了,-,0,0";
    
    /** OCO注文 失効ステータスが有効でないの時のoutput */
    private static final String OCO_REJECT_OUTPUT = "失効,-,0,0";
    
    /** OCO注文 約定ステータスが全部出来の時のoutput */
    private static final String OCO_EXECSTATUS_OUTPUT = "完了,-,0,0";

    /** OCO注文 WEB注文ステータスがREJECTEDの時のoutput */
    private static final String OCO_WEB_OUTPUT = "注文不可,-,0,0";
    
    /** OCO注文 有効期限がマーケット発注日より前の時のoutput */
    private static final String OCO_LIMIT_OUTPUT = "注文中,-,0,0";
    
    /** 現引現渡注文 強制区分が有効でないの時のoutput */
    private static final String RECEIPTDELIVERY_FORCE_OUTPUT = "強制注文中,-,0,0";
    
    /** 現引現渡注文 失効ステータスが有効でないの時のoutput */
    private static final String RECEIPTDELIVERY_REJECT_OUTPUT = "失効,-,0,0";
    
    /** 現引現渡注文 約定取消区分が取消済みではなく、約定ステータスが出来済みの時のoutput */
    private static final String RECEIPTDELIVERY_TRADE_OUTPUT = "完了,-,0,0";

    /** 国内株式注文 Ph.1対象外の注文の場合訂正ボタン、取消ボタンを非表示 */
    private static final String DISABLE_BUTTON_PH1 = "0";
    
    /** 注文状況補足(算出) 判定値A(※１) */
    private static final String ORDER_STATUS_SUPPLEMENT_ONE = "A";
    
    /** 注文状況補足(算出) 判定値B(※2) */
    private static final String ORDER_STATUS_SUPPLEMENT_TWO = "B";
    
    /** 注文状況補足(算出) 判定値C(※3) */
    private static final String ORDER_STATUS_SUPPLEMENT_THREE = "C";
    
    /** 注文状況補足(算出) 判定値D(※4) */
    private static final String ORDER_STATUS_SUPPLEMENT_FOUR = "D";
    
    /** 注文状況補足(算出) (訂正できず) */
    private static final String ORDER_STATUS_SUPPLEMENT_NOT_CORRECTABEL = "（訂正できず）";
    
    /** 注文状況補足(算出) (一部約定) */
    private static final String ORDER_STATUS_SUPPLEMENT_PART_TRADE = "（一部約定）";
    
    /** 注文状況補足(算出) （訂正エラー） */
    private static final String ORDER_STATUS_SUPPLEMENT_CORRECT_ERROR = "（訂正エラー）";
    
    /** 注文状況補足(算出) （取消しできず） */
    private static final String ORDER_STATUS_SUPPLEMENT_NOT_CANSELABEL = "（取消できず）";
    
    /** 国内株式:注文状況 "待機中" */
    private static final String CALC_ORDER_STATUS_WAITING = "待機中";
    
    /** 国内株式：取引種別 7:現引 */
    private static final String DOMESTIC_BUYSELLTYPE_RECEIPT = "7";
    
    /** 国内株式：取引種別 8:現渡 */
    private static final String DOMESTIC_BUYSELLTYPE_DELIVERY = "8";
    
    /** 国内株式:市場 A:SOR */
    private static final String DOMESTIC_MARKET_SOR = "A";
    
    /** 国内株式:信用取引区分 年 */
    private static final String DOMESTIC_MARGINTRADETYPE_TEXT_YEAR = "年";
    
    /** 国内株式:信用取引区分 ヵ月 */
    private static final String DOMESTIC_MARGINTRADETYPE_TEXT_MONTH = "ヵ月";
    
    /** 国内株式:信用取引区分 日 */
    private static final String DOMESTIC_MARGINTRADETYPE_TEXT_DAY = "日";
    
    /** 国内株式:信用取引区分 無期限 */
    private static final String DOMESTIC_MARGINTRADETYPE_TEXT_UNLIMITED = "無期限";
    
    /** 国内投信:注文状況 "約定" */
    private static final String MUTUALFUND_ORDERSTATUS_CONVENTIONS = "約定";
    
    /** 国内投信:注文状況 "取消済" */
    private static final String MUTUALFUND_ORDERSTATUS_CANCELED = "取消済";
    
    /** 国内投信:注文状況 "注文中" */
    private static final String MUTUALFUND_ORDERSTATUS_ORDERING = "注文中";
    
    /** 国内投信:取引種別 "9"：解約（定額） */
    private static final String MUTUALFUND_SELLTYPENAME_KAIYAKU_TEIGAKU = "9";
    
    /** 国内投信:取引種別 "5"：買取（定額） */
    private static final String MUTUALFUND_SELLTYPENAME_KAITORI_TEIGAKU = "5";
    
    /** 国内投信:取引種別 "14"：解約（定率） */
    private static final String MUTUALFUND_SELLTYPENAME_KAIYAKU_TEIRITSU = "14";
    
    /** 国内投信:取引種別 "12"：買取（定率） */
    private static final String MUTUALFUND_SELLTYPENAME_KAITORI_TEIRITSU = "12";
    
    /** 国内投信:取引種別 "15"：解約（期間指定） */
    private static final String MUTUALFUND_SELLTYPENAME_KAIYAKU_KIKANSHITEI = "15";
    
    /** 国内投信:取引種別 "13"：買取（期間指定） */
    private static final String MUTUALFUND_SELLTYPENAME_KAITORI_KIKANSHITEI = "13";
    
    /** 国内投信:取引種別 "2"：購入（積立） */
    private static final String MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE = "2";
    
    /** 国内投信:取引種別 "11"：購入（カード積立） */
    private static final String MUTUALFUND_SELLTYPENAME_KONYU_CARDTUMITATE = "11";
    
    /** 国内投信:取引種別 "10"：全額解約 */
    private static final String MUTUALFUND_SELLTYPENAME_ZENGAKUKAIYAKU = "10";
    
    /** 国内投信:取引種別 "6"：全額買取 */
    private static final String MUTUALFUND_SELLTYPENAME_ZENGAKUKAITORI = "6";
    
    /** 国内投信:取消しボタン表示判定 "0":非表示 */
    private static final String MUTUALFUND_CANCELBUTTON_HIDE = "0";
    
    /** 国内投信:取消しボタン表示判定 "1":表示 */
    private static final String MUTUALFUND_CANCELBUTTON_DISPLAY = "1";
    
    /** 国内投信:数量単位 "口" */
    private static final String MUTUALFUND_UNIT_KUCHI = "口";
    
    /** 国内投信:数量単位 "円" */
    private static final String MUTUALFUND_UNIT_EN = "円";
    
    /** 外株委託:取引種別 "6":定期 */
    private static final String FOREIGN_BUYSELLTYPE_TEIKI = "6";
    
    /** 外株委託:取引種別 "成行" */
    private static final String FOREIGNPRICE_MARKET_ORDER = "成行";
    
    /** 外株委託:期間 "当日注文" */
    private static final String FOREIGNORDER_PERIOD = "当日注文";
    
    /** 外株委託:取消しボタン表示判定 "0":非表示 */
    private static final String FOREIGN_CANCELBUTTON_HIDE = "0";
    
    /** 外株委託:取消しボタン表示判定 "1":表示 */
    private static final String FOREIGN_CANCELBUTTON_DISPLAY = "1";
    
    /** 外株委託：条件詳細 以上 */
    private static final String FOREIGNCONDITIONS_OVER_MESSAGE = "現在値が%s以上になった時点で%sで発注";
    
    /** 外株委託：条件詳細 以下 */
    private static final String FOREIGNCONDITIONS_LESS_MESSAGE = "現在値が%s以下になった時点で%sで発注";
    
    /** 外株委託：条件詳細 成行 */
    private static final String FOREIGNCONDITIONS_MARKET_ORDER_MESSAGE = "成行";
    
    /** 店頭取引注文リスト.取引種別　"11":店頭買*/
    private static final String BUY_SELL_TYPE_BUY = "11";
    
    /** 店頭取引注文リスト.取引種別　"12":店頭売*/
    private static final String BUY_SELL_TYPE_SELL = "12";

    /** 端株注文フラグ.端株注文 */
    private static final String ODD_STOCK_ORDER_FLAG_TRUE = "1";

    /** 手数料区分.立会外分売手数料 */
    private static final String COMM_TYPE_OFF_FLOOR_DISTRIBUTION_INDIVISUAL = "4";

    /** 手数料区分.リテールオファー手数料 */
    private static final String COMM_TYPE_RETAIL_OFFER = "6";

    // --------------------------------
    // メッセージ
    // --------------------------------
    /**
     * 入力した部店口座は存在しません。<br>
     * 部店: [{0}]、口座: [{1}]
     */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 他のユーザーは当該{0}を操作しました。ご確認ください。 */
    private static final String ERRORS_EXCLUSIVE = "errors.exclusive";
    
    /** 該当の注文の状況は確認できません。 */
    private static final String ERRORS_CMM_ORDERSTATUS_NOTFOUND = "errors.cmm.orderStatus.notFound";
    
    /**
     * マトリクス表ID作成処理
     *
     * @param param1 パラーメータ1
     * @param param2 パラーメータ2
     * @param param3 パラーメータ3
     * @return 各引数を結合したID
     */
    private static String createMatrixid(String param1, String param2, String param3) {
        
        // paramを結合してマトリクス表IDとする(半角スペースはSに置き換え)
        String matrixId = param1 == null ? StringUtil.EMPTY_STRING : param1.replace(" ", SPACE_TO);
        matrixId += param2 == null ? StringUtil.EMPTY_STRING : param2.replace(" ", SPACE_TO);
        matrixId += param3 == null ? StringUtil.EMPTY_STRING : param3.replace(" ", SPACE_TO);
        return StringUtil.isNullOrEmpty(matrixId) ? StringUtil.EMPTY_STRING : matrixId;
    }
    
    /**
     * アクションID：X001 アクション名：初期化 Dto リクエスト：brokerageMenu.customerMenuX001DtoRequest
     * Dto レスポンス：brokerageMenu.customerMenuX001DtoResponse model
     * リクエスト：IfaOrderStatusListSql004RequestModel model
     * レスポンス：IfaOrderStatusListSql004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return IfaOrderStatusListX001DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListX001DtoResponse> initializeX001(IfaOrderStatusListX001DtoRequest dtoReq)
            throws Exception {
        
        List<IfaOrderStatusListX001DtoResponse> resDto = new ArrayList<IfaOrderStatusListX001DtoResponse>();
        IfaOrderStatusListX001DtoResponse response = new IfaOrderStatusListX001DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOrderStatusListServiceImplL.initializeX001");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 利用者の口座に対する権限チェックを行う。
        // FCT001
        OutputFct001Dto fct001Res = callFct001(cc.getButenCode(), cc.getAccountNumber());
        // アカウントが存在するか確認
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        
        // 口座の契約締結前交付書面コードのチェック および 共同募集顧客(共募顧客)のチェックを行う
        // FCT003
        OutputFct003Dto fct003Res = callFct003(cc.getButenCode(), cc.getAccountNumber());
        
        // 共通関数出力値の格納
        response.setTradeSuspendFlag(fct001Res.getTradeSuspendFlag());
        List<IfaOrderStatusListDtoResponseIntermediaryValue> intermediaryValueList = getIntermediaryValue(fct003Res);
        response.setIntermediaryValueList(intermediaryValueList);
        
        // API001を呼ぶ
        List<QueryStockUniteOrderPointOrd> api001result = callApi001(FROM_DEFAULT, TO_DEFAULT, X001_EXEC_ORDER, X001_ORDER_NO,
                X001_TORIHIKI_KBN, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        // API001の返却値から各値を設定し、国内株式注文一覧のlistを生成
        List<IfaOrderStatusListDtoResponseDomesticStockOrder> domesticStockOrderResult = getDomesticStockOrderResult(
                api001result);
        response.setDomesticStockOrderList(domesticStockOrderResult);
        
        // API001の取得件数を設定
        if (!StringUtils.equals(String.valueOf(api001result.size()), null)) {
            response.setDomesticStockOrderCount(String.valueOf(api001result.size()));
        } else {
            response.setDomesticStockOrderCount(COUNT_DEFAULT);
        }
        
        // API002を呼ぶ
        List<QueryFundOrderWebExtDetail> api002result = callApi002(FROM_DEFAULT, TO_DEFAULT, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        // API002の返却値から各値を設定し、国内投信注文一覧のlistを生成
        List<IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus> domesticMutualFundOrderStatusResult = getDomesticMutualFundOrderStatusResult(
                api002result);
        response.setDomesticMutualFundOrderStatusList(domesticMutualFundOrderStatusResult);
        // API002の取得件数を設定
        if (!StringUtils.equals(String.valueOf(api002result.size()), null)) {
            response.setDomesticMutualFundOrderCount(String.valueOf(api002result.size()));
        } else {
            response.setDomesticMutualFundOrderCount(COUNT_DEFAULT);
        }
        
        // SQL001を呼び注文一覧を取得する。
        DataList<IfaOrderStatusListSql001ResponseModel> sql001Res = getSubscriptOrder(null, null);
        // SQL001の取得件数を設定
        response.setSubscriptOrderStatusCount(String.valueOf(sql001Res.size()));
        List<IfaOrderStatusListDtoResponseSubscriptOrderStatus> subscriptOrderStatusList = getSubscriptOrderStatus(
                sql001Res);
        response.setSubscriptOrderStatusList(subscriptOrderStatusList);
        
        // 顧客共通情報.外国株式取引口座開設状況が開設済みの場合、外株委託注文一覧を取得
        response.setFsEntrustOrderStatusTotalCount(COUNT_DEFAULT);
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.OPEN.getId())) {
            // API003実行から国別リストを取得する処理
            List<IfaOrderStatusListDtoResponseForeignStockEntrustOrder> foreignStockEntrustOrderList = new ArrayList<IfaOrderStatusListDtoResponseForeignStockEntrustOrder>();
            try {
                foreignStockEntrustOrderList = getForeignStockEntrustOrder();
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaOrderStatusListServiceImpL.initializeX001}", e);
                }
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }

            response.setForeignStockEntrustOrder(foreignStockEntrustOrderList);
            // 国別リスト内の外国株式委託注文一覧の取得総件数を設定
            int count = 0;
            for (IfaOrderStatusListDtoResponseForeignStockEntrustOrder f : foreignStockEntrustOrderList) {
                if (f.getFsEntrustOrderStatusList() == null) {
                    continue;
                }
                count += f.getFsEntrustOrderStatusList().size();
            }
            response.setFsEntrustOrderStatusTotalCount(String.valueOf(count));
        }
        
        // SQL002を呼び外株店頭注文一覧を取得する。
        DataList<IfaOrderStatusListSql002ResponseModel> sql002Res = getForeignStockCounterOrderInfo(null);
        // 取得件数を格納
        response.setForeignStockCounterOrderCount(String.valueOf(sql002Res.getDataList().size()));
        // SQL002の返却値を設定した店頭取引注文リストを作成
        List<IfaOrderStatusListDtoResponseFsStoreOrderStatus> fsStoreOrderStatus = getFsStoreOrderStatus(sql002Res);
        response.setFsStoreOrderStatusList(fsStoreOrderStatus);
        
        if (StringUtils.equals(cc.getForeignSecurityTradeAccountOpenStatus(),
                ForeignSecurityTradeAccountOpenStatus.OPEN.getId())) {
            // SQL004を呼びコールセンタ認証IDを取得する。
            DataList<IfaOrderStatusListSql004ResponseModel> sql004Res = getCallCenterAuthId();
            // SQL004の出力格納
            if (sql004Res.getDataList().size() > 0) {
                response.setCcsAuthId(sql004Res.getDataList().get(0).getCcsAuthId());
            }
        }
        
        resDto.add(response);
        return apiErrorUtil.createDataList(resDto, null);
    }
    
    /**
     * アクションID：A002 アクション名：初期化（注文一覧） Dto
     * リクエスト：brokerageMenu.customerMenuA002DtoRequest Dto
     * レスポンス：brokerageMenu.customerMenuA002DtoResponse model
     * リクエスト：IfaOrderStatusListSql004RequestModel model
     * レスポンス：IfaOrderStatusListSql004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return IfaOrderStatusListA002DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListA002DtoResponse> initializeOrderListA002(IfaOrderStatusListA002DtoRequest dtoReq)
            throws Exception {
        
        List<IfaOrderStatusListA002DtoResponse> resDto = new ArrayList<IfaOrderStatusListA002DtoResponse>();
        IfaOrderStatusListA002DtoResponse response = new IfaOrderStatusListA002DtoResponse();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOrderStatusListServiceImplL.initializeOrderListA002");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 利用者の口座に対する権限チェックを行う。
        // FCT001
        OutputFct001Dto fct001Res = callFct001(cc.getButenCode(), cc.getAccountNumber());
        // アカウントが存在するか確認
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }
        
        // 口座の契約締結前交付書面コードのチェック および 共同募集顧客(共募顧客)のチェックを行う
        // FCT003
        OutputFct003Dto fct003Res = callFct003(cc.getButenCode(), cc.getAccountNumber());
        
        // 共通関数出力値の格納
        response.setTradeSuspendFlag(fct001Res.getTradeSuspendFlag());
        List<IfaOrderStatusListDtoResponseIntermediaryValue> intermediaryValueList = getIntermediaryValue(fct003Res);
        response.setIntermediaryValueList(intermediaryValueList);
        
        // 商品区分が国内株式の場合にのみ国内株式一覧を設定
        if (StringUtils.equals(dtoReq.getSecurityType(), OrderListSecurityType.DOMESTIC.getId())) {
            // API001を呼ぶ
            String torihikiKbn = null;
            if (StringUtils.equals(dtoReq.getHattyuuKbn(), HATTYU_SPOT)
                    || StringUtils.equals(dtoReq.getHattyuuKbn(), HATTYU_MARGIN)) {
                torihikiKbn = A002_TORIHIKI_KBN;
            } else if (StringUtils.equals(dtoReq.getHattyuuKbn(), HATTYU_GENHIKI)) {
                torihikiKbn = A002_RECEPTDELIVERY_TORIHIKI_KBN;
            }
            List<QueryStockUniteOrderPointOrd> api001result = callApi001(FROM_DEFAULT, TO_DEFAULT, A002_EXEC_ORDER,
                    dtoReq.getEcOrderNo(), torihikiKbn, apiErrorUtil);
            if (apiErrorUtil.isFatal()) {
                return apiErrorUtil.createDataList(new ArrayList<>(), null);
            }
            
            // API001の返却値から各値を設定し、国内株式注文一覧のlistを生成
            List<IfaOrderStatusListDtoResponseDomesticStockOrder> domesticStockOrderResult = getDomesticStockOrderResult(
                    api001result);
            
            // 取得件数が1件でなければエラー
            if (domesticStockOrderResult.size() != COUNT_VALUE) {
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_EXCLUSIVE,
                        IfaCommonUtil.getMessage(ERRORS_CMM_ORDERSTATUS_NOTFOUND));
            }
            // API001の取得件数を設定
            response.setDomesticStockOrderCount(String.valueOf(domesticStockOrderResult.size()));
            
            response.setDomesticStockOrderList(domesticStockOrderResult);
        } else {
            // 商品区分が異なるなら取得件数に0件を設定
            response.setDomesticStockOrderCount(COUNT_DEFAULT);
            // 空のリストを設定
            response.setDomesticStockOrderList(new ArrayList<IfaOrderStatusListDtoResponseDomesticStockOrder>());
        }
        
        // 商品区分が国内投信の場合にのみ国内投信注文一覧を設定
        if (StringUtils.equals(dtoReq.getSecurityType(), OrderListSecurityType.MUTUAL_FUND.getId())) {
            // API002を呼ぶ
            List<QueryFundOrderWebExtDetail> api002result = callApi002(FROM_DEFAULT, TO_DEFAULT, apiErrorUtil);
            if (apiErrorUtil.isFatal()) {
                return apiErrorUtil.createDataList(new ArrayList<>(), null);
            }
            
            // API002.注文部.EC受注番号 = リクエスト.EC受注番号（国内投信注文）かつ、API002.注文部.商品区分 = リクエスト.商品区分（国内投信）
            // を満たすレスポンスのみに絞る
            api002result.removeIf(
                    d -> !(StringUtils.equals(d.getOrdeNo(), dtoReq.getDomesticMutualFundOrderStatusEcOrderNo())
                    && StringUtils.equals(d.getSecId(), dtoReq.getOrderType()))
                    );
            // 取得件数が1件でなければエラー
            if (api002result.size() != COUNT_VALUE) {
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_EXCLUSIVE,
                        IfaCommonUtil.getMessage(ERRORS_CMM_ORDERSTATUS_NOTFOUND));
            }

            // API002の返却値から各値を設定し、国内投信注文一覧のlistを生成
            List<IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus> domesticMutualFundOrderStatusResult = getDomesticMutualFundOrderStatusResult(
                    api002result);

            // API002の取得件数を設定
            response.setDomesticMutualFundOrderCount(String.valueOf(api002result.size()));
            response.setDomesticMutualFundOrderStatusList(domesticMutualFundOrderStatusResult);
        } else {
            // 商品区分が異なるなら取得件数に0件を設定
            response.setDomesticMutualFundOrderCount(COUNT_DEFAULT);
            // 空のリストを設定
            response.setDomesticMutualFundOrderStatusList(new ArrayList<IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus>());
        }
        
        // 商品区分が募集の場合にのみ注文一覧を設定
        if (StringUtils.equals(dtoReq.getSecurityType(), OrderListSecurityType.SUBSCRIPT.getId())) {
            // SQL001を呼び注文一覧を取得する。
            DataList<IfaOrderStatusListSql001ResponseModel> sql001Res = getSubscriptOrder(dtoReq.getBrandCode(),
                    dtoReq.getDepositType());
            // 取得件数が1件でなければエラー
            if (sql001Res.getDataList().size() != COUNT_VALUE) {
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_EXCLUSIVE,
                        IfaCommonUtil.getMessage(ERRORS_CMM_ORDERSTATUS_NOTFOUND));
            }
            // 取得件数を格納
            response.setSubscriptOrderStatusCount(String.valueOf(sql001Res.getDataList().size()));
            List<IfaOrderStatusListDtoResponseSubscriptOrderStatus> subscriptOrderStatusList = getSubscriptOrderStatus(
                    sql001Res);
            response.setSubscriptOrderStatusList(subscriptOrderStatusList);
        } else {
            // 商品区分が異なるなら取得件数に0件を設定
            response.setSubscriptOrderStatusCount(COUNT_DEFAULT);
            // 空のリストを設定
            response.setSubscriptOrderStatusList(new ArrayList<IfaOrderStatusListDtoResponseSubscriptOrderStatus>());
        }
        
        // 商品区分が外株委託注文の場合にのみ外株委託注文一覧を設定
        if (StringUtils.equals(dtoReq.getSecurityType(), OrderListSecurityType.FOREIGN_ENTRUST.getId())) {
            // 顧客共通情報.外国株式取引口座開設状況が開設済みの場合、外株委託注文一覧を取得
            if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                    ForeignStockTradeAccountOpenStatus.OPEN.getId())) {
                
                // API003実行から国別リストを取得する処理
                List<IfaOrderStatusListDtoResponseForeignStockEntrustOrder> foreignStockEntrustOrderList = new ArrayList<IfaOrderStatusListDtoResponseForeignStockEntrustOrder>();
                try {
                    foreignStockEntrustOrderList = getForeignStockEntrustOrder();
                } catch (Exception e) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("{IfaOrderStatusListServiceImpL.initializeOrderListA002}", e);
                    }
                    return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                            ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
                }
                // 取得件数の合計値を取得
                int count = 0;
                for (IfaOrderStatusListDtoResponseForeignStockEntrustOrder f : foreignStockEntrustOrderList) {
                    if (f.getFsEntrustOrderStatusList() == null) {
                        continue;
                    }
                    // 注文番号と注文サブ番号が入力値と同様のものだけに絞り込む
                    f.getFsEntrustOrderStatusList()
                            .removeIf(fs -> !StringUtils.equals(fs.getOrderNumber(), dtoReq.getOrderNumber()));
                    f.getFsEntrustOrderStatusList()
                            .removeIf(fs -> !StringUtils.equals(fs.getOrderSubNumber(), dtoReq.getOrderSubNumber()));
                    // 取得件数が1件でなければエラー
                    if (f.getFsEntrustOrderStatusList().size() != COUNT_VALUE) {
                        return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_EXCLUSIVE,
                                IfaCommonUtil.getMessage(ERRORS_CMM_ORDERSTATUS_NOTFOUND));
                    }
                    // 絞り込んだ後の件数を設定
                    count += f.getFsEntrustOrderStatusList().size();
                    f.setFsEntrustOrderStatusCount(String.valueOf(f.getFsEntrustOrderStatusList().size()));
                }
                // 国別リスト内の外国株式委託注文一覧の取得総件数を設定
                response.setFsEntrustOrderStatusTotalCount(String.valueOf(count));
                
                response.setForeignStockEntrustOrder(foreignStockEntrustOrderList);
            }
        } else {
            // 商品区分が異なるなら取得件数に0件を設定
            response.setFsEntrustOrderStatusTotalCount(COUNT_DEFAULT);
            // 空のリストを設定
            response.setForeignStockEntrustOrder(new ArrayList<IfaOrderStatusListDtoResponseForeignStockEntrustOrder>());
        }
        
        // 商品区分が外国株式店頭注文の場合にのみ外株店頭注文一覧を設定
        if (StringUtils.equals(dtoReq.getSecurityType(), OrderListSecurityType.FOREIGN_COUNTER.getId())) {
            // SQL002を呼び外株店頭注文一覧を取得する。
            DataList<IfaOrderStatusListSql002ResponseModel> sql002Res = getForeignStockCounterOrderInfo(
                    dtoReq.getManageNumber());
            // 取得件数が1件でなければエラー
            if (sql002Res.getDataList().size() != COUNT_VALUE) {
                return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_EXCLUSIVE,
                        IfaCommonUtil.getMessage(ERRORS_CMM_ORDERSTATUS_NOTFOUND));
            }
            // 取得件数を格納
            response.setForeignStockCounterOrderCount(String.valueOf(sql002Res.getDataList().size()));
            // SQL002の返却値を設定した店頭取引注文リストを作成
            List<IfaOrderStatusListDtoResponseFsStoreOrderStatus> fsStoreOrderStatus = getFsStoreOrderStatus(sql002Res);
            response.setFsStoreOrderStatusList(fsStoreOrderStatus);
        } else {
            // 商品区分が異なるなら取得件数に0件を設定
            response.setForeignStockCounterOrderCount(COUNT_DEFAULT);
            // 空のリストを設定
            response.setFsStoreOrderStatusList(new ArrayList<IfaOrderStatusListDtoResponseFsStoreOrderStatus>());
        }
        
        if (StringUtils.equals(cc.getForeignSecurityTradeAccountOpenStatus(),
                ForeignSecurityTradeAccountOpenStatus.OPEN.getId())) {
            // SQL004を呼びコールセンタ認証IDを取得する。
            DataList<IfaOrderStatusListSql004ResponseModel> sql004Res = getCallCenterAuthId();
            // SQL004の出力格納
            if (sql004Res.getDataList().size() > 0) {
                response.setCcsAuthId(sql004Res.getDataList().get(0).getCcsAuthId());
            }
        }
        
        resDto.add(response);
        return apiErrorUtil.createDataList(resDto, null);
    }
    
    /**
     * アクションID：A004 アクション名：外国株式店頭詳細表示 Dto
     * リクエスト：brokerageMenu.customerMenuA004DtoRequest Dto
     * レスポンス：brokerageMenu.customerMenuA004DtoResponse model
     * リクエスト：IfaOrderStatusListSql003RequestModel model
     * レスポンス：IfaOrderStatusListSql003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return IfaOrderStatusListA004DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListA004DtoResponse> foreignStockCounterDetailDisplayA004(
            IfaOrderStatusListA004DtoRequest dtoReq) throws Exception {
        
        List<IfaOrderStatusListA004DtoResponse> resDto = new ArrayList<IfaOrderStatusListA004DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOrderStatusListServiceImplL.foreignStockCounterDetailDisplayA004");  
        }
      
        // SQL003を呼び注文レコードを確認する
        DataList<IfaOrderStatusListSql003ResponseModel> sql003Res = getCounterOrder(dtoReq.getManageNumber());
        if (StringUtils.equals(sql003Res.getDataList().get(0).getCount(), "0")) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_EXCLUSIVE,
                    IfaCommonUtil.getMessage(ERRORS_EXCLUSIVE, new String[] { ORDER }));
        }
        return IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * API001呼び出し処理
     *
     * @param refFrom     検索番号指定From
     * @param refTo       検索番号指定To
     * @param execOrder   リクエスト区分
     * @param orderNo     EC受注番号
     * @param torihikiKbn 取引区分
     * @param apiErrorUtil エラーハンドリング
     * @return API結果
     * 
     */
    private List<QueryStockUniteOrderPointOrd> callApi001(String refFrom, String refTo, String execOrder, String orderNo,
            String torihikiKbn, ApiErrorUtil apiErrorUtil) throws Exception {
        
        QueryStockUniteOrderPointInData inData = new QueryStockUniteOrderPointInData();
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード
        inData.setButenCd(cc.getButenCode());
        // 口座番号
        String kozaNo = String.format(KOZA_NO_FORMAT, cc.getAccountNumber()).replace(" ", "0");
        inData.setKozaNo(kozaNo);
        // リクエスト区分
        inData.setExecOrder(execOrder);
        // 検索番号指定FROM
        inData.setRefFrom(String.format(REF_FROMTO_FORMAT, Integer.parseInt(refFrom)));
        // 検索番号指定TO
        inData.setRefTo(String.format(REF_FROMTO_FORMAT, Integer.parseInt(refTo)));
        // EC受注番号
        inData.setOrderNo(orderNo);
        // 取引区分
        inData.setTorihikiKbn(torihikiKbn);
        // 約定取得区分
        inData.setTradeGetKbn(API001_TRADE_GET_KBN);
        // 銘柄コード
        inData.setBrandCd(API001_BRAND_CD);
        // 取得口座区分
        if (StringUtils.equals(cc.getJrIsaContractType(), JrIsaContractType.CONTRACT.getId())) {
            inData.setAccountGetKbn(AccountType.ALL.getId());
        } else {
            inData.setAccountGetKbn(AccountType.WHOLE.getId());
        }
        QueryStockUniteOrderPointIn input = new QueryStockUniteOrderPointIn();
        input.setIndata(inData);
        
        QueryStockUniteOrderPointOutData result = new QueryStockUniteOrderPointOutData();
        
        // NRI_APIから注文状況を取得する。
        LOGGER.info("IfaOrderStatusListServiceImpL.callApi001");
        LOGGER.info("API input(queryStockUniteOrderPointForOrderStatusList) : {}", ReflectionToStringBuilder.toString(inData,ToStringStyle.MULTI_LINE_STYLE));
        
        result = nriApiService.queryStockUniteOrderPointForOrderStatusList(input.getIndata(), true);
        
        List<QueryStockUniteOrderPointOrd> resultList = new ArrayList<QueryStockUniteOrderPointOrd>();
        if (!apiErrorUtil.isError(result.getShubetu(), result.getCode(), result.getMessage())) {
            for (QueryStockUniteOrderPointOrd r : result.getReqOrderDataExe()) {
                LOGGER.info("API output(queryStockUniteOrderPointForOrderStatusList.ReqOrderDataExe) : {}", ReflectionToStringBuilder.toString(r, ToStringStyle.MULTI_LINE_STYLE));
            }
            resultList.addAll(result.getReqOrderDataExe());
        }
        
        // 取得件数が100件ならfromとtoの値を変えて再度自身を呼ぶ
        if (result != null) {
            if (result.getGetNumber() == API001_GETNUM) {
                String refFromNext = String.format(REF_FROMTO_FORMAT, Integer.parseInt(refFrom) + 100);
                String refToNext = String.format(REF_FROMTO_FORMAT, Integer.parseInt(refTo) + 100);
                List<QueryStockUniteOrderPointOrd> next100Data = callApi001(refFromNext, refToNext, execOrder, orderNo, torihikiKbn, apiErrorUtil);
                resultList.addAll(next100Data);
            }
        }
        return resultList;
    }
    
    /**
     * API002呼び出し処理
     *
     * @param refFrom     検索番号指定From
     * @param refTo       検索番号指定To
     * @param execOrder   リクエスト区分
     * @param orderNo     EC受注番号
     * @param torihikiKbn 取引区分
     * @param apiErrorUtil　エラーハンドリング
     * @return API結果
     * 
     */
    private List<QueryFundOrderWebExtDetail> callApi002(String refFrom, String refTo, ApiErrorUtil apiErrorUtil) throws Exception {
        
        QueryFundOrderWebExtInData inData = new QueryFundOrderWebExtInData();
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード
        inData.setButenCd(cc.getButenCode());
        // 口座番号
        String kozaNo = String.format(KOZA_NO_FORMAT, cc.getAccountNumber()).replace(" ", "0");
        inData.setKozaNo(kozaNo);
        // 商品区分
        inData.setSecId(API002_SEQ_ID);
        // 約定注文区分
        inData.setExecOrder(API002_EXEC_ORDER);
        // 検索番号指定FROM
        inData.setRefFrom(String.format(REF_FROMTO_FORMAT, Integer.parseInt(refFrom)));
        // 検索番号指定TO
        inData.setRefTo(String.format(REF_FROMTO_FORMAT, Integer.parseInt(refTo)));
        // 取得口座区分
        if (StringUtils.equals(cc.getJrIsaContractType(), JrIsaContractType.CONTRACT.getId())) {
            inData.setAccountGetKbn(AccountType.ALL.getId());
        } else {
            inData.setAccountGetKbn(AccountType.WHOLE.getId());
        }
        
        QueryFundOrderWebExtIn input = new QueryFundOrderWebExtIn();
        input.setIndata(inData);
        
        QueryFundOrderWebExtOutData result = new QueryFundOrderWebExtOutData();
        // NRI_APIから注文状況を取得する。
        result = apiWrapper.queryFundOrderWebExt(input);
        
        List<QueryFundOrderWebExtDetail> resultList = new ArrayList<QueryFundOrderWebExtDetail>();
        if (!apiErrorUtil.isError(result.getShubetu(), result.getCode(), result.getMessage())) {
            resultList.addAll(result.getQueryFundOrderWebData());
        }
        
        // 取得件数が100件ならfromとtoの値を変えて再度自身を呼ぶ
        if (result.getTotalNumber() == API002_GETNUM) {
            String refFromNext = String.format(REF_FROMTO_FORMAT, Integer.parseInt(refFrom) + 100);
            String refToNext = String.format(REF_FROMTO_FORMAT, Integer.parseInt(refTo) + 100);
            List<QueryFundOrderWebExtDetail> next100Data = callApi002(refFromNext, refToNext, apiErrorUtil);
            resultList.addAll(next100Data);
        }
        
        return resultList;
    }
    
    /**
     * API003呼び出し処理
     *
     * @param orderSubNo 注文サブ番号
     * @return API003出力結果
     */
    private ListForeignStockOrdersResp listForeignStockOrders(String countryCode, GetOffsetBusinessDateResp api004)
            throws Exception {
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コードと口座番号取得
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        /* 外国株式信用注文取消初期情報取得APIの戻り値を返す */
        return foreignStockService.listForeignStockOrders(butenCode, accountNumber, countryCode, null, null, null,
                api004.getBusinessDate(), api004.getBusinessDate(), API003_DATE_TYPE, null, null);
    }
    
    /**
     * API004呼び出し処理
     *
     * @param orderSubNo 注文サブ番号
     * @return API004出力結果
     */
    private GetOffsetBusinessDateResp getOffsetBusinessDate(String countryCode, Integer businessDateOffset)
            throws Exception {
        
        // パラメータ: 基準日
        LocalDate currentDate = LocalDate.now();
        /* 外国株式信用注文取消初期情報取得APIの戻り値を返す */
        return foreignStockService.getOffsetBusinessDate(countryCode, String.valueOf(currentDate), businessDateOffset);
    }
    
    /**
     * FCT001チェック
     *
     * @param butenCode     部店コード
     * @param accountNumber 口座番号
     * @return チェック結果
     */
    private OutputFct001Dto callFct001(String butenCode, String accountNumber) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        
        return output;
    }
    
    /**
     * FCT003チェック
     *
     * @param butenCode     部店コード
     * @param accountNumber 口座番号
     * @param tradeCd       取引種別
     * @return output チェック結果
     */
    private OutputFct003Dto callFct003(String butenCode, String accountNumber) {
        
        InputFct003Dto input = new InputFct003Dto();
        // 部店コード
        input.setButenCode(butenCode);
        // 口座番号
        input.setAccountNumber(accountNumber);
        
        OutputFct003Dto output = fct003.doCheck(input);
        
        return output;
    }
    
    /**
     * FCT037チェック
     *
     * @param api001data  api001の一レコード文のデータ
     * @param orderStatus 注文状況(算出)
     * @return output fct037の出力結果
     */
    private OutputFct037Dto callFct037(QueryStockUniteOrderPointOrd api001data, String orderStatus) {
        
        InputFct037Dto input = new InputFct037Dto();
        
        // 注文訂正ステータス
        if (StringUtils.equals(orderStatus, FCT37_ORDER)) {
            input.setOrderCorrectStatus(FCT37_STATUS_CORRECT);
        } else {
            input.setOrderCorrectStatus(FCT37_STATUS_NOTCORRECT);
        }
        // 自動注文種別
        input.setAutoOrderClass(api001data.getAutoOrderKind());
        // RBE注文種別
        input.setRbeChumonShubetsu(api001data.getRbeOrderKind());
        // RBE注文ステータス
        input.setRbeOrderStatus(api001data.getRbeOrderStatus());
        // 指成区分
        input.setSashinariKbn(
                codeListService.convertKeyToExtKey("LIMIT_MARKET_TYPE", "EC-GW", api001data.getSasinariId()));
        // 指値
        input.setSashine(api001data.getLimitPrice());
        // 直近トリガ発動ゾーン
        input.setLatestTriggerZone(api001data.getLatestTriggerZone());
        // 直近トリガ値段
        input.setLatestTriggerNedan(api001data.getLatestTriggerPrice());
        // 直近OCO指成区分
        input.setLatestOcoLimitMarketType(api001data.getLatestOcoSasinariId());
        // 直近OCO値段
        input.setLatestOcoLimitlimitPrice(api001data.getLatestOcoPrice());
        // DONE RBE注文種別
        input.setDoneRbeOrderClass(api001data.getDoneRbeOrderKind());
        // DONE 指成区分
        input.setDoneLimitMarketType(api001data.getDoneSasinariId());
        // DONE 指値
        input.setDoneLimitPrice(api001data.getDonePrice());
        // DONE トリガ発動ゾーン
        input.setDoneTriggerZone(api001data.getDoneTriggerZone());
        // DONE トリガ値段
        input.setDoneTriggerNedan(api001data.getDoneTriggerPrice());
        // DONE OCO指成区分
        input.setDoneOcoLimitMarketType(api001data.getDoneOcoSasinariId());
        // DONE OCO指値
        input.setDoneOcoLimitPrice(api001data.getDoneOcoPrice());
        // DONE 有効期限
        input.setDoneExpirationDate(api001data.getDoneLimit());
        
        OutputFct037Dto output = fct037.getData(input);
        
        return output;
    }
    
    /**
     * SQL001 募集注文情報を取得
     *
     * @param brandCode   銘柄コード
     * @param depositType 預かり区分
     * @return SQL001の出力結果
     * @throws Exception SQLExceptionなど
     */
    
    private DataList<IfaOrderStatusListSql001ResponseModel> getSubscriptOrder(String brandCode, String depositType)
            throws Exception {
        
        IfaOrderStatusListSql001RequestModel sql001Req = new IfaOrderStatusListSql001RequestModel();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 部店コード（半角英数字）
        sql001Req.setButenCode(cc.getButenCode());
        
        // 口座番号（数字）
        sql001Req.setAccountNumber(cc.getAccountNumber());
        
        // 銘柄コード（半角英数字）
        sql001Req.setBrandCode(brandCode);
        
        // 預かり区分
        sql001Req.setDepositType(depositType);
        
        return dao.selectIfaOrderStatusListSql001(sql001Req);
    }
    
    /**
     * SQL002 募集注文情報を取得
     *
     * @param brandCode   銘柄コード
     * @param depositType 預かり区分
     * @return SQL002の出力結果
     * @throws Exception SQLExceptionなど
     */
    
    private DataList<IfaOrderStatusListSql002ResponseModel> getForeignStockCounterOrderInfo(String manageNumber)
            throws Exception {
        
        IfaOrderStatusListSql002RequestModel sql002Req = new IfaOrderStatusListSql002RequestModel();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 部店コード（半角英数字）
        sql002Req.setButenCode(cc.getButenCode());
        
        // 口座番号（数字）
        sql002Req.setAccountNumber(cc.getAccountNumber());
        
        // 銘柄コード（半角英数字）
        sql002Req.setManageNumber(manageNumber);
        
        return dao.selectIfaOrderStatusListSql002(sql002Req);
    }
    
    /**
     * SQL003 店頭取引注文(T_COUNTER_ORDER)から該当する管理番号のレコード数を取得する。
     *
     * @param brandCode   銘柄コード
     * @param depositType 預かり区分
     * @return SQL002の出力結果
     * @throws Exception SQLExceptionなど
     */
    
    private DataList<IfaOrderStatusListSql003ResponseModel> getCounterOrder(String manageNumber) throws Exception {
        
        IfaOrderStatusListSql003RequestModel sql003Req = new IfaOrderStatusListSql003RequestModel();
        
        // 管理番号
        sql003Req.setManageNumber(manageNumber);
        
        return dao.selectIfaOrderStatusListSql003(sql003Req);
    }
    
    /**
     * SQL004 CCS認証ID取得を取得
     *
     * @param brandCode   銘柄コード
     * @param depositType 預かり区分
     * @return SQL004の出力結果
     * @throws Exception SQLExceptionなど
     */
    
    private DataList<IfaOrderStatusListSql004ResponseModel> getCallCenterAuthId() throws Exception {
        
        IfaOrderStatusListSql004RequestModel sql004Req = new IfaOrderStatusListSql004RequestModel();
        return dao.selectIfaOrderStatusListSql004(sql004Req);
    }
    
    /**
     * FCT003の出力結果を元に、媒介可否リストに対応する値を設定
     *
     * @param api001result API001の出力結果
     * @return result 媒介可否リスト
     */
    private List<IfaOrderStatusListDtoResponseIntermediaryValue> getIntermediaryValue(OutputFct003Dto fct003Res) {
        
        List<IfaOrderStatusListDtoResponseIntermediaryValue> result = new ArrayList<IfaOrderStatusListDtoResponseIntermediaryValue>();
        if (fct003Res == null) {
            return result;
        }
        if (fct003Res.getMediateProprietyList() == null) {
            return result;
        }
        for (MediatePropriety m : fct003Res.getMediateProprietyList()) {
            IfaOrderStatusListDtoResponseIntermediaryValue intermediaryValue = new IfaOrderStatusListDtoResponseIntermediaryValue();
            // 証券金銭種別（全角半角）
            intermediaryValue.setProductCd(m.getSecurityMoneyClass());
            // 取引種別（全角半角）
            intermediaryValue.setTradeCd(m.getTradeClass());
            // 国籍コード（全角半角）
            intermediaryValue.setCountryCd(m.getNationalityCode());
            // 媒介可否
            intermediaryValue.setMediatePropriety(m.getMediatePropriety());
            result.add(intermediaryValue);
        }
        return result;
    }
    
    /**
     * API001の出力結果を元に、国内株式注文一覧に対応する値を設定
     *
     * @param api001result API001の出力結果
     * @return result 国内株式注文一覧リスト
     */
    private List<IfaOrderStatusListDtoResponseDomesticStockOrder> getDomesticStockOrderResult(
            List<QueryStockUniteOrderPointOrd> api001result) {
        
        List<IfaOrderStatusListDtoResponseDomesticStockOrder> result = new ArrayList<IfaOrderStatusListDtoResponseDomesticStockOrder>();
        
        for (QueryStockUniteOrderPointOrd r : api001result) {
            IfaOrderStatusListDtoResponseDomesticStockOrder param = new IfaOrderStatusListDtoResponseDomesticStockOrder();
            
            // 商品区分ごとの算出値を格納する
            // 商品区分が株式の場合
            if (StringUtils.equals(r.getSecId(), SEC_ID_STOCK)) {
                setDomesticStockOrderResultForStock(param, r);
            } else if (StringUtils.equals(r.getSecId(), SEC_ID_GENHIKI)) { // 商品区分が現引現渡の場合
                setDomesticStockOrderResultForGenhiki(param, r);
            }
            
            // 各種商品区分共通の算出値を格納する
            
            // 注文種別(算出)
            param.setOrderStatusListOrderClass(getDomesticOrderType(r));
            
            // 残りの項目を設定
            // EC受注番号（半角英数字）
            param.setEcOrderNo(r.getOrderNo());
            // 商品区分（全角半角）
            param.setSecurityType(r.getSecId());
            // 有効期限（全角半角）
            param.setOrderPeriod(r.getLimit());
            // マーケット発注日
            param.setMarketOrderDate(r.getMktOrderDate());
            // 強制区分（全角半角）
            param.setCoercionType(r.getForceKbn());
            // 直近市場
            param.setLatestMarketId(r.getLatestMarketId());
            // SOR連携区分（全角半角）
            param.setSorLinkKbn(r.getSorLinkKbn());
            // 国内株式銘柄コード
            param.setBrandCode(r.getStockSecCode());
            // 漢字銘柄名
            param.setBrandName(r.getSecName());
            // 非特定預り売買区分（全角半角）
            param.setNotSpecificDepositTradeType(r.getHitokuteiTradeKbn());
            // 採用手数料区分
            param.setTradingCoursText(r.getComIdR());
            // 利用Ｔポイント
            param.setPoint(r.getPointOrder());
            // 注文受注日時
            param.setOrderDayTime(r.getInputDate());
            // 受注数量（数値(整数)）
            param.setOrderQuantity(r.getQuantity());
            
            // FCT037を呼び出し、注文条件を設定
            OutputFct037Dto fct037Res = callFct037(r, param.getOrderStatus());
            param.setConditions(fct037Res.getOrderConditions());
            result.add(param);
        }
        return result;
    }
    
    /**
     * 商品区分「株式」時の項目設定処理
     *
     * @param param IfaOrderStatusListDtoResponseDomesticStockOrder
     * @param r     API取得データ
     */
    private void setDomesticStockOrderResultForStock(IfaOrderStatusListDtoResponseDomesticStockOrder param,
            QueryStockUniteOrderPointOrd r) {
        
        String combInfo = StringUtil.EMPTY_STRING;
        switch (r.getRbeOrderKind()) {
            case RBE_ORDER_KIND_NORMAL:
                // RBE注文種別が通常注文
                combInfo = getNomalResult(r);
                break;
            case RBE_ORDER_KIND_SLO:
                // RBE注文ステータスが通常注文または発火済みの場合通常注文処理
                if (StringUtils.equals(r.getRbeOrderStatus(), RBE_ORDER_STATUS_NORMAL)
                        || StringUtils.equals(r.getRbeOrderStatus(), RBE_ORDER_STATUS_STOP)) {
                    combInfo = getNomalResult(r);
                    break;
                }
                // RBE注文種別が逆指値注文の場合
                combInfo = getSloResult(r);
                break;
            case RBE_ORDER_KIND_OCO:
                // RBE注文ステータスが通常注文または発火済みの場合通常注文処理
                if (StringUtils.equals(r.getRbeOrderStatus(), RBE_ORDER_STATUS_NORMAL)
                        || StringUtils.equals(r.getRbeOrderStatus(), RBE_ORDER_STATUS_STOP)) {
                    combInfo = getNomalResult(r);
                    break;
                }
                // RBE注文種別がOCO注文の場合
                combInfo = getOcoResult(r);
                break;
            default:
                break;
        }
        
        // 取引種別の算出
        ConvertCalcDomesticBuySellTypeName outputValue = ConvertCalcDomesticBuySellTypeName
                .valueOfId(createMatrixid(r.getSettleCode(), r.getBuySell(), null));
        String buySellTypeName = outputValue != null ? outputValue.getLabel() : null;
        
        // 市場の算出
        String market = null;
        if (StringUtils.equals(r.getOddStockOrderFlg(), ODD_STACK_ORDERFLAG_ORDER)) {
            market = NONE_OUTPUT;
        } else {
            if (StringUtils.equals(r.getSorLinkKbn(), SORLINKKBN_FORWORD)) {
                market = DOMESTIC_MARKET_SOR;
            } else if (StringUtils.equals(r.getSorLinkKbn(), SORLINKKBN_LINKED)) {
                market = DOMESTIC_MARKET_SOR;
            } else {
                market = r.getLatestMarketId();
            }
        }
        
        setDomesticStockOrderResultCalcColumns(r, param, combInfo, r.getExecLeftQuantity(), buySellTypeName, market);
        /* 株式時のみ設定する項目 */
        // 執行方法(算出)・価格(算出)
        if (!StringUtils.equals(param.getOrderStatus(), CALC_ORDER_STATUS_WAITING)) {
            param.setOrderExecuteMethodList(r.getSasinariId());
            param.setPrice(r.getLimitPrice());
        } else {
            param.setOrderExecuteMethodList(NONE_OUTPUT);
            param.setPrice(NONE_OUTPUT);
        }
        
    }
    
    /**
     * 商品区分「現引現渡」時の項目設定処理
     *
     * @param param IfaOrderStatusListDtoResponseDomesticStockOrder
     * @param r     API取得データ
     */
    private void setDomesticStockOrderResultForGenhiki(IfaOrderStatusListDtoResponseDomesticStockOrder param,
            QueryStockUniteOrderPointOrd r) {
        
        String combInfo = getReceiptDeliveryResult(r);
        // 取引種別の算出
        String buySellTypeName = null;
        if (StringUtils.equals(r.getSettlementId(), SETTLEMENT_ID_GENWATASI)) {
            buySellTypeName = DOMESTIC_BUYSELLTYPE_RECEIPT;
        } else if (StringUtils.equals(r.getSettlementId(), SETTLEMENT_ID_GENHIKI)) {
            buySellTypeName = DOMESTIC_BUYSELLTYPE_DELIVERY;
        }
        
        setDomesticStockOrderResultCalcColumns(r, param, combInfo, r.getQuantity(), buySellTypeName, NONE_OUTPUT);
        
    }
    
    /**
     * 各算出項目設定処理
     *
     * @param r                     　API001のレスポンス
     * @param param 算出結果の格納対象
     * @param combInfo               算出された注文状況/注文状況補足/訂正ボタン表示/取消ボタン（カンマ区切り持ち）
     * @param unMatchedTradeQuantity 未出来数量
     * @param buySellTypeName        取引種別
     * @param market                 市場
     */
    private void setDomesticStockOrderResultCalcColumns(QueryStockUniteOrderPointOrd r,
            IfaOrderStatusListDtoResponseDomesticStockOrder param, String combInfo, String unMatchedTradeQuantity,
            String buySellTypeName, String market) {
        
        if (!StringUtil.isNullOrEmpty(combInfo) && combInfo.split(",").length > 0) {
            String[] split = combInfo.split(",");
            // 注文状況(算出)を設定
            param.setOrderStatus(StringUtil.isNullOrEmpty(split[0]) ? NONE_OUTPUT : split[0]);
            // 注文状況補足(算出)を設定
            //　格納されている値が"-"でなければ算出処理
            String orderStatusSupplement = StringUtil.isNullOrEmpty(split[1]) ? NONE_OUTPUT_HYPHEN : split[1];
            if (!StringUtils.equals(orderStatusSupplement, NONE_OUTPUT_HYPHEN)) {
                orderStatusSupplement = getOrderStatusSupplement(r, orderStatusSupplement);
            }
            param.setOrderStatusSupplement(orderStatusSupplement);

            // 下記のいずれかの条件に当てはまる場合は、別紙を参照せず、訂正ボタン、取消ボタンを非表示に設定する。
            // ・API001.注文部.端株注文フラグ ＝ 「"1":端株注文」 の場合
            // ・API001.注文部.採用手数料区分 ＝ 「"4":立会外分売手数料」 かつ API001.注文部.手数料区分 ＝ 「"4":立会外分売手数料」 の場合
            // ・API001.注文部.採用手数料区分 ＝ 「"6":リテールオファー手数料」 かつ API001.注文部.手数料区分 ＝ 「"6":リテールオファー手数料」 の場合
            if (
                    StringUtils.equals(r.getOddStockOrderFlg(), ODD_STOCK_ORDER_FLAG_TRUE)
                    || (
                            StringUtils.equals(r.getComIdR(), COMM_TYPE_OFF_FLOOR_DISTRIBUTION_INDIVISUAL)
                            && StringUtils.equals(r.getComId(), COMM_TYPE_OFF_FLOOR_DISTRIBUTION_INDIVISUAL)
                    )
                    || (
                            StringUtils.equals(r.getComIdR(), COMM_TYPE_RETAIL_OFFER)
                            && StringUtils.equals(r.getComId(), COMM_TYPE_RETAIL_OFFER)
                    )
            ) {
                param.setDomesticStockCorrectButtonDisplayJudgment(DISABLE_BUTTON_PH1);
                param.setDomesticStockCancelButtonDisplayJudgment(DISABLE_BUTTON_PH1);

            } else {
                // 訂正ボタン表示判定（算出）
                param.setDomesticStockCorrectButtonDisplayJudgment(
                        StringUtil.isNullOrEmpty(split[2]) ? NONE_OUTPUT : split[2]);
                // 取消ボタン表示判定（算出）
                param.setDomesticStockCancelButtonDisplayJudgment(
                        StringUtil.isNullOrEmpty(split[3]) ? NONE_OUTPUT : split[3]);
            }
        }
        // 未出来数量(算出)
        param.setUnMatchedTradeQuantity(unMatchedTradeQuantity);
        // 取引種別(算出)
        param.setBuySellTypeName(buySellTypeName);
        // 市場(算出)
        param.setMarket(market);
        // 信用取引区分(算出)
        param.setMarginTradeTypeText(getDomesticMarginTradeTypeText(r));

    }
    
    //　注文状況補足(算出)の算出処理
    private String getOrderStatusSupplement(QueryStockUniteOrderPointOrd r, String input) {
        
        String output = NONE_OUTPUT_HYPHEN;
        if (StringUtils.equals(input, null)) {
            return output;
        }
        
        //WEB注文ステータスが'1':ACCEPTED または '2':DEFFEREDであるかを先に確認
        boolean webState = false;
        //WEB注文ステータス が '1':ACCEPTED または '2':DEFFERED の場合取消できずを設定
        if (StringUtils.equals(r.getWebOrderStatus(), WEB_ORDER_STATUS_ACCEPTED)
                || StringUtils.equals(r.getWebOrderStatus(), WEB_ORDER_STATUS_DEFFERED)) {
            webState = true;
        }
        
        switch (input) {
            //※１のパターンの処理
            case ORDER_STATUS_SUPPLEMENT_ONE:
                //約定ステータスが一部出来なら一部約定を設定
                if (StringUtils.equals(r.getExecStatus(), EXEC_STATUS_PART)) {
                    output = ORDER_STATUS_SUPPLEMENT_PART_TRADE;
                }
                break;
        
            //※2のパターンの処理
            case ORDER_STATUS_SUPPLEMENT_TWO:
                //WEB注文ステータス が '1':ACCEPTED または '2':DEFFERED の場合取消できずを設定
                if (webState) {
                    output = ORDER_STATUS_SUPPLEMENT_NOT_CANSELABEL;
                }
                break;
        
            //※3のパターンの処理
            case ORDER_STATUS_SUPPLEMENT_THREE:
                //RBE注文種別がOCO注文かチェック
                if (StringUtils.equals(r.getRbeOrderKind(), RBE_ORDER_KIND_OCO)) {
                    //特殊入力区分 ≠ '1':OCO発火訂正であり、WEB注文ステータス が '1':ACCEPTED または '2':DEFFEREDなら（訂正エラー）を設定
                    if (!StringUtils.equals(r.getSpInputKbn(), SP_INPUT_KBN_OCO_CORRECT) && webState) {
                        output = ORDER_STATUS_SUPPLEMENT_CORRECT_ERROR;
                    }
                } else {
                    //WEB注文ステータス が '1':ACCEPTED または '2':DEFFERED の場合訂正できずを設定
                    if (webState) {
                        output = ORDER_STATUS_SUPPLEMENT_NOT_CORRECTABEL;
                    }
                }
                break;
        
            //※4のパターンの処理
            case ORDER_STATUS_SUPPLEMENT_FOUR:
                //特殊入力区分 ≠ '1':OCO発火訂正であり、WEB注文ステータス が '1':ACCEPTED または '2':DEFFEREDなら（訂正できず）を設定
                if (!StringUtils.equals(r.getSpInputKbn(), SP_INPUT_KBN_OCO_CORRECT) && webState) {
                    output = ORDER_STATUS_SUPPLEMENT_NOT_CORRECTABEL;
                }
                break;
            default:
                break;
        }
        
        return output;
    }
    
    /**
     * 結果算出(通常注文)
     *
     * @param QueryStockUniteOrderPointOrd API001の出力結果
     * @return 注文状況/注文状況補足/訂正ボタン表示/取消ボタン（カンマ区切り持ち）
     */
    private String getNomalResult(QueryStockUniteOrderPointOrd reqOrderData) {
        
        /* 特殊パターンの判定--------- */
        // 強制区分が通常注文でない
        if (!StringUtils.equals(reqOrderData.getForceKbn(), FORCEKBN_DEFAULT)) {
            // 約定ステータスが全部出来ではない
            if (!StringUtils.equals(reqOrderData.getExecStatus(), EXEC_STATUS_ALL)) {
                return NORMAL_FORCE_OUTPUT;
            // 約定ステータスが全部出来
            } else {
                return NORMAL_FORCE_EXECSTATUS_OUTPUT;
            }
        }
        // 失効ステータスが有効で無いなら、値を設定
        if (!StringUtils.equals(reqOrderData.getRejectStatus(), REJECTSTATUS_ACTIVE)) {
            return NORMAL_REJECT_OUTPUT;
        }
        // 約定ステータスが全部出来なら、値を設定
        if (StringUtils.equals(reqOrderData.getExecStatus(), EXEC_STATUS_ALL)) {
            return NORMAL_EXECSTATUS_OUTPUT;
        }
        // 有効期限がマーケット発注日より前なら、値を設定
        if ((reqOrderData.getLimit().compareTo(reqOrderData.getMktOrderDate()) < 0)) {
            return NORMAL_LIMIT_OUTPUT;
        }
        /* 各項目の組み合わせからの判定--------- */
        // WEB注文ステータスが"3":REJECTEDか否かを出力
        String webOrder = WEB_ORDER_STATUS_REJECTED;
        if (!StringUtils.equals(reqOrderData.getWebOrderStatus(), WEB_ORDER_STATUS_REJECTED)) {
            webOrder = WEB_ORDER_STATUS_NOT_REJECTED;
        }
        // WEB注文ステ-タス、訂正ステ-タス、取消ステ-タスから算出
        String input = createMatrixid(webOrder, reqOrderData.getModStatus(), reqOrderData.getCxlStatus());
        
        ConvertNormal outputValue = ConvertNormal.valueOfId(input);
        if (outputValue == null) {
            return StringUtil.EMPTY_STRING;
        }
        
        return outputValue.getLabel();
    }
    
    /**
     * 結果算出(逆指値注文)
     *
     * @param QueryStockUniteOrderPointOrd API001の出力結果
     * @return 注文状況/注文状況補足/訂正ボタン表示/取消ボタン（カンマ区切り持ち）
     */
    private String getSloResult(QueryStockUniteOrderPointOrd reqOrderData) {
        
        /* 特殊パターンの判定--------- */
        // 失効ステータスが有効で無いなら、値を設定
        if (!StringUtils.equals(reqOrderData.getRejectStatus(), REJECTSTATUS_ACTIVE)) {
            return SLO_REJECT_OUTPUT;
        }
        // 約定ステータスが全部出来なら、値を設定
        if (StringUtils.equals(reqOrderData.getExecStatus(), EXEC_STATUS_ALL)) {
            return SLO_EXECSTATUS_OUTPUT;
        }
        // WEB注文ステータスがREJECTEDなら、値を設定
        if (StringUtils.equals(reqOrderData.getWebOrderStatus(), WEB_ORDER_STATUS_REJECTED)) {
            return SLO_WEB_OUTPUT;
        }
        // 有効期限がマーケット発注日より前なら、値を設定
        if ((reqOrderData.getLimit().compareTo(reqOrderData.getMktOrderDate()) < 0)) {
            return SLO_LIMIT_OUTPUT;
        }
        
        /* 各項目の組み合わせからの判定--------- */
        // RBE注文ステ-タス、RBE訂正ステ-タス、RBE取消ステ-タスから算出
        String input = createMatrixid(reqOrderData.getRbeOrderStatus(), reqOrderData.getRbeModStatus(),
                reqOrderData.getRbeCxlStatus());
        ConvertSlo outputValue = ConvertSlo.valueOfId(input);
        if (outputValue == null) {
            return StringUtil.EMPTY_STRING;
        }
        
        return outputValue.getLabel();
    }
    
    /**
     * 結果算出(OCO注文)
     *
     * @param QueryStockUniteOrderPointOrd API001の出力結果
     * @return 注文状況/注文状況補足/訂正ボタン表示/取消ボタン（カンマ区切り持ち）
     */
    private String getOcoResult(QueryStockUniteOrderPointOrd reqOrderData) {
        
        /* 特殊パターンの判定--------- */
        // 強制区分が通常注文でない
        if (!StringUtils.equals(reqOrderData.getForceKbn(), FORCEKBN_DEFAULT)) {
            // 約定ステータスが全部出来ではない
            if (!StringUtils.equals(reqOrderData.getExecStatus(), EXEC_STATUS_ALL)) {
                return OCO_FORCE_OUTPUT;
            // 約定ステータスが全部出来ではない
            } else {
                return OCO_FORCE_EXECSTATUS_OUTPUT;
            }
        }
        // 失効ステータスが有効で無いなら、値を設定
        if (!StringUtils.equals(reqOrderData.getRejectStatus(), REJECTSTATUS_ACTIVE)) {
            return OCO_REJECT_OUTPUT;
        }
        // 約定ステータスが全部出来なら、値を設定
        if (StringUtils.equals(reqOrderData.getExecStatus(), EXEC_STATUS_ALL)) {
            return OCO_EXECSTATUS_OUTPUT;
        }
        // WEB注文ステータスがREJECTEDなら、値を設定
        if (StringUtils.equals(reqOrderData.getWebOrderStatus(), WEB_ORDER_STATUS_REJECTED)) {
            return OCO_WEB_OUTPUT;
        }
        // 有効期限がマーケット発注日より前なら、値を設定
        if ((reqOrderData.getLimit().compareTo(reqOrderData.getMktOrderDate()) < 0)) {
            return OCO_LIMIT_OUTPUT;
        }
        
        /* 各項目の組み合わせからの判定--------- */
        // 訂正ステータスとRBE訂正ステータスから変数訂正ステータスを算出
        String varInput = createMatrixid(reqOrderData.getModStatus(), reqOrderData.getRbeModStatus(), null);
        ConvertCalcVarModStatus outputValueVar = ConvertCalcVarModStatus.valueOfId(varInput);
        if (outputValueVar == null) {
            return StringUtil.EMPTY_STRING;
        }
        
        // RBE注文ステ-タス、取消ステ-タス、変数訂正ステ-タスから算出
        String input = createMatrixid(reqOrderData.getRbeOrderStatus(), reqOrderData.getCxlStatus(),
                outputValueVar.getLabel());
        
        // 算出処理
        ConvertOco outputValue = ConvertOco.valueOfId(input);
        if (outputValue == null) {
            return StringUtil.EMPTY_STRING;
        }
        
        String result = outputValue.getLabel();
        
        return result;
        
    }
    
    /**
     * 結果算出(現引現渡注文)
     *
     * @param QueryStockUniteOrderPointOrd API001の出力結果
     * @return 注文状況/注文状況補足/訂正ボタン表示/取消ボタン（カンマ区切り持ち）
     */
    private String getReceiptDeliveryResult(QueryStockUniteOrderPointOrd reqOrderData) {
        
        // 強制区分が通常注文でない
        if (!StringUtils.equals(reqOrderData.getForceKbn(), FORCEKBN_DEFAULT)) {
            return RECEIPTDELIVERY_FORCE_OUTPUT;
        }
        // 失効ステータスが有効で無いなら、値を設定
        if (!StringUtils.equals(reqOrderData.getRejectStatus(), REJECTSTATUS_ACTIVE)) {
            return RECEIPTDELIVERY_REJECT_OUTPUT;
        }
        // 約定取消区分が取消済みではなく、約定ステータスが出来済み(一部出来)なら、値を設定
        if (!StringUtils.equals(reqOrderData.getTradeCxlId(), TRADE_CXL_ID_CANCELLED)
                && StringUtils.equals(reqOrderData.getExecStatus(), EXEC_STATUS_PART)) {
            return RECEIPTDELIVERY_TRADE_OUTPUT;
            
        }
        // 注文ステ-タス、取消ステ-タスから算出
        String input = createMatrixid(reqOrderData.getOrderStatus(), reqOrderData.getCxlStatus(),
                StringUtil.EMPTY_STRING);
        
        // 算出処理
        ConvertReceiptDelivery outputValue = ConvertReceiptDelivery.valueOfId(input);
        if (outputValue == null) {
            return StringUtil.EMPTY_STRING;
        }
        
        return outputValue.getLabel();
    }
    
    /**
     * API002の出力結果を元に、国内投信注文一覧に対応する値を設定
     *
     * @param api002result API002の出力結果
     * @return result 国内投信注文一覧リスト
     */
    private List<IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus> getDomesticMutualFundOrderStatusResult(
            List<QueryFundOrderWebExtDetail> api002result) {
        
        List<IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus> result = new ArrayList<IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus>();
        
        for (QueryFundOrderWebExtDetail r : api002result) {
            // 出力結果を変換してresultに格納
            IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus param = new IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus();
            
            // 注文状況(算出)
            param.setOrderStatus(getMutualFundOrderStatus(r));
            // 取引種別(算出)
            param.setBuySellTypeName(getMutualFundSellTypeName(r));
            // 数量(算出)
            param.setUnitAmount(getMutualFundUnitAmount(r));
            // 数量単位(算出)
            param.setUnit(getMutualFundUnit(r));
            // 取消ボタン表示判定(算出)
            param.setMutualFundCancelButtonDisplayJudgment(getMutualFundMutualFundCancelButtonDisplayJudgment(r));
            
            // 残りの項目を設定
            // EC受注番号（半角英数字）
            param.setEcOrderNo(r.getOrdeNo());
            // 売買区分（全角半角）
            param.setTradeKbn(r.getBuySell());
            // ファンドコード（回数）（半角英数字）
            param.setFundCodeTimes(r.getFundCodeSerNo());
            // ファンドコード（号）（半角英数字）
            param.setFundCodeIssues(r.getFundCodeSub());
            // 銘柄名（全角半角）
            param.setBrandName(r.getSecName());
            // 非特定預り売買区分（全角半角）
            param.setNotSpecificDepositTradeType(r.getIsaKbn());
            // 発注日
            param.setOrderDate(r.getMktOrderDate());
            // 注文受付日時
            param.setOrderDayTime(r.getInputDate());
            // 再投資停止指定
            param.setDistributionReceiveMethod(r.getReinvest());
            // 受付経路区分
            param.setCallcenterKbn(r.getCallcenterKbn());
            // ポイント種別（全角半角）
            param.setPointType(r.getPointType());
            // 利用ポイント（数値(整数)）
            param.setPoint(r.getPointOrder());
            // 余力チェック区分（全角半角）
            param.setYoryokuCheckKbn(r.getCheckId());
            
            result.add(param);
        }
        return result;
    }
    
    /**
     * API003の出力結果を元に、外株委託注文一覧に対応する値を設定
     *
     * @param api003result API003の出力結果
     * @return result 外株委託注文一覧リスト
     */
    private List<IfaOrderStatusListDtoResponseForeignStockEntrustOrder> getForeignStockEntrustOrder() throws Exception {
        
        // 国別リスト
        List<IfaOrderStatusListDtoResponseForeignStockEntrustOrder> result = new ArrayList<IfaOrderStatusListDtoResponseForeignStockEntrustOrder>();
        
        // 国籍コード項目数分処理を実行
        for (NationalityCode n : NationalityCode.values()) {
        	
            if (n == NationalityCode.OT) {
                continue; // このコード(その他）はスキップ
            }
            
            // 国別リスト(該当国籍コード)
            IfaOrderStatusListDtoResponseForeignStockEntrustOrder foreignStockEntrustOrder = new IfaOrderStatusListDtoResponseForeignStockEntrustOrder();
            // 国別リスト.外株委託注文一覧
            List<IfaOrderStatusListDtoResponseFsEntrustOrderStatus> fsEntrustOrderStatusList = new ArrayList<IfaOrderStatusListDtoResponseFsEntrustOrderStatus>();
            
            // APIで営業日・前営業日の注文情報を取得
            GetOffsetBusinessDateResp api004ResToday = null;
            GetOffsetBusinessDateResp api004ResPrevious = null;
            ListForeignStockOrdersResp api003ResToday = null;
            ListForeignStockOrdersResp api003ResPrevious = null;
            try {                
                api004ResToday = getOffsetBusinessDate(n.getId(), BUSINESSDATE_TODAY);
                api003ResToday = listForeignStockOrders(n.getId(), api004ResToday);
            } catch (Exception e) {
                throw e;
            }
            
            try {
                api004ResPrevious = getOffsetBusinessDate(n.getId(), BUSINESSDATE_PREVIOUS);
                api003ResPrevious = listForeignStockOrders(n.getId(), api004ResPrevious);                
            } catch (Exception e) {
                throw e;                
            }
            
            // API003の出力を一つにする
            List<OrderDecode> api003OrderDecodes = new ArrayList<OrderDecode>();
            if (null != api003ResToday) {                
                if (api003ResToday.getOrderDecodes() != null) {
                    api003OrderDecodes.addAll(api003ResToday.getOrderDecodes());
                }
            }
            if (null != api003ResPrevious) {                
                if (api003ResPrevious.getOrderDecodes() != null) {
                    api003OrderDecodes.addAll(api003ResPrevious.getOrderDecodes());
                }
            }
            
            // API003の取得件数を外株委託注文件数（算出）に設定
            int getDataLength = api003OrderDecodes.size();
            foreignStockEntrustOrder.setFsEntrustOrderStatusCount(String.valueOf(getDataLength));
            
            for (OrderDecode o : api003OrderDecodes) {
                IfaOrderStatusListDtoResponseFsEntrustOrderStatus fsEntrustOrderStatus = new IfaOrderStatusListDtoResponseFsEntrustOrderStatus();
                // 取引種別(算出)
                fsEntrustOrderStatus.setBuySellTypeName(getForeignBuySellTypeName(o));
                // 価格(算出)
                fsEntrustOrderStatus.setPrice(getForeignPrice(o));
                // 期間(算出)
                fsEntrustOrderStatus.setOrderPeriod(getForeignOrderPeriod(o));
                // 条件詳細(算出)
                fsEntrustOrderStatus.setConditions(getForeignConditions(o, n));
                // 取消ボタン表示判定(算出)
                fsEntrustOrderStatus.setMutualFundCancelButtonDisplayJudgment(getForeignCancel(o));
                // 残りのAPI返却項目を設定
                // 注文番号（数字）
                fsEntrustOrderStatus.setOrderNumber(o.getOrderNo());
                // 注文Sub番号（数字）
                fsEntrustOrderStatus.setOrderSubNumber(o.getOrderSubNo());
                // 銘柄コード（半角英数字）
                fsEntrustOrderStatus.setBrandCode(o.getSecurities().getSecuritiesCode());
                // 銘柄名（全角半角）
                fsEntrustOrderStatus.setBrandName(o.getSecurities().getSecuritiesName());
                // 市場略名
                fsEntrustOrderStatus.setMarket(o.getMarket().getMarketShortName());
                // 株取引区分
                fsEntrustOrderStatus.setStockTradeType(o.getStockTradeType());
                // 注文数量
                fsEntrustOrderStatus.setDomesticQuantityInput(o.getOrderQuantity());
                // 預り区分（全角半角）
                fsEntrustOrderStatus.setDepositType(o.getSpecificAccountCode());
                // 決済方法（全角半角）
                fsEntrustOrderStatus.setKessaiHoho(o.getSettlementMethodCode());
                // 平均約定単価（数値(小数)）
                fsEntrustOrderStatus.setAverageTradePrice(o.getExecutionAveragePrice());
                // 約定数量（数値(整数)）
                fsEntrustOrderStatus.setTradeQuantity(o.getExecutionQuantity());
                // 注文状況（全角半角）
                fsEntrustOrderStatus.setOrderStatus(o.getOrderStatus());
                // 約定状況
                fsEntrustOrderStatus.setTradeStatus(o.getExecutionStatus());
                // 注文日時
                fsEntrustOrderStatus.setOrderDate(o.getOrderInputDatetime());
                // 価格条件
                fsEntrustOrderStatus.setOrderStatusListOrderClass(o.getOrderPriceKindCode());
                // 手数料適用区分
                fsEntrustOrderStatus.setCommissionApplicationType(o.getCommissionType());
                
                fsEntrustOrderStatusList.add(fsEntrustOrderStatus);
            }
            
            // 注文情報をソート
            if (fsEntrustOrderStatusList.size() > 1) {
                fsEntrustOrderStatusList.sort(
                        Comparator.comparing(IfaOrderStatusListDtoResponseFsEntrustOrderStatus::getOrderSubNumber));
                fsEntrustOrderStatusList.sort(Comparator
                        .comparing(IfaOrderStatusListDtoResponseFsEntrustOrderStatus::getBrandCode).reversed());
                fsEntrustOrderStatusList
                        .sort(Comparator.comparing(IfaOrderStatusListDtoResponseFsEntrustOrderStatus::getOrderDate));
            }
            // 外株委託注文一覧をセット
            foreignStockEntrustOrder.setFsEntrustOrderStatusList(fsEntrustOrderStatusList);
            // 国籍コードをセット
            foreignStockEntrustOrder.setCountryCd(n.getId());
            
            result.add(foreignStockEntrustOrder);
        }
        return result;
    }
    
    /**
     * SQL001の出力結果を元に、募集注文リストに対応する値を設定
     *
     * @param sql001Res SQL001の出力結果
     * @return result 募集注文リスト
     */
    private List<IfaOrderStatusListDtoResponseSubscriptOrderStatus> getSubscriptOrderStatus(
            DataList<IfaOrderStatusListSql001ResponseModel> sql001Res) throws Exception {
        
        // 募集注文リスト
        List<IfaOrderStatusListDtoResponseSubscriptOrderStatus> result = new ArrayList<IfaOrderStatusListDtoResponseSubscriptOrderStatus>();
        
        // SQL001の取得件数回処理を実行
        for (IfaOrderStatusListSql001ResponseModel sql001R : sql001Res.getDataList()) {
            IfaOrderStatusListDtoResponseSubscriptOrderStatus subscriptOrderStatus = new IfaOrderStatusListDtoResponseSubscriptOrderStatus();
            // 銘柄コード（半角英数字）
            subscriptOrderStatus.setBrandCode(sql001R.getBrandCode());
            // 募集受注日時
            subscriptOrderStatus.setRecruitmentOrderDate(sql001R.getRecruitmentOrderDate());
            // 銘柄名（全角半角）
            subscriptOrderStatus.setBrandName(sql001R.getBrandName());
            // 注文状況（全角半角）
            subscriptOrderStatus.setOrderStatus(sql001R.getOrderStatus());
            // 数量（数値(整数)）
            subscriptOrderStatus.setQuantity(sql001R.getQuantity());
            // 預り区分（全角半角）
            subscriptOrderStatus.setDepositType(sql001R.getDepositType());
            
            result.add(subscriptOrderStatus);
        }
        return result;
    }
    
    /**
     * SQL002の出力結果を元に、店頭取引注文リストに対応する値を設定
     *
     * @param sql002Res SQL002の出力結果
     * @return result 店頭取引注文リスト
     */
    private List<IfaOrderStatusListDtoResponseFsStoreOrderStatus> getFsStoreOrderStatus(
            DataList<IfaOrderStatusListSql002ResponseModel> sql002Res) throws Exception {
        
        // 店頭取引注文リスト
        List<IfaOrderStatusListDtoResponseFsStoreOrderStatus> result = new ArrayList<IfaOrderStatusListDtoResponseFsStoreOrderStatus>();
        
        // SQL002の取得件数回処理を実行
        for (IfaOrderStatusListSql002ResponseModel sql002R : sql002Res.getDataList()) {
            IfaOrderStatusListDtoResponseFsStoreOrderStatus fsStoreOrderStatus = new IfaOrderStatusListDtoResponseFsStoreOrderStatus();
            // 管理番号（半角英数字）
            fsStoreOrderStatus.setManageNumber(sql002R.getManageNumber());
            // ステータス
            fsStoreOrderStatus.setStatus(sql002R.getStatus());
            // 売買区分（全角半角）
            fsStoreOrderStatus.setTradeKbn(sql002R.getTradeKbn());
            // 銘柄コード（半角英数字）
            fsStoreOrderStatus.setBrandCode(sql002R.getBrandCode());
            // 銘柄名（全角半角）
            fsStoreOrderStatus.setBrandName(sql002R.getBrandName());
            // 預り区分（全角半角）
            fsStoreOrderStatus.setDepositType(sql002R.getDepositType());
            // 注文時間
            fsStoreOrderStatus.setOrderDayTime(sql002R.getOrderTime());
            // 約定時間
            fsStoreOrderStatus.setTradeDateTime(sql002R.getAppointTime());
            // 注文数量
            fsStoreOrderStatus.setDomesticQuantityInput(sql002R.getOrderCount());
            // 取引価格（数値(小数)）
            fsStoreOrderStatus.setTradePrice(sql002R.getTradePrice());
            // ワーニング申請取引（全角半角）
            fsStoreOrderStatus.setWarningApplyTrade(sql002R.getWarningApplyTrade());
            // 取消理由（全角半角）
            fsStoreOrderStatus.setCancelReason(sql002R.getCancelReason());
            // 取引種別（算出）
            if (StringUtils.equals(sql002R.getTradeKbn(), SQL002_TRADEKBN_BUY)) {
                fsStoreOrderStatus.setBuySellTypeName(BUY_SELL_TYPE_BUY);
            } else if (StringUtils.equals(sql002R.getTradeKbn(), SQL002_TRADEKBN_SELL)) {
                fsStoreOrderStatus.setBuySellTypeName(BUY_SELL_TYPE_SELL);
            }
            result.add(fsStoreOrderStatus);
        }
        return result;
    }
    
    /**
     * 国内株式：注文種別を取得
     *
     * @param api001res api001の出力
     * @return result 注文種別(算出)
     */
    private String getDomesticOrderType(QueryStockUniteOrderPointOrd api001res) {
        
        String result = null;
        String input = StringUtil.EMPTY_STRING;
        
        // 各値を","区切りで結合して検索のための入力値にする
        
        // 商品区分
        input += api001res.getSecId() + ",";
        // 自動注文種別 (半角スペースは"S"に置き換える)
        input += api001res.getAutoOrderKind().replace(" ", SPACE_TO) + ",";
        // RBE注文種別 (半角スペースは"S"に置き換える)
        input += api001res.getRbeOrderKind().replace(" ", SPACE_TO);
        
        // 判定
        ConvertCalcDomesticOrderType outputValue = ConvertCalcDomesticOrderType.valueOfId(input);
        if (outputValue != null) {
            result = outputValue.getLabel();
        } else {
            result = NONE_OUTPUT;
        }
        return result;
    }
    
    /**
     * 国内株式：信用取引区分を取得
     *
     * @param api001res api001の出力
     * @return result 信用取引区分(算出)
     */
    private String getDomesticMarginTradeTypeText(QueryStockUniteOrderPointOrd api001res) {
        
        // 弁済期限
        String input = api001res.getPaymentLimit();
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
        if (StringUtils.equals(api001res.getIppanMgPaymentKbn(), IPPAN_MG_PAYMENTKBN_YEAR)) {
            ymd = DOMESTIC_MARGINTRADETYPE_TEXT_YEAR;
        } else if (StringUtils.equals(api001res.getIppanMgPaymentKbn(), IPPAN_MG_PAYMENTKBN_MONTH)) {
            ymd = DOMESTIC_MARGINTRADETYPE_TEXT_MONTH;
        } else if (StringUtils.equals(api001res.getIppanMgPaymentKbn(), IPPAN_MG_PAYMENTKBN_DAY)) {
            ymd = DOMESTIC_MARGINTRADETYPE_TEXT_DAY;
        } else if (StringUtils.equals(api001res.getIppanMgPaymentKbn(), IPPAN_MG_PAYMENTKBN_UNLIMITED)) {
            return DOMESTIC_MARGINTRADETYPE_TEXT_UNLIMITED;
        }
        // 一般信用売弁済期限年月日数
        String result = null;
        result = api001res.getIppanMgPaymentLimit().replaceFirst("^0+", "") + ymd;
        if (StringUtils.equals(api001res.getIppanMgPaymentLimit(), null)) {
            return NONE_OUTPUT;
        }
        return result;
    }
    
    /**
     * 国内投信:注文状況を取得
     *
     * @param api002res api002の出力
     * @return result 注文状況(算出)
     */
    private String getMutualFundOrderStatus(QueryFundOrderWebExtDetail api002res) {
        
        String result = NONE_OUTPUT;
        // 注文ステータス
        if (!StringUtils.equals(api002res.getOrderStatus(), ORDER_STATUS_ACCEPTED)) {
            return result;
        }
        // 注文取消区分
        if (StringUtils.equals(api002res.getCxlOrderId(), CXLORDERID_ACCEPTED_CANCEL)) {
            result = MUTUALFUND_ORDERSTATUS_CANCELED;
        } else if (StringUtils.equals(api002res.getCxlOrderId(), CXLORDERID_ACCEPTED_NORMAL)) {
            // 約定区分
            if (StringUtils.equals(api002res.getExecStatus(), EXECSTATUS_NOT_CONVENTIONS)) {
                result = MUTUALFUND_ORDERSTATUS_ORDERING;
            } else if (StringUtils.equals(api002res.getExecStatus(), EXECSTATUS_CONVENTIONS)) {
                result = MUTUALFUND_ORDERSTATUS_CONVENTIONS;
            }
        }
        return result;
    }
    
    /**
     * 国内投信:取引種別を取得
     *
     * @param api002res api002の出力
     * @return result 取引種別(算出)
     */
    private String getMutualFundSellTypeName(QueryFundOrderWebExtDetail api002res) {
        
        String result = null;
        // 受付経路区分
        if (StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_BUY)) {
            return MUTUALFUND_SELLTYPENAME_KONYU_TUMITATE;
        } else if (StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_BUY_CREDIT)) {
            return MUTUALFUND_SELLTYPENAME_KONYU_CARDTUMITATE;
        } else if (StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_SELL)) {
            // 売買区分
            if (StringUtils.equals(api002res.getBuySell(), BUYSELL_SELL)) {
                result = MUTUALFUND_SELLTYPENAME_KAIYAKU_TEIGAKU;
            } else if (StringUtils.equals(api002res.getBuySell(), BUYSELL_SELL_BUY)) {
                result = MUTUALFUND_SELLTYPENAME_KAITORI_TEIGAKU;
            }
            return result;
        } else if (StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_SELL_FIXED)) {
            // 売買区分
            if (StringUtils.equals(api002res.getBuySell(), BUYSELL_SELL)) {
                result = MUTUALFUND_SELLTYPENAME_KAIYAKU_TEIRITSU;
            } else if (StringUtils.equals(api002res.getBuySell(), BUYSELL_SELL_BUY)) {
                result = MUTUALFUND_SELLTYPENAME_KAITORI_TEIRITSU;
            }
            return result;
        } else if (StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_SELL_LIMIT)) {
            // 売買区分
            if (StringUtils.equals(api002res.getBuySell(), BUYSELL_SELL)) {
                result = MUTUALFUND_SELLTYPENAME_KAIYAKU_KIKANSHITEI;
            } else if (StringUtils.equals(api002res.getBuySell(), BUYSELL_SELL_BUY)) {
                result = MUTUALFUND_SELLTYPENAME_KAITORI_KIKANSHITEI;
            }
            return result;
        }
        
        /// 売買区分
        if (StringUtils.equals(api002res.getBuySell(), BUYSELL_CANCELL)) {
            return MUTUALFUND_SELLTYPENAME_ZENGAKUKAIYAKU;
        } else if (StringUtils.equals(api002res.getBuySell(), BUYSELL_ALL_BUY)) {
            return MUTUALFUND_SELLTYPENAME_ZENGAKUKAITORI;
        }
        // 商品区分
        String input = createMatrixid(api002res.getBuySell(), api002res.getSecId(), null);
        // 判定
        ConvertCalcMutualFundSellTypeName outputValue = ConvertCalcMutualFundSellTypeName.valueOfId(input);
        if (outputValue != null) {
            result = outputValue.getLabel();
        }
        return result;
    }
    
    /**
     * 国内投信:数量を取得
     *
     * @param api002res api002の出力
     * @return result 数量(算出)
     */
    private String getMutualFundUnitAmount(QueryFundOrderWebExtDetail api002res) {
        
        String result = null;
        // 商品区分
        if (StringUtils.equals(api002res.getSecId(), SECID_GENERAL)) {
            result = api002res.getQuantity();
        } else if (StringUtils.equals(api002res.getSecId(), SECID_ACCUMULATION)) {
            // 購入解約方法
            if (StringUtils.equals(api002res.getBuySellMtd(), BUYSELLMTD_ACCOUNT)) {
                result = api002res.getQuantity();
            } else {
                result = api002res.getPayment();
            }
        }
        return result;
    }
    
    /**
     * 国内投信:数量単位を取得
     *
     * @param api002res api002の出力
     * @return result 数量単位(算出)
     */
    private String getMutualFundUnit(QueryFundOrderWebExtDetail api002res) {
        
        String result = null;
        // 商品区分
        if (StringUtils.equals(api002res.getSecId(), SECID_GENERAL)) {
            result = MUTUALFUND_UNIT_KUCHI;
        } else if (StringUtils.equals(api002res.getSecId(), SECID_ACCUMULATION)) {
            // 購入解約方法
            if (StringUtils.equals(api002res.getBuySellMtd(), BUYSELLMTD_ACCOUNT)) {
                result = MUTUALFUND_UNIT_KUCHI;
            } else {
                result = MUTUALFUND_UNIT_EN;
            }
        }
        return result;
    }
    
    /**
     * 国内投信:取消表示判定を取得
     *
     * @param api002res api002の出力
     * @return result 取消表示判定(算出)
     */
    private String getMutualFundMutualFundCancelButtonDisplayJudgment(QueryFundOrderWebExtDetail api002res) {
        
        String result = MUTUALFUND_CANCELBUTTON_HIDE;
        // 注文ステータス
        if (!StringUtils.equals(api002res.getOrderStatus(), ORDER_STATUS_ACCEPTED)) {
            return result;
        }
        // 注文取消区分
        if (!StringUtils.equals(api002res.getCxlOrderId(), CXLORDERID_ACCEPTED_NORMAL)) {
            return result;
        }
        // 約定区分
        if (!StringUtils.equals(api002res.getExecStatus(), EXECSTATUS_NOT_CONVENTIONS)) {
            return result;
        }
        // 取消ステータス
        if (StringUtils.equals(api002res.getCxlStatus(), CXLSTATUS_NOT_CANCELL)) {
            return MUTUALFUND_CANCELBUTTON_DISPLAY;
        }
        if (!StringUtils.equals(api002res.getCxlStatus(), CXLSTATUS_ACCEPTED)) {
            return result;
        }
        // 受付経路区分
        if (StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_SELL)
                || StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_SELL_FIXED)
                || StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_SELL_LIMIT)
                || StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_BUY)
                || StringUtils.equals(api002res.getCallcenterKbn(), CALLCENTER_KBN_BUY_CREDIT)) {
            return result;
        }
        result = MUTUALFUND_CANCELBUTTON_DISPLAY;
        return result;
    }
    
    /**
     * 外株委託：取引種別を取得
     *
     * @param api003Res api003の出力(注文状況)
     * @return result 取引種別(算出)
     */
    private String getForeignBuySellTypeName(OrderDecode api003Res) {
        
        String result = null;
        String input = StringUtil.EMPTY_STRING;
        // 自動買付区分
        if (StringUtils.equals(api003Res.getAutoStockOrderType(), AUTO_STOCK_ORDERTYPE_RESERVE_ORDER)) {
            return FOREIGN_BUYSELLTYPE_TEIKI;
        } else if (StringUtils.equals(api003Res.getAutoStockOrderType(), AUTO_STOCK_ORDERTYPE_NORMAL_ORDER)) {
            
            // 株取引区分
            input += api003Res.getStockTradeType() + ",";
            // 売買区分
            input += api003Res.getBuySellCode();
            // 判定
            ConvertCalcForeignBuySellTypeName outputValue = ConvertCalcForeignBuySellTypeName.valueOfId(input);
            if (outputValue != null) {
                result = outputValue.getLabel();
            }
        }
        
        return result;
    }
    
    /**
     * 外株委託：条件詳細を取得
     *
     * @param api003Res api003の出力(注文状況)
     * @return result 条件詳細(算出)
     */
    private String getForeignConditions(OrderDecode api003Res, NationalityCode nationalityCode) {
        
        String result = null;
        
        // 国別通貨単位(国籍コードを使用して算出 表示パターン1)
        String nationalCurrencyUnit = codeListService.getValue(NATIONAL_CURRENCY_UNIT, nationalityCode.getId());
        
        // 発注条件(国別通貨単位は国籍コードを使用)
        String nationalprice = api003Res.getStopPrice() + nationalCurrencyUnit;
        
        // 価格条件と売買区分の値から設定するメッセージを決定
        if (StringUtils.equals(api003Res.getOrderPriceKindCode(), ORDERPRICE_KINDCODE_REVERSE_MARKET_ORDER)
                && StringUtils.equals(api003Res.getBuySellCode(), BUYSELL_CODE_SELL)) {
            result = String.format(FOREIGNCONDITIONS_LESS_MESSAGE, nationalprice,
                    FOREIGNCONDITIONS_MARKET_ORDER_MESSAGE);
        } else if (StringUtils.equals(api003Res.getOrderPriceKindCode(), ORDERPRICE_KINDCODE_REVERSE_MARKET_ORDER)
                && StringUtils.equals(api003Res.getBuySellCode(), BUYSELL_CODE_BUY)) {
            result = String.format(FOREIGNCONDITIONS_OVER_MESSAGE, nationalprice,
                    FOREIGNCONDITIONS_MARKET_ORDER_MESSAGE);
        } else if (StringUtils.equals(api003Res.getOrderPriceKindCode(), ORDERPRICE_KINDCODE_REVERSE_PRICE_LIMIT)
                && StringUtils.equals(api003Res.getBuySellCode(), BUYSELL_CODE_BUY)) {
            result = String.format(FOREIGNCONDITIONS_OVER_MESSAGE, nationalprice,
                    api003Res.getOrderPrice() + nationalCurrencyUnit);
        } else if (StringUtils.equals(api003Res.getOrderPriceKindCode(), ORDERPRICE_KINDCODE_REVERSE_PRICE_LIMIT)
                && StringUtils.equals(api003Res.getBuySellCode(), BUYSELL_CODE_SELL)) {
            result = String.format(FOREIGNCONDITIONS_LESS_MESSAGE, nationalprice,
                    api003Res.getOrderPrice() + nationalCurrencyUnit);
        } else {
            result = NONE_OUTPUT;
        }
        return result;
    }
    
    /**
     * 外株委託：価格を取得
     *
     * @param api003Res api003の出力(注文状況)
     * @return result 価格(算出)
     */
    private String getForeignPrice(OrderDecode api003Res) {
        
        String result = null;
        // 価格条件
        if (StringUtils.equals(api003Res.getOrderPriceKindCode(), ORDERPRICE_KINDCODE_MARKET_ORDER)
                || StringUtils.equals(api003Res.getOrderPriceKindCode(), ORDERPRICE_KINDCODE_REVERSE_MARKET_ORDER)) {
            result = FOREIGNPRICE_MARKET_ORDER;
        } else if (StringUtils.equals(api003Res.getOrderPriceKindCode(), ORDERPRICE_KINDCODE_PRICE_LIMIT)
                || StringUtils.equals(api003Res.getOrderPriceKindCode(), ORDERPRICE_KINDCODE_REVERSE_PRICE_LIMIT)) {
            result = api003Res.getOrderPrice();
        }
        return result;
    }
    
    /**
     * 外株委託：期間を取得
     *
     * @param api003Res api003の出力(注文状況)
     * @return result 期間(算出)
     */
    private String getForeignOrderPeriod(OrderDecode api003Res) {
        
        String result = null;
        // 発火条件価格
        if (StringUtils.equals(api003Res.getOrderLimitCode(), STOP_PRICE_TODAY_ORDER)) {
            result = FOREIGNORDER_PERIOD;
        } else if (StringUtils.equals(api003Res.getOrderLimitCode(), STOP_PRICE_CARRY_OVER_ORDER)) {
            result = api003Res.getOrderTerm();
        }
        return result;
    }
    
    /**
     * 外株委託：取消表示判定を取得
     *
     * @param api003Res api003の出力(注文状況)
     * @return result 取消表示判定(算出)
     */
    private String getForeignCancel(OrderDecode api003Res) {
        
        String result = null;
        // 取消し可能判定
        if (api003Res.getCancelable() == null) {
            return FOREIGN_CANCELBUTTON_HIDE;
        }
        if (!api003Res.getCancelable()) {
            return FOREIGN_CANCELBUTTON_HIDE;
        }
        // 自動買い付け区分
        if (StringUtils.equals(api003Res.getAutoStockOrderType(), AUTO_STOCK_ORDERTYPE_RESERVE_ORDER)) {
            result = FOREIGN_CANCELBUTTON_HIDE;
        }
        if (StringUtils.equals(api003Res.getAutoStockOrderType(), AUTO_STOCK_ORDERTYPE_NORMAL_ORDER)) {
            result = FOREIGN_CANCELBUTTON_DISPLAY;
        }
        
        return result;
    }
    
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.sbibits.earth.extapi.ApiIOException;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.enums.OrderPriceKind;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.util.OrderStatusUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticStockOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001SubRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCancelConfirmA002bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticStockOrderCancelConfirmService;
import com.sbisec.helios.ap.common.dto.CheckApiResultDto;
import com.sbisec.helios.ap.common.enums.DomesticStockTradeClass;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ExecuteMethod;
import com.sbisec.helios.ap.common.enums.JrIsaContractType;
import com.sbisec.helios.ap.common.enums.OrderClass;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.exception.ApiError;
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

import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOrd;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderOutData;

/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
@Component(value = "cmpIfaDomesticStockOrderCancelConfirmService")
public class IfaDomesticStockOrderCancelConfirmServiceImpL implements IfaDomesticStockOrderCancelConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticStockOrderCancelConfirmServiceImpL.class);
    
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
     * DAO
     */
    @Autowired
    private IfaDomesticStockOrderCancelConfirmDao dao;
    
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
    
    /**
     * 訂正/取消ボタン表示の判定クラス
     */
    @Autowired
    private OrderStatusUtil orderStatusUtil;
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] {0}:顧客共通情報.部店コード {1}:顧客共通情報.口座番号*/
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 指定した注文が見つかりません。 */
    private static final String ERRORS_CMN_ORDER_NOTFOUND = "errors.cmn.order.notFound";
    
    /** 指定した注文は取消できません。 */
    private static final String ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE = "errors.cmn.orderCancel.outOfService";
    
    /** {0}ができないコースです。*/
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 注文発注前の注文データが登録できないため、注文しませんでした。 */
    private static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    /** 注文取消後の注文データが更新できませんでした。注文取消は完了しています。 */
    private static final String WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED = "warnings.cmn.postOrderExecutionCancel.completed";
    
    /** 注文取消処理でエラーが発生しました。(エラーコード：{0}、エラーメッセージ{1}) {0}:APIエラーコード {1}:APIエラーメッセージ*/
    private static final String ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED = "errors.cmn.orderExecutionCancel.failed";
    
    /** CCSIDが未登録のためご利用できません。 */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    /** 国内株式設定値 */
    private static final String DOMESTICSTOCK = "01";
    
    /** 区分定義.ドメインID_対象取引 */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 対象取引 区分値：2 */
    private static final String MSG_DISPLAY_TARGET_TRADE_KUBUN = "2";
    
    /**　対象取引 表示パターン: 1 */
    private static final String MSG_DISPLAY_TARGET_TRADE_DISP_PATTERN = "1";
    
    /** FCT003.媒介可否リスト.媒介可否が"1"（媒介可） */
    private static final String MEDIATE_PROPRIETY = "1";
    
    /** API001 リクエスト区分 : "2"(受注番号指定) */
    private static final String API001_EXEC_ORDER = "2";
    
    /** API001 取引区分 : "1":現物信用注文明細（上場CW含まない） */
    private static final String API001_TORIHIKI_KBN = "1";
    
    /** API001 約定取得区分 : "1":約定明細取得 */
    private static final String API001_TRADE_GET_KBN = "1";
    
    /** API001 銘柄コード : ""  */
    private static final String API001_BRAND_CD = "";
    
    /** API001 取得口座区分 : "△":通常口座およびJrNISA口座の第一口座  */
    private static final String API001_ACCOUNT_GET_KBN_SPACE = " ";
    
    /** API001 取得口座区分 : "2"：JrNISA口座(第一、第二口座両方)  */
    private static final String API001_ACCOUNT_GET_KBN_2 = "2";
    
    /** API002 オリジン : "0"  */
    private static final String API002_ORIGIN = "0";
    
    /** API002 商品区分 : "S"：株  */
    private static final String API002_ORDER_TYPE = "S";
    
    /** API002 取消受付経路区分 : "0"：支店  */
    private static final String API002_CXL_CALLCENTER_KBN = "0";
    
    /** API002 IPアドレス : "ifap"  */
    private static final String API002_IP_ADDRESS = "ifap";
    
    /** SQL001 注文種別（一覧） : 1:逆指値注文 */
    private static final String ORDER_STATUS_STOP = "1";
    
    /** SQL001 注文種別（一覧） : 0:通常注文 */
    private static final String ORDER_STATUS_NORMAL = "0";
    
    /** SQL001 注文種別（一覧） : 2:OCO注文 */
    private static final String ORDER_STATUS_OCO = "2";
    
    /** SQL001 注文種別（一覧） : 4:IFD1注文（逆指値） */
    private static final String ORDER_STATUS_IFD1_STOP = "4";
    
    /** SQL001 注文種別（一覧） : 3:IFD1注文（通常） */
    private static final String ORDER_STATUS_IFD1_NORMAL = "3";
    
    /* */
    /** 訂正ボタン表示判定) API001 RBE注文種別の出力値 " 　　":通常注文 */
    private static final String RBE_ORDER_KIND_NORMAL = "   ";
    
    /** 訂正ボタン表示判定) API001 RBE注文種別の出力値 "SLO":逆指値注文 */
    private static final String RBE_ORDER_KIND_SLO = "SLO";
    
    /** 訂正ボタン表示判定) API001 RBE注文種別の出力値 "OCO":OCO注文 */
    private static final String RBE_ORDER_KIND_OCO = "OCO";
    
    /** DONE RBE注文種別.通常注文 */
    private static final String DONE_RBE_ORDER_KIND_NORMAL = "   ";
    
    /** DONE RBE注文種別.OCO注文 */
    private static final String DONE_RBE_ORDER_KIND_OCO = "OCO";
    
    /** DONE RBE注文種別.逆指値注文 */
    private static final String DONE_RBE_ORDER_KIND_SLO = "SLO";
    
    /** API001 RBE注文ステータス "1":発火済み */
    private static final String RBE_ORDER_STATUS_STOP = "1";
    
    /** API001 自動注文種別.IFD親注文 */
    private static final String AUTO_ORDER_KIND_IFD_PARENT = "IF  ";
    
    /** API001 自動注文種別.IFD子注文 */
    private static final String AUTO_ORDER_KIND_IFD_CHILD = "DONE";
    
    /** API001 自動注文種別.通常注文/逆指注文/OCO注文 */
    private static final String AUTO_ORDER_KIND_OTHERS = "    ";
    
    /** API001 指成区分.指値 */
    private static final String SASINARI_ID_PRICE_LIMIT = " ";
    
    /** API001 指成区分.寄付指値 */
    private static final String SASINARI_ID_OPEN_PRICE_LIMIT = "Z";
    
    /** API001 指成区分.引け指し */
    private static final String SASINARI_ID_CLOSE_PRICE_LIMIT = "I";
    
    /** API001 指成区分.成行 */
    private static final String SASINARI_ID_MARKET_ORDER = "N";
    
    /** API001 指成区分.寄り成行 */
    private static final String SASINARI_ID_OPEN_MARKET_PRICE = "Y";
    
    /** API001 指成区分.引け成行 */
    private static final String SASINARI_ID_CLOSE_MARKET_PRICE = "H";
    
    /** API001 指成区分.不成 */
    private static final String SASINARI_ID_FAILED = "F";
    
    /** API001 指成区分.ICO指 */
    private static final String SASINARI_ID_IOC_PRICE_LIMIT = "P";
    
    /** API001 指成区分.IOC成 */
    private static final String SASINARI_ID_IOC_MARKET_PRICE = "O";
    
    /** API001 直近OCO指成区分.指値 */
    private static final String LATEST_OCO_SASINARI_ID_PRICE_LIMIT = " ";
    
    /** API001 直近OCO指成区分.成行 */
    private static final String LATEST_OCO_SASINARI_ID_MARKET_PRICE = "N";
    
    /** API001 直近OCO指成区分.不成 */
    private static final String LATEST_OCO_SASINARI_ID_FAIL = "F";
    
    /** API001 DONE 指成区分.指値 */
    private static final String DONE_SASINARI_ID_PRICE_LIMIT = " ";
    
    /** API001 DONE 指成区分.寄付指値 */
    private static final String DONE_SASINARI_ID_OPEN_PRICE_LIMIT = "Z";
    
    /** API001 DONE 指成区分.引け指し */
    private static final String DONE_SASINARI_ID_CLOSE_PRICE_LIMIT = "I";
    
    /** API001 DONE 指成区分.成行 */
    private static final String DONE_SASINARI_ID_MARKET_PRICE = "N";
    
    /** API001 DONE 指成区分.寄り成行 */
    private static final String DONE_SASINARI_ID_OPEN_MARKET_PRICE = "Y";
    
    /** API001 DONE 指成区分.引け成行 */
    private static final String DONE_SASINARI_ID_CLOSE_MARKET_PRICE = "H";
    
    /** API001 DONE 指成区分.不成 */
    private static final String DONE_SASINARI_ID_FAIL = "F";
    
    /** API001 DONE 指成区分.IOC指 */
    private static final String DONE_SASINARI_ID_IOC_PRICE_LIMIT = "P";
    
    /** API001 DONE 指成区分.IOC成 */
    private static final String DONE_SASINARI_ID_IOC_MARKET_PRICE = "O";
    
    /** SQL001 注文状況.取消 */
    private static final String ORDER_STATUS_CANCEL = "2";
    
    /** SQL001 商品区分.株 */
    private static final String SECURITY_TYPE_STOCK = "S";
    
    /** SQL001 指定値 : リクエスト.預り区分="0" */
    private static final String SQL_REQ_DEPOSITTYPE_ZERO = "0";
    
    /** SQL001 指定値 : リクエスト.預り区分="1" */
    private static final String SQL_REQ_DEPOSITTYPE_ONE = "1";
    
    /** SQL001 指定値 : リクエスト.預り区分="4" */
    private static final String SQL_REQ_DEPOSITTYPE_FOUR = "4";
    
    /** SQL001 指定値 : リクエスト.預り区分="5" */
    private static final String SQL_REQ_DEPOSITTYPE_FIVE = "5";
    
    /** SQL001 指定値 : リクエスト.預り区分="6" */
    private static final String SQL_REQ_DEPOSITTYPE_SIX = "6";
    
    /** SQL001 指定値 : リクエスト.預り区分="7" */
    private static final String SQL_REQ_DEPOSITTYPE_SEVEN = "7";
    
    /** SQL001 指定値 : リクエスト.預り区分="J" */
    private static final String SQL_REQ_DEPOSITTYPE_J = "J";

    /** サブクエリ①でレコード0件 */
    private static final int SQL_SUB_RECODE_ZERO = 0;
    
    /** サブクエリ①でレコード1件 */
    private static final int SQL_SUB_RECODE_ONE = 1;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticStockOrderCancelConfirmA001DtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCancelConfirmA001DtoResponse
     * model リクエスト：IfaDomesticStockOrderCancelConfirmSql001RequestModel
     * model レスポンス：IfaDomesticStockOrderCancelConfirmSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return 画面に返す情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse> initializeA001(
            IfaDomesticStockOrderCancelConfirmA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderCancelConfirmServiceImplL.initializeA001");
        }
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // FCT001
        DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse> dtoResFct001 = checkFct001(cc.getButenCode(),
                cc.getAccountNumber());
        if (dtoResFct001 != null) {
            return dtoResFct001;
        }
        
        // API001を呼ぶ
        QueryStockUniteOrderPointOrd api001result = new QueryStockUniteOrderPointOrd();
        try {
            api001result = callApi001(cc, dtoReq.getEcOrderNo());
            
        } catch (ApiError | ApiIOException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{}, {}, {}", "IfaDomesticStockOrderCancelConfirmServiceImpL", "initializeA001", e);
            }
            
            DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse> dtoResApi001Error = IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_NOTFOUND,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_NOTFOUND));
            
            return dtoResApi001Error;
        }
        
        //指定した注文情報が取得できない場合、エラーを返す。
        if (api001result == null) {
            DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse> dtoResApi001Null = IfaCommonUtil.createDataList(
                    new ArrayList<>(), ErrorLevel.FATAL, ERRORS_CMN_ORDER_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDER_NOTFOUND));
            
            return dtoResApi001Null;
        }
        
        // 取消できない注文の場合、エラーを返す
        if (!orderStatusUtil.canCancelOrder(api001result)) {
            DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse> dtoResCanNotCancelOrder = IfaCommonUtil
                    .createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE,
                            IfaCommonUtil.getMessage(ERRORS_CMN_ORDERCANCEL_OUTOFSERVICE));
            
            return dtoResCanNotCancelOrder;
        }
        
        //APIレスポンスの値をA001のレスポンスに格納する処理
        IfaDomesticStockOrderCancelConfirmA001DtoResponse response = new IfaDomesticStockOrderCancelConfirmA001DtoResponse();
        setApi001ResToA001Res(response, api001result);
        
        // FCT003 取引コース媒介可否チェック
        DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse> dtoResFct003 = checkFct003(cc.getButenCode(),
                cc.getAccountNumber(), response.getTradeKbn());
        if (dtoResFct003 != null) {
            return dtoResFct003;
        }
        
        // コントローラにレスポンスを返す
        DataList<IfaDomesticStockOrderCancelConfirmA001DtoResponse> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(response), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), StringUtil.EMPTY_STRING);
        return dtoRes;
    }
    
    /**
     * アクションID：A002a
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderCancelConfirmA002aDtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCancelConfirmA002aDtoResponse
     * model リクエスト：IfaDomesticStockOrderCancelConfirmSql002RequestModel
     * model レスポンス：IfaDomesticStockOrderCancelConfirmSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return 画面に返す情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse> orderPlacementA002a(
            IfaDomesticStockOrderCancelConfirmA002aDtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderCancelConfirmServiceImplL.orderPlacementA002a");
        }
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // FCT001
        DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse> dtoResFct001 = checkFct001(cc.getButenCode(),
                cc.getAccountNumber());
        if (dtoResFct001 != null) {
            return dtoResFct001;
        }
        
        // FCT003
        DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse> dtoResFct003 = checkFct003(cc.getButenCode(),
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

        // 発注前に注文取消内容を国内株式注文テーブルへ記録する。(SQL001)
        try {
            // サブクエリ①を実行して、EC受注番号が一致するレコードの存在を確認
            DataList<IfaDomesticStockOrderCancelConfirmSql001SubResponseModel> sql001Sub = getIfaEcOrderNo(
                    dtoReq.getEcOrderNo(), dtoReq.getOrderDayTime(), cc.getButenCode());
            
            // DBに注文取消内容を事前登録
            DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse> dtoRes = orderRegister(dtoReq, sql001Sub, cc);
            
            if (dtoRes == null) {
                return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                        ERRORS_FRS_PREORDEREXECUTION_FAILED,
                        IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
            } else {
                return dtoRes;
            }
            
        } catch (Exception e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{}, {}, {}", "IfaDomesticStockOrderCancelConfirmServiceImpL", "orderPlacementA002a", e);
            }
            
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_FRS_PREORDEREXECUTION_FAILED, IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
        }
    }
    
    /**
     * アクションID：A002b
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderCancelConfirmA002bDtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCancelConfirmA002bDtoResponse
     * model リクエスト：IfaDomesticStockOrderCancelConfirmSql002RequestModel
     * model レスポンス：IfaDomesticStockOrderCancelConfirmSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return 画面に返す情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaDomesticStockOrderCancelConfirmA002bDtoResponse> orderPlacementA002b(
            IfaDomesticStockOrderCancelConfirmA002bDtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderCancelConfirmServiceImplL.orderPlacementA002b");
        }
        
        //API002
        StockCancelOrderOutData api002Res = new StockCancelOrderOutData();
        ApiErrorUtil apiError = new ApiErrorUtil();
        
        try {
            //API002を呼ぶ
            api002Res = callApi002(dtoReq.getEcOrderNo());
            apiError.checkApiResponse(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage());
            
        } catch (Exception e) { // Exceptionが発生した場合、DBにデータを登録してレスポンスを返却する。
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{}, {}, {}", "IfaDomesticStockOrderCancelConfirmServiceImpL", "orderPlacementA002b", e);
            }
            IfaDomesticStockOrderCancelConfirmSql002RequestModel sql002Req = new IfaDomesticStockOrderCancelConfirmSql002RequestModel();
        
            //ユーザー共通情報取得
            UserAccount ua = IfaCommonUtil.getUserAccount();
            //更新者
            sql002Req.setUpdateUser(ua.getUserId());
            //IFA注文番号
            sql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            //IFA注文サブ番号
            sql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            
            int insSql002Res = dao.updateIfaDomesticStockOrderCancelConfirmSql002b(sql002Req);

            DataList<IfaDomesticStockOrderCancelConfirmA002bDtoResponse> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.FATAL,
                    ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED)
            );
            return dtoRes;
        }
        
        // 取消発注後にAPIの実行結果に応じてAPI結果ステータスをDBに反映する。
        try {
            boolean successSql002 = updateOrderInfo(api002Res, !apiError.isFatal(), dtoReq.getIfaOrderNo(),
                    dtoReq.getIfaOrderSubNo());
            
            if (!successSql002) {
                apiError.addDbError(WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED, null);
            }
            
        } catch (Exception e) { // エラーの場合もAPIエラーを優先する。
            LOGGER.debug("{}, {}, {}", "IfaDomesticStockOrderCancelConfirmServiceImpL", "orderPlacementA002b", e);

            apiError.addDbError(WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED, null);
        }

        // レスポンスを返却する
        DataList<IfaDomesticStockOrderCancelConfirmA002bDtoResponse> dtoRes = new DataList<>();

        if (apiError.isFatal()) {
            dtoRes = apiError.createDataList(new ArrayList<>(), ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED);

        } else {
            IfaDomesticStockOrderCancelConfirmA002bDtoResponse response = new IfaDomesticStockOrderCancelConfirmA002bDtoResponse();
            BeanUtils.copyProperties(response, dtoReq);
            response.setOrderDayTime(api002Res.getAcceptDate() + " " + api002Res.getAcceptTime());

            dtoRes = apiError.createDataList(Arrays.asList(response), null);

        }

        return dtoRes;
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
        
        if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_BUTENACCOUNTNOTEXIST,
                    IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST, new String[] { butenCode, accountNumber }));
            
            return dtoRes;
        }
        
        if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
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
        
        //FCT003.媒介可否リスト.媒介可否が"1"（媒介可）以外の場合 = 取引不可エラー
        if (!StringUtils.equals(output.getMediateProprietyList().get(0).getMediatePropriety(), MEDIATE_PROPRIETY)) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                            new String[] { codeListService.getValue(MSG_DISPLAY_TARGET_TRADE,
                                    MSG_DISPLAY_TARGET_TRADE_KUBUN, MSG_DISPLAY_TARGET_TRADE_DISP_PATTERN) }));
            
            return dtoRes;
        }
        
        return null;
    }
    
    /**
     * API001呼び出し処理
     *
     * @param orderNo EC受注番号
     * @param torihikiKbn 取引区分
     * @return API結果
     */
    private QueryStockUniteOrderPointOrd callApi001(CustomerCommon cc, String orderNo) throws Exception {
        
        QueryStockUniteOrderPointInData inData = new QueryStockUniteOrderPointInData();
        
        // 部店コード
        inData.setButenCd(cc.getButenCode());
        // 口座番号
        inData.setKozaNo(StringUtils.leftPad(cc.getAccountNumber(), 7, "0"));
        // リクエスト区分
        inData.setExecOrder(API001_EXEC_ORDER);
        // 検索番号指定FROM
        inData.setRefFrom(StringUtils.repeat(StringUtils.SPACE, 5));
        // 検索番号指定TO
        inData.setRefTo(StringUtils.repeat(StringUtils.SPACE, 5));
        // EC受注番号
        inData.setOrderNo(StringUtils.leftPad(orderNo, 6, "0"));
        // 取引区分
        inData.setTorihikiKbn(API001_TORIHIKI_KBN);
        // 約定取得区分
        inData.setTradeGetKbn(API001_TRADE_GET_KBN);
        // 銘柄コード
        inData.setBrandCd(API001_BRAND_CD);
        // 取得口座区分
        if (StringUtils.equals(cc.getJrIsaContractType(), JrIsaContractType.CONTRACT.getId())) {
            inData.setAccountGetKbn(API001_ACCOUNT_GET_KBN_2);
        } else {
            inData.setAccountGetKbn(API001_ACCOUNT_GET_KBN_SPACE);
        }
        
        QueryStockUniteOrderPointIn input = new QueryStockUniteOrderPointIn();
        input.setIndata(inData);
        
        QueryStockUniteOrderPointOutData result = apiWrapper.queryStockUniteOrderPoint(input);
        return result.getReqOrderDataExe().get(0);
    }
    
    /**
     * API002呼び出し処理
     *
     * @param orderNo EC受注番号
     * @param torihikiKbn 取引区分
     * @return API結果
     */
    private StockCancelOrderOutData callApi002(String orderNo) throws Exception {
        
        StockCancelOrderInData inData = new StockCancelOrderInData();
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        // トランザクションID
        inData.setTransactionId(StringUtils.repeat("0", 32));
        // 通番
        inData.setCommandNum(StringUtils.repeat("0", 7));
        // 年月日
        inData.setCommandDate(StringUtils.repeat("0", 8));
        // 部店コード3桁
        inData.setButenCd(cc.getButenCode());
        // 口座番号7桁
        inData.setKozaNo(StringUtils.leftPad(cc.getAccountNumber(), 7, "0"));
        // アカウントID
        inData.setAccountId(StringUtils.repeat("0", 11));
        // アカウント毎の連番
        inData.setNumber(StringUtils.repeat("0", 7));
        // オリジン
        inData.setOrigin(API002_ORIGIN);
        // 商品区分
        inData.setOrderType(API002_ORDER_TYPE);
        // EC受注番号（元注文）
        inData.setOrderNum(orderNo);
        // 取消受付経路区分
        inData.setCxlCallcenterKbn(API002_CXL_CALLCENTER_KBN);
        // 取消ユーザーＩＤ
        inData.setCxlUserId(ua.getCcsUserId());
        // IPアドレス
        inData.setIpAddress(API002_IP_ADDRESS);
        
        StockCancelOrderIn input = new StockCancelOrderIn();
        input.setIndata(inData);
        
        // NRI_APIから買付余力情報を取得する。
        StockCancelOrderOutData result = apiWrapper.stockCancelOrder(input);
        
        return result;
    }
    
    /**
     * API001の出力結果をA001のレスポンスに格納
     *
     * @param response A001のレスポンス
     * @param api001Res API001のレスポンス
     */
    private void setApi001ResToA001Res(IfaDomesticStockOrderCancelConfirmA001DtoResponse res, QueryStockUniteOrderPointOrd api001res) {
        
        /* 銘柄コード */
        res.setBrandCode(api001res.getStockSecCode());
        /* 銘柄名 */
        res.setBrandName(api001res.getSecName().trim());
        /* 市場 */
        res.setMarket(api001res.getLatestMarketId());
        /* 取引種別 */
        if (api001res.getBuySell().equals("K")) {
            res.setTradeKbn(DomesticStockTradeClass.SPOT_BUY.getId());
            
        } else if (api001res.getBuySell().equals("U")) {
            res.setTradeKbn(DomesticStockTradeClass.SPOT_SELL.getId());
        }
        
        /* 注文数量 */
        res.setOrderQuantity(api001res.getQuantity().trim());
        /* 未約定数量 */
        res.setUnTradeQuantity(api001res.getExecLeftQuantity().trim());
        /* 期間 */
        res.setYukoKigen(api001res.getLimit());
        /* 預り区分 */
        res.setNotSpecificDepositTradeType(api001res.getHitokuteiTradeKbn());
        /* 注文種別 */
        String orderKind = null;
        
        //注文部.自動注文種別=通常注文/逆指注文/OCO注文 または　注文部.自動注文種別=IFD子注文　の場合
        if (StringUtils.equals(api001res.getAutoOrderKind(), AUTO_ORDER_KIND_OTHERS)
                || StringUtils.equals(api001res.getAutoOrderKind(), AUTO_ORDER_KIND_IFD_CHILD)) {
            
            //注文部.RBE注文種別=通常注文　または　逆指値注文　の場合
            if (StringUtils.equals(api001res.getRbeOrderKind(), RBE_ORDER_KIND_NORMAL)
                    || StringUtils.equals(api001res.getRbeOrderKind(), RBE_ORDER_KIND_SLO)) {
                //区分.注文種別 通常/逆指値
                orderKind = OrderClass.NORMAL.getId();
                
            } else if (StringUtils.equals(api001res.getRbeOrderKind(), RBE_ORDER_KIND_OCO)) { //RBE注文種別=OCO注文　の場合
                //区分.注文種別 OCO
                orderKind = OrderClass.OCO.getId();
            }
            
        } else if (StringUtils.equals(api001res.getAutoOrderKind(), AUTO_ORDER_KIND_IFD_PARENT)) {
            //DONE RBE注文種別=OCO注文　の場合
            if (StringUtils.equals(api001res.getDoneRbeOrderKind(), DONE_RBE_ORDER_KIND_OCO)) {
                //区分.注文種別 IFDOCO
                orderKind = OrderClass.IFDOCO.getId();
                
            } else {
                //区分.注文種別 IFD
                orderKind = OrderClass.IFD.getId();
            }
        }
        res.setOrderKind(orderKind);
        /* 受注日時 */
        res.setOrderDayTime(api001res.getInputDate());
        
        if (StringUtils.equals(res.getOrderKind(), OrderClass.NORMAL.getId())) {
            /* 通常/逆指値.執行方法 */
            String sasinariHouhou = null;
            //RBE注文種別=通常注文　の場合
            if (StringUtils.equals(api001res.getRbeOrderKind(), RBE_ORDER_KIND_NORMAL)) {
                //注文部.指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                if (StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_OPEN_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_CLOSE_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_FAILED)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_IOC_PRICE_LIMIT)) {
                    //区分.執行方法 指値
                    sasinariHouhou = OrderPriceKind.LIMIT.getIfaCd();
                } else if (//注文部.指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_MARKET_ORDER)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_OPEN_MARKET_PRICE)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_CLOSE_MARKET_PRICE)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_IOC_MARKET_PRICE)) {
                    //区分.執行方法 成行
                    sasinariHouhou = OrderPriceKind.MARKET.getIfaCd();
                }
            } else if (StringUtils.equals(api001res.getRbeOrderKind(), RBE_ORDER_KIND_SLO)) {
                //区分.執行方法 逆指値
                sasinariHouhou = OrderPriceKind.STOP_PRICE_LIMIT.getIfaCd();
            }
            res.setSasinariHouhou(sasinariHouhou);
            /* 通常/逆指値.執行条件 */
            res.setSasinariJyouken(api001res.getSasinariId());
            /* 通常/逆指値.発火条件価格（逆指値） */
            res.setTriggerPrice(api001res.getLatestTriggerPrice().trim());
            /* 通常/逆指値.発火条件価格（逆指値）ゾーン */
            res.setStopOrderPriceText2(api001res.getLatestTriggerZone());
            /* 通常/逆指値.執行方法（逆指値） */
            String gyakusasiHouhou = null;
            //RBE注文種別=逆指値注文　の場合
            if (StringUtils.equals(api001res.getRbeOrderKind(), RBE_ORDER_KIND_SLO)) {
                //注文部.指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                if (StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_OPEN_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_CLOSE_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_FAILED)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_IOC_PRICE_LIMIT)) {
                    // 区分.執行方法 指値
                    gyakusasiHouhou = OrderPriceKind.LIMIT.getIfaCd();
                } else if (//注文部.指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_MARKET_ORDER)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_OPEN_MARKET_PRICE)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_CLOSE_MARKET_PRICE)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_IOC_MARKET_PRICE)) {
                    // 区分.執行方法 成行
                    gyakusasiHouhou = OrderPriceKind.MARKET.getIfaCd();
                }
            }
            res.setGyakusasiHouhou(gyakusasiHouhou);
            /* 通常/逆指値.注文単価 */
            res.setPrice(api001res.getLimitPrice().trim());
        }
        
        if (StringUtils.equals(res.getOrderKind(), OrderClass.OCO.getId())
                || StringUtils.equals(res.getOrderKind(), OrderClass.IFDOCO.getId())) {
            /* OCO1.執行方法 */
            res.setOco1SasinariHouhou(OrderPriceKind.LIMIT.getIfaCd());
            
            //注文種別=OCO　の場合
            if (StringUtils.equals(res.getOrderKind(), OrderClass.OCO.getId())) {
                /* OCO1.執行条件 */
                res.setOco1SasinariJyouken(api001res.getSasinariId());
                
                /* OCO1.注文単価 */
                res.setOco1Price(api001res.getLimitPrice().trim());
                
                /* OCO2.発火条件価格（逆指値） */
                res.setOco2TriggerPrice(api001res.getLatestTriggerPrice().trim());
                
                /* OCO2.発火条件価格（逆指値）ゾーン */
                res.setOco2StopOrderPriceText2(api001res.getLatestTriggerZone());
                
                if (// 注文部.直近OCO指成区分=指値、不成(F)　の場合
                StringUtils.equals(api001res.getLatestOcoSasinariId(), LATEST_OCO_SASINARI_ID_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getLatestOcoSasinariId(), LATEST_OCO_SASINARI_ID_FAIL)) {
                    res.setOco2GyakusasiHouhou(OrderPriceKind.LIMIT.getIfaCd());
                    
                } else if (//注文部.直近OCO指成区分=成行　の場合
                StringUtils.equals(api001res.getLatestOcoSasinariId(), LATEST_OCO_SASINARI_ID_MARKET_PRICE)) {
                    res.setOco2GyakusasiHouhou(OrderPriceKind.MARKET.getIfaCd());
                }
                
                /* OCO2.執行条件（逆指値） */
                res.setOco2GyakusasiJyouken(api001res.getLatestOcoSasinariId());
                
                /* OCO2.注文単価 */
                res.setOco2Price(api001res.getLatestOcoPrice());
            } else if (StringUtils.equals(res.getOrderKind(), OrderClass.IFDOCO.getId())) {
                /* OCO1.執行条件 */
                res.setOco1SasinariJyouken(api001res.getDoneSasinariId());
                
                /* OCO1.注文単価 */
                res.setOco1Price(api001res.getDonePrice().trim());
                
                /* OCO2.発火条件価格（逆指値） */
                res.setOco2TriggerPrice(api001res.getDoneTriggerPrice().trim());
                
                /* OCO2.発火条件価格（逆指値）ゾーン */
                res.setOco2StopOrderPriceText2(api001res.getDoneTriggerZone());
                
                /* OCO2.執行方法（逆指値） */
                if (// 注文部.直近OCO指成区分=指値、不成(F)　の場合
                StringUtils.equals(api001res.getDoneOcoSasinariId(), LATEST_OCO_SASINARI_ID_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getDoneOcoSasinariId(), LATEST_OCO_SASINARI_ID_FAIL)) {
                    res.setOco2GyakusasiHouhou(OrderPriceKind.LIMIT.getIfaCd());
                    
                } else if (//注文部.直近OCO指成区分=成行　の場合
                StringUtils.equals(api001res.getDoneOcoSasinariId(), LATEST_OCO_SASINARI_ID_MARKET_PRICE)) {
                    res.setOco2GyakusasiHouhou(OrderPriceKind.MARKET.getIfaCd());
                }
                
                /* OCO2.執行条件（逆指値） */
                res.setOco2GyakusasiJyouken(api001res.getDoneOcoSasinariId());
                
                /* OCO2.注文単価 */
                res.setOco2Price(api001res.getDoneOcoPrice().trim());
            }
        }
        
        if (StringUtils.equals(res.getOrderKind(), OrderClass.IFD.getId())
                || StringUtils.equals(res.getOrderKind(), OrderClass.IFDOCO.getId())) {
            /* IFD1.執行方法 */
            String ifd1SasinariHouhou = null;
            //RBE注文種別=通常注文　の場合
            if (StringUtils.equals(api001res.getRbeOrderKind(), RBE_ORDER_KIND_NORMAL)) {
                //注文部.指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                if (StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_OPEN_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_CLOSE_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_FAILED)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_IOC_PRICE_LIMIT)) {
                    //区分.執行方法 指値
                    ifd1SasinariHouhou = OrderPriceKind.LIMIT.getIfaCd();
                    
                } else if (//注文部.指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_MARKET_ORDER)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_OPEN_MARKET_PRICE)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_CLOSE_MARKET_PRICE)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_IOC_MARKET_PRICE)) {
                    //区分.執行方法 成行
                    ifd1SasinariHouhou = OrderPriceKind.MARKET.getIfaCd();
                }
            } else if (StringUtils.equals(api001res.getRbeOrderKind(), RBE_ORDER_KIND_SLO)) {
                //区分.執行方法 逆指値
                ifd1SasinariHouhou = OrderPriceKind.STOP_PRICE_LIMIT.getIfaCd();
            }
            res.setIfd1SasinariHouhou(ifd1SasinariHouhou);
            /* IFD1.執行条件 */
            res.setIfd1SasinariJyouken(api001res.getSasinariId());
            /* IFD1.発火条件価格（逆指値） */
            res.setIfd1TriggerPrice(api001res.getLatestTriggerPrice().trim());
            /* IFD1.発火条件価格（逆指値）ゾーン */
            res.setIfd1StopOrderPriceText2(api001res.getLatestTriggerZone());
            /* IFD1.執行方法（逆指値） */
            String ifd1GyakusasiHouhou = null;
            //RBE注文種別=逆指値注文　の場合
            if (StringUtils.equals(api001res.getRbeOrderKind(), RBE_ORDER_KIND_SLO)) {
                //注文部.指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                if (StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_OPEN_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_CLOSE_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_FAILED)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_IOC_PRICE_LIMIT)) {
                    // 区分.執行方法 指値
                    ifd1GyakusasiHouhou = OrderPriceKind.LIMIT.getIfaCd();
                } else if (//注文部.指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_MARKET_ORDER)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_OPEN_MARKET_PRICE)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_CLOSE_MARKET_PRICE)
                        || StringUtils.equals(api001res.getSasinariId(), SASINARI_ID_IOC_MARKET_PRICE)) {
                    // 区分.執行方法 成行
                    ifd1GyakusasiHouhou = OrderPriceKind.MARKET.getIfaCd();
                }
            }
            res.setIfd1GyakusasiHouhou(ifd1GyakusasiHouhou);
            /* IFD1.注文単価 */
            res.setIfd1Price(api001res.getLimitPrice().trim());
            /* IFD2.期間.日付 */
            res.setIfd2Limit(api001res.getDoneLimit());
        }
        
        if (StringUtils.equals(res.getOrderKind(), OrderClass.IFD.getId())) {
            /* IFD2.執行方法 */
            String ifd2SasinariHouhou = null;
            // DONE RBE注文種別=通常注文　の場合
            if (StringUtils.equals(api001res.getDoneRbeOrderKind(), DONE_RBE_ORDER_KIND_NORMAL)) {
                //注文部.DONE 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                if (StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_OPEN_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_CLOSE_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_FAIL)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_IOC_PRICE_LIMIT)) {
                    //区分.執行方法 指値
                    ifd2SasinariHouhou = OrderPriceKind.LIMIT.getIfaCd();
                } else if (//注文部.DONE 指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_MARKET_PRICE)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_OPEN_MARKET_PRICE)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_CLOSE_MARKET_PRICE)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_IOC_MARKET_PRICE)) {
                    //区分.執行方法 成行
                    ifd2SasinariHouhou = OrderPriceKind.MARKET.getIfaCd();
                }
            } else if (StringUtils.equals(api001res.getDoneRbeOrderKind(), DONE_RBE_ORDER_KIND_SLO)) {
                //区分.執行方法 逆指値
                ifd2SasinariHouhou = OrderPriceKind.STOP_PRICE_LIMIT.getIfaCd();
            }
            res.setIfd2SasinariHouhou(ifd2SasinariHouhou);
            /* IFD2.執行条件 */
            res.setIfd2SasinariJyouken(api001res.getDoneSasinariId());
            /* IFD2.発火条件価格（逆指値） */
            res.setIfd2TriggerPrice(api001res.getDoneTriggerPrice().trim());
            /* IFD2.発火条件価格（逆指値）ゾーン */
            res.setIfd2StopOrderPriceText2(api001res.getDoneTriggerZone());
            /* IFD2.執行方法（逆指値） */
            String ifd2GyakusasiHouhou = null;
            // DONE RBE注文種別=逆指値注文　の場合
            if (StringUtils.equals(api001res.getDoneRbeOrderKind(), DONE_RBE_ORDER_KIND_SLO)) {
                //注文部.DONE 指成区分=指値、寄指(Y)、引指(H)、不成(F)、IOC指(I)　の場合
                if (StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_OPEN_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_CLOSE_PRICE_LIMIT)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_FAIL)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_IOC_PRICE_LIMIT)) {
                    //区分.執行方法 指値
                    ifd2GyakusasiHouhou = OrderPriceKind.LIMIT.getIfaCd();
                } else if (//注文部.DONE 指成区分=成行、寄成(Y)、引成(H)、IOC成(I)　の場合
                StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_MARKET_PRICE)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_OPEN_MARKET_PRICE)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_CLOSE_MARKET_PRICE)
                        || StringUtils.equals(api001res.getDoneSasinariId(), DONE_SASINARI_ID_IOC_MARKET_PRICE)) {
                    //区分.執行方法 成行
                    ifd2GyakusasiHouhou = OrderPriceKind.MARKET.getIfaCd();
                }
            }
            res.setIfd2GyakusasiHouhou(ifd2GyakusasiHouhou);
            /* IFD2.注文単価 */
            res.setIfd2Price(api001res.getDonePrice().trim());
        }
        
        /* 発火状態 */
        String workingStatus = null;
        //注文部.RBE注文ステータス=1:発火済　の場合
        if (StringUtils.equals(api001res.getRbeOrderStatus(), RBE_ORDER_STATUS_STOP)) {
            //true：発火後
            workingStatus = "true";
        } else {
            //false：発火前
            workingStatus = "false";
        }
        res.setWorkingStatus(workingStatus);
        /* 手数料区分（採用） */
        res.setComIdR(api001res.getComIdR());
        /* 自動注文種別 */
        res.setAutoOrderShubetsu(api001res.getAutoOrderKind());
        /* RBE注文種別 */
        res.setRbeChumonShubetsu(api001res.getRbeOrderKind());
        /* 指成区分 */
        res.setSashinariKbn(api001res.getSasinariId());
        /* 指値 */
        res.setSashine(api001res.getLimitPrice().trim());
        /* トリガ発動ゾーン */
        res.setTriggerZone(api001res.getLatestTriggerZone());
        /* トリガ値段 */
        res.setTriggerNedan(api001res.getLatestTriggerPrice().trim());
        /* OCO指成区分 */
        res.setOcoSasinariKbn(api001res.getLatestOcoSasinariId());
        /* OCO指値 */
        res.setOcoSashine(api001res.getLatestOcoPrice().trim());
        /* DONE指成区分 */
        res.setDoneSasinariKbn(api001res.getDoneSasinariId());
        /* DONE指値 */
        res.setDoneSashine(api001res.getDonePrice().trim());
        /* DONERBE注文種別 */
        res.setDoneRbeOrderKind(api001res.getDoneRbeOrderKind());
        /* DONEトリガ発動ゾーン */
        res.setDoneTriggerZone(api001res.getDoneTriggerZone());
        /* DONEトリガ値段 */
        res.setDoneTriggerNedan(api001res.getDoneTriggerPrice().trim());
        /* DONEOCO指成区分 */
        res.setDoneOcoSasinariKbn(api001res.getDoneOcoSasinariId());
        /* DONEOCO指値 */
        res.setDoneOcoSashine(api001res.getDoneOcoPrice().trim());
        /* DONE有効期限 */
        res.setDoneYuukouKigen(api001res.getDoneLimit());
        /* RBE注文ステータス */
        res.setRbeOrderStatus(api001res.getRbeOrderStatus());
    }
    
    /**
     * SQL001の発行のための検索
     *
     * @throws Exception システムエラー
     */
    private DataList<IfaDomesticStockOrderCancelConfirmSql001SubResponseModel> getIfaEcOrderNo(String ecOrderNo, String orderDayTime, String butenCode)
            throws Exception {
        
        IfaDomesticStockOrderCancelConfirmSql001SubRequestModel sql001SubReq = new IfaDomesticStockOrderCancelConfirmSql001SubRequestModel();
        
        sql001SubReq.setEcOrderNo(ecOrderNo);
        sql001SubReq.setOrderDate(orderDayTime);
        sql001SubReq.setButenCode(butenCode);
        
        return dao.selectIfaDomesticStockOrderCancelConfirmSql001Sub(sql001SubReq);
    }
    
    /**
     * 発注前に注文取消内容を国内株式注文テーブル(SQL001の発行)
     *
     * @param dtoReq A002aリクエストDTO
     * @param sql001SubRes SQL001 サブクエリ①の結果
     * @param cc 顧客共通情報
     * @return A002aレスポンス
     * @throws Exception システムエラー
     */
    private DataList<IfaDomesticStockOrderCancelConfirmA002aDtoResponse> orderRegister(
            IfaDomesticStockOrderCancelConfirmA002aDtoRequest dtoReq,
            DataList<IfaDomesticStockOrderCancelConfirmSql001SubResponseModel> sql001SubRes, CustomerCommon cc)
            throws Exception {
        
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        // 登録処理
        IfaDomesticStockOrderCancelConfirmSql001RequestModel sql001Req = new IfaDomesticStockOrderCancelConfirmSql001RequestModel();
        BeanUtils.copyProperties(sql001Req, dtoReq);

        // 注文状況
        sql001Req.setOrderStatus(ORDER_STATUS_CANCEL);
        
        // 取消ユーザID
        sql001Req.setTorikeshiUserId(ua.getCcsUserId());
        
        // 商品区分
        sql001Req.setSecurityType(SECURITY_TYPE_STOCK);
        
        // 仲介業者コード
        sql001Req.setBrokerCode(cc.getBrokerCode());
        
        //仲介業者営業員コード
        sql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());

        // 手数料区分（採用）
        sql001Req.setComIdR(dtoReq.getComIdR());
        
        //作成者
        sql001Req.setCreateUser(ua.getUserId());
        
        //更新者
        sql001Req.setUpdateUser(ua.getUserId());
        
        // 有効期限
        sql001Req.setYukoKigen(dtoReq.getPeriod());

        // サブクエリ①でレコードが0件の場合
        if (sql001SubRes.getDataList().size() == SQL_SUB_RECODE_ZERO) {
            //IFA注文サブ番号
            sql001Req.setIfaOrderSubNo("1");
            //部店コード
            sql001Req.setButenCode(cc.getButenCode());
            //口座番号
            sql001Req.setAccountNumber(cc.getAccountNumber());
            //顧客ID
            sql001Req.setKokyakuId(cc.getCustomerCode());
            //特定口座区分
            //リクエスト.預り区分='5'、'6'、'7'、'J'　ではない場合
            if (!StringUtils.equals(dtoReq.getDepositType(), SQL_REQ_DEPOSITTYPE_FIVE)
                    && !StringUtils.equals(dtoReq.getDepositType(), SQL_REQ_DEPOSITTYPE_SIX)
                    && !StringUtils.equals(dtoReq.getDepositType(), SQL_REQ_DEPOSITTYPE_SEVEN)
                    && !StringUtils.equals(dtoReq.getDepositType(), SQL_REQ_DEPOSITTYPE_J)) {
                //顧客共通情報.特定口座区分
                sql001Req.setTokuteiKouzaKbn(cc.getSpecificAccountType());
                
            // リクエスト.預り区分='5'、'6'、'7'、'J'　の場合
            } else if (StringUtils.equals(dtoReq.getDepositType(), SQL_REQ_DEPOSITTYPE_FIVE)
                    || StringUtils.equals(dtoReq.getDepositType(), SQL_REQ_DEPOSITTYPE_SIX)
                    || StringUtils.equals(dtoReq.getDepositType(), SQL_REQ_DEPOSITTYPE_SEVEN)
                    || StringUtils.equals(dtoReq.getDepositType(), SQL_REQ_DEPOSITTYPE_J)) {
                //顧客共通情報.ジュニア特定口座区分
                sql001Req.setTokuteiKouzaKbn(cc.getJrTokuteiKouzaKbn());
            }
            // 注文種別 （一覧）
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
            //ユーザーID
            sql001Req.setUserId(ua.getCcsUserId());
            //RBE注文ステータス
            sql001Req.setRbeOrderStand(dtoReq.getRbeOrderStatus());
        } else if (sql001SubRes.getDataList().size() == SQL_SUB_RECODE_ONE) {
            IfaDomesticStockOrderCancelConfirmSql001SubResponseModel sql001SubResFirstRecord = sql001SubRes
                    .getDataList().get(0);
            //IFA注文番号
            sql001Req.setIfaOrderNo(sql001SubResFirstRecord.getIfaOrderNo());
            //IFA注文サブ番号
            sql001Req
                    .setIfaOrderSubNo(String.valueOf(Integer.parseInt(sql001SubResFirstRecord.getIfaOrderSubNo()) + 1));
            //部店コード
            sql001Req.setButenCode(sql001SubResFirstRecord.getButenCode());
            //口座番号
            sql001Req.setAccountNumber(sql001SubResFirstRecord.getAccountNumber());
            //顧客ID
            sql001Req.setKokyakuId(sql001SubResFirstRecord.getKokyakuId());
            //特定口座区分
            sql001Req.setTokuteiKouzaKbn(sql001SubResFirstRecord.getTokuteiKouzaKbn());
            //注文種別
            sql001Req.setOrderKind(sql001SubResFirstRecord.getOrderKind());
            //注文種別 （一覧）
            sql001Req.setOrderStatusListOrderClass(sql001SubResFirstRecord.getOrderStatusListOrderClass());
            //勧誘区分
            sql001Req.setKanyuKbn(sql001SubResFirstRecord.getKanyuKbn());
            //受注方法
            sql001Req.setReceiveOrder(sql001SubResFirstRecord.getReceiveOrder());
            //確認項目.インサイダー確認
            sql001Req.setCheckInsider(sql001SubResFirstRecord.getCheckInsider());
            //確認項目.SOR確認
            sql001Req.setCheckSor(sql001SubResFirstRecord.getCheckSor());
            //アラート内容確認.コンプラチェックワーニング確認
            sql001Req.setCheckCompWrnAlert(sql001SubResFirstRecord.getCheckCompWrnAlert());
            //資金性格区分
            sql001Req.setUaiQaFundCharacter(sql001SubResFirstRecord.getUaiQaFundCharacter());
            //ユーザーID
            sql001Req.setUserId(sql001SubResFirstRecord.getUserId());
            //手数料区分
            sql001Req.setTesuuryouKbn(sql001SubResFirstRecord.getTesuuryouKbn());
            //訂正区分
            sql001Req.setTeiseiKbn(sql001SubResFirstRecord.getTeiseiKbn());
            //譲渡益税区分
            sql001Req.setJoutoekizeiKbn(sql001SubResFirstRecord.getJoutoekizeiKbn());
            //EC受注番号訂正時
            sql001Req.setEc(sql001SubResFirstRecord.getEc());
            //RBE注文ステータス
            sql001Req.setRbeOrderStand(sql001SubResFirstRecord.getRbeOrderStand());
        }
        int insSql001Res = dao.insertIfaDomesticStockOrderCancelConfirmSql001(sql001Req);
        if (insSql001Res != 1) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_FRS_PREORDEREXECUTION_FAILED, IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
        }
        
        IfaDomesticStockOrderCancelConfirmA002aDtoResponse innerData = new IfaDomesticStockOrderCancelConfirmA002aDtoResponse();
        innerData.setIfaOrderNo(sql001Req.getIfaOrderNo()); // MyBatisのselectedKeysでSQL実行時にマッピング
        innerData.setIfaOrderSubNo(sql001Req.getIfaOrderSubNo());
        
        return IfaCommonUtil.createDataList(Arrays.asList(innerData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                StringUtil.EMPTY_STRING);
    }
    
    /**
     * 発注後に注文取消内容を国内株式注文テーブルへ記録する。(SQL002の発行)
     *
     * @param api002Res API002出力データ
     * @param successApi002 API002の成否
     * @param ifaOrderNo IFA注文番号
     * @param ifaOrderSubNo IFA注文サブ番号
     * @return 更新成否 成功：true、失敗：false
     * @throws Exception システムエラー
     */
    private boolean updateOrderInfo(StockCancelOrderOutData api002Res, boolean successApi002, String ifaorderNo,
            String ifaorderSubNo) throws Exception {
        
        IfaDomesticStockOrderCancelConfirmSql002RequestModel sql002Req = new IfaDomesticStockOrderCancelConfirmSql002RequestModel();
        
        if (successApi002) {
            //受注時刻
            sql002Req.setOrderTime(api002Res.getAcceptTime());
        }
        
        //受注日
        sql002Req.setOrderDate(api002Res.getAcceptDate());
        //ユーザー共通情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        //種別
        sql002Req.setShubetu(api002Res.getShubetu());
        //エラーコード
        sql002Req.setCode(api002Res.getCode());
        //エラーメッセージ
        sql002Req.setErrMessage(api002Res.getMessage());
        //更新者
        sql002Req.setUpdateUser(ua.getUserId());
        //IFA注文番号
        sql002Req.setIfaOrderNo(ifaorderNo);
        //IFA注文サブ番号
        sql002Req.setIfaOrderSubNo(ifaorderSubNo);
        //API002の成否
        sql002Req.setSuccessApi002(successApi002);
        
        int insSql002Res = dao.updateIfaDomesticStockOrderCancelConfirmSql002(sql002Req);
        return insSql002Res == 1; // 更新成否
    }
    
}

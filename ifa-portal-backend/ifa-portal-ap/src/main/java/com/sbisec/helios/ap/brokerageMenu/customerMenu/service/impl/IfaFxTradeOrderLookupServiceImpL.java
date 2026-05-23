package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.dto.ExchangeTradeCurrency;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.dto.ExchangeOrderDetail;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA001DtoResponseCurrency;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA001DtoResponseFxOrderInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA001DtoResponseFxTradeMediatePropriety;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA003DtoResponseFxOrderInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA003DtoResponseFxTradeMediatePropriety;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaFxTradeOrderLookupService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 画面ID：SUB0202_0501-01
 * 画面名：為替取引注文照会
 * 2023/09/12 新規作成
 *
 * @author scsk
 *
 */
@Component(value = "cmpIfaFxTradeOrderLookupService")
public class IfaFxTradeOrderLookupServiceImpL implements IfaFxTradeOrderLookupService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaFxTradeOrderLookupServiceImpL.class);
    
    private static final char CHAR_ZERO = '0';
    
    //権限なし(0)
    private static final String AUTH_FLAG_0 = "0";
    
    //空文字列
    private static final String EMPTY = "";
    
    //証券金銭種別(外貨)
    private static final String PRODUCT_CODE_98 = "98";
    
    //国籍コード(99)
    //private static final String COUNTRY_CODE_99 = "99";
    
    //開設状況（未開設）
    private static final String OPEN_STATUS_0 = "0";
    
    //カレンダー区分（証券営業日ベース）
    private static final String CALENDAR_TYPE_0 = "0";
    
    //前営業日
    private static final Integer ONE_DAY_BEFORE = -1;
    
    //ハイフン
    private static final String HYPHEN = "-";
    
    //"yyyy-MM-dd"形式日付
    private static final String FORMAT = "yyyy-MM-dd";
    
    //「すべて」選択の値
    private static final String SELECT_ALL = "0";
    
    //為替注文情報の取得最大件数
    private static final Integer LIMIT = 5000;
    
    //為替注文ステータスORDERING（注文中）
    private static final String EXCHANGE_ORDER_STATUS_ORDERING = "ORDERING";
    
    //為替注文ステータスCOMPLETED（完了）
    private static final String EXCHANGE_ORDER_STATUS_COMPLETED = "COMPLETED";
    
    //為替注文ステータスCANCELING（取消中）
    private static final String EXCHANGE_ORDER_STATUS_CANCELING = "CANCELING";
    
    //為替注文ステータスCANCELLED（取消済）
    private static final String EXCHANGE_ORDER_STATUS_CANCELLED = "CANCELLED";
    
    //為替注文ステータスEXPIRED（失効済）
    private static final String EXCHANGE_ORDER_STATUS_EXPIRED = "EXPIRED";
    
    //為替注文ステータスCORRECTING（訂正中）
    private static final String EXCHANGE_ORDER_STATUS_CORRECTING = "CORRECTING";
    
    //為替処理ステータスPROCESSING（処理中）
    private static final String EXCHANGE_PROC_STATUS_PROCESSING = "PROCESSING";
    
    //為替処理ステータスCANCEL_FAILED（取消失敗）
    private static final String EXCHANGE_PROC_STATUS_CANCEL_FAILED = "CANCEL_FAILED";
    
    //為替処理ステータスBALANCE_SHORT（買付不足）
    private static final String EXCHANGE_PROC_STATUS_BALANCE_SHORT = "BALANCE_SHORT";
    
    //為替処理ステータスORDER_UNABLE（注文不可）
    private static final String EXCHANGE_PROC_STATUS_ORDER_UNABLE = "ORDER_UNABLE";
    
    //注文状況(注文中)
    private static final String ORDER_STATUS_ORDERING = "注文中";
    
    //注文状況(約定済)
    private static final String ORDER_STATUS_COMPLETED = "約定済";
    
    //注文状況(取消中)
    private static final String ORDER_STATUS_CANCELING = "取消中";
    
    //注文状況(取消済)
    private static final String ORDER_STATUS_CANCELLED = "取消済";
    
    //注文状況(買付不足)
    private static final String ORDER_STATUS_BALANCE_SHORT = "買付不足";
    
    //注文状況(注文不可)
    private static final String ORDER_STATUS_ORDER_UNABLE = "注文不可";
    
    //注文状況(失効)
    private static final String ORDER_STATUS_EXPIRED = "失効";
    
    //ソートレベル1
    private static final String SORT_LEVEL_1 = "1";
    
    //ソートレベル2
    private static final String SORT_LEVEL_2 = "2";
    
    //ソートレベル3
    private static final String SORT_LEVEL_3 = "3";
    
    //ソートレベル4
    private static final String SORT_LEVEL_4 = "4";
    
    //ソートレベル5
    private static final String SORT_LEVEL_5 = "5";
    
    //ソートレベル6
    private static final String SORT_LEVEL_6 = "6";
    
    //入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}]
    private static final String ERROR_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    //メッセージ(外貨建商品取引口座が未開設です。)
    private static final String ERROR_CMN_FORIGNSECURITIESACCOUNT_NOTOPEN = "errors.cmn.foreignSecuritiesAccount.notOpen";
    
    //メッセージ(検索結果が0件です。\n条件を設定して再度検索して下さい。)
    private static final String ERROR_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    //メッセージ(検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。)
    private static final String WARINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    // 共通関数FCT001
    @Autowired
    private Fct001 fct001;
    
    // 共通関数FCT003
    @Autowired
    private Fct003 fct003;
    
    // 共通関数FCT007
    @Autowired
    private Fct007 fct007;
    
    // API
    @Autowired
    private ExchangeService exchangeService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaFxTradeOrderLookupA001DtoRequest
     * Dto レスポンス：IfaFxTradeOrderLookupA001DtoResponse
     *
     * @param dtoReq A003リクエスト
     * @return A001レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaFxTradeOrderLookupA001DtoResponse> initializeA001(IfaFxTradeOrderLookupA001DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaFxTradeOrderLookupA001DtoResponse> dtoRes = new DataList<IfaFxTradeOrderLookupA001DtoResponse>();
        
        //開始ログ
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFxTradeOrderLookupServiceImplL.initializeA001");
        }
        
        //顧客共通情報取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        //利用者の口座に対する権限チェックを行う。       
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        //部店コード   設定元：   顧客共通情報.部店コード
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        //口座番号    設定元：   顧客共通情報.口座番号
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        //
        if (AUTH_FLAG_0.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            //権限なし：権限なしエラーを返す
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERROR_BUTENACCOUNTNOTEXIST,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
            return dtoRes;
        }
        
        //利用者の口座における取引コース媒介可否リストを取得する。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        //部店コード   設定元：   顧客共通情報.部店コード
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        //口座番号    設定元：   顧客共通情報.口座番号
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        //証券金銭種別  設定元：  "外貨"
        inputFct003Dto.setProductCd(PRODUCT_CODE_98);
        //取引種別    設定元：    初期化時、""（設定無し）を設定。
        inputFct003Dto.setTradeCd(EMPTY);
        
        //国籍コード　設定無し
        inputFct003Dto.setCountryCd(EMPTY);
        
        //通貨コード   設定元：    初期化時、""（設定無し）を設定。
        inputFct003Dto.setCurrencyCode(EMPTY);
        OutputFct003Dto outputFct003Dto;
        outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        //顧客共通情報.外貨建商品取引口座開設状況、顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        if (OPEN_STATUS_0.equals(customerCommon.getForeignSecurityTradeAccountOpenStatus())
                && OPEN_STATUS_0.equals(customerCommon.getForeignStockTradeAccountOpenStatus())) {
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERROR_CMN_FORIGNSECURITIESACCOUNT_NOTOPEN));
            return dtoRes;
        }
        
        //検索条件指定エリア.絞込期間fromに表示する日付を取得する。
        InputFct007Dto inputFct007Dto = new InputFct007Dto();
        //基準日 設定元：   システム日付
        inputFct007Dto.setStandardDate(DateUtil.now());
        //カレンダー区分 設定元：   ０（証券営業日ベース）
        inputFct007Dto.setCalendarType(CALENDAR_TYPE_0);
        //日数  設定元：   ー1
        inputFct007Dto.setDay(ONE_DAY_BEFORE);
        OutputFct007Dto outputFct007Dto = fct007.getData(inputFct007Dto);
        
        //検索条件指定エリア.通貨のドロップダウンリストに表示する通貨名を取得する。
        //API003呼び出し
        ListExchangeTradeCurrenciesReq listExchangeTradeCurrenciesReq = new ListExchangeTradeCurrenciesReq();
        ListExchangeTradeCurrenciesRes listExchangeTradeCurrenciesRes;
        try {
            listExchangeTradeCurrenciesRes = exchangeService
                    .listExchangeTradeCurrencies(listExchangeTradeCurrenciesReq);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        //検索条件指定エリアの検索条件に適合した為替注文一覧を取得する。
        ListExchangeOrdersReq listExchangeOrdersReq = new ListExchangeOrdersReq();
        //Header.token    
        //顧客共通情報.部店コード(3桁)　＋　”‐”　+　顧客共通情報.口座番号(7桁)
        listExchangeOrdersReq.getHeader().setToken(customerCommon.getButenCode() + HYPHEN
                + StringUtil.fillLeft(customerCommon.getAccountNumber(), CHAR_ZERO, 7));
        //為替営業日(From) "yyyy-MM-dd"形式日付    
        //初期化時：FCT007.指定日
        listExchangeOrdersReq.getParameter()
                .setBusinessDateFrom(DateUtil.format(outputFct007Dto.getDesignatedDate(), FORMAT));
        //為替営業日(To) "yyyy-MM-dd"形式日付  
        //初期化時：システム共通情報.システム日付
        listExchangeOrdersReq.getParameter().setBusinessDateTo(DateUtil.format(DateUtil.now(), FORMAT));
        //注文状況    
        //初期化時：設定しない
        listExchangeOrdersReq.getParameter().setExchangeOrderStatusInput(null);
        //通貨コード   
        //初期化時：設定しない
        listExchangeOrdersReq.getParameter().setCurrencyCode(null);
        //売買区分    
        //初期化時：設定しない
        listExchangeOrdersReq.getParameter().setBuySellCode(null);
        //API004呼び出し
        ListExchangeOrdersRes listExchangeOrdersRes = null;
        try {
            listExchangeOrdersRes = exchangeService.listExchangeOrders(listExchangeOrdersReq);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        boolean overFiveThousand = false;
        boolean isZero = false;
        Integer listSize = 0;
        List<ExchangeOrderDetail> subOrderDetails = new ArrayList<ExchangeOrderDetail>();
        if (listExchangeOrdersRes.getOrderDetails().size() == 0) {
            //取得件数　＝　0件：為替注文情報の取得件数0件リストを返す。
            isZero = true;
        } else if (listExchangeOrdersRes.getOrderDetails().size() > LIMIT) {
            //取得件数　＞　5000件：為替注文情報の取得件数が画面表示最大件数を超過している旨メッセージを表示。
            overFiveThousand = true;
            listSize = listExchangeOrdersRes.getOrderDetails().size();
            subOrderDetails = listExchangeOrdersRes.getOrderDetails().subList(0, LIMIT);
        } else {
            //上記以外：レスポンスに取得した為替注文一覧をセットし次の処理へ。
            subOrderDetails = listExchangeOrdersRes.getOrderDetails();
        }
        
        //レスポンスを設定
        IfaFxTradeOrderLookupA001DtoResponse ifaFxTradeOrderLookupA001DtoResponse = new IfaFxTradeOrderLookupA001DtoResponse();
        //取引停止フラグ 設定元 FCT001  取引停止フラグ
        ifaFxTradeOrderLookupA001DtoResponse.setTradeSuspendFlag(outputFct001Dto.getTradeSuspendFlag());
        
        if (!isZero) {
            //取得した為替注文ステータスおよび為替処理ステータスをもとに、注文状況の表示内容を編集する。
            List<FxOrderInfoForSort> sortedFxOrderInfoList = editFxOrderInfo(subOrderDetails);
            //為替注文情報リスト   ソート後為替注文情報リストを設定
            List<IfaFxTradeOrderLookupA001DtoResponseFxOrderInfo> ifaFxTradeOrderLookupA001DtoResponseFxOrderInfoList = new ArrayList<IfaFxTradeOrderLookupA001DtoResponseFxOrderInfo>();
            for (FxOrderInfoForSort fxOrderInfoForSort : sortedFxOrderInfoList) {
                IfaFxTradeOrderLookupA001DtoResponseFxOrderInfo ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo = new IfaFxTradeOrderLookupA001DtoResponseFxOrderInfo();
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setOrderDate(fxOrderInfoForSort.getOrderDate());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setFxOrderStatus(fxOrderInfoForSort.getFxOrderStatus());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setTradeDateTime(fxOrderInfoForSort.getTradeDateTime());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setDeadlineDate(fxOrderInfoForSort.getDeadlineDate());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setCurrencyCode(fxOrderInfoForSort.getCurrencyCode());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setTradeCd(fxOrderInfoForSort.getTradeCd());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setQuantity(fxOrderInfoForSort.getQuantity());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo
                        .setDeliveryAmount(fxOrderInfoForSort.getDeliveryAmount());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setContractRate(fxOrderInfoForSort.getContractRate());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setTradeKbn(fxOrderInfoForSort.getTradeKbn());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setFxTradeStatus(fxOrderInfoForSort.getFxTradeStatus());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setOrderStatus(fxOrderInfoForSort.getOrderStatus());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setAccountType(fxOrderInfoForSort.getAccountType());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setOrderNumber(fxOrderInfoForSort.getOrderNumber());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setCycleNumber(fxOrderInfoForSort.getCycleNumber());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setOrderEventId(fxOrderInfoForSort.getOrderEventId());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo.setBusinessDay(fxOrderInfoForSort.getBusinessDay());
                ifaFxTradeOrderLookupA001DtoResponseFxOrderInfoList
                        .add(ifaFxTradeOrderLookupA001DtoResponseFxOrderInfo);
            }
            ifaFxTradeOrderLookupA001DtoResponse
                    .setFxOrderInfoList(ifaFxTradeOrderLookupA001DtoResponseFxOrderInfoList);
        } else {
            ifaFxTradeOrderLookupA001DtoResponse.setFxOrderInfoList(new ArrayList<>());
        }
        
        //通貨リスト   
        List<IfaFxTradeOrderLookupA001DtoResponseCurrency> ifaFxTradeOrderLookupA001DtoResponseCurrencyList = new ArrayList<IfaFxTradeOrderLookupA001DtoResponseCurrency>();
        if (listExchangeTradeCurrenciesRes.getCurrencies() != null) {
            for (ExchangeTradeCurrency exchangeTradeCurrency : listExchangeTradeCurrenciesRes.getCurrencies()) {
                IfaFxTradeOrderLookupA001DtoResponseCurrency ifaFxTradeOrderLookupA001DtoResponseCurrency = new IfaFxTradeOrderLookupA001DtoResponseCurrency();
                //通貨リスト.通貨コード 設定元 API003  通貨アイテム.通貨コード
                ifaFxTradeOrderLookupA001DtoResponseCurrency.setCurrencyCode(exchangeTradeCurrency.getCurrencyCode());
                //通貨リスト.通貨名   設定元 API003  通貨アイテム.通貨名
                ifaFxTradeOrderLookupA001DtoResponseCurrency.setCurrency(exchangeTradeCurrency.getCurrencyName());
                //通貨リスト.小数部有効桁数   設定元 API003  通貨アイテム.小数部有効桁数
                ifaFxTradeOrderLookupA001DtoResponseCurrency
                        .setDecimalLength(exchangeTradeCurrency.getDecimalLength().toString());
                ifaFxTradeOrderLookupA001DtoResponseCurrencyList.add(ifaFxTradeOrderLookupA001DtoResponseCurrency);
            }
        }
        ifaFxTradeOrderLookupA001DtoResponse.setCurrencyList(ifaFxTradeOrderLookupA001DtoResponseCurrencyList);
        
        //為替取引媒介可否リスト
        List<IfaFxTradeOrderLookupA001DtoResponseFxTradeMediatePropriety> fxTradeMediateProprietyList = new ArrayList<IfaFxTradeOrderLookupA001DtoResponseFxTradeMediatePropriety>();
        if (outputFct003Dto.getMediateProprietyList() != null) {
            for (MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
                IfaFxTradeOrderLookupA001DtoResponseFxTradeMediatePropriety fxTradeMediatePropriety = new IfaFxTradeOrderLookupA001DtoResponseFxTradeMediatePropriety();
                //為替取引媒介可否リスト.取引種別    設定元 FCT003  媒介可否リスト.取引種別
                fxTradeMediatePropriety.setTradeCd(mediatePropriety.getTradeClass());
                //為替取引媒介可否リスト.通貨コード   設定元 FCT003  媒介可否リスト.通貨コード
                fxTradeMediatePropriety.setCurrencyCode(mediatePropriety.getCurrencyCode());
                //為替取引媒介可否リスト.媒介可否    設定元 FCT003  媒介可否リスト.媒介可否
                fxTradeMediatePropriety.setMediatePropriety(mediatePropriety.getMediatePropriety());
                fxTradeMediateProprietyList.add(fxTradeMediatePropriety);
            }
        }
        ifaFxTradeOrderLookupA001DtoResponse.setFxTradeMediateProprietyList(fxTradeMediateProprietyList);
        
        List<IfaFxTradeOrderLookupA001DtoResponse> ifaFxTradeOrderLookupA001DtoResponseList = new ArrayList<IfaFxTradeOrderLookupA001DtoResponse>();
        ifaFxTradeOrderLookupA001DtoResponseList.add(ifaFxTradeOrderLookupA001DtoResponse);
        if (overFiveThousand) {
            dtoRes = IfaCommonUtil.createDataList(ifaFxTradeOrderLookupA001DtoResponseList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARINGS_DATALIST_OVERMAXROWNUM,
                            new String[] { Integer.toString(LIMIT), Integer.toString(listSize) }));
            return dtoRes;
        } else if (isZero) {
            dtoRes = IfaCommonUtil.createDataList(ifaFxTradeOrderLookupA001DtoResponseList, ErrorLevel.INFO,
                    ERROR_DATALIST_NOTFOUND, IfaCommonUtil.getMessage(ERROR_DATALIST_NOTFOUND));
            return dtoRes;
        }
        dtoRes = IfaCommonUtil.createDataList(ifaFxTradeOrderLookupA001DtoResponseList, ErrorLevel.SUCCESS,
                "", "");
        return dtoRes;
        
    }
    
    /**
     * アクションID：A003
     * アクション名：表示
     * Dto リクエスト：IfaFxTradeOrderLookupA003DtoRequest
     * Dto レスポンス：IfaFxTradeOrderLookupA003DtoResponse
     *
     * @param dtoReq A003リクエスト
     * @return A003レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaFxTradeOrderLookupA003DtoResponse> displayA003(IfaFxTradeOrderLookupA003DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaFxTradeOrderLookupA003DtoResponse> dtoRes = new DataList<IfaFxTradeOrderLookupA003DtoResponse>();
        
        //開始ログ
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFxTradeOrderLookupServiceImplL.displayA003");
        }
        
        //顧客共通情報取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        //利用者の口座に対する権限チェックを行う。       
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        //部店コード   設定元：   顧客共通情報.部店コード
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        //口座番号    設定元：   顧客共通情報.口座番号
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        //
        if (AUTH_FLAG_0.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            //権限なし：権限なしエラーを返す
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERROR_BUTENACCOUNTNOTEXIST,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
            return dtoRes;
        }
        
        //利用者の口座における取引コース媒介可否リストを取得する。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        //部店コード   設定元：   顧客共通情報.部店コード
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        //口座番号    設定元：   顧客共通情報.口座番号
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        //証券金銭種別  設定元：  "外貨"
        inputFct003Dto.setProductCd(PRODUCT_CODE_98);
        //取引種別    設定元：    ""
        inputFct003Dto.setTradeCd(EMPTY);
        
        //国籍コード　設定無し
        inputFct003Dto.setCountryCd(EMPTY);
        
        //通貨コード   設定元：    ""
        inputFct003Dto.setCurrencyCode(EMPTY);
        OutputFct003Dto outputFct003Dto;
        outputFct003Dto = fct003.doCheck(inputFct003Dto);
        
        //顧客共通情報.外貨建商品取引口座開設状況、顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        if (OPEN_STATUS_0.equals(customerCommon.getForeignSecurityTradeAccountOpenStatus())
                && OPEN_STATUS_0.equals(customerCommon.getForeignStockTradeAccountOpenStatus())) {
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERROR_CMN_FORIGNSECURITIESACCOUNT_NOTOPEN));
            return dtoRes;
        }
        
        //検索条件指定エリアの検索条件に適合した為替注文一覧を取得する。(API004)
        ListExchangeOrdersReq listExchangeOrdersReq = new ListExchangeOrdersReq();
        //Header.token    
        //顧客共通情報.部店コード(3桁)　＋　”‐”　+　顧客共通情報.口座番号(7桁)
        listExchangeOrdersReq.getHeader().setToken(customerCommon.getButenCode() + HYPHEN
                + StringUtil.fillLeft(customerCommon.getAccountNumber(), CHAR_ZERO, 7));
        //為替営業日(From) "yyyy-MM-dd"形式日付    
        //表示時：リクエスト.絞込期間from
        listExchangeOrdersReq.getParameter().setBusinessDateFrom(
                DateUtil.format(DateUtil.parse(dtoReq.getRefinementPeriodFrom(), DateUtil.SEPARATED_YYYYMMDD), FORMAT));
        //為替営業日(To) "yyyy-MM-dd"形式日付  
        //表示時：リクエスト.絞込期間to
        listExchangeOrdersReq.getParameter().setBusinessDateTo(
                DateUtil.format(DateUtil.parse(dtoReq.getRefinementPeriodTo(), DateUtil.SEPARATED_YYYYMMDD), FORMAT));
        //注文状況    
        //表示時：リクエスト.注文状況 （リクエスト.注文状況が”すべて”の場合、設定しない）
        if (SELECT_ALL.equals(dtoReq.getOrderStatus())) {
            listExchangeOrdersReq.getParameter().setExchangeOrderStatusInput(null);
        } else {
            //　内部コード　→　外部コードに変換 API呼び出しところに実装
            //            String exchangeOrderStatusInputAthena = codeListService.convertKeyToExtKey(FX_TRADE_SEARCH_ORDER_STATUS,
            //                    ATHENA, dtoReq.getOrderStatus());
            listExchangeOrdersReq.getParameter().setExchangeOrderStatusInput(dtoReq.getOrderStatus());
        }
        //通貨コード   
        //表示時：リクエスト.通貨コード （リクエスト.通貨コードが”すべて”の場合、設定しない）
        if (SELECT_ALL.equals(dtoReq.getCurrencyCode())) {
            listExchangeOrdersReq.getParameter().setCurrencyCode(null);
        } else {
            listExchangeOrdersReq.getParameter().setCurrencyCode(dtoReq.getCurrencyCode());
        }
        //売買区分    
        //表示時：　リクエスト.売買区分　（リクエスト.売買区分が”すべて”の場合、設定しない）
        if (SELECT_ALL.equals(dtoReq.getTradeKbn())) {
            listExchangeOrdersReq.getParameter().setBuySellCode(null);
        } else {
            //　内部コード　→　外部コードに変換 API呼び出しところに実装
            //            String buySellAthena = codeListService.convertKeyToExtKey(FX_TRADE_SEARCH_TRADE_CLASS, ATHENA,
            //                    dtoReq.getTradeKbn());
            listExchangeOrdersReq.getParameter().setBuySellCode(dtoReq.getTradeKbn());
        }
        ListExchangeOrdersRes listExchangeOrdersRes = null;
        try {
            listExchangeOrdersRes = exchangeService.listExchangeOrders(listExchangeOrdersReq);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        boolean overFiveThousand = false;
        boolean isZero = false;
        Integer listSize = 0;
        List<ExchangeOrderDetail> subOrderDetails = new ArrayList<ExchangeOrderDetail>();
        if (listExchangeOrdersRes.getOrderDetails().size() == 0) {
            //取得件数　＝　0件：為替注文情報の取得件数0件リストを返す。
            isZero = true;
        } else if (listExchangeOrdersRes.getOrderDetails().size() > LIMIT) {
            //取得件数　＞　5000件：為替注文情報の取得件数が画面表示最大件数を超過している旨メッセージを表示。
            overFiveThousand = true;
            listSize = listExchangeOrdersRes.getOrderDetails().size();
            subOrderDetails = listExchangeOrdersRes.getOrderDetails().subList(0, LIMIT);
            
        } else {
            //上記以外：レスポンスに取得した為替注文一覧をセットし次の処理へ。
            subOrderDetails = listExchangeOrdersRes.getOrderDetails();
        }
        
        //レスポンスを設定
        IfaFxTradeOrderLookupA003DtoResponse ifaFxTradeOrderLookupA003DtoResponse = new IfaFxTradeOrderLookupA003DtoResponse();
        
        //取引停止フラグ 設定元 FCT001  取引停止フラグ
        ifaFxTradeOrderLookupA003DtoResponse.setTradeSuspendFlag(outputFct001Dto.getTradeSuspendFlag());
        
        if (!isZero) {
            //取得した為替注文ステータスおよび為替処理ステータスをもとに、注文状況の表示内容を編集する。
            List<FxOrderInfoForSort> sortedFxOrderInfoList = editFxOrderInfo(subOrderDetails);
            //為替注文情報リスト   ソート後為替注文情報リストを設定
            List<IfaFxTradeOrderLookupA003DtoResponseFxOrderInfo> ifaFxTradeOrderLookupA003DtoResponseFxOrderInfoList = new ArrayList<IfaFxTradeOrderLookupA003DtoResponseFxOrderInfo>();
            for (FxOrderInfoForSort fxOrderInfoForSort : sortedFxOrderInfoList) {
                IfaFxTradeOrderLookupA003DtoResponseFxOrderInfo ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo = new IfaFxTradeOrderLookupA003DtoResponseFxOrderInfo();
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setOrderDate(fxOrderInfoForSort.getOrderDate());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setFxOrderStatus(fxOrderInfoForSort.getFxOrderStatus());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setTradeDateTime(fxOrderInfoForSort.getTradeDateTime());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setDeadlineDate(fxOrderInfoForSort.getDeadlineDate());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setCurrencyCode(fxOrderInfoForSort.getCurrencyCode());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setTradeCd(fxOrderInfoForSort.getTradeCd());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setQuantity(fxOrderInfoForSort.getQuantity());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo
                        .setDeliveryAmount(fxOrderInfoForSort.getDeliveryAmount());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setContractRate(fxOrderInfoForSort.getContractRate());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setTradeKbn(fxOrderInfoForSort.getTradeKbn());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setFxTradeStatus(fxOrderInfoForSort.getFxTradeStatus());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setOrderStatus(fxOrderInfoForSort.getOrderStatus());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setAccountType(fxOrderInfoForSort.getAccountType());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setOrderNumber(fxOrderInfoForSort.getOrderNumber());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setCycleNumber(fxOrderInfoForSort.getCycleNumber());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setOrderEventId(fxOrderInfoForSort.getOrderEventId());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo.setBusinessDay(fxOrderInfoForSort.getBusinessDay());
                ifaFxTradeOrderLookupA003DtoResponseFxOrderInfoList
                        .add(ifaFxTradeOrderLookupA003DtoResponseFxOrderInfo);
            }
            ifaFxTradeOrderLookupA003DtoResponse
                    .setFxOrderInfoList(ifaFxTradeOrderLookupA003DtoResponseFxOrderInfoList);
        } else {
            ifaFxTradeOrderLookupA003DtoResponse.setFxOrderInfoList(new ArrayList<>());
        }
        
        //為替取引媒介可否リスト
        List<IfaFxTradeOrderLookupA003DtoResponseFxTradeMediatePropriety> fxTradeMediateProprietyList = new ArrayList<IfaFxTradeOrderLookupA003DtoResponseFxTradeMediatePropriety>();
        if (outputFct003Dto.getMediateProprietyList() != null) {
            for (MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
                IfaFxTradeOrderLookupA003DtoResponseFxTradeMediatePropriety fxTradeMediatePropriety = new IfaFxTradeOrderLookupA003DtoResponseFxTradeMediatePropriety();
                //為替取引媒介可否リスト.取引種別    設定元 FCT003  媒介可否リスト.取引種別
                fxTradeMediatePropriety.setTradeCd(mediatePropriety.getTradeClass());
                //為替取引媒介可否リスト.通貨コード   設定元 FCT003  媒介可否リスト.通貨コード
                fxTradeMediatePropriety.setCurrencyCode(mediatePropriety.getCurrencyCode());
                //為替取引媒介可否リスト.媒介可否    設定元 FCT003  媒介可否リスト.媒介可否
                fxTradeMediatePropriety.setMediatePropriety(mediatePropriety.getMediatePropriety());
                fxTradeMediateProprietyList.add(fxTradeMediatePropriety);
            }
        }
        
        ifaFxTradeOrderLookupA003DtoResponse.setFxTradeMediateProprietyList(fxTradeMediateProprietyList);
        
        List<IfaFxTradeOrderLookupA003DtoResponse> ifaFxTradeOrderLookupA003DtoResponseList = new ArrayList<IfaFxTradeOrderLookupA003DtoResponse>();
        ifaFxTradeOrderLookupA003DtoResponseList.add(ifaFxTradeOrderLookupA003DtoResponse);
        if (overFiveThousand) {
            dtoRes = IfaCommonUtil.createDataList(ifaFxTradeOrderLookupA003DtoResponseList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARINGS_DATALIST_OVERMAXROWNUM,
                            new String[] { Integer.toString(LIMIT), Integer.toString(listSize) }));
            return dtoRes;
        } else if (isZero) {
            dtoRes = IfaCommonUtil.createDataList(ifaFxTradeOrderLookupA003DtoResponseList, ErrorLevel.INFO,
                    ERROR_DATALIST_NOTFOUND, IfaCommonUtil.getMessage(ERROR_DATALIST_NOTFOUND));
            return dtoRes;
        }
        dtoRes = IfaCommonUtil.createDataList(ifaFxTradeOrderLookupA003DtoResponseList, ErrorLevel.SUCCESS,
                "", "");
        return dtoRes;
    }
    
    private List<FxOrderInfoForSort> editFxOrderInfo(List<ExchangeOrderDetail> exchangeOrderDetailList) {
        
        List<FxOrderInfoForSort> fxOrderInfoForSortList = new ArrayList<FxOrderInfoForSort>();
        for (ExchangeOrderDetail exchangeOrderDetail : exchangeOrderDetailList) {
            FxOrderInfoForSort fxOrderInfoForSort = new FxOrderInfoForSort();
            // 為替注文情報リスト.注文日時  設定元：   API004  為替注文情報.通貨コード.注文日時
            fxOrderInfoForSort.setOrderDate(exchangeOrderDetail.getOrderInputDatetime());
            // 為替注文情報リスト.為替注文ステータス 設定元：   API004  為替注文情報.為替注文ステータス
            fxOrderInfoForSort.setFxOrderStatus(exchangeOrderDetail.getExchangeOrderStatus());
            // 為替注文情報リスト.約定日時  設定元：   API004  為替注文情報.通貨コード.約定日時
            fxOrderInfoForSort.setTradeDateTime(exchangeOrderDetail.getExecutionDatetime());
            // 為替注文情報リスト.締切日時  設定元：   API004  為替注文情報.通貨コード.締切日時
            fxOrderInfoForSort.setDeadlineDate(exchangeOrderDetail.getCloseDatetime());
            // 為替注文情報リスト.通貨コード 設定元：   API004  為替注文情報.通貨コード
            fxOrderInfoForSort.setCurrencyCode(exchangeOrderDetail.getCurrencyCode());
            // 為替注文情報リスト.取引種別  設定元：   API004  為替注文情報.注文種別表示
            fxOrderInfoForSort.setTradeCd(exchangeOrderDetail.getOrderType());
            // 為替注文情報リスト.数量    設定元：   API004  為替注文情報.通貨コード.注文金額
            fxOrderInfoForSort.setQuantity(exchangeOrderDetail.getOrderAmount());
            // 為替注文情報リスト.受渡金額  設定元：   API004  為替注文情報.通貨コード.受渡金額
            fxOrderInfoForSort.setDeliveryAmount(exchangeOrderDetail.getNetAmount());
            // 為替注文情報リスト.約定レート 設定元：   API004  為替注文情報.通貨コード.レート
            fxOrderInfoForSort.setContractRate(exchangeOrderDetail.getExchangeRate());
            // 為替注文情報リスト.売買区分  設定元：   API004  為替注文情報.売買区分
            fxOrderInfoForSort.setTradeKbn(exchangeOrderDetail.getBuySellCode());
            // 為替注文情報リスト.為替処理ステータス 設定元：   API004  為替注文情報.為替処理ステータス
            fxOrderInfoForSort.setFxTradeStatus(exchangeOrderDetail.getExchangeProcStatus());
            // 為替注文情報リスト.注文状況  設定元：   算出値   
            if (EXCHANGE_ORDER_STATUS_ORDERING.equals(exchangeOrderDetail.getExchangeOrderStatus())
                    && (null == exchangeOrderDetail.getExchangeProcStatus()
                            || exchangeOrderDetail.getExchangeProcStatus() == "")) {
                //為替注文ステータス：  ORDERING（注文中）   為替処理ステータス：  null    →注文状況：  '注文中'
                fxOrderInfoForSort.setOrderStatus(ORDER_STATUS_ORDERING);
            } else if (EXCHANGE_ORDER_STATUS_ORDERING.equals(exchangeOrderDetail.getExchangeOrderStatus())
                    && EXCHANGE_PROC_STATUS_PROCESSING.equals(exchangeOrderDetail.getExchangeProcStatus())) {
                //為替注文ステータス：  ORDERING（注文中）   為替処理ステータス：  PROCESSING（処理中） →注文状況：  '注文中'
                fxOrderInfoForSort.setOrderStatus(ORDER_STATUS_ORDERING);
            } else if (EXCHANGE_ORDER_STATUS_ORDERING.equals(exchangeOrderDetail.getExchangeOrderStatus())
                    && EXCHANGE_PROC_STATUS_CANCEL_FAILED.equals(exchangeOrderDetail.getExchangeProcStatus())) {
                //為替注文ステータス：  ORDERING（注文中）   為替処理ステータス：  CANCEL_FAILED（取消失敗） →注文状況：  '注文中'
                fxOrderInfoForSort.setOrderStatus(ORDER_STATUS_ORDERING);
            } else if (EXCHANGE_ORDER_STATUS_COMPLETED.equals(exchangeOrderDetail.getExchangeOrderStatus())
                    && (null == exchangeOrderDetail.getExchangeProcStatus()
                            || exchangeOrderDetail.getExchangeProcStatus() == "")) {
                //為替注文ステータス：  COMPLETED（完了）   為替処理ステータス：  null    →注文状況：  '約定済'
                fxOrderInfoForSort.setOrderStatus(ORDER_STATUS_COMPLETED);
            } else if (EXCHANGE_ORDER_STATUS_CANCELING.equals(exchangeOrderDetail.getExchangeOrderStatus())
                    && (null == exchangeOrderDetail.getExchangeProcStatus()
                            || exchangeOrderDetail.getExchangeProcStatus() == "")) {
                //為替注文ステータス：  CANCELING（取消中）  為替処理ステータス：  null    →注文状況：  '取消中'
                fxOrderInfoForSort.setOrderStatus(ORDER_STATUS_CANCELING);
            } else if (EXCHANGE_ORDER_STATUS_CANCELLED.equals(exchangeOrderDetail.getExchangeOrderStatus())
                    && (null == exchangeOrderDetail.getExchangeProcStatus()
                            || exchangeOrderDetail.getExchangeProcStatus() == "")) {
                //為替注文ステータス：  CANCELLED（取消済）  為替処理ステータス：  null    →注文状況：  '取消済'
                fxOrderInfoForSort.setOrderStatus(ORDER_STATUS_CANCELLED);
            } else if (EXCHANGE_ORDER_STATUS_EXPIRED.equals(exchangeOrderDetail.getExchangeOrderStatus())
                    && EXCHANGE_PROC_STATUS_BALANCE_SHORT.equals(exchangeOrderDetail.getExchangeProcStatus())) {
                //為替注文ステータス：  EXPIRED（失効済）    為替処理ステータス：  BALANCE_SHORT（買付不足） →注文状況：  '買付不足'
                fxOrderInfoForSort.setOrderStatus(ORDER_STATUS_BALANCE_SHORT);
            } else if (EXCHANGE_ORDER_STATUS_EXPIRED.equals(exchangeOrderDetail.getExchangeOrderStatus())
                    && EXCHANGE_PROC_STATUS_ORDER_UNABLE.equals(exchangeOrderDetail.getExchangeProcStatus())) {
                //為替注文ステータス：  EXPIRED（失効済）    為替処理ステータス：  ORDER_UNABLE（注文不可）  →注文状況：  '注文不可'
                fxOrderInfoForSort.setOrderStatus(ORDER_STATUS_ORDER_UNABLE);
            } else if (EXCHANGE_ORDER_STATUS_EXPIRED.equals(exchangeOrderDetail.getExchangeOrderStatus())
                    && (null == exchangeOrderDetail.getExchangeProcStatus()
                            || exchangeOrderDetail.getExchangeProcStatus() == "")) {
                //為替注文ステータス：  EXPIRED（失効済）    為替処理ステータス：  null    →注文状況：  '失効'
                fxOrderInfoForSort.setOrderStatus(ORDER_STATUS_EXPIRED);
            } else {
                //上記以外
                fxOrderInfoForSort.setOrderStatus(EMPTY);
            }
            // 為替注文情報リスト.口座区分  設定元：   API004  為替注文情報.口座区分
            fxOrderInfoForSort.setAccountType(exchangeOrderDetail.getSpecificAccountCode());
            // 為替注文情報リスト.注文番号  設定元：   API004  為替注文情報.注文番号
            fxOrderInfoForSort.setOrderNumber(exchangeOrderDetail.getOrderNo());
            // 為替注文情報リスト.サイクル番号    設定元：   API004  為替注文情報.為替処理ステータス.サイクル番号
            fxOrderInfoForSort.setCycleNumber(exchangeOrderDetail.getCycleNumber());
            // 為替注文情報リスト.注文イベントID  設定元：   API004  為替注文情報.売買区分.注文イベントID
            fxOrderInfoForSort.setOrderEventId(exchangeOrderDetail.getOrderEventId());
            // 為替注文情報リスト.営業日    設定元：   API004  為替注文情報.営業日"yyyy-MM-dd"形式
            fxOrderInfoForSort.setBusinessDay(exchangeOrderDetail.getBusinessDate());
            
            //ソース用項目設定
            if (EXCHANGE_ORDER_STATUS_ORDERING.equals(exchangeOrderDetail.getExchangeOrderStatus())) {
                fxOrderInfoForSort.setOrderStatusValue(SORT_LEVEL_1);
            } else if (EXCHANGE_ORDER_STATUS_COMPLETED.equals(exchangeOrderDetail.getExchangeOrderStatus())) {
                fxOrderInfoForSort.setOrderStatusValue(SORT_LEVEL_2);
            } else if (EXCHANGE_ORDER_STATUS_CANCELING.equals(exchangeOrderDetail.getExchangeOrderStatus())) {
                fxOrderInfoForSort.setOrderStatusValue(SORT_LEVEL_3);
            } else if (EXCHANGE_ORDER_STATUS_CANCELLED.equals(exchangeOrderDetail.getExchangeOrderStatus())) {
                fxOrderInfoForSort.setOrderStatusValue(SORT_LEVEL_4);
            } else if (EXCHANGE_ORDER_STATUS_EXPIRED.equals(exchangeOrderDetail.getExchangeOrderStatus())) {
                fxOrderInfoForSort.setOrderStatusValue(SORT_LEVEL_5);
            } else if (EXCHANGE_ORDER_STATUS_CORRECTING.equals(exchangeOrderDetail.getExchangeOrderStatus())) {
                fxOrderInfoForSort.setOrderStatusValue(SORT_LEVEL_6);
            } else {
                continue;
            }
            fxOrderInfoForSortList.add(fxOrderInfoForSort);
        }
        //編集済の為替注文一覧について、以下の順でソートする。
        //ⅰ.注文日時：降順
        //ⅱ.売買区分：買付　→　売却
        //ⅲ.為替注文ステータス：　注文中　→　完了　→　取消中　→　取消済　→　失効済 → 訂正中
        Comparator<FxOrderInfoForSort> comparator = Comparator.comparing(FxOrderInfoForSort::getOrderDate).reversed()
                .thenComparing(FxOrderInfoForSort::getTradeKbn).thenComparing(FxOrderInfoForSort::getOrderStatusValue);
        Collections.sort(fxOrderInfoForSortList, comparator);
        return fxOrderInfoForSortList;
    }
    
    @Data
    private class FxOrderInfoForSort {
        
        /** 注文日時. */
        private String orderDate;
        
        /** 為替注文ステータス. */
        private String fxOrderStatus;
        
        /** 約定日時. */
        private String tradeDateTime;
        
        /** 締切日時. */
        private String deadlineDate;
        
        /** 通貨コード（全角半角）. */
        private String currencyCode;
        
        /** 取引種別. */
        private String tradeCd;
        
        /** 数量（数値(整数)）. */
        private String quantity;
        
        /** 受渡金額（数値(整数)）. */
        private String deliveryAmount;
        
        /** 約定レート. */
        private String contractRate;
        
        /** 売買区分（全角半角）. */
        private String tradeKbn;
        
        /** 為替処理ステータス. */
        private String fxTradeStatus;
        
        /** 注文状況（全角半角）. */
        private String orderStatus;
        
        /** 口座区分. */
        private String accountType;
        
        /** 注文番号（数字）. */
        private String orderNumber;
        
        /** サイクル番号. */
        private String cycleNumber;
        
        /** 注文イベントID. */
        private String orderEventId;
        
        /** 営業日 */
        private String businessDay;
        
        /** 注文状況ソート用値 */
        private String orderStatusValue;
    }
    
}

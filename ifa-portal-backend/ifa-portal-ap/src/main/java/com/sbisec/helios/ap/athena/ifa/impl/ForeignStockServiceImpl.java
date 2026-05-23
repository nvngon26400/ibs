package com.sbisec.helios.ap.athena.ifa.impl;

import java.util.List;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.common.Page;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ClosedPositionInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInputForConfirmForeignStockClosedMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderInput;
import com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusReq;
import com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetOffsetBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetOffsetBusinessDateResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListForeignStockOrdersReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListForeignStockOrdersResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.service.CometForeignStockAttentionSecuritiesService;
import com.sbisec.helios.ap.athena.service.CometForeignStockFrnTradeDateService;
import com.sbisec.helios.ap.athena.service.CometForeignStockLendingService;
import com.sbisec.helios.ap.athena.service.CometForeignStockMarginOrderService;
import com.sbisec.helios.ap.athena.service.CometForeignStockOrderService;
import com.sbisec.helios.ap.athena.service.CometForeignStockSecuritiesService;
import com.sbisec.helios.ap.athena.service.CometForeignStockShortableStockService;
import com.sbisec.helios.ap.athena.utils.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * IFA Athena 外国株式 Service
 *
 */
@Service
public class ForeignStockServiceImpl implements ForeignStockService {
    
    @Autowired
    CometForeignStockSecuritiesService cometForeignStockSecuritiesService;
    
    @Autowired
    CometForeignStockAttentionSecuritiesService cometForeignStockAttentionSecuritiesService;
    
    @Autowired
    CometForeignStockMarginOrderService cometForeignStockMarginOrderService;
    
    @Autowired
    CometForeignStockShortableStockService cometForeignStockShortableStockService;
    
    @Autowired
    CometForeignStockFrnTradeDateService cometForeignStockFrnTradeDateService;
    
    @Autowired
    CometForeignStockOrderService cometForeignStockOrderService;
    
    @Autowired
    CometForeignStockLendingService cometForeignStockLendingService;
    
    private static final Logger LOG = LoggerFactory.getLogger(ForeignStockServiceImpl.class);
    
    @Override
    public GetForeignStockSecuritiesResp getForeignStockSecurities(String countryCode, String securitiesCode)
            throws Exception {
        
        GetForeignStockSecuritiesReq request = new GetForeignStockSecuritiesReq();
        request.getParameter().setCountryCode(countryCode);
        request.getParameter().setSecuritiesCode(securitiesCode);
        
        return cometForeignStockSecuritiesService.getForeignStockSecurities(request);
    }
    
    @Override
    public GetForeignStockAttentionSecuritiesResp getForeignStockAttentionSecurities(String countryCode,
            String securitiesCode) throws Exception {
        
        GetForeignStockAttentionSecuritiesReq request = new GetForeignStockAttentionSecuritiesReq();
        request.getParameter().setCountryCode(countryCode);
        request.getParameter().setSecuritiesCode(securitiesCode);
        return cometForeignStockAttentionSecuritiesService.getForeignStockAttentionSecurities(request);
    }
    
    @Override
    public CreateForeignStockMarginOrderResp createForeignStockMarginOrder(String butenCode, String accountNumber,
            String tradePassword, String requestId, String channel, MarginOrderInput marginOrderInput)
            throws Exception {
        
        /* API引数初期化 */
        CreateForeignStockMarginOrderReq request = new CreateForeignStockMarginOrderReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // Header:取引パスワード
        request.getHeader()
                .setTrade_password(StringUtil.isNullOrEmpty(tradePassword) ? StringUtil.EMPTY_STRING : tradePassword);
        // Header:チケット
        request.getHeader().setRequest_id(StringUtil.isNullOrEmpty(requestId) ? StringUtil.EMPTY_STRING : requestId);
        // Header:チャネル
        request.getHeader().setChannel(StringUtil.isNullOrEmpty(channel) ? RequestUtil.getChannel() : channel);
        // パラメータ:国コード
        request.getParameter().setCountryCode(marginOrderInput.getCountryCode());
        // パラメータ:市場コード
        request.getParameter().setMarketCode(marginOrderInput.getMarketCode());
        // パラメータ:銘柄コード
        request.getParameter().setSecuritiesCode(marginOrderInput.getSecuritiesCode());
        // パラメータ:売買区分
        request.getParameter().setBuySellCode(marginOrderInput.getBuySellCode());
        // パラメータ:注文数量
        request.getParameter().setOrderQuantity(marginOrderInput.getOrderQuantity());
        // パラメータ:価格条件
        request.getParameter().setOrderPriceKindCode(marginOrderInput.getOrderPriceKindCode());
        // パラメータ:注文単価
        request.getParameter().setOrderPrice(marginOrderInput.getOrderPrice());
        // パラメータ:発火条件価格
        request.getParameter().setStopPrice(marginOrderInput.getStopPrice());
        // パラメータ:トレールストップ幅
        request.getParameter().setTrailingStopAmount(marginOrderInput.getTrailingStopAmount());
        // パラメータ:期間条件
        request.getParameter().setOrderLimitCode(marginOrderInput.getOrderLimitCode());
        // パラメータ:期間
        request.getParameter().setOrderTerm(marginOrderInput.getOrderTerm());
        // パラメータ:預り区分
        request.getParameter().setSpecificAccountCode(marginOrderInput.getSpecificAccountCode());
        // パラメータ:決済方法
        request.getParameter().setSettlementMethodCode(marginOrderInput.getSettlementMethodCode());
        // パラメータ:信用期日
        request.getParameter().setMarginCloseLimitType(marginOrderInput.getMarginCloseLimitType());
        
        /* 外国株式信用新規建注文確認APIの戻り値を返す */
        return cometForeignStockMarginOrderService.createForeignStockMarginOrder(request);
    }
    
    @Override
    public GetForeignStockCreatedMarginOrderInitializationResp getForeignStockCreatedMarginOrderInitialization(
            String butenCode, String accountNumber, String countryCode, String securitiesCode, String stockTradeType,
            String buySellCode, String specificAccountCode) throws Exception {
        
        /* API引数初期化 */
        GetForeignStockCreatedMarginOrderInitializationReq request = new GetForeignStockCreatedMarginOrderInitializationReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // パラメータ フィルド:国コード
        request.getParameter().setCountryCode(countryCode);
        // パラメータ フィルド:銘柄コード
        request.getParameter().setSecuritiesCode(securitiesCode);
        // パラメータ フィルド:株取引区分
        request.getParameter().setStockTradeType(stockTradeType);
        // パラメータ フィルド:売買区分
        request.getParameter().setBuySellCode(buySellCode);
        
        request.getParameter().setSpecificAccountCode(specificAccountCode);
        /* 外国株式信用注文初期情報取得APIの戻り値を返す */
        return cometForeignStockMarginOrderService.getForeignStockCreatedMarginOrderInitialization(request);
    }
    
    @Override
    public GetForeignStockCreatedMarginOrderInitializationResp getForeignStockCreatedMarginOrderInitializationNoConvertBuySellCode(
            String butenCode, String accountNumber, String countryCode, String securitiesCode, String stockTradeType,
            String buySellCode, String specificAccountCode) throws Exception {
        
        /* API引数初期化 */
        GetForeignStockCreatedMarginOrderInitializationReq request = new GetForeignStockCreatedMarginOrderInitializationReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // パラメータ フィルド:国コード
        request.getParameter().setCountryCode(countryCode);
        // パラメータ フィルド:銘柄コード
        request.getParameter().setSecuritiesCode(securitiesCode);
        // パラメータ フィルド:株取引区分
        request.getParameter().setStockTradeType(stockTradeType);
        // パラメータ フィルド:売買区分
        request.getParameter().setBuySellCode(buySellCode);
        
        request.getParameter().setSpecificAccountCode(specificAccountCode);
        
        /* 外国株式信用注文初期情報取得APIの戻り値を返す */
        return cometForeignStockMarginOrderService
                .getForeignStockCreatedMarginOrderInitializationNoConvertBuySellCode(request);
    }
    
    @Override
    public CloseForeignStockMarginOrderResp closeForeignStockMarginOrder(String butenCode, String accountNumber,
            String tradePassword, String requestId, String channel,
            MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder marginOrder,
            List<ClosedPositionInput> positions) throws Exception {
        
        // API引数初期化
        CloseForeignStockMarginOrderReq request = new CloseForeignStockMarginOrderReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // Header:取引パスワード
        request.getHeader()
                .setTrade_password(StringUtil.isNullOrEmpty(tradePassword) ? StringUtil.EMPTY_STRING : tradePassword);
        // Header:チケット
        request.getHeader().setRequest_id(StringUtil.isNullOrEmpty(requestId) ? StringUtil.EMPTY_STRING : requestId);
        // Header:チャネル
        request.getHeader().setChannel(channel);
        // パラメータ:MarginOrderInput-信用注文情報
        request.getParameter().setMarginOrder(marginOrder);
        // パラメータ:決済相手建玉明細情報
        request.getParameter().setPositions(positions);
        /* 外国株式信用返済注文確認APIの戻り値を返す */
        return cometForeignStockMarginOrderService.closeForeignStockMarginOrder(request);
    }
    
    @Override
    public ConfirmForeignStockCreatedMarginOrderResp confirmForeignStockCreatedMarginOrder(String butenCode,
            String accountNumber, String tradePassword, String ticket, String channel,
            MarginOrderInput marginOrderInput) throws Exception {
        
        /* API引数初期化 */
        ConfirmForeignStockCreatedMarginOrderReq request = new ConfirmForeignStockCreatedMarginOrderReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // Header:取引パスワード
        request.getHeader()
                .setTrade_password(StringUtil.isNullOrEmpty(tradePassword) ? StringUtil.EMPTY_STRING : tradePassword);
        // Header:チケット
        request.getHeader().setTicket(StringUtil.isNullOrEmpty(ticket) ? StringUtil.EMPTY_STRING : ticket);
        // Header:チャネル
        request.getHeader().setChannel(StringUtil.isNullOrEmpty(channel) ? RequestUtil.getChannel() : channel);
        // パラメータ:国コード
        request.getParameter().setCountryCode(marginOrderInput.getCountryCode());
        // パラメータ:市場コード
        request.getParameter().setMarketCode(marginOrderInput.getMarketCode());
        // パラメータ:銘柄コード
        request.getParameter().setSecuritiesCode(marginOrderInput.getSecuritiesCode());
        // パラメータ:売買区分
        request.getParameter().setBuySellCode(marginOrderInput.getBuySellCode());
        // パラメータ:注文数量
        request.getParameter().setOrderQuantity(marginOrderInput.getOrderQuantity());
        // パラメータ:価格条件
        request.getParameter().setOrderPriceKindCode(marginOrderInput.getOrderPriceKindCode());
        // パラメータ:注文単価
        request.getParameter().setOrderPrice(marginOrderInput.getOrderPrice());
        // パラメータ:発火条件価格
        request.getParameter().setStopPrice(marginOrderInput.getStopPrice());
        // パラメータ:トレールストップ幅
        request.getParameter().setTrailingStopAmount(marginOrderInput.getTrailingStopAmount());
        // パラメータ:期間条件
        request.getParameter().setOrderLimitCode(marginOrderInput.getOrderLimitCode());
        // パラメータ:期間
        request.getParameter().setOrderTerm(marginOrderInput.getOrderTerm());
        // パラメータ:預り区分
        request.getParameter().setSpecificAccountCode(marginOrderInput.getSpecificAccountCode());
        // パラメータ:決済方法
        request.getParameter().setSettlementMethodCode(marginOrderInput.getSettlementMethodCode());
        // パラメータ:信用期日
        request.getParameter().setMarginCloseLimitType(marginOrderInput.getMarginCloseLimitType());
        
        /* 外国株式信用新規建注文確認APIの戻り値を返す */
        return cometForeignStockMarginOrderService.confirmForeignStockCreatedMarginOrder(request);
    }
    
    @Override
    public ListShortableStocksResp listShortableStocks(String countryCode, String searchKeyword,
            String shortableStockTradeStatus, Page page) throws Exception {
        
        // API引数初期化
        ListShortableStocksReq request = new ListShortableStocksReq();
        // パラメータ:国コード
        request.getParameter().setCountryCode(countryCode);
        // パラメータ:検索用キーワード（最大 384 バイト、大体 128 つ UTF-8 文字に相当する）
        request.getParameter().setSearchKeyword(searchKeyword);
        // パラメータ:銘柄信用売建可能
        request.getParameter().setTradeStatus(shortableStockTradeStatus);
        // パラメータ:ページ
        request.getParameter().setPage(page);
        
        /* 外国株式信用売建可能銘柄一覧取得APIの戻り値を返す */
        return cometForeignStockShortableStockService.listShortableStocks(request);
    }
    
    @Override
    public ConfirmForeignStockClosedMarginOrderResp confirmForeignStockClosedMarginOrder(String butenCode,
            String accountNumber, String tradePassword, String ticket, String channel,
            MarginOrderInputForConfirmForeignStockClosedMarginOrder marginOrder, List<ClosedPositionInput> positions)
            throws Exception {
        
        // API引数初期化
        ConfirmForeignStockClosedMarginOrderReq request = new ConfirmForeignStockClosedMarginOrderReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // Header:取引パスワード
        request.getHeader()
                .setTrade_password(StringUtil.isNullOrEmpty(tradePassword) ? StringUtil.EMPTY_STRING : tradePassword);
        // Header:チケット
        request.getHeader().setTicket(StringUtil.isNullOrEmpty(ticket) ? StringUtil.EMPTY_STRING : ticket);
        // Header:チャネル
        request.getHeader().setChannel(channel);
        // パラメータ:MarginOrderInput-信用注文情報
        request.getParameter().setMarginOrder(marginOrder);
        // パラメータ:決済相手建玉明細情報
        request.getParameter().setPositions(positions);
        /* 外国株式信用返済注文確認APIの戻り値を返す */
        return cometForeignStockMarginOrderService.confirmForeignStockClosedMarginOrder(request);
    }
    
    @Override
    public GetMarginAccountAutoTransferSettingResp getMarginAccountAutoTransferSetting(String butenCode,
            String accountNumber) throws Exception {
        
        /* API引数初期化 */
        GetMarginAccountAutoTransferSettingReq request = new GetMarginAccountAutoTransferSettingReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        
        return cometForeignStockMarginOrderService.getMarginAccountAutoTransferSetting(request);
    }
    
    @Override
    public CreateMarginAccountAutoTransferSettingResp createMarginAccountAutoTransferSetting(String butenCode,
            String accountNumber, String requestId, Boolean marginBuyingPowerShortfallCash,
            Boolean marginBuyingPowerShortfallSecurities, Boolean marginShortfallCash,
            Boolean marginShortfallSecurities, String depositType) throws Exception {
        
        /* API引数初期化 */
        CreateMarginAccountAutoTransferSettingReq request = new CreateMarginAccountAutoTransferSettingReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // Header:Request Id
        request.getHeader().setRequest_id(StringUtil.isNullOrEmpty(requestId) ? StringUtil.EMPTY_STRING : requestId);
        // パラメータ:建余力不足 自動振替設定(米ドル)
        request.getParameter().setMarginBuyingPowerShortfallCash(marginBuyingPowerShortfallCash);
        // パラメータ:建余力不足 自動振替設定(米国株式)
        request.getParameter().setMarginBuyingPowerShortfallSecurities(marginBuyingPowerShortfallSecurities);
        // パラメータ:保証金不足 自動振替設定(米ドル)
        request.getParameter().setMarginShortfallCash(marginShortfallCash);
        // パラメータ:保証金不足 自動振替設定(米国株式)
        request.getParameter().setMarginShortfallSecurities(marginShortfallSecurities);
        // パラメータ:現物買付時 株式自動振替先設定
        request.getParameter().setDepositType(depositType);
        
        return cometForeignStockMarginOrderService.createMarginAccountAutoTransferSetting(request);
    }
    
    @Override
    public ListForeignStockSecuritiesResp listForeignStockSecurities(String countryCode, String searchKeyword,
            String matchType, String marketCode, Page page, String marginCode) throws Exception {
        
        ListForeignStockSecuritiesReq listForeignStockSecuritiesReq = new ListForeignStockSecuritiesReq();
        listForeignStockSecuritiesReq.getParameter().setCountryCode(countryCode);
        listForeignStockSecuritiesReq.getParameter().setSearchKeyword(searchKeyword);
        // マッチ種別 入力しない場合、「1」とする。
        if (StringUtil.isNullOrEmpty(matchType)) {
            listForeignStockSecuritiesReq.getParameter().setMatchType("1");
        } else {
            listForeignStockSecuritiesReq.getParameter().setMatchType(matchType);
        }
        listForeignStockSecuritiesReq.getParameter().setMarketCode(marketCode);
        listForeignStockSecuritiesReq.getParameter().setPage(page);
        listForeignStockSecuritiesReq.getParameter().setMarginCode(marginCode);
        return cometForeignStockSecuritiesService.listForeignStockSecurities(listForeignStockSecuritiesReq);
    }

    @Override
    public GetForeignStockDeletedMarginOrderInitializationResp getForeignStockDeletedMarginOrderInitialization(
            String butenCode, String accountNumber, String orderSubNo) throws Exception {
        
        /* API引数初期化 */
        GetForeignStockDeletedMarginOrderInitializationReq request = new GetForeignStockDeletedMarginOrderInitializationReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // パラメータ: 注文Sub番号
        request.getParameter()
                .setOrderSubNo(StringUtil.isNullOrEmpty(orderSubNo) ? StringUtil.EMPTY_STRING : orderSubNo);
        
        /* 外国株式信用注文取消初期情報取得APIの戻り値を返す */
        return cometForeignStockMarginOrderService.getForeignStockDeletedMarginOrderInitialization(request);
    }
    
    @Override
    public DeleteForeignStockMarginOrderResp deleteForeignStockMarginOrder(String butenCode, String accountNumber,
            String tradePassword, String requestId, String orderSubNo) throws Exception {
        
        /* API引数初期化 */
        DeleteForeignStockMarginOrderReq request = new DeleteForeignStockMarginOrderReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // Header:取引パスワード
        request.getHeader()
                .setTrade_password(StringUtil.isNullOrEmpty(tradePassword) ? StringUtil.EMPTY_STRING : tradePassword);
        // Header:チケット
        request.getHeader().setRequest_id(StringUtil.isNullOrEmpty(requestId) ? StringUtil.EMPTY_STRING : requestId);
        // パラメータ: 注文Sub番号
        request.getParameter()
                .setOrderSubNo(StringUtil.isNullOrEmpty(orderSubNo) ? StringUtil.EMPTY_STRING : orderSubNo);
        
        /* 外国株式信用注文取消登録APIの戻り値を返す */
        return cometForeignStockMarginOrderService.deleteForeignStockMarginOrder(request);
    }
    
    @Override
    public GetOffsetBusinessDateResp getOffsetBusinessDate(String countryCode, String baseDate,
            Integer businessDateOffset) throws Exception {
        
        GetOffsetBusinessDateReq getOffsetBusinessDateReq = new GetOffsetBusinessDateReq();
        getOffsetBusinessDateReq.getParameter().setCountryCode(countryCode);
        getOffsetBusinessDateReq.getParameter().setBaseDate(baseDate);
        getOffsetBusinessDateReq.getParameter().setBusinessDateOffset(businessDateOffset);
        return cometForeignStockFrnTradeDateService.getOffsetBusinessDate(getOffsetBusinessDateReq);
    }
    
    @Override
    public ListForeignStockOrdersResp listForeignStockOrders(String butenCode, String accountNo, String countryCode,
            String foreignStockProductType, String securitiesCode, String accountKind, String orderDateFrom,
            String orderDateTo, String orderDateType, List<String> orderStatuses, Page page) throws Exception {
        
        ListForeignStockOrdersReq request = new ListForeignStockOrdersReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        request.getParameter().setCountryCode(countryCode);
        request.getParameter().setForeignStockProductType(foreignStockProductType);
        request.getParameter().setSecuritiesCode(securitiesCode);
        request.getParameter().setAccountKind(accountKind);
        request.getParameter().setOrderDateFrom(orderDateFrom);
        request.getParameter().setOrderDateTo(orderDateTo);
        request.getParameter().setOrderDateType(orderDateType);
        request.getParameter().setOrderStatuses(orderStatuses);
        request.getParameter().setPage(page);
        
        return cometForeignStockOrderService.listForeignStockOrders(request);
    }

    @Override
    public GetForeignStockCreatedOrderInitializationResp getForeignStockCreatedOrderInitialization(String butenCode,
            String accountNo, String countryCode, String securitiesCode, String buySellType) throws Exception {
        
        GetForeignStockCreatedOrderInitializationReq request = new GetForeignStockCreatedOrderInitializationReq();
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        request.getParameter().setCountryCode(countryCode);
        request.getParameter().setSecuritiesCode(securitiesCode);
        request.getParameter().setBuySellCode(buySellType);
        
        return cometForeignStockOrderService.getForeignStockCreatedOrderInitialization(request);
    }
    
    @Override
    public CreateForeignStockOrderResp createForeignStockOrder(String token, String tradePassword, String requestId,
            String channel, String countryCode, String marketCode, String securitiesCode, String buySellCode,
            String orderQuantity, String orderPriceKindCode, String orderPrice, String stopPrice, String noLimitPrice,
            String orderLimitCode, String orderTerm, String specificAccountCode, String settlementMethodCode)
            throws Exception {
        
        CreateForeignStockOrderReq request = new CreateForeignStockOrderReq();
        request.getHeader().setToken(token);
        request.getHeader()
                .setTrade_password(StringUtil.isNullOrEmpty(tradePassword) ? StringUtil.EMPTY_STRING : tradePassword);
        request.getHeader().setRequest_id(StringUtil.isNullOrEmpty(requestId) ? StringUtil.EMPTY_STRING : requestId);
        request.getHeader().setChannel(channel);
        request.getParameter().setCountryCode(countryCode);
        request.getParameter().setMarketCode(marketCode);
        request.getParameter().setSecuritiesCode(securitiesCode);
        request.getParameter().setBuySellCode(buySellCode);
        request.getParameter().setOrderQuantity(orderQuantity);
        request.getParameter().setOrderPriceKindCode(orderPriceKindCode);
        request.getParameter()
                .setOrderPrice(StringUtil.isNullOrEmpty(orderPrice) ? StringUtil.EMPTY_STRING : orderPrice);
        request.getParameter().setStopPrice(StringUtil.isNullOrEmpty(stopPrice) ? StringUtil.EMPTY_STRING : stopPrice);
        request.getParameter()
                .setNoLimitPrice(StringUtil.isNullOrEmpty(noLimitPrice) ? StringUtil.EMPTY_STRING : noLimitPrice);
        request.getParameter().setOrderLimitCode(orderLimitCode);
        request.getParameter().setOrderTerm(StringUtil.isNullOrEmpty(orderTerm) ? StringUtil.EMPTY_STRING : orderTerm);
        request.getParameter().setSpecificAccountCode(specificAccountCode);
        request.getParameter().setSettlementMethodCode(settlementMethodCode);
        
        return cometForeignStockOrderService.createForeignStockOrder(request);
    }
    
    @Override
    public ConfirmForeignStockCreatedOrderResp confirmForeignStockCreatedOrder(String butenCode, String accountNumber,
            String tradePassword, String ticket, String channel, OrderInput orderInput) throws Exception {
        
        // API引数初期化
        ConfirmForeignStockCreatedOrderReq request = new ConfirmForeignStockCreatedOrderReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // Header:取引パスワード
        request.getHeader()
                .setTradePassword(StringUtil.isNullOrEmpty(tradePassword) ? StringUtil.EMPTY_STRING : tradePassword);
        // Header:チケット
        request.getHeader().setTicket(StringUtil.isNullOrEmpty(ticket) ? StringUtil.EMPTY_STRING : ticket);
        // Header:チャネル
        request.getHeader().setChannel(channel);
        // パラメータ:国コード
        request.getParameter().setCountryCode(orderInput.getCountryCode());
        // パラメータ:市場コード
        request.getParameter().setMarketCode(orderInput.getMarketCode());
        // パラメータ:銘柄コード
        request.getParameter().setSecuritiesCode(orderInput.getSecuritiesCode());
        // パラメータ:売買区分
        request.getParameter().setBuySellCode(orderInput.getBuySellCode());
        // パラメータ:注文数量
        request.getParameter().setOrderQuantity(orderInput.getOrderQuantity());
        // パラメータ:価格条件
        request.getParameter().setOrderPriceKindCode(orderInput.getOrderPriceKindCode());
        // パラメータ:注文単価
        request.getParameter().setOrderPrice(orderInput.getOrderPrice());
        // パラメータ:発火条件価格
        request.getParameter().setStopPrice(orderInput.getStopPrice());
        // パラメータ:トレールストップ幅
        request.getParameter().setTrailingStopAmount(orderInput.getTrailingStopAmount());
        // パラメータ:成行基準価格
        request.getParameter().setNoLimitPrice(orderInput.getNoLimitPrice());
        // パラメータ:期間条件
        request.getParameter().setOrderLimitCode(orderInput.getOrderLimitCode());
        // パラメータ:期間
        request.getParameter().setOrderTerm(orderInput.getOrderTerm());
        // パラメータ:預り区分
        request.getParameter().setSpecificAccountCode(orderInput.getSpecificAccountCode());
        // パラメータ:決済方法
        request.getParameter().setSettlementMethodCode(orderInput.getSettlementMethodCode());
        // パラメータ:余力チェック不要
        request.getParameter().setRemainingPowerCheckDisabled(orderInput.getRemainingPowerCheckDisabled());
        // パラメータ:NISA枠チェック不要
        request.getParameter().setNisaRemainingPowerCheckDisabled(orderInput.getNisaRemainingPowerCheckDisabled());
        
        /* 外国株式現物注文確認APIの戻り値を返す */
        return cometForeignStockOrderService.confirmForeignStockCreatedOrder(request);
    }
    
    @Override
    public GetForeignStockRuTickSizeResp getForeignStockRuTickSize(String securitiesCode) throws Exception {
        
        // API引数初期化
        GetForeignStockRuTickSizeReq request = new GetForeignStockRuTickSizeReq();
        
        // パラメータ:securitiesCode-銘柄コード
        request.getParameter().setSecuritiesCode(securitiesCode);
        /* 外国株式ロシア株呼値情報取得APIの戻り値を返す */
        return cometForeignStockOrderService.getForeignStockRuTickSize(request);
    }
    
    @Override
    public GetForeignStockOrderDetailResp getForeignStockOrderDetail(String butenCode, String accountNo, String orderNo)
            throws Exception {
        
        // API引数初期化
        GetForeignStockOrderDetailReq getForeignStockOrderDetailReq = new GetForeignStockOrderDetailReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        getForeignStockOrderDetailReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        // パラメータ:注文番号
        getForeignStockOrderDetailReq.getParameter().setOrderNo(orderNo);
        
        /* 外国株式現物注文詳細取得APIの戻り値を返す */
        return cometForeignStockOrderService.getForeignStockOrderDetail(getForeignStockOrderDetailReq);
    }
    
    @Override
    public GetForeignStockMarginOrderDetailResp getForeignStockMarginOrderDetail(String butenCode, String accountNumber,
            String orderSubNo) throws Exception {
        
        // API引数初期化
        GetForeignStockMarginOrderDetailReq getForeignStockMarginOrderDetailReq = new GetForeignStockMarginOrderDetailReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        getForeignStockMarginOrderDetailReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        // パラメータ:注文Sub番号
        getForeignStockMarginOrderDetailReq.getParameter().setOrderSubNo(orderSubNo);

        /* 外国株式信用注文詳細取得APIの戻り値を返す */
        return cometForeignStockMarginOrderService
                .getForeignStockMarginOrderDetail(getForeignStockMarginOrderDetailReq);
    }
    
    @Override
    public GetForeignStockLendingSubscribedStatusResp getForeignStockLendingSubscribedStatus(String butenCode,
            String accountNumber) throws Exception {
        
        /* API引数初期化 */
        GetForeignStockLendingSubscribedStatusReq request = new GetForeignStockLendingSubscribedStatusReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        
        return cometForeignStockLendingService.getForeignStockLendingSubscribedStatus(request);
    }

    public GetForeignStockDeletedOrderInitializationResp getForeignStockDeletedOrderInitialization(String butenCode,
            String accountNo, String orderSubNo) throws Exception {
        
        /* API引数初期化 */
        GetForeignStockDeletedOrderInitializationReq request = new GetForeignStockDeletedOrderInitializationReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        // パラメータ: 注文Sub番号
        request.getParameter().setOrderSubNo(orderSubNo);
        
        /* 外国株式現物注文取消初期情報取得APIの戻り値を返す */
        return cometForeignStockOrderService.getForeignStockDeletedOrderInitialization(request);
    }
    
    @Override
    public DeleteForeignStockOrderResp deleteForeignStockOrder(String butenCode, String accountNo, String tradePassword,
            String requestId, String orderSubNo) throws Exception {
        
        /* API引数初期化 */
        DeleteForeignStockOrderReq request = new DeleteForeignStockOrderReq();
        // Header:{部店}3桁 + "-" + {口座}7桁
        request.getHeader().setToken(RequestUtil.getToken(butenCode, accountNo));
        // Header:取引パスワード
        request.getHeader()
                .setTradePassword(StringUtil.isNullOrEmpty(tradePassword) ? StringUtil.EMPTY_STRING : tradePassword);
        // Header:Request Id
        request.getHeader().setRequestId(StringUtil.isNullOrEmpty(requestId) ? StringUtil.EMPTY_STRING : requestId);
        // パラメータ: 注文Sub番号
        request.getParameter().setOrderSubNo(orderSubNo);
        
        /* 外国株式現物注文取消登録APIの戻り値を返す */
        return cometForeignStockOrderService.deleteForeignStockOrder(request);
    }
}

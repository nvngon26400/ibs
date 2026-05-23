package com.sbisec.helios.ap.athena.ifa;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.Page;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ClosedPositionInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInputForConfirmForeignStockClosedMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderInput;
import com.sbisec.helios.ap.athena.protocol.fstock.lending.GetForeignStockLendingSubscribedStatusResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetOffsetBusinessDateResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListForeignStockOrdersResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesResp;

/**
 * IFA Athena 外国株式 Service
 * 
 * @author shuchen.xin
 * @date 02/21/2022
 */
public interface ForeignStockService extends com.sbibits.earth.service.Service {
    
    /**
     * 外国株式銘柄マスタ取得
     * 
     * @param countryCode「必須」 国コード<br>
     *                        <li>米株：US</li>
     *                        <li>日本株：JP</li>
     * @param securitiesCode  「必須」 銘柄コード
     * @return 外国株式銘柄マスタ取得 GetForeignStockSecuritiesResp
     * @throws Exception Exception 異常
     */
    public GetForeignStockSecuritiesResp getForeignStockSecurities(String countryCode, String securitiesCode)
            throws Exception;
    
    /**
     * 外国株式本日注意銘柄取得
     * 
     * @param countryCode    「必須」 国コード<br>
     *                       <li>米株：US</li>
     *                       <li>日本株：JP</li>
     * @param securitiesCode 「必須」 銘柄コード
     * @return 外国株式本日注意銘柄取得 GetForeignStockAttentionSecuritiesResp
     * @throws Exception 異常
     */
    public GetForeignStockAttentionSecuritiesResp getForeignStockAttentionSecurities(String countryCode,
            String securitiesCode) throws Exception;
    
    /**
     * 外国株式信用新規建注文登録
     * 
     * @param butenCode        「必須」部店
     * @param accountNumber    「必須」口座
     * @param tradePassword    「必須」取引パスワー
     * @param requestId        「必須」Request Id
     * @param channel          「必須」チャネル
     * @param marginOrderInput 「必須」MarginOrderInput信用注文情報
     * @return 信用注文情報 MarginOrder
     * @throws Exception 異常
     */
    public CreateForeignStockMarginOrderResp createForeignStockMarginOrder(String butenCode, String accountNumber,
            String tradePassword, String requestId, String channel, MarginOrderInput marginOrderInput) throws Exception;
    
    /**
     * 外国株式信用注文初期情報取得
     * 
     * @param butenCode      「必須」部店
     * @param accountNumber  「必須」口座
     * @param countryCode    「必須」国コード<br>
     *                       <li>米株：US</li>
     *                       <li>日本株：JP</li>
     * @param securitiesCode 「非必須」銘柄コード
     * @param stockTradeType 「必須」株取引区分<br>
     *                       <li>STOCK：現物</li>
     *                       <li>MARGIN_OPEN：信用新規</li>
     *                       <li>MARGIN_CLOSE：信用返済</li>
     * @param buySellCode    「必須」売買区分<br>
     *                       <li>SELL：売付</li>
     *                       <li>BUY：買付</li>
     * @param specificAccountCode 「非必須」預り区分
     * @return
     * @throws Exception
     */
    public GetForeignStockCreatedMarginOrderInitializationResp getForeignStockCreatedMarginOrderInitialization(
            String butenCode, String accountNumber, String countryCode, String securitiesCode, String stockTradeType,
            String buySellCode, String specificAccountCode) throws Exception;
    
    /**
     * 外国株式信用注文初期情報取得
     * 建区分の区分値変換無し
     * 
     * @param butenCode      「必須」部店
     * @param accountNumber  「必須」口座
     * @param countryCode    「必須」国コード<br>
     *                       <li>米株：US</li>
     *                       <li>日本株：JP</li>
     * @param securitiesCode 「非必須」銘柄コード
     * @param stockTradeType 「必須」株取引区分<br>
     *                       <li>STOCK：現物</li>
     *                       <li>MARGIN_OPEN：信用新規</li>
     *                       <li>MARGIN_CLOSE：信用返済</li>
     * @param buySellCode    「必須」売買区分<br>
     *                       <li>SELL：売付</li>
     *                       <li>BUY：買付</li>
     * @param specificAccountCode 「非必須」預り区分
     * @return
     * @throws Exception
     */
    public GetForeignStockCreatedMarginOrderInitializationResp getForeignStockCreatedMarginOrderInitializationNoConvertBuySellCode(
            String butenCode, String accountNumber, String countryCode, String securitiesCode, String stockTradeType,
            String buySellCode, String specificAccountCode) throws Exception;
    
    /**
     * 外国株式信用返済注文登録API.
     * 
     * @param butenCode     「必須」部店コード(3桁)
     * @param accountNumber 「必須」口座番号(最大7桁)
     * @param tradePassword 「必須」取引パスワー
     * @param requestId     「必須」Request Id
     * @param channel       「必須」チャネル
     * @param marginOrder   「必須」信用注文情報
     * @param positions     決済相手建玉明細情報
     * @return 外国株式信用返済注文登録API CloseForeignStockMarginOrderResp
     * @throws Exception 異常
     */
    public CloseForeignStockMarginOrderResp closeForeignStockMarginOrder(String butenCode, String accountNumber,
            String tradePassword, String requestId, String channel,
            MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder marginOrder,
            List<ClosedPositionInput> positions) throws Exception;
    
    /**
     * 外国株式信用新規建注文確認
     * 
     * @param butenCode        「必須」部店
     * @param accountNumber    「必須」口座
     * @param tradePassword    「必須」取引パスワー
     * @param ticket           「必須」チケット
     * @param channel          「必須」チャネル
     * @param marginOrderInput 「必須」MarginOrderInput信用注文情報
     * @return 信用注文情報 MarginOrder
     * @throws Exception 異常
     */
    public ConfirmForeignStockCreatedMarginOrderResp confirmForeignStockCreatedMarginOrder(String butenCode,
            String accountNumber, String tradePassword, String ticket, String channel,
            MarginOrderInput marginOrderInput) throws Exception;
    
    /**
     * 外国株式信用売建可能銘柄一覧取得
     * 
     * @param countryCode   「必須」国コード
     * @param searchKeyword 「非必須」検索用キーワード（最大 384 バイト、大体 128 つUTF8文字に相当する）
     * @param tradeStatus   「非必須」銘柄信用売建可能<br>
     *                      <li>TRADABLE</li>
     *                      <li>UNTRADABLE</li>
     * @param page          「非必須」 sourceページPage
     * @return 外国株式信用売建可能銘柄一覧取得 ListShortableStocksResp
     * @throws Exception 異常
     */
    public ListShortableStocksResp listShortableStocks(String countryCode, String searchKeyword,
            String shortableStockTradeStatus, Page page) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式信用返済注文確認API.
     * 
     * @param butenCode     「必須」部店コード(3桁)
     * @param accountNumber 「必須」口座番号(最大7桁)
     * @param tradePassword 「必須」取引パスワード
     * @param ticket        「必須」チケット
     * @param channel       「必須」チャネル
     * @param marginOrder   「必須」信用注文情報
     * @param positions     決済相手建玉明細情報
     * @return 外国株式信用返済注文確認
     * @throws Exception 異常
     */
    public ConfirmForeignStockClosedMarginOrderResp confirmForeignStockClosedMarginOrder(String butenCode,
            String accountNumber, String tradePassword, String ticket, String channel,
            MarginOrderInputForConfirmForeignStockClosedMarginOrder marginOrder, List<ClosedPositionInput> positions)
            throws Exception;
    
    /**
     * 信用口座自動振替設定取得API
     * 
     * @param butenCode     「必須」部店
     * @param accountNumber 「必須」口座
     * @return 信用口座自動振替設定情報
     * @throws Exception 異常
     */
    public GetMarginAccountAutoTransferSettingResp getMarginAccountAutoTransferSetting(String butenCode,
            String accountNumber) throws Exception;
    
    /**
     * 口座情報サービス - 信用口座自動振替設定登録API
     * 
     * @param butenCode                            「必須」部店
     * @param accountNumber                        「必須」口座
     * @param requestId                            「必須」Request Id
     * @param marginBuyingPowerShortfallCash       「必須」建余力不足 自動振替設定(米ドル)
     * @param marginBuyingPowerShortfallSecurities 「必須」建余力不足 自動振替設定(米国株式)
     * @param marginShortfallCash                  「必須」保証金不足 自動振替設定(米ドル)
     * @param marginShortfallSecurities            「必須」保証金不足 自動振替設定(米国株式)
     * @param depositType                          「必須」現物買付時 株式自動振替先設定 (許可値:
     *                                             "PROTECTION_KEEPING",
     *                                             "COLLATERAL_SECURITIES")
     * @return 信用口座自動振替設定情報
     * @throws Exception 異常
     */
    public CreateMarginAccountAutoTransferSettingResp createMarginAccountAutoTransferSetting(String butenCode,
            String accountNumber, String requestId, Boolean marginBuyingPowerShortfallCash,
            Boolean marginBuyingPowerShortfallSecurities, Boolean marginShortfallCash,
            Boolean marginShortfallSecurities, String depositType) throws Exception;
    
    /**
     * 外国株式信用注文取消初期情報取得
     * 
     * @param butenCode     「必須」部店
     * @param accountNumber 「必須」口座
     * @param orderSubNo    「必須」注文Sub番号
     * @return 信用注文情報 MarginOrder
     * @throws Exception 異常
     */
    public GetForeignStockDeletedMarginOrderInitializationResp getForeignStockDeletedMarginOrderInitialization(
            String butenCode, String accountNumber, String orderSubNo) throws Exception;
    
    /**
     * 外国株式信用注文取消登録
     * 
     * @param butenCode        「必須」部店
     * @param accountNumber    「必須」口座
     * @param tradePassword    「必須」取引パスワー
     * @param requestId        「必須」Request Id
     * @param orderSubNo       「必須」orderSubNo
     * @param marginOrderInput 「必須」MarginOrderInput信用注文情報
     * @return 信用注文情報 MarginOrder
     * @throws Exception 異常
     */
    public DeleteForeignStockMarginOrderResp deleteForeignStockMarginOrder(String butenCode, String accountNumber,
            String tradePassword, String requestId, String orderSubNo) throws Exception;
    
    /**
     * 外国株式現地営業日取得API（日数指定版）
     * 
     * @param countryCode 「必須」国コード<br>
     *                    <li>米株：US</li>
     *                    <li>日本株：JP</li>
     * @param baseDate 「必須」基準日。"yyyy-MM-dd"形式日付
     * @param businessDateOffset 「非必須」基準日から何営業日数で計算。例：当日営業日=0/前営業日=-1/翌々営業日=2
     * @return 外国株式現地営業日取得（日数指定版）情報 GetOffsetBusinessDateResp
     * @throws Exception 異常
     */
    public GetOffsetBusinessDateResp getOffsetBusinessDate(String countryCode, String baseDate,
            Integer businessDateOffset) throws Exception;
    
    /**
     * 外国株式注文一覧取得
     * 
     * @param butenCode               「必須」部店コード(3桁)
     * @param accountNo               「必須」口座番号(最大7桁)
     * @param countryCode             「必須」国コード<br>
     *                                <li>米株：US</li>
     *                                <li>日本株：JP</li>
     * @param foreignStockProductType 「非必須」商品種別<br>
     *                                <li>外国株式（現物）：ORDER</li>
     *                                <li>外国株式（信用）：MARGIN_ORDER</li>
     * @param securitiesCode          「非必須」 銘柄コード
     * @param accountKind             「非必須」 口座分類<br>
     *                                <li>総合：GENERAL</li>
     *                                <li>ジュニアNISA：JR_NISA</li>
     * @param orderDateFrom           「必須」注文日(From) "yyyy-MM-dd"形式
     * @param orderDateTo             「必須」注文日(To) "yyyy-MM-dd"形式
     * @param orderDateType           「必須」 注文日基準種別<br>
     *                                <li>現地約定日基準：FRN_TRADE_DATE</li>
     *                                <li>国内注文日基準：ORDER_INPUT_DATE</li>
     * @param orderStatuses           「非必須」 注文状況<br>
     *                                <li>注文中：ORDERING</li>
     *                                <li>訂正中：CORRECTING</li>
     *                                <li>取消中：CANCELING</li>
     *                                <li>失効済：EXPIRED</li>
     *                                <li>取消済：CANCELLED</li>
     *                                <li>待機中：WAITING</li>
     *                                <li>完了：COMPLETED</li>
     * @param page                    「非必須」 sourceページPage
     * @return 外国株式注文一覧取得 ListForeignStockOrdersResp
     * @throws Exception 異常
     */
    public ListForeignStockOrdersResp listForeignStockOrders(String butenCode, String accountNo, String countryCode,
            String foreignStockProductType, String securitiesCode, String accountKind, String orderDateFrom,
            String orderDateTo, String orderDateType, List<String> orderStatuses, Page page) throws Exception;
    
    /**
     * 外国株式現物注文初期情報取得.
     *
     * @param butenCode               「必須」部店コード(3桁)
     * @param accountNo               「必須」口座番号(最大7桁)
     * @param countryCode             「必須」国コード<br>
     * @param securitiesCode          「非必須」 銘柄コード
     * @param buySellType             「必須」取引種別
     * @return 外国株式現物注文初期情報
     * @throws Exception 異常
     */
    public GetForeignStockCreatedOrderInitializationResp getForeignStockCreatedOrderInitialization(String butenCode,
            String accountNo, String countryCode, String securitiesCode, String buySellType) throws Exception;
    
    /**
     * 外国株式現物注文登録.
     *
     * @param token トークン
     * @param tradePassword　取引パスワード
     * @param requestId　Request Id
     * @param channel　チャネル
     * @param countryCode　国コード
     * @param marketCode　市場コード
     * @param securitiesCode　銘柄コード
     * @param buySellCode　売買区分
     * @param orderQuantity　注文数量
     * @param orderPriceKindCode　価格条件
     * @param orderPrice　注文単価
     * @param stopPrice　発火条件価格
     * @param noLimitPrice　成行基準価格
     * @param orderLimitCode　期間条件
     * @param orderTerm　期間
     * @param specificAccountCode　預り区分
     * @param settlementMethodCode　決済方法
     * @return　外国株式現物注文登録結果情報
     * @throws Exception 異常
     */
    public CreateForeignStockOrderResp createForeignStockOrder(String token, String tradePassword, String requestId,
            String channel, String countryCode, String marketCode, String securitiesCode, String buySellCode,
            String orderQuantity, String orderPriceKindCode, String orderPrice, String stopPrice, String noLimitPrice,
            String orderLimitCode, String orderTerm, String specificAccountCode, String settlementMethodCode)
            throws Exception;
    
    /**
     * 外国株式現物注文確認
     *
     * @param butenCode        「必須」部店コード(3桁)
     * @param accountNumber    「必須」口座番号(最大7桁)
     * @param tradePassword    「必須」取引パスワード
     * @param ticket           「非必須」チケット
     * @param channel          「必須」チャネル
     * @param orderInput 「必須」OrderInput 注文情報
     * @return 注文情報 Order
     * @throws Exception 異常
     */
    public ConfirmForeignStockCreatedOrderResp confirmForeignStockCreatedOrder(String butenCode, String accountNumber,
            String tradePassword, String ticket, String channel, OrderInput orderInput) throws Exception;
    
    /**
     * 外国株式ロシア株呼値情報取得.
     *
     * @param securitiesCode    「必須」 銘柄コード
     * @return 外国株式現物注文初期情報
     * @throws Exception 異常
     */
    public GetForeignStockRuTickSizeResp getForeignStockRuTickSize(String securitiesCode) throws Exception;
    
    /**
     * 外国株式銘柄検索
     * 
     * @param countryCode   「非必須」国コード<br>
     *                      <li>米株：US</li>
     *                      <li>日本株：JP</li>
     * @param searchKeyword 「非必須」検索用キーワード（最大 384 バイト、大体 128 つUTF8文字に相当する）
     * @param matchType     「非必須」マッチ種別<br>
     *                      <li>先頭からマッチ：START_WITH</li>
     *                      <li>含まれる：CONTAINS</li>
     *                      <li>末尾からマッチ：END_WITH</li>
     * @param marketCode    「非必須」市場コード<br>
     *                      <li>NYSE：US_NYSE</li>
     *                      <li>NYSEArca：US_ARCA</li>
     *                      <li>NYSE American：US_AMEX</li>
     *                      <li>ナスダック：US_NASDAQ</li>
     * @param page          「非必須」 sourceページPage
     * @param marginCode    「非必須」信用取引コード<br>
     *                      <li></li>
     * @return 外国株式銘柄検索情報取得 ListForeignStockSecuritiesResp
     * @throws Exception 異常
     */
    public ListForeignStockSecuritiesResp listForeignStockSecurities(String countryCode, String searchKeyword,
            String matchType, String marketCode, Page page, String marginCode) throws Exception;
    
    /**
     * 外国株式現物注文詳細取得
     *
     * @param butenCode 「必須」部店コード(3桁)
     * @param accountNo 「必須」口座番号(最大7桁)
     * @param orderNo   「必須」注文番号
     * @return 外国株式現物注文詳細取得 GetForeignStockOrderDetailResp
     * @throws Exception 異常
     */
    public GetForeignStockOrderDetailResp getForeignStockOrderDetail(String butenCode, String accountNo, String orderNo)
            throws Exception;
    
    /**
     * 外国株式信用注文詳細取得
     *
     * @param butenCode     「必須」部店コード(3桁)
     * @param accountNumber 「必須」口座番号(最大7桁)
     * @param orderNo       「必須」注文番号
     * @return 外国株式信用注文詳細取得API GetForeignStockMarginOrderDetailResp
     * @throws Exception 異常
     */
    public GetForeignStockMarginOrderDetailResp getForeignStockMarginOrderDetail(String butenCode, String accountNumber,
            String orderNo) throws Exception;
    
    /**
     * 外国株式貸株サービス加入判定.
     *
     * @param butenCode               「必須」部店コード(3桁)
     * @param accountNo               「必須」口座番号(最大7桁)
     * @return 外国株式貸株サービス加入状況
     * @throws Exception 異常
     */
    public GetForeignStockLendingSubscribedStatusResp getForeignStockLendingSubscribedStatus(String butenCode,
            String accountNo) throws Exception;

    /**
     * 外国株式現物注文取消初期情報取得.
     *
     * @param butenCode      「必須」部店コード(3桁)
     * @param accountNo      「必須」口座番号(最大7桁)
     * @param orderSubNo     「必須」注文サブ番号
     * @return 外国株式現物注文取消初期情報
     * @throws Exception 異常
     */
    public GetForeignStockDeletedOrderInitializationResp getForeignStockDeletedOrderInitialization(String butenCode,
            String accountNo, String orderSubNo) throws Exception;
    
    /**
     * 外国株式現物注文取消登録.
     *
     * @param butenCode       「必須」部店コード(3桁)
     * @param accountNo       「必須」口座番号(最大7桁)
     * @param tradePassword   「必須」取引パスワード
     * @param requestId       「必須」Request Id
     * @param orderSubNo      「必須」注文サブ番号
     * @return 外国株式現物注文取消初期情報
     * @throws Exception 異常
     */
    public DeleteForeignStockOrderResp deleteForeignStockOrder(String butenCode, String accountNo, String tradePassword,
            String requestId, String orderSubNo) throws Exception;
}

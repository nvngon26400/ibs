package com.sbisec.helios.ap.nri.ifa;

import java.util.List;

import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderSortInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderSortOutData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderSortInData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderSortOutData;
import jp.co.sbisec.pcenter.dto.yanap.MgRcptDeliverCancelOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.MgRcptDeliverCancelOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderOutData;

/**
 * NRI API 呼び出しサービスクラス
 * 2024/05/07 新規作成
 *
 * @author SCSK 笹倉 秀行
 *
 */
public interface NriApiService {
    
    /**
     * 信用一括返済注文確認．
     * NRI_EstimateMarginLumpOrder
     *
     * @param input 信用一括返済注文確認情報
     * @return 信用一括返済注文確認結果情報
     * @throws Exception システムエラー
     */
    public EstimateMarginLumpOrderOutData estimateMarginLumpOrder(EstimateMarginLumpOrderInData input) throws Exception;
    
    /**
     * 返済順序指定信用一括返済注文確認．
     * NRI_EstimateMarginLumpOrderSort
     *
     * @param input 返済順序指定信用一括返済注文確認情報
     * @return 返済順序指定信用一括返済注文確認結果情報
     * @throws Exception 異常
     */
    public EstimateMarginLumpOrderSortOutData estimateMarginLumpOrderSort(EstimateMarginLumpOrderSortInData input)
            throws Exception;
    
    /**
     * 建玉残高明細
     * NRI_QueryMarginContract1
     * 検索結果件数が50件を超える場合に、検索結果件数に達するまで、検索番号指定FROM/検索番号指定TOを50件ずつオフセットして繰り返します.
     *
     * @param buttenCode 部店コード
     * @param accountNumber 口座番号
     * @param brandCode 銘柄コード
     * @param openTradeKbn 新規売買区分
     * @param openMarket 新規市場
     * @param paymentLimit 弁済期限
     * @param requestType リクエストタイプ
     * @return 建玉残高明細レスポンス
     * @throws Exception 異常
     */
    public List<QueryMarginContract1OutData> queryMarginContract1List(String buttenCode, String accountNumber,
            String brandCode, String openTradeKbn, String openMarket, String paymentLimit, String requestType)
            throws Exception;
    
    /**
     * 信用一括返済注文
     * NRI_MarginLumpPlaceOrder
     *
     * @param input 信用一括返済注文情報
     * @return 信用一括返済注文結果情報
     * @throws Exception 異常
    */
    public MarginLumpPlaceOrderOutData marginLumpPlaceOrder(MarginLumpPlaceOrderInData input) throws Exception;
    
    /**
     * 返済順序指定信用一括返済注文
     * NRI_MarginLumpPlaceOrderSort
     *
     * @param input 返済順序指定信用一括返済注文情報
     * @return 返済順序指定信用一括返済注文結果情報
     * @throws Exception 異常
    */
    public MarginLumpPlaceOrderSortOutData marginLumpPlaceOrderSort(MarginLumpPlaceOrderSortInData input)
            throws Exception;
    
    /**
     * 株式統合注文一覧照会(ポイント)
     * NRI_QueryStockUniteOrderPoint
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 異常
     */
    public QueryStockUniteOrderPointOutData queryStockUniteOrderPoint(QueryStockUniteOrderPointInData input)
            throws Exception;
    
     /**
     * 株式統合注文一覧照会(ポイント) 注文状況一覧用
     * NRI_QueryStockUniteOrderPoint
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 異常
     */
    public QueryStockUniteOrderPointOutData queryStockUniteOrderPointForOrderStatusList(QueryStockUniteOrderPointInData input, Boolean isOrderStatusList)
            throws Exception;
    

    /**
     * 国内株式・信用注文取消
     * NRI_StockCancelOrder
     *
     * @param input 国内株式・信用注文情報
     * @return 国内株式・信用注文取消結果情報
     * @throws Exception 異常
     */
    public StockCancelOrderOutData stockCancelOrder(StockCancelOrderInData input) throws Exception;
    
    /**
     * 信用現引現渡取消
     * NRI_MgRcptDeliverCancelOrder
     *
     * @param input 信用現引現渡取消リクエスト
     * @return 信用現引現渡取消
     * @throws Exception 異常
     */
    public MgRcptDeliverCancelOrderOutData mgRcptDeliverCancelOrder(MgRcptDeliverCancelOrderInData input)
            throws Exception;
}

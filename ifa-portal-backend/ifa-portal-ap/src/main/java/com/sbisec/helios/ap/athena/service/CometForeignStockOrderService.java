package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListForeignStockOrdersReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListForeignStockOrdersResp;

/**
 * Comet API 外国株式取引サービス Service.
 * <p/>
 * 一覧
 * <p/>
 * 外国株式取引サービス - 外国株式注文一覧取得API<br>
 *
 * @author shuchen.xin
 * @date 01/12/2022
 * 
 */
public interface CometForeignStockOrderService {
    
    /**
     * 外国株式取引サービス - 外国株式注文一覧取得API.
     *
     * @param request Httpリクエスト
     * @return 外国株式注文一覧取得
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ListForeignStockOrdersReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ListForeignStockOrdersResp
     */
    public ListForeignStockOrdersResp listForeignStockOrders(ListForeignStockOrdersReq request) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式現物注文初期情報取得API.
     *
     * @param request Httpリクエスト
     * @return 外国株式現物注文初期情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockCreatedOrderInitializationResp
     */
    public GetForeignStockCreatedOrderInitializationResp getForeignStockCreatedOrderInitialization(
            GetForeignStockCreatedOrderInitializationReq request) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式ロシア株呼値情報取得API.
     *
     * @param request Httpリクエスト
     * @return 外国株式ロシア株呼値情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockRuTickSizeResp
     */
    public GetForeignStockRuTickSizeResp getForeignStockRuTickSize(GetForeignStockRuTickSizeReq request)
            throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式現物注文登録API.
     *
     * @param request Httpリクエスト
     * @return 外国株式現物注文登録情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.CreateForeignStockOrderResp
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.CreateForeignStockOrderReq
     */
    public CreateForeignStockOrderResp createForeignStockOrder(CreateForeignStockOrderReq request) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式現物注文確認API.
     *
     * @param request Httpリクエスト
     * @return 外国株式現物注文確認情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedOrderResp
     */
    public ConfirmForeignStockCreatedOrderResp confirmForeignStockCreatedOrder(
            ConfirmForeignStockCreatedOrderReq request) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式現物注文詳細取得API.
     *
     * @param request Httpリクエスト
     * @return 注文詳細情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailResp
     * 
     */
    public GetForeignStockOrderDetailResp getForeignStockOrderDetail(
            GetForeignStockOrderDetailReq request) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式現物注文取消初期情報取得API.
     *
     * @param request Httpリクエスト
     * @return 外国株式現物注文取消初期情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedOrderInitializationResp
     */
    public GetForeignStockDeletedOrderInitializationResp getForeignStockDeletedOrderInitialization(
            GetForeignStockDeletedOrderInitializationReq request) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式現物注文取消登録API.
     *
     * @param request Httpリクエスト
     * @return 外国株式現物注文取消登録レスポンス
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockOrderResp
     */
    public DeleteForeignStockOrderResp deleteForeignStockOrder(DeleteForeignStockOrderReq request) throws Exception;
}

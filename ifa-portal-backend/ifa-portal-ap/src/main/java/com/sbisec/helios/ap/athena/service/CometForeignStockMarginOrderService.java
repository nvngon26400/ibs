package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingResp;

/**
 * 
 * Comet API 外国株式現地約定（信用） Service.
 * <p>
 * 一覧
 * <p>
 * 外国株式取引サービス - 外国株式信用注文取消登録API<br>
 * 外国株式取引サービス - 外国株式信用注文取消初期情報取得API<br>
 * 
 * @author shuchen.xin
 * @date 02/11/2022
 * 
 */
public interface CometForeignStockMarginOrderService {
    
    /**
     * 外国株式取引サービス - 外国株式信用注文取消登録API.
     * 
     * @param request Httpリクエスト
     * @return 信用注文情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderResp
     */
    public DeleteForeignStockMarginOrderResp deleteForeignStockMarginOrder(DeleteForeignStockMarginOrderReq request)
            throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式信用注文取消初期情報取得API.
     * 
     * @param confirmForeignStockClosedMarginOrderReq リクエスト
     * @return 外国株式信用注文取消初期情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationResp
     */
    public GetForeignStockDeletedMarginOrderInitializationResp getForeignStockDeletedMarginOrderInitialization(
            GetForeignStockDeletedMarginOrderInitializationReq getForeignStockDeletedMarginOrderInitializationReq)
            throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式信用新規建注文登録API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式信用新規建注文登録情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderResp
     */
    public CreateForeignStockMarginOrderResp createForeignStockMarginOrder(CreateForeignStockMarginOrderReq request)
            throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式信用返済注文確認API.
     * 
     * @param confirmForeignStockClosedMarginOrderReq リクエスト
     * @return 外国株式信用返済注文確認情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderResp
     */
    public ConfirmForeignStockClosedMarginOrderResp confirmForeignStockClosedMarginOrder(
            ConfirmForeignStockClosedMarginOrderReq confirmForeignStockClosedMarginOrderReq) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式信用注文初期情報取得API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式信用注文初期情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp
     */
    public GetForeignStockCreatedMarginOrderInitializationResp getForeignStockCreatedMarginOrderInitialization(
            GetForeignStockCreatedMarginOrderInitializationReq request) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式信用注文初期情報取得API.
     * 建区分の区分値変換無し.
     * 
     * @param request Httpリクエスト
     * @return 外国株式信用注文初期情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp
     */
    public GetForeignStockCreatedMarginOrderInitializationResp getForeignStockCreatedMarginOrderInitializationNoConvertBuySellCode(
            GetForeignStockCreatedMarginOrderInitializationReq request) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式信用返済注文登録API.
     *
     * @param closeForeignStockMarginOrderReq リクエスト
     * @return 外国株式信用返済注文登録情報
     * @throws Exception 異常
     *
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderResp
     */
    public CloseForeignStockMarginOrderResp closeForeignStockMarginOrder(
            CloseForeignStockMarginOrderReq closeForeignStockMarginOrderReq) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式信用新規建注文確認API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式信用新規建注文確認情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.ConfirmForeignStockCreatedMarginOrderResp
     */
    public ConfirmForeignStockCreatedMarginOrderResp confirmForeignStockCreatedMarginOrder(
            ConfirmForeignStockCreatedMarginOrderReq request) throws Exception;
    
    /**
     * 口座情報サービス - 信用口座自動振替設定取得API.
     * 
     * @param getMarginAccountAutoTransferSettingReq リクエスト
     * @return 信用口座自動振替設定情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingResp
     */
    public GetMarginAccountAutoTransferSettingResp getMarginAccountAutoTransferSetting(
            GetMarginAccountAutoTransferSettingReq getMarginAccountAutoTransferSettingReq) throws Exception;
    
    /**
     * 口座情報サービス - 信用口座自動振替設定登録API.
     * 
     * @param CreateMarginAccountAutoTransferSettingReq リクエスト
     * @return 信用口座自動振替設定情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingResp
     */
    public CreateMarginAccountAutoTransferSettingResp createMarginAccountAutoTransferSetting(
            CreateMarginAccountAutoTransferSettingReq createMarginAccountAutoTransferSettingReq) throws Exception;
    
    /**
     * 外国株式取引サービス - 外国株式信用注文詳細取得API.
     *
     * @param getForeignStockMarginOrderDetailReq リクエスト
     * @return 外国株式信用注文詳細情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailResp
     */
    public GetForeignStockMarginOrderDetailResp getForeignStockMarginOrderDetail(
            GetForeignStockMarginOrderDetailReq getForeignStockMarginOrderDetailReq) throws Exception;
}

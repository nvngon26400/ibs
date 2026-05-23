package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.exchange.ifa.order.CreateIfaExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.ifa.order.CreateIfaExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CancelExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CancelExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeCancelledOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeCancelledOrderInitializationRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersRes;

public interface CometExchangeOrderService extends com.sbibits.earth.service.Service {
    
    /**
     * 通貨別サービスステータスチェックAPI
     * 
     */
    public CheckExchangeCurrencyServiceStatusRes checkExchangeCurrencyServiceStatus(
            CheckExchangeCurrencyServiceStatusReq req) throws Exception;
    
    public CheckExchangeCurrencyServiceStatusRes checkExchangeCurrencyServiceStatusOrderCancel(
            CheckExchangeCurrencyServiceStatusReq req) throws Exception;
    
    
    /**
     * 為替注文確認API（リテール向け）
     * @param req
     * @return
     * @throws Exception
     */
    public ConfirmExchangeCreatedOrderRes confirmExchangeCreatedOrder(ConfirmExchangeCreatedOrderReq req)
            throws Exception;
    
    /**
     * 為替注文確認API（IFA向け）
     * 
     */
    

    public ConfirmIfaExchangeCreatedOrderRes confirmIfaExchangeCreatedOrder(ConfirmIfaExchangeCreatedOrderReq req)
            throws Exception;
    /**
     * 為替注文登録API（IFA向け）
     * @return
     * @throws Exception
     */
    public CreateIfaExchangeOrderRes createIfaExchangeOrder(CreateIfaExchangeOrderReq req) throws Exception;
    
    /**
     * 営業日情報取得API（リテール向け）
     * @param req
     * @return
     * @throws Exception
     */
    public CreateExchangeOrderRes createExchangeOrder(CreateExchangeOrderReq req) throws Exception;
    
    /**
     * 為替注文一覧取得API
     * @param req
     * @return
     * @throws Exception
     */
    public ListExchangeOrdersRes listExchangeOrders(ListExchangeOrdersReq req) throws Exception;
    
    /**
     * 為替注文取消初期情報取得API（リテール向け）
     * @param req
     * @return
     * @throws Exception
     */
    public GetExchangeCancelledOrderInitializationRes getExchangeCancelledOrderInitialization(
            GetExchangeCancelledOrderInitializationReq req) throws Exception;
    
    
    /**
     * 為替取引サービス - 為替注文取消登録API（リテール向け）
     */
    
    public CancelExchangeOrderRes cancelExchangeOrder(
            CancelExchangeOrderReq req) throws Exception;
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA006DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputA006DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputX001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderInputX001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * SUB0202_0212-08_1_現引現渡注文入力
 *
 * @author 池亀緑
 */
public interface IfaReceiptDeliveryOrderInputService extends Service {
    
    /**
     * アクションID：X001
     * アクション名：初期化
     * Dto リクエスト：IfaReceiptDeliveryOrderInputX001DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderInputX001DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderInputX001RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderInputX001ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderInputX001DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderInputX001DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReceiptDeliveryOrderInputX001DtoResponse> initializeX001(IfaReceiptDeliveryOrderInputX001DtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A003
     * アクション名：初期化
     * Dto リクエスト：IfaReceiptDeliveryOrderInputA003DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderInputA003DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderInputA003RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderInputA003ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderInputA003DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderInputA003DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReceiptDeliveryOrderInputA003DtoResponse> updateA003(IfaReceiptDeliveryOrderInputA003DtoRequest dtoReq)
            throws Exception;    

    /**
     * アクションID：A006
     * アクション名：初期化
     * Dto リクエスト：IfaReceiptDeliveryOrderInputA006DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderInputA006DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderInputA006RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderInputA006ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderInputA006DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderInputA006DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReceiptDeliveryOrderInputA006DtoResponse> orderConfirmA006(IfaReceiptDeliveryOrderInputA006DtoRequest dtoReq)
            throws Exception;
      
}

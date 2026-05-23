package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaReceiptDeliveryOrderConfirmA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-08_2
 * 画面名：現引現渡注文確認
 * 2024/04/01 新規作成
 *
 * @author SCSK 笹倉秀行
 */
public interface IfaReceiptDeliveryOrderConfirmService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：注文発注
     * Dto リクエスト：IfaReceiptDeliveryOrderConfirmA001DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderConfirmA001DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderConfirmA001RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderConfirmA001ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderConfirmA001DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderConfirmA001DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReceiptDeliveryOrderConfirmA001DtoResponse> orderPlacementA001a(
            IfaReceiptDeliveryOrderConfirmA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A001
     * アクション名：注文発注
     * Dto リクエスト：IfaReceiptDeliveryOrderConfirmA001DtoRequest
     * Dto レスポンス：IfaReceiptDeliveryOrderConfirmA001DtoResponse
     * model リクエスト：IfaReceiptDeliveryOrderConfirmA001RequestModel
     * model レスポンス：IfaReceiptDeliveryOrderConfirmA001ResponseModel
     *
     * @param dtoReq {@code IfaReceiptDeliveryOrderConfirmA001DtoRequest }
     * @return {@code DataList <IfaReceiptDeliveryOrderConfirmA001DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaReceiptDeliveryOrderConfirmA001DtoResponse> orderPlacementA001b(
            IfaReceiptDeliveryOrderConfirmA001DtoRequest dtoReq) throws Exception;
    
}

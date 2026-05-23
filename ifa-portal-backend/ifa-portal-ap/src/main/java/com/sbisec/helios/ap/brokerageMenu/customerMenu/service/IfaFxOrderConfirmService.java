package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxOrderConfirmA001bDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0502-02_2
 * 画面名：為替注文確認
 * @author <author-name>
 *
 * 2023/09/25 新規作成
 */
public interface IfaFxOrderConfirmService extends Service {
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaFxOrderConfirmA001DtoRequest
     * Dto レスポンス：IfaFxOrderConfirmA001DtoResponse
     * model リクエスト：IfaFxOrderConfirmA001RequestModel
     * model レスポンス：IfaFxOrderConfirmA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderConfirmA001aDtoResponse> orderPlacementA001a(IfaFxOrderConfirmA001aDtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaFxOrderConfirmA001bDtoRequest
     * Dto レスポンス：IfaFxOrderConfirmA001bDtoResponse
     * model リクエスト：IfaFxOrderConfirmA001bRequestModel
     * model レスポンス：IfaFxOrderConfirmA001bResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxOrderConfirmA001bDtoResponse> orderPlacementA001b(IfaFxOrderConfirmA001bDtoRequest dtoReq)
            throws Exception;
    
}

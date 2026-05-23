package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0208-02
 * 画面名：株式詳細情報
 * アクションID：A001
 * アクション名：初期化
 * @author <author-name>
 *
 * 2023/07/31 新規作成
 */
public interface IfaStockDetailInfoService extends Service {

    /**
     * 
     * Dto リクエスト：IfaStockDetailInfoA001DtoRequest
     * Dto レスポンス：IfaStockDetailInfoA001DtoResponse
     * model リクエスト：IfaStockDetailInfoA001RequestModel
     * model レスポンス：IfaStockDetailInfoA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaStockDetailInfoA001DtoResponse> initializeA001(IfaStockDetailInfoA001DtoRequest dtoReq)
            throws Exception;

}

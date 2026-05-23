package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterDetailDisplayA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterDetailDisplayA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0104-03
 * 画面名：外国株式店頭詳細表示

 * @author 大崎
　　　2024/03/19 新規作成
 */

public interface IfaForeignStockCounterDetailDisplayService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignStockCounterDetailDisplayA001DtoRequest
     * Dto レスポンス：IfaForeignStockCounterDetailDisplayA001DtoResponse
     * model リクエスト：IfaForeignStockCounterDetailDisplayA001RequestModel
     * model レスポンス：IfaForeignStockCounterDetailDisplayA001ResponseModel

     * @param dtoReq リクエスト
     * @return IfaForeignStockCounterDetailDisplayA001DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaForeignStockCounterDetailDisplayA001DtoResponse> initializeA001(IfaForeignStockCounterDetailDisplayA001DtoRequest dtoReq)
            throws Exception;

}

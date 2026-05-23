package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockEntrustOrderTradeInfoA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockEntrustOrderTradeInfoA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0104-02
 * 画面名：外国株式委託注文約定情報
 *
 * @author SCSK 矢口
 *
 *         2024/04/02 新規作成
 */
public interface IfaForeignStockEntrustOrderTradeInfoService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignStockEntrustOrderTradeInfoA001DtoRequest
     * Dto レスポンス：IfaForeignStockEntrustOrderTradeInfoA001DtoResponse
     * model リクエスト：IfaForeignStockEntrustOrderTradeInfoA001RequestModel
     * model レスポンス：IfaForeignStockEntrustOrderTradeInfoA001ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaForeignStockEntrustOrderTradeInfoA001DtoResponse> initializeA001(IfaForeignStockEntrustOrderTradeInfoA001DtoRequest dtoReq)
            throws Exception;

}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderCorrectConfirmA001bDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0208-03_2
 * 画面名：国内株式注文訂正確認
 *
 * @author SCSK 矢口
 *
 *      2024/04/16 新規作成
 */
public interface IfaDomesticStockOrderCorrectConfirmService extends Service {
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderCorrectConfirmA001aDtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectConfirmA001aDtoResponse
     * model リクエスト：IfaDomesticStockOrderCorrectConfirmA001aRequestModel
     * model レスポンス：IfaDomesticStockOrderCorrectConfirmA001aResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスDTO
     * @exception Exception 例外
     */
    public DataList<IfaDomesticStockOrderCorrectConfirmA001aDtoResponse> orderPlacementA001a(
            IfaDomesticStockOrderCorrectConfirmA001aDtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderCorrectConfirmA001bDtoRequest
     * Dto レスポンス：IfaDomesticStockOrderCorrectConfirmA001bDtoResponse
     * model リクエスト：IfaDomesticStockOrderCorrectConfirmA001bRequestModel
     * model レスポンス：IfaDomesticStockOrderCorrectConfirmA001bResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスDTO
     * @exception Exception 例外
     */
    public DataList<IfaDomesticStockOrderCorrectConfirmA001bDtoResponse> orderPlacementA001b(
            IfaDomesticStockOrderCorrectConfirmA001bDtoRequest dtoReq) throws Exception;
    
}

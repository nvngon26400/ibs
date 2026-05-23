package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderCorrectConfirmA001bDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-02_2
 * 画面名：信用新規注文訂正確認
 *
 * @author SCSK
   2024/04/16 新規作成
 */
public interface IfaMarginNewOrderCorrectConfirmService extends Service {

    /**
     * アクションID：A001a
     * アクション名：訂正発注
     * Dto リクエスト：IfaMarginNewOrderCorrectConfirmA001aDtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectConfirmA001aDtoResponse
     * model リクエスト：IfaMarginNewOrderCorrectConfirmA001RequestModel
     * model レスポンス：IfaMarginNewOrderCorrectConfirmA001ResponseModel
     *
     * @return A001aレスポンス
     * @exception Exception error
     */
    public DataList<IfaMarginNewOrderCorrectConfirmA001aDtoResponse> correctionOrderA001a(
            IfaMarginNewOrderCorrectConfirmA001aDtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A001b
     * アクション名：訂正発注
     * Dto リクエスト：IfaMarginNewOrderCorrectConfirmA001bDtoRequest
     * Dto レスポンス：IfaMarginNewOrderCorrectConfirmA001bDtoResponse
     * model リクエスト：IfaMarginNewOrderCorrectConfirmA001RequestModel
     * model レスポンス：IfaMarginNewOrderCorrectConfirmA001ResponseModel
     *
     * @return A001bレスポンス
     * @exception Exception error
     */
    public DataList<?> correctionOrderA001b(
            IfaMarginNewOrderCorrectConfirmA001bDtoRequest dtoReq) throws Exception;
}

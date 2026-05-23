package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002bDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0501-02_1
 * 画面名：為替取引注文取消確認
 * @author <author-name>
 *
 * 2023/11/20 新規作成
 */
public interface IfaFxTradeOrderCancelConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaFxTradeOrderCancelConfirmA001DtoRequest
     * Dto レスポンス：IfaFxTradeOrderCancelConfirmA001DtoResponse
     * model リクエスト：IfaFxTradeOrderCancelConfirmA001RequestModel
     * model レスポンス：IfaFxTradeOrderCancelConfirmA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxTradeOrderCancelConfirmA001DtoResponse> initializeA001(IfaFxTradeOrderCancelConfirmA001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002a
     * アクション名：注文取消
     * Dto リクエスト：IfaFxTradeOrderCancelConfirmA002aDtoRequest
     * Dto レスポンス：IfaFxTradeOrderCancelConfirmA002aDtoResponse
    * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaFxTradeOrderCancelConfirmA002aDtoResponse> orderCancellationA002a(IfaFxTradeOrderCancelConfirmA002aDtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002b
     * アクション名：注文取消
     * Dto リクエスト：IfaFxTradeOrderCancelConfirmA002bDtoRequest
     * Dto レスポンス：IfaFxTradeOrderCancelConfirmA002bDtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaFxTradeOrderCancelConfirmA002bDtoResponse> orderCancellationA002b(IfaFxTradeOrderCancelConfirmA002bDtoRequest dtoReq)
            throws Exception;

}

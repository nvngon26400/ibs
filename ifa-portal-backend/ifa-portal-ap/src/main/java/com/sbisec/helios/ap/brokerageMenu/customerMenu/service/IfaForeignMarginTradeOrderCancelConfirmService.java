package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0303-03_1
 * 画面名：米株信用取引注文取消確認
 * @author <author-name>
 *
 * 2023/09/16 新規作成
 */
public interface IfaForeignMarginTradeOrderCancelConfirmService extends Service {

	/**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse
     * model リクエスト：IfaForeignMarginTradeOrderCancelConfirmA001RequestModel
     * model レスポンス：IfaForeignMarginTradeOrderCancelConfirmA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeOrderCancelConfirmA001DtoResponse> initializeA001(IfaForeignMarginTradeOrderCancelConfirmA001DtoRequest dtoReq)
            throws Exception;
	
    /**
     * アクションID：A010
     * アクション名：注文取消
     * Dto リクエスト：IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest
     * Dto レスポンス：IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse
     * model リクエスト：IfaForeignMarginTradeOrderCancelConfirmA010aRequestModel
     * model レスポンス：IfaForeignMarginTradeOrderCancelConfirmA010aResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse> orderCancellationA010a(
            IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest dtoReq)
            throws Exception;

    /**
     * アクションID：A010
     * アクション名：注文取消
     * Dto リクエスト：IfaForeignMarginTradeOrderCancelConfirmA010aDtoRequest
     * Dto レスポンス：IfaForeignMarginTradeOrderCancelConfirmA010aDtoResponse
     * model リクエスト：IfaForeignMarginTradeOrderCancelConfirmA010aRequestModel
     * model レスポンス：IfaForeignMarginTradeOrderCancelConfirmA010aResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeOrderCancelConfirmA010bDtoResponse> orderCancellationA010b(
            IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest dtoReq)
            throws Exception;
}

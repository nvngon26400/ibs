package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0301-03_1
 * 画面名：外国現物取引注文取消確認
 * 2024/03/29 新規作成
 *
 * @author 宇田川達弥
 */
public interface IfaForeignSpotTradeOrderCancelConfirmService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignSpotTradeOrderCancelConfirmA001RequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto
     * model リクエスト：IfaForeignSpotTradeOrderCancelConfirmA001RequestModel
     * model レスポンス：IfaForeignSpotTradeOrderCancelConfirmA001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderCancelConfirmA001ResponseDto> initializeA001(
            IfaForeignSpotTradeOrderCancelConfirmA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：注文取消
     * Dto リクエスト：IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto
     * model リクエスト：IfaForeignSpotTradeOrderCancelConfirmA010aRequestModel
     * model レスポンス：IfaForeignSpotTradeOrderCancelConfirmA010aResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderCancelConfirmA010aResponseDto> orderCancellationA010a(
            IfaForeignSpotTradeOrderCancelConfirmA010aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：注文取消
     * Dto リクエスト：IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto
     * Dto レスポンス：IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto
     * model リクエスト：IfaForeignSpotTradeOrderCancelConfirmA010bRequestModel
     * model レスポンス：IfaForeignSpotTradeOrderCancelConfirmA010bResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignSpotTradeOrderCancelConfirmA010bResponseDto> orderCancellationA010b(
            IfaForeignSpotTradeOrderCancelConfirmA010bRequestDto dtoReq) throws Exception;
}

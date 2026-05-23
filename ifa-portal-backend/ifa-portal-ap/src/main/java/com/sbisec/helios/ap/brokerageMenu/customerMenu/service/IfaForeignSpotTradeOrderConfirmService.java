package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0301-01_2
 * 画面名：外国現物取引注文確認
 *
 * @author 福岡　利基
 *
 */
public interface IfaForeignSpotTradeOrderConfirmService extends Service {
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaForeignSpotTradeOrderConfirmA004DtoRequest
     * Dto レスポンス：IfaForeignSpotTradeOrderConfirmA004DtoResponse
     * model リクエスト：IfaForeignSpotTradeOrderConfirmA004RequestModel
     * model レスポンス：IfaForeignSpotTradeOrderConfirmA004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return DataList＜IfaForeignSpotTradeOrderConfirmA004ResponseDto＞ 実行結果
     * @exception Exception 例外処理
     */
    public DataList<IfaForeignSpotTradeOrderConfirmA004ResponseDto> updateA004(
            IfaForeignSpotTradeOrderConfirmA004RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：注文発注
     * Dto リクエスト：IfaForeignSpotTradeOrderConfirmA010aDtoRequest
     * Dto レスポンス：IfaForeignSpotTradeOrderConfirmA010aDtoResponse
     * model リクエスト：IfaForeignSpotTradeOrderConfirmA010aRequestModel
     * model レスポンス：IfaForeignSpotTradeOrderConfirmA010aResponseModel
     *
     * @param dtoReq リクエスト
     * @return DataList＜IfaForeignSpotTradeOrderConfirmA010aResponseDto＞ 実行結果
     * @exception Exception 例外処理
     */
    public DataList<IfaForeignSpotTradeOrderConfirmA010aResponseDto> orderPlacementA010a(
            IfaForeignSpotTradeOrderConfirmA010aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：注文発注
     * Dto リクエスト：IfaForeignSpotTradeOrderConfirmA010bDtoRequest
     * Dto レスポンス：IfaForeignSpotTradeOrderConfirmA010bDtoResponse
     * model リクエスト：IfaForeignSpotTradeOrderConfirmA010bRequestModel
     * model レスポンス：IfaForeignSpotTradeOrderConfirmA010bResponseModel
     *
     * @param dtoReq リクエスト
     * @return DataList＜IfaForeignSpotTradeOrderConfirmA010bResponseDto＞ 実行結果
     * @exception Exception 例外処理
     */
    public DataList<IfaForeignSpotTradeOrderConfirmA010bResponseDto> orderPlacementA010b(
            IfaForeignSpotTradeOrderConfirmA010bRequestDto dtoReq) throws Exception;
}

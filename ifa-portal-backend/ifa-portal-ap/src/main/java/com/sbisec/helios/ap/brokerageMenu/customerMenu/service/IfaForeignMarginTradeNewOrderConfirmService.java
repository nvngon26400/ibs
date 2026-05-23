package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0303-01_2
 * 画面名：米株信用取引新規注文確認
 *
 * @author SCSK 金志
 *
 */
public interface IfaForeignMarginTradeNewOrderConfirmService extends Service {
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaForeignMarginTradeNewOrderConfirmA004DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeNewOrderConfirmA004DtoResponse
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 更新の際、例外が発生した場合
     */
    public DataList<IfaForeignMarginTradeNewOrderConfirmA004ResponseDto> updateA004(
            IfaForeignMarginTradeNewOrderConfirmA004RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：注文発注a
     * Dto リクエスト：IfaForeignMarginTradeNewOrderConfirmA010aRequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderConfirmA010aResponseDto
     * model リクエスト：IfaForeignMarginTradeNewOrderConfirmSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 注文発注aの際、例外が発生した場合
     */
    public DataList<IfaForeignMarginTradeNewOrderConfirmA010aResponseDto> orderPlacementA010a(
            IfaForeignMarginTradeNewOrderConfirmA010aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：注文発注b
     * Dto リクエスト：IfaForeignMarginTradeNewOrderConfirmA010bRequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderConfirmA010bResponseDto
     * model リクエスト：IfaForeignMarginTradeNewOrderConfirmSql002RequestModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 注文発注bの際、例外が発生した場合
     */
    public DataList<IfaForeignMarginTradeNewOrderConfirmA010bResponseDto> orderPlacementA010b(
            IfaForeignMarginTradeNewOrderConfirmA010bRequestDto dtoReq) throws Exception;
}

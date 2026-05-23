package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0303-04_2
 * 画面名：米株信用取引返済注文確認
 * 2023/09/07 新規作成
 *
 * @author SCSK
 */
public interface IfaForeignMarginTradeRepayOrderConfirmService extends Service {
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderConfirmA004RequestDto
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderConfirmA004DtoResponse
     * model リクエスト：IfaForeignMarginTradeRepayOrderConfirmA004RequestModel
     * model レスポンス：IfaForeignMarginTradeRepayOrderConfirmA004ResponseModel
     *
     * @param dtoReq A004のリクエスト
     * @return DataList A004のレスポンス
     * @exception Exception エクセプショ
     */
    public DataList<IfaForeignMarginTradeRepayOrderConfirmA004ResponseDto> updateA004(
            IfaForeignMarginTradeRepayOrderConfirmA004RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010a
     * アクション名：注文発注
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderConfirmA010aDtoResponse
     * model リクエスト：IfaForeignMarginTradeRepayOrderConfirmA010aRequestModel
     * model レスポンス：IfaForeignMarginTradeRepayOrderConfirmA010aResponseModel
     *
     * @param dtoReq A010aのリクエスト
     * @return DataList A010aのレスポンス
     * @exception Exception エクセプショ
     */
    public DataList<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto> orderPlacementA010a(
            IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A010b
     * アクション名：注文発注
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderConfirmA010bDtoResponse
     * model リクエスト：IfaForeignMarginTradeRepayOrderConfirmA010bRequestModel
     * model レスポンス：IfaForeignMarginTradeRepayOrderConfirmA010bResponseModel
     *
     * @param dtoReq A010bのリクエスト
     * @return DataList A010bのレスポンス
     * @exception Exception エクセプショ
     */
    public DataList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto> orderPlacementA010b(
            IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto dtoReq) throws Exception;
    
}

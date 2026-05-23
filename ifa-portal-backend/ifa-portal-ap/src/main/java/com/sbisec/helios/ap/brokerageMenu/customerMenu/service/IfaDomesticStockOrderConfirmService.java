package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0208-01_3
 * 画面名：国内株式注文確認
 * @author <author-name>
 *
 * 2023/11/28 新規作成
 */
public interface IfaDomesticStockOrderConfirmService extends Service {

    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderConfirmA001aDtoRequest
     * Dto レスポンス：IfaDomesticStockOrderConfirmA001aDtoResponse
     * model リクエスト：IfaDomesticStockOrderConfirmA001aRequestModel
     * model レスポンス：IfaDomesticStockOrderConfirmA001aResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticStockOrderConfirmA001aResponseDto> orderPlacementA001a(IfaDomesticStockOrderConfirmA001aRequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticStockOrderConfirmA001bDtoRequest
     * Dto レスポンス：IfaDomesticStockOrderConfirmA001bDtoResponse
     * model リクエスト：IfaDomesticStockOrderConfirmA001bRequestModel
     * model レスポンス：IfaDomesticStockOrderConfirmA001bResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticStockOrderConfirmA001bResponseDto> orderPlacementA001b(IfaDomesticStockOrderConfirmA001bRequestDto dtoReq)
            throws Exception;

}

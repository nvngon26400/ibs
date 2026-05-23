package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-01_2
 * 画面名：信用新規注文確認
 * @author <author-name>
 *
 * 2023/10/13 新規作成
 */
public interface IfaMarginNewOrderConfirmService extends Service {
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginNewOrderConfirmA001aRequestDto
     * Dto レスポンス：IfaMarginNewOrderConfirmA001ResponseDto
     * model リクエスト：
     * model レスポンス：
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderConfirmA001aResponseDto> orderPlacementA001a(
            IfaMarginNewOrderConfirmA001aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaMarginNewOrderConfirmA001bRequestDto
     * Dto レスポンス：IfaMarginNewOrderConfirmA001ResponseDto
     * model リクエスト：
     * model レスポンス：
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderConfirmA001bResponseDto> orderPlacementA001b(
            IfaMarginNewOrderConfirmA001bRequestDto dtoReq) throws Exception;
    
}

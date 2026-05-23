package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA012ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0303-04_1
 * 画面名：米株信用取引返済注文入力
 * @author <author-name>
 *
 */
public interface IfaForeignMarginTradeRepayOrderInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderInputA001DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderInputA001DtoResponse
     * model リクエスト：IfaForeignMarginTradeRepayOrderInputA001RequestModel
     * model レスポンス：IfaForeignMarginTradeRepayOrderInputA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeRepayOrderInputA001ResponseDto> initializeA001(
            IfaForeignMarginTradeRepayOrderInputA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderInputA005DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderInputA005DtoResponse
     * model リクエスト：IfaForeignMarginTradeRepayOrderInputA005RequestModel
     * model レスポンス：IfaForeignMarginTradeRepayOrderInputA005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeRepayOrderInputA005ResponseDto> updateA005(
            IfaForeignMarginTradeRepayOrderInputA005RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A012
     * アクション名：注文確認
     * Dto リクエスト：IfaForeignMarginTradeRepayOrderInputA012DtoRequest
     * Dto レスポンス：IfaForeignMarginTradeRepayOrderInputA012DtoResponse
     * model リクエスト：IfaForeignMarginTradeRepayOrderInputA012RequestModel
     * model レスポンス：IfaForeignMarginTradeRepayOrderInputA012ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignMarginTradeRepayOrderInputA012ResponseDto> orderConfirmA012(
            IfaForeignMarginTradeRepayOrderInputA012RequestDto dtoReq) throws Exception;
}

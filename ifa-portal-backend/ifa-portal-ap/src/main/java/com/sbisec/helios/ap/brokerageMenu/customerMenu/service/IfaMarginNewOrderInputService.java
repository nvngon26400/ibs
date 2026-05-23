package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA005DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA005DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA016DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginNewOrderInputA016DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0212-01_1
 * 画面名：信用新規注文入力
 * @author <author-name>
 *
 * 2023/08/17 新規作成
 */
public interface IfaMarginNewOrderInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化 
     * Dto リクエスト：IfaMarginNewOrderInputA001DtoRequest
     * Dto レスポンス：IfaMarginNewOrderInputA001DtoResponse
     * model リクエスト：IfaMarginNewOrderInputA001RequestModel
     * model レスポンス：IfaMarginNewOrderInputA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderInputA001DtoResponse> initializeA001(IfaMarginNewOrderInputA001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：銘柄選択、市場選択 
     * Dto リクエスト：IfaMarginNewOrderInputA002DtoRequest
     * Dto レスポンス：IfaMarginNewOrderInputA002DtoResponse
     * model リクエスト：IfaMarginNewOrderInputA002RequestModel
     * model レスポンス：IfaMarginNewOrderInputA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderInputA002DtoResponse> brandSelectionMarketSelectionA002(
            IfaMarginNewOrderInputA002DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaMarginNewOrderInputA005DtoRequest
     * Dto レスポンス：IfaMarginNewOrderInputA005DtoResponse
     * model リクエスト：IfaMarginNewOrderInputA005RequestModel
     * model レスポンス：IfaMarginNewOrderInputA005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderInputA005DtoResponse> updateA005(IfaMarginNewOrderInputA005DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A016
     * アクション名：注文確認
     * Dto リクエスト：IfaMarginNewOrderInputA016DtoRequest
     * Dto レスポンス：IfaMarginNewOrderInputA016DtoResponse
     * model リクエスト：IfaMarginNewOrderInputA016RequestModel
     * model レスポンス：IfaMarginNewOrderInputA016ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMarginNewOrderInputA016DtoResponse> orderConfirmA016(
            IfaMarginNewOrderInputA016DtoRequest dtoReq) throws Exception;
    
}

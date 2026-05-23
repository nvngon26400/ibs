package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderLookupA003DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0501-01
 * 画面名：為替取引注文照会
 * 2023/09/12 新規作成
 *
 * @author scsk
 *
 */
public interface IfaFxTradeOrderLookupService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto レスポンス：IfaFxTradeOrderLookupA001DtoResponse
     *
     * @return A001レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaFxTradeOrderLookupA001DtoResponse> initializeA001(IfaFxTradeOrderLookupA001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：表示
     * Dto リクエスト：IfaFxTradeOrderLookupA003DtoRequest
     * Dto レスポンス：IfaFxTradeOrderLookupA003DtoResponse
     *
     * @param dtoReq A003リクエスト
     * @return A003レスポンス
     * @exception Exception 例外
     */
    public DataList<IfaFxTradeOrderLookupA003DtoResponse> displayA003(IfaFxTradeOrderLookupA003DtoRequest dtoReq)
            throws Exception;
    
}

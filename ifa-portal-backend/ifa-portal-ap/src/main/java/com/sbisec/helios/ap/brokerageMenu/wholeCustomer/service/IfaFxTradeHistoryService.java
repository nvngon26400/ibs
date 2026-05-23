package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA004aDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0202-01
 * 画面名：為替取引履歴
 *
 * @author SCSK川崎
 2024/05/09 新規作成
 */
public interface IfaFxTradeHistoryService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaFxTradeHistoryA001DtoRequest
     * Dto レスポンス：IfaFxTradeHistoryA001DtoResponse
     * model リクエスト：IfaFxTradeHistoryA001RequestModel
     * model レスポンス：IfaFxTradeHistoryA001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception exception システムエラー
     */
    public DataList<IfaFxTradeHistoryA001DtoResponse> initializeA001(IfaFxTradeHistoryA001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaFxTradeHistoryA002DtoRequest
     * Dto レスポンス：IfaFxTradeHistoryA002DtoResponse
     * model リクエスト：IfaFxTradeHistoryA002RequestModel
     * model レスポンス：IfaFxTradeHistoryA002ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception exception システムエラー
     */
    public DataList<IfaFxTradeHistoryA002DtoResponse> displayA002(IfaFxTradeHistoryA002DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力（CSV作成）
     * Dto リクエスト：IfaFxTradeHistoryA004aDtoRequest
     * Dto レスポンス：IfaFxTradeHistoryA004aDtoResponse
     * model リクエスト：IfaFxTradeHistoryA004RequestModel
     * model レスポンス：IfaFxTradeHistoryA004ResponseModel
     *
     * @param dtoReq リクエストDto
     * @param fwSessionId フレームワークセッションID
     * @return レスポンスDto
     * @exception exception システムエラー
     */
    public DataList<IfaFxTradeHistoryA004aDtoResponse> csvOutputA004a(IfaFxTradeHistoryA004aDtoRequest dtoReq, String fwSessionId)
            throws Exception;
    
}

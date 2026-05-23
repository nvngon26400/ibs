package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0201-01
 * 画面名：取引履歴
 *
 * @author SCSK
 2024/06/13 新規作成
 */
public interface IfaTradeHistoryService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaTradeHistoryA001RequestDto
     * Dto レスポンス：IfaTradeHistoryA001ResponseDto
     * model リクエスト：IfaTradeHistoryA001RequestModel
     * model レスポンス：IfaTradeHistoryA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeHistoryA001ResponseDto> initializeA001(IfaTradeHistoryA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaTradeHistoryA002RequestDto
     * Dto レスポンス：IfaTradeHistoryA002ResponseDto
     * model リクエスト：IfaTradeHistoryA002RequestModel
     * model レスポンス：IfaTradeHistoryA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeHistoryA002ResponseDto> displayA002(IfaTradeHistoryA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaTradeHistoryA005RequestDto
     * Dto レスポンス：IfaTradeHistoryA005ResponseDto
     * model リクエスト：IfaTradeHistoryA005RequestModel
     * model レスポンス：IfaTradeHistoryA005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeHistoryA005ResponseDto> csvOutputA005(IfaTradeHistoryA005RequestDto dtoReq,
            String fwSessionId) throws Exception;

}

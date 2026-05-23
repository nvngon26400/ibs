package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA004aResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
public interface IfaCustomerTradeHistoryService extends Service {


    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto レスポンス：IfaCustomerTradeHistoryA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCustomerTradeHistoryA001ResponseDto> initializeA001(
                IfaCustomerTradeHistoryA001RequestDto dtoReq
    ) throws Exception;

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCustomerTradeHistoryA002RequestDto
     * Dto レスポンス：IfaCustomerTradeHistoryA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCustomerTradeHistoryA002ResponseDto> displayA002(
            IfaCustomerTradeHistoryA002RequestDto dtoReq
    ) throws Exception;

    /**
     * アクションID：A004a
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerTradeHistoryA004aRequestDto
     * Dto レスポンス：IfaCustomerTradeHistoryA004aResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return CSV出力に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCustomerTradeHistoryA004aResponseDto> csvOutputA004a(
            IfaCustomerTradeHistoryA004aRequestDto dtoReq,
            String fwSessionId
    ) throws Exception;

}

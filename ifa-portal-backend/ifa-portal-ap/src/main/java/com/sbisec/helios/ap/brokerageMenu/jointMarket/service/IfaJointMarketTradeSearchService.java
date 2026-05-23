package com.sbisec.helios.ap.brokerageMenu.jointMarket.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

public interface IfaJointMarketTradeSearchService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointMarketTradeSearchA001RequestDto
     * Dto レスポンス：IfaJointMarketTradeSearchA001ResponseDto
     * model リクエスト：IfaJointMarketTradeSearchaA001RequestModel
     * model レスポンス：IfaJointMarketTradeSearchA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTradeSearchA001ResponseDto> initializeA001(
        IfaJointMarketTradeSearchA001RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaJointMarketTradeSearchA002RequestDto
     * Dto レスポンス：IfaJointMarketTradeSearchA002ResponseDto
     * model リクエスト：IfaJointMarketTradeSearchaA002RequestModel
     * model レスポンス：IfaJointMarketTradeSearchA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTradeSearchA002ResponseDto> displayA002(
        IfaJointMarketTradeSearchA002RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaJointMarketTradeSearchA004RequestDto
     * Dto レスポンス：IfaJointMarketTradeSearchA004ResponseDto
     * model リクエスト：IfaJointMarketTradeSearchA004RequestModel
     * model レスポンス：IfaJointMarketTradeSearchA004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTradeSearchA004ResponseDto> csvOutputA004(
        IfaJointMarketTradeSearchA004RequestDto dtoReq, String fwSessionId) throws Exception;

}

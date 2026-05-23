package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql003ResponseModel;

/**
 * 画面ID：SUB020302_0202-01
 * 画面名：為替取引履歴
 *
 * @author SCSK川崎
 2024/05/08 新規作成
 */
@Mapper
public interface IfaFxTradeHistoryMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：為替取引履歴一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeHistorySql001RequestModel
     * レスポンスクラス：IfaFxTradeHistorySql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaFxTradeHistorySql001ResponseModel> selectIfaFxTradeHistorySql001(
            @Param("req") IfaFxTradeHistorySql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：通貨リストコード取得
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeHistorySql002RequestModel
     * レスポンスクラス：IfaFxTradeHistorySql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaFxTradeHistorySql002ResponseModel> selectIfaFxTradeHistorySql002(
            @Param("req") IfaFxTradeHistorySql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：為替取引履歴コメント取得
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeHistorySql003RequestModel
     * レスポンスクラス：IfaFxTradeHistorySql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaFxTradeHistorySql003ResponseModel> selectIfaFxTradeHistorySql003(
            @Param("req") IfaFxTradeHistorySql003RequestModel req) throws Exception;
    
}

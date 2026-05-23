package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql003ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 画面ID：SUB020302_0201-01
 * 画面名：取引履歴
 *
 * @author SCSK
 2024/06/13 新規作成
 */
@Mapper
public interface IfaTradeHistoryMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：取引履歴情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaTradeHistorySql001RequestModel
     * レスポンスクラス：IfaTradeHistorySql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaTradeHistorySql001ResponseModel> selectIfaTradeHistorySql001(
            @Param("req") IfaTradeHistorySql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：取引履歴画面コメント取得
     * SQLタイプ：select
     * リクエストクラス：IfaTradeHistorySql002RequestModel
     * レスポンスクラス：IfaTradeHistorySql002ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaTradeHistorySql002ResponseModel> selectIfaTradeHistorySql002() throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：現株ポイント参照可能仲介業者チェック
     * SQLタイプ：select
     * リクエストクラス：IfaTradeHistorySql003RequestModel
     * レスポンスクラス：IfaTradeHistorySql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaTradeHistorySql003ResponseModel> selectIfaTradeHistorySql003(
            @Param("req") IfaTradeHistorySql003RequestModel req) throws Exception;
    
    
    
    
}

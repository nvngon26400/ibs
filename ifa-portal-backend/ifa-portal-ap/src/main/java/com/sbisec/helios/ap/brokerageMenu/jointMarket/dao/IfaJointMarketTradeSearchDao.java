package com.sbisec.helios.ap.brokerageMenu.jointMarket.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTradeSearchSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTradeSearchSql001ResponseModel;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

public interface IfaJointMarketTradeSearchDao {

    /**
     * SQLID：Sql001
     * SQL名：共同店舗取引検索情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointMarketTradeSearchSql001RequestModel
     * レスポンスクラス：IfaJointMarketTradeSearchSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTradeSearchSql001ResponseModel> selectIfaJointMarketTradeSearchSql001(
        IfaJointMarketTradeSearchSql001RequestModel req) throws Exception;

}

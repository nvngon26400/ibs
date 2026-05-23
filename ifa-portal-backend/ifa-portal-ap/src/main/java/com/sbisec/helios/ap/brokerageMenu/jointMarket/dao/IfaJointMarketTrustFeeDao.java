package com.sbisec.helios.ap.brokerageMenu.jointMarket.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTrustFeeSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTrustFeeSql001ResponseModel;

/**
 * 画面ID：SUB0510_02
 * 画面名：共同店舗 信託報酬
 *
 * @author SBI大連 董
 2024/12/12 新規作成
 *
 */
public interface IfaJointMarketTrustFeeDao {
    
    /**
     * SQLID：Sql001
     * SQL名：共同店舗　信託報酬一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointMarketTrustFeeSql001ToSql006RequestModel
     * レスポンスクラス：IfaJointMarketTrustFeeSql001ToSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTrustFeeSql001ResponseModel> selectIfaJointMarketTrustFeeSql001(
            IfaJointMarketTrustFeeSql001RequestModel req) throws Exception;
    

}

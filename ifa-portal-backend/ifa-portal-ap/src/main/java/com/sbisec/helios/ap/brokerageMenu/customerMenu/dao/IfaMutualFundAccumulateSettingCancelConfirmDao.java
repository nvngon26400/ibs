package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel;

/**
 * 画面ID：SUB0202_0403-01
 * 画面名：投信積立設定解除確認
 *
 * @author WJL
 *     2025/04/13 新規作成
 *
 */
public interface IfaMutualFundAccumulateSettingCancelConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：設定解除受付データの格納
     * SQLタイプ：insert
     * リクエストクラス：IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel
     * レスポンス：int
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 設定解除受付データの格納で例外が発生した場合
     */
    public int insertIfaMutualFundAccumulateSettingCancelConfirmSql001(
            IfaMutualFundAccumulateSettingCancelConfirmSql001RequestModel req) throws Exception;
    
}

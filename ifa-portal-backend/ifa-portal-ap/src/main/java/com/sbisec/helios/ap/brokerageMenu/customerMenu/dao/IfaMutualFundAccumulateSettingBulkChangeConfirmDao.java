package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel;

/**
 * 画面ID：SUB0202_0403-05_2
 * 画面名：投信積立設定一括変更確認
 *
 * @author nicksen.li
 * 2025/07/24 新規作成
 *
 */
public interface IfaMutualFundAccumulateSettingBulkChangeConfirmDao {

    /**
     * SQLID：Sql001
     * SQL名：積立設定一括変更登録
     * SQLタイプ：insert
     * 
     * リクエストクラス：IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel
     * レスポンス：int
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception 設定解除受付データの格納で例外が発生した場合
     */
    public int insertIfaMutualFundAccumulateSettingBulkChangeConfirmSql001(
            IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel req) throws Exception;

}

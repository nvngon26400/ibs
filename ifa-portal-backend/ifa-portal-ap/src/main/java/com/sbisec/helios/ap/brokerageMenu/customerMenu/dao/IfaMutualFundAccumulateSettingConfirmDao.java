package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingConfirmSql001RequestModel;

/**
 * 画面ID：SUB0202_0403-02_2
 * 画面名：投信積立設定確認
 * 
 * @author nicksen.li
 * 
 * 2025/04/20 新規作成
 *
 */
public interface IfaMutualFundAccumulateSettingConfirmDao {

    /**
     * SQLID：Sql001
     * SQL名：積立設定登録
     * SQLタイプ：insert
     * リクエストクラス：IfaMutualFundAccumulateSettingConfirmSql001RequestModel
     * レスポンスクラス：IfaMutualFundAccumulateSettingConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaMutualFundAccumulateSettingConfirmSql001(IfaMutualFundAccumulateSettingConfirmSql001RequestModel req)
            throws Exception;

}

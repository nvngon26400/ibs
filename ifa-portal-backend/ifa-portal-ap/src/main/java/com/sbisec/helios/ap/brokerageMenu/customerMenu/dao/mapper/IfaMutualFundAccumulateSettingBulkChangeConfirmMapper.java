package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel;


/**
 * 画面ID：SUB0202_0403-05_2
 * 画面名：投信積立設定一括変更確認
 *
 * @author nicksen.li
 * 2025/07/24 新規作成
 *
 */
@Mapper
public interface IfaMutualFundAccumulateSettingBulkChangeConfirmMapper {
    
    
    /**
     * SQLID：Sql001
     * SQL名：設定解除受付データの格納
     * SQLタイプ：insert
     *
     * @param req リクエストパラメータ
     * @return レスポンス
     * @exception Exception  設定解除受付データの格納で例外が発生した場合
     */
    public int insertIfaMutualFundAccumulateSettingBulkChangeConfirmSql001(
            @Param("req") IfaMutualFundAccumulateSettingBulkChangeConfirmSql001RequestModel req
        ) throws Exception;
    
}

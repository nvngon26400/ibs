package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingChangeConfirmSql001RequestModel;



/**
 * 
 * 画面ID：SUB0202_0403-03_2
 * 画面名：投信積立設定変更確認
 * @author nicksen.li
 *
 * 2025/04/20 新規作成
 */
@Mapper
public interface IfaMutualFundAccumulateSettingChangeConfirmMapper {
    
    
    /**
     * SQLID：Sql001
     * SQL名：設定変更受付データの格納
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaMutualFundAccumulateSettingChangeConfirmSql001(
        @Param("req")  IfaMutualFundAccumulateSettingChangeConfirmSql001RequestModel req
        ) throws Exception;

}

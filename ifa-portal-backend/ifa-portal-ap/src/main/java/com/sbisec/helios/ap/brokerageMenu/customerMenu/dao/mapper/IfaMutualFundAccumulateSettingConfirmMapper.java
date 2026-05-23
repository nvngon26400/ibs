package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundAccumulateSettingConfirmSql001RequestModel;



/**
 * 
 * 画面ID：SUB0202_0403-02_2
 * 画面名：投信積立設定確認
 * @author nicksen.li
 *
 * 2025/04/20 新規作成
 */
@Mapper
public interface IfaMutualFundAccumulateSettingConfirmMapper {
    
    
    /**
     * SQLID：Sql001
     * SQL名：積立設定登録
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaMutualFundAccumulateSettingConfirmSql001(
        @Param("req")  IfaMutualFundAccumulateSettingConfirmSql001RequestModel req
        ) throws Exception;

}

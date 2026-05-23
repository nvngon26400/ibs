package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxOrderConfirmSql002RequestModel;

/**
 * 
 * 画面ID：SUB0202_0502-02_2
 * 画面名：為替注文確認
 * @author <author-name>
 *
 * 2023/09/25 新規作成
 */
@Mapper
public interface IfaFxOrderConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaFxOrderConfirmSql001(@Param("req") IfaFxOrderConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：更新
     * SQLタイプ：update
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaFxOrderConfirmSql002(@Param("req") IfaFxOrderConfirmSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public String selectIfaFxOrderConfirmSql003() throws Exception;
    
}

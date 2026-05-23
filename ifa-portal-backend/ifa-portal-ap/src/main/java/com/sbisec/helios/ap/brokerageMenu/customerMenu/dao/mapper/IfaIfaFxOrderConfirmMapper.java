package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaIfaFxOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaIfaFxOrderConfirmSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
 * 
 * 画面ID：SUB0202_0503-02_2
 * 画面名：【IFA】為替注文確認
 * @author <author-name>
 *
 * 2023/09/26 新規作成
 */
@Mapper
public interface IfaIfaFxOrderConfirmMapper {
    
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaIfaFxOrderConfirmSql001(
        @Param("req")  IfaIfaFxOrderConfirmSql001RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：更新
     * SQLタイプ：update
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaIfaFxOrderConfirmSql002(
        @Param("req")  IfaIfaFxOrderConfirmSql002RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql003
     * SQL名：クエリ
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public String selectIfaIfaFxOrderConfirmSql003(
        ) throws Exception;
    
}

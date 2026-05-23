package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql003aResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql003bResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 画面ID：SUB0202_0501-02_1
 * 画面名：為替取引注文取消確認
 * @author 鄒
 * 
 * 2023/11/20 新規作成
 *
 */
@Mapper
public interface IfaFxTradeOrderCancelConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaFxTradeOrderCancelConfirmSql001(
        @Param("req")  IfaFxTradeOrderCancelConfirmSql001RequestModel req
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
    public int updateIfaFxTradeOrderCancelConfirmSql002(
        @Param("req")  IfaFxTradeOrderCancelConfirmSql002RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql003a
     * SQL名：クエリ
     * SQLタイプ：select
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public IfaFxTradeOrderCancelConfirmSql003aResponseModel selectIfaFxTradeOrderCancelConfirmSql003a(
        @Param("req")  IfaFxTradeOrderCancelConfirmSql003RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql003b
     * SQL名：クエリ
     * SQLタイプ：select
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public IfaFxTradeOrderCancelConfirmSql003bResponseModel selectIfaFxTradeOrderCancelConfirmSql003b(
            @Param("req")  IfaFxTradeOrderCancelConfirmSql003RequestModel req
            ) throws Exception;


    /**
     * SQLID：Sql004
     * SQL名：クエリ
     * SQLタイプ：select
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public String selectIfaFxTradeOrderCancelConfirmSql004() throws Exception;
}

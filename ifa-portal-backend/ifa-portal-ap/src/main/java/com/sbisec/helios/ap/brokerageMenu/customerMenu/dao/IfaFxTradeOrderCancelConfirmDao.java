package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql003aResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql003bResponseModel;

/**
 * 画面ID：SUB0202_0501-02_1
 * 画面名：為替取引注文取消確認
 * @author 鄒
 * 
 * 2023/11/20 新規作成
 *
 */
public interface IfaFxTradeOrderCancelConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql001RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaFxTradeOrderCancelConfirmSql001(IfaFxTradeOrderCancelConfirmSql001RequestModel req)
            throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：更新
     * SQLタイプ：update
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql002RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaFxTradeOrderCancelConfirmSql002(IfaFxTradeOrderCancelConfirmSql002RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql003a
     * SQL名：クエリ
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql003RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public IfaFxTradeOrderCancelConfirmSql003aResponseModel selectIfaFxTradeOrderCancelConfirmSql003a(IfaFxTradeOrderCancelConfirmSql003RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql003b
     * SQL名：クエリ
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql003RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public IfaFxTradeOrderCancelConfirmSql003bResponseModel selectIfaFxTradeOrderCancelConfirmSql003b(IfaFxTradeOrderCancelConfirmSql003RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：クエリ
     * SQLタイプ：select
     * リクエストクラス：IfaFxTradeOrderCancelConfirmSql004RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public String selectIfaFxTradeOrderCancelConfirmSql004()
            throws Exception;
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxOrderConfirmSql002RequestModel;

/**
 * 画面ID：SUB0202_0502-02_2
 * 画面名：為替注文確認
 * @author <author-name>
 * 
 * 2023/09/25 新規作成
 *
 */
public interface IfaFxOrderConfirmDao {
    
    /**
     * SQLID：Sql002
     * SQL名：更新
     * SQLタイプ：update
     * リクエストクラス：IfaFxOrderConfirmSql002RequestModel
     * レスポンスクラス：IfaFxOrderConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaFxOrderConfirmSql002(IfaFxOrderConfirmSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * リクエストクラス：IfaFxOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaFxOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaFxOrderConfirmSql001(IfaFxOrderConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * リクエストクラス：IfaFxOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaFxOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public String selectIfaFxOrderConfirmSql003() throws Exception;
}

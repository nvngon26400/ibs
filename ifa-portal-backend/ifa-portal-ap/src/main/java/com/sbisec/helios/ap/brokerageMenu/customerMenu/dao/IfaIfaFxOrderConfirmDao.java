package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaIfaFxOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaIfaFxOrderConfirmSql002RequestModel;



/**
 * 画面ID：SUB0202_0503-02_2
 * 画面名：【IFA】為替注文確認
 * @author <author-name>
 * 
 * 2023/09/26 新規作成
 *
 */
public interface IfaIfaFxOrderConfirmDao {
    
    
    /**
     * SQLID：Sql003
     * SQL名：クエリ
     * SQLタイプ：select
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public String selectIfaIfaFxOrderConfirmSql003()
            throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：更新
     * SQLタイプ：update
     * リクエストクラス：IfaIfaFxOrderConfirmSql002RequestModel
     * レスポンスクラス：IfaIfaFxOrderConfirmSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaIfaFxOrderConfirmSql002(IfaIfaFxOrderConfirmSql002RequestModel req)
            throws Exception;
    
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * リクエストクラス：IfaIfaFxOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaIfaFxOrderConfirmSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaIfaFxOrderConfirmSql001(IfaIfaFxOrderConfirmSql001RequestModel req)
            throws Exception;
    
    
}

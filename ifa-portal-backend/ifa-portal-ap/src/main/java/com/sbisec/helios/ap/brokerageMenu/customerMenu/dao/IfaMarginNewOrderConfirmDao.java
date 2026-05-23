package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.IfaMarginNewOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.IfaMarginNewOrderConfirmSql002RequestModel;

/**
 * 画面ID：SUB0202_0212-01_2
 * 画面名：信用新規注文確認
 * @author <author-name>
 * 
 * 2023/10/13 新規作成
 *
 */
public interface IfaMarginNewOrderConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaMarginNewOrderConfirmSql001RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaMarginNewOrderConfirmSql001(IfaMarginNewOrderConfirmSql001RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaMarginNewOrderConfirmSql002_1RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaMarginNewOrderConfirmSql002(IfaMarginNewOrderConfirmSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaMarginNewOrderConfirmSql002RequestModel
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaMarginNewOrderConfirmSql002b(IfaMarginNewOrderConfirmSql002RequestModel req) throws Exception;
    
    public String selectIfaMarginNewOrderConfirmSql003() throws Exception;
    
}

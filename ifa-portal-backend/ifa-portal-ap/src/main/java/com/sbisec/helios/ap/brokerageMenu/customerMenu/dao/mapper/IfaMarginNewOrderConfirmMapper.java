package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.IfaMarginNewOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.IfaMarginNewOrderConfirmSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 画面ID：SUB0202_0212-01_2
 * 画面名：信用新規注文確認
 * @author <author-name>
 *
 * 2023/10/13 新規作成
 */
@Mapper
public interface IfaMarginNewOrderConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaMarginNewOrderConfirmSql001(@Param("req") IfaMarginNewOrderConfirmSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaMarginNewOrderConfirmSql002(@Param("req") IfaMarginNewOrderConfirmSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaMarginNewOrderConfirmSql002b(@Param("req") IfaMarginNewOrderConfirmSql002RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    
    public String selectIfaMarginNewOrderConfirmSql003() throws Exception;
    
}

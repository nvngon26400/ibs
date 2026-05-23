package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel;

/**
 * 
 * 画面ID：SUB0202_0303-03_1
 * 画面名：米株信用取引注文取消確認
 * @author <author-name>
 *
 * 2023/09/16 新規作成
 */
@Mapper
public interface IfaForeignMarginTradeOrderCancelConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：登録
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaForeignMarginTradeOrderCancelConfirmSql001(
            @Param("req") IfaForeignMarginTradeOrderCancelConfirmSql001RequestModel req) throws Exception;
    
    /**
    * SQLID：Sql002
    * SQL名：更新
    * SQLタイプ：update
    * @param <paramName> <description of param value>
    * @return <description of return value>
    * @exception <exceptionName> <description>
    * @see <reference item>
    */
    public int updateIfaForeignMarginTradeOrderCancelConfirmSql002(
            @Param("req") IfaForeignMarginTradeOrderCancelConfirmSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel
     * レスポンスクラス：IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaForeignMarginTradeOrderCancelConfirmSql003ResponseModel> selectIfaForeignMarginTradeOrderCancelConfirmSql003(
            @Param("req") IfaForeignMarginTradeOrderCancelConfirmSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：検索
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaForeignMarginTradeOrderCancelConfirmSql004ResponseModel> selectIfaForeignMarginTradeOrderCancelConfirmSql004()
            throws Exception;
    
}

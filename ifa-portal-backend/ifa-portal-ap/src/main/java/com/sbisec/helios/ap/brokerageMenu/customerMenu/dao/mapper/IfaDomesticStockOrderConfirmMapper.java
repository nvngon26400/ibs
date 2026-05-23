package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderConfirmSql002RequestModel;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderConfirmSql001RequestModel;

/**
 * 
 * 画面ID：SUB0202_0208-01_3
 * 画面名：国内株式注文確認
 * @author <author-name>
 *
 * 2023/11/08 新規作成
 */
@Mapper
public interface IfaDomesticStockOrderConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaDomesticStockOrderConfirmSql001(
            @Param("req") IfaDomesticStockOrderConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderConfirmSql002RequestModel
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaDomesticStockOrderConfirmSql002(
            @Param("req") IfaDomesticStockOrderConfirmSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderConfirmSql002RequestModel
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaDomesticStockOrderConfirmSql002b(
            @Param("req") IfaDomesticStockOrderConfirmSql002RequestModel req) throws Exception;

}

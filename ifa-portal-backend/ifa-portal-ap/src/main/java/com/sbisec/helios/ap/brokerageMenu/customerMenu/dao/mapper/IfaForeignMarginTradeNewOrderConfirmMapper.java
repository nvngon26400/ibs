package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeNewOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeNewOrderConfirmSql002RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_0303-01_2
 * 画面名：米株信用取引新規注文確認
 *
 * @author SCSK 金志
 *
 */
@Mapper
public interface IfaForeignMarginTradeNewOrderConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return 登録結果
     * @exception Exception 発注前の注文登録の際、例外が発生した場合
     */
    public int insertIfaForeignMarginTradeNewOrderConfirmSql001(
            @Param("req") IfaForeignMarginTradeNewOrderConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return 更新結果
     * @exception Exception 発注後の注文更新の際、例外が発生した場合
     */
    public int updateIfaForeignMarginTradeNewOrderConfirmSql002(
            @Param("req") IfaForeignMarginTradeNewOrderConfirmSql002RequestModel req) throws Exception;
    
}

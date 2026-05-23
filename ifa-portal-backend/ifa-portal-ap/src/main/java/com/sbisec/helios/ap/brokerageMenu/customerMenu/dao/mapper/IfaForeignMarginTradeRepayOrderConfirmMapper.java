package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeRepayOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeRepayOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeRepayOrderConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeRepayOrderConfirmSql004ResponseModel;

/**
 * 画面ID：SUB0202_0303-04_2
 * 画面名：米株信用取引返済注文確認
 * 2023/09/07 新規作成
 *
 * @author SCSK
 */
@Mapper
public interface IfaForeignMarginTradeRepayOrderConfirmMapper {
    
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req Sql001リクエスト
     * @return Integer 挿入行数
     * @exception Exception DBエクセプション
     */
    public int insertIfaForeignMarginTradeRepayOrderConfirmSql001(
            @Param("req") IfaForeignMarginTradeRepayOrderConfirmSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注前の個別建玉情報登録
     * SQLタイプ：insert
     *
     * @param req Sql002リクエスト
     * @return Integer 挿入行数
     * @exception Exception DBエクセプション
     */
    public int insertIfaForeignMarginTradeRepayOrderConfirmSql002(
            @Param("req") IfaForeignMarginTradeRepayOrderConfirmSql002RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql003
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     *
     * @param req Sql003リクエスト
     * @return Integer 更新行数
     * @exception Exception DBエクセプション
     */
    public int updateIfaForeignMarginTradeRepayOrderConfirmSql003(
            @Param("req") IfaForeignMarginTradeRepayOrderConfirmSql003RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：IFA注文番号の取得
     * SQLタイプ：select
     *
     * @return IfaForeignMarginTradeRepayOrderConfirmSql004ResponseModel Sql004レスポンス
     * @exception Exception DBエクセプション
     */
    public IfaForeignMarginTradeRepayOrderConfirmSql004ResponseModel selectIfaForeignMarginTradeRepayOrderConfirmSql004() throws Exception;
    
    
}

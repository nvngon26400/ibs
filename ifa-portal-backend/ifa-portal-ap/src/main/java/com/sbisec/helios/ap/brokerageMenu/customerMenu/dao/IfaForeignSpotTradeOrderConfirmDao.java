package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderConfirmSql001RequestModel;

/**
 * 画面ID：SUB0202_0301-01_2
 * 画面名：外国現物取引注文確認
 *
 * @author 福岡　利基
 * 
 */
public interface IfaForeignSpotTradeOrderConfirmDao {
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新: 発注が正常の場合
     * SQLタイプ：update
     * リクエストクラス：IfaForeignSpotTradeOrderConfirmSql001RequestModel
     *
     * @param req リクエスト
     * @return INSERTデータ数
     * @exception Exception 例外処理
     */
    public int updateIfaForeignSpotTradeOrderConfirmSql002Success(IfaForeignSpotTradeOrderConfirmSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新: 発注が異常の場合
     * SQLタイプ：update
     * リクエストクラス：IfaForeignSpotTradeOrderConfirmSql001RequestModel
     *
     * @param req リクエスト
     * @return UPDATEデータ数
     * @exception Exception 例外処理
     */
    public int updateIfaForeignSpotTradeOrderConfirmSql002Error(IfaForeignSpotTradeOrderConfirmSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaForeignSpotTradeOrderConfirmSql001RequestModel
     *
     * @param req リクエスト
     * @return UPDATEデータ数
     * @exception Exception 例外処理
     */
    public int insertIfaForeignSpotTradeOrderConfirmSql001(IfaForeignSpotTradeOrderConfirmSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：連番のシーケンスオブジェクト取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignSpotTradeOrderConfirmSql003RequestModel
     *
     * @return String シーケンスオブジェクト
     * @exception Exception 例外処理
     */
    public String selectIfaForeignSpotTradeOrderConfirmSql003() throws Exception;
    
}

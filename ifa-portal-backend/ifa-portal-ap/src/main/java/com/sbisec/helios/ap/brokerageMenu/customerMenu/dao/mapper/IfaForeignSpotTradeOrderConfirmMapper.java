package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderConfirmSql001RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_0301-01_2
 * 画面名：外国現物取引注文確認
 * 2024/02/08 新規作成
 *
 * @author 福岡　利基
 */
@Mapper
public interface IfaForeignSpotTradeOrderConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     *
     * @param req リクエスト
     * @return INSERTデータ数
     * @exception Exception 例外処理
     */
    public int insertIfaForeignSpotTradeOrderConfirmSql001(
            @Param("req") IfaForeignSpotTradeOrderConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新: 発注が正常の場合
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return UPDATEデータ数
     * @exception Exception 例外処理
     */
    public int updateIfaForeignSpotTradeOrderConfirmSql002Success(
            @Param("req") IfaForeignSpotTradeOrderConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新: 発注が異常の場合
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return UPDATEデータ数
     * @exception Exception 例外処理
     */
    public int updateIfaForeignSpotTradeOrderConfirmSql002Error(
            @Param("req") IfaForeignSpotTradeOrderConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：連番のシーケンスオブジェクト取得
     * SQLタイプ：select
     *
     * @return String シーケンスオブジェクト
     * @exception Exception 例外処理
     */
    public String selectIfaForeignSpotTradeOrderConfirmSql003() throws Exception;
}

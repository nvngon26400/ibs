package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import java.util.Optional;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel;

/**
 * 画面ID：SUB0202_0301-03_1
 * 画面名：外国現物取引注文取消確認
 * 2024/03/29 新規作成
 *
 * @author 宇田川達弥
 */
public interface IfaForeignSpotTradeOrderCancelConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：取消発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel
     *
     * @param req リクエストモデル
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    public int insertIfaForeignSpotTradeOrderCancelConfirmSql001(
            IfaForeignSpotTradeOrderCancelConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：取消発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaForeignSpotTradeOrderCancelConfirmSql002RequestModel
     *
     * @param req リクエストモデル
     * @return 件数
     * @exception Exception 例外が発生した場合
     */
    public int updateIfaForeignSpotTradeOrderCancelConfirmSql002(
            IfaForeignSpotTradeOrderCancelConfirmSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：委託注文情報を取得
     * SQLタイプ：select
     *
     * @param acceptOrderNo 取引注文番号
     * @return IFA注文サブ番号
     * @exception Exception 例外が発生した場合
     */
    public Optional<String> selectIfaForeignSpotTradeOrderCancelConfirmSql003(String acceptOrderNo) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：委託注文情報を取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel
     *
     * @param acceptOrderNo 取引注文番号
     * @param ifaOrderSubNo IFA注文サブ番号
     * @return レスポンスモデル
     * @exception Exception 例外が発生した場合
     */
    public Optional<IfaForeignSpotTradeOrderCancelConfirmSql004ResponseModel> selectIfaForeignSpotTradeOrderCancelConfirmSql004(
            String acceptOrderNo, String ifaOrderSubNo) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：連番のシーケンスオブジェクト取得
     * SQLタイプ：select
     *
     * @return IFA注文番号
     * @exception Exception 例外が発生した場合
     */
    public String selectIfaForeignSpotTradeOrderCancelConfirmSql005() throws Exception;
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCancelConfirmSql002RequestModel;

/**
 * 画面ID：SUB0202_0212-03_1
 * 画面名：信用新規注文取消確認
 * 2024/04/17 新規作成
 *
 * @author 宇田川達弥
 */
public interface IfaMarginNewOrderCancelConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaMarginNewOrderCancelConfirmSql001RequestModel
     *
     * @param req リクエスト
     * @return 件数
     * @exception exception システムエラー
     */
    public int insertIfaMarginNewOrderCancelConfirmSql001(IfaMarginNewOrderCancelConfirmSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql001Sub
     * SQL名：国内株式注文取得
     * SQLタイプ：select
     * レスポンスクラス：IfaMarginNewOrderCancelConfirmSql001SubResponseModel
     *
     * @param orderNum EC受注番号
     * @param butenCode 部店コード
     * @param orderDayTime 受注日時
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public IfaMarginNewOrderCancelConfirmSql001SubResponseModel selectIfaMarginNewOrderCancelConfirmSql001Sub(
            String orderNum, String butenCode, String orderDayTime) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaMarginNewOrderCancelConfirmSql002RequestModel
     *
     * @param req リクエスト
     * @return 件数
     * @exception exception システムエラー
     */
    public int updateIfaMarginNewOrderCancelConfirmSql002(IfaMarginNewOrderCancelConfirmSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     *
     * @param req リクエスト
     * @return 件数
     * @exception Exception システムエラー
     */
    public int updateIfaMarginNewOrderCancelConfirmSql002b(IfaMarginNewOrderCancelConfirmSql002RequestModel req)
            throws Exception;

}

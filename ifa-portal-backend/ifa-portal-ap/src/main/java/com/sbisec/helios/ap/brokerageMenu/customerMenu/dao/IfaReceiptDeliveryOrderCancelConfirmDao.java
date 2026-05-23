package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaReceiptDeliveryOrderCancelConfirmSql003ResponseModel;



/**
 * 画面ID：SUB0202_0212-09_1
 * 画面名：現引現渡注文取消確認
 *
 * @author SCSK
 2024/05/21 新規作成
 *
 */
public interface IfaReceiptDeliveryOrderCancelConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaReceiptDeliveryOrderCancelConfirmSql001RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaReceiptDeliveryOrderCancelConfirmSql001(
            IfaReceiptDeliveryOrderCancelConfirmSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaReceiptDeliveryOrderCancelConfirmSql002(IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaReceiptDeliveryOrderCancelConfirmSql002b(IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql03
     * SQL名：既存レコードのチェック
     * SQLタイプ：select
     * リクエストクラス：IfaReceiptDeliveryOrderCancelConfirmSql003RequestModel
     * レスポンスクラス：IfaReceiptDeliveryOrderCancelConfirmSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaReceiptDeliveryOrderCancelConfirmSql003ResponseModel> selectIfaReceiptDeliveryOrderCancelConfirmSql003(
            IfaReceiptDeliveryOrderCancelConfirmSql003RequestModel req)
            throws Exception;
    
    
}

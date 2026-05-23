package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMarginNewOrderCorrectConfirmSql003ResponseModel;



/**
 * 画面ID：SUB0202_0212-02_2
 * 画面名：信用新規注文訂正確認
 *
 * @author SCSK
   2024/04/16 新規作成
 *
 */
public interface IfaMarginNewOrderCorrectConfirmDao {
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaMarginNewOrderCorrectConfirmSql001RequestModel
     * レスポンスクラス：IfaMarginNewOrderCorrectConfirmSql001ResponseModel
     *
     * @return sql001レスポンス
     * @exception Exception error
     */
    public int insertIfaMarginNewOrderCorrectConfirmSql001(IfaMarginNewOrderCorrectConfirmSql001RequestModel req)
            throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaMarginNewOrderCorrectConfirmSql002RequestModel
     * レスポンスクラス：IfaMarginNewOrderCorrectConfirmSql002ResponseModel
     *
     * @return sql002レスポンス
     * @exception Exception error
     */
    public int updateIfaMarginNewOrderCorrectConfirmSql002(IfaMarginNewOrderCorrectConfirmSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaMarginNewOrderCorrectConfirmSql002RequestModel
     *
     * @return sql002レスポンス
     * @exception Exception error
     */
    public int updateIfaMarginNewOrderCorrectConfirmSql002b(IfaMarginNewOrderCorrectConfirmSql002RequestModel req)
            throws Exception;
    
    public DataList<IfaMarginNewOrderCorrectConfirmSql003ResponseModel> selectIfaMarginNewOrderCorrectConfirmSql003(
            IfaMarginNewOrderCorrectConfirmSql003RequestModel req) throws Exception;
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCorrectConfirmSql001RequestModel;



/**
 * 画面ID：SUB0202_0208-03_2
 * 画面名：国内株式注文訂正確認
 *
 * @author SCSK 矢口
 * 
 *      2024/04/16 新規作成
 *
 */
public interface IfaDomesticStockOrderCorrectConfirmDao {
    
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaDomesticStockOrderCorrectConfirmSql001RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCorrectConfirmSql001ResponseModel
     *
     * @param req SQL001リクエスト
     * @return 挿入件数
     * @exception Exception 例外
     */
    public int insertIfaDomesticStockOrderCorrectConfirmSql001(IfaDomesticStockOrderCorrectConfirmSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderCorrectConfirmSql002RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCorrectConfirmSql002ResponseModel
     *
     * @param req SQL002リクエスト
     * @return 更新件数
     * @exception Exception 例外
     */
    public int updateIfaDomesticStockOrderCorrectConfirmSql002(IfaDomesticStockOrderCorrectConfirmSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新(発注がエラーの場合(API応答なし))
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderCorrectConfirmSql002RequestModel
     *
     * @param req SQL002リクエスト
     * @return 更新件数
     * @exception Exception 例外
     */
    public int updateIfaDomesticStockOrderCorrectConfirmSql002b(IfaDomesticStockOrderCorrectConfirmSql002RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：同じEC受注番号のデータを取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticStockOrderCorrectConfirmSql003RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCorrectConfirmSql003ResponseModel
     *
     * @param req SQL003リクエスト
     * @return 同じEC受注番号のデータ
     * @exception Exception 例外
     */
    public DataList<IfaDomesticStockOrderCorrectConfirmSql003ResponseModel> selectIfaDomesticStockOrderCorrectConfirmSql003(IfaDomesticStockOrderCorrectConfirmSql003RequestModel req) throws Exception;
    
}

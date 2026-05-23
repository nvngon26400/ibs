package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignAmountUnpaidOverdraftAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel;





/**
 * 画面ID：SUB020301_01-03
 * 画面名：外貨未入金・赤残アラート一覧
 *
 * @author <author-name>
 2024/06/12 新規作成
 *
 */
public interface IfaForeignAmountUnpaidOverdraftAlertListDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：外貨預り金赤残顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignAmountUnpaidOverdraftAlertListSql001RequestModel
     * レスポンスクラス：IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel> selectIfaForeignAmountUnpaidOverdraftAlertListSql001(IfaForeignAmountUnpaidOverdraftAlertListSql001RequestModel req)
            throws Exception;
    
    
    
    
}

package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel;





/**
 * 画面ID：SUB020301_01-01
 * 画面名：円貨未入金・赤残アラート一覧
 *
 * @author BASE李
 2024/05/23 新規作成
 *
 */
public interface IfaJpyAmountUnpaidOverdraftAlertListDao {
    
    
    /**
     * SQLID：Sql001
     * SQL名：預り金赤残顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel
     * レスポンスクラス：IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel> selectIfaJpyAmountUnpaidOverdraftAlertListSql001(IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel req)
            throws Exception;
    
    
    
    
}

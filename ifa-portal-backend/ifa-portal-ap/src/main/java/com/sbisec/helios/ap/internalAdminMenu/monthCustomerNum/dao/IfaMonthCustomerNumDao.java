package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql003ResponseModel;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
public interface IfaMonthCustomerNumDao {
    /**
     * SQLID：Sql002
     * SQL名：月末口座数リスト取得
     * SQLタイプ：select
     * リクエスト:IfaMonthCustomerNumSql002RequestModel
     * レスポンスクラス：IfaMonthCustomerNumSql002ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumSql002ResponseModel> selectIfaMonthCustomerNumSql002(
            IfaMonthCustomerNumSql002RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：仲介業者顧客情報リスト取得
     * SQLタイプ：select
     * リクエスト:IfaMonthCustomerNumSql003RequestModel
     * レスポンスクラス：IfaMonthCustomerNumSql003ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumSql003ResponseModel>  selectIfaMonthCustomerNumSql003(
            IfaMonthCustomerNumSql003RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：基準年月取得
     * SQLタイプ：select
     * レスポンスクラス：String
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public String selectIfaMonthCustomerNumSql004()
            throws Exception;
}

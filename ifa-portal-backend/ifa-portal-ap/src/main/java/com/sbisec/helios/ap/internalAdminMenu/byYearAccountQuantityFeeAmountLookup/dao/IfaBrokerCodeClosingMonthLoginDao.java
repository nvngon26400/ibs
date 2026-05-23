package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao;

import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaBrokerCodeClosingMonthLoginSql004RequestModel;

/**
 * 画面ID：SUB0406-01_1
 * 画面名：仲介業者決算月設定
 *
 * @author SBI大連 夏
 * @date   2025/05/27
 */

public interface IfaBrokerCodeClosingMonthLoginDao {
    
    /**
     * SQLID：Sql001
     * SQL名：仲介業者名取得
     * SQLタイプ：select
     * リクエストクラス：IfaBrokerCodeClosingMonthLoginSql001RequestModel
     * レスポンスクラス：IfaBrokerCodeClosingMonthLoginSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public IfaBrokerCodeClosingMonthLoginSql001ResponseModel selectIfaBrokerCodeClosingMonthLoginSql001(
        IfaBrokerCodeClosingMonthLoginSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：仲介業者仲介業者決算月取得
     * SQLタイプ：select
     * リクエストクラス：IfaBrokerCodeClosingMonthLoginSql002RequestModel
     * レスポンスクラス：IfaBrokerCodeClosingMonthLoginSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public IfaBrokerCodeClosingMonthLoginSql002ResponseModel selectIfaBrokerCodeClosingMonthLoginSql002(
        IfaBrokerCodeClosingMonthLoginSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：仲介業者期初年月取得
     * SQLタイプ：select
     * リクエストクラス：IfaBrokerCodeClosingMonthLoginSql003RequestModel
     * レスポンスクラス：IfaBrokerCodeClosingMonthLoginSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public IfaBrokerCodeClosingMonthLoginSql003ResponseModel selectIfaBrokerCodeClosingMonthLoginSql003(
        IfaBrokerCodeClosingMonthLoginSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：仲介業者決算月マスタ登録･更新
     * SQLタイプ：select
     * リクエストクラス：IfaBrokerCodeClosingMonthLoginSql004RequestModel
     *
     * @param req リクエスト
     * @return int 更新件数
     * @exception exception システムエラー
     */
    public int updateIfaBrokerCodeClosingMonthLoginSql004(
        IfaBrokerCodeClosingMonthLoginSql004RequestModel req) throws Exception;
}

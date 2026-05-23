package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model.IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/05/22
 */

public interface IfaByYearAccountQuantityFeeAmountLookupDao {
    
    /**
     * SQLID：Sql002
     * SQL名：仲介業者決算月情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel
     * レスポンスクラス：IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel> selectIfaByYearAccountQuantityFeeAmountLookupSql002(
        IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：年度別口座数・報酬額情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel
     * レスポンスクラス：IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel> selectIfaByYearAccountQuantityFeeAmountLookupSql003(
        IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：媒介口座明細情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaByYearAccountQuantityFeeAmountLookupSql004RequestModel
     * レスポンスクラス：IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel> selectIfaByYearAccountQuantityFeeAmountLookupSql004(
        IfaByYearAccountQuantityFeeAmountLookupSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：媒介可否区分取得
     * SQLタイプ：select
     * リクエストクラス：IfaByYearAccountQuantityFeeAmountLookupSql005RequestModel
     * レスポンスクラス：IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel> selectIfaByYearAccountQuantityFeeAmountLookupSql005(
        IfaByYearAccountQuantityFeeAmountLookupSql005RequestModel req) throws Exception;
}

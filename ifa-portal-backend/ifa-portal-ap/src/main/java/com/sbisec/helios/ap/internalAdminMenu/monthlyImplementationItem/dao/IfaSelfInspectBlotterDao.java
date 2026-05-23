package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql004ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql005ResponseModel;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 *
 */
public interface IfaSelfInspectBlotterDao {
    
    /**
     * SQLID：Sql001
     * SQL名：表示年月dropDownLisTデータ取得
     * SQLタイプ：select
     * レスポンスクラス：IfaSelfInspectBlotterSql001ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterSql001ResponseModel> selectIfaSelfInspectBlotterSql001() throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：最終更新日時取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterSql002RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterSql002ResponseModel> selectIfaSelfInspectBlotterSql002(
            IfaSelfInspectBlotterSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：自己点検確認日取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterSql003RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterSql003ResponseModel> selectIfaSelfInspectBlotterSql003(
            IfaSelfInspectBlotterSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：未登録自己点検記録簿情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterSql004RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterSql004ResponseModel> selectIfaSelfInspectBlotterSql004(
            IfaSelfInspectBlotterSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：登録自己点検記録簿情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterSql005RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterSql005ResponseModel> selectIfaSelfInspectBlotterSql005(
            IfaSelfInspectBlotterSql005RequestModel req) throws Exception;
    
}

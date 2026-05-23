package com.sbisec.helios.ap.brokerageMenu.commFee.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaTrustFeeSql001ToSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaTrustFeeSql001ToSql006ResponseModel;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 *
 */
public interface IfaTrustFeeDao {
    
    /**
     * SQLID：Sql001
     * SQL名：信託報酬（日次 - 明細）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTrustFeeSql001ToSql006RequestModel
     * レスポンスクラス：IfaTrustFeeSql001ToSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql001(
            IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：信託報酬（日次 - 顧客・証券種別・通貨毎）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTrustFeeSql001ToSql006RequestModel
     * レスポンスクラス：IfaTrustFeeSql001ToSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql002(
            IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：信託報酬（日次 - 通貨毎）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTrustFeeSql001ToSql006RequestModel
     * レスポンスクラス：IfaTrustFeeSql001ToSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql003(
            IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：信託報酬（月次累計 - 明細）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTrustFeeSql001ToSql006RequestModel
     * レスポンスクラス：IfaTrustFeeSql001ToSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql004(
            IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：信託報酬（月次累計 - 顧客・証券種別・通貨毎）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTrustFeeSql001ToSql006RequestModel
     * レスポンスクラス：IfaTrustFeeSql001ToSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql005(
            IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：信託報酬（月次累計 - 通貨毎）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTrustFeeSql001ToSql006RequestModel
     * レスポンスクラス：IfaTrustFeeSql001ToSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql006(
            IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
}

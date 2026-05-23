package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql004ResponseModel;

/**
 * 画面ID：SUB0202_0703-01
 * 画面名：受発信状況照会
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */
public interface IfaSendReceiveStatusLookupDao {
    
    /**
     * SQLID：Sql001
     * SQL名：書類一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql001ResponseModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql001RequestModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception 
     * @exception exception システムエラー
     */
    public DataList<IfaSendReceiveStatusLookupSql001ResponseModel> selectIfaSendReceiveStatusLookupSql001(
            IfaSendReceiveStatusLookupSql001RequestModel selSql001Req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：受発信状況一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql002ResponseModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql002RequestModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception 
     * @exception exception システムエラー
     */
    DataList<IfaSendReceiveStatusLookupSql002ResponseModel> selectIfaSendReceiveStatusLookupSql002(
            IfaSendReceiveStatusLookupSql002RequestModel selSql002Req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：書類請求付加情報詳細
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql003ResquestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception 
     * @exception exception システムエラー
     */
    DataList<IfaSendReceiveStatusLookupSql003ResponseModel> selectIfaSendReceiveStatusLookupSql003(
            IfaSendReceiveStatusLookupSql003RequestModel selSql003Req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：書類不備付加情報リス取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql004ResponseModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql004RequestModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @throws Exception 
     * @exception exception システムエラー
     */
    DataList<IfaSendReceiveStatusLookupSql004ResponseModel> selectIfaSendReceiveStatusLookupSql004(
            IfaSendReceiveStatusLookupSql004RequestModel selSql004Req) throws Exception;

}

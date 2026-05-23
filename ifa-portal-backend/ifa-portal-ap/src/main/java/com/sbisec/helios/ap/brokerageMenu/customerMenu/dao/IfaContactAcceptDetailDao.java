package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactAcceptDetailSql012ResponseModel;

/**
 * 画面ID：SUB0202_0106-02
 * 画面名：接触履歴受付詳細
 * 
 * @author 趙韫慧
 * 2025/04/22 新規作成
 */
public interface IfaContactAcceptDetailDao {

    /**
     * SQLID：Sql002
     * SQL名：国内株式注文履歴詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql002RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql002ResponseModel
     * 
     * @param req リクエスト
     * @return 国内株式注文履歴詳細情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql002ResponseModel> selectIfaContactAcceptDetailSql002(
            IfaContactAcceptDetailSql002RequestModel req) throws Exception;

    /**
     * SQLID：Sql003 
     * SQL名：国内投信当日注文履歴詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql003RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql003ResponseModel
     * 
     * @param req リクエスト
     * @return 国内投信注文履歴詳細情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql003ResponseModel> selectIfaContactAcceptDetailSql003(
            IfaContactAcceptDetailSql003RequestModel req) throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：その他余力拘束履歴詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql004RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql004ResponseModel
     * 
     * @param req リクエスト
     * @return その他余力拘束履歴情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql004ResponseModel> selectIfaContactAcceptDetailSql004(
            IfaContactAcceptDetailSql004RequestModel req) throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：外株委託注文接触履歴受付詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql005RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql005ResponseModel
     * 
     * @param req リクエスト
     * @return 外株委託注文接触履歴受付詳細情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql005ResponseModel> selectIfaContactAcceptDetailSql005(
            IfaContactAcceptDetailSql005RequestModel req) throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：外株店頭注文接触履歴受付詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql006RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql006ResponseModel
     * 
     * @param req リクエスト
     * @return 外株店頭注文接触履歴受付詳細情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql006ResponseModel> selectIfaContactAcceptDetailSql006(
            IfaContactAcceptDetailSql006RequestModel req) throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：為替取引接触履歴受付詳細取得 
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql007RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql007ResponseModel
     * 
     * @param req リクエスト
     * @return 為替取引接触履歴受付詳細情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql007ResponseModel> selectIfaContactAcceptDetailSql007(
            IfaContactAcceptDetailSql007RequestModel req) throws Exception;

    /**
     * SQLID：Sql008
     * SQL名：同一のIFA注文番号内1番目レコードの注文種別区分情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql008RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql008ResponseModel
     * 
     * @return 同一のIFA注文番号内1番目レコードの注文種別区分情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql008ResponseModel> selectIfaContactAcceptDetailSql008() throws Exception;

    /**
     * SQLID：Sql009
     * SQL名：同一の数据キー内1番目レコードの為替取引注文状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql009RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql009ResponseModel
     * 
     * @return 同一の数据キー内1番目レコードの為替取引注文状況情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql009ResponseModel> selectIfaContactAcceptDetailSql009() throws Exception;

    /**
     * SQLID：Sql010
     * SQL名：同一のEC受注番号内1番目レコードの注文状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql010RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql010ResponseModel
     * 
     * @return 同一のEC受注番号内1番目レコードの注文状況情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql010ResponseModel> selectIfaContactAcceptDetailSql010() throws Exception;

    /**
     * SQLID：Sql011
     * SQL名：同一のEC受注番号内1番目レコードの注文状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql011RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql011ResponseModel
     * 
     * @return 同一のEC受注番号内1番目レコードの注文状況情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql011ResponseModel> selectIfaContactAcceptDetailSql011() throws Exception;

    /**
     * SQLID：Sql012
     * SQL名：：同一のトランザクションID内1番目レコードの取消区分情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactAcceptDetailSql012RequestModel
     * レスポンスクラス：IfaContactAcceptDetailSql012ResponseModel
     * 
     * @return ：同一のトランザクションID内1番目レコードの取消区分情報
     * @exception Exception 異常
     */
    public DataList<IfaContactAcceptDetailSql012ResponseModel> selectIfaContactAcceptDetailSql012() throws Exception;

}
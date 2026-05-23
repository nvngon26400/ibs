package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaMutualFundDetailInfoSql013ResponseModel;

/**
 * 画面ID：SUB0202_0401-03
 * 画面名：投信詳細情報
 *
 * @author SCSK
 *     2024/04/15 新規作成
 *
 */
public interface IfaMutualFundDetailInfoDao {
    
    /**
     * SQLID：Sql001
     * SQL名：協会コード取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql001RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql001ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql001ResponseModel> selectIfaMutualFundDetailInfoSql001(
            IfaMutualFundDetailInfoSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：投信銘柄当月休場日取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql002RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql002ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql002ResponseModel> selectIfaMutualFundDetailInfoSql002(
            IfaMutualFundDetailInfoSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：投信銘柄翌月休場日取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql003RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql003ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql003ResponseModel> selectIfaMutualFundDetailInfoSql003(
            IfaMutualFundDetailInfoSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：締切日（直近）取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql004RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql004ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql004ResponseModel> selectIfaMutualFundDetailInfoSql004(
            IfaMutualFundDetailInfoSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：締切日（次回）取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql005RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql005ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql005ResponseModel> selectIfaMutualFundDetailInfoSql005(
            IfaMutualFundDetailInfoSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：お知らせ取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql006RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql006ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql006ResponseModel> selectIfaMutualFundDetailInfoSql006(
            IfaMutualFundDetailInfoSql006RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql009
     * SQL名：投信明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql009RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql009ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql009ResponseModel> selectIfaMutualFundDetailInfoSql009(
            IfaMutualFundDetailInfoSql009RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql010
     * SQL名：手数料取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql010RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql010ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql010ResponseModel> selectIfaMutualFundDetailInfoSql010(
            IfaMutualFundDetailInfoSql010RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql011
     * SQL名：扱者別手数料取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql011RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql011ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql011ResponseModel> selectIfaMutualFundDetailInfoSql011(
            IfaMutualFundDetailInfoSql011RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql012
     * SQL名：積立単位取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql012RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql012ResponseModel
     *
     * @param req リクエストモデル
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql012ResponseModel> selectIfaMutualFundDetailInfoSql012(
            IfaMutualFundDetailInfoSql012RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql013
     * SQL名：消費税取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql013RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql013ResponseModel
     *
     * @return 応答データリスト
     * @exception Exception システム例外
     */
    public DataList<IfaMutualFundDetailInfoSql013ResponseModel> selectIfaMutualFundDetailInfoSql013() throws Exception;
    
}

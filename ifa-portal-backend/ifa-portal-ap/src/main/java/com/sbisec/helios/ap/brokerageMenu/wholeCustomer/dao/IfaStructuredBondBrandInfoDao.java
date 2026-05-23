package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql007ResponseModel;

/**
 * 画面ID：SUB020302_0104-02
 * 画面名：仕組債銘柄情報
 *
 * @author SCSK川崎
 2024/06/11 新規作成
 *
 */
public interface IfaStructuredBondBrandInfoDao {
    
    /**
     * SQLID：Sql001
     * SQL名：仕組債マスタ取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql001RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql001ResponseModel> selectIfaStructuredBondBrandInfoSql001(
            IfaStructuredBondBrandInfoSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：参照営業日カレンダー取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql002RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql002ResponseModel> selectIfaStructuredBondBrandInfoSql002(
            IfaStructuredBondBrandInfoSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：参照取引所カレンダー取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql003RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql003ResponseModel> selectIfaStructuredBondBrandInfoSql003(
            IfaStructuredBondBrandInfoSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：コード値の表示用文言取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql004RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql004ResponseModel> selectIfaStructuredBondBrandInfoSql004(
            IfaStructuredBondBrandInfoSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：早期償還判定日取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql005RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql005ResponseModel> selectIfaStructuredBondBrandInfoSql005(
            IfaStructuredBondBrandInfoSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：仕組債判定条件マスタ取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql006RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql006ResponseModel> selectIfaStructuredBondBrandInfoSql006(
            IfaStructuredBondBrandInfoSql006RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：参照銘柄リスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql007RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql007ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql007ResponseModel> selectIfaStructuredBondBrandInfoSql007(
            IfaStructuredBondBrandInfoSql007RequestModel req) throws Exception;
    
}

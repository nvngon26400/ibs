package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql001ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql002ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql003ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql004ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql005ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql006ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql007ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql008ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql009ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql010ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql012ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql013ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql014RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql014ResponseModel;


import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectConfirmSql015RequestModel;




/**
 * 画面ID：SUB0204_02-02_2
 * 画面名：BB申込訂正確認
 *
 * @author BASE 李
 2024/04/23 新規作成
 */
@Mapper
public interface IfaBbApplyCorrectConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：申込期間内銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql001RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql001ResponseModel> selectIfaBbApplyCorrectConfirmSql001(
            @Param("req") IfaBbApplyCorrectConfirmSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：投資家属性のプルダウンリスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql002RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql002ResponseModel> selectIfaBbApplyCorrectConfirmSql002(
            @Param("req") IfaBbApplyCorrectConfirmSql002RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql003RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql003ResponseModel> selectIfaBbApplyCorrectConfirmSql003(
            @Param("req") IfaBbApplyCorrectConfirmSql003RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：年間裁量配分割当回数情報を取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql004RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql004ResponseModel> selectIfaBbApplyCorrectConfirmSql004(
            @Param("req") IfaBbApplyCorrectConfirmSql004RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：裁量配分割当回数(未抽選)情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql005RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql005ResponseModel> selectIfaBbApplyCorrectConfirmSql005(
            @Param("req") IfaBbApplyCorrectConfirmSql005RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：移管前の裁量配分割当回数(未抽選)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql006RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql006ResponseModel> selectIfaBbApplyCorrectConfirmSql006(
            @Param("req") IfaBbApplyCorrectConfirmSql006RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：預り資産額を取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql007RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql007ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql007ResponseModel> selectIfaBbApplyCorrectConfirmSql007(
            @Param("req") IfaBbApplyCorrectConfirmSql007RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql008
     * SQL名：目論見書閲覧状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql008RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql008ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql008ResponseModel> selectIfaBbApplyCorrectConfirmSql008(
            @Param("req") IfaBbApplyCorrectConfirmSql008RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql009
     * SQL名：銘柄コード存在件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql009RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql009ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql009ResponseModel> selectIfaBbApplyCorrectConfirmSql009(
            @Param("req") IfaBbApplyCorrectConfirmSql009RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql010
     * SQL名：入力データ件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql010RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql010ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql010ResponseModel> selectIfaBbApplyCorrectConfirmSql010(
            @Param("req") IfaBbApplyCorrectConfirmSql010RequestModel req
        ) throws Exception;

    
    /**
     * SQLID：Sql012
     * SQL名：最良配分あるかフラグ取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql012RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql012ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql012ResponseModel> selectIfaBbApplyCorrectConfirmSql012(
            @Param("req") IfaBbApplyCorrectConfirmSql012RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql013
     * SQL名：上限値(売買単位*上限単元数量)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql013RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql013ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql013ResponseModel> selectIfaBbApplyCorrectConfirmSql013(
            @Param("req") IfaBbApplyCorrectConfirmSql013RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql014
     * SQL名：セクション情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectConfirmSql014RequestModel
     * レスポンスクラス：IfaBbApplyCorrectConfirmSql014ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectConfirmSql014ResponseModel> selectIfaBbApplyCorrectConfirmSql014(
            @Param("req") IfaBbApplyCorrectConfirmSql014RequestModel req
        ) throws Exception;
    
    
}

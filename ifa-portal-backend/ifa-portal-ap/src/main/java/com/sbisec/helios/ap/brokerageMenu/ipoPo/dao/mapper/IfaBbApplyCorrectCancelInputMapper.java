package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql001ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql002ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql003ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql004ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql005ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql006ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql007ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql008ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql009ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql010ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql012ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql013ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql014RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql014ResponseModel;





/**
 * 画面ID：SUB0204_02-02_1
 * 画面名：BB申込訂正・取消入力
 *
 * @author BASE李
 2024/04/15 新規作成
 */
@Mapper
public interface IfaBbApplyCorrectCancelInputMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：申込期間内銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql001RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql001ResponseModel> selectIfaBbApplyCorrectCancelInputSql001(
            @Param("req") IfaBbApplyCorrectCancelInputSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：投資家属性のプルダウンリスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql002RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql002ResponseModel> selectIfaBbApplyCorrectCancelInputSql002(
            @Param("req") IfaBbApplyCorrectCancelInputSql002RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql003RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql003ResponseModel> selectIfaBbApplyCorrectCancelInputSql003(
            @Param("req") IfaBbApplyCorrectCancelInputSql003RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：年間裁量配分割当回数情報を取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql004RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql004ResponseModel> selectIfaBbApplyCorrectCancelInputSql004(
            @Param("req") IfaBbApplyCorrectCancelInputSql004RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：裁量配分割当回数(未抽選)情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql005RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql005ResponseModel> selectIfaBbApplyCorrectCancelInputSql005(
            @Param("req") IfaBbApplyCorrectCancelInputSql005RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：移管前の裁量配分割当回数(未抽選)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql006RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql006ResponseModel> selectIfaBbApplyCorrectCancelInputSql006(
            @Param("req") IfaBbApplyCorrectCancelInputSql006RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：預り資産額を取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql007RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql007ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql007ResponseModel> selectIfaBbApplyCorrectCancelInputSql007(
            @Param("req") IfaBbApplyCorrectCancelInputSql007RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql008
     * SQL名：目論見書閲覧状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql008RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql008ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql008ResponseModel> selectIfaBbApplyCorrectCancelInputSql008(
            @Param("req") IfaBbApplyCorrectCancelInputSql008RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql009
     * SQL名：銘柄コード存在件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql009RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql009ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql009ResponseModel> selectIfaBbApplyCorrectCancelInputSql009(
            @Param("req") IfaBbApplyCorrectCancelInputSql009RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql010
     * SQL名：入力データ件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql010RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql010ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql010ResponseModel> selectIfaBbApplyCorrectCancelInputSql010(
            @Param("req") IfaBbApplyCorrectCancelInputSql010RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql012
     * SQL名：最良配分あるかフラグ取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql012RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql012ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql012ResponseModel> selectIfaBbApplyCorrectCancelInputSql012(
            @Param("req") IfaBbApplyCorrectCancelInputSql012RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql013
     * SQL名：上限値(売買単位*上限単元数量)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql013RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql013ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql013ResponseModel> selectIfaBbApplyCorrectCancelInputSql013(
            @Param("req") IfaBbApplyCorrectCancelInputSql013RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql014
     * SQL名：BB申込内容取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql014RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql014ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaBbApplyCorrectCancelInputSql014ResponseModel> selectIfaBbApplyCorrectCancelInputSql014(
            @Param("req") IfaBbApplyCorrectCancelInputSql014RequestModel req
        ) throws Exception;
    
    
    
    
}

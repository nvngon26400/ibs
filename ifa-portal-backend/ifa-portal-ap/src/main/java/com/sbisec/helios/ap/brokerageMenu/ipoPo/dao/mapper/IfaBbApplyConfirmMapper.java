package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql001ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql003ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql004ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql005ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql006ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql009ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql010ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql011ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql012ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql013ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql014RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyConfirmSql014ResponseModel;


/**
 * 画面ID：SUB0204_01-02_2
 * 画面名：BB申込確認
 *
 * @author BASE李
 * 
 2024/02/29 新規作成
 */
@Mapper
public interface IfaBbApplyConfirmMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：申込期間内銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql001RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql001ResponseModel
     *
     * @param req IfaBbApplyConfirmSql001RequestModel
     * @return IfaBbApplyConfirmSql001ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql001ResponseModel> selectIfaBbApplyConfirmSql001(
            @Param("req") IfaBbApplyConfirmSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql003RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql003ResponseModel
     *
     * @param req IfaBbApplyConfirmSql003RequestModel
     * @return IfaBbApplyConfirmSql003ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql003ResponseModel> selectIfaBbApplyConfirmSql003(
            @Param("req") IfaBbApplyConfirmSql003RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：年間裁量配分割当回数情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql004RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql004ResponseModel
     *
     * @param req IfaBbApplyConfirmSql004RequestModel
     * @return IfaBbApplyConfirmSql004ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql004ResponseModel> selectIfaBbApplyConfirmSql004(
            @Param("req") IfaBbApplyConfirmSql004RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：裁量配分割当回数(未抽選)情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql005RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql005ResponseModel
     *
     * @param req IfaBbApplyConfirmSql005RequestModel
     * @return IfaBbApplyConfirmSql005ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql005ResponseModel> selectIfaBbApplyConfirmSql005(
            @Param("req") IfaBbApplyConfirmSql005RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：移管前の裁量配分割当回数(未抽選)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql006RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql006ResponseModel
     *
     * @param req IfaBbApplyConfirmSql006RequestModel
     * @return IfaBbApplyConfirmSql006ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql006ResponseModel> selectIfaBbApplyConfirmSql006(
            @Param("req") IfaBbApplyConfirmSql006RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql009
     * SQL名：銘柄コード存在件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql009RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql009ResponseModel
     *
     * @param req IfaBbApplyConfirmSql009RequestModel
     * @return IfaBbApplyConfirmSql009ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql009ResponseModel> selectIfaBbApplyConfirmSql009(
            @Param("req") IfaBbApplyConfirmSql009RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql010
     * SQL名：入力データ件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql010RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql010ResponseModel
     *
     * @param req IfaBbApplyConfirmSql010RequestModel
     * @return IfaBbApplyConfirmSql010ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql010ResponseModel> selectIfaBbApplyConfirmSql010(
            @Param("req") IfaBbApplyConfirmSql010RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql011
     * SQL名：銘柄プルダウン取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql011RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql011ResponseModel
     *
     * @param req IfaBbApplyConfirmSql011RequestModel
     * @return IfaBbApplyConfirmSql011ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql011ResponseModel> selectIfaBbApplyConfirmSql011(
            @Param("req") IfaBbApplyConfirmSql011RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql012
     * SQL名：最良配分あるかフラグ取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql012RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql012ResponseModel
     *
     * @param req IfaBbApplyConfirmSql012RequestModel
     * @return IfaBbApplyConfirmSql012ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql012ResponseModel> selectIfaBbApplyConfirmSql012(
            @Param("req") IfaBbApplyConfirmSql012RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql013
     * SQL名：上限値(売買単位*上限単元数量)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql013RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql013ResponseModel
     *
     * @param req IfaBbApplyConfirmSql013RequestModel
     * @return IfaBbApplyConfirmSql013ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql013ResponseModel> selectIfaBbApplyConfirmSql013(
            @Param("req") IfaBbApplyConfirmSql013RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql014
     * SQL名：セクション情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyConfirmSql014RequestModel
     * レスポンスクラス：IfaBbApplyConfirmSql014ResponseModel
     *
     * @param req IfaBbApplyConfirmSql014RequestModel
     * @return IfaBbApplyConfirmSql014ResponseModel
     * @exception Exception SQLエラー
     */
    public List<IfaBbApplyConfirmSql014ResponseModel> selectIfaBbApplyConfirmSql014(
            @Param("req") IfaBbApplyConfirmSql014RequestModel req
        ) throws Exception;
    
}

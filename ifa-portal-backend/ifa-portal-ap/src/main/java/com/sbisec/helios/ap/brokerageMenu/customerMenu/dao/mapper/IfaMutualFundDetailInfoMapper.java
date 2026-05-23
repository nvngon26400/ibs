package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_0401-03
 * 画面名：投信詳細情報
 *
 * @author SCSK
 *
 *     2024/04/15 新規作成
 */
@Mapper
public interface IfaMutualFundDetailInfoMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：協会コード取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql001RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql001ResponseModel> selectIfaMutualFundDetailInfoSql001(
            @Param("req") IfaMutualFundDetailInfoSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：投信銘柄当月休場日取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql002RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql002ResponseModel> selectIfaMutualFundDetailInfoSql002(
            @Param("req") IfaMutualFundDetailInfoSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：投信銘柄翌月休場日取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql003RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql003ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql003ResponseModel> selectIfaMutualFundDetailInfoSql003(
            @Param("req") IfaMutualFundDetailInfoSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：締切日（直近）取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql004RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql004ResponseModel
     *
     * @param req リクエストモデル
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql004ResponseModel> selectIfaMutualFundDetailInfoSql004(
            @Param("req") IfaMutualFundDetailInfoSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：締切日（次回）取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql005RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql005ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql005ResponseModel> selectIfaMutualFundDetailInfoSql005(
            @Param("req") IfaMutualFundDetailInfoSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：お知らせ取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql006RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql006ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql006ResponseModel> selectIfaMutualFundDetailInfoSql006(
            @Param("req") IfaMutualFundDetailInfoSql006RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql009
     * SQL名：投信明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql009RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql009ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql009ResponseModel> selectIfaMutualFundDetailInfoSql009(
            @Param("req") IfaMutualFundDetailInfoSql009RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql010
     * SQL名：手数料取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql010RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql010ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql010ResponseModel> selectIfaMutualFundDetailInfoSql010(
            @Param("req") IfaMutualFundDetailInfoSql010RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql011
     * SQL名：扱者別手数料取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql011RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql011ResponseModel
     *
     * @param req リクエストモデル
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql011ResponseModel> selectIfaMutualFundDetailInfoSql011(
            @Param("req") IfaMutualFundDetailInfoSql011RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql012
     * SQL名：積立単位取得
     * SQLタイプ：select
     * リクエストクラス：IfaMutualFundDetailInfoSql012RequestModel
     * レスポンスクラス：IfaMutualFundDetailInfoSql012ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql012ResponseModel> selectIfaMutualFundDetailInfoSql012(
            @Param("req") IfaMutualFundDetailInfoSql012RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql013
     * SQL名：消費税取得
     * SQLタイプ：select
     * レスポンスクラス：IfaMutualFundDetailInfoSql013ResponseModel
     *
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaMutualFundDetailInfoSql013ResponseModel> selectIfaMutualFundDetailInfoSql013() throws Exception;
    
}

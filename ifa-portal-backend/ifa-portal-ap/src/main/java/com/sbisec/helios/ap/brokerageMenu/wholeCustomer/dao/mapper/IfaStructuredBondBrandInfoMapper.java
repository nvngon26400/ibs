package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
 */
@Mapper
public interface IfaStructuredBondBrandInfoMapper {
    
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
    public List<IfaStructuredBondBrandInfoSql001ResponseModel> selectIfaStructuredBondBrandInfoSql001(
            @Param("req") IfaStructuredBondBrandInfoSql001RequestModel req) throws Exception;
    
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
    public List<IfaStructuredBondBrandInfoSql002ResponseModel> selectIfaStructuredBondBrandInfoSql002(
            @Param("req") IfaStructuredBondBrandInfoSql002RequestModel req) throws Exception;
    
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
    public List<IfaStructuredBondBrandInfoSql003ResponseModel> selectIfaStructuredBondBrandInfoSql003(
            @Param("req") IfaStructuredBondBrandInfoSql003RequestModel req) throws Exception;
    
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
    public List<IfaStructuredBondBrandInfoSql004ResponseModel> selectIfaStructuredBondBrandInfoSql004(
            @Param("req") IfaStructuredBondBrandInfoSql004RequestModel req) throws Exception;
    
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
    public List<IfaStructuredBondBrandInfoSql005ResponseModel> selectIfaStructuredBondBrandInfoSql005(
            @Param("req") IfaStructuredBondBrandInfoSql005RequestModel req) throws Exception;
    
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
    public List<IfaStructuredBondBrandInfoSql006ResponseModel> selectIfaStructuredBondBrandInfoSql006(
            @Param("req") IfaStructuredBondBrandInfoSql006RequestModel req) throws Exception;
    
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
    public List<IfaStructuredBondBrandInfoSql007ResponseModel> selectIfaStructuredBondBrandInfoSql007(
            @Param("req") IfaStructuredBondBrandInfoSql007RequestModel req) throws Exception;
    
}

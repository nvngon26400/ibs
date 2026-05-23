package com.sbisec.helios.ap.brokerageMenu.commFee.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaTrustFeeSql001ToSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaTrustFeeSql001ToSql006ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
@Mapper
public interface IfaTrustFeeMapper {
    
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
    public List<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql001(
            @Param("req") IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
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
    public List<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql002(
            @Param("req") IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
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
    public List<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql003(
            @Param("req") IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
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
    public List<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql004(
            @Param("req") IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
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
    public List<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql005(
            @Param("req") IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
    
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
    public List<IfaTrustFeeSql001ToSql006ResponseModel> selectIfaTrustFeeSql006(
            @Param("req") IfaTrustFeeSql001ToSql006RequestModel req) throws Exception;
}

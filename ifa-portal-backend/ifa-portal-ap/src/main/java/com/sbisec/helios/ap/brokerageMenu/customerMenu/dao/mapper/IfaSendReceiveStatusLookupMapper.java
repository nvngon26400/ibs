package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface IfaSendReceiveStatusLookupMapper {
    /**
     * SQLID：Sql001
     * SQL名：書類一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql001RequestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSendReceiveStatusLookupSql001ResponseModel> selectIfaSendReceiveStatusLookupSql001(
            @Param("req") IfaSendReceiveStatusLookupSql001RequestModel req) throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：受発信状況一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql002RequestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSendReceiveStatusLookupSql002ResponseModel> selectIfaSendReceiveStatusLookupSql002(
            @Param("req") IfaSendReceiveStatusLookupSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：書類請求付加情報詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql003RequestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql003ResponseModel
     *
     * @param selSql003Req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSendReceiveStatusLookupSql003ResponseModel> selectIfaSendReceiveStatusLookupSql003(
            @Param("req") IfaSendReceiveStatusLookupSql003RequestModel selSql003Req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：書類不備付加情報リス取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql004RequestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaSendReceiveStatusLookupSql004ResponseModel> selectIfaSendReceiveStatusLookupSql004(
            @Param("req") IfaSendReceiveStatusLookupSql004RequestModel req) throws Exception;
}

   
package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql011RequestModel;

/**
 * SQLマッパーのインターフェース
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Mapper
public interface IfaContactCorrectMapper {

    /**
     * SQLID：Sql003
     * SQL名：問合せ情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactCorrectSql003ResponseModel
     * レスポンスクラス：IfaContactCorrectSql003RequestModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactCorrectSql003ResponseModel> selectIfaContactCorrectSql003(
            @Param("req") IfaContactCorrectSql003RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql004
     * SQL名:IFA問合せテーブルへの更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactCorrectSql004RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactCorrectSql004(
            @Param("req") IfaContactCorrectSql004RequestModel x_req) throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：IFA回答テーブルへの取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactCorrectSql005ResponseModel
     * レスポンスクラス：IfaContactCorrectSql005RequestModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactCorrectSql005ResponseModel> selectIfaContactCorrectSql005(
            @Param("req") IfaContactCorrectSql005RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql006
     * SQL名:IFA回答テーブルへの更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactCorrectSql006RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactCorrectSql006(
            @Param("req") IfaContactCorrectSql006RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql008
     * SQL名:IFA問合せ・回答エラーテーブル登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactCorrectSql008RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactCorrectSql008(
            @Param("req") IfaContactCorrectSql008RequestModel x_req) throws Exception;
    
    /**
     * SQLID:Sql009
     * SQL名:回答情報登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactCorrectSql009RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactCorrectSql009(
            @Param("req") IfaContactCorrectSql009RequestModel x_req) throws Exception;
    
    /**
     * SQLID:Sql010
     * SQL名:回答NO更新
     * SQLタイプ:insert
     * リクエストクラス：IfaContactCorrectSql010RequestModel
     *
     * @param x_req リクエスト
     * @return int 更新件数件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactCorrectSql010(
            @Param("req") IfaContactCorrectSql010RequestModel x_req) throws Exception;
    
    /**
     * SQLID:Sql011
     * SQL名:IFA接触履歴修正履歴テーブル登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactCorrectSql011RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactCorrectSql011(
            @Param("req") IfaContactCorrectSql011RequestModel x_req) throws Exception;
}

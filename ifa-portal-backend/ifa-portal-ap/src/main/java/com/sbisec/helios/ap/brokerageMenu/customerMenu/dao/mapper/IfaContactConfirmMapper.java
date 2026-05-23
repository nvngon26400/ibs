package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql011RequestModel;

/**
 * SQLマッパーのインターフェース
 * 画面ID:SUB0202_0106-04
 * 画面名:接触履歴入力確認
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Mapper
public interface IfaContactConfirmMapper {

    /**
     * SQLID:Sql001
     * SQL名:問合せカテゴリリスト取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactConfirmSql001ResponseModel
     * レスポンスクラス:IfaContactConfirmSql001RequestModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactConfirmSql001ResponseModel> selectIfaContactConfirmSql001(
            @Param("req") IfaContactConfirmSql001RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql002
     * SQL名:問合せ情報登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactConfirmSql002RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactConfirmSql002(
            @Param("req") IfaContactConfirmSql002RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql003
     * SQL名:問合せ情報更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactConfirmSql003RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactConfirmSql003(
            @Param("req") IfaContactConfirmSql003RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql004
     * SQL名:回答情報登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactConfirmSql004RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactConfirmSql004(
            @Param("req") IfaContactConfirmSql004RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql005
     * SQL名:問合せNO更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactConfirmSql005RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactConfirmSql005(
            @Param("req") IfaContactConfirmSql005RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql006
     * SQL名:回答NO更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactConfirmSql006RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactConfirmSql006(
            @Param("req") IfaContactConfirmSql006RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql008
     * SQL名:問合せ情報の取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactConfirmSql008ResponseModel
     * レスポンスクラス:IfaContactConfirmSql008RequestModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactConfirmSql008ResponseModel> selectIfaContactConfirmSql008(
            @Param("req") IfaContactConfirmSql008RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql009
     * SQL名:IFA問合せ・回答エラーテーブル登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactConfirmSql009RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactConfirmSql009(
            @Param("req") IfaContactConfirmSql009RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql010
     * SQL名:登録グループ取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactConfirmSql010ResponseModel
     * レスポンスクラス:IfaContactConfirmSql010RequestModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactConfirmSql010ResponseModel> selectIfaContactConfirmSql010(
            @Param("req") IfaContactConfirmSql010RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql011
     * SQL名:IFA接触履歴修正履歴テーブル登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactConfirmSql011RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactConfirmSql011(
            @Param("req") IfaContactConfirmSql011RequestModel x_req) throws Exception;
}

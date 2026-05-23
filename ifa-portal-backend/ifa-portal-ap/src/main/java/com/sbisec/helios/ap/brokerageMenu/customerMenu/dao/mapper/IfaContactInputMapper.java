package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql005ResponseModel;

/**
 * SQLマッパーのインターフェース
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Mapper
public interface IfaContactInputMapper {

    /**
     * SQLID:Sql001
     * SQL名:問合せカテゴリリスト（大）取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql001RequestModel
     * レスポンスクラス:IfaContactInputSql001ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactInputSql001ResponseModel> selectIfaContactInputSql001(
            @Param("req") IfaContactInputSql001RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql002
     * SQL名:問合せ情報取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql002RequestModel
     * レスポンスクラス:IfaContactInputSql002ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactInputSql002ResponseModel> selectIfaContactInputSql002(
            @Param("req") IfaContactInputSql002RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql003
     * SQL名:問合せカテゴリリスト（中）取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql003RequestModel
     * レスポンスクラス:IfaContactInputSql003ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactInputSql003ResponseModel> selectIfaContactInputSql003(
            @Param("req") IfaContactInputSql003RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql004
     * SQL名:問合せカテゴリリスト（小）取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql004RequestModel
     * レスポンスクラス:IfaContactInputSql004ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactInputSql004ResponseModel> selectIfaContactInputSql004(
            @Param("req") IfaContactInputSql004RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql005
     * SQL名:回答情報取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql005RequestModel
     * レスポンスクラス:IfaContactInputSql005ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactInputSql005ResponseModel> selectIfaContactInputSql005(
            @Param("req") IfaContactInputSql005RequestModel x_req) throws Exception;
}

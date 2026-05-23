package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql003RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql003ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql004RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql004ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql005RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql005ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql006RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql006ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql007RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql008RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql009RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql010RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql011RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql011ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql012RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql013RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdUpdateRegisterSql013ResponseModel;

/**
 * 画面ID：SUB0601_01-03_1
 * 画面名：ログインID更新登録
 * @author <author-name>
 * 
 * 2023/11/06 新規作成
 *
 */
public interface IfaLoginIdUpdateRegisterDao {
    
    /**
     * SQLID：Sql001
     * SQL名：メニュー一覧取得（ユーザID）
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdUpdateRegisterSql001RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterSql001ResponseModel> selectIfaLoginIdUpdateRegisterSql001(
            IfaLoginIdUpdateRegisterSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：本支名dropDownList取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdUpdateRegisterSql003RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterSql003ResponseModel> selectIfaLoginIdUpdateRegisterSql003(
            IfaLoginIdUpdateRegisterSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：仲介業者名dropDownList取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdUpdateRegisterSql004RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterSql004ResponseModel> selectIfaLoginIdUpdateRegisterSql004(
            IfaLoginIdUpdateRegisterSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：仲介業者支店名取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdUpdateRegisterSql005RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterSql005ResponseModel> selectIfaLoginIdUpdateRegisterSql005(
            IfaLoginIdUpdateRegisterSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：担当者名dropDownList取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdUpdateRegisterSql006RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql006ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterSql006ResponseModel> selectIfaLoginIdUpdateRegisterSql006(
            IfaLoginIdUpdateRegisterSql006RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：認証ユーザー情報更新
     * SQLタイプ：update
     * リクエストクラス：IfaLoginIdUpdateRegisterSql007RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql007ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaLoginIdUpdateRegisterSql007(IfaLoginIdUpdateRegisterSql007RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql008
     * SQL名：ユーザ情報更新
     * SQLタイプ：update
     * リクエストクラス：IfaLoginIdUpdateRegisterSql008RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql008ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaLoginIdUpdateRegisterSql008(IfaLoginIdUpdateRegisterSql008RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql009
     * SQL名：Cordysユーザーとメニューマッピング情報_新テーブル削除
     * SQLタイプ：delete
     * リクエストクラス：IfaLoginIdUpdateRegisterSql009RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql009ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginIdUpdateRegisterSql009(IfaLoginIdUpdateRegisterSql009RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql010
     * SQL名：Cordysユーザーとメニューマッピング情報_新テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：IfaLoginIdUpdateRegisterSql010RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql010ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaLoginIdUpdateRegisterSql010(IfaLoginIdUpdateRegisterSql010RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql011
     * SQL名：Horusユーザー情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdUpdateRegisterSql011RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql011ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterSql011ResponseModel> selectIfaLoginIdUpdateRegisterSql011(
            IfaLoginIdUpdateRegisterSql011RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql012
     * SQL名：CCS情報リセット
     * SQLタイプ：update
     * リクエストクラス：IfaLoginIdUpdateRegisterSql012RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql012ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaLoginIdUpdateRegisterSql012(IfaLoginIdUpdateRegisterSql012RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql013
     * SQL名：メニュー一覧取得（権限毎）
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdUpdateRegisterSql013RequestModel
     * レスポンスクラス：IfaLoginIdUpdateRegisterSql013ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdUpdateRegisterSql013ResponseModel> selectIfaLoginIdUpdateRegisterSql013(
            IfaLoginIdUpdateRegisterSql013RequestModel req) throws Exception;
    
}

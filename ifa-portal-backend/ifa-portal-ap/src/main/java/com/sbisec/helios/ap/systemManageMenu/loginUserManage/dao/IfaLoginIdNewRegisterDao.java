package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql002RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql002ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql004RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql004ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql005RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql005ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql006RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql006ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql007RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql007ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql008RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql009RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql010RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql011RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql011ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql012RequestModel;


/**
 * 画面ID：SUB0601_01-02_1
 * 画面名：ログインID新規登録
 * @author 布施佑太
 * 
 * 2023/11/09 新規作成
 *
 */
public interface IfaLoginIdNewRegisterDao {
    
    /**
     * SQLID：Sql002
     * SQL名：メニュー一覧取得（権限毎）
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql002RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql002ResponseModel> selectIfaLoginIdNewRegisterSql002(IfaLoginIdNewRegisterSql002RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：本支名dropDownList取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql004RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql004ResponseModel> selectIfaLoginIdNewRegisterSql004(IfaLoginIdNewRegisterSql004RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：仲介業者名dropDownList取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql005RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql005ResponseModel> selectIfaLoginIdNewRegisterSql005(IfaLoginIdNewRegisterSql005RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：仲介業者支店名取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql006RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql006ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql006ResponseModel> selectIfaLoginIdNewRegisterSql006(IfaLoginIdNewRegisterSql006RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：担当者名dropDownList取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql007RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql007ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql007ResponseModel> selectIfaLoginIdNewRegisterSql007(IfaLoginIdNewRegisterSql007RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql011
     * SQL名：Horusユーザー情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql011RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql011ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql011ResponseModel> selectIfaLoginIdNewRegisterSql011(IfaLoginIdNewRegisterSql011RequestModel req)
            throws Exception;
    
    
    
    /**
     * SQLID：Sql008
     * SQL名：ユーザ情報登録
     * SQLタイプ：insert
     * リクエストクラス：IfaLoginIdNewRegisterSql008RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql008ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaLoginIdNewRegisterSql008(IfaLoginIdNewRegisterSql008RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql010
     * SQL名：Cordysユーザーとメニューマッピング情報_新テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：IfaLoginIdNewRegisterSql010RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql010ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaLoginIdNewRegisterSql010(IfaLoginIdNewRegisterSql010RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql012
     * SQL名：認証ユーザー情報登録
     * SQLタイプ：insert
     * リクエストクラス：IfaLoginIdNewRegisterSql012RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql012ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaLoginIdNewRegisterSql012(IfaLoginIdNewRegisterSql012RequestModel req)
            throws Exception;
    
    
    /**
     * SQLID：Sql009
     * SQL名：Cordysユーザーとメニューマッピング情報_新テーブル削除
     * SQLタイプ：delete
     * リクエストクラス：IfaLoginIdNewRegisterSql009RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql009ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginIdNewRegisterSql009(IfaLoginIdNewRegisterSql009RequestModel req)
            throws Exception;
    
}

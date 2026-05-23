package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql001RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql001ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql002RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql002ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql003RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql004RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql005RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql006RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql007RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginUserManageManagerLookupSql007ResponseModel;

/**
 * 画面ID：SUB0601_01-01
 * 画面名：ログイン者管理（管理者用）照会
 * @author <author-name>
 * 
 * 2023/11/02 新規作成
 *
 */
public interface IfaLoginUserManageManagerLookupDao {
    
    /**
     * SQLID：Sql001
     * SQL名：ユーザ情報検索
     * SQLタイプ：select
     * リクエストクラス：IfaLoginUserManageManagerLookupSql001RequestModel
     * レスポンスクラス：IfaLoginUserManageManagerLookupSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginUserManageManagerLookupSql001ResponseModel> selectIfaLoginUserManageManagerLookupSql001(
            IfaLoginUserManageManagerLookupSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：Horusユーザー権限情報の存在チェック
     * SQLタイプ：select
     * リクエストクラス：IfaLoginUserManageManagerLookupSql002RequestModel
     * レスポンスクラス：IfaLoginUserManageManagerLookupSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginUserManageManagerLookupSql002ResponseModel> selectIfaLoginUserManageManagerLookupSql002(
            IfaLoginUserManageManagerLookupSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：Cordysユーザーとメニューマッピング情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaLoginUserManageManagerLookupSql003RequestModel
     * レスポンスクラス：IfaLoginUserManageManagerLookupSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginUserManageManagerLookupSql003(IfaLoginUserManageManagerLookupSql003RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：Horusユーザー情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaLoginUserManageManagerLookupSql004RequestModel
     * レスポンスクラス：IfaLoginUserManageManagerLookupSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginUserManageManagerLookupSql004(IfaLoginUserManageManagerLookupSql004RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：認証ユーザー情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaLoginUserManageManagerLookupSql005RequestModel
     * レスポンスクラス：IfaLoginUserManageManagerLookupSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginUserManageManagerLookupSql005(IfaLoginUserManageManagerLookupSql005RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：Horusユーザー権限情報の削除
     * SQLタイプ：delete
     * リクエストクラス：IfaLoginUserManageManagerLookupSql006RequestModel
     * レスポンスクラス：IfaLoginUserManageManagerLookupSql006ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginUserManageManagerLookupSql006(IfaLoginUserManageManagerLookupSql006RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：メニューリスト検索
     * SQLタイプ：select
     * リクエストクラス：IfaLoginUserManageManagerLookupSql007RequestModel
     * レスポンスクラス：IfaLoginUserManageManagerLookupSql007ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginUserManageManagerLookupSql007ResponseModel> selectIfaLoginUserManageManagerLookupSql007(
            IfaLoginUserManageManagerLookupSql007RequestModel req) throws Exception;
    
}

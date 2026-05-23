package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.mapper;

import java.util.List;

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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 画面ID：SUB0601_01-01
 * 画面名：ログイン者管理（管理者用）照会
 * @author <author-name>
 *
 * 2023/11/02 新規作成
 */
@Mapper
public interface IfaLoginUserManageManagerLookupMapper {
    
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
    public List<IfaLoginUserManageManagerLookupSql001ResponseModel> selectIfaLoginUserManageManagerLookupSql001(
            @Param("req") IfaLoginUserManageManagerLookupSql001RequestModel req) throws Exception;
    
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
    public List<IfaLoginUserManageManagerLookupSql002ResponseModel> selectIfaLoginUserManageManagerLookupSql002(
            @Param("req") IfaLoginUserManageManagerLookupSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：Cordysユーザーとメニューマッピング情報削除
     * SQLタイプ：delete
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginUserManageManagerLookupSql003(
            @Param("req") IfaLoginUserManageManagerLookupSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：Horusユーザー情報削除
     * SQLタイプ：delete
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginUserManageManagerLookupSql004(
            @Param("req") IfaLoginUserManageManagerLookupSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：認証ユーザー情報削除
     * SQLタイプ：delete
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginUserManageManagerLookupSql005(
            @Param("req") IfaLoginUserManageManagerLookupSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：Horusユーザー権限情報の削除
     * SQLタイプ：delete
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginUserManageManagerLookupSql006(
            @Param("req") IfaLoginUserManageManagerLookupSql006RequestModel req) throws Exception;
    
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
    public List<IfaLoginUserManageManagerLookupSql007ResponseModel> selectIfaLoginUserManageManagerLookupSql007(
            @Param("req") IfaLoginUserManageManagerLookupSql007RequestModel req) throws Exception;
}

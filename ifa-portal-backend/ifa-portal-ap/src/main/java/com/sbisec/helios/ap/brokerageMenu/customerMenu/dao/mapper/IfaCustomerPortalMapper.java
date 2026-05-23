package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003InsertRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectExclusiveResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectExclusiveResquestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003UpdateRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSqlStbRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSqlStbResponseModel;

/**
 * 
 * 画面ID：SUB0202_00-02
 * 画面名：顧客ポータル_顧客情報
 * @author <author-name>
 *
 * 2023/11/30 新規作成
 */
@Mapper
public interface IfaCustomerPortalMapper {
    
    /**
     * SQLID：Sql002
     * SQL名：メモ(IFA専用)取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql002RequestModel
     * レスポンスクラス：IfaCustomerPortalSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSql002ResponseModel> selectIfaCustomerPortalSql002(
            @Param("req") IfaCustomerPortalSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：CCS顧客メモ情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql004RequestModel
     * レスポンスクラス：IfaCustomerPortalSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSql004ResponseModel> selectIfaCustomerPortalSql004(
            @Param("req") IfaCustomerPortalSql004RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：コース名情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql005RequestModel
     * レスポンスクラス：IfaCustomerPortalSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSql005ResponseModel> selectIfaCustomerPortalSql005(
            @Param("req") IfaCustomerPortalSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：メモ(IFA専用)取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql003SelectRequestModel
     * レスポンスクラス：IfaCustomerPortalSql003SelectResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSql003SelectResponseModel> selectIfaCustomerPortalSql003(
            @Param("req") IfaCustomerPortalSql003SelectRequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：メモ(IFA専用)取得(排他制御)
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql003SelectExclusiveResquestModel
     * レスポンスクラス：IfaCustomerPortalSql003SelectExclusiveResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSql003SelectExclusiveResponseModel> selectIfaCustomerPortalSql003Exclusive(
            @Param("req") IfaCustomerPortalSql003SelectExclusiveResquestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：メモ(IFA専用)取得
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaCustomerPortalSql003(@Param("req") IfaCustomerPortalSql003InsertRequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：メモ(IFA専用)取得
     * SQLタイプ：update
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaCustomerPortalSql003(@Param("req") IfaCustomerPortalSql003UpdateRequestModel req) throws Exception;
    
    /**
     * SQLID：SqlStb
     * SQL名：コース名情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSqlStbRequestModel
     * レスポンスクラス：IfaCustomerPortalSqlStbResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSqlStbResponseModel> selectIfaCustomerPortalSqlStb(
            @Param("req") IfaCustomerPortalSqlStbRequestModel req) throws Exception;
    
    /**
     * SQLID:Sql007
     * SQL名：仲介業者顧客口座属性情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql007RequestModel
     * レスポンスクラス：IfaCustomerPortalSql007ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSql007ResponseModel> selectIfaCustomerPortalSql007(
        @Param("req") IfaCustomerPortalSql007RequestModel req) throws Exception;
    
    /**
     * SQLID:Sql008
     * SQL名：顧客口座属性情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql008RequestModel
     * レスポンスクラス：IfaCustomerPortalSql008ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSql008ResponseModel> selectIfaCustomerPortalSql008(
        @Param("req") IfaCustomerPortalSql008RequestModel req) throws Exception;
    
    /**
     * SQLID:Sql009
     * SQL名：仲介業者顧客口座属性情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql009RequestModel
     * レスポンスクラス：IfaCustomerPortalSql009ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSql009ResponseModel> selectIfaCustomerPortalSql009(
        @Param("req") IfaCustomerPortalSql009RequestModel req) throws Exception;
    
    /**
     * SQLID:Sql010
     * SQL名：EC顧客マスタビュー情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql010RequestModel
     * レスポンスクラス：IfaCustomerPortalSql010ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaCustomerPortalSql010ResponseModel> selectIfaCustomerPortalSql010(
        @Param("req") IfaCustomerPortalSql010RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql011
     * SQL名：店群情報取得
     * SQLタイプ：select
     *
     * @param req リクエスト
     * @return String レスポンス
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public String selectIfaCustomerPortalSql011(String req) throws Exception;
}

package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql005ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportViewStatusLookupManagerSql007RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 
 * 画面ID：SUB0505_02-01
 * 画面名：コンプライアンス通信閲覧状況照会（管理者用）
 * @author <author-name>
 *
 * 2023/12/27 新規作成
 */
@Mapper
public interface IfaComplianceReportViewStatusLookupManagerMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：タイトル取得
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportViewStatusLookupManagerSql001RequestModel
     * レスポンスクラス：IfaComplianceReportViewStatusLookupManagerSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaComplianceReportViewStatusLookupManagerSql001ResponseModel> selectIfaComplianceReportViewStatusLookupManagerSql001(
        @Param("req") IfaComplianceReportViewStatusLookupManagerSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：コンプライアンス通信の閲覧状況取得
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportViewStatusLookupManagerSql002RequestModel
     * レスポンスクラス：IfaComplianceReportViewStatusLookupManagerSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaComplianceReportViewStatusLookupManagerSql002ResponseModel> selectIfaComplianceReportViewStatusLookupManagerSql002(
        @Param("req") IfaComplianceReportViewStatusLookupManagerSql002RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql003
     * SQL名：コンプライアンス通信の閲覧不要登録
     * SQLタイプ：insert
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaComplianceReportViewStatusLookupManagerSql003(
        @Param("req")  IfaComplianceReportViewStatusLookupManagerSql003RequestModel req
        ) throws Exception;
    
    
    
    /**
     * SQLID：Sql004
     * SQL名：コンプライアンス通信の閲覧不要登録解除
     * SQLタイプ：delete
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaComplianceReportViewStatusLookupManagerSql004(
        @Param("req")  IfaComplianceReportViewStatusLookupManagerSql004RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql005
     * SQL名：コンプライアンス通信閲覧状況のデータ量取得
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportViewStatusLookupManagerSql004RequestModel
     * レスポンスクラス：IfaComplianceReportViewStatusLookupManagerSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public List<IfaComplianceReportViewStatusLookupManagerSql005ResponseModel> selectIfaComplianceReportViewStatusLookupManagerSql005(
            @Param("req") IfaComplianceReportViewStatusLookupManagerSql005RequestModel req
            ) throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：コンプライアンス通信の閲覧報告
     * SQLタイプ：insert/update
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaComplianceReportViewStatusLookupManagerSql006(
        @Param("req")  IfaComplianceReportViewStatusLookupManagerSql006RequestModel req
        ) throws Exception;

     /**
     * SQLID：Sql007
     * SQL名：コンプライアンス通信の閲覧報告解除
     * SQLタイプ：delete
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaComplianceReportViewStatusLookupManagerSql007(
        @Param("req")  IfaComplianceReportViewStatusLookupManagerSql007RequestModel req
            ) throws Exception;
    
}

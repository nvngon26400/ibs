package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao;

import com.sbibits.earth.model.DataList;
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

/**
 * 画面ID：SUB0505_02-01
 * 画面名：コンプライアンス通信閲覧状況照会（管理者用）
 * @author <author-name>
 * 
 * 2023/12/27 新規作成
 *
 */
public interface IfaComplianceReportViewStatusLookupManagerDao {
    
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
    public DataList<IfaComplianceReportViewStatusLookupManagerSql001ResponseModel> selectIfaComplianceReportViewStatusLookupManagerSql001(IfaComplianceReportViewStatusLookupManagerSql001RequestModel req)
            throws Exception;
    
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
    public DataList<IfaComplianceReportViewStatusLookupManagerSql002ResponseModel> selectIfaComplianceReportViewStatusLookupManagerSql002(IfaComplianceReportViewStatusLookupManagerSql002RequestModel req)
            throws Exception;
    
    
    
    /**
     * SQLID：Sql003
     * SQL名：コンプライアンス通信の閲覧不要登録
     * SQLタイプ：insert
     * リクエストクラス：IfaComplianceReportViewStatusLookupManagerSql003RequestModel
     * レスポンスクラス：IfaComplianceReportViewStatusLookupManagerSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaComplianceReportViewStatusLookupManagerSql003(IfaComplianceReportViewStatusLookupManagerSql003RequestModel req)
            throws Exception;
    
    
    /**
     * SQLID：Sql004
     * SQL名：コンプライアンス通信の閲覧不要登録解除
     * SQLタイプ：delete
     * リクエストクラス：IfaComplianceReportViewStatusLookupManagerSql004RequestModel
     * レスポンスクラス：IfaComplianceReportViewStatusLookupManagerSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaComplianceReportViewStatusLookupManagerSql004(IfaComplianceReportViewStatusLookupManagerSql004RequestModel req)
            throws Exception;
    
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
    public DataList<IfaComplianceReportViewStatusLookupManagerSql005ResponseModel> selectIfaComplianceReportViewStatusLookupManagerSql005(IfaComplianceReportViewStatusLookupManagerSql005RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：コンプライアンス通信の閲覧報告
     * SQLタイプ：insert/update
     * リクエストクラス：IfaComplianceReportViewStatusLookupManagerSql006RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaComplianceReportViewStatusLookupManagerSql006(IfaComplianceReportViewStatusLookupManagerSql006RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：コンプライアンス通信の閲覧報告解除
     * SQLタイプ：delete
     * リクエストクラス：IfaComplianceReportViewStatusLookupManagerSql007RequestModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaComplianceReportViewStatusLookupManagerSql007(IfaComplianceReportViewStatusLookupManagerSql007RequestModel req)
            throws Exception;
}

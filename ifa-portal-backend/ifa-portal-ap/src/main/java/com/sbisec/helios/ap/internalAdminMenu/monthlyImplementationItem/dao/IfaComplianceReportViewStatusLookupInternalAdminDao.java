package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel;





/**
 * 画面ID：SUB0401_01-01
 * 画面名：コンプライアンス通信閲覧状況照会(内部管理責任者用)
 * @author <author-name>
 * 
 * 2023/08/04 新規作成
 *
 */
public interface IfaComplianceReportViewStatusLookupInternalAdminDao {
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel
     * レスポンスクラス：IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel> selectIfaComplianceReportViewStatusLookupInternalAdminSql001(IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel
     * レスポンスクラス：IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel> selectIfaComplianceReportViewStatusLookupInternalAdminSql002(IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel req)
            throws Exception;
    
    
    
    
}

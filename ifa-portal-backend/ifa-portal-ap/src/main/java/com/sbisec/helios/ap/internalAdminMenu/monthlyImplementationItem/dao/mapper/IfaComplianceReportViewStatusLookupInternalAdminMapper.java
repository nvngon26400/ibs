package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;







/**
 * 
 * 画面ID：SUB0401_01-01
 * 画面名：コンプライアンス通信閲覧状況照会(内部管理責任者用)
 * @author <author-name>
 *
 * 2023/08/04 新規作成
 */
@Mapper
public interface IfaComplianceReportViewStatusLookupInternalAdminMapper {
    
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
    public List<IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel> selectIfaComplianceReportViewStatusLookupInternalAdminSql001(
        @Param("req") IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel req
        ) throws Exception;
    
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
    public List<IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel> selectIfaComplianceReportViewStatusLookupInternalAdminSql002(
        @Param("req") IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel req
        ) throws Exception;
    
    
    
    
}

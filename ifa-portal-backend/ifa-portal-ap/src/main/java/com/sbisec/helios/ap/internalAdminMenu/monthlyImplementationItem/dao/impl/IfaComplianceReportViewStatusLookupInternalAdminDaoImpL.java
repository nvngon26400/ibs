package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.IfaComplianceReportViewStatusLookupInternalAdminDao;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.mapper.IfaComplianceReportViewStatusLookupInternalAdminMapper;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;







/**
 * 画面ID：SUB0401_01-01
 * 画面名：コンプライアンス通信閲覧状況照会(内部管理責任者用)
 * @author <author-name>
 *
 * 2023/08/04 新規作成
 */
@Component
public class IfaComplianceReportViewStatusLookupInternalAdminDaoImpL extends RowSelectableDao implements IfaComplianceReportViewStatusLookupInternalAdminDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaComplianceReportViewStatusLookupInternalAdminDaoImpL.class);
    @Autowired
    private IfaComplianceReportViewStatusLookupInternalAdminMapper mapper;
    
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
            throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel> res = new DataList<IfaComplianceReportViewStatusLookupInternalAdminSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaComplianceReportViewStatusLookupInternalAdminSql001(req));
        return res;
    }
    
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
            throws Exception {
        
        DataList<IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel> res = new DataList<IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaComplianceReportViewStatusLookupInternalAdminSql002(req));
        return res;
    }
    
    
    
    
}

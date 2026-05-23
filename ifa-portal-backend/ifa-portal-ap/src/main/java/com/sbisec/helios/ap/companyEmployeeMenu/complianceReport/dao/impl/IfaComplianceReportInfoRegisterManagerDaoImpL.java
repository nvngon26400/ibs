package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.IfaComplianceReportInfoRegisterManagerDao;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.mapper.IfaComplianceReportInfoRegisterManagerMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql004RequestModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;





/**
 * 画面ID：SUB0505_01-01
 * 画面名：コンプライアンス通信情報登録（管理者用）
 * @author <author-name>
 *
 * 2023/12/12 新規作成
 */
@Component
public class IfaComplianceReportInfoRegisterManagerDaoImpL extends RowSelectableDao implements IfaComplianceReportInfoRegisterManagerDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaComplianceReportInfoRegisterManagerDaoImpL.class);
    @Autowired
    private IfaComplianceReportInfoRegisterManagerMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：コンプライアンス通信情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportInfoRegisterManagerSql001RequestModel
     * レスポンスクラス：IfaComplianceReportInfoRegisterManagerSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportInfoRegisterManagerSql001ResponseModel> selectIfaComplianceReportInfoRegisterManagerSql001(IfaComplianceReportInfoRegisterManagerSql001RequestModel req)
            throws Exception {
        
        DataList<IfaComplianceReportInfoRegisterManagerSql001ResponseModel> res = new DataList<IfaComplianceReportInfoRegisterManagerSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaComplianceReportInfoRegisterManagerSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：コンプライアンス通信状態変更
     * SQLタイプ：update
     * リクエストクラス：IfaComplianceReportInfoRegisterManagerSql002RequestModel
     * レスポンスクラス：IfaComplianceReportInfoRegisterManagerSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaComplianceReportInfoRegisterManagerSql002(IfaComplianceReportInfoRegisterManagerSql002RequestModel req)
            throws Exception {

        return mapper.updateIfaComplianceReportInfoRegisterManagerSql002(req);
    }
    

    /**
     * SQLID：Sql003
     * SQL名：ファイルディレクトリパス取得
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportInfoRegisterManagerSql003RequestModel
     * レスポンスクラス：IfaComplianceReportInfoRegisterManagerSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportInfoRegisterManagerSql003ResponseModel> selectIfaComplianceReportInfoRegisterManagerSql003(IfaComplianceReportInfoRegisterManagerSql003RequestModel req)
            throws Exception {
        
        DataList<IfaComplianceReportInfoRegisterManagerSql003ResponseModel> res = new DataList<IfaComplianceReportInfoRegisterManagerSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaComplianceReportInfoRegisterManagerSql003(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql004
     * SQL名：コンプライアンス通信テーブル情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaComplianceReportInfoRegisterManagerSql004RequestModel
     * レスポンスクラス：IfaComplianceReportInfoRegisterManagerSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaComplianceReportInfoRegisterManagerSql004(IfaComplianceReportInfoRegisterManagerSql004RequestModel req)
            throws Exception {

        return mapper.deleteIfaComplianceReportInfoRegisterManagerSql004(req);
    }
}

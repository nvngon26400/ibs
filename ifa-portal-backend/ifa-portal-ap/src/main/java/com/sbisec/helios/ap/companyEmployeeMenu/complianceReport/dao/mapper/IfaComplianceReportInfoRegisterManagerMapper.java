package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql004RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;





/**
 * 
 * 画面ID：SUB0505_01-01
 * 画面名：コンプライアンス通信情報登録（管理者用）
 * @author <author-name>
 *
 * 2023/12/12 新規作成
 */
@Mapper
public interface IfaComplianceReportInfoRegisterManagerMapper {
    
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
    public List<IfaComplianceReportInfoRegisterManagerSql001ResponseModel> selectIfaComplianceReportInfoRegisterManagerSql001(
        @Param("req") IfaComplianceReportInfoRegisterManagerSql001RequestModel req
        ) throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：コンプライアンス通信状態変更
     * SQLタイプ：update
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaComplianceReportInfoRegisterManagerSql002(
        @Param("req")  IfaComplianceReportInfoRegisterManagerSql002RequestModel req
        ) throws Exception;
    
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
    public List<IfaComplianceReportInfoRegisterManagerSql003ResponseModel> selectIfaComplianceReportInfoRegisterManagerSql003(
        @Param("req") IfaComplianceReportInfoRegisterManagerSql003RequestModel req
        ) throws Exception;
    
    
    
    
    /**
     * SQLID：Sql004
     * SQL名：コンプライアンス通信テーブル情報削除
     * SQLタイプ：delete
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaComplianceReportInfoRegisterManagerSql004(
        @Param("req")  IfaComplianceReportInfoRegisterManagerSql004RequestModel req
        ) throws Exception;
}

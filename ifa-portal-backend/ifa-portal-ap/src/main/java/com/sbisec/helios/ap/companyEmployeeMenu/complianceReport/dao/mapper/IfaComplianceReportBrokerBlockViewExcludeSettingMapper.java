package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel;

/**
 * 画面ID：SUB0505_03-01
 * 画面名：コンプライアンス通信仲介業者一括閲覧不要設定
 * 2023/11/08 新規作成
 *
 * @author SCSK 江口
 *
 */
@Mapper
public interface IfaComplianceReportBrokerBlockViewExcludeSettingMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：コンプライアンス通信の閲覧要否管理情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel
     * レスポンスクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return コンプライアンス通信閲覧要否一覧
     * @exception Exception MyBathis関連のException
     */
    public List<IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel> selectIfaComplianceReportBrokerBlockViewExcludeSettingSql001(
            @Param("req") IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：コンプライアンス通信の閲覧要否管理情報登録
     * SQLタイプ：insert
     *
     * @param req 登録情報
     * @return SUCCESS：1以上、FAIL：0
     * @exception Exception MyBathis関連のException
     */
    public int insertIfaComplianceReportBrokerBlockViewExcludeSettingSql002(
            @Param("req") IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：コンプライアンス通信の閲覧要否管理情報削除
     * SQLタイプ：delete
     *
     * @param req 削除対象指定
     * @return SUCCESS：1以上、FAIL：0
     * @exception Exception MyBathis関連のException
     */
    public int deleteIfaComplianceReportBrokerBlockViewExcludeSettingSql003(
            @Param("req") IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel req) throws Exception;
    
}

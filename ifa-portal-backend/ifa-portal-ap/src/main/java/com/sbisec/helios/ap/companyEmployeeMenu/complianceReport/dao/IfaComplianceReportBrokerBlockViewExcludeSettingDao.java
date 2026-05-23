package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao;

import com.sbibits.earth.model.DataList;
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
public interface IfaComplianceReportBrokerBlockViewExcludeSettingDao {
    
    /**
     * SQLID：Sql001
     * SQL名：コンプライアンス通信の閲覧要否管理情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel
     * レスポンスクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return コンプライアンス通信要否管理情報一覧
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel> selectIfaComplianceReportBrokerBlockViewExcludeSettingSql001(
            IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：コンプライアンス通信の閲覧要否管理情報登録
     * SQLタイプ：insert
     * リクエストクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel
     * レスポンスクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql002ResponseModel
     *
     * @param req 登録するコンプライアンス通信閲覧要否情報
     * @return SUCCESS:0以上、FAIL：0
     * @exception Exception SQLExceptionなど
     */
    public int insertIfaComplianceReportBrokerBlockViewExcludeSettingSql002(
            IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：コンプライアンス通信の閲覧要否管理情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel
     * レスポンスクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql003ResponseModel
     *
     * @param req 登録するコンプライアンス通信閲覧要否情報
     * @return SUCCESS:0以上、FAIL：0
     * @exception Exception SQLExceptionなど
     */
    public int deleteIfaComplianceReportBrokerBlockViewExcludeSettingSql003(
            IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel req) throws Exception;
    
}

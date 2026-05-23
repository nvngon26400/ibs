package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0505_03-01
 * 画面名：コンプライアンス通信仲介業者一括閲覧不要設定
 * 2023/11/08 新規作成
 *
 * @author SCSK 江口
 *
 */
public interface IfaComplianceReportBrokerBlockViewExcludeSettingService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：全仲介業者名表示
     * Dto リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestDto
     * Dto レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto
     * model リクエスト：companyEmployeeMenu.complianceReportA002RequestModel
     * model レスポンス：companyEmployeeMenu.complianceReportA002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return コンプライアンス通信閲覧要否管理情報
     * @exception Exception SQLExceptionやIFARuntimeExceptionなど
     */
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto> allBrokerNameDisplayA002(
            IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：検索表示
     * Dto リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestDto
     * Dto レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto
     * model リクエスト：companyEmployeeMenu.complianceReportA003RequestModel
     * model レスポンス：companyEmployeeMenu.complianceReportA003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return コンプライアンス通信閲覧要否管理情報
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto> searchDisplayA003(
            IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：登録
     * Dto リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto
     * Dto レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto
     * model リクエスト：companyEmployeeMenu.complianceReportA004RequestModel
     * model レスポンス：companyEmployeeMenu.complianceReportA004ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return コンプライアンス通信閲覧要否管理情報
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto> registerA004(
            IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：登録解除
     * Dto リクエスト：IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestDto
     * Dto レスポンス：IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto
     * model リクエスト：companyEmployeeMenu.complianceReportA005RequestModel
     * model レスポンス：companyEmployeeMenu.complianceReportA005ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return コンプライアンス通信閲覧要否管理情報
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto> registrationCancellationA005(
            IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestDto dtoReq) throws Exception;
    
}

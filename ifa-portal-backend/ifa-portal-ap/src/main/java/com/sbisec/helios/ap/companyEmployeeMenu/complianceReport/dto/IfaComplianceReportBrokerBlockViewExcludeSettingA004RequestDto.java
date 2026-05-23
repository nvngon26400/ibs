package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto;

import lombok.Data;

/**
 * 画面ID：SUB0505_03-01
 * 画面名：コンプライアンス通信仲介業者一括閲覧不要設定
 * 2023/11/08 新規作成
 *
 * @author SCSK 江口
 * 
 */
@Data
public class IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto {
    
    /** 仲介業者コード（数字） */
    private String brokerCode;
    
}

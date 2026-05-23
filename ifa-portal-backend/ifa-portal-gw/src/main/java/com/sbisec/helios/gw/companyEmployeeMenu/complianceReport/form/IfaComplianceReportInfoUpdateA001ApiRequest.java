package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * コンプライアンス通信情報更新 A001リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaComplianceReportInfoUpdateA001ApiRequest {
    
    /** 画面リンク情報 */
    @NotEmpty(message = "画面リンク情報")
    private String corLecId;
}

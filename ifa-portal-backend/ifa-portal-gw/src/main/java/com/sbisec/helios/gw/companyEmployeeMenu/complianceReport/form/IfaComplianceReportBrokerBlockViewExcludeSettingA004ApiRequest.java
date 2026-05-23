package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
public class IfaComplianceReportBrokerBlockViewExcludeSettingA004ApiRequest {
    
    /** 仲介業者コード（数字） */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp = "0-9", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
}

package com.sbisec.helios.gw.infoMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * コンプライアンス通信X001リクエスト
 *
 * @author SCSK
 * 
 */
@Data
public class IfaComplianceReportX001ApiRequest {

    /** 表示年月. */
    @NotEmpty(message = "表示年月")
    private String lecId;

}

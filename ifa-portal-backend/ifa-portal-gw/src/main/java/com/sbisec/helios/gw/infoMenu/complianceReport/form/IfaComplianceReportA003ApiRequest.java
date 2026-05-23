package com.sbisec.helios.gw.infoMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * コンプライアンス通信A003リクエスト
 *
 * @author SCSK
 * 
 */
@Data
public class IfaComplianceReportA003ApiRequest {
    /** LECTURE_ID. */
    @NotEmpty(message = "LECTURE_ID")
    private String lecId;

    /** 確認フラグ. */
    @NotEmpty(message = "確認フラグ")
    private String confirmFlg;

}

package com.sbisec.helios.gw.infoMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * コンプライアンス通信A004aリクエスト
 *
 * @author SCSK
 * 
 */
@Data
public class IfaComplianceReportA004aApiRequest {
    /** ファイル名. */
    @NotEmpty(message = "ファイル名")
    private String filename;

    /** ファイルパス. */
    @NotEmpty(message = "ファイルパス")
    private String directory;

    /** 確認フラグ. */
    @NotEmpty(message = "確認フラグ")
    private String confirmFlg;

}

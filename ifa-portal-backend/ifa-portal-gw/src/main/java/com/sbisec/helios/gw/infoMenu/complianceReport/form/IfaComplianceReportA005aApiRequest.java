package com.sbisec.helios.gw.infoMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaComplianceReportA005aApiRequest {
    /** ファイル名. */
    @NotEmpty(message = "ファイル名")
    private String filename;

    /** ファイルパス. */
    @NotEmpty(message = "ファイルパス")
    private String directory;
}

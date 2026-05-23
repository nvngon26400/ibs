package com.sbisec.helios.gw.infoMenu.complianceReport.form;

import lombok.Data;

/**
 * コンプライアンス通信A004aレスポンス
 *
 * @author SCSK
 * 
 */
@Data
public class IfaComplianceReportA004aApiResponse {
    
    /** PDFファイル名 */
    private String pdfFileName;
}
package com.sbisec.helios.gw.infoMenu.complianceReport.form;

import lombok.Data;

/**
 * コンプライアンス通信A004bリクエスト
 *
 * @author SCSK
 * 
 */
@Data
public class IfaComplianceReportA004bApiRequest {
    
    /** PDFファイル名 */
    private String pdfFileName;
}

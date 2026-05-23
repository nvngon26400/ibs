package com.sbisec.helios.gw.infoMenu.complianceReport.form;

import lombok.Data;

/**
 * コンプライアンス通信A001リスポンス（コンプライアンス通信リスト）
 *
 * @author SCSK
 * 
 */
@Data
public class IfaComplianceReportA001SlctYmListApiResponse {

    /** LECTURE_ID. */
    private String lecId;
    
    /** 通信年月. */
    private String communicationDate;
}

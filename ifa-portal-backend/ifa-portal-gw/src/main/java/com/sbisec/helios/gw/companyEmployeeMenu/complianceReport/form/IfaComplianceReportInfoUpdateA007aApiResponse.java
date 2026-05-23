package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import lombok.Data;

/**
 * コンプライアンス通信情報更新 A00７aレスポンス
 *
 * @author SCSK
 */
@Data
public class IfaComplianceReportInfoUpdateA007aApiResponse {
    
    /**ファイル名1 */
    private String corFileName1;
    
    /**ファイル名2 */
    private String corFileName2;
    
    /**ファイル名3 */
    private String corFileName3;
    
    /**コンテンツファイル名 */
    private String corContentsFileName;    
}

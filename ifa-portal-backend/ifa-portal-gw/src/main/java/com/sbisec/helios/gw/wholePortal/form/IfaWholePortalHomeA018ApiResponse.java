package com.sbisec.helios.gw.wholePortal.form;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA018ApiResponse {

    /** 管理者アラート・コンプライアンス通信 */
    private IfaWholePortalHomeA018ApiResponseManagerAlertComplianceReport managerAlertComplianceReport;

    /** 管理者アラート・自己点検 */
    private IfaWholePortalHomeA018ApiResponseManagerAlertSelfAssessment selfAssessment;

}

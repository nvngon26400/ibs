package com.sbisec.helios.ap.wholePortal.dto;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA018ResponseDto {

    /** 管理者アラート・コンプライアンス通信リスト */
    private IfaWholePortalHomeA018ResponseDtoManagerAlertComplianceReport managerAlertComplianceReport;

    /** 管理者アラート・自己点検リスト */
    private IfaWholePortalHomeA018ResponseDtoManagerAlertSelfAssessment managerAlertSelfAssessment;

}

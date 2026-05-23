package com.sbisec.helios.ap.wholePortal.dto;

import java.util.List;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA001ResponseDto {

    /** 顧客アラートリスト */
    private List<IfaWholePortalHomeA001ResponseDtoCustomerAlert> customerAlertList;

    /** 管理者アラート・コンプライアンス通信 */
    private IfaWholePortalHomeA001ResponseDtoManagerAlertComplianceReport managerAlertComplianceReport;

    /** 管理者アラート・自己点検 */
    private IfaWholePortalHomeA001ResponseDtoManagerAlertSelfAssessment managerAlertSelfAssessment;

    /** お知らせカテゴリリスト */
    private List<IfaWholePortalHomeA001ResponseDtoNotificationCategory> notificationCategoryList;

    /** お知らせリスト */
    private List<IfaWholePortalHomeA001ResponseDtoNotification> notificationList;

    /** ファイルディレクトリ（全角半角） */
    private String fileDirectory;

}

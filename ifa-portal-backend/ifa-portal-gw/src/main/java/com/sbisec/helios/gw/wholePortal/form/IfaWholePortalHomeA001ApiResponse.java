package com.sbisec.helios.gw.wholePortal.form;

import java.util.List;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA001ApiResponse {

    /** 顧客アラートリスト */
    private List<IfaWholePortalHomeA001ApiResponseCustomerAlert> customerAlertList;

    /** 管理者アラート・コンプライアンス通信 */
    private IfaWholePortalHomeA001ApiResponseManagerAlertComplianceReport managerAlertComplianceReport;

    /** 管理者アラート・自己点検 */
    private IfaWholePortalHomeA001ApiResponseManagerAlertSelfAssessment managerAlertSelfAssessment;

    /** お知らせカテゴリリスト */
    private List<IfaWholePortalHomeA001ApiResponseNotificationCategory> notificationCategoryList;

    /** お知らせリスト */
    private List<IfaWholePortalHomeA001ApiResponseNotification> notificationList;

    /** ファイルディレクトリ（全角半角） */
    private String fileDirectory;

}

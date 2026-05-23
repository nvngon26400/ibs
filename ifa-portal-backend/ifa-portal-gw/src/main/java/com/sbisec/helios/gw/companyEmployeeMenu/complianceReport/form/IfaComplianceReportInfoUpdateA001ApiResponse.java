package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import lombok.Data;

/**
 * コンプライアンス通信情報更新 A001レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaComplianceReportInfoUpdateA001ApiResponse {
    
    /** 通信年月 */
    private String corCommunicationDate;

    /** タイトル */
    private String corTitle;

    /** 概要 */
    private String corBrief;

    /** ファイル名1 */
    private String corFileName1;

    /** ファイル1（コメント） */
    private String corFileDesc1;

    /** ファイル2名 */
    private String corFileName2;

    /** ファイル2（コメント）. */
    private String corFileDesc2;

    /** ファイル3名 */
    private String corFileName3;

    /** ファイル3（コメント）. */
    private String corFileDesc3;

    /** コンテンツファイル名 */
    private String corContentsFileName;

    /** コンテンツファイル（コメント） */
    private String corContents;
}

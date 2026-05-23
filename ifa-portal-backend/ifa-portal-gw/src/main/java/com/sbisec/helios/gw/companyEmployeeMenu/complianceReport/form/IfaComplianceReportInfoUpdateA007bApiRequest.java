package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * コンプライアンス通信情報更新 A007bリクエスト
 *
 * @author SCSK
 */
@Data
public class IfaComplianceReportInfoUpdateA007bApiRequest {
    
    /** LECTURE_ID */
    @NotEmpty(message = "LECTURE_ID")
    private String corLecId;
    
    /** 通信年月. */
    @NotEmpty(message = "通信年月")
    private String corCommunicationDate;

    /** タイトル */
    @NotEmpty(message = "タイトル")
    @Size(max = 120, message = "タイトル")
    private String corTitle;

    /** 概要 */
    @Size(max = 1000, message = "概要")
    private String corBrief;

    /** ファイル名1 */
    @Size(max = 127, message = "ファイル名1")
    private String corFileName1;
    
    /** ファイル1（変更前） */
    private String corFileName1BeforeChange;
    
    /** ファイル名1（削除） */
    private String corFileName1Delete;

    /** ファイル名1コメント. */
    @Size(max = 120, message = "ファイル名1コメント")
    private String corFileDesc1;

    /** ファイル名2　*/
    @Size(max = 127, message = "ファイル名2")
    private String corFileName2;

    /** ファイル2（変更前） */
    private String corFileName2BeforeChange;    
    
    /** ファイル名2（削除） */
    private String corFileName2Delete;

    /** ファイル名2コメント */
    @Size(max = 120, message = "ファイル名2コメント")
    private String corFileDesc2;

    /** ファイル名3 */
    @Size(max = 127, message = "ファイル名3")
    private String corFileName3;

    /** ファイル3（変更前） */
    private String corFileName3BeforeChange;
    
    /** ファイル名3（削除） */
    private String corFileName3Delete;

    /** ファイル名3コメント. */
    @Size(max = 120, message = "ファイル名3コメント")
    private String corFileDesc3;

    /** コンテンツファイル名 */
    @Size(max = 127, message = "コンテンツファイル名")
    private String corContentsFileName;

    /** コンテンツファイル（変更前） */
    private String corContentsFileNameBeforeChange;
    
    /** コンテンツファイル名（削除） */
    private String corContentsFileNameDelete;
    
    /** コンテンツコメント */
    @Size(max = 120, message = "コンテンツコメント")
    private String corContents;
}

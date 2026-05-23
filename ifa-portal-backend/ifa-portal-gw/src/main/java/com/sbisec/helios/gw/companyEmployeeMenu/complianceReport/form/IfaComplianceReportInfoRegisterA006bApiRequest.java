package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 画面ID：SUB0505_01-03_1
 * 画面名：コンプライアンス通信情報登録
 *
 * @author SCSK
 */
@Data
public class IfaComplianceReportInfoRegisterA006bApiRequest {

    /** 通信年月 */
    @NotEmpty(message = "通信年月")
    @DateTimeFormat(pattern = "yyyy/MM")
    @JsonFormat(pattern = "yyyy/MM")
    private String corCommunicationDate;

    /** タイトル */
    @NotEmpty(message = "タイトル")
    @Size(max = 120, message = "タイトル")
    private String corTitle;

    /** 概要 */
    @Size(max = 1000, message = "概要")
    private String corBrief;

    /** ファイル1 */
    @Size(max = 127, message = "ファイル名1")
    private String corFileName1;

    /** ファイル1（コメント） */
    @Size(max = 120, message = "ファイル名1コメント")
    private String corFileDesc1;

    /** ファイル2 */
    @Size(max = 127, message = "ファイル名2")
    private String corFileName2;

    /** ファイル2（コメント） */
    @Size(max = 120, message = "ファイル名2コメント")
    private String corFileDesc2;

    /** ファイル3 */
    @Size(max = 127, message = "ファイル名3")
    private String corFileName3;

    /** ファイル3（コメント） */
    @Size(max = 120, message = "ファイル名3コメント")
    private String corFileDesc3;

    /** コンテンツファイル */
    @NotEmpty(message = "コンテンツファイル")
    @Size(max = 127, message = "コンテンツファイル名")
    private String corContentsFileName;

    /** コンテンツファイル（コメント） */
    @NotEmpty(message = "コンテンツコメント")
    @Size(max = 120, message = "コンテンツコメント")
    private String corContents;

    /** 公開フラグ */
    @NotEmpty(message = "公開フラグ")
    @Pattern(regexp = "[0-9]", message = "公開フラグ")
    private String corHonbanFlg;

}

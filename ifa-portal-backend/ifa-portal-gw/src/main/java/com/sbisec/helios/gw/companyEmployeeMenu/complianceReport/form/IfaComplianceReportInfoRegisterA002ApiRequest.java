package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 画面ID：SUB0505_01-03_1
 * 画面名：コンプライアンス通信情報登録
 *
 * @author SCSK
 */
@Data
public class IfaComplianceReportInfoRegisterA002ApiRequest {
    
    /** 通信年月 */
    @NotEmpty(message = "通信年月")
    @DateTimeFormat(pattern = "yyyy/MM")
    @JsonFormat(pattern = "yyyy/MM")
    private String corCommunicationDate;

    /** タイトル */
    @NotEmpty(message = "タイトル")
    private String corTitle;

    /** 概要 */
    private String corBrief;

    /** ファイル1 */
    private String corFileName1;

    /** ファイル1（コメント） */
    private String corFileDesc1;

    /** ファイル2 */
    private String corFileName2;

    /** ファイル2（コメント） */
    private String corFileDesc2;

    /** ファイル3 */
    private String corFileName3;

    /** ファイル3（コメント） */
    private String corFileDesc3;

    /** コンテンツファイル */
    @NotEmpty(message = "コンテンツファイル")
    private String corContentsFileName;

    /** コンテンツファイル（コメント） */
    @NotEmpty(message = "コンテンツコメント")
    private String corContents;

}

package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model;

import lombok.Data;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/10/27
 */

@Data
public class IfaReleaseNoteUpdateSql001ResponseModel {

    /** リリースノートNo. */
    private String releaseNoteNo;

    /** タイトル. */
    private String title;

    /** 画面表示開始日. */
    private String displayedDate;

    /** ファイルタイプ. */
    private String fileType;

    /** PDFサイズ. */
    private String pdfSize;

    /** PDF向き. */
    private String pdfDirection;

    /** 詳細ファイル. */
    private String detailedFile;

    /** リリースノート内容No. */
    private String releaseNoteContentNo;

    /** メニュー名. */
    private String menuName;

    /** 画面名. */
    private String screenName;

    /** 内容. */
    private String content;

}

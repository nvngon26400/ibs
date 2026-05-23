package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model;

import lombok.Data;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/10/30
 */

@Data
public class IfaReleaseNoteUpdateSql005RequestModel {

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

    /** 更新日時. */
    private String updateTime;

    /** 更新者. */
    private String updateUser;

}

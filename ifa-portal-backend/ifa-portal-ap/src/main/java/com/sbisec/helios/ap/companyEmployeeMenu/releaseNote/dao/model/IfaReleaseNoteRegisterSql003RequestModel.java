package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dao.model;

import lombok.Data;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */

@Data
public class IfaReleaseNoteRegisterSql003RequestModel {

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

    /** 作成日時. */
    private String createTime;

    /** 作成者. */
    private String createUser;

    /** 更新日時. */
    private String updateTime;

    /** 更新者. */
    private String updateUser;

}

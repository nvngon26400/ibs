package com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto;

import java.util.List;
import lombok.Data;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/10/23
 */

@Data
public class IfaReleaseNoteRegisterA007bRequestDto {

    /** 詳細ファイル名. */
    private String detailedFileName;

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

    /** 内容リスト. */
    private List<ContentItem> contentItemList;

}

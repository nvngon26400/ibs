package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form;

import java.util.List;
import lombok.Data;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/11/04
 */

@Data
public class IfaReleaseNoteUpdateA009bApiRequest {

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
    private String detailedFileName;

    /** 登録済詳細ファイル. */
    private String registeredDetailedFileName;

    /** 登録済詳細ファイル削除フラグ. */
    private String registeredDetailedFileDeleteFlag;

    /** 内容リスト. */
    private List<ContentItem> contentItemList;

}

package com.sbisec.helios.gw.releaseNote.form;

import lombok.Data;

/**
 * 画面ID：SUB00-07_2
 * 画面名：リリースノート詳細
 * 2025/11/04 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaReleaseNoteDetailA001ApiResponse {

    /** ファイルディレクトリ */
    private String fileDir;

    /** ファイルタイプ */
    private String fileType;

    /** PDFサイズ */
    private String pdfSize;

    /** PDF向き */
    private String pdfDirection;

    /** 詳細ファイル */
    private String detailedFile;
}

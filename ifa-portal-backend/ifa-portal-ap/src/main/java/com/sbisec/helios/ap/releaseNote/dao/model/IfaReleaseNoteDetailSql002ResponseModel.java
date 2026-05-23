package com.sbisec.helios.ap.releaseNote.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00-07_2
 * リリースノート SQL002（リリースノート一覧取得） レスポンスパラメタ
 * 2025/10/24 新規作成
 *
 * @author 大連 葉
 */
@Data
public class IfaReleaseNoteDetailSql002ResponseModel {

    /** ファイルタイプ */
    private String fileType;

    /** PDFサイズ */
    private String pdfSize;

    /** PDF向き */
    private String pdfDirection;

    /** 詳細ファイル */
    private String detailedFile;
}

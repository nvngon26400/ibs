package com.sbisec.helios.gw.wholePortal.form;

import lombok.Data;


/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaInfoDetailA001ApiResponse {

    /** ファイルディレクトリ */
    private String fileDirectory;

    /** タイトル */
    private String title;

    /** 内容 */
    private String contents;

    /** URL */
    private String url;

    /** URLコメント */
    private String urlComment;

    /** 添付ファイル1 */
    private String attachDocument1;

    /** 添付ファイル2 */
    private String attachDocument2;

    /** 添付ファイル3 */
    private String attachDocument3;

    /** 添付ファイルコメント1 */
    private String attachDocumentComment1;

    /** 添付ファイルコメント2 */
    private String attachDocumentComment2;

    /** 添付ファイルコメント3 */
    private String attachDocumentComment3;

    /** ディスクレーマ */
    private String disclaimer;

}

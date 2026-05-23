package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaInfoDetailSql002ResponseModel {

    /** お知らせID */
    private String notificationId;

    /** タイトル */
    private String title;

    /** 内容 */
    private String contents;

    /** URL */
    private String url;

    /** URLコメント */
    private String urlComment;

    /** 添付ファイル１ */
    private String attachDocument1;

    /** 添付ファイル２ */
    private String attachDocument2;

    /** 添付ファイル３ */
    private String attachDocument3;

    /** 添付ファイルコメント１ */
    private String attachDocumentComment1;

    /** 添付ファイルコメント２ */
    private String attachDocumentComment2;

    /** 添付ファイルコメント３ */
    private String attachDocumentComment3;

    /** ディスクレーマー */
    private String disclaimer;

}

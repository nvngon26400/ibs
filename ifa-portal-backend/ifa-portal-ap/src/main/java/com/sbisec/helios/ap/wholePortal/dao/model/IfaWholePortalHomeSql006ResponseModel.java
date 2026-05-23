package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeSql006ResponseModel {

    /** お知らせID（数字） */
    private String notificationId;

    /** タイトル（全角半角） */
    private String title;

    /** カテゴリ名 */
    private String categoryName;

    /** 内容 */
    private String contents;

    /** 添付ファイル１ */
    private String attachment1;

    /** 添付ファイル２ */
    private String attachment2;

    /** 添付ファイル３ */
    private String attachment3;

    /** 添付ファイルコメント１ */
    private String attachmentComment1;

    /** 添付ファイルコメント２ */
    private String attachmentComment2;

    /** 添付ファイルコメント３ */
    private String attachmentComment3;

    /** 重要度（数字） */
    private String t4nImportance;

    /** URL（英数字記号B(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<')） */
    private String url;

    /** URLコメント */
    private String urlComment;

    /** ディスクレーマー（全角半角） */
    private String disclaimer;

    /** 更新日時 */
    private String updateTime;

    /** 登録日時 */
    private String registerDayTime;

    /** 既読フラグ（全角半角） */
    private String t5nReadFlg;

    /** 閲覧日 */
    private String readDate;

}

package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaInfoUpdateSql003RequestModel {

    /** お知らせID（数字）. */
    private String notificationId;

    /** タイトル（全角半角）. */
    private String title;

    /** 内容. */
    private String contents;

    /** 添付ファイル１. */
    private String attachFile1;

    /** 添付ファイル２. */
    private String attachFile2;

    /** 添付ファイル３. */
    private String attachFile3;

    /** 資料種別１. */
    private String documentKindInput1;

    /** 添付ファイルコメント１. */
    private String attachFileComment1;

    /** 添付ファイルコメント２. */
    private String attachFileComment2;

    /** 添付ファイルコメント３. */
    private String attachFileComment3;

    /** URL. */
    private String url;

    /** URLコメント. */
    private String urlComment;

    /** 参照範囲（数字）. */
    private String corReferenceCondition;

    /** ディスクレーマー（半角英数字）. */
    private String disclaimer;

    /** 既読管理フラグ（数字）. */
    private String corReadFlg;

    /** 更新者. */
    private String updateUser;

}

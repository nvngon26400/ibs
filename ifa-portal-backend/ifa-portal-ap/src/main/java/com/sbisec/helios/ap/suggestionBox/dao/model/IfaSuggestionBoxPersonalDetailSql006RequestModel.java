package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項詳細
 * @author SCSK神木
 * 2025/06/19 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailSql006RequestModel {

    /** ユーザーID */
    private String userId;

    /** 要望No */
    private String sbpNo;

    /** タイトル */
    private String title;

    /** カテゴリ */
    private String category;

    /** 要望内容 */
    private String suggestion;

    /** 添付ファイル1 */
    private String attachFile1;

    /** 添付ファイル2 */
    private String attachFile2;

    /** 添付ファイル3 */
    private String attachFile3;

    /** 登録済添付ファイル1削除指示フラグ */
    private String registeredAttachFile1DeleteFlag;

    /** 登録済添付ファイル2削除指示フラグ */
    private String registeredAttachFile2DeleteFlag;

    /** 登録済添付ファイル3削除指示フラグ */
    private String registeredAttachFile3DeleteFlag;
}

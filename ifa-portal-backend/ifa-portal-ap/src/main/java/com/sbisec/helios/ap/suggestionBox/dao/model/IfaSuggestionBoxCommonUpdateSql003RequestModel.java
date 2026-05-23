package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 *
 2025/06/23 新規作成
 */

@Data
public class IfaSuggestionBoxCommonUpdateSql003RequestModel {

    /** タイトル. */
    private String title;

    /** カテゴリ. */
    private String category;

    /** ステータス. */
    private String status;

    /** 要望内容. */
    private String suggestion;

    /** 皆様からの要望No. */
    private String sbcNo;

    /** 添付ファイル1（リネーム後ファイル名）. */
    private String registerFileName1;

    /** 添付ファイル2（リネーム後ファイル名）. */
    private String registerFileName2;

    /** 添付ファイル3（リネーム後ファイル名）. */
    private String registerFileName3;

    /** 登録済添付ファイル1削除示フラグ */
    private String registeredAttachFile1DeleteFlag;

    /** 登録済添付ファイル2削除フラグ */
    private String registeredAttachFile2DeleteFlag;

    /** 登録済添付ファイル3削除フラグ */
    private String registeredAttachFile3DeleteFlag;

    /** ユーザ共通情報.ユーザID. */
    private String userId;

}

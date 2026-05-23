package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0511_02-02
 * 画面名：皆様からの要望新規登録
 *
 2025/06/26 新規作成
 */

@Data
public class IfaSuggestionBoxCommonRegisterSql003RequestModel {

    /** 皆様からの要望No. */
    private String sbcNo;

    /** タイトル. */
    private String title;

    /** カテゴリ. */
    private String category;

    /** ステータス. */
    private String status;

    /** 要望内容. */
    private String suggestion;

    /** 添付ファイル1（リネーム後ファイル名）. */
    private String registerFileName1;

    /** 添付ファイル2（リネーム後ファイル名）. */
    private String registerFileName2;

    /** 添付ファイル3（リネーム後ファイル名）. */
    private String registerFileName3;

    /** ユーザ共通情報.ユーザID. */
    private String userId;

}

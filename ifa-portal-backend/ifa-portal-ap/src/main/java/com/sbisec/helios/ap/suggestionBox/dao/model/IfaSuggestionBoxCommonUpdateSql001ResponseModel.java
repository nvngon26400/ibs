package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 *
 2025/06/23 新規作成
 */

@Data
public class IfaSuggestionBoxCommonUpdateSql001ResponseModel {

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

    /** 添付ファイル1. */
    private String attachFile1;

    /** 添付ファイル2. */
    private String attachFile2;

    /** 添付ファイル3. */
    private String attachFile3;

    /** 回答一覧.回答No. */
    private String answerNo;

    /** 回答一覧.回答登録日. */
    private String answerRegisterDate;

    /** 回答一覧.回答更新日. */
    private String answerUpdateDate;

    /** 回答一覧.回答内容. */
    private String answerContents;

}

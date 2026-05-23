package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 *
 2025/06/23 新規作成
 */

@Data
public class IfaSuggestionBoxCommonUpdateSql005RequestModel {

    /** 皆様からの要望No. */
    private String sbcNo;

    /** 回答No. */
    private String answerNo;

    /** 回答登録日. */
    private String answerRegisterDate;

    /** 回答一覧.回答内容. */
    private String answerContents;

    /** ユーザ共通情報.ユーザID. */
    private String userId;

}

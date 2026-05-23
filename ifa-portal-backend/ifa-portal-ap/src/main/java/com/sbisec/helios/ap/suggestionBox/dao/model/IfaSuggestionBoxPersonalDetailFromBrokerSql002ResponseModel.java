package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 * @author SCSK山岸
 * 2025/07/25 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailFromBrokerSql002ResponseModel {

    /** 要望回答No（数字） */
    private String answerNo;

    /** 回答内容（全角半角） */
    private String answerContents;

    /** 回答作成日時（数字） */
    private String answerRegisterDate;

    /** 要望作成日時（数字） */
    private String answerUpdateDate;

}

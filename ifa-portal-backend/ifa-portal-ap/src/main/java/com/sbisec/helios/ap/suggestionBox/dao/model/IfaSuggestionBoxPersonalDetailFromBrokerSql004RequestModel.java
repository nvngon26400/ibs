package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 * @author SCSK山岸
 * 2025/07/25 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailFromBrokerSql004RequestModel {
    
    /** 要望No（数字） */
    private String sbpNo;
    
    /** 回答内容（全角半角） */
    private String answerContents;

    /** ユーザーID */
    private String userId;

}

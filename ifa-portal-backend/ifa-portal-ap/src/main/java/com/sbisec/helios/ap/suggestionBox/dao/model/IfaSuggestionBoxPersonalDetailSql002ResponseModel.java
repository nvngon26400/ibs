package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項詳細
 * @author SCSK神木
 * 2025/06/24 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailSql002ResponseModel {

    /** 回答No. */
    private String answerNo;
    
    /** 回答内容. */
    private String answerContents;
    
    /** 作成日時. */
    private String createTime;

    /** 更新日時. */
    private String answerUpdateTime;

}

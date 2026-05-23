package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00_01-06_1
 * 画面名：あなたの要望
 * @author SCSK神木
 * 2025/06/12 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalSql002RequestModel {

    /** 最大件数 */
    private int maxRow;

    /** ユーザーID */
    private String userId;

    /** ステータス */
    private String status;

    /** 登録日_From */
    private String registerDateFrom;

    /** 登録日_To */
    private String registerDateTo;

    /** タイトル */
    private String title;

}

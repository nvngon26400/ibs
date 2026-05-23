package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 * @author SCSK山岸
 * 2025/07/25 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailFromBrokerSql001ResponseModel {

    /** 要望No（数字） */
    private String sbpNo;

    /** タイトル（全角半角） */
    private String title;

    /** ステータス（数字） */
    private String status;

    /** 要望内容（全角半角） */
    private String suggestion;

    /** 添付ファイル1（全角半角） */
    private String attachFile1;

    /** 添付ファイル2（全角半角） */
    private String attachFile2;

    /** 添付ファイル3（全角半角） */
    private String attachFile3;

    /** 要望作成日時（数字） */
    private String registerDate;

}

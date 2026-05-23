package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00_01-06_2
 * 画面名：要望事項 新規登録
 *
 * 2025/06/30 新規作成
 */

@Data
public class IfaSuggestionBoxPersonalRegisterSql002RequestModel {
	
    /** あなたの要望No. */
    private String sbpNo;

    /** タイトル. */
    private String title;

    /** カテゴリ. */
    private String category;

    /** 要望内容. */
    private String suggestion;

    /** 添付ファイル1（リネーム後ファイル名）. */
    private String registerFileName1;

    /** 添付ファイル2（リネーム後ファイル名）. */
    private String registerFileName2;

    /** 添付ファイル3（リネーム後ファイル名）. */
    private String registerFileName3;

    /** ユーザ共通情報.仲介業者コード. */
    private String brokerCode;

    /** ユーザ共通情報.仲介業者営業員コード. */
    private String intermediaryEmpCd;

    /** ユーザ共通情報.ユーザID. */
    private String userId;

}

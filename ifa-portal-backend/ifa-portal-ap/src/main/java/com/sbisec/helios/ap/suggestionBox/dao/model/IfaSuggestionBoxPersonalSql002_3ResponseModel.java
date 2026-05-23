package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00_01-06_1
 * 画面名：あなたの要望
 * @author SCSK神木
 * 2025/06/12 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalSql002_3ResponseModel {

    /** 総件数 */
    private int totalRow;

    /** 要望一覧.要望No */
    private String sbpNo;

    /** 要望一覧.更新日 */
    private String ansUpdateTime;

    /** 要望一覧.登録日 */
    private String registerDate;

    /** 要望一覧.仲介業者コード  */
    private String brokerCode;

    /** 要望一覧.仲介業者名 */
    private String brokerName;

    /** 要望一覧.営業員コード  */
    private String empCode;

    /** 要望一覧.営業員名  */
    private String empName;

    /** 要望一覧.タイトル  */
    private String title;

    /** 要望一覧.カテゴリ  */
    private String category;

    /** 要望一覧.ステータス  */
    private String status;

    /** 要望一覧.要望内容 */
    private String suggestion;

}

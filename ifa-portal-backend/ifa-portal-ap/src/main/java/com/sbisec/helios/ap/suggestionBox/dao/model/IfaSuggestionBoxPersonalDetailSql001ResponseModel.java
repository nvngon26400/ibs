package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項詳細
 * @author SCSK神木
 * 2025/06/24 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailSql001ResponseModel {

    /** 要望No */
    private String sbpNo;

    /** タイトル  */
    private String title;

    /** カテゴリ  */
    private String category;

    /** ステータス  */
    private String status;

    /** 要望内容 */
    private String suggestion;

    /** 登録済添付ファイル1  */
    private String registeredAttachFile1;

    /** 登録済添付ファイル2  */
    private String registeredAttachFile2;

    /** 登録済添付ファイル3 */
    private String registeredAttachFile3;

}

package com.sbisec.helios.gw.suggestionBox.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項詳細
 * 
 * @author SCSK神木
 * 2025/06/19 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailA007bApiRequest {

    /** 要望No */
    @NotEmpty(message = "要望No")
    private String sbpNo;

    /** タイトル */
    @NotEmpty(message = "タイトル")
    private String title;

    /** カテゴリ */
    @NotEmpty(message = "カテゴリ")
    private String category;

    /** 要望内容 */
    private String suggestion;

    /** 登録済添付ファイル1 */
    private String registeredAttachFile1;

    /** 登録済添付ファイル2 */
    private String registeredAttachFile2;

    /** 登録済添付ファイル3 */
    private String registeredAttachFile3;

    /** 登録済添付ファイル1削除指示フラグ */
    private String registeredAttachFile1DeleteFlag;

    /** 登録済添付ファイル2削除指示フラグ */
    private String registeredAttachFile2DeleteFlag;

    /** 登録済添付ファイル3削除指示フラグ */
    private String registeredAttachFile3DeleteFlag;

    /** 添付ファイル1（リネーム後ファイル名） */
    private String registerFileName1;

    /** 添付ファイル2（リネーム後ファイル名） */
    private String registerFileName2;

    /** 添付ファイル3（リネーム後ファイル名） */
    private String registerFileName3;

}

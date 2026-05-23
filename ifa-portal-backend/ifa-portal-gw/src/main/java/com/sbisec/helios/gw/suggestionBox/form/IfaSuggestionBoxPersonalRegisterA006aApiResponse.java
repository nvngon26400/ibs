package com.sbisec.helios.gw.suggestionBox.form;

import lombok.Data;

/**
 * 画面ID：SUB00_01-06_2
 * 画面名：要望事項 新規登録
 *
 * 2025/06/29 新規作成
 */

@Data
public class IfaSuggestionBoxPersonalRegisterA006aApiResponse {

    /** 添付ファイル1（リネーム後ファイル名）. */
    private String registerFileName1;

    /** 添付ファイル2（リネーム後ファイル名）. */
    private String registerFileName2;

    /** 添付ファイル3（リネーム後ファイル名）. */
    private String registerFileName3;

    /** あなたの要望No. */
    private String sbpNo;

}

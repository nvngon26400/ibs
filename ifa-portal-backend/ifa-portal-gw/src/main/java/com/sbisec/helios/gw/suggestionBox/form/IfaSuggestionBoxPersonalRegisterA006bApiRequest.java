package com.sbisec.helios.gw.suggestionBox.form;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB00_01-06_2
 * 画面名：要望事項 新規登録
 *
 * 2025/06/29 新規作成
 */

@Data
public class IfaSuggestionBoxPersonalRegisterA006bApiRequest {

    /** タイトル */
    @NotEmpty(message = "タイトル")
    private String title;

    /** カテゴリ */
    @NotEmpty(message = "カテゴリ")
    private String category;

    /** 要望内容 */
    @NotEmpty(message = "要望内容")
    private String suggestion;
    
    /** あなたの要望No */
    private String sbpNo;

    /** 添付ファイル1（リネーム後ファイル名） */
    private String registerFileName1;

    /** 添付ファイル2（リネーム後ファイル名） */
    private String registerFileName2;

    /** 添付ファイル3（リネーム後ファイル名） */
    private String registerFileName3;

}

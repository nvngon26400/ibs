package com.sbisec.helios.gw.suggestionBox.form;

import lombok.Data;

/**
 * 画面ID：SUB0511_02-02
 * 画面名：皆様からの要望新規登録
 *
 2025/06/26 新規作成
 */

@Data
public class IfaSuggestionBoxCommonRegisterA007aApiResponse {
    
    /** 添付ファイル1（リネーム後ファイル名）. */
    private String registerFileName1;

    /** 添付ファイル2（リネーム後ファイル名）. */
    private String registerFileName2;

    /** 添付ファイル3（リネーム後ファイル名）. */
    private String registerFileName3;
    
    /** 皆様からの要望No */
    private String sbcNo;
    
}


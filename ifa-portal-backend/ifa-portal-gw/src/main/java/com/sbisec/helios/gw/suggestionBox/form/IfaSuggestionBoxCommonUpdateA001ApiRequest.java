package com.sbisec.helios.gw.suggestionBox.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;


/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 *
 2025/06/23 新規作成
 */

@Data
public class IfaSuggestionBoxCommonUpdateA001ApiRequest {

    /** 皆様からの要望No */
    @NotEmpty(message = "皆様からの要望No")
    private String sbcNo;

}

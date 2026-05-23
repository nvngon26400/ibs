package com.sbisec.helios.gw.suggestionBox.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * 画面ID：SUB00_02-06_1
 * 画面名：皆様からの要望詳細
 *
 * @author SCSK
 2025/06/19 新規作成
 */
@Data
public class IfaSuggestionBoxCommonDetailA001ApiRequest {

    /** 皆様からの要望No */
    @NotEmpty(message = "皆様からの要望No")
    private String sbcNo;

}

package com.sbisec.helios.gw.suggestionBox.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項詳細
 * 
 * @author SCSK神木
 * 2025/06/23 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalDetailA001ApiRequest {

    /** 要望No */
    @NotEmpty(message = "要望No")
    private String sbpNo;

}

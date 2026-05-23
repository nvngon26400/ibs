package com.sbisec.helios.gw.suggestionBox.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 画面ID：SUB00_01-06_1
 * 画面名：あなたの要望
 * 
 * @author SCSK神木
 * 2025/06/12 新規作成
 */
@Data
public class IfaSuggestionBoxPersonalInitializeX001ApiRequest {

    /** ステータス_初期値 */
	@NotEmpty(message = "ステータス_初期値")
    private String status;

    /** 登録日_初期値 */
    @NotEmpty(message = "登録日_開始")
    private String registerDateFrom;

    /** 登録日_初期値 */
    @NotEmpty(message = "登録日_終了")
    private String registerDateTo;

    /** タイトル_初期値 */
    private String title;

}

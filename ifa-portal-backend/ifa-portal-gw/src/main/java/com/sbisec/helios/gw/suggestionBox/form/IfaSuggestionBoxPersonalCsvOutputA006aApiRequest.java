package com.sbisec.helios.gw.suggestionBox.form;

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
public class IfaSuggestionBoxPersonalCsvOutputA006aApiRequest {

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者名 */
    private String brokerName;

    /** ステータス */
    @NotEmpty(message = "ステータス")
    private String status;

    /** 登録日_開始 */
    @NotEmpty(message = "登録日_開始")
    private String registerDateFrom;

    /** 登録日_終了 */
    @NotEmpty(message = "登録日_終了")
    private String registerDateTo;

    /** タイトル */
    private String title;

}

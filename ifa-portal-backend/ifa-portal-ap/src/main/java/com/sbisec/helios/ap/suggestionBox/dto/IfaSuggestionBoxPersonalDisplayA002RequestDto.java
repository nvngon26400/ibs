package com.sbisec.helios.ap.suggestionBox.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * 画面ID：SUB00_01-06_1
 * 画面名：あなたの要望
 * 
 * @author SCSK神木
 * 2025/06/12 新規作成
 */
@Data
@JsonSerialize
public class IfaSuggestionBoxPersonalDisplayA002RequestDto {

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者名 */
    private String brokerName;

    /** ステータス */
    private String status;

    /** 登録日_開始 */
    private String registerDateFrom;

    /** 登録日_終了 */
    private String registerDateTo;

    /** タイトル */
    private String title;
    
}
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
public class IfaSuggestionBoxPersonalInitializeX001RequestDto {

    /** ステータス_初期値 */
    private String status;

    /** 登録日_初期値 */
    private String registerDateFrom;

    /** 登録日_初期値 */
    private String registerDateTo;

    /** タイトル_初期値 */
    private String title;
    
}
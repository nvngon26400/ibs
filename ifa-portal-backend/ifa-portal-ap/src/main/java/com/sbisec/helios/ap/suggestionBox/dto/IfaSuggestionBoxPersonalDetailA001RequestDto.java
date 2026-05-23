package com.sbisec.helios.ap.suggestionBox.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項詳細
 * 
 * @author SCSK神木
 * 2025/06/23 新規作成
 */
@Data
@JsonSerialize
public class IfaSuggestionBoxPersonalDetailA001RequestDto {

    /** 要望No */
    private String sbpNo;

}
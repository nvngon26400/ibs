package com.sbisec.helios.ap.suggestionBox.dto;

import lombok.Data;

@Data
public class IfaSuggestionBoxCommonA001DtoRequest {
    
    /** ステータス. */
    private String status;
    
    /** 登録日（From). */
    private String registerDateFrom;
    
    /** 登録日（To). */
    private String registerDateTo;
    
    /** タイトル. */
    private String title;
    
}

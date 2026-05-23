package com.sbisec.helios.ap.suggestionBox.dao.model;

import lombok.Data;

@Data
public class IfaSuggestionBoxCommonSql002RequestModel {
    
    /** ステータス. */
    private String status;
    
    /** 登録日（From). */
    private String registerDateFrom;
    
    /** 登録日（To). */
    private String registerDateTo;
    
    /** タイトル. */
    private String title;
    
    /** 最大件数. */
    private int maxRowNum;
    
}

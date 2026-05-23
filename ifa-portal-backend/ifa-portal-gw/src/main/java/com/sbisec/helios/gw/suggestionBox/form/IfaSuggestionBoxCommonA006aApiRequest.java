package com.sbisec.helios.gw.suggestionBox.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class IfaSuggestionBoxCommonA006aApiRequest {
    
    /** ステータス. */
    @NotEmpty(message = "ステータス")
    private String status;
    
    /** 登録日（From). */
    @NotEmpty(message = "登録日（From)")
    private String registerDateFrom;
    
    /** 登録日（To). */
    @NotEmpty(message = " 登録日（To)")
    private String registerDateTo;
    
    /** タイトル. */
    private String title;
    
}

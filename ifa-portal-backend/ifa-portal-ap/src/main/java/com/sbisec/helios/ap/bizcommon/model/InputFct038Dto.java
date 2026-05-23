package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT038
 *
 * @author SCSK
 */

@Data
public class InputFct038Dto {
    
    //画面ID
    private String screenId;
    
    //ユーザ権限
    private String userAuthority;
    
}

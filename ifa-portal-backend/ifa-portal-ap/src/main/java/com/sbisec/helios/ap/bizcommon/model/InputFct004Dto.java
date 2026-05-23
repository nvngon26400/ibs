package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT004
 * @author base 熊
 */

@Data
public class InputFct004Dto {
    
    //部店コード
    private String butenCode;
    
    //口座番号
    private String accountNumber;
    
    //預り区分
    private String depositType;
    
    //店頭管理番号
    private String otcManageNumber;
    
    //取引区分
    private String tradeType;
}

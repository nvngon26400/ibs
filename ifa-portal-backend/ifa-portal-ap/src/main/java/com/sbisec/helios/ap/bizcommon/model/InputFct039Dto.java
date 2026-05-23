package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT039
 *
 * @author SCSK
 */
@Data
public class InputFct039Dto {
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 連携区分 */
    private int linkKbn;
    
}

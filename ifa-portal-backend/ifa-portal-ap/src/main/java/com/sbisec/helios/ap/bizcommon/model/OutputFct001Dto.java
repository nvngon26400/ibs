package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT001
 *
 * @author SCSK
 */

@Data
public class OutputFct001Dto extends BaseOutputDto {
    
    // 対象顧客参照権限有無
    private String targetCustomerRefAuthFlag;
    
    // 取引停止フラグ
    private String tradeSuspendFlag;
    
}

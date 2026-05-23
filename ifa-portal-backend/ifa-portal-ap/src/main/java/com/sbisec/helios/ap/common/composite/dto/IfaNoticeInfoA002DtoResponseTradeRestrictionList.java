package com.sbisec.helios.ap.common.composite.dto;

import lombok.Data;

/**
*
* @author SCSK
*
*/
@Data
public class IfaNoticeInfoA002DtoResponseTradeRestrictionList {

    /** 制限番号（全角半角）. */
    private String restrictionCode;
    
    /** 制限内容（全角半角）. */
    private String restrictionContents;
    
    /** 確認期限. */
    private String confirmDeadline;

}

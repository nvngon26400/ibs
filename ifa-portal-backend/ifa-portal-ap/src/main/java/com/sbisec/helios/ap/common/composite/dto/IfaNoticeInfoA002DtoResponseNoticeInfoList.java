package com.sbisec.helios.ap.common.composite.dto;

import lombok.Data;

/**
*
* @author SCSK
*
*/
@Data
public class IfaNoticeInfoA002DtoResponseNoticeInfoList {

    /** 分類（全角半角）. */
    private String cautionKind;
    
    /** メモ（全角半角）. */
    private String memo;
    
    /** 発生日. */
    private String accuralDate;
    
    /** 取扱者（全角半角）. */
    private String dealtPerson;

}

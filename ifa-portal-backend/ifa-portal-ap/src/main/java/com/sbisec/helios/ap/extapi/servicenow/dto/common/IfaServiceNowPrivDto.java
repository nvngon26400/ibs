package com.sbisec.helios.ap.extapi.servicenow.dto.common;

import lombok.Data;

/**
 * 権限 Dto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowPrivDto {
    
    /** 権限コード. */
    private String privId;
    
    /** 権限コード名称. */
    private String privIdNm;
    
    /** 入力パターン. */
    private String inputPattern;
}

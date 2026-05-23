package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;

/**
 * メニュー取得 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowMenuAndAclManagerA010RequestDto {
    
    /** 権限コード. */
    private String privId;
    
}

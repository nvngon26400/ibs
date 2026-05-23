package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;

/**
 * ユーザ利用できるメニューを削除 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowMenuAndAclManagerA011RequestDto {
    
    /** ユーザーID. */
    private String userId;
    
}

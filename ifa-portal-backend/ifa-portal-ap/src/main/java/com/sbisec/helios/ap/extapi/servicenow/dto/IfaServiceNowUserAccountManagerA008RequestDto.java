package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;


/**
 * ユーザ&&利用できるメニューを削除 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowUserAccountManagerA008RequestDto {
    
    /** ユーザーID. */
    private String userId;
    
}

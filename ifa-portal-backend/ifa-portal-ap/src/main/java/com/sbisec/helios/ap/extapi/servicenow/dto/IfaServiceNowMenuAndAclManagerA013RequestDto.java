package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;

/**
 * ユーザ利用可能メニュー一覧取得 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowMenuAndAclManagerA013RequestDto {
    
    /** ユーザーID. */
    private String userId;
    
}

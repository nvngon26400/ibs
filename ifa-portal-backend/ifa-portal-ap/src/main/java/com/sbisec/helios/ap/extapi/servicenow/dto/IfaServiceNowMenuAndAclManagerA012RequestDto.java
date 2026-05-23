package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;

/**
 * ユーザ利用できるメニューを登録 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowMenuAndAclManagerA012RequestDto {
    
    /** ユーザーID. */
    private String userId;
    
    /** メニューコード. */
    private String menuId;
    
    /** 権限コード. */
    private String privId;
    
    /** 仲介業者コード. */
    private String brokerCode;

    /** 登録者. */
    private String createUser;
    
    /** 更新者. */
    private String uptimestampUser;
    
}

package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA001DtoRequest {

    /** ログインID. */
    private String loginId;
    
    /** 権限コード（全角半角）. */
    private String privId;
    
}

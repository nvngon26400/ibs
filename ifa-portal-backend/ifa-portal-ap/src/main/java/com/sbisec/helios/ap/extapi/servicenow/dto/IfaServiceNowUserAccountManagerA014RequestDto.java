package com.sbisec.helios.ap.extapi.servicenow.dto;

import lombok.Data;

/**
 * ログインID検索 リクエストDto
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowUserAccountManagerA014RequestDto {
    
    /** 仲介業者コード. */
    private String brokerCode;

    /** 仲介業者名. */
    private String brokerOrBranchName;
    
    /** ユーザー名. */
    private String employeeName;
    
    /** メールアドレス. */
    private String mailAddress;
    
}

package com.sbisec.helios.ap.extapi.servicenow.dto.common;

import lombok.Data;

/**
 * ユーザー Dto
 *
 * @author SCSK
 *
 */
@Data
public class IfaServiceNowUserDto {
    
    /** ユーザーID. */
    private String userId;

    /** ユーザー名. */
    private String userNm;

    /** 権限コード. */
    private String privId;

    /** 本支店コード. */
    private String branchCode;
    
    /** 本支店名. */
    private String branchName;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 仲介業者支店コード. */
    private String brokerBranchCode;
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
    /** 仲介業者営業員コード. */
    private String employeeId;
    
    /** 仲介業者担当者名. */
    private String employeeName;

    /** メールアドレス. */
    private String mailAddress;
    
}

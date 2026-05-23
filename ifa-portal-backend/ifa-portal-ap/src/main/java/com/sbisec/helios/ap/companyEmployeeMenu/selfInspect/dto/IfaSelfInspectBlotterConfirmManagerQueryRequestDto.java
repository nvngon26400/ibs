package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto;

import lombok.Data;

/**
 *　SUB0506_01-01_自己点検記録簿確認（管理者用）A002,3,4要求
 *
 * @author SCSK
 *
 */
@Data
public class IfaSelfInspectBlotterConfirmManagerQueryRequestDto {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 登録年月. */
    private String registerDate;
    
    /** 回答状況. */
    private String answerStatus;
    
    /** 回答結果（半角英数字）. */
    private String answerResult;
    
}

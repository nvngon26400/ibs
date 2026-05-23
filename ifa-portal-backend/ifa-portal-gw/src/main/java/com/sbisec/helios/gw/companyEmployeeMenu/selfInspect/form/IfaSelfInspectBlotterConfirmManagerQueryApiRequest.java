package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * SUB0506_01-01_自己点検記録簿確認（管理者用）A002,3,4共通要求
 *
 * @author SCSK
 *
 */
@Data
public class IfaSelfInspectBlotterConfirmManagerQueryApiRequest {
    
    /** 仲介業者コード（数字）. */
    @Pattern(regexp = "[0-9]*", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    @Size(max = 80, message = "仲介業者名")
    private String brokerName;
    
    /** 登録年月. */
    @DateTimeFormat(pattern = "yy年MM月")
    @JsonFormat(pattern = "yy年MM月")
    @NotEmpty(message = "登録年月")
    private String registerDate;
    
    /** 回答状況. */
    @NotEmpty(message = "回答状況")
    @Size(min = 1, max = 1, message = "回答状況")
    private String answerStatus;
    
    /** 回答結果（半角英数字）. */
    @NotEmpty(message = "回答結果")
    @Size(min = 1, max = 1, message = "回答結果")
    private String answerResult;
    
}

package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * SUB0506_01-02_自己点検記録簿詳細 API要求
 *
 * @author SCSK
 *
 */
@Data
public class IfaSelfInspectBlotterDetailA001ApiRequest {
    
    /** 登録年月. */
    @DateTimeFormat(pattern = "yy年MM月")
    @JsonFormat(pattern = "yy年MM月")
    @NotEmpty(message = "登録年月")
    @Size(min = 6, max = 6, message = "登録年月")
    private String registerDate;
    
    /** 仲介業者コード（数字）. */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp = "[0-9]*", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    @NotEmpty(message = "仲介業者名")
    @Size(max = 80, message = "仲介業者名")
    private String brokerName;
    
}

package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaRepAddA008ApiRequest {
    
    /** 権限コード（全角半角）. */
    @NotEmpty(message = "権限コード")
    @Size(max = 16, message = "権限コード")
    private String privId;
    
    /** SBI証券支店コード（数字）. */
    @NotEmpty(message = "SBI証券支店コード")
    @Pattern(regexp = "0-9", message = "SBI証券支店コード")
    @Size(max = 3, message = "SBI証券支店コード")
    private String sbiSecurityCode;
    
    /** 仲介業者コード（数字）. */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp = "0-9", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者支店コード（数字）. */
    @NotEmpty(message = "仲介業者支店コード")
    @Pattern(regexp = "0-9", message = "仲介業者支店コード")
    @Size(max = 3, message = "仲介業者支店コード")
    private String subBrokerId;
    
}

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
public class IfaRepAddA002ApiRequest {
    
    /** 権限コード（全角半角）. */
    @NotEmpty(message = "権限コード")
    @Size(max = 16, message = "権限コード")
    private String privId;
    
    /** 仲介業者コード（数字）. */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp = "0-9", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
}

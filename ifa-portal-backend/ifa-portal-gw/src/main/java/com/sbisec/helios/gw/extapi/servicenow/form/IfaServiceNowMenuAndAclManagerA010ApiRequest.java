package com.sbisec.helios.gw.extapi.servicenow.form;

import javax.validation.constraints.NotEmpty;

import com.sbisec.helios.gw.extapi.servicenow.validator.PrivId;

import lombok.Data;

/**
 * メニュー取得
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowMenuAndAclManagerA010ApiRequest {
    
    /** 権限コード. */
    @NotEmpty(message = "権限コード")
    @PrivId
    private String privId;
    
}

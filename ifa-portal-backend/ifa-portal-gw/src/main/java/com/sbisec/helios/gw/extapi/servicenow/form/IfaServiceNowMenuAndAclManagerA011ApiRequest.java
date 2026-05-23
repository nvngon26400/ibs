package com.sbisec.helios.gw.extapi.servicenow.form;

import javax.validation.constraints.NotEmpty;

import com.sbisec.helios.gw.extapi.servicenow.validator.UserId;

import lombok.Data;

/**
 * ユーザ利用できるメニューを削除
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowMenuAndAclManagerA011ApiRequest {
    
    /** ユーザーID. */
    @NotEmpty(message = "ログインID")
    @UserId
    private String userId;
    
}

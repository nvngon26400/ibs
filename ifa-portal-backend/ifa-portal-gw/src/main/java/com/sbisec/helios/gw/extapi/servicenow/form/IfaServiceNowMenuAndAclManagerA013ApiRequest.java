package com.sbisec.helios.gw.extapi.servicenow.form;

import javax.validation.constraints.NotEmpty;

import com.sbisec.helios.gw.extapi.servicenow.validator.UserId;

import lombok.Data;

/**
 * ユーザ利用可能メニュー一覧取得
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowMenuAndAclManagerA013ApiRequest {
    
    /** ユーザーID. */
    @NotEmpty(message = "ログインID")
    @UserId
    private String userId;
    
}

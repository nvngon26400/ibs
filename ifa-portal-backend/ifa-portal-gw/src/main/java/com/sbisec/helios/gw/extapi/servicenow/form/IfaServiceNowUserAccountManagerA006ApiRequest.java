package com.sbisec.helios.gw.extapi.servicenow.form;

import javax.validation.constraints.NotEmpty;

import com.sbisec.helios.gw.extapi.servicenow.validator.UserId;

import lombok.Data;

/**
 * 申請情報項目一覧を取得
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowUserAccountManagerA006ApiRequest {
    
    /** ユーザーID. */
    @NotEmpty(message = "ログインID")
    @UserId
    private String userId;
    
}

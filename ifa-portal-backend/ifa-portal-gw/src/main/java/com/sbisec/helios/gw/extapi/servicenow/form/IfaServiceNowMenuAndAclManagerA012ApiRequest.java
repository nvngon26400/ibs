package com.sbisec.helios.gw.extapi.servicenow.form;

import javax.validation.constraints.NotEmpty;

import com.sbisec.helios.gw.extapi.servicenow.validator.CreateUser;
import com.sbisec.helios.gw.extapi.servicenow.validator.ServiceNowMenuId;
import com.sbisec.helios.gw.extapi.servicenow.validator.PrivId;
import com.sbisec.helios.gw.extapi.servicenow.validator.BrokerCode;
import com.sbisec.helios.gw.extapi.servicenow.validator.UptimestampUser;
import com.sbisec.helios.gw.extapi.servicenow.validator.UserId;

import lombok.Data;

/**
 * ユーザ利用できるメニューを登録
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowMenuAndAclManagerA012ApiRequest {
    
    /** ユーザーID. */
    @NotEmpty(message = "ログインID")
    @UserId
    private String userId;
    
    /** メニューコード. */
    @ServiceNowMenuId
    private String menuId;
    
    /** 権限コード. */
    @NotEmpty(message = "権限コード")
    @PrivId
    private String privId;
    
    /** 仲介業者コード. */
    @BrokerCode
    private String brokerCode;

    /** 登録者. */
    @NotEmpty(message = "登録者")
    @CreateUser
    private String createUser;
    
    /** 更新者. */
    @NotEmpty(message = "更新者")
    @UptimestampUser
    private String uptimestampUser;
    
}

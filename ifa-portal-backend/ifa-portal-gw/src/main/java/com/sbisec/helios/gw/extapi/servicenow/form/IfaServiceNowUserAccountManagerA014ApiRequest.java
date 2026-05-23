package com.sbisec.helios.gw.extapi.servicenow.form;

import com.sbisec.helios.gw.extapi.servicenow.validator.BrokerName;
import com.sbisec.helios.gw.extapi.servicenow.validator.UserNm;
import com.sbisec.helios.gw.extapi.servicenow.validator.MailAddressNoPattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * ログインID検索
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowUserAccountManagerA014ApiRequest {
    
    /** 仲介業者コード. */
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者名. */
    @BrokerName
    private String brokerOrBranchName;
    
    /** ユーザー名. */
    @UserNm
    private String employeeName;
    
    /** メールアドレス. */
    @MailAddressNoPattern
    private String mailAddress;
    
}

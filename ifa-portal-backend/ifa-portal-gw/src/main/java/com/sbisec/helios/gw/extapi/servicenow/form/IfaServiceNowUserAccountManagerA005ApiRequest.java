package com.sbisec.helios.gw.extapi.servicenow.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.sbisec.helios.gw.extapi.servicenow.form.common.IfaServiceNowBroker;
import com.sbisec.helios.gw.extapi.servicenow.form.common.IfaServiceNowMenu;
import com.sbisec.helios.gw.extapi.servicenow.validator.GovernorFlag;
import com.sbisec.helios.gw.extapi.servicenow.validator.MailAddress;
import com.sbisec.helios.gw.extapi.servicenow.validator.Password;
import com.sbisec.helios.gw.extapi.servicenow.validator.PrivId;
import com.sbisec.helios.gw.extapi.servicenow.validator.UserId;
import com.sbisec.helios.gw.extapi.servicenow.validator.UserNm;

import lombok.Data;

/**
 * ログインID登録
 *
 * @author SCSK
 */
@Data
public class IfaServiceNowUserAccountManagerA005ApiRequest {
    
    /** ユーザーID. */
    @NotEmpty(message = "ログインID")
    @UserId
    private String userId;
    
    /** ユーザー名. */
    @NotEmpty(message = "ユーザー名")
    @UserNm
    private String userNm;
    
    /** パスワード. */
    @NotEmpty(message = "パスワード")
    @Password
    private String password;
    
    /** 権限コード. */
    @NotEmpty(message = "権限コード")
    @PrivId
    private String privId;
    
    /** 管理者フラグ. */
    @GovernorFlag
    private String governorFlag;
    
    /** メニューリスト. */
    @Valid
    private List<IfaServiceNowMenu> menuList;
    
    /** 仲介業者リスト. */
    @Valid
    private List<IfaServiceNowBroker> brokerList;
    
    /** メールアドレス. */
    @MailAddress
    private String mailAddress;
    
    /** CCSユーザーID */
    // 条件付きのため単項目チェックは無し
    private String ccsUserId;
    
}

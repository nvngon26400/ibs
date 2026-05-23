package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA014ApiRequest {
    
    /** ログインID. */
    @NotEmpty(message = "ログインID")
    @Size(max = 16, message = "ログインID")
    private String loginId;
    
    /** パスワード. */
    @NotEmpty(message = "パスワード")
    @Size(max = 32, message = "パスワード")
    private String password;
    
    /** ユーザー名. */
    @NotEmpty(message = "ユーザー名")
    private String userName;
    
    /** メールアドレス（メールアドレス）. */
    @NotEmpty(message = "メールアドレス")
    @Size(max = 127, message = "メールアドレス")
    private String mailAddress;
    
    /** 所属権限コード. */
    @NotEmpty(message = "所属権限コード")
    private String privId;
    
    /** 本支店コード. */
    @NotEmpty(message = "本支店コード")
    private String branchCode;
    
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
    
    /** 担当者コード. */
    @NotEmpty(message = "担当者コード")
    private String employeeId;
    
    /** 担当者名（全角半角）. */
    @NotEmpty(message = "担当者名")
    @Size(max = 80, message = "担当者名")
    private String chargeName;
    
    /** 表示リスト */
    @NotEmpty(message = "表示リスト")
    private List<String> displayList;
    
}

package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA001ApiRequest {
    
    /** ログインID. */
    @NotEmpty(message = "ログインID")
    @Size(max = 16, message = "ログインID")
    private String loginId;
    
    /** 担当者数（数値(整数)）. */
    @NotEmpty(message = "担当者数")
    @Size(max = 5, message = "担当者数")
    private String repNumber;
    
    /** 権限コード（全角半角）. */
    @NotEmpty(message = "権限コード")
    @Size(max = 16, message = "権限コード")
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
    
    /** 仲介業者担当者コード（数字）. */
    @NotEmpty(message = "仲介業者担当者コード")
    @Pattern(regexp = "0-9", message = "仲介業者担当者コード")
    private String employeeId;
    
    /** 社員名担当者名（全角半角）. */
    @NotEmpty(message = "社員名担当者名")
    @Size(max = 80, message = "社員名担当者名")
    private String employeeNameChargeName;
    
    /** メニューリスト */
    @NotEmpty(message = "メニューリスト")
    private List<IfaLoginIdUpdateRegisterApiResponse_menuList> menuList;
    
    /**　メールアドレス　*/
    @NotEmpty(message = "メールアドレス")
    private String mailAddress;
    
}

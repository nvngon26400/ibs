package com.sbisec.helios.gw.systemManageMenu.loginUserManage.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA002ApiRequest {
    
    /** 所属権限コード. */
    @NotEmpty(message = "所属権限コード")
    private String privId;
    
    /** ログインID. */
    @NotEmpty(message = "ログインID")
    @Size(max = 16, message = "ログインID")
    private String loginId;
    
    /** 非表示リスト. */
    @NotEmpty(message = "非表示リスト")
    private List<IfaLoginIdUpdateRegisterApiResponse_unDisplay> unDisplayList;
    
    /** 表示リスト. */
    @NotEmpty(message = "表示リスト")
    private List<String> displayList;
}

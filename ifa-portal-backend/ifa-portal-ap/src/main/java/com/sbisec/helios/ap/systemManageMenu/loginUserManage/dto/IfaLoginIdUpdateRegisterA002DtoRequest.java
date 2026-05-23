package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterA002DtoRequest {
    
    /** 所属権限コード. */
    private String privId;
    
    /** ログインID. */
    private String loginId;
    
    /** 非表示リスト. */
    private List<IfaLoginIdUpdateRegisterDtoResponse_unDisplay> unDisplayList;
    
    /** 表示リスト. */
    private List<String> displayList;
}

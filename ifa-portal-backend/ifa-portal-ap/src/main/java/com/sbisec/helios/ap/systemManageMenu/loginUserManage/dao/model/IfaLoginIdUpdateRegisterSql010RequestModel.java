package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import java.util.List;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterSql010RequestModel {
    
    /** ログインID（英数字記号A(+-_./@*#%)）. */
    private String loginId;
    
    /** ユーザID. */
    private String UserId;
    
    /**表示リスト*/
    private List<String> displayList;
}

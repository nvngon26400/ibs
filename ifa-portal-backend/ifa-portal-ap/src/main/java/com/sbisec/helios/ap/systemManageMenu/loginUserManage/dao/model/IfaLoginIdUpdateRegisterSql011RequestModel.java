package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterSql011RequestModel {
    
    /** ログインID（英数字記号A(+-_./@*#%)）. */
    private String loginId;
    
}

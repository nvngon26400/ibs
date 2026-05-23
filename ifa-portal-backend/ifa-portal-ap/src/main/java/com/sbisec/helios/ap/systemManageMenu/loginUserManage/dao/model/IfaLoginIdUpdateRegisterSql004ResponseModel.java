package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model;

import lombok.Data;

@Data
public class IfaLoginIdUpdateRegisterSql004ResponseModel {
    
    /**仲介業者コード */
    private String brokerCode;
    
    /**仲介業支店コード*/
    private String brokerBranchCode;
    
    /**仲介業者支店種別*/
    private String brokerBranchKind;
    
    /**仲介業者支店名*/
    private String branchName;
    
}

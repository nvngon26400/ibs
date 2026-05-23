package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaInfoNewRegisterSql005ResponseModel {
    
    /** メールアドレス */
    private String mailAddress;
    
    /** ユーザー名 */
    private String userNm;
    
    /** 支店名 */
    private String branchName;
}

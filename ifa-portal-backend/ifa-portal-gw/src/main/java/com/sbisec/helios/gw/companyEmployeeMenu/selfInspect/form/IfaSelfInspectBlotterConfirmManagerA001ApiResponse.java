package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form;

import java.util.List;

import lombok.Data;

/**
 *　SUB0506_01-01_自己点検記録簿確認（管理者用）A001応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaSelfInspectBlotterConfirmManagerA001ApiResponse {
    
    /**
     * 登録年月
     *
     * @author SCSK
     *
     */
    @Data
    public static class RegisterDate {
        
        /** yyyymm */
        private String codeId;
        
        /** yyyy年mm月 */
        private String codeName;
    }
    
    /** 登録年月リスト. */
    private List<RegisterDate> registerDateList;
    
}

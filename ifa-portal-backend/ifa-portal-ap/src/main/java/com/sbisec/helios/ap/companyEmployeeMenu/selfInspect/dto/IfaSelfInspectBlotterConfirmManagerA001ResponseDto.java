package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *　SUB0506_01-01_自己点検記録簿確認（管理者用）A001応答
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaSelfInspectBlotterConfirmManagerA001ResponseDto {
    
    /**
     * 登録年月
     *
     * @author SCSK
     *
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterDate {
        
        /** yyyymm */
        private String codeId;
        
        /** yyyy年mm月 */
        private String codeName;
    }
    
    /** 登録年月リスト. */
    private List<RegisterDate> registerDateList;
    
}

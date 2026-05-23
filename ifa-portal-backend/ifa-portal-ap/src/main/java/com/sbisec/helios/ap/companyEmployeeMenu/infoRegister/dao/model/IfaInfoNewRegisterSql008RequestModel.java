package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaInfoNewRegisterSql008RequestModel {
    
    /** 機能ID */
    private String funcId;
    
    /** カテゴリID */
    private String catId;
}

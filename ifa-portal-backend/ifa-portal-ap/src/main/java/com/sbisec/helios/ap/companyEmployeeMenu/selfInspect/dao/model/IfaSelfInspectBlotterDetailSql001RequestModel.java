package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SUB0506_01-02_自己点検記録簿詳細 SQL001要求
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaSelfInspectBlotterDetailSql001RequestModel {
    
    /** 登録年月. */
    private String registerDate;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
}

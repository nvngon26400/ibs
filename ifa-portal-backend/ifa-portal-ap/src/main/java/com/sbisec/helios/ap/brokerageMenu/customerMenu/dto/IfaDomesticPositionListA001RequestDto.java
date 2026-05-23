package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内建玉一覧A001リクエストDTO
 *
 * @author SCSK 金志
 * 
 */
@Data
public class IfaDomesticPositionListA001RequestDto {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
}

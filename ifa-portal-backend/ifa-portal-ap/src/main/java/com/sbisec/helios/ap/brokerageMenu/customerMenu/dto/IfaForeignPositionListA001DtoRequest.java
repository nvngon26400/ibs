package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株建玉一覧 A001 リクエストパラメタ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignPositionListA001DtoRequest {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
}

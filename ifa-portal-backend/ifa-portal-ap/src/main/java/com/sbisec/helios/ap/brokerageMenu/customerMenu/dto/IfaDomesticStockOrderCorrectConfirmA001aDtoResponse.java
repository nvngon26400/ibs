package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaDomesticStockOrderCorrectConfirmA001aDtoResponse {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
}

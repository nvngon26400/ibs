package com.sbisec.helios.gw.brokerageMenu.customerList.form;

import lombok.Data;

@Data
public class IfaCustomerListMarginA005bApiRequest {
    
    /** CSVファイル名. */
    private String csvDownloadFile;
    
    private String pattern;
}

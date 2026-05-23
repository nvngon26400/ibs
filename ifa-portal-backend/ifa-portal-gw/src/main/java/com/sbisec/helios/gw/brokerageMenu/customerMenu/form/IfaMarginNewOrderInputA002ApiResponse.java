package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaMarginNewOrderInputA002ApiResponse {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 規制銘柄区分. */
    private String regKbn;
    
    /** 東証一般信用区分. */
    private String mktIppanLoanKbnTky;
    
    /** 名証一般信用区分. */
    private String mktIppanLoanKbnNgy;
    
    /** 福証一般信用区分. */
    private String mktIppanLoanKbnFko;
    
    /** 札証一般信用区分. */
    private String mktIppanLoanKbnSpr;
    
    /**　営業日リスト　*/
    private String businessDayList;
    
}

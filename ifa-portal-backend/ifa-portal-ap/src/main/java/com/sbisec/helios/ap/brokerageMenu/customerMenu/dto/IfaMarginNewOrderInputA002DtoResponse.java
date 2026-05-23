package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaMarginNewOrderInputA002DtoResponse {
    
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
    
    /** 東証貸借区分. */
    private String mktLoanKbnTky;
    
    /** PTS貸借区分. */
    private String mktLoanKbnPts;
    
    /** PTS一般信用区分. */
    private String mktIppanLoanKbnPts;
    
    /**　営業日リスト　*/
    private String businessDayList;
}

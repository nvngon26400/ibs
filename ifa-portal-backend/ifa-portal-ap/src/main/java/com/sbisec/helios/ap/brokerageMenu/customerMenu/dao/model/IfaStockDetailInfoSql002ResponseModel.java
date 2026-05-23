package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaStockDetailInfoSql002ResponseModel {
    
    /** SOR取扱区分. */
    private String SorServiceKbn;
    
    /** 一日信用買建区分. */
    private String dayMgBuyKbn;
    
    /** 一日信用売建区分. */
    private String dayMgSellKbn;
    
    /** プレミアム空売り区分. */
    private String premiumShortSellingKbn;
    
    /** 東証貸借区分. */
    private String mktLoanKbnTky;
    
    /** 名証貸借区分. */
    private String mktLoanKbnNgy;
    
    /** 福証貸借区分. */
    private String mktLoanKbnFko;
    
    /** 札証貸借区分. */
    private String mktLoanKbnSpr;
    
    /** PTS貸借区分. */
    private String mktLoanKbnPts;
    
}

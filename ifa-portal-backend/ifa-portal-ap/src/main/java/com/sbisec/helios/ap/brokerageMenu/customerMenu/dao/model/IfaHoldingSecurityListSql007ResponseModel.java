package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;


@Data
public class IfaHoldingSecurityListSql007ResponseModel {
    
    /** SOR取扱区分 */
    private String sorServiceKbn;
    
    /** 東証上場区分 */
    private String mktKbnTky;
    
    /** 名証上場区分 */
    private String mktKbnNgy;
    
    /** 福証上場区分 */
    private String mktKbnFko;
    
    /** 札証上場区分 */
    private String mktKbnSpr;
    
    /** PTS */
    private int pts;
}

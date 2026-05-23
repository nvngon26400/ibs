package com.sbisec.helios.ap.common.composite.model;

import lombok.Data;

@Data
public class IfaBrandSearchSql003ResponseModel {
    
    /** SOR. */
    private String mktSor;
    
    /** 東証. */
    // 手修正
    private String mktTky;
    
    /** 名証. */
    // 手修正
    private String mktNgy;
    
    /** 福証. */
    // 手修正
    private String mktFko;
    
    /** 札証. */
    // 手修正
    private String mktSpr;
    
    /** PTS. */
    // 手修正
    private String mktPts;
    
}

package com.sbisec.helios.ap.common.composite.model;

import lombok.Data;

@Data
public class IfaBrandSearchSql004ResponseModel {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 東証. */
    private String mktTky;
    
    /** 名証. */
    private String mktNgy;
    
    /** 福証. */
    private String mktFko;
    
    /** 札証. */
    private String mktSpr;
    
    /** PTS. */
    private String mktPts;
    
    /** 最上位上場市場（全角半角）. */
    private String upperRankMkt;
    
}

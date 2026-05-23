package com.sbisec.helios.ap.common.composite.dto;

import lombok.Data;

@Data
public class IfaBrandSearchA002DtoResponse_SearchResultDetail {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
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
    
    /** 最上位上場市場（全角半角）. */
    private String upperRankMkt;
    
}

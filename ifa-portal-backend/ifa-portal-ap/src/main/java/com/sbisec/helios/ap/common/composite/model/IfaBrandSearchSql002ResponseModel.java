package com.sbisec.helios.ap.common.composite.model;

import lombok.Data;

@Data
public class IfaBrandSearchSql002ResponseModel {
    
    /** TOPIX採用フラグ（全角半角）. */
    private String topixFlg;
    
    /** 規制銘柄区分（全角半角）. */
    private String regKbn;
    
    /** SOR取扱区分. */
    private String sorServiceKbn;
    
    /** 最上位上場市場（全角半角）. */
    private String upperRankMkt;
    
}

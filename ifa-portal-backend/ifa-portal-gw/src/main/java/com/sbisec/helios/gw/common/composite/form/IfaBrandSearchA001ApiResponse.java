package com.sbisec.helios.gw.common.composite.form;

import lombok.Data;

@Data
public class IfaBrandSearchA001ApiResponse {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 銘柄市場リスト. */
    // TODO 項目辞書未登録
    private String brandMarketList;
    
    /** TOPIX採用フラグ（全角半角）. */
    private String topixFlg;
    
    /** 規制銘柄区分（全角半角）. */
    private String regKbn;
    
    /** 最上位上場市場（全角半角）. */
    private String upperRankMkt;
    
}

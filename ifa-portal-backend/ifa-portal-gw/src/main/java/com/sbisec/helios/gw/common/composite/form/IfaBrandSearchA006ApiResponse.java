package com.sbisec.helios.gw.common.composite.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaBrandSearchA006ApiResponse {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 銘柄市場リスト. */
    // 手修正
    private List<IfaBrandSearchA006ApiResponse_SelectMarketList> selectMarketList;
    
    /** TOPIX採用フラグ（全角半角）. */
    private String topixFlg;
    
    /** 規制銘柄区分（全角半角）. */
    private String regKbn;
    
    /** 最上位上場市場（全角半角）. */
    private String upperRankMkt;
    
}

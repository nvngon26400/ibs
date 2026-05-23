package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
 * 仕組債銘柄情報　参照銘柄

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoDtoResponseReferenceBrand {
    
    /** 表示順（数字）. */
    private String disporder;
    
    /** 判定銘柄種別. */
    private String judgmentBrandClass;
    
    /** 判定市場コード. */
    private String judgmentMarketCode;
    
    /** 判定銘柄コード. */
    private String judgmentBrandCode;
    
    /** 判定銘柄名. */
    private String judgmentBrandName;
    
    /** 単位. */
    private String unit;
    
    /** 当初価格. */
    private String initiallyPrice;
    
    /** ノックイン水準価格（数値(小数)）. */
    private String knockInLevelPrice;
    
    /** 早期償還水準価格（数値(小数)）. */
    private String earlyRedemptionLevelPrice;
    
    /** ノックイン発生日. */
    private String knockInAccuralDate;
    
    /** ノックイン発生時価格. */
    private String knockInAccuralTimePrice;
    
}

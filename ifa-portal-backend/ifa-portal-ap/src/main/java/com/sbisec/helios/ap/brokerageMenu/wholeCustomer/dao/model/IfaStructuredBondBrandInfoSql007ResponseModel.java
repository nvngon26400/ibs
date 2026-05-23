package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 仕組債銘柄情報　SQL007　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoSql007ResponseModel {
    
    /** 表示順. */
    private String sbjmDisplayOrder;
    
    /** 判定銘柄種別. */
    private String sbjmGroupCode;
    
    /** 判定市場コード. */
    private String sbjmExchangeCode;
    
    /** 判定銘柄コード. */
    private String sbjmTickerCode;
    
    /** 判定銘柄名. */
    private String sbjmBrandName;
    
    /** 汎用フィールド4. */
    private String flexField04;
    
    /** 当初価格. */
    private String sbjmFirstPrice;
    
    /** ノックイン水準価格（数値(小数)）. */
    private String sbjmNockinPrice;
    
    /** 早期償還水準価格（数値(小数)）. */
    private String sbjmEarlyPaymentPrice;
    
    /** ノックイン発生日. */
    private String sbjmNockinOnsetDate;
    
    /** ノックイン発生時価格. */
    private String sbjmNockinOnsetPrice;
    
}

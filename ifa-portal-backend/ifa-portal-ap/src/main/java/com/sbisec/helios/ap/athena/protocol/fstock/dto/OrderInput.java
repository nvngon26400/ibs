package com.sbisec.helios.ap.athena.protocol.fstock.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @Description 注文情報 Dto
 *
 * @author SCSK 宇田川達弥
 * @date 2024/02/16
 */
@Data
public class OrderInput implements Serializable {
    
    private static final long serialVersionUID = 1868671673377015934L;
    
    /** 国コード */
    private String countryCode;
    
    /** 市場コード */
    private String marketCode;
    
    /** 銘柄コード */
    private String securitiesCode;
    
    /** 売買区分 */
    private String buySellCode;
    
    /** 注文数量 */
    private String orderQuantity;
    
    /** 価格条件 */
    private String orderPriceKindCode;
    
    /** 注文単価 */
    private String orderPrice;
    
    /** 発火条件価格 */
    private String stopPrice;
    
    /** トレールストップ幅 */
    private String trailingStopAmount;
    
    /** 成行基準価格 */
    private String noLimitPrice;
    
    /** 期間条件 */
    private String orderLimitCode;
    
    /** 期間 "yyyy-MM-dd"形式 */
    private String orderTerm;
    
    /** 預り区分 */
    private String specificAccountCode;
    
    /** 決済方法 */
    private String settlementMethodCode;
    
    /** 余力チェック不要 */
    private Boolean remainingPowerCheckDisabled;
    
    /** NISA枠チェック不要 */
    private Boolean nisaRemainingPowerCheckDisabled;
    
}

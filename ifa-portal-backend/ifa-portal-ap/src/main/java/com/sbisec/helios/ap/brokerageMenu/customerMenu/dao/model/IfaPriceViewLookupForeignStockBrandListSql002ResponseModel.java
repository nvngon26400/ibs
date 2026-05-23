package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Data
public class IfaPriceViewLookupForeignStockBrandListSql002ResponseModel {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 銘柄名 */
    private String brandName;
    
    /** 更新時間 */
    private String updateTime;
    
    /** 売却価格 */
    private BigDecimal sellingPrice;
    
    /** 基準価格（仲値） */
    private BigDecimal basePrice;
    
    /** 買付価格 */
    private BigDecimal buyingPrice;
    
    /** 前日終値 */
    private BigDecimal prevDayClosingPrice;
    
    /** 前日比（％） */
    private BigDecimal prevDayRatio;
    
    /** メッセージ */
    private String message;
    
    /** 販売状態 */
    private String tradeState;
    
    /** 買枠（枚数） */
    private Long buyTradeQuotaRemaining;
    
    /** 売枠（枚数） */
    private Long sellTradeQuotaRemaining;
    
    /** 取引開始時間 */
    private String tradeStartTime;
    
    /** 取引終了時間 */
    private String tradeEndTime;
}

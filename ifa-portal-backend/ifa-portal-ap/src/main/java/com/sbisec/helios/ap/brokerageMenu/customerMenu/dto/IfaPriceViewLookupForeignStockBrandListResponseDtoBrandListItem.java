package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Data
public class IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem {
    
    /** ティッカー */
    private String ticker;
    
    /** 名称 */
    private String name;
    
    /** 売却価格 */
    private String sellPrice;
    
    /** 基準価格 */
    private String basePrice8;
    
    /** 買付価格 */
    private String buyPrice;
    
    /** 前日終値 */
    private String last;
    
    /** 前日比 */
    private String diff;
    
    /** 取引停止/価格変更理由 */
    private String tradeSuspendPriceChangeReason;
    
}

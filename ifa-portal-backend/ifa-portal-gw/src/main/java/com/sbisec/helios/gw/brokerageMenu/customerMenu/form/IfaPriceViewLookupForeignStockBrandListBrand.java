package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Data
public class IfaPriceViewLookupForeignStockBrandListBrand {
    
    /** ティッカー（全角半角）. */
    private String ticker;
    
    /** 名称. */
    private String name;
    
    /** 売却価格. */
    private String sellPrice;
    
    /** 基準価格（数値(整数)）. */
    private String basePrice8;
    
    /** 買付価格. */
    private String buyPrice;
    
    /** 前日終値（数値(小数)）. */
    private String last;
    
    /** 前日比（数値(小数)）. */
    private String diff;
    
    /** 取引停止/価格変更理由（全角半角）. */
    private String tradeSuspendPriceChangeReason;
    
}

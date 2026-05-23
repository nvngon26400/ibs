package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderConfirmA004ApiResponse_priceBasicInfo {
    
    /** 現在値（数値(小数)）. */
    private String currentPrice;
    
    /** 現在値日時. */
    private String currentDateTime;
    
    /** ティック矢印(アップorダウン). */
    private String tickArrow;
    
    /** 前日比（数値(小数)）. */
    private String diff;
    
    /** 前日比(%). */
    private String diffPercentage;
    
    /** 始値（数値(小数)）. */
    private String start;
    
    /** 高値（数値(小数)）. */
    private String high;
    
    /** 安値（数値(小数)）. */
    private String low;
    
    /** 出来高（数値(整数)）. */
    private String volume;
    
    /** 前日終値（数値(小数)）. */
    private String last;
    
    /** 前日終値日付. */
    private String lastDate;
    
}

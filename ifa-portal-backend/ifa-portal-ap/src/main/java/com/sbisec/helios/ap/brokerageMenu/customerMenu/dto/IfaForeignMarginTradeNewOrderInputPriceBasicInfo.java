package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 価格基本情報
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputPriceBasicInfo {
    
    /** 現在値（数値(小数)）. */
    private String currentPrice;
    
    /** 現在値日時. */
    private String currentDateTime;
    
    /** ティック矢印(アップorダウン). */
    private String tick;
    
    /** 前日比（数値(小数)）. */
    private String diff;
    
    /** 前日比(%). */
    private String ratio;
    
    /** 始値（数値(小数)）. */
    private String start;
    
    /** 始値日時. */
    private String startDate;
    
    /** 高値（数値(小数)）. */
    private String high;
    
    /** 高値日時. */
    private String highDate;
    
    /** 安値（数値(小数)）. */
    private String low;
    
    /** 安値日時. */
    private String lowDate;
    
    /** 出来高（数値(整数)）. */
    private String volume;
    
    /** 前日終値（数値(小数)）. */
    private String last;
    
    /** 前日終値日付. */
    private String lastDate;
    
}

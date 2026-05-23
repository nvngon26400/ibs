package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文入力株価表示レスポンス価格基本情報
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputPriceBasicInfo {

    /** 現在値（数値(小数)）. */
    private String currentPrice;
    
    /** 現在値日時. */
    private String currentPriceDate;
    
    /** ティック矢印(アップorダウン). */
    private String tick;
    
    /** 前日比（数値(小数)）. */
    private String diff;
    
    /** 前日比(%). */
    private String ratio;
    
    /** 始値（数値(小数)）. */
    private String start;
    
    /** 始値日時. */
    private String startPriceDate;
    
    /** 高値（数値(小数)）. */
    private String high;
    
    /** 高値日時. */
    private String highPriceDate;
    
    /** 安値（数値(小数)）. */
    private String low;
    
    /** 安値日時. */
    private String lowPriceDate;
    
    /** 出来高（数値(整数)）. */
    private String volume;
    
    /** 前日終値（数値(小数)）. */
    private String last;
    
    /** 前日終値日付. */
    private String lastDate;

}

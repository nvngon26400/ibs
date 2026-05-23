package com.sbisec.helios.ap.common.composite.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaBrandPriceInfoA002DtoResponse {
    
    /** 銘柄コード. */
    private String brandCode;
    
    /** 市場. */
    private String market;
    
    /** 現在値. */
    private String currentPrice;
    
    /** 現在値ティック. */
    // マッピング定義から取得(他画面を参考)
    private String tick;
    
    /** 現在値フラグ. */
    private String currentFlag;
    
    /** 始値. */
    private String start;
    
    /** 始値更新時刻. */
    private String startTime;
    
    /** 高値. */
    private String high;
    
    /** 高値更新時刻. */
    private String highTime;
    
    /** 安値. */
    private String low;
    
    /** 安値更新時刻. */
    private String lowTime;
    
    /** 前日比. */
    private String diff;
    
    /** 前日比率. */
    private String ratio;
    
    /** 現在値日付. */
    private String updateDate;
    
    /** 現在値更新時刻/前日比更新時刻. */
    private String updateTime4;
    
    /** 前日終値. */
    private String last;
    
    /** 前日終値日付. */
    private String lastDate;
    
    /** 売買代金. */
    private String buySellPrice;
    
    /** 出来高. */
    private String volume;
    
    /** 出来高更新時刻. */
    // 現在値更新時刻/前日比更新時刻と同じためサフィックスを付与
    private String updateTime41;
    
    /** 売買単位. */
    private String unit;
    
    /** ストップ高(売). */
    private String sellStopHigh;
    
    /** ストップ安(売). */
    private String sellStopLow;
    
    /** ストップ高(買). */
    private String buyStopHigh;
    
    /** ストップ安(買). */
    private String buyStopLow;
    
    /** 値幅制限(年月日). */
    private String baseDate;

    /** 基準価格 */
    private String basePrice;
    
    /** 呼値リスト */
    private List<IfaBrandPriceInfoA002DtoResponse_orderPriceUnit> orderPriceUnit;
    
}

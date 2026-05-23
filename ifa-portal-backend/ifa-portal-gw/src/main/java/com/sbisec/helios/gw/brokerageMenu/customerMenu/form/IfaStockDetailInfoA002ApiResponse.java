package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaStockDetailInfoA002ApiResponse {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場（全角）. */
    private String market;
    
    /** 一日信用売建区分. */
    private String onedayCreditSellCategory;
    
    /** 一日信用買建区分. */
    private String onedayCreditBuyCategory;
    
    /** プレミアム空売り区分. */
    private String premiumShortSaleCcategory;
    
    /** 更新日時. */
    private String updateTime;
    
    /** 現在値（数値(小数)）. */
    private String currentPrice;
    
    /** 現在値ティック. */
    private String tick;
    
    /** 現在値フラグ. */
    private String currentFlag;
    
    /** 前日比. */
    private String diff;
    
    /** 前日比率. */
    private String ratio;
    
    /** 現在値日付. */
    private String updateDate;
    
    /** 現在値更新時刻/前日比更新時刻. */
    private String updateTime4;
    
    /** 始値（数値(小数)）. */
    private String start;
    
    /** 始値更新時刻. */
    private String startTime;
    
    /** 高値（数値(小数)）. */
    private String high;
    
    /** 高値更新時刻. */
    private String highTime;
    
    /** 安値（数値(小数)）. */
    private String low;
    
    /** 安値更新時刻. */
    private String lowTime;
    
    /** 前日終値（数値(小数)）. */
    private String last;
    
    /** 前日終値日付. */
    private String lastDate;
    
    /** 出来高（数字）. */
    private String volume;
    
    /** 売買代金（数字）. */
    private String buySellPrice;
    
    /** 成行売注文数量. */
    private String executionSellOrderQuantity;
    
    /** 売気配株数OVER. */
    private String sellSignVolumeOver;
    
    /** 売気配株数10. */
    private String sellSignVolume10;
    
    /** 売気配株数9. */
    private String sellSignVolume9;
    
    /** 売気配株数8. */
    private String sellSignVolume8;
    
    /** 売気配株数7. */
    private String sellSignVolume7;
    
    /** 売気配株数6. */
    private String sellSignVolume6;
    
    /** 売気配株数5. */
    private String sellSignVolume5;
    
    /** 売気配株数4. */
    private String sellSignVolume4;
    
    /** 売気配株数3. */
    private String sellSignVolume3;
    
    /** 売気配株数2. */
    private String sellSignVolume2;
    
    /** 売気配株数1. */
    private String sellSignVolume1;
    
    /** 複数売気配値フラグ. */
    private String sellSignPriceFlg;
    
    /** 売気配値10. */
    private String sellSignPrice10;
    
    /** 売気配値9. */
    private String sellSignPrice9;
    
    /** 売気配値8. */
    private String sellSignPrice8;
    
    /** 売気配値7. */
    private String sellSignPrice7;
    
    /** 売気配値6. */
    private String sellSignPrice6;
    
    /** 売気配値5. */
    private String sellSignPrice5;
    
    /** 売気配値4. */
    private String sellSignPrice4;
    
    /** 売気配値3. */
    private String sellSignPrice3;
    
    /** 売気配値2. */
    private String sellSignPrice2;
    
    /** 売気配値1. */
    private String sellSignPrice1;
    
    /** 買気配値1. */
    private String buySignPrice1;
    
    /** 買気配値2. */
    private String buySignPrice2;
    
    /** 買気配値3. */
    private String buySignPrice3;
    
    /** 買気配値4. */
    private String buySignPrice4;
    
    /** 買気配値5. */
    private String buySignPrice5;
    
    /** 買気配値6. */
    private String buySignPrice6;
    
    /** 買気配値7. */
    private String buySignPrice7;
    
    /** 買気配値8. */
    private String buySignPrice8;
    
    /** 買気配値9. */
    private String buySignPrice9;
    
    /** 買気配値10. */
    private String buySignPrice10;
    
    /** 複数買気配値フラグ. */
    private String buySignPriceFlg;
    
    /** 成行買注文数量. */
    private String ExecutionBuyOrderQuantity;
    
    /** 買気配株数1. */
    private String buySignVolume1;
    
    /** 買気配株数2. */
    private String buySignVolume2;
    
    /** 買気配株数3. */
    private String buySignVolume3;
    
    /** 買気配株数4. */
    private String buySignVolume4;
    
    /** 買気配株数5. */
    private String buySignVolume5;
    
    /** 買気配株数6. */
    private String buySignVolume6;
    
    /** 買気配株数7. */
    private String buySignVolume7;
    
    /** 買気配株数8. */
    private String buySignVolume8;
    
    /** 買気配株数9. */
    private String buySignVolume9;
    
    /** 買気配株数10. */
    private String buySignVolume10;
    
    /** 買気配株数UNDER. */
    private String buySignVolumeUnder;
    
    /** 年初来高値（数値(小数)）. */
    private String yearToDateHigh;
    
    /** 信用売残（数字）. */
    private String marginSellBalance;
    
    /** 信用買残（数字）. */
    private String creditBuyStock;
    
    /** 貸借倍率（数値(小数)）. */
    private String loanMagnification;
    
    /** 年初来安値（数値(小数)）. */
    private String yearToDateLow;
    
    /** 信用買残前週比（数字）. */
    private String creditBuyWeekDiff;
    
    /** 信用売残前週比（数字）. */
    private String creditSellWeekDiff;
    
    /** 信用貸借. */
    private String creditLoan;
    
}

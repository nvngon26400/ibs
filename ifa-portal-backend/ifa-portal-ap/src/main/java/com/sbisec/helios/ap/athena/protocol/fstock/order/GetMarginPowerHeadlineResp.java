package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import lombok.Data;

/**
 * 余力サービス - 外国株式信用建余力取得API Response.
 * 
 * 2023/11/22移植
 */
@Data
public class GetMarginPowerHeadlineResp {
    
    public GetMarginPowerHeadlineResp() {
        
    }
    
    /** 信用建余力 */
    private String marginBuyingPower;
    
    /** 出金指示可能額 */
    private String marginWithdrawable;
    
    /** 出庫余力 */
    private String collateralWithdrawable;
    
    /** 預託率 */
    private String depositRate;
    
    /** 追証ステータス */
    private String marginCallStatus;
    
    /** 追証アラートステータスリスト */
    private List<String> remainingPowerAlertStatus;
    
    /** 直近値洗い区分 */
    private String markToMarketStatus;
    
    /** 追証ワーニング */
    private String marginCallWarning;
    
    /** 新規建不足額合計 */
    private String totalInitialMarginShortfall;
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 最低委託保証金 */
    private String minRequiredMargin;
    
}

package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputBuyingPowerDomestic;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputBuyingPowerForeign;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderInputPriceBasicInfo;

import lombok.Data;

/**
 * 外国現物取引注文入力更新APIレスポンス
 *
 * @author SCSK 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderInputA005ApiResponse {
    
    /** 価格基本情報. */
    private IfaForeignSpotTradeOrderInputPriceBasicInfo priceBasicInfo;
    
    /** 外貨余力リスト. */
    private List<IfaForeignSpotTradeOrderInputBuyingPowerForeign> buyingPowerForeignList;
    
    /** 円貨余力リスト. */
    private List<IfaForeignSpotTradeOrderInputBuyingPowerDomestic> domesticBuyingPowerList;
    
    /** 売却可能数. */
    private String acPosition;
    
    /** 売却可能預り区分. */
    private String sellableCustodyCategory;
    
    /** 売却可能保護区分. */
    private String sellableProtectionCategory;
    
    /** 値幅制限なし. */
    private String priceRangeNoLimit;
    
    /** 値幅下限. */
    private String priceRangeLimitMin;
    
    /** 値幅上限. */
    private String priceRangeLimitMax;
}

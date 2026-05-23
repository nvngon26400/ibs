package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmBrandInformation;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmMarketInformation;

import lombok.Data;

/**
 * 外国現物取引注文取消確認注文取消APIレスポンス
 *
 * @author 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderCancelConfirmA010ApiResponse {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 銘柄情報. */
    private IfaForeignSpotTradeOrderCancelConfirmBrandInformation brandInformationList;
    
    /** 取引通貨. */
    private String limitPriceText;
    
    /** 市場情報. */
    private IfaForeignSpotTradeOrderCancelConfirmMarketInformation marketInformationList;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 注文数量. */
    private String foreignQuantity;
    
    /** 価格条件. */
    private String priceConditionsType;
    
    /** 注文単価（数値(小数)）. */
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    private String stopOrderPrice2;
    
    /** 期間条件. */
    private String periodRadio;
    
    /** 期間. */
    private String period;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 決済方法（半角英数字）. */
    private String kessaiHoho;
    
    /** 注文日時. */
    private String orderDate;
    
    /** リクエスト内容. */
    private IfaForeignSpotTradeOrderCancelConfirmA010ApiRequest request;
    
}

package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmBrandInformation;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignSpotTradeOrderCancelConfirmMarketInformation;

import lombok.Data;

/**
 * 外国現物取引注文取消確認注文取消APIリクエスト
 *
 * @author 宇田川達弥
 */
@Data
public class IfaForeignSpotTradeOrderCancelConfirmA010ApiRequest {
    
    /** 国籍コード（全角半角）. */
    @NotEmpty(message = "国籍コード")
    private String countryCd;
    
    /** 注文Sub番号（数字）. */
    @NotEmpty(message = "注文Sub番号")
    private String orderSubNumber;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    private String tradeCd;
    
    /** 注文番号（数字）. */
    @NotEmpty(message = "注文番号")
    private String orderNumber;
    
    /** 銘柄情報. */
    private IfaForeignSpotTradeOrderCancelConfirmBrandInformation brandInformationList;
    
    /** 取引通貨. */
    @NotEmpty(message = "取引通貨")
    private String limitPriceText;
    
    /** 市場情報. */
    private IfaForeignSpotTradeOrderCancelConfirmMarketInformation marketInformationList;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分")
    private String tradeKbn;
    
    /** 注文数量. */
    @NotEmpty(message = "注文数量")
    private String foreignQuantity;
    
    /** 価格条件. */
    @NotEmpty(message = "価格条件")
    private String priceConditionsType;
    
    /** 注文単価（数値(小数)）. */
    @NotEmpty(message = "注文単価")
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    @NotEmpty(message = "発火条件価格")
    private String stopOrderPrice2;
    
    /** 期間条件. */
    @NotEmpty(message = "期間条件")
    private String periodRadio;
    
    /** 期間. */
    @NotEmpty(message = "期間")
    private String period;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    private String depositType;
    
    /** 決済方法（半角英数字）. */
    @NotEmpty(message = "決済方法")
    private String kessaiHoho;
    
    /** 注文日時. */
    @NotEmpty(message = "注文日時")
    private String orderDate;
    
    /** 国内約定日. */
    @NotEmpty(message = "国内約定日")
    private String domesticTradeDate;
    
    /** 現地約定日. */
    @NotEmpty(message = "現地約定日")
    private String foreignTradeDate;
    
}

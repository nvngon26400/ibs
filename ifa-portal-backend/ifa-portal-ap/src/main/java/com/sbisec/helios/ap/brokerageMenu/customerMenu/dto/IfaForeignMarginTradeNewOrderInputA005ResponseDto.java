package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 A003 レスポンスパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA005ResponseDto {
    
    /** 価格基本情報. */
    private List<IfaForeignMarginTradeNewOrderInputPriceBasicInfo> priceBasicInfo;
    
    /** 在庫株数. */
    private String quantityAvailableForSale;
    
    /** 信用建余力. */
    private String foreignMarginPositionPower;
    
    /** 預託率. */
    private String collateralTransferMarginDepositRateAfter;
    
}

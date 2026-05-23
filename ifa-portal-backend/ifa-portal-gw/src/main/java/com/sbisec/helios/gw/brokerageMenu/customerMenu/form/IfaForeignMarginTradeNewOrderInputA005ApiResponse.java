package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 米株信用取引新規注文入力 A005 レスポンスパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderInputA005ApiResponse {
    
    /** 価格基本情報. */
    private List<IfaForeignMarginTradeNewOrderInputPriceBasicInfo> priceBasicInfo;
    
    /** 在庫株数. */
    private List<String> quantityAvailableForSale;
    
    /** 信用建余力. */
    private String foreignMarginPositionPower;
    
    /** 預託率. */
    private String collateralTransferMarginDepositRateAfter;
    
}

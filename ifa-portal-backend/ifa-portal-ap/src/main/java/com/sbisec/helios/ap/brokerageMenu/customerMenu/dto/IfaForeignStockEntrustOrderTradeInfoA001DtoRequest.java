package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国株式委託注文約定情報A001リクエスト
 *
 * @author SCSK 矢口
 */
@Data
public class IfaForeignStockEntrustOrderTradeInfoA001DtoRequest {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
}

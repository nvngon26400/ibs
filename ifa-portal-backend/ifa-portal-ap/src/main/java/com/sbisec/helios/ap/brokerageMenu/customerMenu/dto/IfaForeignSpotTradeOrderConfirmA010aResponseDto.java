package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 外国現物取引注文確認注文発注レスポンス
 *
 * @author 福岡利基
 */
@Data
public class IfaForeignSpotTradeOrderConfirmA010aResponseDto {
    
    /** IFA注文番号（数字） */
    private String ifaOrderNo;
    
    /** 銘柄種別 */
    private String securitiesType;
    
    /** 注意銘柄 */
    private String attentionSecurities;
    
}

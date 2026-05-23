package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 米株信用取引新規注文確認A010aリスポンスDTO
 *
 * @author SCSK 金志
 * 
 */
@Data
public class IfaForeignMarginTradeNewOrderConfirmA010aResponseDto {
    
    /** 銘柄種別（全角半角）. */
    private String brandClass;
    
    /** 注意銘柄. */
    private String tradeLimitTitle;
    
    /** 有効期間一覧. */
    private List<String> validityPeriodList;
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
}

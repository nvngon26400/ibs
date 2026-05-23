package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * A010aのレスポンス
 *
 * @author SCSK
 */
@Data
public class IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto {

    /** 銘柄種別. */
    private String brandClass;

    /** 注意銘柄. */
    private String tradeLimit;
    
    /** IFA注文番号. */
    private String ifaOrderNo;

}

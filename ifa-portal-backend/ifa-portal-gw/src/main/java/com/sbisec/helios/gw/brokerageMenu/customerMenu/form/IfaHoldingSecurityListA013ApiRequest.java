package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 保有商品一覧 A013 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListA013ApiRequest {
    
    
    /** 口座区分. */
    @NotEmpty(message = "口座区分")
    private String accountClassification;
    
    /** 商品選択（全角半角）. */
    @NotEmpty(message = "商品選択")
    @Size(min = 1, max = 1, message = "商品選択")
    private String securitySelect;
    
}

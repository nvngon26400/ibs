package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 保有商品一覧 A012 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListA012ApiRequest {
    
    /** 口座区分. */
    @NotEmpty(message = "口座区分")
    private String accountClassification;
    
    /** 商品選択（全角半角）. */
    @NotEmpty(message = "商品選択")
    @Size(min = 1, max = 1, message = "商品選択")
    private String securitySelect;
}

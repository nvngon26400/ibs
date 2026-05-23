package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 解除対象積立設定リストの明細
 *
 * @author WJL
 *
 *     2025/04/15 新規作成
 */
@Data
public class CancelTargetAccumulateSettingDetail {
    
    /** ファンドコード（回数）. */
	@NotEmpty(message = "ファンドコード（回数）")
    private String mfgo;
    
    /** ファンドコード（号）. */
	@NotEmpty(message = "ファンドコード（号）")
    private String mfkaisu;
	
	/** 協会コード. */
    @NotEmpty(message = "協会コード")
    private String fundCode;
   
    /** 決済方法. */
    @NotEmpty(message = "決済方法")
    private String paymentMethod;
    
    /** 預り区分. */
    @NotEmpty(message = "預り区分")
    private String accountType;
    
}

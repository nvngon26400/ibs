package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 投信積立設定解除確認の設定解除 A003 リクエストパラメータ
 *
 * @author WJL
 * 
 *     2025/04/13 新規作成
 */
@Data
public class IfaMutualFundAccumulateSettingCancelConfirmA003ApiRequest {
	
	@NotNull(message = "解除対象積立設定リスト")
    private List<IfaMutualFundAccumulateSettingCancelConfirmA003RequesDetail> cancelConfirmA003RequestList;
    
    /**
     * 解除対象積立設定リスト
     */
    @Data
    public static class IfaMutualFundAccumulateSettingCancelConfirmA003RequesDetail {
    	
        /** 回数. */
    	
        private String mfkaisu;
        
        /** 号. */
        @NotEmpty(message = "号")
        private String mfgo;
    
        /** 協会コード. */
        private String fundCode;
        
        /**決済方法. */
        @NotEmpty(message = "決済方法")
        private String paymentMethod;
        
        /** 預り区分. */
        @NotEmpty(message = "預り区分")
        private String accountType;
    }
}

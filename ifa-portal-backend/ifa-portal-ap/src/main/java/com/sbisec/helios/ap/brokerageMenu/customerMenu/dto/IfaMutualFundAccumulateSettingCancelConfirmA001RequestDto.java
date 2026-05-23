package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;



import lombok.Data;

/**
 * 投信積立設定解除確認 A001 リクエストパラメータ
 *
 * @author WJL
 *
 *     2025/04/11 新規作成
 */
@Data
public class IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto {
	
    /** 解除対象積立設定リスト. */
    private List<CancelTargetAccumulateSettingDetail> cancelTargetAccumulateSettingList; 
    
    @Data
    public static class CancelTargetAccumulateSettingDetail {
    	   
        /** ファンドコード（回数）. */
        private String mfgo;
        
        /** ファンドコード（号）. */
        private String mfkaisu;
    	
    	/** 協会コード. */
        private String fundCode;
       
        /** 決済方法. */
        private String paymentMethod;
        
        /** 預り区分. */
        private String accountType;
        
    }

}

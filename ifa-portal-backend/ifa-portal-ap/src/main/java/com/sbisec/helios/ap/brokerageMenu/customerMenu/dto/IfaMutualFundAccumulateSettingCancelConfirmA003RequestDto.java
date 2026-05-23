package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;


import lombok.Data;

/**
 * 投信積立設定解除確認の設定解除 A003 リクエストパラメータ
 *
 * @author WJL
 * 
 *     2025/04/13 新規作成
 */
@Data
public class IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto {
	
	/** 口座番号（数字）. */
    // 手動変換 部店コード + "-" + 口座番号
    private String accountNumber;
    
    /** 個人・法人アイコン. */
    // 1（法人）
    private String corporationKbn;
    
    /** 顧客名. */
    // 手動変換 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
    private String customerName;

    /**
     * 解除対象積立設定リスト
     */
    private List<IfaMutualFundAccumulateSettingCancelConfirmA003RequesDetail> cancelConfirmA003RequestList;
    

    @Data
    public static class IfaMutualFundAccumulateSettingCancelConfirmA003RequesDetail {
    	
        /** 回数. */
        private String mfkaisu;
        
        /** 号. */
        private String mfgo;
    
        /** 協会コード. */
        private String fundCode;
        
        /**決済方法. */
        private String paymentMethod;
        
        /** 預り区分. */
        private String accountType;
    }
    
}

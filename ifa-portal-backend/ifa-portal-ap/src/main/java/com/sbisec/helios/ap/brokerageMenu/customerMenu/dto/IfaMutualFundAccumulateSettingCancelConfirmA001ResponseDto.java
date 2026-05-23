package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;
/**
 * 投信積立設定解除確認 A001 レスポンスパラメータ
 *
 * @author WJL
 *
 *     2025/04/11 新規作成
 */
@Data
public class IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto {	
	 
	/** 口座番号（数字）. */
    // 手動変換 部店コード + "-" + 口座番号
    private String accountNumber;
    
    /** 個人・法人アイコン. */
    // 1（法人）
    private String corporationKbn;
    
    /** 顧客名. */
    // 手動変換 顧客名（漢字）+ "（"＋顧客名（カナ）＋"）"
    private String customerName;
	 
    /** 明細リスト. */
    // 手動変換
    private List<IfaMutualFundAccumulateSettingCancelListDetail> settingCancelConfirmList;
    
    @Data
    public static class IfaMutualFundAccumulateSettingCancelListDetail {

        /** 協会コード. */
        private String fundCode;
        
        /** ファンドコード（回数）. */
        private String mfkaisu;
        
        /** ファンドコード（号）. */
        private String mfgo;
        
        /** 銘柄コード. */
        // 手動変換
        private String brandCode;
        
        /** 銘柄名. */
        private String fundName;

        /** 預り区分. */
        // 手動変換
        private String accountType;
        
        /** 預り区分名. */
        // 手動変換
        private String accountTypeName;

        /** 決済方法. */
        // 手動変換
        private String paymentMethod;
        
        /** 決済方法名. */
        // 手動変換
        private String paymentMethodName;

        /** 積立コース. */
        private String courseType;
        
        /** 設定金額. */
        private String settingAmount;

        /** ボーナス月の設定. */
        // 手動変換
        private String settingBonusAmount;
        
        /** ボーナス月の設定. */
        // 手動変換
        private String settingBonusMonthDay;

        /** NISA枠ぎりぎり注文. */
        // 手動変換
        private String nisaBarelyBuyingType;

        /** 課税枠シフト注文. */
        // 手動変換
        private String taxShiftType;

        /** 1ヵ月あたりの積立概算. */
        private String oneMonthSumAmount;

        /** 次回発注予定日. */
        // 手動変換
        private String nextReserveDate;
      
    }

}

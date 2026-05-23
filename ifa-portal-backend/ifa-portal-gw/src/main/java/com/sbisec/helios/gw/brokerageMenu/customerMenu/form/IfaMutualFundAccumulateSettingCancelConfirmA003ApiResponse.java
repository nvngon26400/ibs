package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 投信積立設定解除確認の設定解除 A003 レスポンスパラメータ
 *
 *
 * @author WJL
 * 
 *     2025/04/13 新規作成
 */
@Data
public class IfaMutualFundAccumulateSettingCancelConfirmA003ApiResponse {
    
    /** 明細リスト. */
    // 手動変換
    private List<IfaMutualFundAccumulateSettingFinshListDetail> settingCancelConfirmList;
    
	@Data
	public static class IfaMutualFundAccumulateSettingFinshListDetail {

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

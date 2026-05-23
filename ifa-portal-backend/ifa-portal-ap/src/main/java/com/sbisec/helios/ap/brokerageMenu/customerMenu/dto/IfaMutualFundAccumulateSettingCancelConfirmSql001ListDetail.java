package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;


import lombok.Data;

/**
 * 投信積立設定解除受付データの格納 明細 リクエストパラメタ
 *
 * @author WJL
 *
 *     2025/04/13 新規作成
 */
@Data
public class IfaMutualFundAccumulateSettingCancelConfirmSql001ListDetail {
	
	/** 部店コード. */
	private String butenCode;
	
	/** 口座番号. */
	private String accountNumber;
	
	/**ファンドコード（回数）*/
	private String mfkaisu;
	
	/** ファンドコード（号）. */
	private String mfgo;

	/** 預り区分. */
	private String accountType;
	
	/** 更新区分. */
	private String modifyType;
	
	/** 決済方法. */
	private String paymentMethod;
	
	/** NISAPI応答枠ぎりぎり買付区分. */
	private String nisaBarelyBuyingType;

	/** NISAPI応答枠超過時買付区分. */
	private String taxShiftType;

	/** 設定金額. */
	private String settingAmount;

	/** コース区分. */
	private String courseType;

	/** 積立日付. */
	private String settingReserveDay;

	/** 積立隔月設定. */
	private String settingReserveBimonthly;

	/** 積立毎週設定. */
	private String settingReserveWeek;

	/** 積立複数日設定. */
	private String settingReserveMultiDay;

	/** 1ヵ月あたりの設定金額（概算）. */
	private String oneMonthSumAmount;

	/** ボーナス設定有無. */
	private String settingBonus;

	/** ボーナス設定金額. */
	private String settingBonusAmount;

	/** ボーナス１設定月. */
	private String settingBonus1Month;

	/** ボーナス１設定日. */
	private String settingBonus1Day;

	/** ボーナス２設定月. */
	private String settingBonus2Month;

	/** ボーナス２設定日. */
	private String settingBonus2Day;

	/** 買付予定日. */
	private String planDate;

	/** ボーナス１買付予定日. */
	private String bonusPlanDate1;

	/** ボーナス２買付予定日. */
	private String bonusPlanDate2;

	/** 次回買付日. */
	private String nextReserveDate;

    /** オペレータＩＤ */
    private String operatorId;

    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者営業員コード */
    private String intermediaryEmpCd;

    /** 作成者 */
    private String createUser;

    /** 更新者*/
    private String updateUser;
  
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaDomesticMutualFundOrderConfirmSql002RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;

	/** 商品区分（全角半角）. */
	private String securityType;

	/** EC受注番号（半角英数字）. */
	private String ecOrderNo;

	/** 受注日. */
	private String acceptDate;

	/** 受注時刻. */
	private String orderDayTime;

	/** 種別（全角半角）. */
	private String shubetu;

	/** エラーコード（半角英数字）. */
	private String code;

	/** エラーメッセージ（全角半角）. */
	private String errMessage;

	/** 与信チェック用時価（数値(小数)）. */
	private String estimatePrice;

	/** 約定金額（概算）（数値(整数)）. */
	private String amount;

	/** 手数料（概算）（数値(整数)）. */
	private String commission;

	/** 消費税（概算）（数値(整数)）. */
	private String consumptionTax;

	/** 譲渡益税（概算）（数値(整数)）. */
	private String capitalGainTax;

	/** 精算金額（概算）（数値(整数)）. */
	private String netAmount;

	/** 購入・解約の口数. */
	private String quantity;

	/** 売却可能数量（数値(整数)）. */
	private String acPosition;

	/** 注文後の売却可能数量（数値(整数)）. */
	private String acPositionAfter;

	/** 買付可能金額（数値(整数)）. */
	private String acBalance;

	/** 注文後の買付可能金額（数値(整数)）. */
	private String acBalanceAfter;

	/** 約定予定日. */
	private String contractDate;

	/** 受渡予定日. */
	private String deliveryDate;

	/** 注文前のISA買付可能枠. */
	private String isaBuyLimitBefore;

	/** 注文後のISA買付可能枠（数値(整数)）. */
	private String isaBuyLimitAfter;

	/** 発注日. */
	private String orderDate;

	/** ファンド締切時刻. */
	private String fundCloseTime;

	/** ジュニアNISA振替金額（数値(整数)）. */
	private String jrnisaTransferAmount;

	/** ポイント. */
	private String point;

	/** 利用後のポイント. */
	private String pointAfter;

	/** 更新日時. */
	private String updateTime;

	/** 更新者. */
	private String updateUser;

    /** APIエラー. */
    private Boolean apiError;

	/** API003応答である */
	private boolean isApi003;

}

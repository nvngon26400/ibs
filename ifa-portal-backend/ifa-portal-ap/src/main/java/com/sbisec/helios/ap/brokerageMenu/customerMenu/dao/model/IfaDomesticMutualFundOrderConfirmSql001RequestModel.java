package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaDomesticMutualFundOrderConfirmSql001RequestModel {

	/** IFA注文番号（数字）. */
	private String ifaOrderNo;

	/** IFA注文サブ番号（数字）. */
	private String ifaOrderSubNo;

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 顧客ID（数字）. */
	private String kokyakuId;

	/** 特定口座区分（半角英数字）. */
	private String tokuteiKouzaKbn;

	/** ファンドタイプ（半角英数字）. */
	private String fundType;

	/** ファンドコード（回数）（半角英数字）. */
	private String fundCodeTimes;

	/** ファンドコード（号）（半角英数字）. */
	private String fundCodeIssues;

	/** 注文状況（全角半角）. */
	private String orderStatus;

	/** 取引種別（全角半角）. */
	private String tradeCd;

	/** 売買区分（全角半角）. */
	private String tradeKbn;
	
	/** 売却指定 */
	private String sellDesignated;

	/** 口数（数値(整数)）. */
	private String unit;

	/** 金額（数値(整数)）. */
	private String amount;

	/** 乗換優遇区分（半角英数字）. */
	private String norikaeYuguKbn;

	/** 分配金受取方法. */
	private String distributionReceiveMethodWord;

	/** 預り区分（全角半角）. */
	private String depositType;

	/** 目論見書チェック区分（半角英数字）. */
	private String dispatchId;

	/** ポイント種別（半角英数字）. */
	private String pointType;

	/** ポイント利用（半角英数字）. */
	private String pointFlg;

	/** 注文時ポイント. */
	private String orderPoint;

	/** レバレッジ投資信託. */
	private String leverageInvestTrust;

	/** 乗換勧誘（半角英数字）. */
	private String norikaeKanyuKbn;

	/** 同一通貨/同一国の乗換. */
	private String douitsuTukaKuniKbn;

	/** 他社間投信乗換勧誘（半角英数字）. */
	private String tashaNorikaeKbn;

	/** 短期売却確認（半角英数字）. */
	private String tankiSellKbn;

	/** 償還前売却確認（半角英数字）. */
	private String shokanMaeKbn;

	/** 勧誘区分（全角半角）. */
	private String kanyuKbn;

	/** 受注方法. */
	private String receiveOrderType;

	/** 目論見書の交付方法（半角英数字）. */
	private String mokuromiKoufuKbn;

	/** 利益相反可能性の説明. */
	private String conflictOfInterestExplain;

	/** 確認項目.目論見書補完書面の確認（半角英数字）. */
	private String checkMokuromi;

	/** 確認項目.窓空きファンドの注意事項に同意（半角英数字）. */
	private String checkMadoAki;

	/** 確認項目.費用について説明済（半角英数字）. */
	private String checkHiyou;

	/** 確認項目.複数取引業者での手数料等明示済（半角英数字）. */
	private String checkFukusu;

	/** アラート内容確認.コンプラチェックワーニング確認（半角英数字）. */
	private String checkCompWrnAlert;

	/** 資金性格区分（半角英数字）. */
	private String uaiQaFundCharacter;

	/** ユーザーＩＤ（全角半角）. */
	private String userId;

	/** 取消ユーザーID（全角半角）. */
	private String torikeshiUserId;

	/** 商品区分（全角半角）. */
	private String securityType;

	/** EC受注番号（半角英数字）. */
	private String ecOrderNo;

	/** 受注日. */
	private String acceptDay;

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
	private String contractAmount;

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

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者営業員コード（半角英数字）. */
	private String brokerChargeCode;

	/** 作成日時. */
	private String createTime;

	/** 作成者. */
	private String createUser;

	/** 更新日時. */
	private String updateTime;

	/** 更新者. */
	private String updateUser;

	/** CCS送付日. */
	private String ccsSendDate;

}

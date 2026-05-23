package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize
public class IfaSubscriptInputConfirmA002ApiRequest {

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 送信・訂正用ロジック処理判定フラグ（全角半角）. */
	private String sendCorrectLogicJudgeFlag;

	/** 上場日. */
	private String presentationDate;

	/** 抽選結果. */
	private String lotteryResult;

	/** 当選株数（数値(整数)）. */
	private String bbQuantityAlloc;

	/** 売買単位（数値(整数)）. */
	private String unit;

	/** 売買単位区分（半角英数字）. */
	private String sellBuyUnitType;

	/** 銘柄コード（半角英数字）. */
	private String brandCode;

	/** ブックビルディング申込期間（開始）（全角半角）. */
	private String bookBuildingPresentationFrom;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者営業員コード（半角英数字）. */
	private String brokerChargeCode;

	/** 扱者コード（半角英数字）. */
	private String dealerNumber;

	/** 注文状況（全角半角）. */
	private String orderStatus;

	/** 顧客名（漢字）（全角半角）. */
	private String customerNameKanji;

	/** 顧客名（カナ）（全角半角）. */
	private String customerNameKana;

	/** 注意情報レベル. */
	private String noticeInfoLevel;

	/** 銘柄名（全角半角）. */
	private String brandName;

	/** 数量（数値(整数)）. */
	private String quantity;

	/** 約定金額（数値(整数)）. */
	private String contractAmount;

	/** 預り区分（全角半角）. */
	private String depositType;

	/** 勧誘区分（全角半角）. */
	private String kanyuKbn;

	/** 受注方法. */
	private String jutyuKbn;

	/** 目論見書の交付方法（半角英数字）. */
	private String mokuromiKoufuKbn;

	/** 重要事項の説明. */
	private String importantMatterType;

	/** 注文フラグ（半角英数字）. */
	private String orderFlag;


}

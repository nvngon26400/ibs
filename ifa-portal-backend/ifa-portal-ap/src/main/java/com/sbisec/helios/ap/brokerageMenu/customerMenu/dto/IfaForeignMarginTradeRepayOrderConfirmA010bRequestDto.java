package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto {

	/** 市場コード（全角半角）. */
	private String marketCode;

	/** 市場略名. */
	private String marketAbbreviatedName;

	/** 銘柄コード（半角英数字）. */
	private String brandCode;

	/** 銘柄名（全角半角）. */
	private String brandName;

	/** 取引種別（全角半角）. */
	private String tradeCd;

	/** 注文数量. */
	private String orderCount;

	/** 価格条件. */
	private String orderPriceKindList;

	/** 注文単価（数値(小数)）. */
	private String hiddenOrderPrice;

	/** 発火条件価格. */
	private String stopOrderPrice2;

	/** 成行基準価格（全角半角）. */
	private String executeBasePrice;

	/** 期間条件. */
    private String periodRadio;

	/** 期間. */
	private String period;

	/** 預り区分（全角半角）. */
	private String depositType;

	/** 決済方法（半角英数字）. */
	private String kessaiHoho;

	/** 勧誘区分（全角半角）. */
	private String kanyuKbn;

	/** 受注方法. */
	private String receiveOrderTypeName;

	/** 返済期限. */
	private String lastTradeDate;

	/** 返済建玉指定方法（全角半角）. */
	private String repayPositionDesignateMethod;

	/** 返済選択順序（全角半角）. */
	private String repaySelectOrder;

	/** 建区分. */
	private String trade;

	/** 個別建玉情報一覧. */
	private List<IfaForeignMarginTradeRepayOrderConfirmA010RequestDto_positionDesignationAreaIndividualPositionInfoList> positionDesignationAreaIndividualPositionInfoList;

	/** 株価チケット（全角半角）. */
	private String stockTicket;

	/** 確認項目.インサイダー確認（半角英数字）. */
	private String checkInsider;

	/** アラート内容確認.取引注意情報（銘柄）確認. */
    private String tradingCautionInformation;

	/** アラート内容確認.注意情報アラート確認. */
    private String noteInfoCheckbox;

	/** アラート内容確認.お知らせアラート確認. */
    private String noteLimitCheck;

	/** アラート内容確認.逆指値注文即時発火. */
    private String methodCheck;

	/** アラート内容確認.翌営業日向け注文. */
    private String nextDayCheck;

	/** 注意情報アラート（全角半角）. */
    private List<String> noticeInfoAlert;

	/** お知らせアラート（全角半角）. */
    private List<String> noticeAlert;

	/** 逆指値注文即時発火メッセージ. */
    private List<String> stopOrderInstantMessage;

	/** 現地約定日超過メッセージ. */
    private List<String> nextBusinessDayMessage;

	/** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private List<String> tradeNoticeInfoBrandMsg;

	/** 本日の注意銘柄URL. */
	private String tradeLimitUrl;

	/** 注文番号（数字）. */
	private String orderNumber;

	/** 注文Sub番号（数字）. */
	private String orderSubNumber;

	/** 売買区分（全角半角）. */
	private String tradeKbn;

	/** 取引通貨. */
    private String currencyCode;

	/** 注文日時. */
	private String orderDate;

    /** 国内約定日. */
    private String businessDaysAfterOrder;
    
	/** 銘柄種別（全角半角）. */
	private String brandClass;

	/** 注意銘柄. */
	private String tradeLimit;

	/** IFA注文番号（数字）. */
	private String ifaOrderNo;

}

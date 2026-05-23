package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaMarginNewOrderCorrectConfirmA001ApiResponse {

	/** 約定予定日. */
	private String contractDate;

	/** 受渡予定日. */
	private String deliveryDate;

	/** 受注日時. */
	private String orderDayTime;

	/** 訂正後建玉余力（数値(整数)）. */
	private String afterCorrectPositionPower;
	
	/** 訂正SOR注文結果区分. */
	private String correctSorOrderResultClassification;

    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 市場(訂正前)（全角）. */
    private String market;
    
    /** 市場(訂正後)（全角）. */
    private String afterCorrectMarket;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 未約定数量（数値(整数)）. */
    private String unTradeQuantity;
    
    /** 注文種別（半角英数字）. */
    private String orderKind;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 期間. */
    private String period;
    
    /** 信用取引区分（全角半角）. */
    private String marginTradeTypeText;
    
    /** 通常/逆指値.執行方法（半角英数字）. */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件（半角英数字）. */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
    private String triggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値）（半角英数字）. */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数値(整数)）. */
    private String price;
    
    /** OCO1.執行方法（半角英数字）. */
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件（半角英数字）. */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数値(整数)）. */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
    private String oco2TriggerPrice;
    
    /** OCO2.執行方法（逆指値）（半角英数字）. */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値）（半角英数字）. */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数値(整数)）. */
    private String oco2Price;
    
    /** IFD1.執行方法（半角英数字）. */
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件（半角英数字）. */
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数値(整数)）. */
    private String ifd1TriggerPrice;
    
    /** IFD1.執行方法（逆指値）（半角英数字）. */
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数値(整数)）. */
    private String ifd1Price;
    
    /** IFD2.期間.期間条件. */
    private String ifd2PeriodTerm;
    
    /** IFD2.期間.日付（全角半角）. */
    private String ifd2Limit;
    
    /** IFD2.執行方法（半角英数字）. */
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件（半角英数字）. */
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数値(整数)）. */
    private String ifd2TriggerPrice;
    
    /** IFD2.執行方法（逆指値）（半角英数字）. */
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価（数値(整数)）. */
    private String ifd2Price;
    
    /** 訂正前価格.通常/逆指値.執行方法. */
    private String correctBeforePriceSasinariHouhou;
    
    /** 訂正前価格.通常/逆指値.執行条件. */
    private String correctBeforePriceSasinariJyouken;
    
    /** 訂正前価格.通常/逆指値.発火条件価格（逆指値）. */
    private String correctBeforePriceTriggerPrice;
    
    /** 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン. */
    private String correctBeforePriceTriggerPriceZone;
    
    /** 訂正前価格.通常/逆指値.執行方法（逆指値）. */
    private String correctBeforePriceGyakusasiHouhou;
    
    /** 訂正前価格.通常/逆指値.注文単価. */
    private String correctBeforePricePrice;
    
    /** 訂正前価格.OCO1.執行方法. */
    private String correctBeforePriceOco1SasinariHouhou;
    
    /** 訂正前価格.OCO1.執行条件. */
    private String correctBeforePriceOco1SasinariJyouken;
    
    /** 訂正前価格.OCO1.注文単価. */
    private String correctBeforePriceOco1Price;
    
    /** 訂正前価格.OCO2.発火条件価格（逆指値）. */
    private String correctBeforePriceOco2TriggerPrice;
    
    /** 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン. */
    private String correctBeforePriceOco2TriggerPriceZone;
    
    /** 訂正前価格.OCO2.執行方法（逆指値）. */
    private String correctBeforePriceOco2GyakusasiHouhou;
    
    /** 訂正前価格.OCO2.執行条件（逆指値）. */
    private String correctBeforePriceOco2GyakusasiJyouken;
    
    /** 訂正前価格.OCO2.注文単価. */
    private String correctBeforePriceOco2Price;
    
    /** 訂正前価格.IFD1.執行方法. */
    private String correctBeforePriceIfd1SasinariHouhou;
    
    /** 訂正前価格.IFD1.執行条件. */
    private String correctBeforePriceIfd1SasinariJyouken;
    
    /** 訂正前価格.IFD1.発火条件価格（逆指値）. */
    private String correctBeforePriceIfd1TriggerPrice;
    
    /** 訂正前価格.IFD1.発火条件価格（逆指値）ゾーン. */
    private String correctBeforePriceIfd1TriggerPriceZone;
    
    /** 訂正前価格.IFD1.執行方法（逆指値）. */
    private String correctBeforePriceIfd1GyakusasiHouhou;
    
    /** 訂正前価格.IFD1.注文単価. */
    private String correctBeforePriceIfd1Price;
    
    /** 訂正前価格.IFD2.期間.日付. */
    private String correctBeforePriceIfd2Limit;
    
    /** 訂正前価格.IFD2.執行方法. */
    private String correctBeforePriceIfd2SasinariHouhou;
    
    /** 訂正前価格.IFD2.執行条件. */
    private String correctBeforePriceIfd2SasinariJyouken;
    
    /** 訂正前価格.IFD2.発火条件価格（逆指値）. */
    private String correctBeforePriceIfd2TriggerPrice;
    
    /** 訂正前価格.IFD2.発火条件価格（逆指値）ゾーン. */
    private String correctBeforePriceIfd2TriggerPriceZone;
    
    /** 訂正前価格.IFD2.執行方法（逆指値）. */
    private String correctBeforePriceIfd2GyakusasiHouhou;
    
    /** 訂正前価格.IFD2.注文単価. */
    private String correctBeforePriceIfd2Price;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String orderMethod;
    
    /** 確認項目.契約締結前交付書面確認. */
    private String checkInsiderConfirmCheckBox;
    
    /** 確認項目.SOR確認. */
    private String checkSor;
    
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    private String tradeNoticeInfoBrandConfirm;
    
    /** アラート内容確認.注意情報アラート確認. */
    private String noticeInfoAlertConfirm;
    
    /** アラート内容確認.お知らせアラート確認. */
    private String noticeAlertConfirm;
    
    /** アラート内容確認.空売り規制の抵触確認. */
    private String shortSellingRegulationsConfirm;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** 空売り規制の抵触確認メッセージ. */
    private String shortSellingRegulationsMessage;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private String tradeNoticeInfoBrandMsg;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** DONE状態（半角英数字）. */
    private String doneState;
    
    /** 発火状態（半角英数字）. */
    private Boolean workingStatus;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** RBE注文ステータス（半角英数字）. */
    private String rbeOrderStatus;
    
    /** 手数料区分（半角英数字）. */
    private String comId;
    
    /** 受注日. */
    private String inputDate;
    
    /** 訂正SOR注文区分. */
    private String correctSorOrderClassification;
    
    private String yukoKigenChangeFlag;
    
    private String yukoKigen;

    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;

}

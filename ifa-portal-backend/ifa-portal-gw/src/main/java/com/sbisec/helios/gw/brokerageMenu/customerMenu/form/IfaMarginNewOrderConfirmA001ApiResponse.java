package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaMarginNewOrderConfirmA001ApiResponse {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 見積単価（数値(小数)）. */
    private String quoteUnitPrice;
    
    /** 約定金額（数値(整数)）. */
    private String contractAmount;
    
    /** 手数料/諸費用（数値(整数)）. */
    private String charge;
    
    /** 消費税（数値(整数)）. */
    private String consumptionTax;
    
    /** 精算金額（数値(整数)）. */
    private String settlementAmount;
    
    /** 適用金利. */
    private String applicableInterestRate;
    
    /** 適用貸株料. */
    private String applicableStockLendingFees;
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** 注文入力市場（全角半角）. */
    private String orderedMarket;
    
    /** リクエスト内容. */
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 市場（全角）. */
    private String market;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 受注数量（数値(整数)）. */
    private String orderQuantity;
    
    /** 期間.期間条件. */
    private String periodRadio;
    
    /** 期間.日付（全角半角）. */
    private String limit;
    
    /** 注文種別（全角半角）. */
    private String orderKind;
    
    /** 通常/逆指値.執行方法（全角半角）. */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件（全角半角）. */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
    private String triggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値）（全角半角）. */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数値(整数)）. */
    private String price;
    
    /** OCO1.執行方法. */
    private String oco1OrderExecuteMethodText;
    
    /** OCO1.執行条件（全角半角）. */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数値(整数)）. */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
    private String oco2TriggerPrice;
    
    /** OCO2.執行方法（逆指値）（全角半角）. */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値）（全角半角）. */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数値(整数)）. */
    private String oco2Price;
    
    /** IFD1.執行方法（全角半角）. */
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件（全角半角）. */
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数値(整数)）. */
    private String ifd1TriggerPrice;
    
    /** IFD1.執行方法（逆指値）（全角半角）. */
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数値(整数)）. */
    private String ifd1Price;
    
    /** IFD2.期間.期間条件. */
    private String ifd2PeriodDate;
    
    /** IFD2.期間.日付（全角半角）. */
    private String ifd2Limit;
    
    /** IFD2.執行方法（全角半角）. */
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件（全角半角）. */
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数値(整数)）. */
    private String ifd2TriggerPrice;
    
    /** IFD2.執行方法（逆指値）. */
    private String ifd2OrderExecuteMethodText;
    
    /** IFD2.注文単価（数値(整数)）. */
    private String ifd2Price;
    
    /** 信用取引区分（全角半角）. */
    private String marginTradeTypeText;
    
    /** 手数料区分（全角半角）. */
    private String tesuuryouKbn;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrderTypeName;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    private String checkInsider;
    
    /** 確認項目.SOR確認（全角半角）. */
    private String checkSor;
    
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    private String tradingCautionInformation;
    
    /** アラート内容確認.注意情報アラート確認. */
    private String noteInfoCheckbox;
    
    /** アラート内容確認.お知らせアラート確認. */
    private String noteLimitCheck;
    
    /** アラート内容確認.内部者エラー確認. */
    private String insiderErrorConfirmationCheckbox;
    
    /** アラート内容確認.空売り規制の抵触確認. */
    private String shortSellingRegulationsCheckbox;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** 内部者確認メッセージ. */
    private String insiderErrorMsg;
    
    /** 空売り規制の抵触確認メッセージ(空売り規制抵触メッセージ). */
    private String shortSellingRegulationsMessage;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private String tradeNoticeInfoBrandMsg;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
}

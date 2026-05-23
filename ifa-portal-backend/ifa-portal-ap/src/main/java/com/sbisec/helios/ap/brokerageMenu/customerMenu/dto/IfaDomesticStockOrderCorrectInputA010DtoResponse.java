package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;
import java.util.List;

@Data
public class IfaDomesticStockOrderCorrectInputA010DtoResponse {
    
    /** 銘柄名 */
    private String brandName;
    
    /** 注意情報アラート */
    private String noticeInfoAlert;
    
    /** お知らせアラート */
    private String noticeAlert;
    
    /** コンプラランクチェック.メッセージ */
    private String messageId;
    
    /** コンプラランクチェック.チェックボックス文言 */
    private String chkBoxLabel;
    
    /** コンプラランクチェック.コンプラチェック用資金性格 */
    private String fundComplianceCheck;
    
    /** 取引注意情報（銘柄）メッセージ */
    private String regKbn;
    
    /** 種別 */
    private String shubetu;
    
    /** エラーコード */
    private String code;
    
    /** エラーメッセージ */
    private String errMessage;
    
    /** ジュニアNISA振替金額 */
    private String jrnisaTransferAmount;
    
    /** 見積単価 */
    private String quoteUnitPrice;
    
    /** 投資可能枠 */
    private String nisaInvestableQuote;
    
    /** 約定予定日 */
    private String contractDate;
    
    /** 受渡予定日 */
    private String deliveryDate;
    
    /** 受注日時 */
    private String orderDayTime;
    
    /** 訂正後買付余力 */
    private String afterCorrecBuyPower;
    
    /** 有効期限変更フラグ */
    private String yukoKigenChange;
    
    /** 有効期限 */
    private String yukoKigen;
    
    /** 訂正SOR注文結果区分 */
    private String correctSorOrderResultClassification;
    
    /** EC受注番号 */
    private String ecOrderNo;
    
    /** 市場 */
    private String market;
    
    /** 訂正後市場 */
    private String afterCorrectMarket;
    
    /** 銘柄コード（半角英数字） */
    private String brandCode;
    
    /** 数量（数字） */
    private String quantity;
    
    /** 未約定数量（数字） */
    private String unTradeQuantity;
    
    /** 注文種別 */
    private String orderKind;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 期間 */
    private String period;
    
    /** 預り区分 */
    private String depositType;
    
    /** 発火状態 */
    private String workingStatus;
    
    /** DONE状態 */
    private String doneState;
    
    /** RBE注文ステータス */
    private String rbeOrderStatus;
    
    /** 通常/逆指値.執行方法 */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件 */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数字） */
    private String triggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値） */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数字） */
    private String price;
    
    /** OCO1.執行方法 */
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件 */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数字） */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数字） */
    private String oco2TriggerPrice;
    
    /** OCO2.執行方法（逆指値） */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値） */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数字） */
    private String oco2Price;
    
    /** IFD1.執行方法 */
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件 */
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数字） */
    private String ifd1TriggerPrice;
    
    /** IFD1.執行方法（逆指値） */
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数字） */
    private String ifd1Price;
    
    /** IFD2.期間.日付 */
    private String ifd2Limit;
    
    /** IFD2.執行方法 */
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件 */
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数字） */
    private String ifd2TriggerPrice;
    
    /** IFD2.執行方法（逆指値） */
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価（数字） */
    private String ifd2Price;
    
    /** 訂正前価格 */
    private List<IfaDomesticStockOrderCorrectInputA010BeforeCorrectPriceDtoResponse> beforeCorrectPrice;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** 受注方法 */
    private String receiveOrderType;
    
    /** 確認項目.SOR確認 */
    private String checkSor;
    
    /** 確認項目.インサイダー確認 */
    private String checkInsider;
    
    /**　手数料区分 */
    private String tesuuryouKbn;

    /** 受注日 */
    private String orderDate;
    
    /** 訂正SOR注文区分 */
    private String correctSorOrderClassification;
    
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaDomesticStockOrderCorrectConfirmA001bDtoRequest {
    
    /** EC受注番号 */
    private String ecOrderNo;

    /** 市場（訂正前） */
    private String market;

    /** 市場（訂正後） */
    private String afterCorrectMarket;
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 数量 */
    private String quantity;
    
    /** 未約定数量 */
    private String unTradeQuantity;
    
    /** 注文種別 */
    private String orderKind;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 期間 */
    private String period;
    
    /** 預り区分 */
    private String depositType;
    
    /** 通常/逆指値.執行方法 */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件 */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値） */
    private String triggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値） */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価 */
    private String price;
    
    /** OCO1.執行方法 */
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件 */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価 */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値） */
    private String oco2TriggerPrice;
    
    /** OCO2.執行方法（逆指値） */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値） */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価 */
    private String oco2Price;
    
    /** IFD1.執行方法 */
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件 */
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値） */
    private String ifd1TriggerPrice;
    
    /** IFD1.執行方法（逆指値） */
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価 */
    private String ifd1Price;
    
    /** IFD2.期間.期間条件 */
    private String ifd2PeriodTerms;
    
    /** IFD2.期間.日付 */
    private String ifd2Limit;
    
    /** IFD2.執行方法 */
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件 */
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値） */
    private String ifd2TriggerPrice;
    
    /** IFD2.執行方法（逆指値） */
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価 */
    private String ifd2Price;
    
    /** 訂正前価格 */
    private List<IfaDomesticStockOrderCorrectConfirmA001DtoRequestBeforeCorrectPrice> beforeCorrectPrice;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** 受注方法 */
    private String receiveOrderType;
    
    /** 確認項目.インサイダー確認 */
    private String checkInsider;

    /** 確認項目.SOR確認 */
    private String checkSor;
    
    /** アラート内容確認.取引注意情報（銘柄）確認 */
    private String tradingCautionInformation;
    
    /** アラート内容確認.コンプラランクチェック確認 */
    private String invitationCheck;
    
    /** アラート内容確認.注意情報アラート確認 */
    private String noticeInfoAlertCheck;
    
    /** アラート内容確認.お知らせアラート確認 */
    private String noticeAlertCheck;
    
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
    
    /** 銘柄名 */
    private String brandName;
    
    /** DONE状態 */
    private String doneState;
    
    /** 発火状態 */
    private String workingStatus;
    
    /** RBE注文ステータス */
    private String rbeOrderStatus;
    
    /** 手数料区分 */
    private String tesuuryouKbn;

    /** 受注日 */
    private String orderDate;

    /** 訂正SOR注文区分 */
    private String correctSorOrderClassification;
    
    /** IFA注文番号 */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号 */
    private String ifaOrderSubNo;
    
}

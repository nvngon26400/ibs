package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0104-03
 * 画面名：外国株式店頭詳細表示

 * @author 大崎
　　　2024/03/19 新規作成
 */

@Data
public class IfaForeignStockCounterDetailDisplayA001DtoResponse {

    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 取引価格（数値(小数)）. */
    private String tradePrice;
    
    /** 管理番号（半角英数字）. */
    private String manageNumber;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 約定日時. */
    private String tradeDateTime;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 約定金額（数値(整数)）. */
    private String contractAmount;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 決済区分（全角半角）. */
    private String settlementType;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String orderMethod;
    
    /** ワーニング申請取引（全角半角）. */
    private String warningApplyTrade;
    
    /** 重要事項の説明. */
    private String importantMatterTypeText;
    
    /** 外国証券情報.版番 */
    private String foreignStockYmd;
    
    /** 外国証券情報.交付日 */
    private String documentDeliveryDate;
    
    /** 外国証券情報.交付方法 */
    private String informationDeliveredText;
    
    /** 乗換え勧誘(ETF). */
    private String solicitationEtfText;
    
    /** 英文開示銘柄. */
    private String engPubText;
    
    /** 説明日. */
    private String engPubYmd;
    
    /** 摘要(任意)（全角半角）. */
    private String summaryAny;
    
    /** ステータス. */
    private String status;
    
    /** 取消理由（全角半角）. */
    private String cancelReason;
    
    /** コメント（全角半角）. */
    private String comment;

}

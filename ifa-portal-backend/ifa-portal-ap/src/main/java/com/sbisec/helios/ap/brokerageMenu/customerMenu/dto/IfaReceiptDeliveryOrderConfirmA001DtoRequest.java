package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-08_2
 * 画面名：現引現渡注文確認
 * 2024/04/01 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaReceiptDeliveryOrderConfirmA001DtoRequest {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 銘柄名 */
    private String brandName;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 受注数量 */
    private String quantity;
    
    /** 弁済期限 */
    private String paymentDeadline;

    /** 特定・一般区分 */
    private String accountType;
    
    /** 建市場 */
    private String builtMarket;
    
    /** 新規建日 */
    private String constructionDate;
    
    /** 親株新規約定日 */
    private String parentStockTradeDate;
    
    /** 新規建玉指定番号 */
    private String newOpenInterestNumber;
    
    /** 新規単価 */
    private String newPrice;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** 受注方法 */
    private String receiveOrderType;
    
    /** 取得単価 */
    private String openPrice;
    
    /** 残高数量 */
    private String contPosition;
    
    /** 返済注文済未出来数量 */
    private String unactualQuantity;
    
    /** 特定建玉区分 */
    private String tokuteiContractId;
    
    /** 確認項目の契約締結前交付書面の確認 */
    private String checkCustomerAttribute;

    /** 弁済期限（算出） */
    private String paymentDeadlineCalculation;

    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;
    
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    private String tradingCautionInformation;
    
    /** アラート内容確認.注意情報アラート確認. */
    private String noteInfoCheck;
    
    /** アラート内容確認.お知らせアラート確認. */
    private String noteLimitCheck;
    
    /** アラート内容確認.内部者エラー確認. */
    private String insiderErrorConfirmationCheck;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** 内部者確認メッセージ. */
    private String insiderErrorMsg;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private String tradeNoticeInfoBrandMsg;
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-04_1
 * 画面名：信用返済注文入力
 * 2024/04/08 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaMarginRepayOrderInputA016ResponseDto {
    
    /** 銘柄名 */
    private String brandName;
    
    /** 種別 */
    private String shubetu;
    
    /** エラーコード */
    private String code;
    
    /** エラーメッセージ */
    private String errMessage;
    
    /** エラー明細(1-999) */
    private String errorDetail1To999;
    
    /** 注意情報アラート */
    private String noticeInfoAlert;
    
    /** お知らせアラート */
    private String noticeAlert;
    
    /** 内部者確認メッセージ */
    private String insiderConfirmMsg;
    
    /** 取引注意情報（銘柄）メッセージ */
    private String tradeNoticeInfoBrandMsg;
    
    /** 見積単価 */
    private String quoteUnitPrice;
    
    /** 約定金額 */
    private String contractAmount;
    
    /** 手数料/諸費用 */
    private String charge;
    
    /** 消費税 */
    private String consumptionTax;
    
    /** 讓渡益税 */
    private String yieldTax;
    
    /** 精算金額 */
    private String settlementAmount;
    
    /** 約定予定日 */
    private String contractDate;
    
    /** 受渡予定日 */
    private String deliveryDate;
    
    /** 受注日時 */
    private String orderDayTime;
    
    /** リクエスト内容 */
    private IfaMarginRepayOrderInputA016RequestDto request;
    
    private List<String> warningList;
}

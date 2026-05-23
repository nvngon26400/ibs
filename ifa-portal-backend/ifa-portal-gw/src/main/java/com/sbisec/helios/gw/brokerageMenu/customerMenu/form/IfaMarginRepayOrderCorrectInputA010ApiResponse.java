package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-06_1
 * 画面名：信用返済注文訂正入力
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaMarginRepayOrderCorrectInputA010ApiResponse {
    
    /** 銘柄名 */
    private String brandName;
    
    /** 注意情報アラート */
    private String noticeInfoAlert;
    
    /** お知らせアラート */
    private String noticeAlert;
    
    /** 取引注意情報（銘柄）メッセージ */
    private String tradeNoticeInfoBrandMsg;
    
    /** 種別 */
    private String shubetu;
    
    /** エラーコード */
    private String code;
    
    /** エラーメッセージ */
    private String errMessage;
    
    /** 約定予定日 */
    private String contractDate;
    
    /** 受渡予定日 */
    private String deliveryDate;
    
    /** 受注日時 */
    private String orderDayTime;
    
    /** 訂正後建玉余力 */
    private String positionPower;
    
    /** 有効期限変更フラグ */
    private String yukoKigenChange;
    
    /** 有効期限 */
    private String yukoKigen;
    
    /** 訂正SOR注文結果区分 */
    private String correctSorOrderResultClassification;

    /** リクエスト内容 */
    private IfaMarginRepayOrderCorrectInputA010ApiRequest request;
}

package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

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
public class IfaMarginRepayOrderInputA004ApiResponse {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 弁済期限 */
    private String paymentDeadline;
    
    /** 返済方法 */
    private String repayMethod;
    
    /** 返済順序 */
    private String repaymentOrder;
    
    /** 合計数量 */
    private String totalQuantity;
    
    /** 返済建玉明細 */
    private List<IfaMarginRepayOrderInputApiRepayPositionDetail> repayPositionDetailList;
    
    /** 注文可能数量 */
    private String maxOrderableQuantity;
    
    /** CT夜間バッチ終了フラグ */
    private String ctNightBatchFinishFlag;
    
    /** 営業日リスト */
    private List<String> businessDayList;

    /** 弁済期限（算出） */
    private String paymentDeadlineCalculation;
}

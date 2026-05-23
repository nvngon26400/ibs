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
public class IfaMarginRepayOrderInputA001RequestDto {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 新規売買区分 */
    private String openTradeKbn;
    
    /** 弁済期限 */
    private String paymentDeadline;
    
    /** 返済建玉明細 */
    private List<IfaMarginRepayOrderInputDtoRepayPositionDetail> repayPositionDetail;
    
    /** 返済方法 */
    private String repayMethod;
    
    /** 返済順序 */
    private String repaymentOrder;
    
    /** 合計数量 */
    private String totalQuantity;
}

package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-05_1
 * 画面名：信用一括返済入力
 * 2024/04/15 新規作成
 *
 * @author SCSK 池亀緑
 */
@Data
public class IfaMarginMassRepayInputA003ApiRequest {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 新規建売買区分 */
    private String newCreditOrderType;
    
    /** 弁済期限 */
    private String paymentDeadline;
    
    /** 返済順序 */
    private String repaymentOrder;
}
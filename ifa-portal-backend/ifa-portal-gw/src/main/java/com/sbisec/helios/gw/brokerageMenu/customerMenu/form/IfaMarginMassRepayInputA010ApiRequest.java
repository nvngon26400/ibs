package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-05_1
 * 画面名：信用一括返済入力
 * 2024/04/15 新規作成
 *
 * @author SCSK 池亀緑
 */
@Data
public class IfaMarginMassRepayInputA010ApiRequest {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 新規建売買区分 */
    private String newCreditOrderType;
    
    /** 弁済期限 */
    private String paymentDeadline;
    
    /** 建玉明細 */
    private List<IfaMarginMassRepayInputApiPositionDetail> positionDetailList;
    
    /** 返済方法 */
    private String repayMethod;
    
    /** 返済順序 */
    private String repaymentOrder;
    
    /** 合計数量 */
    private String totalQuantity;
}
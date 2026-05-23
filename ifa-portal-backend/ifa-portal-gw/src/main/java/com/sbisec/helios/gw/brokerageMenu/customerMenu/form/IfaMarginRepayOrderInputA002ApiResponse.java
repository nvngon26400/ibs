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
public class IfaMarginRepayOrderInputA002ApiResponse {
    
    /** 返済建玉明細 */
    private List<IfaMarginRepayOrderInputApiRepayPositionDetail> repayPositionDetailList;
    
    /** 営業日リスト */
    private List<String> businessDayList;
}

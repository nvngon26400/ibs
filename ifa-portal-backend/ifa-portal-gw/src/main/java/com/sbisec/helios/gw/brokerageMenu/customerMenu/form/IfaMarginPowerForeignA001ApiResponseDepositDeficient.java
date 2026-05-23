package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 信用余力(米株) A001 レスポンスパラメータ
 * 預り金不足リスト
 * 
 * @author SCSK 矢口
 *
 */
@Data
public class IfaMarginPowerForeignA001ApiResponseDepositDeficient {
    
    /** 解消期限. */
    private String cancellationDeadline;
    
    /** 状況（全角半角）. */
    private String status;
    
    /** 預り金不足額（数値(整数)）. */
    private String depositShortage;
    
    /** 入金額（数値(整数)）. */
    private String depositAmount;
    
}

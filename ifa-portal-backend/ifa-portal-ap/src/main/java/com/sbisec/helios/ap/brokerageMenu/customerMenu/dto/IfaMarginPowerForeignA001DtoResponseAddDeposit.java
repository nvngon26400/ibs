package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用余力(米株) A001 レスポンスパラメータ
 * 追加保証金リスト

 * @author SCSK 矢口
 *
 */
@Data
public class IfaMarginPowerForeignA001DtoResponseAddDeposit {
    
    /** 値洗区分（全角半角）. */
    private String markToMarketType;
    
    /** 解消期限. */
    private String cancellationDeadline;
    
    /** 当初追証額（数値(整数)）. */
    private String initialMarginAmount;
    
    /** 追証発生日. */
    private String marginCallDate;

    /** 入金充当額. */
    private String addDepositPaymentInterest;
    
    /** 代用充当額（数値(小数)）. */
    private String substituteInterest;
    
    /** 決済建玉充当額（数値(小数)）. */
    private String settlementOpenInterestForeignCurrency;
    
    /** 必要額（数値(小数)）. */
    private String openInterestCostForeignCurrency;
    
}

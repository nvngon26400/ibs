package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用余力(米株) A001 レスポンスパラメータ
 * 新規建不足リスト

 * @author SCSK 矢口
 *
 */
@Data
public class IfaMarginPowerForeignA001DtoResponseNewPositionDeficient {
    
    /** 解消期限. */
    private String cancellationDeadline;
    
    /** 新規建不足金額（数値(小数)）. */
    private String constructionShortage;
    
    /** 発生日. */
    private String accuralDate;
    
    /** 入金充当額（数値(小数)）. */
    private String paymentInterest;
    
    /** 代用充当額（数値(小数)）. */
    private String substituteInterest;
    
    /** 決済建玉充当額（数値(小数)）. */
    private String settlementOpenInterestForeignCurrency;
    
    /** 必要額（数値(小数)）. */
    private String openInterestCostForeignCurrency;
    
}

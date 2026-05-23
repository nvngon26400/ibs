package com.sbisec.helios.ap.common.brokerMaintenance.dto;

import lombok.Data;

@Data
public class IfaForeignPositionDetailX001RequestDto {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 新規売買区分（全角半角）. */
    private String openTradeKbn;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 個別一括判定（全角半角）. */
    private String individualBatchJudge;
    
    /** 国内約定日. */
    private String businessDaysAfterOrder;
    
    /** 現地約定日. */
    private String LocalTradeDate;
    
    /** 新規建単価（外貨）. */
    private String previousDayValue;
    
    /** 新規建単価（円貨）（数値(整数)）. */
    private String jpyAmountNewPositionPrice;
    
}

package com.sbisec.helios.gw.common.brokerMaintenance.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaForeignPositionDetailX001ApiRequest {
    
    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 国コード（全角半角）. */
    @NotEmpty(message = "国コード")
    @Size(min = 2, max = 2, message = "国コード")
    private String countryCode;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 新規売買区分（全角半角）. */
    @NotEmpty(message = "新規売買区分")
    @Size(min = 1, max = 1, message = "新規売買区分")
    private String openTradeKbn;
    
    /** 弁済期限（全角半角）. */
    @NotEmpty(message = "弁済期限")
    @Size(max = 6, message = "弁済期限")
    private String paymentDeadline;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 個別一括判定（全角半角）. */
    @NotEmpty(message = "個別一括判定")
    @Size(min = 1, max = 1, message = "個別一括判定")
    private String individualBatchJudge;
    
    /** 国内約定日. */
    @NotEmpty(message = "国内約定日")
    private String businessDaysAfterOrder;
    
    /** 現地約定日. */
    @NotEmpty(message = "現地約定日")
    private String LocalTradeDate;
    
    /** 新規建単価（外貨）. */
    @NotEmpty(message = "新規建単価（外貨）")
    private String previousDayValue;
    
    /** 新規建単価（円貨）（数値(整数)）. */
    @Digits(integer = 17, fraction = 0, message = "新規建単価（円貨）")
    @NotEmpty(message = "新規建単価（円貨）")
    @Size(max = 22, message = "新規建単価（円貨）")
    private String jpyAmountNewPositionPrice;
    
}

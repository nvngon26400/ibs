package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class IfaMatchedTradeDetailA001ApiRequest {
    
    /** 約定日. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "約定日")
    @Size(min = 10, max = 10, message = "約定日")
    private String tradeDate;
    
    /** 受渡予定日. */
    @DateTimeFormat(pattern = "yy年MM月dd日")
    @JsonFormat(pattern = "yy年MM月dd日")
    @NotEmpty(message = "受渡予定日")
    @Size(min = 11, max = 11, message = "受渡予定日")
    private String deliveryDate;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 取引区分. */
    @NotEmpty(message = "取引区分")
    private String tradeClassification;
    
    /** 弁済期限（全角半角）. */
    @NotEmpty(message = "弁済期限")
    private String paymentDeadline;
    
    /** 信用取引区分（全角半角）. */
    @NotEmpty(message = "信用取引区分")
    @Size(max = 4, message = "信用取引区分")
    private String marginTradeTypeText;
    
    /** 非特定預り売買区分（全角半角）. */
    @NotEmpty(message = "非特定預り売買区分")
    @Size(min = 1, max = 1, message = "非特定預り売買区分")
    private String notSpecificDepositTradeType;
    
}

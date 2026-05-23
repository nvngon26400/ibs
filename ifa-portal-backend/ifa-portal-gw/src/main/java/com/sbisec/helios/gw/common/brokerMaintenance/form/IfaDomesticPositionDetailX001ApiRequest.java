package com.sbisec.helios.gw.common.brokerMaintenance.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaDomesticPositionDetailX001ApiRequest {
    
    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 新規売買区分（全角半角）. */
    @NotEmpty(message = "新規売買区分")
    private String openTradeKbn;
    
    /** 弁済期限（全角半角）. */
    @NotEmpty(message = "弁済期限")
    private String paymentDeadline;
    
    /** 新規市場（全角半角）. */
    @NotEmpty(message = "新規市場")
    private String newOpenMarket;
    
    /** 新規建玉指定番号（数字）. */
    @NotEmpty(message = "新規建玉指定番号")
    @Pattern(regexp = "0-9", message = "新規建玉指定番号")
    @Size(max = 6, message = "新規建玉指定番号")
    private String newOpenInterestNumber;
    
    /** 親株新規約定日. */
    private String parentStockTradeDate;
    
    /** 新規約定日. */
    private String newTradeDate;
    
    /** 取得単価. */
    private String openPrice;
    
    /** 一括個別表示フラグ. */
    @NotEmpty(message = "一括個別表示フラグ")
    private String batchIndividualDisplayFlag;
    
}

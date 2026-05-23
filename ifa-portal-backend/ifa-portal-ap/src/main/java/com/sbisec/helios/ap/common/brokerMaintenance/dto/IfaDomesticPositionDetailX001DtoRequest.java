package com.sbisec.helios.ap.common.brokerMaintenance.dto;

import lombok.Data;

@Data
public class IfaDomesticPositionDetailX001DtoRequest {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 新規売買区分（全角半角）. */
    private String openTradeKbn;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 新規市場（全角半角）. */
    private String newOpenMarket;
    
    /** 新規建玉指定番号（数字）. */
    private String newOpenInterestNumber;
    
    /** 親株新規約定日. */
    private String parentStockTradeDate;
    
    /** 新規約定日. */
    private String newTradeDate;
    
    /** 取得単価. */
    private String openPrice;
    
    /** 一括個別表示フラグ. */
    private String batchIndividualDisplayFlag;
    
}

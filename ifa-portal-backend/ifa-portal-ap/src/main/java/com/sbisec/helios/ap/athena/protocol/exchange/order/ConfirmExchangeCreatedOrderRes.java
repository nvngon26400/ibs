package com.sbisec.helios.ap.athena.protocol.exchange.order;

import com.sbisec.helios.ap.athena.enums.CurrencyCode;
import com.sbisec.helios.ap.athena.enums.DepositType;
import com.sbisec.helios.ap.athena.enums.ExchangeOrderType;

import lombok.Data;

@Data
public class ConfirmExchangeCreatedOrderRes {
    
    /** 注文番号 */
    private String orderNo;
    
    /** 通貨コード */
    private CurrencyCode currencyCode;
    
    /** 通貨名 */
    private String currencyName;
    
    /** 売買区分 */
    private String buySellCode;
    
    /** 為替注文金額 */
    private String orderAmount;
    
    /** 約定日時 */
    private String executionDatetime;
    
    /** 受渡日 */
    private String valueDate;
    
    /** 為替取引 */
    private String exchangeTradeType;
    
    /** 為替レート */
    private String exchangeRate;
    
    /** 為替レート日時 */
    private String rateDatetime;
    
    /** 受渡金額（円貨） */
    private String netAmount;
    
    /** 注文日時 */
    private String orderInputDatetime;
    
    /** 注文種別表示 */
    private ExchangeOrderType orderType;
    
    /** 預り区分 */
    private DepositType depositType;
    
}

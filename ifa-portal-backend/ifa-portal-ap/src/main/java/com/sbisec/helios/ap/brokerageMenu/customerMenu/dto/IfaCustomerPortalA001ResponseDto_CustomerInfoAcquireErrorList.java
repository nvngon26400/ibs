package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList {
    
    /** ISA買付可能枠の取得失敗メッセージ */
    private String isaBuyLimitAcquireFailureMsg;
    
    /** ISA買付可能枠の取得失敗エラーレベル */
    private String isaBuyLimitAcquireFailureErrorLevel;
    
    /** 買付余力情報（円貨）・信用余力情報（円貨）取得失敗メッセージ. */
    private String marginPowerInfoYenAcquireFailureMsg;
    
    /** 買付余力情報（円貨）・信用余力情報（円貨）取得失敗エラーレベル. */
    private String marginPowerInfoYenAcquireFailureErrorLevel;
    
    /** 外貨建取引口座開設状況取得失敗メッセージ. */
    private String foreignTradeAccountOpenStatusAcquireFailureMsg;
    
    /** 外貨建取引口座開設状況取得失敗エラーレベル. */
    private String foreignTradeAccountOpenStatusAcquireFailureErrorLevel;
    
    /** 買付余力情報（外貨）取得失敗メッセージ. */
    private String buyReservePowerInfoForeignAcquireFailureMsg;
    
    /** 買付余力情報（外貨）取得失敗エラーレベル. */
    private String buyReservePowerInfoForeignAcquireFailureErrorLevel;
    
    /** 米株信用口座開設状況取得失敗メッセージ. */
    private String usStockMarginAccountOpenStatusAcquireFailureMsg;
    
    /** 米株信用口座開設状況取得失敗エラーレベル. */
    private String usStockMarginAccountOpenStatusAcquireFailureErrorLevel;
    
    /** 信用余力情報（外貨）の取得失敗メッセージ */
    private String marginPowerInfoForeignAcquireFailureMsg;
    
    /** 信用余力情報（外貨）の取得失敗エラーレベル */
    private String marginPowerInfoForeignAcquireFailureErrorLevel;
    
}

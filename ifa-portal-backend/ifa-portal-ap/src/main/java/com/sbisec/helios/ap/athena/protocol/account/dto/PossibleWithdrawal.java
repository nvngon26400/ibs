package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PossibleWithdrawal implements Serializable {
    
    private static final long serialVersionUID = -6643571307731939266L;
    
    public PossibleWithdrawal() {
        
    }
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 出金予定日 */
    private String withdrawalDate;
    
    /** 口座分類 */
    private String accountKind;
    
    /** 出金可能額 */
    private String withdrawalPossibleAmount;
    
}

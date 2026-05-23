package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PossibleWithdrawalInput implements Serializable {
    
    private static final long serialVersionUID = 2942114414821361687L;
    
    public PossibleWithdrawalInput() {
        
    }
    
    /** 通貨コード */
    private String currencyCode;
    
    /** 出金予定日 */
    private String withdrawalDate;
    
    /** 口座分類 */
    private String accountKind;
    
    /** 入出金仕訳種別 */
    private String cashingJournalType;
    
}

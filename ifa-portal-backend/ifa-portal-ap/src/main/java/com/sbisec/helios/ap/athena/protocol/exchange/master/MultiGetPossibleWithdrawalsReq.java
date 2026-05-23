package com.sbisec.helios.ap.athena.protocol.exchange.master;

import lombok.Data;

@Data
public class MultiGetPossibleWithdrawalsReq {
    
    /**オペレーターID*/
    private String operator_id;
    
    /**出金情報*/
    private PossibleWithdrawals possibleWithdrawals;
    
    @Data
    public static class PossibleWithdrawals {
        
        /**通貨コード*/
        private String currencyCode;
        
        /**出金予定日*/
        private String withdrawalDate;
        
        /**口座分類*/
        private String accountKind;
        
        /**入出金仕訳種別*/
        private String cashingJournalType;
    }
    
}

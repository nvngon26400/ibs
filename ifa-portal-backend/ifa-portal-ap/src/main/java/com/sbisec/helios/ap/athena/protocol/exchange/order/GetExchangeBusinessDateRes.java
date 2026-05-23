package com.sbisec.helios.ap.athena.protocol.exchange.order;

import lombok.Data;

@Data
public class GetExchangeBusinessDateRes {
    
    /** 営業日 */
    private String businessDate;
    
    /** サイクル番号 */
    private int cycleNumber;
    
    /** 終了日時 */
    private String closeDatetime;
    
    /** 入出金予定日 */
    private String depositWithdrawalDate;
    
}

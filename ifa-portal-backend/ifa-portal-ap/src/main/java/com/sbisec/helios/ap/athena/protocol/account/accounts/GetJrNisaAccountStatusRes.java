package com.sbisec.helios.ap.athena.protocol.account.accounts;

import lombok.Data;

@Data
public class GetJrNisaAccountStatusRes {
    
    public GetJrNisaAccountStatusRes() {
        
    }
    
    /** ジュニアNISA口座状態 */
    private String jrNisaAccountStatusCode;
    
}

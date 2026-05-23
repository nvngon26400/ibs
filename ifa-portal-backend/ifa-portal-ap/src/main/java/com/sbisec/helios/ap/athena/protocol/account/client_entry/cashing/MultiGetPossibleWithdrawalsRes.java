package com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleWithdrawal;

import lombok.Data;

@Data
public class MultiGetPossibleWithdrawalsRes {
    
    public MultiGetPossibleWithdrawalsRes() {
        
    }
    
    /** 出金情報 */
    private List<PossibleWithdrawal> possibleWithdrawals;
    
}

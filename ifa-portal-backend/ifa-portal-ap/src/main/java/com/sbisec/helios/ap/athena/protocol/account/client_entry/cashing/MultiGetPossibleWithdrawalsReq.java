package com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleWithdrawalInput;

import lombok.Data;

@Data
public class MultiGetPossibleWithdrawalsReq implements BaseRequest {
    
    public MultiGetPossibleWithdrawalsReq() {
        
    }
    
    // headerとparameterインスタンス化
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        public Header() {
            
        }
        
        /** オペレータID */
        private String operator_id;
        
        /** token */
        private String token;
    }
    
    @Data
    public class Parameter {
        
        public Parameter() {
            
        }
        
        /** 為替グループ */
        private List<PossibleWithdrawalInput> possibleWithdrawals;
        
        /** 余力チェック不要フラグ */
        private Boolean checkBalanceDisabled;
        
        /** 預り区分 */
        private String depositType;
    }
    
}

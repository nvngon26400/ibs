package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.account.dto.CollateralSecuritiesTransferInput;

import lombok.Data;

/**
 * 入出金・入出庫サービス - 代用有価証券振替確認API Request.
 *
 * @author SCSK川崎
 * @date: 03/26/2024
 */
@Data
public class ConfirmCollateralSecuritiesTransferReq implements BaseRequest {
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    /** 
     * 入出金・入出庫サービス - 代用有価証券振替確認API リクエストヘッダ
     */
    @Data
    public class Header {
        
        /** token ({部店}3桁 + "-" + {口座}7桁) */
        private String token;
        
    }
    
    /** 
     * 入出金・入出庫サービス - 代用有価証券振替確認API リクエストパラメータ.
     */
    @Data
    public class Parameter {
        
        /** 代用有価証券振替入力情報 */
        private List<CollateralSecuritiesTransferInput> transfers;
        
    }
}

package com.sbisec.helios.ap.athena.protocol.fstock.lending;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

/**
 * 外国株式貸株サービス - 外国株式貸株サービス加入判定API Request.
 *
 * @author SCSK川崎
 * @date: 03/22/2024
 */
@Data
public class GetForeignStockLendingSubscribedStatusReq implements BaseRequest {
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    /** 
     * 外国株式貸株サービス - 外国株式貸株サービス加入判定API リクエストヘッダ
     */
    @Data
    public class Header {
        
        /** token ({部店}3桁 + "-" + {口座}7桁) */
        private String token;
        
    }
    
    /** 
     * 外国株式貸株サービス - 外国株式貸株サービス加入判定API リクエストパラメータ.
     */
    @Data
    public class Parameter {
        
    }
}

package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式現物注文取消登録API リクエスト.
 *
 * @author 宇田川達弥
 * @date 2024/04/01
 */
@Data
public class DeleteForeignStockOrderReq implements BaseRequest {
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    /** 
     * 外国株式取引サービス - 外国株式現物注文取消登録API リクエストヘッダ.
     */
    @Data
    public class Header {
        
        /** token ({部店}3桁 + "-" + {口座}7桁) */
        private String token;

        /** 取引パスワード */
        @JsonProperty("trade_password")
        private String tradePassword;

        /** Request Id */
        @JsonProperty("request_id")
        private String requestId;
    }
    
    /** 
     * 外国株式取引サービス - 外国株式現物注文取消登録API リクエストパラメータ.
     */
    @Data
    public class Parameter {
        
        /** 注文Sub番号 */
        @UrlParm(name = "order_sub_no")
        private String orderSubNo;
    }
}

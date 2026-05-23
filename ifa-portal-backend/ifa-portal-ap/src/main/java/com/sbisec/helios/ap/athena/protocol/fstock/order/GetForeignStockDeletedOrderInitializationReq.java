package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式現物注文取消初期情報取得API リクエスト.
 *
 * @author 宇田川達弥
 * @date 2024/04/01
 */
@Data
public class GetForeignStockDeletedOrderInitializationReq implements BaseRequest {
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    /** 
     * 外国株式取引サービス - 外国株式現物注文取消初期情報取得API リクエストヘッダ.
     */
    @Data
    public class Header {
        
        /** token ({部店}3桁 + "-" + {口座}7桁) */
        private String token;
    }
    
    /** 
     * 外国株式取引サービス - 外国株式現物注文取消初期情報取得API リクエストパラメータ.
     */
    @Data
    public class Parameter {
        
        /** 注文Sub番号 */
        @UrlParm(name = "order_sub_no")
        private String orderSubNo;
    }
}

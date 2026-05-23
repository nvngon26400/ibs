package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式現物注文初期情報取得取得API リクエスト.
 *
 * @author SCSK 笹倉 秀行
 * @date 02/13/2024
 */
@Data
public class GetForeignStockCreatedOrderInitializationReq implements BaseRequest {
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    /** 
     * 外国株式取引サービス - 外国株式現物注文初期情報取得取得API リクエストヘッダ
     */
    @Data
    public class Header {
        
        /** token ({部店}3桁 + "-" + {口座}7桁) */
        private String token;   
    }
    
    /** 
     * 外国株式取引サービス - 外国株式現物注文初期情報取得取得API リクエストパラメータ.
     */
    @Data
    public class Parameter {
        
        /** 国コード */
        private String countryCode;
        
        /** 銘柄コード */
        private String securitiesCode; 
        
        /** 売買区分 */
        private String buySellCode;
    }
}

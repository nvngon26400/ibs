package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

import lombok.Data;

/**
 * 外国株式ロシア株呼値情報取得 Request
 *
 * @author SCSK 笹倉 秀行
 * @date 02/13/2024
 */
@Data
public class GetForeignStockRuTickSizeReq implements BaseRequest {
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    /**
     * 外国株式ロシア株呼値情報取得のリクエストヘッダ
     */
    @Data
    public class Header {
        
    }
    
    /**
     * 外国株式ロシア株呼値情報取得のリクエストパラメータ
     */
    @Data
    public class Parameter {
        
        // 銘柄コード
        @UrlParm(name = "securities_code")
        private String securitiesCode;
        
    }
}

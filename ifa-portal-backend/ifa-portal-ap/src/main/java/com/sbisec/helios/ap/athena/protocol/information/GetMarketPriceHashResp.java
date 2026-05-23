package com.sbisec.helios.ap.athena.protocol.information;

import lombok.Data;

/**
 * @Description マーケット価格情報サービス - マーケット価格ハッシュ取得API Response.
 * 
 * 2023/11/22移植
 */
@Data
public class GetMarketPriceHashResp {
    
    public GetMarketPriceHashResp() {
        
    }
    
    /** ハッシュ結果 */
    private String hashValue;
    
    /** リアルタイム種別 */
    private String realTimeType;
    
    /** ディレイのハッシュ（※常にディレイハッシュを返す） */
    private String delayHashValue;
}

package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.enums.CountryCode;

import lombok.Data;

/**
 * 外国株式ロシア株呼値情報取得 Response
 *
 * @author SCSK 笹倉 秀行
 * @date 02/13/2024
 */
@Data
public class GetForeignStockRuTickSizeResp {
    
    /** 国コード */
    private CountryCode countryCode;
    
    /** 銘柄コード */
    private String securitiesCode;
    
    /** 呼値 */
    private String tickSize;
    
    /** RICコード */
    private String ricCode;
    
}

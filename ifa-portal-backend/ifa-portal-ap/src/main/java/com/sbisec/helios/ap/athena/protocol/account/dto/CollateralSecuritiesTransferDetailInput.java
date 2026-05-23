package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 振替詳細情報 Dto.

 * @author SCSK 川崎
   @date 03/26/2024
 */
@Data
public class CollateralSecuritiesTransferDetailInput implements Serializable {
    
    private static final long serialVersionUID = 6494122421126775788L;
    
    public CollateralSecuritiesTransferDetailInput() {
        
    }
    
    /** 国コード */
    private String countryCode;
    
    /** 商品コード */
    private String productCode;
    
    /** 銘柄コード */
    private String securitiesCode;
    
    /** 預り区分 */
    private String specificAccountCode;
    
    /** 保護区分 */
    private String depositType;
    
}

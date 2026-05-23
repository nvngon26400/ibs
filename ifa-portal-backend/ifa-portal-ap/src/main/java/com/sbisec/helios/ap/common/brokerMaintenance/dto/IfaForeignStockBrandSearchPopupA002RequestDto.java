package com.sbisec.helios.ap.common.brokerMaintenance.dto;

import lombok.Data;

@Data
public class IfaForeignStockBrandSearchPopupA002RequestDto {
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 銘柄名称またはコード（全角半角）. */
    private String brandNameBrandCode;
    
    /** 検索マッチ種別. */
    private String searchOptions;
    
    /** 国籍. */
    private String countryCode;
    
    /** 市場（全角）. */
    private String market;
    
}

package com.sbisec.helios.gw.common.brokerMaintenance.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaForeignStockBrandSearchPopupA002ApiRequest {
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;
    
    /** 銘柄名称またはコード（全角半角）. */
    @Size(max = 128, message = "銘柄名称またはコード")
    private String brandNameBrandCode;
    
    /** 検索マッチ種別. */
    @NotEmpty(message = "検索マッチ種別")
    private String searchOptions;
    
    /** 国籍. */
    @NotEmpty(message = "国籍")
    private String countryCode;
    
    /** 市場（全角）. */
    @Size(max = 4, message = "市場")
    private String market;
    
}

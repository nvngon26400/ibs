package com.sbisec.helios.gw.common.brokerMaintenance.form;

import lombok.Data;

@Data
public class IfaForeignStockBrandSearchPopupA002ApiResponse_MarginNewSell {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 市場コード（全角半角）. */
    private String marketCode;
    
    /** 市場略名. */
    private String marketAbbreviatedName;
    
    /** 在庫株数. */
    private String positionQuantity;
    
}

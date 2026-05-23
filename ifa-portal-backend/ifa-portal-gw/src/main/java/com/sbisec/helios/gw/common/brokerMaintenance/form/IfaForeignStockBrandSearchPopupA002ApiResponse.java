package com.sbisec.helios.gw.common.brokerMaintenance.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaForeignStockBrandSearchPopupA002ApiResponse {
    
    /** 信用新規売以外. */
    private List<IfaForeignStockBrandSearchPopupA002ApiResponse_MarginNewSellExcepting> marginNewSellExceptingList;
    
    /** 信用新規売. */
    private List<IfaForeignStockBrandSearchPopupA002ApiResponse_MarginNewSell> marginNewSellList;
    
}

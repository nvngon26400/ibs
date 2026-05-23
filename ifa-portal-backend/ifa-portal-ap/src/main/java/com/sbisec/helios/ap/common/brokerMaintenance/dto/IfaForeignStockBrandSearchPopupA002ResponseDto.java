package com.sbisec.helios.ap.common.brokerMaintenance.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaForeignStockBrandSearchPopupA002ResponseDto {
    
    /** 信用新規売以外. */
    private List<IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSellExcepting> marginNewSellExceptingList;
    
    /** 信用新規売. */
    private List<IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSell> marginNewSellList;
    
}

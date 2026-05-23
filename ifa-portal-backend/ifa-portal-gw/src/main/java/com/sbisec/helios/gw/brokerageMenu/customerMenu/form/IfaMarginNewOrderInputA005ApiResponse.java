package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaMarginNewOrderInputA005ApiResponse {
    
    /** 取引種別. */
    private String tradeCd;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 受渡日(T+0). */
    private String settlementDateT0;
    
    /** 受渡日(T+1). */
    private String settlementDateT1;
    
    /** 新規建余力（T+0）. */
    private String newBuildingCapacityT0;
    
    /** 新規建余力（T+1）. */
    private String newBuildingCapacityT1;
    
    /** 維持率（T+0）. */
    private String rateT0;
    
    /** 維持率（T+1）. */
    private String rateT1;
    
    /**　営業日リスト　*/
    private String periodRadio;
}

package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaMarginNewOrderInputA001ApiResponse {
    
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

    /** 維持率(円貨)説明文言 */
    private String maintenanceRateJpyAmountDescriptionMessage;

}

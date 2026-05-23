package com.sbisec.helios.ap.common.composite.model;

import lombok.Data;

@Data
public class IfaBrandPriceInfoSql001RequestModel {
    
    /** 市場. */
    private String market;
    
    /** 呼値フラグ. */
    // 呼値へマッピング
    private String orderPriceUnit;
}

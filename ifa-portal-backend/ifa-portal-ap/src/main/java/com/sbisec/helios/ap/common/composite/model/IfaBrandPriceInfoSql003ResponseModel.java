package com.sbisec.helios.ap.common.composite.model;

import lombok.Data;

@Data
public class IfaBrandPriceInfoSql003ResponseModel {

    /** 呼値単位番号（東京） */
    private String orderPriceUnitTokyo;
    
    /** 呼値単位番号（名古屋） */
    private String orderPriceUnitNagoya;

    /** 呼値単位番号（福岡） */
    private String orderPriceUnitFukuoka;

    /** 呼値単位番号（札幌） */
    private String orderPriceUnitSapporo;

}

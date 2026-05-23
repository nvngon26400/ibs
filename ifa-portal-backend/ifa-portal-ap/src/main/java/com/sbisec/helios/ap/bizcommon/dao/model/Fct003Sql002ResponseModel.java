package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

@Data
public class Fct003Sql002ResponseModel {
    
    /**
     * 証券金銭種別
     */
    private String productCd;
    
    /**
     * 取引種別
     */
    private String tradeCd;
    
    /**
     * 国籍コード
     */
    private String countryCd;
    
    /**
     * 通貨コード
     */
    private String currencyCode;
    
    /**
     * 媒介可否設定値
     */
    private String value;
}

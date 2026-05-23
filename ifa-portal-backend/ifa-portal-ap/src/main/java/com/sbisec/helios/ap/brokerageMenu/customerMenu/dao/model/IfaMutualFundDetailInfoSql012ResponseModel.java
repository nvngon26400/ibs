package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 投信詳細情報 SQL012応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaMutualFundDetailInfoSql012ResponseModel {
    
    /** ファンドコード. */
    private String fundCode;
    
    /** 積立単位. */
    private String reserveUnit;
    
}

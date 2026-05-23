package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 投信詳細情報SQL002応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaMutualFundDetailInfoSql002ResponseModel {
    
    /** 休場日（全角半角）. */
    private String fdcCtlDate;
    
    /** 営業日フラグ. */
    private String businessDayFlag;
    
}

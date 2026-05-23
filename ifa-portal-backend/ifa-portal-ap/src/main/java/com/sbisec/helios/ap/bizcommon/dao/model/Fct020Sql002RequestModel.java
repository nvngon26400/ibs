package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

/**
 * FC020 SQL002リクエストModel
 *
 * @author SCSK
 */
@Data
public class Fct020Sql002RequestModel {

    /** 銘柄コード. */
    private String brandCode;
    
    /** 選択市場コード. */
    private String selectMarketCode;

}

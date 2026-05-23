package com.sbisec.helios.ap.bizcommon.dao.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * FC020 SQL002レスポンスModel
 *
 * @author SCSK
 */
@Data
public class Fct020Sql002ResponseModel {
    
    /** 翌日基準値 */
    private BigDecimal  basePrice;
}

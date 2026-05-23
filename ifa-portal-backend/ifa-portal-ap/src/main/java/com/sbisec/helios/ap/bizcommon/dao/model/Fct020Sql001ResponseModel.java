package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

/**
 * FC020 SQL002レスポンスModel
 *
 * @author SCSK
 */
@Data
public class Fct020Sql001ResponseModel {
    
    /** 主要取引所コード */
    private String preExCode;

    /** SBI主要取引所コード */
    private String preExCodeSbi;
}

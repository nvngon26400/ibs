package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

/**
 * DAOリクエストモデル：FCT017
 *
 * @author SCSK
 *
 */

@Data
public class Fct017Sql002RequestModel {
    
    // 部店コード
    private String butenCode;
    
    //口座番号
    private String accountNumber;
    
    // 銘柄コード
    private String brandCode;
    
    // 書類コード
    private String shoruiCd;
}

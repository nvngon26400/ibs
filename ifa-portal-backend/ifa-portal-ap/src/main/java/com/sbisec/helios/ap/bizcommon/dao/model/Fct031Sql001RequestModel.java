package com.sbisec.helios.ap.bizcommon.dao.model;

import lombok.Data;

/**
 * 顧客情報取得リクエスト
 *
 * @author SCSK
 *
 */
@Data
public class Fct031Sql001RequestModel {
    
    // 部店コード
    private String butenCode;
    
    // 口座番号
    private String accountNumber;
    
}

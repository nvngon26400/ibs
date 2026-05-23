package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 外債買付代金合計の算出リクエスト
 * @author SCSK
 *
 */
@Data
public class Fct004Sql002RequestModel extends ModelBase {
    
    // 部店コード
    private String butenCode;
    
    // 口座番号
    private String accountNumber;
    
    // 名称
    private int name;
    
}

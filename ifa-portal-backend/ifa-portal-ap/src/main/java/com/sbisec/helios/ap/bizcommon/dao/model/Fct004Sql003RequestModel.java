package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 店頭取引注文情報の取得リクエスト
 * @author SCSK
 *
 */
@Data
public class Fct004Sql003RequestModel extends ModelBase {
    
    // 部店コード
    private String butenCode;
    
    // 口座番号
    private String accountNumber;
    
}

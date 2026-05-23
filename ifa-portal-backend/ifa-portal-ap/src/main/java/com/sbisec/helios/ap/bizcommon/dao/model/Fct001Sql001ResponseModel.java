package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 利用者顧客参照権限
 *
 * @author SCSK
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Fct001Sql001ResponseModel extends ModelBase {
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** コンプラランク */
    private String tcCompRank;
    
}

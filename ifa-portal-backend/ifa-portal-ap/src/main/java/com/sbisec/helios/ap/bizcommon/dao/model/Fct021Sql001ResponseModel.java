package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 取引制限マトリクス取得
 *
 * @author SCSK
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Fct021Sql001ResponseModel extends ModelBase {
    
    /** 制限種類 */
    private String restrictType;
    
    /** 制限コード */
    private String restrictCode;
    
    /** 取引制限設定値 */
    private String value;
}

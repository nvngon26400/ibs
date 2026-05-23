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
public class Fct021Sql002ResponseModel extends ModelBase {
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 注意情報エラー有無 */
    private Integer noteInfoErrFlag;
    
    /** 注意情報アラート有無 */
    private Integer noteInfoAlertFlag;
    
    /** お知らせエラー有無 */
    private Integer noteLimitErrFlag;
    
    /** お知らせアラート有無 */
    private Integer noteLimitAlertFlag;
}

package com.sbisec.helios.ap.bizcommon.dao.model;

import java.util.List;

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
public class Fct021Sql002RequestModel extends ModelBase {
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** SQL001結果 */
    private List<Fct021Sql001ResponseModel> fct021Sql001ResponseModel;
    
}

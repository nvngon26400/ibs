package com.sbisec.helios.ap.bizcommon.dao.model;

import com.sbibits.earth.model.ModelBase;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 外債買付代金合計の算出レスポンス
 * @author SCSK
 *
 */
@Data
public class Fct004Sql002ResponseModel extends ModelBase {
    
    // 金額
    private BigDecimal totalAmount;
    
}

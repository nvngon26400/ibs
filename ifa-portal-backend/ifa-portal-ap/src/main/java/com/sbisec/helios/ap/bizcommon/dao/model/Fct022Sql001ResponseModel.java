package com.sbisec.helios.ap.bizcommon.dao.model;

import java.math.BigDecimal;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * FCT022.sql001 国内投信基準価額取得
 *
 * @author 陳
 *
 */
@Data
public class Fct022Sql001ResponseModel extends ModelBase {
    
    //基準価額
    private BigDecimal basePrice;
    
    //前日比
    private BigDecimal diff;
    
    //協会コード
    private String kyoukaiCd;
}

package com.sbisec.helios.ap.bizcommon.dao.model;

import java.math.BigDecimal;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * FCT022.sql003 扱者個別国内投信販売手数料取得
 *
 * @author 陳
 *
 */
@Data
public class Fct022Sql003ResponseModel extends ModelBase {
    
    //ファンドタイプ
    private String fundType;
    
    //協会コード
    private String kyoukaiCd;
    
    //販売手数料率1
    private BigDecimal salesCommRate1;
    
    //販売手数料率2
    private BigDecimal salesCommRate2;
}

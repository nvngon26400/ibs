package com.sbisec.helios.ap.bizcommon.dao.model;

import java.math.BigDecimal;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FCT022.sql002 国内投信販売手数料取得
 *
 * @author 陳
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Fct022Sql002ResponseModel extends ModelBase {
    
    //NRIコード 
    private String nriCd;
    
    //販売手数料率1
    private BigDecimal salesCommRate1;
    
    //販売手数料率2
    private BigDecimal salesCommRate2;
    
    //乗換優遇率分母
    private BigDecimal transfersPreferentialRateDenominator;
    
    //乗換優遇率分子
    private BigDecimal transfersPreferentialRateNumerator;
    
    //注文締切時間
    private String orderDeadlineTime;
    
    //ファンドタイプ
    private String fundType;
    
    //協会コード
    private String kyoukaiCd;
}

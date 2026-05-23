package com.sbisec.helios.ap.bizcommon.dao.model;

import java.math.BigDecimal;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

/**
 * 共通関数FCT024 SQL002レスポンスモデル
 *
 * @author SCSK
 *
 */
@Data
public class Fct024Sql002ResponseModel extends ModelBase {
    
    /*
     * 販売手数料上限1～7
     */
    private BigDecimal salesCommLimit1;
    
    private BigDecimal salesCommLimit2;
    
    private BigDecimal salesCommLimit3;
    
    private BigDecimal salesCommLimit4;
    
    private BigDecimal salesCommLimit5;
    
    private BigDecimal salesCommLimit6;
    
    private BigDecimal salesCommLimit7;
    
    /*
     * 販売手数料率1～7
     */
    private BigDecimal salesCommRate1;
    
    private BigDecimal salesCommRate2;
    
    private BigDecimal salesCommRate3;
    
    private BigDecimal salesCommRate4;
    
    private BigDecimal salesCommRate5;
    
    private BigDecimal salesCommRate6;
    
    private BigDecimal salesCommRate7;
    
    /*
     * 解約手数料率1
     */
    private BigDecimal cancelCommRate1;

    /*
     * 販売手数料上限1～7(特例特定/一般)
     */
    private BigDecimal salesCommLimitSpecificGeneral1;
    
    private BigDecimal salesCommLimitSpecificGeneral2;
    
    private BigDecimal salesCommLimitSpecificGeneral3;
    
    private BigDecimal salesCommLimitSpecificGeneral4;
    
    private BigDecimal salesCommLimitSpecificGeneral5;
    
    private BigDecimal salesCommLimitSpecificGeneral6;
    
    private BigDecimal salesCommLimitSpecificGeneral7;
    
    /*
     * 販売手数料率1～7(特例特定/一般)
     */
    private BigDecimal salesCommRateSpecificGeneral1;
    
    private BigDecimal salesCommRateSpecificGeneral2;
    
    private BigDecimal salesCommRateSpecificGeneral3;
    
    private BigDecimal salesCommRateSpecificGeneral4;
    
    private BigDecimal salesCommRateSpecificGeneral5;
    
    private BigDecimal salesCommRateSpecificGeneral6;
    
    private BigDecimal salesCommRateSpecificGeneral7;
    
    /*
     * 解約手数料率1(特例特定/一般)
     */
    private BigDecimal cancelCommRateSpecificGeneral1;
    
    /*
     * 販売手数料上限1～7(総合NISA・成長投資枠)
     */
    private BigDecimal salesCommLimitSeichou1;
    
    private BigDecimal salesCommLimitSeichou2;
    
    private BigDecimal salesCommLimitSeichou3;
    
    private BigDecimal salesCommLimitSeichou4;
    
    private BigDecimal salesCommLimitSeichou5;
    
    private BigDecimal salesCommLimitSeichou6;
    
    private BigDecimal salesCommLimitSeichou7;
    
    /*
     * 販売手数料率1～7(総合NISA・成長投資枠)
     */
    private BigDecimal salesCommRateSeichou1;
    
    private BigDecimal salesCommRateSeichou2;
    
    private BigDecimal salesCommRateSeichou3;
    
    private BigDecimal salesCommRateSeichou4;
    
    private BigDecimal salesCommRateSeichou5;
    
    private BigDecimal salesCommRateSeichou6;
    
    private BigDecimal salesCommRateSeichou7;
    
    /*
     * 解約手数料率1(総合NISA・成長投資枠)
     */
    private BigDecimal cancelCommRateSeichou1;
    
    /*
     * 解約手数料率1(継続管理勘定) 
     */
    private BigDecimal cancelCommRateKeizoku1;
    
    /*
     * 解約手数料率1(ISA)
     */
    private BigDecimal cancelCommRateIsa1;

    /*
     * 解約手数料率1(ジュニアNISA)
     */
    private BigDecimal cancelCommRateJrNisa1;
    
    // 販売手数料単位口数
    private BigDecimal salesCommTanikuchi;
    
    //乗換優遇率・分母
    private BigDecimal transfersPreferentialRateDenominator;
    
    //乗換優遇率・分子
    private BigDecimal transfersPreferentialRateNumerator;
    
    //乗換優遇率・分母(特例特定/一般)
    private BigDecimal transfersPreferentialRateDenominatorToku;
    
    //乗換優遇率・分子(特例特定/一般)
    private BigDecimal transfersPreferentialRateNumeratorToku;
    
    //ファンドタイプ
    private String fundType;
    
    //協会コード
    private String kyoukaiCd;
}

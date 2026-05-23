package com.sbisec.helios.ap.bizcommon.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * 共通関数DTO：FCT022
 * @author SCSK
 */

@Data
public class OutputFct022Dto extends BaseOutputDto {
    
    //銘柄リスト
    private List<Brand> brandList;
    
    @Data
    public static class Brand {
        
        //NRIコード 
        private String nriCd;
        
        //基準価額
        private BigDecimal basePrice;
        
        //前日比
        private BigDecimal diff;
        
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
    }
    
}

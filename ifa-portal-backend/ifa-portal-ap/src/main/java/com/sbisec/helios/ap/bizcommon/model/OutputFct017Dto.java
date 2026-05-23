package com.sbisec.helios.ap.bizcommon.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 共通関数DTO：FCT017

 * @author SCSK
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class OutputFct017Dto extends BaseOutputDto {
    
    //銘柄リスト
    private List<Brand> brandList;

    /**
     * 銘柄
     *
     * @author SCSK
     */
    @Data
    public static class Brand {
        
        //NRIコード 
        private String nriCd;
        
        //NRIコード.銘柄取得可否
        private String brandAcquirePropriety;
        
        //NRIコード.書類コード
        private String shoruiCd;
        
        //NRIコード.書類コード.受入要否
        private String acceptanceNecessity;
        
        //NRIコード.書類コード.投信銘柄種別
        private String mutualFundBrandClass;
        
        //NRIコード.書類コード.受入状況
        private String acceptanceStatus;
    }
}

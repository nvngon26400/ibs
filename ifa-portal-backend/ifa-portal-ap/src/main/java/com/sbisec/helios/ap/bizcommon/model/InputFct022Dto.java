package com.sbisec.helios.ap.bizcommon.model;


import java.util.List;

import lombok.Data;

/**
 * 共通関数DTO：FCT022
 * @author SCSK
 */

@Data
public class InputFct022Dto {
    
    //銘柄リスト
    private List<BrandForInput> brandList;
    
    //扱者コード
    private String dealerNumber;

    @Data
    public static class BrandForInput {
        //NRIコード 
        private String nriCd;
    }
}

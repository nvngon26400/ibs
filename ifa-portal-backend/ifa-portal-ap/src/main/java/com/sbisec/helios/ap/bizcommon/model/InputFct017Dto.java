package com.sbisec.helios.ap.bizcommon.model;

import java.util.List;

import lombok.Data;

/**
 * 共通関数DTO：FCT017

 * @author SCSK
 */

@Data
public class InputFct017Dto {
    
    //部店コード
    private String butenCode;
    
    //口座番号
    private String accountNumber;
    
    //照会投信銘柄リスト
    private List<InquiryMutualFundBrand> inquiryMutualFundBrandList;
    
    /**
     * 照会投信銘柄
     *
     * @author SCSK
     */

    @Data
    public static class InquiryMutualFundBrand {
        
        //NRIコード
        private String nriCd;
    }
    
}

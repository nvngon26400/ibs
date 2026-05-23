package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto {
    
    /** 前日比5%下落銘柄リスト. */
    private List<IfaMutualFundPriceChangeBrandHoldingList_compare5ParcentDeclineBrandList> compare5ParcentDeclineBrandList;
    
    /** 1ヶ月10%下落銘柄リスト. */
    private List<IfaMutualFundPriceChangeBrandHoldingList_oneMonth10PercentDeclineBrandList> oneMonth10PercentDeclineBrandList;
    
}

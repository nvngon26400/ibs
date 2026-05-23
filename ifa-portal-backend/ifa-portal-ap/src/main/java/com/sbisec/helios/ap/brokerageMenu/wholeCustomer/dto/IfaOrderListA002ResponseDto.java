package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;


import java.util.List;
import lombok.Data;

/**
*
*
* @author BASE李
*
*/
@Data
public class IfaOrderListA002ResponseDto {

    /** 国内株式リスト. */
    private List<IfaOrderListA002ResponseDtoDomesticStock> ifaOrderListA002ResponseDtoDomesticStockList;
    
    /** 国内投資信託リスト. */
    private List<IfaOrderListA002ResponseDtoDomesticMutualFund> ifaOrderListA002ResponseDtoDomesticMutualFundList;
    
    /** 国内投資信託（定期積立）リスト. */
    private List<IfaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitment> ifaOrderListA002ResponseDtoDomesticMutualFundRegularRecruitmentList;
    
    /** 募集注文リスト. */
    private List<IfaOrderListA002ResponseDtoSubscriptOrder> ifaOrderListA002ResponseDtoSubscriptOrderList;
    
    /** 外国株式（委託注文）リスト. */
    private List<IfaOrderListA002ResponseDtoForeignStockEntrustOrder> ifaOrderListA002ResponseDtoForeignStockEntrustOrderList;
    
    /** 外国株式（店頭注文）リスト. */
    private List<IfaOrderListA002ResponseDtoForeignStockCounterOrder> ifaOrderListA002ResponseDtoForeignStockCounterOrderList;

}

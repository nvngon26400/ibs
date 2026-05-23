package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;


import java.util.List;
import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaOrderListA002ApiResponse {

    /** 国内株式リスト. */
    private List<IfaOrderListA002ApiResponseDomesticStock> ifaOrderListA002ApiResponseDomesticStockList;

    /** 国内投資信託リスト. */
    private List<IfaOrderListA002ApiResponseDomesticMutualFund> ifaOrderListA002ApiResponseDomesticMutualFundList;
    
    /** 国内投資信託（定期積立）リスト. */
    private List<IfaOrderListA002ApiResponseDomesticMutualFundRegularRecruitment> ifaOrderListA002ApiResponseDomesticMutualFundRegularRecruitmentList;

    /** 募集注文リスト. */
    private List<IfaOrderListA002ApiResponseSubscriptOrder> ifaOrderListA002ApiResponseSubscriptOrderList;

    /** 外国株式（委託注文）リスト. */
    private List<IfaOrderListA002ApiResponseForeignStockEntrustOrder> ifaOrderListA002ApiResponseForeignStockEntrustOrderList;

    /** 外国株式（店頭注文）リスト. */
    private List<IfaOrderListA002ApiResponseForeignStockCounterOrder> ifaOrderListA002ApiResponseForeignStockCounterOrderList;

}

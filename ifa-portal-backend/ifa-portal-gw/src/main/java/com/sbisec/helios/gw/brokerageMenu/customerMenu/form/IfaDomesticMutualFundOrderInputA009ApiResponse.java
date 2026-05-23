package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * 国内投信注文入力A009リスポンス
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA009ApiResponse {
    
    /** 手数料率リスト(n). */
    private List<IfaDomesticMutualFundOrderInputCommRate> commRateList;
    
}

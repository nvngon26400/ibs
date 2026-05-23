package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 国内投信注文入力A009リスポンス
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA009ResponseDto {
    
    /** 手数料率リスト(n). */
    private List<IfaDomesticMutualFundOrderInputCommRate> commRateList;
    
}

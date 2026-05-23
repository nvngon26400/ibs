package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticA002bResponseDto {
    
    /** 信用建玉一覧リスト. */
    private List<IfaMarginPositionListDomesticDto_MarginPositionListDomestic> marginPositionListDomesticList;
    
}

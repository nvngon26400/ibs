package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticA004aRequestDto {
    
    /** 信用建玉一覧リスト.部店. */
    private List<IfaMarginPositionListDomesticDto_MarginPositionListDomestic> marginPositionListDomestic;
    
}

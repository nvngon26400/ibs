package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticA002bApiResponse {
    
    /** 信用建玉一覧リスト. */
    private List<IfaMarginPositionListDomesticA002ApiResponse_MarginPositionListDomestic> marginPositionListDomesticList;
    
}

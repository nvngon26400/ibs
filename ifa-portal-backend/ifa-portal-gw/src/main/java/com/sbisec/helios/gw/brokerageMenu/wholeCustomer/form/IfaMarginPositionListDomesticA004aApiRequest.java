package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticA004aApiRequest {
    
    /** 信用建玉一覧リスト. */
    @NotEmpty(message = "信用建玉一覧リスト")
    private List<IfaMarginPositionListDomesticA004aApiRequest_marginPositionListDomestic> marginPositionListDomestic = new ArrayList<IfaMarginPositionListDomesticA004aApiRequest_marginPositionListDomestic>();
    
}

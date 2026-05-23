package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignDto_MarginPositionListForeign;

import lombok.Data;

@Data
public class IfaMarginPositionListForeignA006aApiRequest {
    
    /** 信用建玉一覧リスト. */
    @NotEmpty(message = "信用建玉一覧リスト")
    private List<IfaMarginPositionListForeignDto_MarginPositionListForeign> marginPositionListForeignList
            = new ArrayList<IfaMarginPositionListForeignDto_MarginPositionListForeign>();
    
}

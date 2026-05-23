package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaMarginPositionListForeignA006aDtoRequest {
    
    /** 信用建玉情報List */
    private List<IfaMarginPositionListForeignDto_MarginPositionListForeign> marginPositionListForeignList;
}

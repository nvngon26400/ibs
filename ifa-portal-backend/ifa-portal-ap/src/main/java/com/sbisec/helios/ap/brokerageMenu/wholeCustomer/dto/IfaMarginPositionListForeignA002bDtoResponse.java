package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaMarginPositionListForeignA002bDtoResponse {
    
    /** 信用建玉情報 */
    private List<IfaMarginPositionListForeignDto_MarginPositionListForeign> marginPositionListForeignList;
    
}

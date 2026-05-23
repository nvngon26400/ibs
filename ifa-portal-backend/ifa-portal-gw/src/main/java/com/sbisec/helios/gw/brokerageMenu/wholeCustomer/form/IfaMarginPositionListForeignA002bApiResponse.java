package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignDto_MarginPositionListForeign;

import lombok.Data;

@Data
public class IfaMarginPositionListForeignA002bApiResponse {
    
    /** 信用建玉情報 */
    private List<IfaMarginPositionListForeignDto_MarginPositionListForeign> marginPositionListForeignList;
    
}

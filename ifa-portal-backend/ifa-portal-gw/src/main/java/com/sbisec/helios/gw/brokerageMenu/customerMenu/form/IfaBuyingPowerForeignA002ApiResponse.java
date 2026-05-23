package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

@Data
public class IfaBuyingPowerForeignA002ApiResponse {

    /** 国別情報リスト. */
    private List<IfaBuyingPowerForeignA002ApiResponseBycountry> bycountryList;
}

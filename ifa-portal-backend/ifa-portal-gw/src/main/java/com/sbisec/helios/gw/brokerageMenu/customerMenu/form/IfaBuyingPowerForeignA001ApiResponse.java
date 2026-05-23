package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

@Data
public class IfaBuyingPowerForeignA001ApiResponse {

    /** 国別情報リスト. */
    private List<IfaBuyingPowerForeignA001ApiResponseBycountry> bycountryList;

}

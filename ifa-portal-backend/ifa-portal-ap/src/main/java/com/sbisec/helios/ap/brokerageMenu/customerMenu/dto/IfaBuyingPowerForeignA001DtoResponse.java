package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaBuyingPowerForeignA001DtoResponse {

    /** 国別情報リスト. */
    private List<IfaBuyingPowerForeignA001DtoResponseBycountry> byCountryList;

}

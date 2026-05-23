package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaBuyingPowerForeignA002DtoResponse {

    /** 国別情報リスト. */
    private List<IfaBuyingPowerForeignA002DtoResponseBycountry> byCuntryList;

}

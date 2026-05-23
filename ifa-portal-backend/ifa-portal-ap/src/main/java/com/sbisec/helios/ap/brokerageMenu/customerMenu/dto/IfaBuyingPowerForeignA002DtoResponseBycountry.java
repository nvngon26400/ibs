package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaBuyingPowerForeignA002DtoResponseBycountry {

    /** 買付余力（総合口座）リスト. */
    private List<IfaBuyingPowerForeignA002DtoResponseBycountryBuyingPowerWholeAccount> buyingPowerWholeAccountList;

    /** 買付余力（ジュニア口座）リスト. */
    private List<IfaBuyingPowerForeignA002DtoResponseBycountryBuyingPowerJuniorAccount> buyingPowerJuniorAccountList;

}

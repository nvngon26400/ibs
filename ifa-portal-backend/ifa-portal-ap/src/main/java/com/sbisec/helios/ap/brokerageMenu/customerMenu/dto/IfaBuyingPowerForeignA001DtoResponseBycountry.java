package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaBuyingPowerForeignA001DtoResponseBycountry {

    /** 買付余力（総合口座）リスト. */
    private List<IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerWholeAccount> buyingPowerWholeAccountList;

    /** 買付余力（ジュニア口座）リスト. */
    private List<IfaBuyingPowerForeignA001DtoResponseBycountryBuyingPowerJuniorAccount> buyingPowerJuniorAccountList;

}

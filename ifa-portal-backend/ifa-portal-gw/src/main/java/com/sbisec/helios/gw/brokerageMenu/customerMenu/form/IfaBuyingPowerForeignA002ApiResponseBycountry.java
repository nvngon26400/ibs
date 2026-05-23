package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

@Data
public class IfaBuyingPowerForeignA002ApiResponseBycountry {

    /** 買付余力（総合口座）リスト. */
    private List<IfaBuyingPowerForeignA002ApiResponseBycountryBuyingPowerWholeAccount>  buyingPowerWholeAccountList;

    /** 買付余力（ジュニア口座）リスト. */
    private List<IfaBuyingPowerForeignA002ApiResponseBycountryBuyingPowerJuniorAccount> buyingPowerJuniorAccountList;

}

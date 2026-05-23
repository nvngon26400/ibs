package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 代用有価証券一覧 A001リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCollateralSecurityListA001ApiRequest {

    /** 代用種別. */
    @NotEmpty(message = "代用種別")
    private String collateralClass;

}

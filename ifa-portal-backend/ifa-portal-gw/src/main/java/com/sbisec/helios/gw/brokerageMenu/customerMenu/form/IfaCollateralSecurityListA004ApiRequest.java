package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 代用有価証券一覧 A004リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCollateralSecurityListA004ApiRequest {

    /** 表示基準日（受渡日）. */
    @NotEmpty(message = "表示基準日（受渡日）")
    private String displayBaseDate;

    /** 代用種別. */
    @NotEmpty(message = "代用種別")
    private String collateralClass;

}

package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 国内投信注文取消確認 A001 リクエストパラメータ
 *
 * @author SCSK
 *
 *     2023/11/24 新規作成
 */
@Data
public class IfaDomesticMutualFundOrderCancelConfirmA001ApiRequest {
    
    /** EC受注番号（半角英数字）. */
    @NotEmpty(message = "EC受注番号")
    @Size(min = 6, max = 6, message = "EC受注番号")
    private String ecOrderNo;
    
}

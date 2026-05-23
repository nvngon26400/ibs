package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 出金入力A003リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA003ApiRequest {

    /** 出金日 */
    private String payDate;

    /** 出金額 */
    private String payAmount;
}

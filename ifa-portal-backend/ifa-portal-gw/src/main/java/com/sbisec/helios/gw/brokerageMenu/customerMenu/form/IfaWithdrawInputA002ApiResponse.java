package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 出金入力A002レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA002ApiResponse {

    /** 出金可能金額 */
    private String acBalance;

}

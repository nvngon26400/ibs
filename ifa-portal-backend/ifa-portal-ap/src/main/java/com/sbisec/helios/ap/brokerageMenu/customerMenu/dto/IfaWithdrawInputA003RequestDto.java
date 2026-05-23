package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 出金入力A003リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA003RequestDto {

    /** 出金日 */
    private String payDate;

    /** 出金額 */
    private String payAmount;
}

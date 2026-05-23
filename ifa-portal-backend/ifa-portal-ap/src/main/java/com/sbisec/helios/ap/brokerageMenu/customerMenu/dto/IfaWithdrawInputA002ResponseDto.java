package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 出金入力A002レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA002ResponseDto {

    /** 出金可能金額 */
    private String acBalance;

}

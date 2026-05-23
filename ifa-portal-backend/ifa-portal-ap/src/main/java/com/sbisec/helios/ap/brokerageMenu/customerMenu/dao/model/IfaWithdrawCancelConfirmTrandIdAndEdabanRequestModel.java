package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 出金入力A002リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawCancelConfirmTrandIdAndEdabanRequestModel {

    /** 部店CD */
    private String butenCd;

    /** 口座番号 */
    private String accountNumber;

    /** 発注区分 */
    private String hacchuuKbn;

    /** EC入出金番号 */
    private String ecNyushukkinNo;
}

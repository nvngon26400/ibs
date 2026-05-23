package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 出金入力A001レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputSql001ResponseModel {

    /** 銀行コード */
    private String bankCode;

    /** 銀行名 */
    private String bankKanji;

    /** 店舗コード */
    private String bankBranchCode;

    /** 店舗名 */
    private String bankBranchKanji;

    /** 預金種目 */
    private String bankDepositClass;

    /** 振込口座番号 */
    private String bankAccount;

    /** 振込先名義人名 */
    private String bankAccountName;
}

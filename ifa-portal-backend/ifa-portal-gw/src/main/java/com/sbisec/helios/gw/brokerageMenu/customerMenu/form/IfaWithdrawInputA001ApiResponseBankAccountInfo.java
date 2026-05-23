package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 振込先銀行口座
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaWithdrawInputA001ApiResponseBankAccountInfo {

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

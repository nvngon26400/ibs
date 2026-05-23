package com.sbisec.helios.gw.common.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 顧客共通情報削除入力項目クラス。
 *
 * @author SCSK宮坂
 */
@Data
public class RemoveCustomerInfoForm {
	/** 部店コード */
	@NotEmpty(message = "部店コード")
	@Size(min = 3, max = 3, message = "部店コード")
	@Pattern(regexp = "[a-zA-Z0-9]*", message = "部店コード")
	private String butenCode;

	/** 口座番号 */
	@NotEmpty(message = "口座番号")
	@Size(max = 7, message = "口座番号")
	@Pattern(regexp="[0-9]*", message = "口座番号")
	private String accountNumber;
}

package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class IfaOtherFeeDetailA001ApiRequest {

	/** 仲介業者コード（数字）. */
	@NotEmpty(message = "仲介業者コード")
	@Pattern(regexp="0-9", message = "仲介業者コード")
	@Size(max = 4, message = "仲介業者コード")
	private String brokerCode;

	/** 対象年月. */
	@NotEmpty(message = "対象年月")
	private String targetDateYm;

}

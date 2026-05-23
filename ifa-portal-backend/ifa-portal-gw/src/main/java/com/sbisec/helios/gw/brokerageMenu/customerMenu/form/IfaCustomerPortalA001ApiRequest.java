package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class IfaCustomerPortalA001ApiRequest {

	/** 部店コード（半角英数字）. */
	@NotEmpty(message = "部店コード")
	@Size(min = 3, max = 3, message = "部店コード")
	private String butenCode;

	/** 口座番号（数字）. */
	@NotEmpty(message = "口座番号")
	@Pattern(regexp="0-9", message = "口座番号")
	@Size(max = 7, message = "口座番号")
	private String accountNumber;

}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaCustomerPortalA001RequestDto {

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

}

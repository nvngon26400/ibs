package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaCustomerPortalA011ResponseDto {

	/** メモ(IFA専用)内容. */
	private String ifaMemoContent;

	/** メモ(IFA専用)更新日時. */
	private String ifaMemoUpdateDateTime;

}

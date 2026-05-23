package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

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
public class IfaComplianceReportViewStatusLookupManagerA005ApiRequest {

	/** ユーザー_ID. */
	@NotEmpty(message = "ユーザー_ID")
	private String userId;

	/** 仲介業者コード（数字）. */
	@NotEmpty(message = "仲介業者コード")
	@Pattern(regexp="0-9", message = "仲介業者コード")
	@Size(max = 4, message = "仲介業者コード")
	private String brokerCode;

	/** 営業員コード（半角英数字）. */
	@NotEmpty(message = "営業員コード")
	@Size(min = 4, max = 4, message = "営業員コード")
	private String empCode;

	/** 閲覧不要設定（全角半角）. */
	@NotEmpty(message = "閲覧不要設定")
	@Size(max = 10, message = "閲覧不要設定")
	private String viewExcludeSetting;
	
}

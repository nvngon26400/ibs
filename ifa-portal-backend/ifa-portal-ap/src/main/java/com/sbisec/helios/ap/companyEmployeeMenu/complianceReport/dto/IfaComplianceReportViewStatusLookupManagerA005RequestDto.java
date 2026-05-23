package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA005RequestDto {

	/** ユーザー_ID. */
	private String userId;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 営業員コード（半角英数字）. */
	private String empCode;
	
	/** 閲覧不要設定（全角半角）. */
	private String viewExcludeSetting;

}

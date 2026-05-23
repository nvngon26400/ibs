package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupInternalAdminA002ResponseDtoComplianceReport {

	/** 確認日時. */
	private String confirmDateTime;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者支店名. */
	private String brokerBranchName;

	/** 仲介業者担当者コード（数字）. */
	private String employeeId;

	/** 仲介業者担当者名（全角半角）. */
	private String employeeName;

	/** タイトル（全角半角）. */
	private String title;

	/** コード名称. */
	private String codeName;

	/** 閲覧要否フラグ（半角英数字）. */
	private String corBrowseFlag;

}

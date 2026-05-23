package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA002ApiResponseComplianceReportList {

	/** LECTURE_ID（数字）. */
	private String lectureId;

	/** ユーザーID（全角半角）. */
	private String userId;

	/** 確認日時. */
	private String confirmDateTime;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者支店名. */
	private String branchNameOfBranch;

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

	/** 閲覧報告フラグ（半角英数字）. */
	private String viewReportFlag;

}

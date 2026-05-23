package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerSql002ResponseModel extends ModelBase {

	/** 総件数. */
	private String wholeCount;

	/** LECTURE_ID（数字）. */
	private String lectureId;

	/** タイトル（全角半角）. */
	private String title;
	
	/** 仲介業者担当者コード（数字）. */
	private String employeeId;

	/** ユーザー名. */
	private String employeeName;

	/** 仲介業者支店名. */
	private String branchNameOfBranch;

	/** ユーザーID（全角半角）. */
	private String userId;

	/** 確認日時(YYYY/MM/DD). */
	private String confirmDateTime;

	/** 閲覧報告者（全角半角）. */
	private String viewReportUser;

	/** 閲覧要否フラグ（半角英数字）. */
	private String corBrowseFlag;

	/** コード名称. */
	private String codeName;

	/** 仲介業者コード（数字）. */
	private String brokerCode;
	
}

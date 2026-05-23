package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerSql006RequestModel {

	/** LECTURE_ID（数字）. */
	private String lectureId;

	/** ユーザーID（全角半角）. */
	private String userId;
    
	/** ユーザ共通情報.ユーザーID（全角半角）. */
	private String userAccountUserId;
}

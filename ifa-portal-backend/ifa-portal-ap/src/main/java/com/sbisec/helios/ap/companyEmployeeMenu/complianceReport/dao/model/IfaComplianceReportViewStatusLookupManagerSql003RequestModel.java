package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerSql003RequestModel {

	/** ユーザーID（全角半角）. */
	private String userId;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者担当者コード（数字）. */
	private String employeeId;

	/** 閲覧要否フラグ（半角英数字）. */
	private String corBrowseFlag;

	/** 登録年月日. */
	private String registerDate;

	/** 登録日時. */
	private String registerDayTime;

	/** 登録者（全角半角）. */
	private String createBy;

	/** 更新日時. */
	private String updateTime;

	/** 更新者. */
	private String updateBy;

}

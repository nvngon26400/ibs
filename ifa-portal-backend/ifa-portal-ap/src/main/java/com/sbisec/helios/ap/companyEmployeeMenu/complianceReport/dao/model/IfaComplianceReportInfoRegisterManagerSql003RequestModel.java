package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportInfoRegisterManagerSql003RequestModel {

	/** 機能ID（全角半角）. */
	private String functionId;

	/** カテゴリID（数字）. */
	private String t9nInfoCat;

}

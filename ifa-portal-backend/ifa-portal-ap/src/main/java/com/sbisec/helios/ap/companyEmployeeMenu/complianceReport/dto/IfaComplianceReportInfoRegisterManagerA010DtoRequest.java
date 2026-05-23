package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportInfoRegisterManagerA010DtoRequest {

	/** 機能ID（全角半角）. */
	private String functionId;

	/** カテゴリID（数字）. */
	private String t9nInfoCat;

	/** LECTURE_ID（数字）. */
	private String lectureId;

	/** ファイル名1. */
	private String fileName1;

	/** ファイル名2. */
	private String fileName2;

	/** ファイル名3. */
	private String fileName3;

	/** コンテンツファイル名. */
	private String contentsFileName;

}

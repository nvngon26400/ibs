package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportInfoRegisterManagerA010DtoResponseComplianceReport{

	/** LECTURE_ID（数字）. */
	private String lectureId;

	/** 通信年月. */
	private String yearMonth;

	/** タイトル（全角半角）. */
	private String title;

	/** 概要（全角半角）. */
	private String overview;

	/** ファイル名1. */
	private String fileName1;

	/** ファイル名2. */
	private String fileName2;

	/** ファイル名3. */
	private String fileName3;

	/** コンテンツ. */
	private String contents;

	/** 公開フラグ（数字）. */
	private String disclosureFlag;

	/** コンテンツファイル名. */
	private String contentsFileName;

	/** 最終更新日. */
	private String lastUpdateDate;

}

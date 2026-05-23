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
public class IfaComplianceReportInfoRegisterManagerA010ApiRequest {

	/** 機能ID（全角半角）. */
	@NotEmpty(message = "機能ID")
	@Size(max = 20, message = "機能ID")
	private String functionId;

	/** カテゴリID（数字）. */
	@NotEmpty(message = "カテゴリID")
	@Pattern(regexp="0-9", message = "カテゴリID")
	@Size(max = 38, message = "カテゴリID")
	private String t9nInfoCat;

	/** LECTURE_ID（数字）. */
	@NotEmpty(message = "LECTURE_ID")
	@Pattern(regexp="0-9", message = "LECTURE_ID")
	@Size(max = 38, message = "LECTURE_ID")
	private String lectureId;

	/** ファイル名1. */
	@NotEmpty(message = "ファイル名1")
	private String fileName1;

	/** ファイル名2. */
	@NotEmpty(message = "ファイル名2")
	private String fileName2;

	/** ファイル名3. */
	@NotEmpty(message = "ファイル名3")
	private String fileName3;

	/** コンテンツファイル名. */
	@NotEmpty(message = "コンテンツファイル名")
	private String contentsFileName;

}

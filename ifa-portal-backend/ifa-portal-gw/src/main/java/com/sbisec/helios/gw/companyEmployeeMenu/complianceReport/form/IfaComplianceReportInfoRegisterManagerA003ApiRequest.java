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
public class IfaComplianceReportInfoRegisterManagerA003ApiRequest {

	/** 公開フラグ（数字）. */
	@NotEmpty(message = "公開フラグ")
	@Pattern(regexp="0-9", message = "公開フラグ")
	@Size(min = 1, max = 1, message = "公開フラグ")
	private String disclosureFlag;

	/** LECTURE_ID（数字）. */
	@NotEmpty(message = "LECTURE_ID")
	@Pattern(regexp="0-9", message = "LECTURE_ID")
	@Size(max = 38, message = "LECTURE_ID")
	private String lectureId;

}

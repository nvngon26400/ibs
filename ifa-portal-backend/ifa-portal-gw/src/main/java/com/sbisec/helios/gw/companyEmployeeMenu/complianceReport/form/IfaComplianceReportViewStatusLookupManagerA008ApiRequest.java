package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA008ApiRequest {

    /** ユーザーID（全角半角）. */
	@NotEmpty(message = "ユーザーID")
    private String userId;
    
    /** LECTURE_ID（数字）. */
	@NotEmpty(message = "LECTURE_ID")
    private String lectureId;
    
    /** 閲覧報告フラグ（半角英数字）. */
	@NotEmpty(message = "閲覧報告フラグ")
    private String viewReportFlag;

}

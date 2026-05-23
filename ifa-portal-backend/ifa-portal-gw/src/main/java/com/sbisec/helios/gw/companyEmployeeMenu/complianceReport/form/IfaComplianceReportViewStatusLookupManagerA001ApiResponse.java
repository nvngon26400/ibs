package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA001ResponseDtoComplianceReport;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA001ApiResponse {

	/** コンプライアンス通信リスト. */
	private List<IfaComplianceReportViewStatusLookupManagerA001ResponseDtoComplianceReport> complianceReportList;

}

package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReport;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA007ApiResponse {

	/** コンプライアンス通信一覧リスト. */
	private List<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList> complianceReportListList;

	/** コンプライアンス通信リスト. */
	private List<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReport> complianceReportList;

}

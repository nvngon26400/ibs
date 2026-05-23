package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA002ApiResponse {

	/** コンプライアンス通信一覧リスト. */
	private List<IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList> complianceReportListList;

}

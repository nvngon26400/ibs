package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA007ResponseDto {

	/** コンプライアンス通信一覧リスト. */
	private List<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReportList> complianceReportListList;

	/** コンプライアンス通信リスト. */
	private List<IfaComplianceReportViewStatusLookupManagerA007ResponseDtoComplianceReport> complianceReportList;

}

package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form;

import java.util.List;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupInternalAdminA004ApiResponse {

	/** コンプライアンス通信リスト. */
	private List<IfaComplianceReportViewStatusLookupInternalAdminA004ApiResponseComplianceReport> complianceReportList;
	
	/** コンプライアンス通信リスト. */
    private List<IfaComplianceReportViewStatusLookupInternalAdminA001ApiResponseComplianceReport> complianceReportTitleList;
}

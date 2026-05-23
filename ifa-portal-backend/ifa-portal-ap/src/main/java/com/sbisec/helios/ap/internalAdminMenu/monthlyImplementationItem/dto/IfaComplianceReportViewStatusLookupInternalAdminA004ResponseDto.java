package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDto {

	/** コンプライアンス通信リスト. */
	private List<IfaComplianceReportViewStatusLookupInternalAdminA004ResponseDtoComplianceReport> complianceReportList;

	/** コンプライアンス通信リスト. */
    private List<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport> complianceReportTitleList;
}

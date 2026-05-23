package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportInfoRegisterManagerA001DtoResponse {

	/** コンプライアンス通信リスト. */
	private List<IfaComplianceReportInfoRegisterManagerA001DtoResponseComplianceReport> complianceReportList;

}

package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA002ResponseDto {
    
    /** コンプライアンス通信一覧リスト. */
    private List<IfaComplianceReportViewStatusLookupManagerA002ResponseDtoComplianceReportList> complianceReportListList;
    
}

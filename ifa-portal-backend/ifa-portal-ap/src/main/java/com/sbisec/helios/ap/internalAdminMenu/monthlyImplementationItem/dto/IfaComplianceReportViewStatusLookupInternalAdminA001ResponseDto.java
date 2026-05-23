package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDto {

    /** コンプライアンス通信リスト. */
    private List<IfaComplianceReportViewStatusLookupInternalAdminA001ResponseDtoComplianceReport> complianceReportList;

}

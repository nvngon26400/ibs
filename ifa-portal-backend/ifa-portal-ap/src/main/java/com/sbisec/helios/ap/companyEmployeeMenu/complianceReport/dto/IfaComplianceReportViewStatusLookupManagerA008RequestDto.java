package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA008RequestDto {
    
    /** ユーザーID（全角半角）. */
    private String userId;
    
    /** LECTURE_ID（数字）. */
    private String lectureId;
    
    /** 閲覧報告フラグ（半角英数字）. */
    private String viewReportFlag;
    
}

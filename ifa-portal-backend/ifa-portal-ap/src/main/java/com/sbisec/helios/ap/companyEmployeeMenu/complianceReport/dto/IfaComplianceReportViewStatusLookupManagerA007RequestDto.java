package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerA007RequestDto {
    
    /** 閲覧状況（全角半角）. */
    private String viewStatus;
    
    /** 閲覧要否（全角半角）. */
    private String viewNecessity;
    
    /** タイトル（当月）（全角半角）. */
    private String titleThisMonth;
    
    /** タイトル（過去月）（全角半角）. */
    private String titleLastMonth;
    
}

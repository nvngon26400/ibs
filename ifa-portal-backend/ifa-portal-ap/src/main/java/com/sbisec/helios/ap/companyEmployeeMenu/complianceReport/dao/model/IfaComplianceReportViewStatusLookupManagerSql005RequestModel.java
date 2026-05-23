package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupManagerSql005RequestModel {

    /** 仲介業者名（全角半角）. */
    private String brokerName;

    /** 営業員名（全角半角）. */
    private String brokerChargeName;

    /** タイトル（全角半角）. */
    private String title;

    /** 閲覧状況（全角半角）. */
    private String viewStatus;

    /** 閲覧要否（全角半角）. */
    private String viewNecessity;

    /** SBI証券支店コード（数字）. */
    private String branchId;
    
    /** 権限コード. */
    private String privId;
    
    

}

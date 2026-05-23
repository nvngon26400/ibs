package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupInternalAdminA004RequestDto {

    /** 閲覧状況（全角半角）. */
    private String viewStatus;

    /** 閲覧対象（全角半角）. */
    private String viewTarget;

    /** タイトル（当月）（全角半角）. */
    private String titleThisMonth;

    /** タイトル（過去月）（全角半角）. */
    private String titleLastMonth;

}

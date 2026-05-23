package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupInternalAdminSql002RequestModel {

    /** 仲介業者名（全角半角）. */
    private String brokerName;

    /** 営業員名（全角半角）. */
    private String brokerChargeName;

    /** タイトル（全角半角）. */
    private String title;

    /** 閲覧状況（全角半角）. */
    private String viewStatus;

    /** 閲覧対象（全角半角）. */
    private String viewTarget;

    /** ユーザ共通情報.仲介業者コード. */
    private String brokerCode;

    /** ユーザ共通情報.仲介業者支店コード. */
    private String brokerBranchCode;

    /** FCT030.仲介業者営業員リスト. */
    private List brokerChargeList;
    
    /** タイトル（当月）. */
    private String titleThisMonth;
    
    /** タイトル（過去月）. */
    private String titleLastMonth;
    
    /** 権限コード*/
    private String privId;

    /** 最大件数 */
    private int maxRowNum;
}

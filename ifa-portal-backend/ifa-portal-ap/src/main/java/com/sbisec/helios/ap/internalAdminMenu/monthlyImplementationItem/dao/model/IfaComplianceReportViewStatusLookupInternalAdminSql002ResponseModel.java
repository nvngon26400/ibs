package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupInternalAdminSql002ResponseModel extends ModelBase{

    /** 総件数. */
    private String totalCount;

    /** タイトル（全角半角）. */
    private String title;

    /** 仲介業者担当者コード（数字）. */
    private String employeeId;

    /** ユーザーID. */
    private String userId;

    /** 仲介業者支店名. */
    private String brokerBranchName;

    /** 確認日時(YYYY/MM/DD). */
    private String confirmDateTime;

    /** コード名称. */
    private String codeName;

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 閲覧要否フラグ（半角英数字）. */
    private String corBrowseFlag;

}

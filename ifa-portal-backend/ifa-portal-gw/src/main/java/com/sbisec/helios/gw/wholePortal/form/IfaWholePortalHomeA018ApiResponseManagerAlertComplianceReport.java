package com.sbisec.helios.gw.wholePortal.form;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA018ApiResponseManagerAlertComplianceReport {

    /** 当月未回答フラグ */
    private String thisMonthUnreplyFlag;

    /** タイトル（当月）（全角半角） */
    private String titleThisMonth;

    /** 過去月未回答フラグ */
    private String lastMonthUnreplyFlag;

    /** タイトル（過去月）（全角半角） */
    private String titleLastMonth;

}

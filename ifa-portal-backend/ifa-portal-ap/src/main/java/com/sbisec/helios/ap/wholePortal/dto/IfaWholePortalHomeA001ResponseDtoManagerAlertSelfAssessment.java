package com.sbisec.helios.ap.wholePortal.dto;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA001ResponseDtoManagerAlertSelfAssessment {

    /** 登録年月（当月） */
    private String registerYearMonthThisMonth;

    /** 登録年月（過去月） */
    private String registerYearMonthLastMonth;

}

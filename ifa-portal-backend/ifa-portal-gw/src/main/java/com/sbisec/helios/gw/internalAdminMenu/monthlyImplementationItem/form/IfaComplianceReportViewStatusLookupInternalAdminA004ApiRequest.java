package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class IfaComplianceReportViewStatusLookupInternalAdminA004ApiRequest {

    /** 閲覧状況（全角半角）. */
    @NotEmpty(message = "閲覧状況")
    @Size(max = 20, message = "閲覧状況")
    private String viewStatus;

    /** 閲覧対象（全角半角）. */
    @NotEmpty(message = "閲覧対象")
    @Size(max = 20, message = "閲覧対象")
    private String viewTarget;

    /** タイトル（当月）（全角半角）. */
    @NotEmpty(message = "タイトル（当月）")
    @Size(max = 255, message = "タイトル（当月）")
    private String titleThisMonth;

    /** タイトル（過去月）（全角半角）. */
    @NotEmpty(message = "タイトル（過去月）")
    @Size(max = 255, message = "タイトル（過去月）")
    private String titleLastMonth;

}
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
public class IfaComplianceReportViewStatusLookupInternalAdminA002ApiRequest {

    /** 仲介業者名（全角半角）. */
    @NotEmpty(message = "仲介業者名")
    @Size(max = 80, message = "仲介業者名")
    private String brokerName;

    /** 営業員名（全角半角）. */
    @NotEmpty(message = "営業員名")
    @Size(max = 80, message = "営業員名")
    private String brokerChargeName;

    /** タイトル（全角半角）. */
    @NotEmpty(message = "タイトル")
    @Size(max = 255, message = "タイトル")
    private String title;

    /** 閲覧状況（全角半角）. */
    @NotEmpty(message = "閲覧状況")
    @Size(max = 20, message = "閲覧状況")
    private String viewStatus;

    /** 閲覧対象（全角半角）. */
    @NotEmpty(message = "閲覧対象")
    @Size(max = 20, message = "閲覧対象")
    private String viewTarget;

}
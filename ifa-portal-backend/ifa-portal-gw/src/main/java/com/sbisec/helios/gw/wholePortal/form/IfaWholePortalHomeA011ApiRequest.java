package com.sbisec.helios.gw.wholePortal.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA011ApiRequest {

    /** 期間指定（From） */
    @NotEmpty(message = "期間指定（From）")
    private String periodDesignationFrom;

}

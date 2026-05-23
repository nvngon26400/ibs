package com.sbisec.helios.gw.wholePortal.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA021ApiRequest {

    /** カテゴリIDリスト */
    @NotEmpty(message = "カテゴリIDリスト")
    private List<IfaWholePortalHomeA021ApiRequestCategoryid> categoryIdList;

}

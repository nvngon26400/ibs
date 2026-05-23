package com.sbisec.helios.ap.wholePortal.dto;

import java.util.List;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA021RequestDto {

    /** カテゴリIDリスト */
    private List<IfaWholePortalHomeA021RequestDtoCategoryid> categoryIdList;

}

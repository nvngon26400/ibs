package com.sbisec.helios.ap.wholePortal.dto;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA021RequestDtoCategoryid {

    /** カテゴリIDリスト.カテゴリID */
    private String categoryId;

    /** カテゴリIDリスト.必須フラグ */
    private String requiredFlag;

    /** カテゴリIDリスト.カテゴリ */
    private String category;

}

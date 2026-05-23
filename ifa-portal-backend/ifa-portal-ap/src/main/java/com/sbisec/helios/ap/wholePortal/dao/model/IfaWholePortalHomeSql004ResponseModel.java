package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeSql004ResponseModel {

    /** カテゴリID（数字） */
    private String categoryId;

    /** カテゴリ名（全角半角） */
    private String categoryName;

    /** 必須フラグ */
    private String requiredFlag;

}

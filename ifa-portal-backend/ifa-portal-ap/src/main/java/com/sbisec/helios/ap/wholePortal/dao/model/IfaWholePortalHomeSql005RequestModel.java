package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeSql005RequestModel {

    /** カテゴリIDリスト（大カテゴリ） */
    private String categoryIdCat1;

    /** ユーザ共通情報.ユーザーID */
    private String userId;

}

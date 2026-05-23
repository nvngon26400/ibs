package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeSql001RequestModel {

    /** ユーザ共通情報.ユーザーID */
    private String userId;

    /** ユーザ共通情報.権限コード */
    private String privId;
}

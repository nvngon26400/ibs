package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeSql002RequestModel {

    /** ユーザ共通情報.権限コード */
    private String privId;

    /** ユーザ共通情報.ユーザID */
    private String userId;

}

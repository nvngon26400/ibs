package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeSql002ResponseModel {

    /** ユーザーID（全角半角） */
    private String userId;

    /** 通信年月 */
    private String yearMonth;

    /** タイトル（全角半角） */
    private String title;

}

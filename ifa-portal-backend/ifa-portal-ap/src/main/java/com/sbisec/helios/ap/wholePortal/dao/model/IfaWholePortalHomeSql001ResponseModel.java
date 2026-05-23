package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeSql001ResponseModel {

    /** アラート分類ID（数字） */
    private String alertId;

    /** アラート通知日 */
    private String alertDate;

}

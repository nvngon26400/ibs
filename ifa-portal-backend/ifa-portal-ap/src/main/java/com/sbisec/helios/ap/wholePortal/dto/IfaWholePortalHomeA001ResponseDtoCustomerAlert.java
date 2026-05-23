package com.sbisec.helios.ap.wholePortal.dto;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaWholePortalHomeA001ResponseDtoCustomerAlert {

    /** アラート分類ID（数字） */
    private String alertId;

    /** アラート通知日 */
    private String alertDate;

}

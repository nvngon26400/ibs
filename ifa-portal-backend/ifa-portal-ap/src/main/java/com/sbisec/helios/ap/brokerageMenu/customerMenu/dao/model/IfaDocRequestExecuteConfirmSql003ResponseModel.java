package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql003レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestExecuteConfirmSql003ResponseModel {

    /** 配信予定時刻 */
    private String deliveryScheduleTime;

    /** 翌日フラグ */
    private String nextDayFlg;
}

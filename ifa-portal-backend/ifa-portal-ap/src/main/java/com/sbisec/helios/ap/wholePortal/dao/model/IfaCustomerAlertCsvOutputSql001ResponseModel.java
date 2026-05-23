package com.sbisec.helios.ap.wholePortal.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
@Data
public class IfaCustomerAlertCsvOutputSql001ResponseModel {
    
    /** アラート分類ID（数字）. */
    private int alertId;

    /** 年月日. */
    private String dateYmd;

}

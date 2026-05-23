package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;


import lombok.Data;

/**
* 画面ID：SUB0202_0703-01
* 画面名：受発信状況照会
*
* @author SBI大連 董
*2025/03/20 新規作成
*/

@Data
public class IfaSendReceiveStatusLookupSql002RequestModel {

    /** 顧客ID **/
    private String customerId;
    /** 書類コード **/
    private String paperCd;
    /** キーワード **/
    private String keyword;
    /** 最大取得件数 */
    private int maxRowNum;
}

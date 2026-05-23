package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql006ResponseModel {

    /** 登録日時 */
    private String tourokuNichiji;

    /** 金額 */
    private String kingaku;

    /** 出金予定日 */
    private String shukkinYoteibi;

    /** 計上日 */
    private String keijoubi;

    /** ユーザ名 */
    private String userNm;
}

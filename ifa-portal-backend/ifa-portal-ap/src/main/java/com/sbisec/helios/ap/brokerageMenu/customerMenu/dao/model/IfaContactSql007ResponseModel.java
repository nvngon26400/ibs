package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql007ResponseModel {

    /** 登録日時 */
    private String tourokuNichiji;

    /** 金額 */
    private String kingaku;

    /** 出金予定日 */
    private String shukkinYoteibi;

    /** 計上日 */
    private String keijoubi;

    /** EC入出金番号 */
    private String ecNyushukkinNo;

    /** ユーザ名 */
    private String userNm;
}

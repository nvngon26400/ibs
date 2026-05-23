package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql012ResponseModel {

    /** 作成日時 */
    private String createTime;

    /** 作成日付 */
    private String createTimeYmd;

    /** 作成者 */
    private String createUser;

    /** ユーザー名 */
    private String userNm;
}

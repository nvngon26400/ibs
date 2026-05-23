package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql019RequestModel {

    /** 顧客コード */
    private String kokyakuId;
    /** CCSAPI接触履歴の戻り値(内容+記録日時のJSON) */
    private String ccsApiRtnVal;
}
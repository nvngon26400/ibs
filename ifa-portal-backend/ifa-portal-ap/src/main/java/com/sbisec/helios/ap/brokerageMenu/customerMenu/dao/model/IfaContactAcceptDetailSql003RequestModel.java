package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import java.util.List;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql003RequestModel {

    /** 顧客コード */
    private String customerId;

    /** 記録日時 */
    private String recordDate;

    /** 作成者 */
    private String createUser;

    /** 取引種別リスト */
    private List<String> tradeTypeList;
}

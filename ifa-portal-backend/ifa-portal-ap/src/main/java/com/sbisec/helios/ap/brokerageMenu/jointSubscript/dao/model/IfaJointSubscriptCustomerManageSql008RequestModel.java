package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 共通募集顧客テーブルに、指定の条件で口座が存在するか判定する用リクエストモデル
 * 2024/12/04 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageSql008RequestModel {

    /** 部店(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

}

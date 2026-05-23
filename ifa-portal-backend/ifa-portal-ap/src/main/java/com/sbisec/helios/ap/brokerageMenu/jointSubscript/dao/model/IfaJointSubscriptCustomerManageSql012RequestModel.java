package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;
/**
 * IFA顧客属性テーブルに、指定の部店、口座番号が契約先仲介業者に紐づいているか確認する用リクエストモデル
 * 2025/05/16 新規作成
 *
 * @author 大連 赫栄升
 */
@Data
public class IfaJointSubscriptCustomerManageSql012RequestModel {
    /** 部店(半角英数字) **/
    private String butenCode;
    /** 口座番号(数字) **/
    private String accountNumber;
}

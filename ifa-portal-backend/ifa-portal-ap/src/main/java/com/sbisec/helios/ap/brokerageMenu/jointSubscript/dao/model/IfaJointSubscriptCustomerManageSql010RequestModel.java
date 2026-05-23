package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 新しい共同募集顧客情報を共同募集顧客仲介業者情報に新規登録する用リクエストモデル
 * 2025/05/16 新規作成
 *
 * @author 大連 赫栄升
 */
@Data
public class IfaJointSubscriptCustomerManageSql010RequestModel {
    /** 顧客ID(半角英数字) **/
    private String customerId;
    /** 部店(半角英数字) **/
    private String butenCode;
    /** 口座番号(数字) **/
    private String accountNumber;
    /** 仲介業者コード(半角英数字) **/
    private String brokerCode;
    /** 更新理由(数字) **/
    private String reasonCode = "0"; // デフォルト値は「0：未設定」
}

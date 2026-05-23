package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 指定の部店、口座番号が存在するか確認する用レスポンスモデル
 * 2025/12/03 新規作成
 *
 * @author 大連 赫栄升
 */
@Data
public class IfaJointSubscriptCustomerManageSql011ResponseModel {
    /** 顧客ID */
    private String CustomerId;
}

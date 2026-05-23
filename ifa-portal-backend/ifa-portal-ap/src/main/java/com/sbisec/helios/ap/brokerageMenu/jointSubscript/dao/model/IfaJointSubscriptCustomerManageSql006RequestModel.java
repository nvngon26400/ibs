package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 仲介業者コードと名を取得する用リクエストモデル
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageSql006RequestModel {

    /** 部店コード(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

}

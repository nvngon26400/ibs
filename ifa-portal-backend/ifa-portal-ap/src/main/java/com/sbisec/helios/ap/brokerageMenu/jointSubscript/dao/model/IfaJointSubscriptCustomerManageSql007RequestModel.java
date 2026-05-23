package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 仲介業者支店名を取得する用リクエストモデル
 * 2024/12/04 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageSql007RequestModel {

    /** 共募支店コード(数字) */
    private String jointBranchCode;

    /** 仲介業者ID マネプラ（0509）のみ */
    private String broker0509;
}

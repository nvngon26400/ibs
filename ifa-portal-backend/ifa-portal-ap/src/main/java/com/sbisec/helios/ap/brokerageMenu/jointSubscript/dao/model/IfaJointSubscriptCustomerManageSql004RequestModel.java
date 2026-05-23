package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 指定共同募集顧客情報の編集履歴を更新用リクエストモデル
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageSql004RequestModel {

    /** 同意日(YYYYMMDD) */
    private String startDate;

    /** 部店(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

    /** 編集履歴(数字) */
    private String editNumber;

}

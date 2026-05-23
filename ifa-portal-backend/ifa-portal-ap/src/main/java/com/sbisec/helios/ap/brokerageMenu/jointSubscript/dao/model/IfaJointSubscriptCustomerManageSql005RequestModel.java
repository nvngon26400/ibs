package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 共同募集顧客マスタの新規登録用リクエストモデル
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageSql005RequestModel {

    /** 同意日(YYYYMMDD) */
    private String startDate;

    /** 終了日(YYYYMMDD) */
    private String endDate;

    /** 部店(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

    /** 共募支店コード(数字) */
    private String jointBranchCode;

    /** 支払率(半角数字と小数点) */
    private String jointRewardRate;

    /** 契約締結日(YYYYMMDD) */
    private String contractDate;

    /** 手続状況(数字) */
    private String editStatus;

    /** 端末ID */
    private String sysMachineId;

    /** トリガーID */
    private String sysTrigger;

    /** ログインユーザID */
    private String loginId;

    /** ログインユーザ名 */
    private String loginName;

}

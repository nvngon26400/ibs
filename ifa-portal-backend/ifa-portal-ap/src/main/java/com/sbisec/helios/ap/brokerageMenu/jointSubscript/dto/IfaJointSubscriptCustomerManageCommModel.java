package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import lombok.Data;

/**
 * サービス用モデル
 * 共同募集 顧客管理のDB処理用
 * 
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageCommModel {

    /** 仲介業者コード(数字) */
    private String brokerCode;

    /** 部店(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

    /** 共募支店コード(数字) */
    private String jointBranchCode;

    /** 契約締結日(YYYYMMDD) */
    private String contractDate;

    /** 同意日(YYYYMMDD) */
    private String startDate;

    /** 終了日(YYYYMMDD) */
    private String endDate;

    /** 支払率(半角数字と小数点) */
    private String jointRewardRate;

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

package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;

import lombok.Data;

/**
 * リクエストパラメータ
 * 画面ID：SUB0206_01-02
 * 画面名：共同募集 顧客新規登録
 * アクション：A002 登録確認(新規登録入力)
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerNewRegisterA002ApiRequest {

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

}

package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;

import lombok.Data;

/**
 * レスポンスパラメータ
 * 画面ID：SUB0206_01-03
 * 画面名：共同募集 顧客修正
 * アクション：A004 修正(修正確認)
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerCorrectA004ApiResponse {

    /** 仲介業者コード(数字) */
    private String brokerCode;

    /** 仲介業者名(全角半角) */
    private String brokerName;

    /** 部店(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

    /** 共募支店コード(数字) */
    private String jointBranchCode;

    /** 共募支店名(全角半角) */
    private String jointBranchName;

    /** 契約締結日(YYYYMMDD) */
    private String contractDate;

    /** 同意日(YYYYMMDD) */
    private String startDate;

    /** 終了日(YYYYMMDD) */
    private String endDate;

    /** 支払率(半角数字と小数点) */
    private String jointRewardRate;

}

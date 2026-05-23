package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import lombok.Data;

/**
 * サービス用レスポンスモデル
 * 画面ID：SUB0206_01-02
 * 画面名：共同募集 顧客新規登録
 * アクション：A002 登録確認(新規登録入力)
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerNewRegisterA002ResponseDto {

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

package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import lombok.Data;

/**
 * サービス用レスポンスモデル
 * 画面ID：SUB0206_01-01
 * 画面名：共同募集 顧客管理
 * アクション：A002 検索(表示)
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageA002ResponseDto {

    /** 総件数 */
    private String totalCount;

    /** 仲介業者コード(数字) */
    private String brokerCode;

    /** 仲介業者名(全角半角) */
    private String brokerName;

    /** 部店(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

    /** コース名(全角半角) */
    private String customerAttributeName;

    /** 顧客名(全角半角) */
    private String nameKanji;

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

    /** 手続状況(数字) */
    private String editStatus;

    /** 手続状況名(全角半角) */
    private String editStatusName;

    /** 営業員コード(半角英数字) */
    private String brokerChargeCode;

    /** 営業名(全角半角) */
    private String brokerChargeName;

    /** 自動解約理由(全角半角) */
    private  String autoCancellationReason;

}

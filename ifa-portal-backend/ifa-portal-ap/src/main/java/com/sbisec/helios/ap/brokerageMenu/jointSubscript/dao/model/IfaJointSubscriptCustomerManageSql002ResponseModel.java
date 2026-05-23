package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 顧客情報詳細情報取得用レスポンスモデル
 * 2024/12/04 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageSql002ResponseModel {

    /** 部店(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

    /** 仲介業者コード(数字) */
    private String brokerCode;

    /** 仲介業者名(全角半角) */
    private String brokerName;

    /** 仲介業者営業員コード(半角英数字) */
    private String brokerChargeCode;

    /** 仲介業者営業員名(全角半角) */
    private String brokerChargeName;

    /** 顧客名(漢字)(全角半角) */
    private String nameKanji;

    /** 顧客名(カナ)(全角半角) */
    private String nameKana;

    /** 住所(漢字)(全角半角) */
    private String addressKanji1;

    /** 電話番号(全角半角) */
    private String phoneNumber;

    /** 自宅電話不可フラグ(半角英数字) */
    private String phoneFlg;

    /** 性別(全角) */
    private String sex;

    /** 年齢(数字) */
    private String age;

    /** 口座開設年月日(YYYY/MM/DD) */
    private String openAcctDate;

    /** コンプラランク(半角英数字) */
    private String tcCompRank;

}

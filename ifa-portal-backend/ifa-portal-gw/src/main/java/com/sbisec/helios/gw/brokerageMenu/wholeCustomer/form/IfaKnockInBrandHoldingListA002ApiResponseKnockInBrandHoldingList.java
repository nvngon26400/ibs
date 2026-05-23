package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

/**
 * 画面ID：SUB020301_03-02
 * 画面名：ノックイン銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/12 新規作成
 */

@Data
public class IfaKnockInBrandHoldingListA002ApiResponseKnockInBrandHoldingList {

    /** STAR債券コード（全角半角）. */
    private String sbmBondCode;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 契約締結前交付書面コード（半角英数字）. */
    private String customerAttribute;

    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;

    /** 顧客名_姓名(カナ). */
    private String customerNameKana;

    /** 扱者コード（半角英数字）. */
    private String dealerNumber;

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者名（全角半角）. */
    private String brokerName;

    /** 仲介業支店コード. */
    private String brokerageBranchCode;

    /** 仲介業者支店名. */
    private String brokerBranchName;

    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;

    /** 仲介業者担当者名（全角半角）. */
    private String brokerChargeName;

    /** 銘柄名（全角半角）. */
    private String brandName;

    /** 保有数量（数値(整数)）. */
    private String holdingQuantity;

    /** PDF通知URL（英数字記号B(+-_./@*#%!"$&()=~^\?>,|`[]{}:;<')）. */
    private String pdfNoticeUrl;

    /** 判定銘柄名. */
    private String judgmentBrandName;

    /** 時価（数値(小数)）. */
    private String price;

    /** ノックイン水準価格（数値(小数)）. */
    private String knockInLevelPrice;

    /** ノックイン発生日. */
    private String knockinDate;

}

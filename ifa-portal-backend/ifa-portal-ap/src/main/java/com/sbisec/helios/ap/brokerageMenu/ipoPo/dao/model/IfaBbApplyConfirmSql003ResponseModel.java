package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyConfirmSql003ResponseModel {

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 投資方針区分. */
    private String investmentPolicyType;

    /** 本人職業区分. */
    private String professionType;

    /** 投資経験年数(株式現物)（数字）. */
    private String stockSpotInvestExperienceYears;

    /** 勤務先名(漢字). */
    private String officeName;

    /** 金融資産区分（半角英数字）. */
    private String financialAssetsType;

    /** 法人区分（半角英数字）. */
    private String corporationType;

    /** 顧客名_姓(漢字). */
    private String customerLastNameKanji;

    /** 顧客名_名(漢字). */
    private String customerFirstNameKanji;

    /** 顧客名_姓(カナ). */
    private String customerLastNameKana;

    /** 顧客名_名(カナ). */
    private String customerFirstNameKana;

    /** 扱者コード（半角英数字）. */
    private String dealerNumber;

    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;

    /** 口座開設年月日. */
    private String openAcctDate;

    /** コンプラランク（半角英数字）. */
    private String complianceRank;

    /** 電子交付承諾区分. */
    private String electronicDocConsentType;

    /** 承諾日付. */
    private String consentDate;

}

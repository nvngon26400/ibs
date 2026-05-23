package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

@Data
public class IfaTodayTradeListA002bApiRequest {
    
    /** CSVダウンロードMAX件数. */
    private String csvDownloadMaxCount;
    
    /** 総件数 */
    private String totalCount;

    /** 顧客口座情報リスト.部店コード. */
    private String butenCode;

    /** 顧客口座情報リスト.口座番号. */
    private String accountNumber;

    /** 顧客口座情報リスト.顧客名_姓名(漢字). */
    private String customerNameKanji;

    /** 顧客口座情報リスト.顧客名_姓名(カナ). */
    private String customerNameKana;

    /** 顧客口座情報リスト.コース. */
    private String course;

    /** 顧客口座情報リスト.契約締結前交付書面コード. */
    private String customerAttribute;

    /** 顧客口座情報リスト.プロファイル値. */
    private String profileValue;

    /** 顧客口座情報リスト.営業員コード. */
    private String empCode;

    /** 顧客口座情報リスト.営業員名. */
    private String brokerChargeName;

    /** 顧客口座情報リスト.仲介業者コード. */
    private String brokerCode;

    /** 顧客口座情報リスト.仲介業者名. */
    private String brokerName;

    /** 顧客口座情報リスト.支店コード. */
    private String branchCode;

    /** 顧客口座情報リスト.支店名. */
    private String branchName;
    
    /** 信用属性. */
    private String marginProfile;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
}

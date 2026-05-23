package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaTodayTradeListA002aDtoResponse {

	/** CSVダウンロードMAX件数. */
	private String csvDownloadMaxCount;

	/** 総件数 */
    private String totalCount;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;

    /** 顧客名_姓名(カナ). */
    private String customerNameKana;

    /** コース. */
    private String course;

    /** 契約締結前交付書面コード（全角半角）. */
    private String customerAttribute;

    /** プロファイル値. */
    private String profileValue;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 営業員名（全角半角）. */
    private String brokerChargeName;

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者名（全角半角）. */
    private String brokerName;

    /** 支店コード（数字）. */
    private String branchCode;

    /** 支店名（全角半角）. */
    private String branchName;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 信用属性. */
    private String marginProfile;

}

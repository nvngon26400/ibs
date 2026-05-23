package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaSellUnableDetailA001DtoResponseSellUnableDetail {

    /** 商品区分. */
    private String securityType;

    /** コード　銘柄名. */
    private String brandCodeBrandName;

    /** 預り区分. */
    private String depositType;

    /** 預り区分(ファンドタイプ). */
    private String fundDepositType;

    /** 代用適格区分. */
    private String collateralEligibleType;

    /** 制限数（数字）. */
    private String restrictionCount;

    /** 理由. */
    private String reason;

    /** 売却制限期間開始. */
    private String limitedPeriodStart;

    /** 売却制限期間終了. */
    private String limitedPeriodFinish;

    /** 代用制限期間開始. */
    private String substituteStart;

    /** 代用制限期間終了. */
    private String substituteFinish;

    /** 登録日. */
    private String registeredDate;

}

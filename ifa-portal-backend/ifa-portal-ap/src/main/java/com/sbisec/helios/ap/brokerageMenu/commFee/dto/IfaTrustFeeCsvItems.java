package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaTrustFeeCsvItems extends ModelBase {

    /** シリアルバージョンID */
    private static final long serialVersionUID = 1L;

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者名 */
    private String brokerName;

    /** 営業員コード */
    private String empCode;

    /** 営業員名 */
    private String brokerChargeName;

    /** 部店  */
    private String buten;

    /** 口座番号（数字）  */
    private String accountNumber;

    /** 扱者コード */
    private String dealerNumber;

    /** 取引コース */
    private String course;

    /** 顧客名（漢字） */
    private String customerNameKanji;

    /** 顧客名（カナ） */
    private String customerNameKana;

    /** 証券種別  */
    private String securityClass;

    /** 保有日  */
    private String holdingcDate;

    /** 保有月  */
    private String baseMonth;

    /** 銘柄コード */
    private String brandCode;

    /** 銘柄名 */
    private String brandName;

    /** 数量 */
    private String quantity;

    /** 参考価額 */
    private String referencePrice;

    /** 基準価額 */
    private String price;

    /** 評価額 */
    private String valuation;

    /** 信託報酬率 */
    private String trustFeeRate;

    /** 通貨  */
    private String currency;

    /** 為替レート */
    private String fxRate;

    /** 信託報酬額 */
    private String trustFeeAmount;

    /** 支店コード */
    private String branchCode;

    /** 支店名 */
    private String branchName;


}

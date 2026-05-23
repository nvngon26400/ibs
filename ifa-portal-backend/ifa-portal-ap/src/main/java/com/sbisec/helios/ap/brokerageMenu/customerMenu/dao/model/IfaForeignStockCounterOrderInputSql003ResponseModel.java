package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_1
 * 画面名：外国株式店頭注文入力
 * 2024/05/07 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderInputSql003ResponseModel {

    /** 仲介業者名（全角半角） */
    private String brokerName;

    /** 最大売枠（株数） */
    private String maxSellLimitQuantity;

    /** 最大買枠（株数） */
    private String maxBuyLimitQuantity;

    /** 銘柄名（全角半角） */
    private String brandName;

    /** 外国証券情報版番 */
    private String foreignSecurityInfoDate;

    /** 英文開示銘柄適用日 */
    private String englishDisclosureBrandEffectiveDate;

    /** 売価格 */
    private String sellPrice;

    /** 買価格 */
    private String buyPrice;

    /** ETFフラグ */
    private String etfFlag;

    /** 市場コード（全角半角） */
    private String marketCode;

}

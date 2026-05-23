package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_1
 * 画面名：外国株式店頭注文入力
 * 2024/05/07 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderInputA001ApiResponse {

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

    /** 特定買付余力（外貨） */
    private String specificBuyingPowerForeign;

    /** 一般買付余力（外貨） */
    private String generalBuyingPowerForeign;

    /** 特定注文可能数量 */
    private String specificOrderAbleQuantity;

    /** 一般注文可能数量 */
    private String generalOrderAbleQuantity;

    /** 注文入力の使い方URL */
    private String orderInputUsageUrl;

    /** チャートURL */
    private String chartUrl;

    /** 株価参照URL */
    private String stockPriceReferenceUrl;

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 取引区分 */
    private String tradeClassification;

    /** 返却用ティッカー選択フラグ（全角半角） */
    private String returnTickerSelectFlag;

    /** 返却用銘柄コード */
    private String forReturnBrandCode;

    /** 返却用銘柄名 */
    private String forReturnBrandName;

    /** チェック用上限約定金額 */
    private String forCheckUpperLimitContractAmount;

    /** チェック用上限注文数量 */
    private String forCheckUpperLimitOrderAmount;

}

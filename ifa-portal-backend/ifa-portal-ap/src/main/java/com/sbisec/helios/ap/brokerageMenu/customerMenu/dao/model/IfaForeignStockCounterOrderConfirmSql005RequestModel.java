package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderConfirmSql005RequestModel {

    /** 仲介業者コード */
    private String brokerCode;

    /** 銘柄コード */
    private String brandCode;

    /** 部店コード */
    private String butenCode;

    /** 口座番号 */
    private String accountNumber;

    /** 数量 */
    private String quantity;

    /** 決済区分 */
    private String settlementType;

    /** 預り区分 */
    private String depositType;

    /** 勧誘区分 */
    private String kanyuKbn;

    /** 受注方法 */
    private String orderMethod;

    /** 売買区分 */
    private String tradeKbn;

    /** コンプラチェック.チェックボックス文言 */
    private String chkBoxLabel;

    /** 重要事項の説明 */
    private String importantMatter;

    /** 乗換え勧誘(ETF) */
    private String switchingSolicitationEtf;

    /** 外国証券情報.版番 */
    private String foreignStockYmd;

    /** 外国証券情報.交付日 */
    private String documentDeliveryDate;

    /** 外国証券情報.交付方法 */
    private String issuedMethod;

    /** 英文開示銘柄 */
    private String engPubBrand;

    /** 説明日 */
    private String engPubYmd;

    /** 取引価格 */
    private String tradePrice;

    /** 約定金額 */
    private String contractAmount;

    /** 摘要(任意) */
    private String summaryAny;

    /** 仲介業者営業員コード */
    private String brokerChargeCode;

    /** 市場コード */
    private String marketCode;

    /** ユーザID */
    private String userId;

}

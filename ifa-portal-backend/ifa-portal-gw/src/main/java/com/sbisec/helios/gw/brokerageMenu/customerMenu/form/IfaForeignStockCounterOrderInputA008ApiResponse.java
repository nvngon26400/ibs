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
public class IfaForeignStockCounterOrderInputA008ApiResponse {

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 銘柄名（全角半角） */
    private String brandName;

    /** 上限数量 */
    private String upperLimitQuantity;

    /** 取引価格（数値(小数)） */
    private String tradePrice;

    /** チャートURL */
    private String chartUrl;

    /** 株価参照URL */
    private String stockPriceReferenceUrl;

    /** 取引種別（全角半角） */
    private String tradeCd;

    /** 数量（数値(整数)） */
    private String quantity;

    /** 約定金額（数値(整数)） */
    private String contractAmount;

    /** 預り区分（全角半角） */
    private String depositType;

    /** 決済区分（全角半角） */
    private String settlementType;

    /** 勧誘区分（全角半角） */
    private String kanyuKbn;

    /** 受注方法 */
    private String orderMethod;

    /** 余力確認 */
    private String powerConfirm;

    /** 重要事項の説明 */
    private String importantMatter;

    /** 版番 */
    private String foreignStockYmd;

    /** 交付日 */
    private String documentDeliveryDate;

    /** 交付方法 */
    private String issuedMethod;

    /** 乗換え勧誘(ETF) */
    private String switchingSolicitationEtf;

    /** 英文開示銘柄 */
    private String engPubBrand;

    /** 説明日 */
    private String engPubYmd;

    /** 摘要(任意)（全角半角） */
    private String summaryAny;

    /** 市場コード（全角半角） */
    private String marketCode;

    /** 売買区分（全角半角） */
    private String tradeKbn;

    /** 判定結果 */
    private String judgmentResult;

    /** メッセージ */
    private String msg;

    /** チェックボックス文言（半角英数字） */
    private String chkBoxLabel;

    /** 注意情報アラート（全角半角） */
    private String noticeInfoAlert;

    /** お知らせアラート（全角半角） */
    private String noticeAlert;

    /** 約定金額アラート */
    private String tradeAmountAlert;

    /** 海外ETFアラート */
    private String overseasEtfAlert;

}

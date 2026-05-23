package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderConfirmA001ResponseDto {

    /** 銘柄コード（半角英数字） */
    private String brandCode;

    /** 銘柄名（全角半角） */
    private String brandName;

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

    /** 外国証券情報.版番 */
    private String foreignStockYmd;

    /** 外国証券情報.交付日 */
    private String documentDeliveryDate;

    /** 外国証券情報.交付方法 */
    private String issuedMethod;

    /** 乗換え勧誘(ETF) */
    private String switchingSolicitationEtf;

    /** 英文開示銘柄 */
    private String engPubBrand;

    /** 説明日 */
    private String engPubYmd;

    /** 摘要(任意)（全角半角） */
    private String summaryAny;

    /** アラート内容確認.コンプラランクチェック確認 */
    private String complianceRankCheckConfirm;

    /** アラート内容確認.注意情報アラート確認 */
    private String noticeInfoAlertConfirm;

    /** アラート内容確認.お知らせアラート確認 */
    private String noticeAlertConfirm;

    /** アラート内容確認.約定金額アラート確認 */
    private String tradeAmountAlertConfirm;

    /** アラート内容確認.海外ETFアラート確認 */
    private String overseasEtfAlertConfirm;

    /** コンプラチェック.メッセージ */
    private String msg;

    /** コンプラチェック.チェックボックス文言（半角英数字） */
    private String chkBoxLabel;

    /** 注意情報アラートメッセージ（全角半角） */
    private String noticeInfoAlert;

    /** お知らせアラートメッセージ（全角半角） */
    private String noticeAlert;

    /** 約定金額アラートメッセージ（全角半角） */
    private String tradeAmountAlert;

    /** 海外ETFアラートメッセージ（全角半角） */
    private String overseasEtfAlert;

}

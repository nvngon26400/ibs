package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaForeignStockCounterOrderConfirmA001ApiRequest {

    /** 銘柄コード（半角英数字） */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;

    /** 銘柄名（全角半角） */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;

    /** 取引価格（数値(小数)） */
    @Digits(integer = 9, fraction = 3, message = "取引価格")
    @NotEmpty(message = "取引価格")
    @Size(max = 15, message = "取引価格")
    private String tradePrice;

    /** 取引種別（全角半角） */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;

    /** 数量（数値(整数)） */
    @Digits(integer = 15, fraction = 0, message = "数量")
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String quantity;

    /** 約定金額（数値(整数)） */
    @Digits(integer = 15, fraction = 0, message = "約定金額")
    @NotEmpty(message = "約定金額")
    @Size(max = 19, message = "約定金額")
    private String contractAmount;

    /** 預り区分（全角半角） */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;

    /** 決済区分（全角半角） */
    @NotEmpty(message = "決済区分")
    @Size(max = 2, message = "決済区分")
    private String settlementType;

    /** 勧誘区分（全角半角） */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;

    /** 受注方法 */
    @NotEmpty(message = "受注方法")
    private String orderMethod;

    /** 余力確認 */
    @NotEmpty(message = "余力確認")
    private String powerConfirm;

    /** 重要事項の説明 */
    @NotEmpty(message = "重要事項の説明")
    private String importantMatter;

    /** 外国証券情報.版番 */
    @NotEmpty(message = "外国証券情報.版番")
    private String foreignStockYmd;

    /** 外国証券情報.交付日 */
    @NotEmpty(message = "外国証券情報.交付日")
    private String documentDeliveryDate;

    /** 外国証券情報.交付方法 */
    @NotEmpty(message = "外国証券情報.交付方法")
    private String issuedMethod;

    /** 乗換え勧誘(ETF) */
    @NotEmpty(message = "乗換え勧誘(ETF)")
    private String switchingSolicitationEtf;

    /** 英文開示銘柄 */
    @NotEmpty(message = "英文開示銘柄")
    private String engPubBrand;

    /** 説明日 */
    @DateTimeFormat(pattern = "yy年MM月dd日")
    @JsonFormat(pattern = "yy年MM月dd日")
    @NotEmpty(message = "説明日")
    @Size(min = 11, max = 11, message = "説明日")
    private String engPubYmd;

    /** 摘要(任意)（全角半角） */
    @NotEmpty(message = "摘要(任意)")
    @Size(max = 100, message = "摘要(任意)")
    private String summaryAny;

    /** 市場コード（全角半角） */
    @NotEmpty(message = "市場コード")
    @Size(max = 12, message = "市場コード")
    private String marketCode;

    /** 売買区分（全角半角） */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;

    /** アラート内容確認.コンプラランクチェック確認 */
    @NotEmpty(message = "アラート内容確認.コンプラランクチェック確認")
    private String complianceRankCheckConfirm;

    /** アラート内容確認.注意情報アラート確認 */
    @NotEmpty(message = "アラート内容確認.注意情報アラート確認")
    private String noticeInfoAlertConfirm;

    /** アラート内容確認.お知らせアラート確認 */
    @NotEmpty(message = "アラート内容確認.お知らせアラート確認")
    private String noticeAlertConfirm;

    /** アラート内容確認.約定金額アラート確認 */
    @NotEmpty(message = "アラート内容確認.約定金額アラート確認")
    private String tradeAmountAlertConfirm;

    /** アラート内容確認.海外ETFアラート確認 */
    @NotEmpty(message = "アラート内容確認.海外ETFアラート確認")
    private String overseasEtfAlertConfirm;

    /** コンプラチェック.メッセージ */
    @NotEmpty(message = "コンプラチェック.メッセージ")
    private String msg;

    /** コンプラチェック.チェックボックス文言 */
    @NotEmpty(message = "コンプラチェック.チェックボックス文言")
    private String chkBoxLabel;

    /** 注意情報アラートメッセージ（全角半角） */
    @NotEmpty(message = "注意情報アラートメッセージ")
    @Size(max = 50, message = "注意情報アラートメッセージ")
    private String noticeInfoAlert;

    /** お知らせアラートメッセージ（全角半角） */
    @NotEmpty(message = "お知らせアラートメッセージ")
    @Size(max = 50, message = "お知らせアラートメッセージ")
    private String noticeAlert;

    /** 約定金額アラートメッセージ（全角半角） */
    @NotEmpty(message = "約定金額アラートメッセージ")
    @Size(max = 50, message = "約定金額アラートメッセージ")
    private String tradeAmountAlert;

    /** 海外ETFアラートメッセージ（全角半角） */
    @NotEmpty(message = "海外ETFアラートメッセージ")
    @Size(max = 50, message = "海外ETFアラートメッセージ")
    private String overseasEtfAlert;

}

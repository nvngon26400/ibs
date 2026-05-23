package com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

@Data
public class IfaJointMarketTradeSearchSql001ResponseModel {

    /** 総件数. */
    private int totalRow;

    /** 対象年月日 */
    private String sumDateYmd;

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者名 */
    private String brokerName;

    /** 部店. */
    private String buten;

    /** 口座番号 */
    private String accountNumber;

    /** コースコード */
    private String customerAttribute;

    /** 取引コース */
    private String courseName;

    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;

    /** 顧客名_姓名(カナ). */
    private String customerNameKana;

    /** 支店コード（数字）. */
    private String branchCode;

    /** 支店名 */
    private String branchName;

    /** 証券種別. */
    private String securityClass;

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 銘柄名（全角半角）. */
    private String brandName;

    /** 商品ランク（半角英数字）. */
    private String productRank;

    /** 取引種別コード */
    private String tradeCd;

    /** 取引種別（全角半角）. */
    private String tradeTypeName;

    /** 約定日 */
    private String tradeDate;

    /** 受渡日 */
    private String settlementDate;

    /** 単価（全角半角）. */
    private String price;

    /** 数量（数値(整数)）. */
    private String quantity;

    /** 約定金額（円貨）. */
    private String contractAmountJpyAmount;

    /** 手数料（円貨）（数値(小数)）. */
    private String yenFee;

    /** 受渡金額（円貨）（数値(小数)）. */
    private String yenDeliveryAmount;

    /** 通貨. */
    private String currency;

    /** 為替レート（数値(小数)）. */
    private String fxRate;

    /** 約定金額（外貨）（数値(小数)）. */
    private String contractAmountForeign;

    /** 受渡金額（外貨）（数値(小数)）. */
    private String foreignDeliveryAmount;

    /** 共募報酬率 */
    private String jointRate;

    /** 支払額 */
    private String jointRewardPrice;

    /** 営業員コード */
    private String empCode;

    /** 営業員名 */
    private String brokerChargeName;

    /** 債券 媒介/非媒介. */
    private String bondIntermediary;

    /** 米国株 店頭/委託. */
    private String usStockStoreEntrust;

    /** 預り区分. */
    private String depositName;
}

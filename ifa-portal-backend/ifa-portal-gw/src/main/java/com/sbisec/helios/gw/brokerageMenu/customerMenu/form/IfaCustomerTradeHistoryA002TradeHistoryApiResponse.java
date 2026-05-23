package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaCustomerTradeHistoryA002TradeHistoryApiResponse {

    /** 約定日 */
    private String tradeDate;

    /** 受渡日 */
    private String deliveryDate;

    /** 商品区分 */
    private String securityType;

    /** 銘柄コード */
    private String brandCode;

    /** 銘柄名 */
    private String brandName;

    /** 内訳取引 */
    private String detailsTrade;

    /** 内訳区分 */
    private String detailsType;

    /** 預り/税 */
    private String depositTax;

    /** 取引市場/理由 */
    private String tradeMarketReason;

    /** 単価 */
    private String price;

    /** 取得単価 */
    private String openPrice;

    /** 取得日 */
    private String acquireDate;

    /** 通貨 */
    private String currency;

    /** 為替 */
    private String fx;

    /** 数量 */
    private String quantity;

    /** 手数料 */
    private String fee;

    /** 消費税 */
    private String consumptionTax;

    /** 経過利子 */
    private String accruedInterest;

    /** 実現損益 */
    private String realProfitAndLoss;

    /** 譲渡益税/源泉税 */
    private String capitalGainTax;

    /** 精算金額 */
    private String accurateAmount;

    /** 取消区分 */
    private String cancelFlg;

}

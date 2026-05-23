package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaDomesticStockOrderCorrectConfirmSql002RequestModel {

    /** APIエラー判定 */
    private boolean isApiError;

    /** 受注日 */
    private String acceptDate;

    /** 受注時刻 */
    private String acceptTime;

    /** 種別 */
    private String shubetu;

    /** エラーコード */
    private String code;

    /** エラーメッセージ */
    private String message;

    /** 与信チェック用時価 */
    private String estimatePrice;

    /** 約定金額（概算） */
    private String amount;

    /** 手数料（概算） */
    private String commission;

    /** 消費税（概算） */
    private String consumptionTax;

    /** 譲渡益税（概算） */
    private String capitalGainTax;

    /** 精算金額（概算） */
    private String netAmount;

    /** 諸経費 */
    private String cost;

    /** 約定予定日 */
    private String tradeDate;

    /** 受渡予定日 */
    private String settlementDate;

    /** 受付有効期限 */
    private String acceptLimit;

    /** DONE 受付有効期限 */
    private String doneAcceptLimit;

    /** 手数料区分（採用） */
    private String comIdR;

    /** 売却可能数量 */
    private String acPosition;

    /** 注文後の売却可能数量 */
    private String acPositionAfter;

    /** 買付可能金額 */
    private String acBalance;

    /** 注文後の買付可能金額 */
    private String acBalanceAfter;

    /** 注文入力市場 */
    private String orderedMarket;

    /** 取引不足額 */
    private String tradeDeficitAmount;

    /** ISA買付可能枠 */
    private String isaBuyLimit;

    /** ジュニアNISA振替金額 */
    private String jrnisaTransferAmount;

    /** SOR連携区分 */
    private String sorLinkKbn;

    /** 決済可能数量 */
    private String unclosedQuantity;

    /** 注文後の決済可能数量 */
    private String unclosedQuantityAfter;

    /** 建玉余力 */
    private String marginCapability;

    /** 注文後の建玉余力 */
    private String marginCapabilityAfter;

    /** 維持率 */
    private String actualGrntRate;

    /** 注文後維持率 */
    private String actualGrntRateAfter;

    /** 適用金利 */
    private String appInterestRates;

    /** 適用貸株料 */
    private String appLendingStock;

    /** プレミアム料 */
    private String premium;

    /** Ｔポイント */
    private String tpoint;

    /** 利用後のＴポイント */
    private String tpointAfter;

    /** 更新日時 */
    private String updateTime;

    /** 更新者 */
    private String updateUser;

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** IFA注文サブ番号 */
    private String ifaOrderSubNo;

}

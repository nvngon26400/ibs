package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 信用返済注文取消確認SQL001サブクエリレスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderCancelConfirmSql001SubResponseModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客ID（数字）. */
    private String customerId;
    
    /** 特定口座区分（全角半角）. */
    private String specificKbn;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCd;
    
    /** 市場（全角）. */
    private String market;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 取引種別（全角半角）. */
    private String tradeKbn;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 有効期限（全角半角）. */
    private String limit;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 弁済期限（全角半角）. */
    private String paymentLimit;
    
    /** 返済方法. */
    private String repaymentMethodType;
    
    /** 返済順序. */
    private String requestType;
    
    /** 注文種別（全角半角）. */
    private String orderSyubetsu;
    
    /** 注文種別（一覧）. */
    private String orderSyubetsuList;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String jutyuKbn;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    private String checkInsider;
    
    /** 確認項目.SOR確認（全角半角）. */
    private String checkSor;
    
    /** アラート内容確認.コンプラチェックワーニング確認（全角半角）. */
    private String checkCompWrnAlert;
    
    /** 資金性格区分（全角半角）. */
    private String shikinSeikakuKbn;
    
    /** ユーザーＩＤ（全角半角）. */
    private String userId;
    
    /** 取消ユーザーID（全角半角）. */
    private String cancelUserId;
    
    /** 手数料区分（全角半角）. */
    private String tesuuryouKbn;
    
    /** 訂正区分（全角半角）. */
    private String alterFlg;
    
    /** 自動注文種別. */
    private String autoOrderKind;
    
    /** RBE注文種別（全角半角）. */
    private String rbeOrderKind;
    
    /** 指成区分（全角半角）. */
    private String sasinariKbn;
    
    /** 指値（数値(小数)）. */
    private String price;
    
    /** トリガ発動ゾーン（全角半角）. */
    private String triggerZone;
    
    /** トリガ値段（数値(小数)）. */
    private String triggerPrice;
    
    /** OCO指成区分（全角半角）. */
    private String ocoSasinariKbn;
    
    /** OCO指値（数値(小数)）. */
    private String ocoSashine;
    
    /** DONE指成区分（全角半角）. */
    private String doneSasinariKbn;
    
    /** DONE指値（数値(小数)）. */
    private String doneSashine;
    
    /** DONERBE注文種別（全角半角）. */
    private String doneRbeOrderKind;
    
    /** DONEトリガ発動ゾーン（全角半角）. */
    private String doneTriggerZone;
    
    /** DONEトリガ値段（数値(小数)）. */
    private String doneTriggerNedan;
    
    /** DONEOCO指成区分（全角半角）. */
    private String doneOcoSasinariKbn;
    
    /** DONEOCO指値（数値(小数)）. */
    private String doneOcoSashine;
    
    /** DONE有効期限（全角半角）. */
    private String doneLimit;
    
    /** 譲渡益税区分（全角半角）. */
    private String joZeiKbn;
    
    /** EC受注番号訂正時. */
    private String alterOrderNum;
    
    /** RBE注文ステータス. */
    private String rbeOrderStatus;
    
    /** 商品区分（全角半角）. */
    private String orderType;
    
    /** EC受注番号（半角英数字）. */
    private String orderNum;
    
    /** 受注日. */
    private String acceptDate;
    
    /** 受注時刻. */
    private String acceptTime;
    
    /** 種別（全角半角）. */
    private String shubetu;
    
    /** エラーコード（半角英数字）. */
    private String code;
    
    /** エラーメッセージ（全角半角）. */
    private String message;
    
    /** 与信チェック用時価（数値(小数)）. */
    private String estimatePrice;
    
    /** 約定金額（概算）（数値(整数)）. */
    private String amount;
    
    /** 手数料（概算）（数値(整数)）. */
    private String commission;
    
    /** 消費税（概算）（数値(整数)）. */
    private String consumptionTax;
    
    /** 譲渡益税（概算）（数値(整数)）. */
    private String capitalGainTax;
    
    /** 精算金額（概算）（数値(整数)）. */
    private String netAmount;
    
    /** 諸経費（数値(整数)）. */
    private String cost;
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受付有効期限（全角半角）. */
    private String acceptLimit;
    
    /** DONE 受付有効期限（全角半角）. */
    private String doneAcceptLimit;
    
    /** 手数料区分（採用）（全角半角）. */
    private String comIdR;
    
    /** 売却可能数量（数値(整数)）. */
    private String acPosition;
    
    /** 注文後の売却可能数量（数値(整数)）. */
    private String acPositionAfter;
    
    /** 買付可能金額（数値(整数)）. */
    private String acBalance;
    
    /** 注文後の買付可能金額（数値(整数)）. */
    private String acBalanceAfter;
    
    /** 注文入力市場（全角半角）. */
    private String orderedMarket;
    
    /** 取引不足額（数値(整数)）. */
    private String tradeDeficitAmount;
    
    /** ISA買付可能枠（数値(整数)）. */
    private String isaBuyLimit;
    
    /** ジュニアNISA振替金額（数値(整数)）. */
    private String jrnisaTransferAmount;
    
    /** SOR連携区分（全角半角）. */
    private String sorLinkKbn;
    
    /** 決済可能数量（数値(整数)）. */
    private String unclosedQuantity;
    
    /** 注文後の決済可能数量（数値(整数)）. */
    private String unclosedQuantityAfter;
    
    /** 建玉余力（数値(整数)）. */
    private String marginCapability;
    
    /** 注文後の建玉余力（数値(整数)）. */
    private String marginCapabilityAfter;
    
    /** 維持率. */
    private String domesticMarginActualGrntRate;
    
    /** 注文後維持率（数値(整数)）. */
    private String actualGrntRateAfter;
    
    /** 適用金利（数値(小数)）. */
    private String applicableInterestRate;
    
    /** 適用貸株料（数値(小数)）. */
    private String applicableStockLendingFees;
    
    /** プレミアム料（数値(整数)）. */
    private String premium;
    
    /** Ｔポイント（数値(整数)）. */
    private String tpoint;
    
    /** 利用後のＴポイント（数値(整数)）. */
    private String tpointAfter;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String intermediaryEmpCd;
    
    /** 作成日時. */
    private String createTime;
    
    /** 作成者. */
    private String createUser;
    
    /** 更新日時. */
    private String updateTime;
    
    /** 更新者. */
    private String updateUser;
    
    /** CCS送付日. */
    private String ccsSendDate;
}

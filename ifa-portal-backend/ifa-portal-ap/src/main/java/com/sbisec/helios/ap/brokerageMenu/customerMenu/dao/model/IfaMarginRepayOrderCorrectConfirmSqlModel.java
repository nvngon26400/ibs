package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 信用返済注文訂正確認SQL001リクエスト.
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaMarginRepayOrderCorrectConfirmSqlModel {
    
    /** IFA注文番号. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号. */
    private String ifaOrderSubNo;
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** 顧客ID. */
    private String customerId;
    
    /** 特定口座区分. */
    private String specificKbn;
    
    /** 銘柄コード. */
    private String brandCd;
    
    /** 市場（全角）. */
    private String market;
    
    /** 注文状況. */
    private String orderStatus;
    
    /** 取引種別. */
    private String tradeKbn;
    
    /** 数量. */
    private String quantity;
    
    /** 有効期限. */
    private String limit;
    
    /** 預り区分. */
    private String azukariKbn;
    
    /** 弁済期限. */
    private String paymentLimit;
    
    /** 返済方法. */
    private String repaymentMethodType;
    
    /** 返済順序. */
    private String requestType;
    
    /** 注文種別. */
    private String orderSyubetsu;
    
    /** 注文種別（一覧）. */
    private String orderSyubetsuList;
    
    /** 勧誘区分. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String jutyuKbn;
    
    /** 確認項目.インサイダー確認. */
    private String checkInsider;
    
    /** 確認項目.SOR確認. */
    private String checkSor;
    
    /** アラート内容確認.コンプラチェックワーニング確認. */
    private String checkCompWrnAlert;
    
    /** 資金性格区分. */
    private String shikinSeikakuKbn;
    
    /** ユーザーＩＤ. */
    private String userId;
    
    /** 取消ユーザーID. */
    private String cancelUserId;
    
    /** 手数料区分. */
    private String tesuuryouKbn;
    
    /** 訂正区分. */
    private String alterFlg;
    
    /** 自動注文種別. */
    private String autoOrderKind;
    
    /** RBE注文種別. */
    private String rbeOrderKind;
    
    /** 指成区分. */
    private String sasinariKbn;
    
    /** 指値. */
    private String price;
    
    /** トリガ発動ゾーン. */
    private String triggerZone;
    
    /** トリガ値段. */
    private String triggerPrice;
    
    /** OCO指成区分. */
    private String ocoSasinariKbn;
    
    /** OCO指値. */
    private String ocoSashine;
    
    /** DONE指成区分. */
    private String doneSasinariKbn;
    
    /** DONE指値. */
    private String doneSashine;
    
    /** DONERBE注文種別. */
    private String doneRbeOrderKind;
    
    /** DONEトリガ発動ゾーン. */
    private String doneTriggerZone;
    
    /** DONEトリガ値段. */
    private String doneTriggerNedan;
    
    /** DONEOCO指成区分. */
    private String doneOcoSasinariKbn;
    
    /** DONEOCO指値. */
    private String doneOcoSashine;
    
    /** DONE有効期限. */
    private String doneLimit;
    
    /** 譲渡益税区分. */
    private String joZeiKbn;
    
    /** EC受注番号訂正時. */
    private String alterOrderNum;
    
    /** RBE注文ステータス. */
    private String rbeOrderStatus;
    
    /** 商品区分. */
    private String orderType;
    
    /** EC受注番号. */
    private String orderNum;
    
    /** 受注日. */
    private String acceptDate;
    
    /** 受注時刻. */
    private String acceptTime;
    
    /** 種別. */
    private String shubetu;
    
    /** エラーコード. */
    private String code;
    
    /** エラーメッセージ. */
    private String message;
    
    /** 与信チェック用時価. */
    private String estimatePrice;
    
    /** 約定金額（概算）. */
    private String amount;
    
    /** 手数料（概算）. */
    private String commission;
    
    /** 消費税（概算）. */
    private String consumptionTax;
    
    /** 譲渡益税（概算）. */
    private String capitalGainTax;
    
    /** 精算金額（概算）. */
    private String netAmount;
    
    /** 諸経費. */
    private String cost;
    
    /** 約定予定日. */
    private String tradeDate;
    
    /** 受渡予定日. */
    private String settlementDate;
    
    /** 受付有効期限. */
    private String acceptLimit;
    
    /** DONE 受付有効期限. */
    private String doneAcceptLimit;
    
    /** 手数料区分（採用）. */
    private String comIdR;
    
    /** 売却可能数量. */
    private String acPosition;
    
    /** 注文後の売却可能数量. */
    private String acPositionAfter;
    
    /** 買付可能金額. */
    private String acBalance;
    
    /** 注文後の買付可能金額. */
    private String acBalanceAfter;
    
    /** 注文入力市場. */
    private String orderedMarket;
    
    /** 取引不足額. */
    private String tradeDeficitAmount;
    
    /** ISA買付可能枠. */
    private String isaBuyLimit;
    
    /** ジュニアNISA振替金額. */
    private String jrnisaTransferAmount;
    
    /** SOR連携区分. */
    private String sorLinkKbn;
    
    /** 決済可能数量. */
    private String unclosedQuantity;
    
    /** 注文後の決済可能数量. */
    private String unclosedQuantityAfter;
    
    /** 建玉余力. */
    private String marginCapability;
    
    /** 注文後の建玉余力. */
    private String marginCapabilityAfter;
    
    /** 維持率. */
    private String actualGrntRate;
    
    /** 注文後維持率. */
    private String actualGrntRateAfter;
    
    /** 適用金利. */
    private String appInterestRates;
    
    /** 適用貸株料. */
    private String appLendingStock;
    
    /** プレミアム料. */
    private String premium;
    
    /** Ｔポイント. */
    private String tpoint;
    
    /** 利用後のＴポイント. */
    private String tpointAfter;
    
    /** 一日信用区分. */
    private String dailyCreditKbn;
    
    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;

    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者営業員コード. */
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
    
    /**
     * エラーフラグ. {@link com.sbisec.helios.ap.common.util.ApiWrapperUtil#isError ApiWrapperUtil.isError}を設定する.
     *
     * @see com.sbisec.helios.ap.common.util.ApiWrapperUtil#isError
     */
    private boolean isError;
}

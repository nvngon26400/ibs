package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 国内投信注文取消確認 SQL001 リクエストパラメータ
 *
 * @author SCSK
 *
 *     2023/11/24 新規作成
 */
@Data
public class IfaDomesticMutualFundOrderCancelConfirmSql001RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客コード */
    private String customerId;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 特定口座区分 */
    private String specificAccount;
    
    /** ジュニア特定口座区分 */
    private String jrSpecificAccount;
    
    /** ファンドタイプ（全角半角）. */
    private String fundType;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 金額（数値(整数)）. */
    private String amount;
    
    /** 口数（数値(整数)）. */
    private String unit;
    
    /** 乗換優遇区分（全角半角）. */
    private String norikaeYuguKbn;
    
    /** 分配金受取方法. */
    private String distributionReceiveMethod;
    
    /** 目論見書チェック区分 */
    private String dispatchId;
    
    /** ポイント種別（全角半角）. */
    private String pointType;
    
    /** ポイント利用 */
    private String pointFlg;
    
    /** 利用ポイント（数値(整数)）. */
    private String point;
    
    /** レバレッジ投資信託 */
    private String leverageKbn;
    
    /** 乗換勧誘 */
    private String norikaeKanyuKbn;
    
    /** 同一通貨/同一国の乗換 */
    private String douitsuTukaKuniKbn;
    
    /** 他社間投信乗換勧誘 */
    private String tashaNorikaeKbn;
    
    /** 短期売却確認 */
    private String tankiSellKbn;
    
    /** 償還前売却確認 */
    private String shokanMaeKbn;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** 受注方法 */
    private String jutyuKbn;
    
    /** 目論見書の交付方法 */
    private String mokuromiKoufuKbn;
    
    /** 利益相反可能性の説明. */
    private String conflictOfInterestExplain;
    
    /** 確認項目.目論見書補完書面の確認 */
    private String checkMokuromi;
    
    /** 確認項目.窓空きファンドの注意事項に同意 */
    private String checkMadoAki;
    
    /** 確認項目.費用について説明済 */
    private String checkHiyou;
    
    /** 確認項目.複数取引業者での手数料等明示済 */
    private String checkFukusu;
    
    /** アラート内容確認.コンプラチェックワーニング確認 */
    private String checkCompWrnAlert;
    
    /** 資金性格区分 */
    private String shikinSeikakuKbn;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 見積単価（数値(小数)）. */
    private String quoteUnitPrice;
    
    /** 約定金額（数値(整数)）. */
    private String contractAmount;
    
    /** 手数料/諸費用（数値(整数)）. */
    private String charge;
    
    /** 消費税（数値(整数)）. */
    private String consumptionTax;
    
    /** 讓渡益税（数値(整数)）. */
    private String yieldTax;
    
    /** 精算金額（数値(整数)）. */
    private String settlementAmount;
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者営業員コード */
    private String brokerChargeCode;
    
    /** 取消ユーザーＩＤ */
    private String cancelUserId;
    
    /** ユーザーID */
    private String userId;
    
    /** 購入解約方法. */
    private String buyCancelMethod;

    /** 受注日. */
    private String orderDate;

    /** 作成者 */
    private String creatUserId;

    /** 更新者 */
    private String updateUserId;
}

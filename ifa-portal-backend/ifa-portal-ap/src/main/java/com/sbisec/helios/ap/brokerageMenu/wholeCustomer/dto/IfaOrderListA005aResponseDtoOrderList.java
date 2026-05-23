package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
*
* @author BASE李
*
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaOrderListA005aResponseDtoOrderList extends ModelBase {

    /**
     * シリアルナンバー
     */
    private static final long serialVersionUID = -8018996493128649891L;
    
    /** 部店 */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 取引コース */
    private String course;
    
    /** 顧客名(漢字) */
    private String customerNameKanji;
    
    /** 顧客名(カナ) */
    private String customerNameKana;
    
    /** 注文番号 */
    private String orderNumber;
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 銘柄名 */
    private String brandName;
    
    /** 市場 */
    private String market;
    
    /** 注文状況 */
    private String orderStatus;
    
    /** 訂正/取消区分 */
    private String correctCancleKbn;
    
    /** 取消理由 */
    private String cancelReason;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 注文種別 */
    private String securiytClass;
    
    /** 条件詳細 */
    private String conditionDetails;
    
    /** 決済方法 */
    private String settlementMethod;
    
    /** 預り区分 */
    private String depositType;
    
    /** 注文日時 */
    private String orderDate;
    
    /** 期間 */
    private String period;
    
    /** 約定日時 */
    private String tradeTime;
    
    /** 数量 */
    private String quantity;
    
    /** 価格 */
    private String price;
    
    /** 現在値 */
    private String currentPrice;
    
    /** ポイント種別 */
    private String pointType;
    
    /** 利用ポイント */
    private String orderPoint;
    
    /** 分配金受取方法指定 */
    private String distributionReceiveMethodDesignation;
    
    /** 積立コース */
    private String accumulateCourse;
    
    /** 設定金額 */
    private String settingAmount;
    
    /** ボーナス月の設定 */
    private String bonusMonthSetting;
    
    /** NISA枠ぎりぎり注文 */
    private String nisaBarelyBuyingKbn;
    
    /** 課税シフト注文 */
    private String nisaExcessBuyingKbn;
    
    /** 1カ月あたりの積立金額 */
    private String oneMonthSumAmount;
    
    /** 次回発注予定日 */
    private String nextReserveDate;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** 受注方法 */
    private String orderMethod;
    
    /** 英文開示銘柄 */
    private String engPubType;
    
    /** 重要事項の説明 */
    private String importantMatter;
    
    /** 償還優遇枠 */
    private String redemptionIncentives;
    
    /** ワーニング申請取引 */
    private String warningApplyTrade;
    
    /** 目論見書交付方法 */
    private String prospectus;
    
    /** 乗換え勧誘 */
    private String switchingSolicitation;
    
    /** 利益相反可能性の説明 */
    private String conflictOfInterestExplain;
    
    /** 販売手数料の利率等の説明 */
    private String salesCommRate;
    
    /** 複数取引業者での手数料等明示 */
    private String checkFukusu;
    
    /** 同一通貨/同一国の乗換 */
    private String douitsuTukaKuniKbn;
    
    /** 他社間投信乗換え勧誘 */
    private String tashaNorikaeKbn;
    
    /** 短期売却確認 */
    private String tankiSellKbn;

    /** 償還前売却確認 */
    private String shokanMaeKbn;

    /** 発注者 */
    private String order;

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者名 */
    private String brokerName;
    
    /** 支店コード */
    private String brokerageBranchCode;

    /** 支店名 */
    private String brokerBranchName;

    /** 営業員コード */
    private String brokerChargeCode;

    /** 営業員名 */
    private String employeeName;

    /** 閲覧可能部店 */
    private String viewAblrButenCode;

    
}

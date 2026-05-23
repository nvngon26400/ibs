package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 委託保証金率の推移情報

 * @author SCSK 矢口
    2023/12/1 移植
 */
@Data
public class MarginTransition implements Serializable {
    
    private static final long serialVersionUID = 62268898745998246L;
    
    public MarginTransition() {
    
    }
    
    /** 受渡日（yyyy-MM-dd形式） */
    private String baseDate;
    
    /** 委託保証金 */
    private String grossMargin;
    
    /** 委託保証金現金 */
    private String marginCash;
    
    /** 代用有価証券評価額合計 */
    private String totalCollateralValue;
    
    /** 代用有価証券入庫額合計 */
    private String totalDepositCollateralValue;
    
    /** 代用有価証券出庫額合計 */
    private String totalWithdrawalCollateralValue;
    
    /** 保証金振替予定額（保証金→預り金） */
    private String transferEstimatedAmount;
    
    /** 実質保証金 */
    private String actualMargin;
    
    /** 委託保証金率(%) */
    private String depositRate;
    
    /** 未決済建代金合計 */
    private String totalUnclosedGrossAmount;
    
    /** 注文中建代金合計 */
    private String totalOpeningGrossAmount;
    
    /** 決済済建代金合計 */
    private String totalClosedGrossAmount;
    
    /** 信用取引必要保証金 */
    private String marginTransactionRequiredDeposit;
    
    /** 建玉必要保証金 */
    private String positionRequiredMargin;
    
    /** 建玉必要保証金（現金） */
    private String positionRequiredMarginCash;
    
    /** 返却必要保証金 */
    private String withdrawalRequiredMargin;
    
    /** 返却必要保証金（現金） */
    private String withdrawalRequiredMarginCash;
    
    /** 評価損益合計 */
    private String totalEvaluationProfitLoss;
    
    /** 支払諸経費等合計 */
    private String totalExpenses;
    
    /** 決済損益合計 */
    private String totalClosedProfitLoss;
    
    /** 信用建余力 */
    private String marginBuyingPower;
    
    /** 信用建余力(51%超分) */
    private String marginExcessBuyingPower;
    
    /** 出金指示可能額 */
    private String marginWithdrawable;
    
    /** 出庫余力 */
    private String collateralWithdrawable;
    
    /** 振替可能額（米ドル預り金→保証金） */
    private String transferPossibleAmount;
    
    /** 保護預り有価証券評価額合計 */
    private String totalProtectionEvaluationAmount;
    
    /** 参考委託保証金率 */
    private String referenceDepositRate;
    
    /** 残高（保証金） */
    private String marginBalance;
    
}

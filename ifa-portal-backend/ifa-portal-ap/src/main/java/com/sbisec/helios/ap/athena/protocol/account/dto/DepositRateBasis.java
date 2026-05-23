package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * リアルタイム委託保証金率情報 Dto.

 * @author SCSK 矢口
    2023/12/1 移植
 */
@Data
public class DepositRateBasis implements Serializable {
    
    private static final long serialVersionUID = -1970660021952175089L;
    
    public DepositRateBasis() {
    }
    
    /** 基準日 */
    private String baseDate;
    
    /** 委託保証金率 */
    private String marginRate;
    
    /** 実質保証金 */
    private String actualMargin;
    
    /** 委託保証金現金 */
    private String marginCash;
    
    /** 代用有価証券評価額合計 */
    private String totalCollateralValue;
    
    /** 建玉評価損益合計 */
    private String totalEvaluationAmount;
    
    /** 決済損益合計 */
    private String totalClosedProfitLoss;
    
    /** 支払諸経費合計 */
    private String totalExpenses;
    
    /** 建代金合計 */
    private String totalPositionAmount;
    
    /** 振替可能額（米ドル預り金→保証金） */
    private String transferPossibleAmount;
    
    /** 保護預り有価証券評価額合計 */
    private String totalProtectionEvaluationAmount;
    
    /** 参考保証金 */
    private String referenceMargin;
    
    /** 参考委託保証金率 */
    private String referenceDepositRate;
    
}

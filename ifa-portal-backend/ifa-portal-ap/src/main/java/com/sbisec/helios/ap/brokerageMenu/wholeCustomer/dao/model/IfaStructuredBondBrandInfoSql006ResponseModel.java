package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 仕組債銘柄情報　SQL006　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoSql006ResponseModel {
    
    /** ノックイン判定水準. */
    private String sbjmNockinLevel;
    
    /** ノックイン判定端数計算. */
    private String sbjmNockinCalc;
    
    /** ノックイン判定端数処理. */
    private String sbjmNockinRound;
    
    /** 早期償還判定水準. */
    private String sbjmEarlyPaymentLevel;
    
    /** 早期償還判定端数計算. */
    private String sbjmEarlyPaymentCalc;
    
    /** 早期償還判定端数処理. */
    private String sbjmEarlyPaymentRound;
    
    /** 早期償還判定水準ステップダウン有無（全角半角）. */
    private String sbjmEarlyPldFlg;
    
    /** 早期償還判定水準ステップダウン開始回目（数値(整数)）. */
    private String sbjmEarlyPldFrom;
    
    /** 早期償還判定水準ステップダウン設定値. */
    private String sbjmEarlyPldValue;
    
    /** 早期償還判定水準ステップダウン下限. */
    private String sbjmEarlyPldMin;
    
    /** 早期償還判定水準ステップダウン下限端数計算. */
    private String sbjmEarlyPldMinCalc;
    
    /** 早期償還判定水準ステップダウン下限端数処理. */
    private String sbjmEarlyPldMinRound;
    
    /** 早期償還価格比率. */
    private String sbjmEarlyPaymentPercent;
    
    /** 行使価格比率. */
    private String sbjmExercisePricePercent;
    
    /** 行使価格端数計算. */
    private String sbjmExercisePriceCalc;
    
    /** 行使価格端数処理. */
    private String sbjmExercisePriceRound;
    
    /** 満期償還判定水準. */
    private String sbjmPaymentCheckLevel;
    
    /** 満期償還端数計算. */
    private String sbjmExpirePaymentCalc;
    
    /** 満期償還端数処理. */
    private String sbjmExpirePaymentRound;
    
    /** 満期償還価格比率. */
    private String sbjmExpirePaymentPercent;
    
    /** ノックイン免除判定水準. */
    private String sbjmNockinExemptionLevel;
    
    /** ノックイン免除判定端数計算. */
    private String sbjmNockinExemptionCalc;
    
    /** ノックイン免除判定端数処理. */
    private String sbjmNockinExemptionRound;
    
}

package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 国内投信注文入力A001リスポンス
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA001ResponseDto {
    
    /** 売買区分（投信）. */
    private String mutualFundSellBuyType;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 選択口座. */
    private String selectAccountType;
    
    /** 選択預り区分. */
    private String selectDepositType;
    
    /** 預り区分リスト. */
    private List<String> depositTypeList;
    
    /** 銘柄情報. */
    private IfaDomesticMutualFundOrderInputBrand brand;
    
    /** 手数料率リスト(n). */
    private List<IfaDomesticMutualFundOrderInputCommRate> commRateList;
    
    /** 乗換優遇率（数値(小数)）. */
    private String switchingFavorableTreatmentRate;
    
    /** 扱者個別データ有無. */
    private String handlerIndividualDataExistence;
    
    /** 乗換優遇限度額. */
    private IfaDomesticMutualFundOrderInputSwitchingFavorableTreatmentLimit switchingFavorableTreatmentLimit;
    
    /** 買付余力. */
    private IfaDomesticMutualFundOrderInputBuyingPower buyingPower;
    
    /** 預り情報. */
    private IfaDomesticMutualFundOrderInputDepositInfo depositInfo;
    
    /** NISA買付可能最大額文言. */
    private String nisaBuy;
    
    /** 分配金受取方法(現在の設定). */
    private String distributionReceiveMethodWord;
    
    /** ポイント. */
    private IfaDomesticMutualFundOrderInputPoint point;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 乗換優遇枠適用表示状態. */
    private String transfersPreferentialQuotaApplication;
    
    /** 短期売却確認期間. */
    private String shortTermSaleConfirm;
    
    /** 償還前売却確認期間. */
    private String preRedemptionSellConfirmSelect;
    
    /** 目論見書チェック区分. */
    private String dispatchId;
    
}

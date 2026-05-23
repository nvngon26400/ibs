package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 国内投信注文入力A010リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA010RequestDto {
    
    /** 口座. */
    private String accountType;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** ファンドタイプ（半角英数字）. */
    private String fundType;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 同一通貨/同一国の乗換. */
    private String douitsuTukaKuniKbn;
    
    /** 他社間投信乗換勧誘（半角英数字）. */
    private String tashaNorikaeKbn;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrderType;
    
    /** 口数（数値(整数)）. */
    private String unit;
    
    /** 売却方法. */
    private String saleMethod;
    
    /** 売却指定. */
    private String sellDesignated;
    
    /** 金額（数値(整数)）. */
    private String amount;
    
    /** 短期売却確認（半角英数字）. */
    private String tankiSellKbn;
    
    /** 償還前売却確認（半角英数字）. */
    private String shokanMaeKbn;
    
    /** ポイント利用（半角英数字）. */
    private String pointFlg;
    
    /** ポイント. */
    private String point;
    
    /** ポイント種別（半角英数字）. */
    private String pointType;
    
    /** 分配金受取方法. */
    private String distributionReceiveMethodWord;
    
    /** 目論見書の交付方法（半角英数字）. */
    private String mokuromiKoufuKbn;
    
    /** 乗換優遇区分（半角英数字）. */
    private String norikaeYuguKbn;
    
    /** レバレッジ投資信託. */
    private String leverageInvestTrust;
    
    /** 乗換勧誘（半角英数字）. */
    private String norikaeKanyuKbn;
    
    /** 利益相反可能性の説明. */
    private String conflictOfInterestExplain;
    
    /** 確認項目.目論見書補完書面の確認（半角英数字）. */
    private String checkMokuromi;
    
    /** 確認項目.窓空きファンドの注意事項に同意（半角英数字）. */
    private String checkMadoAki;
    
    /** 確認項目.費用について説明済（半角英数字）. */
    private String checkHiyou;
    
    /** 確認項目.複数取引業者での手数料等明示済（半角英数字）. */
    private String checkFukusu;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 短期売却確認期間. */
    private String shortTermSaleConfirm;
    
    /** 償還前売却確認期間. */
    private String preRedemptionSellConfirmSelect;
    
    /** 銘柄情報.特殊区分. */
    private String brandSpecialClassification;
    
    /** 目論見書チェック区分. */
    private String dispatchId;
}

package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 国内投信注文入力A010リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputA010ApiRequest {
    
    /** 口座. */
    @NotEmpty(message = "口座")
    private String accountType;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;
    
    /** ファンドコード（回数）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（回数）")
    @Size(min = 4, max = 4, message = "ファンドコード（回数）")
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（号）")
    @Size(min = 3, max = 3, message = "ファンドコード（号）")
    private String fundCodeIssues;
    
    /** ファンドタイプ（半角英数字）. */
    @NotEmpty(message = "ファンドタイプ")
    @Size(min = 1, max = 1, message = "ファンドタイプ")
    private String fundType;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 同一通貨/同一国の乗換. */
    @NotEmpty(message = "同一通貨/同一国の乗換")
    private String douitsuTukaKuniKbn;
    
    /** 他社間投信乗換勧誘（半角英数字）. */
    @NotEmpty(message = "他社間投信乗換勧誘")
    @Size(min = 1, max = 1, message = "他社間投信乗換勧誘")
    private String tashaNorikaeKbn;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String receiveOrderType;
    
    /** 口数（数値(整数)）. */
    @Digits(integer = 11, fraction = 0, message = "口数")
    @NotEmpty(message = "口数")
    @Size(max = 14, message = "口数")
    private String unit;
    
    /** 売却方法. */
    @NotEmpty(message = "売却方法")
    private String saleMethod;
    
    /** 売却指定. */
    @NotEmpty(message = "売却指定")
    private String sellDesignated;
    
    /** 金額（数値(整数)）. */
    @Digits(integer = 11, fraction = 0, message = "金額")
    @NotEmpty(message = "金額")
    @Size(max = 14, message = "金額")
    private String amount;
    
    /** 短期売却確認（半角英数字）. */
    @NotEmpty(message = "短期売却確認")
    @Size(min = 1, max = 1, message = "短期売却確認")
    private String tankiSellKbn;
    
    /** 償還前売却確認（半角英数字）. */
    @NotEmpty(message = "償還前売却確認")
    @Size(min = 1, max = 1, message = "償還前売却確認")
    private String shokanMaeKbn;
    
    /** ポイント利用（半角英数字）. */
    @NotEmpty(message = "ポイント利用")
    @Size(min = 1, max = 1, message = "ポイント利用")
    private String pointFlg;
    
    /** ポイント. */
    @NotEmpty(message = "ポイント")
    private String point;
    
    /** ポイント種別（半角英数字）. */
    @NotEmpty(message = "ポイント種別")
    @Size(min = 1, max = 1, message = "ポイント種別")
    private String pointType;
    
    /** 分配金受取方法. */
    @NotEmpty(message = "分配金受取方法")
    private String distributionReceiveMethodWord;
    
    /** 目論見書の交付方法（半角英数字）. */
    @NotEmpty(message = "目論見書の交付方法")
    @Size(min = 1, max = 1, message = "目論見書の交付方法")
    private String mokuromiKoufuKbn;
    
    /** 乗換優遇区分（半角英数字）. */
    @NotEmpty(message = "乗換優遇区分")
    @Size(min = 1, max = 1, message = "乗換優遇区分")
    private String norikaeYuguKbn;
    
    /** レバレッジ投資信託. */
    @NotEmpty(message = "レバレッジ投資信託")
    private String leverageInvestTrust;
    
    /** 乗換勧誘（半角英数字）. */
    @NotEmpty(message = "乗換勧誘")
    @Size(min = 1, max = 1, message = "乗換勧誘")
    private String norikaeKanyuKbn;
    
    /** 利益相反可能性の説明（半角英数字）. */
    @NotEmpty(message = "利益相反可能性の説明")
    @Size(min = 1, max = 1, message = "利益相反可能性の説明")
    private String conflictOfInterestExplain;
    
    /** 確認項目.目論見書補完書面の確認（半角英数字）. */
    @NotEmpty(message = "確認項目.目論見書補完書面の確認")
    @Size(min = 1, max = 1, message = "確認項目.目論見書補完書面の確認")
    private String checkMokuromi;
    
    /** 確認項目.窓空きファンドの注意事項に同意（半角英数字）. */
    @NotEmpty(message = "確認項目.窓空きファンドの注意事項に同意")
    @Size(min = 1, max = 1, message = "確認項目.窓空きファンドの注意事項に同意")
    private String checkMadoAki;
    
    /** 確認項目.費用について説明済（半角英数字）. */
    @NotEmpty(message = "確認項目.費用について説明済")
    @Size(min = 1, max = 1, message = "確認項目.費用について説明済")
    private String checkHiyou;
    
    /** 確認項目.複数取引業者での手数料等明示済（半角英数字）. */
    @NotEmpty(message = "確認項目.複数取引業者での手数料等明示済")
    @Size(min = 1, max = 1, message = "確認項目.複数取引業者での手数料等明示済")
    private String checkFukusu;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;
    
    /** 短期売却確認期間. */
    @NotEmpty(message = "短期売却確認期間")
    private String shortTermSaleConfirm;
    
    /** 償還前売却確認期間. */
    @NotEmpty(message = "償還前売却確認期間")
    private String preRedemptionSellConfirmSelect;
    
    /** 銘柄情報.特殊区分. */
    @NotEmpty(message = "銘柄情報.特殊区分")
    private String brandSpecialClassification;
    
    /** 目論見書チェック区分. */
    private String dispatchId;
    
}

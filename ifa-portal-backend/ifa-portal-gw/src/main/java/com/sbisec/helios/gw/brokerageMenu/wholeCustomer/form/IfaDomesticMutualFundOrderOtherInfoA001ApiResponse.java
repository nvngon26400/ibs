package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;


import lombok.Data;

@Data
public class IfaDomesticMutualFundOrderOtherInfoA001ApiResponse {

    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（半角英数字）. */
    private String accountNumber;
    
    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** 売買区分. */
    private String tradeKbn;
    
    /** 乗換優遇区分. */
    private String norikaeYuguKbn;
    
    /** アラート内容確認.コンプラチェックワーニング確認. */
    private String checkCompWrnAlert;
    
    /** 目論見書の交付方法. */
    private String mokuromiKoufuKbn;
    
    /** 乗換勧誘. */
    private String norikaeKanyuKbn;
    
    /** 利益相反可能性の説明. */
    private String conflictOfInterestExplain;
    
    /** 確認項目.費用について説明済. */
    private String checkHiyou;
    
    /** 確認項目.複数取引業者での手数料等明示済. */
    private String checkFukusu;
    
    /** 同一通貨/同一国の乗換. */
    private String douitsuTukaKuniKbn;
    
    /** 他社間投信乗換勧誘. */
    private String tashaNorikaeKbn;
    
    /** 短期売却確認. */
    private String tankiSellKbn;
    
    /** 償還前売却確認. */
    private String shokanMaeKbn;
    
    /** ユーザーID. */
    private String userId;
    
    /** ユーザー名. */
    private String userName;

}

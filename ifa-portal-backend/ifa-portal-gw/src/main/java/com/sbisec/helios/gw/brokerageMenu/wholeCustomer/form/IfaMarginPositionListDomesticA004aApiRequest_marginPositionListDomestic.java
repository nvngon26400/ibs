package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticA004aApiRequest_marginPositionListDomestic {
    
    /** 信用建玉一覧リスト.部店. */
    @NotEmpty(message = "信用建玉一覧リスト.部店")
    private String butenCode;
    
    /** 信用建玉一覧リスト.口座. */
    @NotEmpty(message = "信用建玉一覧リスト.口座")
    private String accountNumber;
    
    /** 信用建玉一覧リスト.取引コース. */
    @NotEmpty(message = "信用建玉一覧リスト.取引コース")
    private String course;
    
    /** 信用建玉一覧リスト.顧客名(漢字). */
    @NotEmpty(message = "信用建玉一覧リスト.顧客名(漢字)")
    private String customerNameKanji;
    
    /** 信用建玉一覧リスト.顧客名(カナ). */
    @NotEmpty(message = "信用建玉一覧リスト.顧客名(カナ)")
    private String customerNameKana;
    
    /** 信用建玉一覧リスト.維持率（%）. */
    @NotEmpty(message = "信用建玉一覧リスト.維持率（%）")
    private String domesticMarginPositionListActualGrntRate;
    
    /** 信用建玉一覧リスト.銘柄名. */
    @NotEmpty(message = "信用建玉一覧リスト.銘柄名")
    private String brandName;
    
    /** 信用建玉一覧リスト.銘柄コード. */
    @NotEmpty(message = "信用建玉一覧リスト.銘柄コード")
    private String brandCode;
    
    /** 信用建玉一覧リスト.市場. */
    @NotEmpty(message = "信用建玉一覧リスト.市場")
    private String market;
    
    /** 信用建玉一覧リスト.取引. */
    @NotEmpty(message = "信用建玉一覧リスト.取引")
    private String openTradeKbn;
    
    /** 信用建玉一覧リスト.建日. */
    @NotEmpty(message = "信用建玉一覧リスト.建日")
    private String openTradeDate;
    
    /** 信用建玉一覧リスト.返済期限. */
    @NotEmpty(message = "信用建玉一覧リスト.返済期限")
    private String lastTradeDate;
    
    /** 信用建玉一覧リスト.預り. */
    @NotEmpty(message = "信用建玉一覧リスト.預り")
    private String depositFullHalf13;
    
    /** 信用建玉一覧リスト.建株数. */
    @NotEmpty(message = "信用建玉一覧リスト.建株数")
    private String contPositionTotal;
    
    /** 信用建玉一覧リスト.注文中. */
    @NotEmpty(message = "信用建玉一覧リスト.注文中")
    private String unactualQuantity;
    
    /** 信用建玉一覧リスト.建単価. */
    @NotEmpty(message = "信用建玉一覧リスト.建単価")
    private String unitPriceForeign;
    
    /** 信用建玉一覧リスト.現在値. */
    @NotEmpty(message = "信用建玉一覧リスト.現在値")
    private String currentPrice;
    
    /** 信用建玉一覧リスト.建代金. */
    @NotEmpty(message = "信用建玉一覧リスト.建代金")
    private String openAmount;
    
    /** 信用建玉一覧リスト.諸経費等合計. */
    @NotEmpty(message = "信用建玉一覧リスト.諸経費等合計")
    private String cost;
    
    /** 信用建玉一覧リスト.評価損益. */
    @NotEmpty(message = "信用建玉一覧リスト.評価損益")
    private String domesticPositionValuation;
    
    /** 信用建玉一覧リスト.仲介業者コード. */
    @NotEmpty(message = "信用建玉一覧リスト.仲介業者コード")
    private String brokerCode;
    
    /** 信用建玉一覧リスト.仲介業者名. */
    @NotEmpty(message = "信用建玉一覧リスト.仲介業者名")
    private String brokerName;
    
    /** 信用建玉一覧リスト.支店コード. */
    @NotEmpty(message = "信用建玉一覧リスト.支店コード")
    private String branchCode;
    
    /** 信用建玉一覧リスト.支店名. */
    @NotEmpty(message = "信用建玉一覧リスト.支店名")
    private String branchName;
    
    /** 信用建玉一覧リスト.営業員コード. */
    @NotEmpty(message = "信用建玉一覧リスト.営業員コード")
    private String empCode;
    
    /** 信用建玉一覧リスト.営業員名. */
    @NotEmpty(message = "信用建玉一覧リスト.営業員名")
    private String brokerChargeName;
    
}

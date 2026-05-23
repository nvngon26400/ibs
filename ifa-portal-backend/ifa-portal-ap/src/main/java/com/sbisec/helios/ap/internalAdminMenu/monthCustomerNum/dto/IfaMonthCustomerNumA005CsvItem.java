package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaMonthCustomerNumA005CsvItem  extends ModelBase {

    private static final long serialVersionUID = 6651543506903761494L;
    
    /** 対象年月. */
    private String baseDateYm;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 扱者コード. */
    private String dealerNumber;
    
    /** 営業員コード. */
    private String intermediaryEmpCd;
    
    /** 営業員名. */
    private String brokerChargeName;
    
    /** 部店. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** 取引コース. */
    private String customerAttributeName;
    
    /** 顧客名（漢字）. */
    private String nameKanji;
    
    /** 顧客名（カナ）. */
    private String nameKana;
    
    /** 年齢. */
    private String age;
    
    /** 年代. */
    private String ageGroup;
    
    /** 性別. */
    private String sexKbn;
    
    /** 個人/法人. */
    private String corporationKbn;
    
    /** Cランク. */
    private String tcCompRank;
    
    /** 支店コード. */
    private String brokerBranchCode;
    
    /** 支店名. */
    private String brokerBranchName;
    
    /** 住所. */
    private String addressKanji1;
    
    /** 口座開設日. */
    private String openAcctDate;
    
    /** 電話番号. */
    private String phoneNumber;
    
    /** 投資方針. */
    private String qaInvestmentPlan;
    
    /** 資金の性格. */
    private String qaFundCharacter;
    
    /** 主な収入源. */
    private String qaIncomeForm;
    
    /** 取引の動機. */
    private String qaTradingMotive;
    
    /** 資産運用期間. */
    private String qaEmploymentPeriod;
    
    /** 年収. */
    private String qaAnnualIncome;
    
    /** 金融資産. */
    private String qaFinancialAssets;
    
    /** 興味ある取引. */
    private String qaInterestedTrading;
    
    /** 投資経験（株式現物）. */
    private String expStock;
    
    /** 投資経験（債券）. */
    private String expDebenture;
    
    /** 投資経験（転換社債）. */
    private String expCb;
    
    /** 投資経験（信用）. */
    private String expMargin;
    
    /** 投資経験（ワラント）. */
    private String expWarrant;
    
    /** 投資経験（先物OP）. */
    private String expFutureop;
    
    /** 投資経験（貯蓄型投信）. */
    private String expSavedtypefund;
    
    /** 投資経験（その他投信）. */
    private String expOtherfund;
    
    /** 投資経験（外国証券）. */
    private String expForeign;
}

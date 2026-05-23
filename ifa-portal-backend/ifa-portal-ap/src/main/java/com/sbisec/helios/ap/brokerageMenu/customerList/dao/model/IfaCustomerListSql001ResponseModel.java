package com.sbisec.helios.ap.brokerageMenu.customerList.dao.model;

import lombok.Data;

/**
 * 顧客一覧_基本 SQL001 レスポンスモデル
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaCustomerListSql001ResponseModel {
    
    /** Cランク. */
    private String tcCompRank;
    
    /** 契約締結前交付書面コード名. */
    private String customerAttributeName;
    
    /** 顧客名（カナ）（全角半角）. */
    private String customerNameKana;
    
    /** 顧客名（漢字）（全角半角）. */
    private String customerNameKanji;
    
    /** 口座開設日. */
    private String openAcctDate;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 郵便番号(前)(後). */
    private String zipCodeBeforeAndAfter;
    
    /** 住所(漢字). */
    private String adressKanji;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 電話番号（英数字記号A(+-_./@*#%)）. */
    private String corporatePhoneNumber;
    
    /** 生年月日. */
    private String corBirthFlg;
    
    /** 年齢（数値(整数)）. */
    private String age;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 仲介業者支店名（支店名用）. */
    private String branchNameOfBranch;
    
    /** 仲介業者支店名（仲介業者名用）. */
    private String branchNameOfBroker;
    
    /** 担当者名（全角半角）. */
    private String chargeName;
    
    /** 仲介業者支店コード（数字）. */
    private String subBrokerId;
    
    /** 最終約定日. */
    private String lastTradeDate;
    
    /** 評価損益（円貨） */
    private String customerListProfitAndLoss;
    
    /** 総資産の合計. */
    private String totalAssets;
    
    /** 手数料累計の合計(年間手数料用). */
    private String commTotalAmountOfYear;
    
    /** 手数料累計の合計(累計手数料用). */
    private String commTotalAmount;
    
    /** 閲覧可能部店コード. */
    private String viewAblrButenCode;

    /** NISA口座表示情報. */
    private String nisaContractKbnViewInfo;
    
    /** 評価額（円貨）の合計. */
    private String valuationTotalJpyAmount;
    
    /** 電子交付承諾日付. */
    private String electronicDocConsentDate;
    
    /** 外国証券取引口座表示情報. */
    private String foreignSecurityAccountViewInfo;
    
    /** 変更完了日時. */
    private String changeFinishDateTime;

    /** 取引制限. */
    private String tradeRestriction;
    
    /** 合計取得件数. */
    private String totalRow;

	/** 法人区分. */
	private String corporationKbn;

	/** 投資の方針. */
	private String investmentPlan;

	/** 職業. */
	private String occupation;

	/** 年収. */
	private String annualIncome;

	/** 資産運用期間. */
	private String employmentPeriod;

	/** 金融資産. */
	private String financialAssets;

	/** 資金の性格. */
	private String fundCharacter;

	/** 主な収入源. */
	private String incomeForm;

	/** 投資経験有無（株式現物）. */
	private String stockExpKbn;

	/** 投資経験年数（株式現物）. */
	private String stockExp;

	/** 投資経験有無（債券）. */
	private String debentureExpKbn;

	/** 投資経験年数（債券）. */
	private String debentureExp;

	/** 投資経験有無（転換社債）. */
	private String cbExpKbn;

	/** 投資経験年数（転換社債）. */
	private String cbExp;

	/** 投資経験有無（信用）. */
	private String marginExpKbn;

	/** 投資経験年数（信用）. */
	private String marginExp;

	/** 投資経験有無（ワラント）. */
	private String warrantExpKbn;

	/** 投資経験年数（ワラント）. */
	private String warrantExp;

	/** 投資経験有無（先物OP）. */
	private String futureopExpKbn;

	/** 投資経験年数（先物OP）. */
	private String futureopExp;

	/** 投資経験有無（貯蓄型投信）. */
	private String savedtypefundExpKbn;

	/** 投資経験年数（貯蓄型投信）. */
	private String savedtypefundExp;

	/** 投資経験有無（外国証券）. */
	private String foreignExpKbn;

	/** 投資経験年数（外国証券）. */
	private String foreignExp;

	/** 投資経験有無（その他投信）. */
	private String otherfundExpKbn;

	/** 投資経験年数（その他投信）. */
	private String otherfundExp;
}

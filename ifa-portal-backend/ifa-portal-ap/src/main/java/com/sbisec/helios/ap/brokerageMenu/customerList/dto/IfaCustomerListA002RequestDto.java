package com.sbisec.helios.ap.brokerageMenu.customerList.dto;

import java.util.List;
import java.util.ArrayList;

import lombok.Data;

/**
 * 顧客一覧_基本 表示 リクエストパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaCustomerListA002RequestDto {
    
    /** 仲介業者コード（数字）. */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外（全角半角）. */
    private String chkBrokerCodeExclude;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名(漢字/カナ)（全角半角）. */
    private String customerNameKanjiKana;
    
    /** 顧客名(漢字/カナ)　（条件リスト）. */
    private String customerNameConditionList;
    
    /** 取引コース（全角半角）. */
    private List<IfaCustomerListTradingCourse> course = new ArrayList<IfaCustomerListTradingCourse>();

    /** 取引制限ありチェックボックス. */
    private String tradeRestrictionCheckbox;
    
    /** コンプラランク（From）（英字（大文字））. */
    private String complianceLankFrom;
    
    /** コンプラランク（To）（英字（大文字））. */
    private String complianceLankTo;
    
    /** 住所（全角半角）. */
    private String adress;
    
    /** 住所　（条件リスト）（全角半角）. */
    private String adressConditionList;
    
    /** 電話番号. */
    private String corporatePhoneNumber;
    
    /** 年齢（From）（数値(整数)）. */
    private String ageFrom;
    
    /** 年齢（To）（数値(整数)）. */
    private String ageTo;
    
    /** 生年月日（From）. */
    private String birthDateFrom;
    
    /** 生年月日（To）. */
    private String birthDateTo;
    
    /** 口座開設日（From）. */
    private String openAccountFrom;
    
    /** 口座開設日（To）. */
    private String openAccountTo;
    
    /** 閲覧可能部店　（複数入力）. */
    private List<String> butenCodeArray;
    
    /** 評価額（From）（数値(整数)）. */
    private String valuationFrom;
    
    /** 評価額（To）（数値(整数)）. */
    private String valuationTo;
    
    /** 評価額（条件リスト）（全角半角）. */
    private String valuationConditionList;
    
    /** 手数料累計（全角半角）. */
    private String commTotalList;

    /** 手数料累計期間（From）. */
    private String commTotalPeriodFrom;
    
    /** 手数料累計期間（To）. */
    private String commTotalPeriodTo;
    
    /** 手数料累計額（From）（数値(整数)）. */
    private String commTotalAmountFrom;
    
    /** 手数料累計額（To）（数値(整数)）. */
    private String commTotalAmountTo;
    
    /** 手数料累計額（条件リスト）（全角半角）. */
    private String commTotalAmountConditionList;
    
    /** 最終約定日（チェック）. */
    private String lastTradeDateCheck;
    
    /** 最終約定日（From）. */
    private String lastTradeDateFrom;
    
    /** 最終約定日（To）. */
    private String lastTradeDateTo;

    /**  NISA口座. */
    private String nisaContractKbnList;
    
    /** コース変更完了日（From）. */
    private String courseChangeFinishDateFrom;
    
    /** コース変更完了日（To）. */
    private String courseChangeFinishDateTo;
    
    /** 外国証券取引口座. */
    private String foreignSecurityAccountList;
    
    /** 住所表示（チェック）（全角半角）. */
    private String adressDisplay;
    
    /** 電話番号表示（チェック）（全角半角）. */
    private String phoneNumberDisplay;
    
    /** 年齢表示（チェック）（全角半角）. */
    private String ageDisplay;
    
    /** 生年月日表示（チェック）（全角半角）. */
    private String birthDateDisplay;
    
    /** 口座開設日表示（チェック）（全角半角）. */
    private String openAccountDisplay;
    
    /** 閲覧可能部店表示（チェック）（全角半角）. */
    private String viewAblrButenDisplay;

    /** NISA口座表示（チェック）（全角半角）. */
    private String nisaContractKbnDisplay;
    
    /** 外国証券取引口座表示（チェック）（全角半角）. */
    private String foreignSecurityAccountDisplay;
    
    /** コース変更完了日表示（チェック）（全角半角）. */
    private String courseChangeFinishDateDisplay;
    
    /** 最終約定日表示（チェック）（全角半角）. */
    private String lastTradeDateDisplay;

	/** 法人区分（全角半角）. */
	private String personalCorporation;

	/** 投資方針（全角半角）. */
	private List<IfaCustomerListPersonalCorporation> investmentPlan;

	/** 資産運用期間（全角半角）. */
	private List<IfaCustomerListPersonalCorporation> employmentPeriod;

	/** 年収（全角半角）. */
	private List<IfaCustomerListPersonalCorporation> annualIncome;

	/** 金融資産（全角半角）. */
	private List<IfaCustomerListPersonalCorporation> financialAssets;

	/** 職業（全角半角）. */
	private List<IfaCustomerListPersonalCorporation> occupation;

	/** 投資経験（全角半角）. */
	private List<IfaCustomerListPersonalCorporation> investmentExp;

	/** 投資経験（チェック）（全角半角）. */
	private String investmentExpDisplay;

	/** 資金の性格（全角半角）. */
	private List<IfaCustomerListPersonalCorporation> fundCharacter;

	/** 主な収入源（全角半角）. */
	private List<IfaCustomerListPersonalCorporation> incomeForm;

}

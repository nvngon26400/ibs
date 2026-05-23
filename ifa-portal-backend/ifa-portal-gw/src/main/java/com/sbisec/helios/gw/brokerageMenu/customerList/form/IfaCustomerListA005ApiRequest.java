package com.sbisec.helios.gw.brokerageMenu.customerList.form;

import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListPersonalCorporation;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * 顧客一覧_基本 表示 リクエストパラメータ
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaCustomerListA005ApiRequest {
    
    /** 仲介業者コード（数字）. */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp = "0-9", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private List<String> brokerCodeList;
    
    /** 仲介業者除外（全角半角）. */
    @NotEmpty(message = "仲介業者除外")
    @Size(min = 1, max = 1, message = "仲介業者除外")
    private String chkBrokerCodeExclude;
    
    /** 支店コード（数字）. */
    @NotEmpty(message = "支店コード")
    @Pattern(regexp = "0-9", message = "支店コード")
    @Size(max = 3, message = "支店コード")
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    @NotEmpty(message = "営業員コード")
    @Size(min = 4, max = 4, message = "営業員コード")
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 顧客名(漢字/カナ)（全角半角）. */
    @NotEmpty(message = "顧客名(漢字/カナ)")
    @Size(max = 72, message = "顧客名(漢字/カナ)")
    private String customerNameKanjiKana;
    
    /** 顧客名(漢字/カナ)　（条件リスト）. */
    @NotEmpty(message = "顧客名(漢字/カナ)　（条件リスト）")
    private String customerNameConditionList;
    
    /** 取引コース（全角半角）. */
    @NotEmpty(message = "取引コース")
    @Size(max = 40, message = "取引コース")
    private List<TradingCourse> course;

    /** 取引制限ありチェックボックス. */
    @NotEmpty(message = "取引制限ありチェックボックス")
    private String tradeRestrictionCheckbox;
    
    /** コンプラランク（From）（英字（大文字））. */
    @NotEmpty(message = "コンプラランク（From）")
    private String complianceLankFrom;
    
    /** コンプラランク（To）（英字（大文字））. */
    @NotEmpty(message = "コンプラランク（To）")
    private String complianceLankTo;
    
    /** 住所（全角半角）. */
    @NotEmpty(message = "住所")
    private String adress;
    
    /** 住所　（条件リスト）（全角半角）. */
    @NotEmpty(message = "住所　（条件リスト）")
    @Size(max = 255, message = "住所　（条件リスト）")
    private String adressConditionList;
    
    /** 電話番号. */
    @NotEmpty(message = "電話番号")
    private String corporatePhoneNumber;
    
    /** 年齢（From）（数値(整数)）. */
    @NotEmpty(message = "年齢（From）")
    private String ageFrom;
    
    /** 年齢（To）（数値(整数)）. */
    @NotEmpty(message = "年齢（To）")
    private String ageTo;
    
    /** 生年月日（From）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "生年月日（From）")
    @Size(min = 10, max = 10, message = "生年月日（From）")
    private String birthDateFrom;
    
    /** 生年月日（To）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "生年月日（To）")
    @Size(min = 10, max = 10, message = "生年月日（To）")
    private String birthDateTo;
    
    /** 口座開設日（From）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "口座開設日（From）")
    @Size(min = 10, max = 10, message = "口座開設日（From）")
    private String openAccountFrom;
    
    /** 口座開設日（To）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "口座開設日（To）")
    @Size(min = 10, max = 10, message = "口座開設日（To）")
    private String openAccountTo;
    
    /** 閲覧可能部店　（複数入力）. */
    @NotEmpty(message = "閲覧可能部店　（複数入力）")
    private List<String> butenCodeArray;
    
    /** 評価額（From）（数値(整数)）. */
    @Digits(integer = 19, fraction = 0, message = "評価額（From）")
    @NotEmpty(message = "評価額（From）")
    @Size(max = 25, message = "評価額（From）")
    private String valuationFrom;
    
    /** 評価額（To）（数値(整数)）. */
    @Digits(integer = 19, fraction = 0, message = "評価額（To）")
    @NotEmpty(message = "評価額（To）")
    @Size(max = 25, message = "評価額（To）")
    private String valuationTo;
    
    /** 評価額（条件リスト）（全角半角）. */
    @NotEmpty(message = "評価額（条件リスト）")
    @Size(max = 255, message = "評価額（条件リスト）")
    private String valuationConditionList;
    
    /** 手数料累計（全角半角）. */
    @NotEmpty(message = "手数料累計")
    @Size(min = 1, max = 1, message = "手数料累計")
    private String commTotalList;
    
    /** 手数料累計期間（From）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "手数料累計期間（From）")
    @Size(min = 10, max = 10, message = "手数料累計期間（From）")
    private String commTotalPeriodFrom;
    
    /** 手数料累計期間（To）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "手数料累計期間（To）")
    @Size(min = 10, max = 10, message = "手数料累計期間（To）")
    private String commTotalPeriodTo;
    
    /** 手数料累計額（From）（数値(整数)）. */
    @Digits(integer = 18, fraction = 0, message = "手数料累計額（From）")
    @NotEmpty(message = "手数料累計額（From）")
    @Size(max = 24, message = "手数料累計額（From）")
    private String commTotalAmountFrom;
    
    /** 手数料累計額（To）（数値(整数)）. */
    @Digits(integer = 18, fraction = 0, message = "手数料累計額（To）")
    @NotEmpty(message = "手数料累計額（To）")
    @Size(max = 24, message = "手数料累計額（To）")
    private String commTotalAmountTo;
    
    /** 手数料累計額（条件リスト）（全角半角）. */
    @NotEmpty(message = "手数料累計額（条件リスト）")
    @Size(max = 255, message = "手数料累計額（条件リスト）")
    private String commTotalAmountConditionList;
    
    /** 最終約定日（チェック）. */
    @NotEmpty(message = "最終約定日（チェック）")
    private String lastTradeDateCheck;
    
    /** 最終約定日（From）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "最終約定日（From）")
    @Size(min = 10, max = 10, message = "最終約定日（From）")
    private String lastTradeDateFrom;
    
    /** 最終約定日（To）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "最終約定日（To）")
    @Size(min = 10, max = 10, message = "最終約定日（To）")
    private String lastTradeDateTo;
    
    /** コース変更完了日（From）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "コース変更完了日（From）")
    @Size(min = 10, max = 10, message = "コース変更完了日（From）")
    private String courseChangeFinishDateFrom;
    
    /** コース変更完了日（To）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "コース変更完了日（To）")
    @Size(min = 10, max = 10, message = "コース変更完了日（To）")
    private String courseChangeFinishDateTo;
    
    /** NISA口座. */
    @NotEmpty(message = "NISA口座")
    private String nisaContractKbnList;
    
    /** 外国証券取引口座. */
    @NotEmpty(message = "外国証券取引口座")
    private String foreignSecurityAccountList;
    
    /** 住所表示（チェック）（全角半角）. */
    @NotEmpty(message = "住所表示（チェック）")
    private String adressDisplay;
    
    /** 電話番号表示（チェック）（全角半角）. */
    @NotEmpty(message = "電話番号表示（チェック）")
    private String phoneNumberDisplay;
    
    /** 年齢表示（チェック）（全角半角）. */
    @NotEmpty(message = "年齢表示（チェック）")
    private String ageDisplay;
    
    /** 生年月日表示（チェック）（全角半角）. */
    @NotEmpty(message = "生年月日表示（チェック）")
    private String birthDateDisplay;
    
    /** 口座開設日表示（チェック）（全角半角）. */
    @NotEmpty(message = "口座開設日表示（チェック）")
    private String openAccountDisplay;
    
    /** 閲覧可能部店表示（チェック）（全角半角）. */
    @NotEmpty(message = "閲覧可能部店表示（チェック）")
    private String viewAblrButenDisplay;

    /** NISA口座表示（チェック）（全角半角）. */
    @NotEmpty(message = "NISA口座表示（チェック）")
    private String nisaContractKbnDisplay;
    
    /** 外国証券取引口座表示（チェック）（全角半角）. */
    @NotEmpty(message = "外国証券取引口座表示（チェック）")
    private String foreignSecurityAccountDisplay;
    
    /** コース変更完了日表示（チェック）（全角半角）. */
    @NotEmpty(message = "コース変更完了日表示（チェック）")
    private String courseChangeFinishDateDisplay;
    
    /** 最終約定日表示（チェック）（全角半角）. */
    @NotEmpty(message = "最終約定日表示（チェック）")
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

    /**
     * 顧客一覧_基本 取引コース
     *
     * @author SCSK池田
     * 
     */
    @Data
    public static class TradingCourse {
        
        /** id */
        @NotEmpty(message = "取引コース(id)")
        private String id;
        
        /** isSelected */
        @NotEmpty(message = "取引コース(isSelected)")
        private String isSelected;
        
    }
    
}

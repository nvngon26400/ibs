package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaCustomerPortalA001ResponseDto {
    
    /** 顧客コード（数字）. */
    private String customerCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名（漢字）（全角半角）. */
    private String customerNameKanji;
    
    /** 顧客名（カナ）（全角半角）. */
    private String customerNameKana;
    
    /** コンプラランク（全角半角）. */
    private String complianceLank;
    
    /** 契約締結前交付書面コード（全角半角）. */
    private String customerAttribute;
    
    /** 契約締結前交付書面コード名. */
    private String customerAttributeName;
    
    /** 扱者コード（半角英数字）. */
    private String dealerNumber;
    
    /** 生年月日. */
    private String corBirthFlg;
    
    /** 年齢（数値(整数)）. */
    private String age;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 法人区分（全角半角）. */
    private String corporationType;
    
    /** 電話番号（英数字記号A(+-_./@*#%)）. */
    private String homePhoneNumbar;
    
    /** 携帯電話番号（英数字記号A(+-_./@*#%)）. */
    private String phoneNumber2;
    
    /** 代表者名（全角半角）. */
    private String representativeName;
    
    /** 代表者カナ（全角半角）. */
    private String representativeNameKana;
    
    /** 代理人名（全角半角）. */
    private String agentName;
    
    /** 代理人カナ（全角半角）. */
    private String agentNameKana;
    
    /** 代理人肩書（全角半角）. */
    private String titleOfAgent;
    
    /** 特定口座区分（全角半角）. */
    private String specificAccountType;
    
    /** 配受無区分（全角半角）. */
    private String dividendReceiptType;
    
    /** マル優適格者区分（全角半角）. */
    private String taxExemptQualifiedPersonType;
    
    /** ISA契約区分. */
    private String isaContractType;
    
    /** ジュニアISA契約区分. */
    private String jrIsaContractType;
    
    /** ジュニアNISA口座番号（数字）. */
    private String jrNisaAccountNumber;
    
    /** ジュニア特定口座区分. */
    private String jrTokuteiKouzaKbn;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
    /** 仲介業者営業員名. */
    private String brokerChargeName;
    
    /** 注意情報レベル. */
    private String noticeInfoLevel;
    
    /** メモ(IFA専用)更新日時. */
    private String ifaMemoUpdateDateTime;
    
    /** メモ(IFA専用)内容. */
    private String ifaMemoContent;
    
    /** ISA買付可能枠(当年). */
    private String isaBuyLimitYear;
    
    /** ISA買付可能枠(翌年). */
    private String isaBuyLimitYearNext;
    
    /** ISA買付可能当年. */
    private String isaBuyAbleYear;
    
    /** ISA買付可能翌年. */
    private String isaBuyAbleYearNext;
    
    /** NISA区分（当年）（全角半角）. */
    private String nisaType;
    
    /** NISA区分（翌年）. */
    private String nisaTypeYearNext;
    
    /** ISA買付可能枠(当年)(JrNISA). */
    private String isaBuyLimitYearJuniorNisa;
    
    /** ISA買付可能枠(翌年)(JrNISA). */
    private String isaBuyLimitYearNextJuniorNisa;
    
    /** ISA買付可能当年(JrNISA). */
    private String isaBuyAbleYearJuniorNisa;
    
    /** ISA買付可能翌年(JrNISA). */
    private String isaBuyAbleYearNextJuniorNisa;
    
    /** 払出制限解除フラグ. */
    private String withdrawalRestrictionCancelFlag;
    
    /** 買付余力(円貨). */
    private String yenBuyingPower;
    
    /** 信用口座区分(国内). */
    private String domesticMarginAccountType;
    
    /** 新規建余力(円貨). */
    private String newBuildingCapacity;
    
    /** 維持率(円貨). */
    private String yenCustomerInfoDetentionRate;
    
    /** 買付余力(外貨)有無. */
    private String foreignBuyPowerFlag;
    
    /** 買付余力(外貨)あり国数. */
    private String foreignBuyingPowerCountryCount;
    
    /** 新規建余力(外貨). */
    private String foreignNewBuildingCapacity;
    
    /** 維持率(外貨). */
    private String foreignCustomerInfoDetentionRate;
    
    /** 外国株式取引口座開設状況（全角半角）. */
    private String foreignStockTradeAccountOpenStatus;
    
    /** 外貨建商品取引口座開設状況（全角半角）. */
    private String foreignSecurityTradeAccountOpenStatus;
    
    /** 信用口座区分(外国). */
    private String foreignMarginAccountType;
    
    /** メモ(CCS). */
    private String ccsMemo;
    
    /** コース名. */
    private String customerInfoCourse;
    
    /** 仲介業者営業員リスト件数. */
    private String brokerChargeListCount;
    
    /** 成長投資枠(当年) */
    private String growthInvestmentLimitYear;
    
    /** つみたて投資枠(当年) */
    private String accumulateInvestmentLimitYear;

    /** 維持率(円貨)説明文言 */
    private String maintenanceRateJpyAmountDescriptionMessage;
    
    /** 顧客情報取得エラーリスト. */
    private List<IfaCustomerPortalA001ResponseDto_CustomerInfoAcquireErrorList> customerInfoAcquireErrorList;
    
    /** 顧客名_姓名(カナ) */
    private String nameKana;
    
    /** 郵便番号(前)(後)（半角英数字）. */
    private String zipCode;
    
    /** 住所(漢字)（全角半角）. */
    private String addressKanji1;
    
    /** 住所(カナ)（全角半角）. */
    private String addressKana1;
    
    /** 性別 */
    private String sexKbn;
    
    /** 本人職業区分 */
    private String uaiOccupation;
    
    /** 勤務先名(漢字)（全角半角）. */
    private String uaiOfficeName;
    
    /** 勤務先電話番号(番号) */
    private String uaiOfficePhoneNumber;
    
    /** 代表者肩書区分 */
    private String titleOfDaihyo;
    
    /** 決算期1 */
    private String uaiCorpSettlementTerm1;
    
    /** 決算期2 */
    private String uaiCorpSettlementTerm2;
    
    /** 口座開設年月日 */
    private String uaiOpenAcctDate;
    
    /** ジュニアNISA払出制限期間終了日 */
    private String uaiJnisaSeigenShuryoYmd;
    
    /** 閲覧可能部店コード */
    private String visibleButenCode;
    
    /** ログインＩＤ・取引ＰＷの配布状況 */
    private String idPwFlg;
    
    /** 社員営業員（ＣＲ）コード */
    private String empCrCd;
    
    /** 社員営業員（ＣＳ）コード */
    private String empCsCd;
    
    /** CR担当者名 */
    private String empCrName;
    
    /** CS担当者名 */
    private String empCsName;
    
    /** 手数料プラン */
    private String commission;
    
    /** 新規手数料適用日付 */
    private String newCommissionDate;
    
    /** ユーザーネーム（ログインID） */
    private String userName;
    
    /** 店群情報取得 */
    private String tenGunId;
    
    /** CCSログインID */
    private String ccsOpId;

}

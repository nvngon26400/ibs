package com.sbisec.helios.gw.brokerageMenu.ipoPo.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/2/6 新規作成
 *
 * @author SCSK 江口
 * 
 */
@Data
public class IfaSubscriptInputA002ApiRequest {
    
    /** 銘柄名称. */
    @NotEmpty(message = "銘柄名称")
    private String brandName;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 発行価格（数値(小数)）. */
    @Digits(integer = 12, fraction = 2, message = "発行価格")
    @NotEmpty(message = "発行価格")
    @Size(max = 18, message = "発行価格")
    private String issueBbPrice;
    
    /** 募集期間（To）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "募集期間（To）")
    @Size(min = 10, max = 10, message = "募集期間（To）")
    private String bbPeriodTo;
    
    /** 顧客名（漢字）（全角半角）. */
    @NotEmpty(message = "顧客名（漢字）")
    @Size(max = 72, message = "顧客名（漢字）")
    private String customerNameKanji;
    
    /** 電子交付同意. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "電子交付同意")
    @Size(min = 10, max = 10, message = "電子交付同意")
    private String edelivAgreementDate;
    
    /** 目論見書閲覧. */
    @DateTimeFormat(pattern = "yy/MM/dd HH:mm")
    @JsonFormat(pattern = "yy/MM/dd HH:mm")
    @NotEmpty(message = "目論見書閲覧")
    @Size(min = 16, max = 16, message = "目論見書閲覧")
    private String readTime;
    
    /** 抽選結果. */
    @NotEmpty(message = "抽選結果")
    private String lotteryResult;
    
    /** 当選株数（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "当選株数")
    @NotEmpty(message = "当選株数")
    @Size(max = 13, message = "当選株数")
    private String bbQuantityAlloc;
    
    /** 注文状況（全角半角）. */
    @NotEmpty(message = "注文状況")
    @Size(max = 4, message = "注文状況")
    private String orderStatus;
    
    /** 数量（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "数量")
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String quantity;
    
    /** 約定金額（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "約定金額")
    @NotEmpty(message = "約定金額")
    @Size(max = 19, message = "約定金額")
    private String contractAmount;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String jutyuKbn;
    
    /** 目論見書の交付方法（半角英数字）. */
    @NotEmpty(message = "目論見書の交付方法")
    @Size(min = 1, max = 1, message = "目論見書の交付方法")
    private String mokuromiKoufuKbn;
    
    /** 重要事項の説明. */
    @NotEmpty(message = "重要事項の説明")
    private String importantMatterType;
    
    /** 備考（全角半角）. */
    @NotEmpty(message = "備考")
    @Size(max = 200, message = "備考")
    private String bbRemark;
    
    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "[0-9]+", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** ブックビルディング申込期間（開始）（全角半角）. */
    @NotEmpty(message = "ブックビルディング申込期間（開始）")
    @Size(max = 8, message = "ブックビルディング申込期間（開始）")
    private String bookBuildingPresentationFrom;
    
    /** 明細番号（全角半角）. */
    @NotEmpty(message = "明細番号")
    @Size(max = 3, message = "明細番号")
    private String detailNumber;
    
    /** 仲介業者コード（数字）. */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp = "[0-9]+", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    @NotEmpty(message = "仲介業者営業員コード")
    @Size(min = 4, max = 4, message = "仲介業者営業員コード")
    private String brokerChargeCode;
    
    /** 扱者コード（半角英数字）. */
    @NotEmpty(message = "扱者コード")
    @Size(max = 6, message = "扱者コード")
    private String dealerNumber;
    
    /** 顧客コード（数字）. */
    @Digits(integer = 9, fraction = 0, message = "顧客コード")
    @NotEmpty(message = "顧客コード")
    @Pattern(regexp = "[0-9]+", message = "顧客コード")
    @Size(max = 9, message = "顧客コード")
    private String customerCode;
    
    /** 上場日. */
    @NotEmpty(message = "上場日")
    private String presentationDate;
    
    /** 更新時間（注文排他用）. */
    @DateTimeFormat(pattern = "yy/MM/dd HH:mm:ss")
    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @NotEmpty(message = "更新時間（注文排他用）")
    @Size(min = 19, max = 19, message = "更新時間（注文排他用）")
    private String updateTimeForOrderExclusivity;
    
    /** 売買単位（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "売買単位")
    @NotEmpty(message = "売買単位")
    @Size(max = 13, message = "売買単位")
    private String unit;
    
    /** 売買単位区分（半角英数字）. */
    @NotEmpty(message = "売買単位区分")
    @Size(min = 1, max = 1, message = "売買単位区分")
    private String sellBuyUnitType;
    
    /** 配分上限株数（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "配分上限株数")
    @NotEmpty(message = "配分上限株数")
    @Size(max = 13, message = "配分上限株数")
    private String maxAllocation;
    
    /** 送信・訂正用ロジック処理判定フラグ（全角半角）. */
    @NotEmpty(message = "送信・訂正用ロジック処理判定フラグ")
    @Size(max = 15, message = "送信・訂正用ロジック処理判定フラグ")
    private String sendCorrectLogicJudgeFlag;
    
    /** 訂正前_勧誘区分. */
    @NotEmpty(message = "訂正前_勧誘区分")
    @Size(max = 2, message = "訂正前_勧誘区分")
    private String solicitTypeName;
    
    /** 訂正前_受注方法. */
    @NotEmpty(message = "訂正前_受注方法")
    private String receiveOrderTypeName;
    
    /** 訂正前_目論見書の交付方法. */
    @NotEmpty(message = "訂正前_目論見書の交付方法")
    @Size(min = 1, max = 1, message = "訂正前_目論見書の交付方法")
    private String prospectusIssueMethodWord;
    
    /** 訂正前_重要事項の説明. */
    @NotEmpty(message = "訂正前_重要事項の説明")
    private String importantMatterType2;
    
    /** 訂正前_備考. */
    @NotEmpty(message = "訂正前_備考")
    @Size(max = 200, message = "訂正前_備考")
    private String bbRemark2;
    
    /** 訂正前_数量. */
    @Digits(integer = 15, fraction = 0, message = "訂正前_数量")
    @NotEmpty(message = "訂正前_数量")
    @Size(max = 19, message = "訂正前_数量")
    private String domesticQuantityInput;
    
    /** 訂正前_約定金額. */
    @Digits(integer = 15, fraction = 0, message = "訂正前_約定金額")
    @NotEmpty(message = "訂正前_約定金額")
    @Size(max = 19, message = "訂正前_約定金額")
    private String subscriptTradeAmount;
    
    /** 訂正前_預り区分. */
    @NotEmpty(message = "訂正前_預り区分")
    @Size(max = 20, message = "訂正前_預り区分")
    private String depositType2;
    
    /** ジュニアNISAフラグ（半角英数字）. */
    @NotEmpty(message = "ジュニアNISAフラグ")
    @Size(min = 1, max = 1, message = "ジュニアNISAフラグ")
    private String juniorNisaFlag;
    
    /** つみたてNISAフラグ（半角英数字）. */
    @NotEmpty(message = "つみたてNISAフラグ")
    @Size(min = 1, max = 1, message = "つみたてNISAフラグ")
    private String accumulateNisaFlag;
    
    /** ISA買付可能判定区分（当年）（半角英数字）. */
    @NotEmpty(message = "ISA買付可能判定区分（当年）")
    @Size(min = 1, max = 1, message = "ISA買付可能判定区分（当年）")
    private String isaBuyAbleJudgeClassificationYear;
    
    /** ISA契約区分. */
    @NotEmpty(message = "ISA契約区分")
    private String isaContractType;
    
    /** NISA買付可能額. */
    @NotEmpty(message = "NISA買付可能額")
    private String nisaBuyPotentialAmount;
    
    /** 特定口座区分. */
    private String tokuteiKouzaKbn;
}

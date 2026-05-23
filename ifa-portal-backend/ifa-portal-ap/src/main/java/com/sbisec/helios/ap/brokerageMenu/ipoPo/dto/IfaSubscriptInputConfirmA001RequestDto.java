package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;


import lombok.Data;

@Data
public class IfaSubscriptInputConfirmA001RequestDto {

    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 送信・訂正用ロジック処理判定フラグ（全角半角）. */
    private String sendCorrectLogicJudgeFlag;
    
    /** 上場日. */
    private String presentationDate;
    
    /** 募集期間（To）. */
    private String bbPeriodTo;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 売買単位（数値(整数)）. */
    private String unit;
    
    /** 売買単位区分（半角英数字）. */
    private String sellBuyUnitType;
    
    /** 当選株数（数値(整数)）. */
    private String bbQuantityAlloc;
    
    /** 配分上限株数（数値(整数)）. */
    private String maxAllocation;
    
    /** 注意情報アラート確認. */
    private String noteInfoCheckbox;
    
    /** お知らせアラート確認. */
    private String noteLimitCheck;
    
    /** 顧客コード（数字）. */
    private String customerCode;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 預り区分アラート確認. */
    private String depositTypeConfirm;
    
    /** コンプラランクチェック確認. */
    private String invitationCheck;
    
    /** コンプラランクチェック.チェックボックス文言 */
    private String complianceCheckMsg;
    
    /** 約定金額（数値(整数)）. */
    private String contractAmount;
    
    /** 備考（全角半角）. */
    private String bbRemark;
    
    /** 訂正前_備考. */
    private String bbRemark2;
    
    /** 電子交付同意. */
    private String edelivAgreementDate;
    
    /** 目論見書閲覧. */
    private String readTime;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** ブックビルディング申込期間（開始）（全角半角）. */
    private String bookBuildingPresentationFrom;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 扱者コード（半角英数字）. */
    private String dealerNumber;
    
    /** 抽選結果. */
    private String lotteryResult;
    
    /** 発行価格（数値(小数)）. */
    private String issueBbPrice;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String jutyuKbn;
    
    /** 目論見書の交付方法（半角英数字）. */
    private String mokuromiKoufuKbn;
    
    /** 重要事項の説明. */
    private String importantMatterType;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 注文フラグ（半角英数字）. */
    private String orderFlag;

}

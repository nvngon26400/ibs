package com.sbisec.helios.ap.brokerageMenu.ipoPo.dto;

import lombok.Data;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/2/6 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSubscriptInputA003RequestDto {
    
    /** 銘柄名称 */
    private String brandName;
    
    /** 銘柄コード（半角英数字） */
    private String brandCode;
    
    /** 募集期間（To） */
    private String bbPeriodTo;
    
    /** 顧客名（漢字）（全角半角） */
    private String customerNameKanji;
    
    /** 抽選結果 */
    private String lotteryResult;
    
    /** 当選株数（数値(整数)） */
    private String bbQuantityAlloc;
    
    /** 注文状況（全角半角） */
    private String orderStatus;
    
    /** 募集受注日時 */
    private String recruitmentOrderDate;
    
    /** 数量（数値(整数)） */
    private String quantity;
    
    /** 約定金額（数値(整数)） */
    private String contractAmount;
    
    /** 預り区分（全角半角） */
    private String depositType;
    
    /** 勧誘区分（全角半角） */
    private String kanyuKbn;
    
    /** 受注方法 */
    private String jutyuKbn;
    
    /** 目論見書の交付方法（半角英数字） */
    private String mokuromiKoufuKbn;
    
    /** 重要事項の説明 */
    private String importantMatterType;
    
    /** 備考（全角半角） */
    private String bbRemark;
    
    /** 部店コード（半角英数字） */
    private String butenCode;
    
    /** 口座番号（数字） */
    private String accountNumber;
    
    /** ブックビルディング申込期間（開始）（全角半角） */
    private String bookBuildingPresentationFrom;
    
    /** 明細番号（全角半角） */
    private String detailNumber;
    
    /** 更新時間（注文排他用） */
    private String updateTimeForOrderExclusivity;
    
    /** 売買単位区分（半角英数字） */
    private String sellBuyUnitType;
    
    /** 送信・訂正用ロジック処理判定フラグ（全角半角） */
    private String sendCorrectLogicJudgeFlag;
    
}

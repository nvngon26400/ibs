package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import lombok.Data;

/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaLevelFeeA002LevelFeeResponseDto {

    /** 年月日 */
    private String dateYmd;

    /** 年月 */
    private String dateYm;

    /** 仲介業者コード（数字） */
    private String brokerCode;

    /** 仲介業者名（全角半角） */
    private String brokerName;

    /** 営業員コード（半角英数字） */
    private String empCode;

    /** 営業員名（全角半角） */
    private String brokerChargeName;

    /** 部店コード（半角英数字） */
    private String butenCode;

    /** 口座番号（数字） */
    private String accountNumber;

    /** 顧客名_姓名(漢字)（全角半角） */
    private String customerNameKanji;

    /** 顧客名_姓名(カナ)（全角半角） */
    private String customerNameKana;

    /** 扱者コード（半角英数字） */
    private String dealerNumber;
    
    /** 計算対象残高 */
    private String valuationTotalJpyAmount;

    /** 徴収額(税抜) */
    private String fee;

    /** 支店コード（数字） */
    private String branchCode;

    /** 支店名（全角半角） */
    private String branchName;

}
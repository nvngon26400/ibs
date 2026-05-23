package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.math.BigDecimal;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 入出金明細CSVアイテム
 *
 * @author SCSK
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaDepositWithdrawDetailCsvItems extends ModelBase {

    private static final long serialVersionUID = -686239847404246835L;

    /** 部店. */
    private String buten;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 取引コース（全角半角）. */
    private String course;

    /** 顧客名(漢字). */
    private String customerNameKanji;

    /** 顧客名(カナ). */
    private String customerNameKana;
    
    /** 入出金日. */
    private String depositsAndWithdrawalsDate;

    /** 取引（全角半角）. */
    private String trade;

    /** 取引区分(画面表示用) */
    private String openTradeKbn;
    
    /** 摘要（全角半角）. */
    private String dispAbstract;
    
    /** 出金額. */
    private String withdraw;
    
    /** 入金額. */
    private String deposit;
    
    /** 受渡金額（数値(整数)）. */
    private BigDecimal deliveryAmount;
    
    /** 振替出金額. */
    private String transferWithdraw;
    
    /** 振替入金額. */
    private String transferDeposit;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 営業員名（全角半角）. */
    private String brokerChargeName;

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者名（全角半角）. */
    private String brokerName;

    /** 支店コード（数字）. */
    private String branchCode;

    /** 支店名（全角半角）. */
    private String branchName;
}

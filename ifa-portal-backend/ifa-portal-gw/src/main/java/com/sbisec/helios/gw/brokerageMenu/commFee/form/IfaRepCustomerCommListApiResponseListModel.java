package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 担当顧客別手数料
 *
 * @author 宇田川達弥
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaRepCustomerCommListApiResponseListModel {

    /** 集計単位. */
    private String chargeCustomerCountingUnit;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 営業員名（全角半角）. */
    private String brokerChargeName;
    
    /** 仲介業支店コード. */
    private String brokerageBranchCode;
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
    /** 手数料合計（数値(小数)）. */
    private String commTotal;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** 扱者コード（半角英数字）. */
    private String dealerNumber;
    
    /** コース名. */
    private String courseName;
    
}

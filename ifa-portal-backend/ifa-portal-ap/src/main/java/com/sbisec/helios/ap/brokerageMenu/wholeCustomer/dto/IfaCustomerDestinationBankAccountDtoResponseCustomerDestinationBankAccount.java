package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.sbibits.earth.model.ModelBase;

/**
 * 画面ID：SUB020303-01
 * 画面名：顧客振込先金融機関口座

 * @author 大崎 辰弥
    2023/10/27 新規作成
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class IfaCustomerDestinationBankAccountDtoResponseCustomerDestinationBankAccount 
        extends ModelBase {
    /**
     * シリアルナンバー
     */
    private static final long serialVersionUID = -6796397794379097878L;

    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 仲介業者担当者名（全角半角）. */
    private String employeeName;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 契約締結前交付書面コード名. */
    private String customerAttributeName;
    
    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** 銀行名(漢字). */
    private String bankNameKanji;
    
    /** 支店名(漢字). */
    private String branchNameKanji;
    
    /** 預金種別（全角半角）. */
    private String depositType;
    
    /** 振込口座番号. */
    private String destinationAccountNumber;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業支店コード. */
    private String brokerageBranchCode;
    
    /** 仲介業者支店名. */
    private String branchNameOfBroker;

}

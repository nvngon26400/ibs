package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

@Data
public class IfaMarginPositionListForeignA002bApiRequest {
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** コース. */
    private String course;
    
    /** 契約締結前交付書面コード（全角半角）. */
    private String customerAttribute;
    
    /** プロファイル値. */
    private String apiProfile;
    
    /** 営業員コード */
    private String empCode;
    
    /** 営業員名 */
    private String brokerChargeName;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 支店コード */
    private String branchCode;
    
    /** 支店名 */
    private String branchName;
    
    /** ティッカー（全角半角）. */
    private String ticker;
    
}

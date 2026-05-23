package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticA002bRequestDto {
    
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
    
    /** プロファイル値. */
    private String aPIProfile;
    
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
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
       
}

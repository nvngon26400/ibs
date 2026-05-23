package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaMutualFundPriceChangeBrandHoldingListA004aApiRequest {
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    
    /** 支店コード */
    private String branchCode;
    
    /** 営業員コード */
    private String empCode;
    
    /** 部店コード */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 顧客名（漢字／カナ） */
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件 */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース. */
    @NotEmpty(message = "取引コース")
    private List<IfaMutualFundPriceChangeBrandHoldingListApiRequestCourseSelected> course;
    
    /** ステータス */
    private String status;
    
    /** 投信協会コード */
    private String investmentTrustAssociationCode;

    /** 期間指定（From） */
    private String periodYmFrom;
    
    /** 期間指定（To） */
    private String periodYmTo;
    
}

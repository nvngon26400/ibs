package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaCouponRedemptionPaymentScheduleListA002ApiRequest {
    
    /** 仲介業者コード（数字）. */
    @Pattern(regexp="0-9", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;
    
    /** 仲介業者除外（半角英数字）. */
    @Size(min = 1, max = 1, message = "仲介業者除外")
    private String chkBrokerCodeExclude;
    
    /** 支店コード（数字）. */
    @Pattern(regexp="0-9", message = "支店コード")
    @Size(max = 3, message = "支店コード")
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    @Size(min = 4, max = 4, message = "営業員コード")
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @Pattern(regexp="0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** 顧客名（漢字／カナ）. */
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース（全角半角）. */
    @Size(max = 40, message = "取引コース")
    private List<IfaCouponRedemptionPaymentScheduleListSelectedApiRequest> course;
    
    /** 期間指定ＦＲＯＭ. */
    private String periodYmFrom;
    
    /** 期間指定ＴＯ. */
    private String periodYmTo;
    
    /** 証券種別. */
    private String securiytClass;
    
    /** 銘柄コード（半角英数字）. */
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;

}

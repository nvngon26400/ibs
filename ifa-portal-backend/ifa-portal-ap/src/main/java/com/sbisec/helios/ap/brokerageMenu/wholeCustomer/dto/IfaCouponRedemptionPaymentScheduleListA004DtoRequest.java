package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaCouponRedemptionPaymentScheduleListA004DtoRequest {

    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者コード（数字）. */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名（漢字／カナ）. */
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース（全角半角）. */
    private List<IfaCouponRedemptionPaymentScheduleListDtoRequestCourseSelected> course;
    
    /** 期間指定ＦＲＯＭ. */
    private String periodYmFrom;
    
    /** 期間指定ＴＯ. */
    private String periodYmTo;
    
    /** 証券種別. */
    private List<String> securiytClassList;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;

}

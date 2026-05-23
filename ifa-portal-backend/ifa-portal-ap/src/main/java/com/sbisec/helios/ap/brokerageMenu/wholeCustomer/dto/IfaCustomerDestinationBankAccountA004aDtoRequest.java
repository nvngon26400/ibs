package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB020303-01
 * 画面名：顧客振込先金融機関口座

 * @author 大崎 辰弥
    2023/10/27 新規作成
 */

@Data
public class IfaCustomerDestinationBankAccountA004aDtoRequest {

    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者除外（全角半角）. */
    private String chkBrokerCodeExclude;
    
    /** 支店コード（数字）. */
    private String branchCode;
    
    /** 営業員コード（半角英数字）. */
    private String empCode;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客名（漢字／カナ）（全角半角）. */
    private String customerNameKanjiKana;
    
    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;
    
    /** 取引コース. */
    private List<IfaCustomerDestinationBankAccountDtoRequestCourseSelected> courseSelected;

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;

}

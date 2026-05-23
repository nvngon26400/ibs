package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB020303-01
 * 画面名：顧客振込先金融機関口座

 * @author 大崎 辰弥
    2023/10/27 新規作成
 */

@Data
public class IfaCustomerDestinationBankAccountSql001RequestModel {
    /** 上限件数 */
    private int maxRow;
    
    /** FCT030.仲介業者営業員リスト */
    private List<IfaCustomerDestinationBankAccountSql001RequestModelBrokerCharge> brokerChargeList;
    
    /** 仲介業者コード. */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外. */
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
    private List<IfaCustomerDestinationBankAccountSql001RequestModelCourseSelected> courseSelected;
    
    /** 権限コード */
    private String privId;

}

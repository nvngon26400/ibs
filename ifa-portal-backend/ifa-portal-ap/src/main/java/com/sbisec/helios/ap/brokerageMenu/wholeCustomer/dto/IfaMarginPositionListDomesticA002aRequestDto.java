package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaMarginPositionListDomesticA002aRequestDto {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
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
    
    /** 取引コース（全角半角）. */
    private List<IfaMarginPositionListDomesticA002aRequestDtoCourseSelected> course;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 画面ID. */
    // FCT038利用のため追加.ap内で取得できないためcontroller側で取得し設定する.
    private String screenId;
    
}

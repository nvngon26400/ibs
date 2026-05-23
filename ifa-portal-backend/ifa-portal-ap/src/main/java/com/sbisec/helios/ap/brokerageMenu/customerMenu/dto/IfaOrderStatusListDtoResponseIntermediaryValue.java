package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0104-01
 * 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *          2023/10/16 新規作成
 */

@Data
public class IfaOrderStatusListDtoResponseIntermediaryValue {

    /** 証券金銭種別（全角半角）. */
    private String productCd;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 国籍コード（全角半角）. */
    private String countryCd;
    
    /** 媒介可否. */
    private String mediatePropriety;

}

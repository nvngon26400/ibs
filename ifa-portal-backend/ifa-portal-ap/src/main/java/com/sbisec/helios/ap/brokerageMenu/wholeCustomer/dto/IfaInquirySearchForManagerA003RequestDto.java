package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 * A003リクエストDto
 *
 * @author SBI大連 夏
 * @date   2025/08/15
 */

@Data
public class IfaInquirySearchForManagerA003RequestDto {

    /** 問合せカテゴリ(大分類) */
    private String toiawaseDCd;
    
    /** 問合せカテゴリ(中分類) */
    private String toiawaseCd;
}

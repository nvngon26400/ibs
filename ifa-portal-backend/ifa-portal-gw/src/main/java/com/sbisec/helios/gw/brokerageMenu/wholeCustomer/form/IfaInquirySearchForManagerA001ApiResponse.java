package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;

import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaContactToiawaseList;

import lombok.Data;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/08/15
 */

@Data
public class IfaInquirySearchForManagerA001ApiResponse {

    /** 接触履歴（入力）検索画面のコメント. */
    private String comment;
    
    /** 問合せカテゴリリスト */
    private List<IfaContactToiawaseList> toiawaseDList;
    
}

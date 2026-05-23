package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import java.util.List;

import lombok.Data;

/**
 * 担当顧客別手数料一覧初期化APIレスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListA001ApiResponse {
    
    /** 現株ポイント参照可能リスト. */
    private List<String> spotStockPointReferenceAbleList;
    
    /** 担当顧客別手数料コメント. */
    private String repCustomerCommComment;
    
}

package com.sbisec.helios.ap.brokerageMenu.commFee.dao.model;

import lombok.Data;

/**
 * 担当顧客別手数料一覧現株ポイント参照可能仲介業者取得レスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListSql002ResponseModel {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
}

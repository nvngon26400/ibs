package com.sbisec.helios.ap.brokerageMenu.customerList.dao.model;

import lombok.Data;

/**
 * 顧客一覧_基本 SQL002 レスポンスモデル
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaCustomerListSql002ResponseModel {
    
    /** CSV出力出力最大件数. */
    private String csvOutputMaxCount;
    
}

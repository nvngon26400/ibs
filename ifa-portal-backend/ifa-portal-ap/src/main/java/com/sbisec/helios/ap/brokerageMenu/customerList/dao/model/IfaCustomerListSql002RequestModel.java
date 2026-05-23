package com.sbisec.helios.ap.brokerageMenu.customerList.dao.model;

import lombok.Data;

/**
 * 顧客一覧_基本 SQL002 リクエストモデル
 *
 * @author SCSK池田
 * 
 */
@Data
public class IfaCustomerListSql002RequestModel {
    
    /** パラメータ項目値（全角半角）. */
    private String paramValue;
    
    /** 権限コード （全角半角）. */
    private String privId;
    
}

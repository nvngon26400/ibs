package com.sbisec.helios.gw.brokerageMenu.customerList.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 顧客一覧_基本 CSVダウンロード リクエストパラメータ
 *
 * @author SCSK池田
 *
 */
@Data
@JsonSerialize
public class IfaCustomerListA005DownloadApiRequest {
    
    /** CSVファイル名 */
    private String csvDownloadFile;
    
    /** データパターン */
    private String pattern;
}

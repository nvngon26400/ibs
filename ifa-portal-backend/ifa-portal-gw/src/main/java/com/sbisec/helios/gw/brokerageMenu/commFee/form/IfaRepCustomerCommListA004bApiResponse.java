package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import lombok.Data;

/**
 * 担当顧客別手数料一覧CSVダウンロードAPIレスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListA004bApiResponse {
    
    /** CSVファイル名. */
    private String csvFileName;
    
    /** CSV出力データ. */
    private String csvData;
    
}

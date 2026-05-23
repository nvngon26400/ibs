package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import lombok.Data;

/**
 * 担当顧客別手数料一覧CSVダウンロードAPIリクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaRepCustomerCommListA004bApiRequest {
    
    /**　CSVダウンロードファイル名 */
    private String csvDownloadFile;
    
}

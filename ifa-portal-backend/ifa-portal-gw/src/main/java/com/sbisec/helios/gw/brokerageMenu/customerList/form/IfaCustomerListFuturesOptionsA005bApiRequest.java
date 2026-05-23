package com.sbisec.helios.gw.brokerageMenu.customerList.form;

import lombok.Data;

/**
 * 顧客一覧_先OP CSVダウンロード
 *
 * @author SCSK
 *
 */
@Data
public class IfaCustomerListFuturesOptionsA005bApiRequest {
    
    /** CSVファイル名 */
    private String csvDownloadFile;
}

package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import lombok.Data;

/**
 * 為替取引履歴　A004b　リクエスト

 * @author SCSK川崎
 */
@Data
public class IfaFxTradeHistoryA004bApiRequest {
    
    /** CSVファイル名 */
    private String csvDownloadFile;
    
}

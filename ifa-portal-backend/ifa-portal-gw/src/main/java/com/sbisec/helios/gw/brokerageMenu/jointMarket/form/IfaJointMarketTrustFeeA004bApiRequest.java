package com.sbisec.helios.gw.brokerageMenu.jointMarket.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
* 画面ID：SUB0208_02
* 画面名：共同店舗 信託報酬
*
* @author SBI大連 董
2024/12/12 新規作成
*/
@Data
public class IfaJointMarketTrustFeeA004bApiRequest {
    
    /** CSVファイル名 */
    @NotEmpty(message = "CSVファイル名")
    private String csvDownloadFile;
    
    /** CSVダウンロードパターン */
    @NotEmpty(message = "CSVダウンロードパターン")
    private String pattern;
    
}

package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 画面ID：SUB020503-01
 * 画面名：信託報酬
 *
 * @author SCSK 仁井田
 2024/06/11 新規作成
 */
@Data
public class IfaTrustFeeA004bApiRequest {
    
    /** CSVファイル名 */
    @NotEmpty(message = "CSVファイル名")
    private String csvDownloadFile;
    
    /** CSVダウンロードパターン */
    @NotEmpty(message = "CSVダウンロードパターン")
    private String pattern;
    
}

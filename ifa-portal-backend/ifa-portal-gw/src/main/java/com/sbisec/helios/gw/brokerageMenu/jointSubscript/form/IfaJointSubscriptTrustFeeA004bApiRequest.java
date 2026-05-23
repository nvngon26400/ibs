package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 画面ID：SUB0206_03-01
 * 画面名：共同募集　信託報酬
 *
 * @author SBI 苗萌
 * 2024/12/18 新規作成
 */
@Data
public class IfaJointSubscriptTrustFeeA004bApiRequest {
    
    /** CSVファイル名 */
    @NotEmpty(message = "CSVファイル名")
    private String csvDownloadFile;
    
    /** CSVダウンロードパターン */
    @NotEmpty(message = "CSVダウンロードパターン")
    private String pattern;
    
}

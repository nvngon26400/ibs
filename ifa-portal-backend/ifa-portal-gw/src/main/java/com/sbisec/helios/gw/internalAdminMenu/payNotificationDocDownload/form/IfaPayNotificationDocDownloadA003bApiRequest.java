package com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
@Data
public class IfaPayNotificationDocDownloadA003bApiRequest {
    
    /** PDFファイル名 */
    @NotEmpty(message = "PDFファイル名")
    private String pdfFileName;
    
}

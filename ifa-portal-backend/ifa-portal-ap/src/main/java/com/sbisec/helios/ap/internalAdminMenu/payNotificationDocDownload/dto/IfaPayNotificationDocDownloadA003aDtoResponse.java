package com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto;

import lombok.Data;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
@Data
public class IfaPayNotificationDocDownloadA003aDtoResponse {
    
    /** PDFファイル名 */
    private String pdfFileName;
    
    /** PDFファイル名(出力用)  */
    private String pdfFileOutputName; 
}

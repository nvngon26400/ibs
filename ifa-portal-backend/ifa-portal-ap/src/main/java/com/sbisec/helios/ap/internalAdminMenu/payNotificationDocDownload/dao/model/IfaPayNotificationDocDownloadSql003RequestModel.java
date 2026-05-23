package com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
@Data
public class IfaPayNotificationDocDownloadSql003RequestModel {
    
    /** 年月 */
    private String dateYm;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** バージョン番号 */
    private String versionNumber;
    
}

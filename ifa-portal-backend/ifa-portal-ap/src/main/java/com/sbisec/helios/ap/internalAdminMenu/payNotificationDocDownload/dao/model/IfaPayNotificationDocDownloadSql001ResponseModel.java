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
public class IfaPayNotificationDocDownloadSql001ResponseModel {
    
    /** 機能ID */
    private String functionId;
    
    /** カテゴリID */
    private String categoryId;
    
    /** ファイルディレクトリ */
    private String fileDirectory;
    
}

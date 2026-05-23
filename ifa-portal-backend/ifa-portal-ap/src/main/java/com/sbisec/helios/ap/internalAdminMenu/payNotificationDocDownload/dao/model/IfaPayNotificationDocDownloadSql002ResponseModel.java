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
public class IfaPayNotificationDocDownloadSql002ResponseModel {
    
    /** 年月 */
    private String dateYm;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** バージョン番号 */
    private String versionNumber;
    
    /** 初回確認日時 */
    private String firstConfirmDateTime;
    
    /** 最終確認日時 */
    private String lastConfirmDateTime;
    
    /** DL */
    private String dl;
    
}

package com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
@Data
public class IfaPayNotificationDocDownloadA003aDtoRequest {
    
    /** 年月. */
    private String dateYm;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** バージョン番号. */
    private String versionNumber;
    
    /** ファイルディレクトリ（全角半角）. */
    private String fileDirectory;
    
    /** 前回検索時の対象年月From. */
    private String beforeSearchTargetDateYmFrom;
    
    /** 前回検索時の対象年月To. */
    private String beforeSearchTargetDateYmTo;
    
    /** 前回検索時の仲介業者コードリスト. */
    private List<String> beforeSearchBrokerCodeList;
    
    /** 前回検索時の仲介業者除外. */
    private String beforeSearchChkBrokerCodeExclude;
    
}

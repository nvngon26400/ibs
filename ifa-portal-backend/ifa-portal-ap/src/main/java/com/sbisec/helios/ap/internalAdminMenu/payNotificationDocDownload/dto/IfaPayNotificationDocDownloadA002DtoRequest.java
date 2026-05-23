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
public class IfaPayNotificationDocDownloadA002DtoRequest {
    
    /** 対象年月From */
    private String targetDateYmFrom;
    
    /** 対象年月To */
    private String targetDateYmTo;
    
    /** 仲介業者コードリスト */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
    
}

package com.sbisec.helios.gw.internalAdminMenu.payNotificationDocDownload.form;

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
public class IfaPayNotificationDocDownloadA002ApiResponse {
    
    /** 仲介業者支払通知書情報データリスト. */
    private List<IfaPayNotificationDocDownloadA002BrokerPayNotificationDocInfoApiResponse> brokerPayNotificationDocInfoDateList;
    
    /** 前回検索時の対象年月From. */
    private String beforeSearchTargetDateYmFrom;
    
    /** 前回検索時の対象年月To. */
    private String beforeSearchTargetDateYmTo;
    
    /** 前回検索時の仲介業者コード. */
    private String beforeSearchBrokerCode;
    
    /** 前回検索時の仲介業者除外. */
    private String beforeSearchChkBrokerCodeExclude;
    
}

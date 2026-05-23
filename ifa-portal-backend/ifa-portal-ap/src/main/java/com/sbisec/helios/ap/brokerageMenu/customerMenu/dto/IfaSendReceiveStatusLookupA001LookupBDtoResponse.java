package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0703-01 画面名：受発信状況照会
 *
 * @author SBI大連 董 2025/03/20 新規作成
 */
@Data
public class IfaSendReceiveStatusLookupA001LookupBDtoResponse {
    
    /** 資料No */
    private String requestNo;

    /** 受発送日 */
    private String changeStatusDate;

    /** 受発信状況 */
    private String st;

    /** 資料名 */
    private String paperName;
    
    /** 目論見書番号 */
    private String prospectusNo;
    
    /** 内容 */
    private String contents;
    
    /** 備考 */
    private String remarks;
    
    /** 理由名称 */
    private String purposeName;
    
    /** 通数 */
    private String copies;
    
    /** 請求日時 */
    private String requestDate;
    
    /** 取扱者 */
    private String agentName;
    
    /** 郵便記録 */
    private String sendMail;
}

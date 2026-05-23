package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;


import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IfaSendReceiveStatusLookupCsvItems extends ModelBase {
    /** シリアルバージョンID */
    private static final long serialVersionUID = 1L;

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

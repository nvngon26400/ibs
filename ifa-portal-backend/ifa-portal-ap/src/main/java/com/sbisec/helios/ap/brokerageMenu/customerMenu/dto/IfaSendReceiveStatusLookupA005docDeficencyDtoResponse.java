package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
* 画面ID：SUB0202_0703-01
* 画面名：受発信状況照会
*
* @author SBI大連 董
*2025/03/20 新規作成
*/

@Data
public class IfaSendReceiveStatusLookupA005docDeficencyDtoResponse {
    /** 連番 */
    private String ddiRequestSubNo;
    /** 不備理由 */
    private String ddiReason;
    /** 備考 */
    private String ddiRemarks;

}

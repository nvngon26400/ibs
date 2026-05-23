package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto;

import lombok.Data;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/05/22
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto {

    /** 媒介口座明細コメント. */
    private String comment;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 決算月. */
    private String closingMonth;
    
    /** 仲介業者コード自動表示フラグ. */
    private String BrokerCodeAutoDispFlag;
    
    /** エラーメッセージ. */
    private String errorMessage;
}

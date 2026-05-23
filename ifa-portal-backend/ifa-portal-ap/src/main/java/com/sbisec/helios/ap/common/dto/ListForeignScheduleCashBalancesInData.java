package com.sbisec.helios.ap.common.dto;

import lombok.Data;

/**
 * 外貨金銭残高スケジュール取得APIInDto
 *
 * @author SCSK
 */
@Data
public class ListForeignScheduleCashBalancesInData {
    
    // Header.token
    private String headerToken;
    
    // パラメータ
    private Parameter parameter;
    
    /**
     * パラメータ
     *
     * @author SCSK
     */
    @Data
    public static class Parameter {
        
        // 通貨コード
        private String currencyCode;
        
        // 口座分類
        private String accountKind;
        
        // 取得日数
        private String days;
        
    }
    
}

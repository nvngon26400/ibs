package com.sbisec.helios.ap.bizcommon.model;

import java.util.List;

import lombok.Data;

/**
 * 共通関数DTO：FCT030
 * 
 * @author base 熊
 */

@Data
public class OutputFct030Dto extends BaseOutputDto {
    
    // 仲介業者営業員リスト
    private List<BrokerCharge> brokerChargeList;
    
    /**
     * 仲介業者営業員
     * @author base 熊
     */
    @Data
    public static class BrokerCharge {
        
        //仲介業者コード
        private String brokerCode;
        
        //営業員コード
        private String empCode;
        
    }
}

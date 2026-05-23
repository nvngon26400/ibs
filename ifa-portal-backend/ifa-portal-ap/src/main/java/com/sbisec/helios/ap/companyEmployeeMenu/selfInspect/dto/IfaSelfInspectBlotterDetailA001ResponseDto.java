package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SUB0506_01-02_自己点検記録簿詳細 DTO応答
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IfaSelfInspectBlotterDetailA001ResponseDto {
    
    /**
     * 自己点検
     *
     * @author SCSK
     *
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SelfAssessment {
        
        /** 自己点検項目名. */
        private String selfInspectItemName;
        
        /** 確認. */
        private String confirmation;
        
        /** 回答結果（半角英数字）. */
        private String answerResult;
        
        /** 回答理由（全角半角）. */
        private String answerReason;
        
    }
    
    /** 登録年月. */
    private String registerDate;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 自己点検リスト. */
    private List<SelfAssessment> selfAssessmentList;
    
    /** メモ（全角半角）. */
    private String memo;
}
